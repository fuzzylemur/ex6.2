package oop.ex6.main;

import java.util.ArrayList;

public class CodeSplitter {

	ArrayList<Line> mainLines;
	ArrayList<Method> methodArray;
	LineFactory myFactory;

	void splitCode(ArrayList<String> allLines) {

		for (int i = 0; i < allLines.size(); i++) {

			Line curLine = myFactory.createLine(allLines.get(i));
			curLine.setLineNum(i);

			if (curLine.type() == LineType.BLANK)
				continue;

			else if (curLine.type() == LineType.METHOD_DEF) {

				Method myMethod = new Method(curLine.methodName(), curLine.varArray);
				methodArray.add(myMethod);

				int counter = 0;
				while (!(curLine.type() == LineType.CLOSE  && counter != 0)) {

					i++;
					if (i >= allLines.size())
						throw exception;

					curLine = myFactory.createLine(allLines.get(i));
					curLine.setLineNum(i);

					if (curLine.type() == LineType.BLANK)
						continue;

					myMethod.addLine(curLine);

					if (curLine.type() == LineType.BLOCK) {
						counter++;
					} else if (curLine.type() == LineType.CLOSE) {
						counter--;
						if (counter < 0)
							throw exception;
					}
				}
			}

			mainLines.add(curLine);
		}
	}
}
