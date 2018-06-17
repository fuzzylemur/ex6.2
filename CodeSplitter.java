package oop.ex6.main;

import oop.ex6.main.Lines.Line;
import oop.ex6.main.Lines.LineType;

import java.util.ArrayList;

public class CodeSplitter {

	private LineFactory myFactory;

	CodeSplitter(){
		myFactory = new LineFactory();
	}

	MainScope splitCode(ArrayList<String> allLines) throws SjavacException {

		MainScope main = new MainScope();

		for (int i = 0; i < allLines.size(); i++) {

			if (allLines.get(i).matches("\\s*")) continue;

			Line curLine = myFactory.createLine(allLines.get(i));
			LineType type = curLine.type();
			curLine.setLineNum(i+1);

			if (type == LineType.COMMENT)
				continue;

			if (type == LineType.VAR_INIT || type == LineType.VAR_ASSIGN) {
				main.addLine(curLine);
				curLine.setScope(main);
				continue;
			}

			else if (type == LineType.METHOD_DEF) {

				Method myMethod = new Method(curLine.methodName(), curLine.varArray(), main);

				int counter = 0;
				while (!(type == LineType.CLOSE  && counter != 0)) {

					i++;
					if (i >= allLines.size())
						throw new SjavacException(Msg.SCOPE_OPEN);

					if (allLines.get(i).matches("\\s*")) continue;
					curLine = myFactory.createLine(allLines.get(i));
					type = curLine.type();
					curLine.setLineNum(i+1);
					curLine.setScope(myMethod);

					if (type == LineType.COMMENT)
						continue;

					if (type == LineType.METHOD_DEF)
						throw new SjavacException(Msg.DEF_IN_METHOD, i);

					if (type == LineType.BLOCK) {
						counter++;
					} else if (type == LineType.CLOSE) {
						counter--;
						if (counter < 0)
							throw new SjavacException(Msg.SCOPE_CLOSED);
					}
					myMethod.addLine(curLine);
				}
				main.addMethod(myMethod);
				continue;
			}
			throw new SjavacException(Msg.INVALID_MAIN_LINE, i);			// TODO duplicate case with verifier
		}
		return main;
	}
}
