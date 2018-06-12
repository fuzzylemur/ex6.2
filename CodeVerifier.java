package oop.ex6.main;

import java.util.ArrayList;
import java.util.Stack;

public class CodeVerifier {

	ArrayList<Line> mainLines;
	ArrayList<Line> allLines;

	ArrayList<Method> methodArray;
	ArrayList<Variable> globalVarArray;
	LineFactory myValidator;


	void validateScope(ScopeType scope, ArrayList<Line> lineArray) {

		for (int i = 0; i < lineArray.size(); i++) {

			Line curLine = mainLines.get(i);
			LineType lineType = curLine.type();

			switch (lineType) {

				case (INIT): {
					if (scope == MAIN)
						// add to global variables

					else if (scope == METHOD)
						// add to local variables
				}

				case (ASSIGN): {
					if (scope == MAIN)
					// check if already initialized in global

					else if (scope == METHOD)
					// check if initialized in global and local
				}

				case (METHOD_CALL): {
					// validate method call (name and params)

					if (scope == MAIN)
					// check if params are initialized in global variables

					else if (scope == METHOD)
					// add to cue with current global variables
				}

			}
		}
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
