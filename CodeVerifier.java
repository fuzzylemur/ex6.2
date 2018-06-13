
package oop.ex6.main;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;

import static oop.ex6.main.LineType.*;

public class CodeVerifier {

	ArrayList<Method> methodArray;
	ScopeVars globalVars;
	Stack<ScopeVars> theStack;

	CodeVerifier(ArrayList<Method> methodArray ){
		globalVars = new ScopeVars();
		this.methodArray = methodArray;

	}

	void validateMainLines(ArrayList<Line> mainLines) throws Exception{

		for (Line curLine : mainLines) {

			LineType lineType = curLine.type();
			verifyValues(curLine.varArray());

			switch (lineType) {

				case(VAR_INIT): {
					globalVars.add(curLine.varArray());
					break;
				}

				case(VAR_ASSIGN): {
					globalVars.contains(curLine.varArray().get(0));
					break;
				}
			}
			throw Exception;
		}
	}

	private

	void validateMethod(Method method) throws Exception {

		theStack = new Stack<>();
		theStack.push(globalVars);
		ScopeVars localVars = new ScopeVars(method.myParams());

		ArrayList<Line> lineArray = method.myLines();

		// No return before method ends.
		if (lineArray.get(lineArray.size() - 2).type() != RETURN) throw Exception();

		for (Line curLine : lineArray) {

			verifyValues(curLine.varArray());
			LineType curLineType = curLine.type();

			switch (curLineType) {

				case (VAR_INIT): {
					localVars.add(curLine.varArray());
					break;
				}

				case (VAR_ASSIGN): {
					theStack.push(localVars);
					verifyUse(curLine.varArray().get(0), true);
					localVars = theStack.pop();
					break;
				}

				case (BLOCK): {
					theStack.push(localVars);
					for (Variable var : curLine.varArray())
						verifyUse(var, false);
					localVars = new ScopeVars();
					break;
				}

				case (CLOSE): {
					localVars = theStack.pop();
					break;
				}

				case (METHOD_CALL): {
					verifyMethodCall(curLine);
					break;
				}
				throw Exception()
			}
		}
	}

	private void verifyValues(ArrayList<Variable> varArray) throws Exception{

		if (varArray == null)
			return;

		for (Variable var : varArray){

			String value = var.value();

			if (value == null)
				if (var.isFinal)
					throw Exception;
				continue;

			Matcher m = VarType.getMatcher(VarType.VAR).reset(value);
			if (m.matches()) {
				Variable refVar = new Variable(VarType.VAR, value, null, false);
				verifyUse(refVar, false);
				return;
			}

			m = VarType.getMatcher(var.type()).reset(value);
			if (!m.matches())
				throw Exception;
		}
	}

	private void verifyUse(Variable varToCheck, boolean assign) throws Exception {


		for (ScopeVars scope : theStack){
			int ans = scope.contains(varToCheck);

			if (ans == 0) {
				if (assign)
					throw Exception;
				else
					return;
			}

			else if (ans == 1)
				return;
		}
		throw Exception;
	}
}