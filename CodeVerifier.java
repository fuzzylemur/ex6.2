package oop.ex6.main;

import com.sun.org.apache.xpath.internal.VariableStack;

import java.util.ArrayList;
import java.util.Stack;

import static oop.ex6.main.LineType.RETURN;
import static oop.ex6.main.LineType.VAR_INIT;

public class CodeVerifier {

	ArrayList<Method> methodArray;
	ScopeVars globalVars;

	CodeVerifier(){
		globalVars = new ScopeVars();
		methodArray = new ArrayList<>();
	}

	void validateScope(ArrayList<Line> lineArray, boolean isMethod) throws Exception{

		Stack<ScopeVars> variableStack = new Stack<>();
		ScopeVars localVars;

		if (isMethod) {

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

	private void assignHelper(Stack<ScopeVars> stack, Variable varToCheck) throws Exception {

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
























	void validateMainLines(){

		for (int i=0; i < mainLines.size(); i++) {

			Line curLine = mainLines.get(i);
			LineType lineType = myValidator.determineLineType(curLine);

			switch (lineType) {

				case(INIT): {
					globalVarArray.addAll(myValidator.createVariable(curLine);
					continue;
				}

				case(ASSIGN): {
					myValidator.validateAssign(curLine, globalVarArray);
					continue;
				}

				case(METHOD_CALL): {
					myValidator.validateMethodCall(curLine, globalVarArray, methodArray);
					continue;
				}
			}
			throw exception;

		}
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
