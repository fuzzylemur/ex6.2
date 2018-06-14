
package oop.ex6.main;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;

public class CodeVerifier {

	private ArrayList<Method> methodArray;
	private ScopeVars globalVars;
	private Stack<ScopeVars> theStack;

	CodeVerifier(ArrayList<Method> methodArray){
		globalVars = new ScopeVars();
		this.methodArray = methodArray;

	}

	void validateMainLines(ArrayList<Line> mainLines) throws SjavacException{

		for (Line curLine : mainLines) {

			LineType lineType = curLine.type();
			verifyValues(curLine.varArray());

			switch (lineType) {

				case VAR_INIT: {
					globalVars.add(curLine.varArray());
					break;
				}

				case VAR_ASSIGN: {
					globalVars.contains(curLine.varArray().get(0));
					break;
				}
			}
			throw new SjavacException(Config.MSG_INVALID_MAIN_LINE, curLine.num());
		}
	}

	void validateMethod(Method method) throws SjavacException {

		theStack = new Stack<>();
		theStack.push(globalVars);
		ScopeVars localVars = new ScopeVars(method.params());

		ArrayList<Line> lineArray = method.lines();

		// No return before method ends.
		if (lineArray.get(lineArray.size()-2).type() != LineType.RETURN)
			throw new SjavacException(Config.MSG_MISSING_RETURN, lineArray.size()-2);

		for (Line curLine : lineArray) {
			try {

				verifyValues(curLine.varArray());
				LineType curLineType = curLine.type();

				switch (curLineType) {

					case VAR_INIT: {
						localVars.add(curLine.varArray());
						break;
					}

					case VAR_ASSIGN: {
						theStack.push(localVars);
						verifyVarUse(curLine.varArray().get(0), true);
						localVars = theStack.pop();
						break;
					}

					case BLOCK: {
						theStack.push(localVars);
						for (Variable var : curLine.varArray())
							verifyVarUse(var, false);
						localVars = new ScopeVars();
						break;
					}

					case CLOSE: {
						localVars = theStack.pop();
						break;
					}

					case METHOD_CALL: {
						verifyMethodCall(curLine);
						break;
					}
				}
			} catch (SjavacException ex) {
				ex.setLineNum(curLine.num());
				throw ex;
			}
		}
	}

	private void verifyValues(ArrayList<Variable> varArray) throws SjavacException{

		if (varArray == null)
			return;

		for (Variable var : varArray){

			String value = var.value();

			if (value == null) {
				if (var.isFinal())
					throw new SjavacException(Config.MSG_VAR_FINAL_NO_VALUE);
				continue;
			}

			Matcher m = VarType.getMatcher(VarType.VAR_NAME).reset(value);
			if (m.matches()) {
				Variable refVar = new Variable(VarType.VAR_NAME, value, null, false);
				verifyVarUse(refVar, false);
				return;
			}

			m = VarType.getMatcher(var.type()).reset(value);
			if (!m.matches())
				throw new SjavacException(Config.MSG_VAR_INVALID_VALUE);
		}
	}

	private void verifyVarUse(Variable varToCheck, boolean assign) throws SjavacException {

		for (ScopeVars scope : theStack){
			int ans = scope.contains(varToCheck);

			if (ans == 0) {
				if (assign)
					throw new SjavacException(Config.MSG_VAR_FINAL_ASSIGN);
				else
					return;
			}

			else if (ans == 1)
				return;
		}
		throw new SjavacException(Config.MSG_VAR_NO_INIT);
	}
}