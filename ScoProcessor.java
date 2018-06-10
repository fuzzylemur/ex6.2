package oop.ex6.main;

import java.util.ArrayList;

public class ScoProcessor {

	LineValidator myValidator;
	ArrayList<Line> allLines;


	ScoProcessor(Scope mainScope) {

		allLines = mainScope.myLines;
		myValidator = new LineValidator();
	}


	void processHelper(Scope scope, int curLine) throws {

		for (int i = curLine; i < allLines.size(); i++) {

			String lineString = allLines.get(i).lineString;						// check if last line and main scope

			LineType lineType = myValidator.determineLineType(lineString);

			switch (lineType) {

				case(CLOSE_SCOPE) :{

					if (scope.isMethod)
						if (myValidator.determineLineType(scope.myLines.get(i-1).lineString) != RETURN))
							throw ....
					return;
				}

				case(COMMENT || EMPTY): {
					//
				}

				case(INIT): {
					scope.myVariables.addAll(myValidator.createVariable(lineString);
				}

				case(ASSIGN): {

					myValidator.validateAssign(lineString, scope);
				}

				case(METHOD_CALL || RETURN): {
					scope.myLines.add(allLines.get(i));
				}

				case(METHOD): {
					Scope newScope = myValidator.createMethod(lineString);
					scope.myScopes.add(newScope);
					processHelper(newScope, i+1);
				}

				case(SCOPE): {
					Scope newScope = myValidator.createScope(lineString);
					scope.myScopes.add(newScope);
					processHelper(newScope, i+1);
				}


			}

		}

	}
}
