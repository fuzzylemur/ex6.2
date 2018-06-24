package oop.ex6.main.codeElements.codeLines;

import oop.ex6.main.SjavacException;

public class LineClose extends Line {

	public LineClose(){
		myType = LineType.CLOSE;
	}

	public void verifyLine() throws SjavacException {
		myScope.variables().closeScope();
	}
}