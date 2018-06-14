package oop.ex6.main;

import java.util.ArrayList;

public class CodeSplitter {

	private ArrayList<Line> mainLines;
	private ArrayList<Method> methodArray;
	private LineFactory myFactory;

	void splitCode(ArrayList<String> allLines) throws SjavacException {

		for (int i = 0; i < allLines.size(); i++) {

			Line curLine = myFactory.createLine(allLines.get(i));
			LineType type = curLine.type();
			curLine.setLineNum(i);

			if (type == LineType.COMMENT)
				continue;

			if (type == LineType.METHOD_CALL || type == LineType.BLOCK)
				throw new SjavacException(Msg.INVALID_MAIN_LINE, i);			// TODO duplicate case with verifier

			else if (type == LineType.METHOD_DEF) {

				Method myMethod = new Method(curLine.methodName(), curLine.varArray());
				methodArray.add(myMethod);

				int counter = 0;
				while (!(type == LineType.CLOSE  && counter != 0)) {

					i++;
					if (i >= allLines.size())
						throw new SjavacException(Msg.SCOPE_OPEN);

					curLine = myFactory.createLine(allLines.get(i));
					type = curLine.type();
					curLine.setLineNum(i);

					if (type == LineType.COMMENT)
						continue;

					if (type == LineType.METHOD_DEF)
						throw new SjavacException(Msg.DEF_IN_METHOD, i);

					myMethod.addLine(curLine);

					if (type == LineType.BLOCK) {
						counter++;
					} else if (type == LineType.CLOSE) {
						counter--;
						if (counter < 0)
							throw new SjavacException(Msg.SCOPE_CLOSED);
					}
				}
			}

			mainLines.add(curLine);
		}
	}
}
