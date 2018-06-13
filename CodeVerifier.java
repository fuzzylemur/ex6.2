package oop.ex6.main;

import com.sun.org.apache.xpath.internal.VariableStack;

import java.util.ArrayList;
import java.util.Stack;

import static oop.ex6.main.LineType.*

public class CodeVerifier {

	ArrayList<Method> methodArray;
	ArrayList<Line> mainLines;
	ScopeVars globalVars;

	CodeVerifier(ArrayList<Method> methodArray ){
		globalVars = new ScopeVars();
		this.methodArray = methodArray;
	}

	void validateMainLines() throws Exception{

		for (Line curLine : mainLines) {

			LineType lineType = curLine.type();
			switch (lineType) {

				case(INIT): {
					verifyValues(curLine.varArray());
					globalVarArray.add(curLine.varArray());
					break;
				}

				case(ASSIGN): {
					verifyValues(curLine.varArray());
					globalVarArray.contains(curLine.varArray().get(0));
					break;
				}
			}
			throw exception;
		}
	}

	void validateMethod(Method method) throws Exception{
		//
		Stack<ScopeVars> variableStack = new Stack<>();
		variableStack.push(globalVars);
		ScopeVars localVars = new ScopeVars();
		// No return before method ends.
		if (lineArray.get(lineArray.size()-2).type() != RETURN) throw Exception();

		for (Line curLine : method.lineArray()) {

			verifyValues(curLine.varArray());
			LineType curLineType = curLine.type();

			switch (curLineType) {

				case (VAR_INIT): {
					localVars.add(curLine.varArray());
					break;
				}

				case (VAR_ASSIGN): {
					variableStack.push(localVars);
					verifyAssign(variableStack, curLine.varArray().get(0));
					localVars = variableStack.pop();
					break;
				}

				case (BLOCK): {
					variableStack.push(localVars);
					if (curLine.varArray().get(0) != null) /// NOT ASSIGN HELPER
						verifyUse(variableStack, curLine.varArray().get(0));
					localVars = new ScopeVars();
					break;
				}

				case (CLOSE): {
					localVars = variableStack.pop();
					break;
					}

				case (METHOD_CALL): {
					verifyMethodCall(curLine);
					break;
				}
				throw Exeption()
			}
		}
	}





































	void validateScope(ArrayList<Line> lineArray, boolean isMethod) throws Exception{

		Stack<ScopeVars> variableStack = new Stack<>();
		ScopeVars localVars;

		if (isMethod) { // add method def vars
			if (lineArray.get(lineArray.size()-2).type() != RETURN)
				throw Exception;

			variableStack.push(globalVars);
			localVars = new ScopeVars();

		} else
			localVars = globalVars;

		for (Line curLine : lineArray) {

			LineType curLineType = curLine.type();

			switch (curLineType) {

				case (VAR_INIT): {

					verifyValues(curLine.varArray());
					localVars.add(curLine.varArray());
				}

				case (VAR_ASSIGN): {

					verifyValues(curLine.varArray());
					variableStack.push(localVars);
					assignHelper(variableStack, curLine.varArray().get(0));
					localVars = variableStack.pop();
				}

				case (BLOCK): {

					if (!isMethod)
						throw Exception;

					else {
						variableStack.push(localVars);
						if (curLine.varArray().get(0) != null)
							assignHelper(variableStack, curLine.varArray().get(0));
						localVars = new ScopeVars();
					}
				}

				case (CLOSE): {

					if (!isMethod)
						throw Exception;

					else {
						localVars = variableStack.pop();
					}
				}


				case (METHOD_CALL): {

					verifyMethodCall(curLine);
				}

			}
		}
	}

	private void verifyValues(ArrayList<Variable> varArray) {

		for (Variable var : varArray){

			if (var.getValue() == null)
				continue;

			// run regex again

			if (VarType is variable)
				//new variable
				// check contains
		}
	}

	private void verifyAssign(Stack<ScopeVars> stack, Variable varToCheck) throws Exception {

		int ans = 0;

		for (ScopeVars scope : stack){
			ans = scope.contains(varToCheck);

			if (ans == 0)
				throw Exception;

			else if (ans == 1)
				break;
		}
		if (ans != 1)
			throw Exception;
	}


























	void validateMethod(Method method, ArrayList<Line> allLines) {

		Stack<ArrayList<Variable>> variableStack = new Stack<>();
		variableStack.push(globalVarArray);

		for (int i = method.getStart(); i <= method.getEnd(); i++) {

			Line curLine = mainLines.get(i);
			LineType lineType = myValidator.determineLineType(curLine);
			ArrayList<Variable> localVariables = new ArrayList<>();

			switch (lineType) {

				case(INIT): {
					localVariables.addAll(myValidator.createVariable(curLine);
					continue;
				}

				case(ASSIGN): {
					variableStack.push(localVariables);
					myValidator.validateAssign(curLine, variableStack);
					variableStack.pop();
					continue;
				}

				case(BLOCK): {
					myValidator.validateBlock(curLine);
					variableStack.push(localVariables);
					localVariables = new ArrayList<>();
					continue;
				}

				case(CLOSE): {
					myValidator.validateBlock(curLine);
					localVariables = variableStack.pop();
					continue;
				}
				case(METHOD_CALL): {
					variableStack.push(localVariables);
					myValidator.validateMethodCall(curLine, variableStack, methodArray);
					variableStack.pop();
					continue;
				}
			}
			throw exception;

		}
	}
}
