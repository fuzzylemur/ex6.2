package oop.ex6.main;

import oop.ex6.main.Lines.Line;
import oop.ex6.main.Lines.LineType;

import java.util.ArrayList;

public class CodeSplitter {

	private static CodeSplitter myInstance = new CodeSplitter();
	private LineFactory myFactory;

	private CodeSplitter(){
		myFactory = LineFactory.instance();
	}

	/* @return the class one and only instance */
	static CodeSplitter instance() {return myInstance;}

	MainScope splitCode(ArrayList<String> allLines) throws SjavacException {

		MainScope main = new MainScope();

		for (int i = 0; i < allLines.size(); i++) {

			if (allLines.get(i).matches("\\s*")) continue;

			Line curLine;
			try {
				curLine = myFactory.createLine(allLines.get(i));
			} catch (SjavacException ex){
				ex.setLineNum(i+1);
				throw ex;
			}
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

				int counter = 1;

				while (type != LineType.CLOSE || counter != 0) {

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
						throw new SjavacException(Msg.DEF_IN_METHOD, i+1);

					if (type == LineType.BLOCK) {
						counter++;
					} else if (type == LineType.CLOSE) {
						counter--;
						if (counter < 0)
							throw new SjavacException(Msg.SCOPE_CLOSED);
					}
					myMethod.addLine(curLine);

				}
				// look for return line  before the method ends.
				if (myMethod.lines().size() < 2)
					throw new SjavacException(Msg.MISSING_RETURN);
				if (myMethod.lines().get(myMethod.lines().size()-2).type() != LineType.RETURN)
					throw new SjavacException(Msg.MISSING_RETURN);

				main.addMethod(myMethod);
				continue;
			}
			throw new SjavacException(Msg.INVALID_MAIN_LINE, i+1);
		}
		return main;
	}
}
