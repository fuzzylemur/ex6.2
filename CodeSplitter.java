package oop.ex6.main;

import java.util.ArrayList;

public class CodeSplitter {

	ArrayList<Line> mainLines;
	ArrayList<Method> methodArray;
	LineValidator myValidator;

	void splitCode(ArrayList<Line> allLines) {

		for (int i = 0; i < allLines.size(); i++) {

			Line curLine = allLines.get(i);

			if (!myValidator.isBlank(curLine))
				continue;

			if (myValidator.isMethod(curLine)) {
				Method method = myValidator.createMethod(curLine);
				methodArray.add(method);									// check return and shit

				int counter = 0;
				while (!(myValidator.isClose(curLine) && counter != 0)) {
					if (myValidator.isBlock(curLine)) {
						counter++;
					} else if (myValidator.isClose(curLine)) {
						counter--;										// negative counter !!!!
					}
					curLine = allLines.get(i++);						// Array out of bounds!!!
				}
				method.setEndLine(i);
			}

			mainLines.add(curLine);
		}
	}


}
