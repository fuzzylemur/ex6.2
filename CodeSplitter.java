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

			Line curLine;
			curLine = createLine(allLines.get(i), i+1);
			LineType type = curLine.type();
			curLine.setLineNum(i+1);

			switch (type) {

				case COMMENT:
					continue;

				case VAR_INIT:
				case VAR_ASSIGN:
					main.addLine(curLine);
					curLine.setScope(main);
					continue;

				case METHOD_DEF:

					Method myMethod = new Method(curLine.methodName(), curLine.varArray(), main);
					int count = 1;

					while (type != LineType.CLOSE || count != 0) {

						i++;
						if (i >= allLines.size())
							throw new SjavacException(Msg.SCOPE_OPEN);
						
						curLine = createLine(allLines.get(i), i+1);
						type = curLine.type();
						curLine.setLineNum(i+1);
						curLine.setScope(myMethod);

						switch (type) {

							case COMMENT:
								continue;

							case METHOD_DEF:
								throw new SjavacException(Msg.DEF_IN_METHOD, i+1);

							case BLOCK:						// TODO why the fuck won't this work ?!
								count ++;

							case CLOSE:
								count --;
								if (count < 0)
									throw new SjavacException(Msg.SCOPE_CLOSED);
						}
						if (type == LineType.BLOCK)
							count ++;
						myMethod.addLine(curLine);

				}
				// look for return line  before the method ends.
				if (myMethod.lines().size() < 2 ||
						myMethod.lines().get(myMethod.lines().size()-2).type() != LineType.RETURN)
					throw new SjavacException(Msg.MISSING_RETURN, i+1);

				main.addMethod(myMethod);
				continue;
			}
			throw new SjavacException(Msg.INVALID_MAIN_LINE, i+1);
		}
		return main;
	}


	private Line createLine(String lineString, int lineNum) throws SjavacException{

		try {
			return myFactory.createLine(lineString);

		} catch (SjavacException ex) {

			ex.setLineNum(lineNum);
			throw ex;
		}
	}
}
