package oop.ex6.main;

import oop.ex6.codeElements.codeLines.Line;
import oop.ex6.codeElements.codeLines.LineType;
import oop.ex6.codeElements.MainScope;
import oop.ex6.codeElements.Method;

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

		Line curLine;
		int lineNum;

		for (int i = 0; i < allLines.size(); i++) {

			lineNum = i+1;
			curLine = createLine(allLines.get(i), lineNum);
			LineType type = curLine.type();

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

						curLine = createLine(allLines.get(i), lineNum);
						type = curLine.type();
						curLine.setScope(myMethod);

						if (type == LineType.COMMENT)
							continue;

						if (type == LineType.METHOD_DEF)
							throw new SjavacException(Msg.DEF_IN_METHOD, lineNum);

						if (type == LineType.BLOCK)
							count ++;

						if (type == LineType.CLOSE)
								count --;
								if (count < 0)
									throw new SjavacException(Msg.SCOPE_CLOSED);

						myMethod.addLine(curLine);
				}
				// look for return line  before the method ends.
				if (myMethod.lines().size() < 2 ||
						myMethod.lines().get(myMethod.lines().size()-2).type() != LineType.RETURN)
					throw new SjavacException(Msg.MISSING_RETURN, lineNum);

				main.addMethod(myMethod);
				continue;
			}
			throw new SjavacException(Msg.INVALID_MAIN_LINE, lineNum);
		}
		return main;
	}


	private Line createLine(String lineString, int lineNum) throws SjavacException{

		try {
			Line curLine = myFactory.createLine(lineString);
			curLine.setLineNum(lineNum);
			return curLine;
		}
		catch (SjavacException ex) {
			ex.setLineNum(lineNum);
			throw ex;
		}
	}
}
