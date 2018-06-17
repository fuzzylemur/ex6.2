package oop.ex6.main.Lines;

import oop.ex6.main.SjavacException;

public class LineComment extends Line {

	public LineComment(){
		myType = LineType.COMMENT;
	}

	public void verifyLine() throws SjavacException {}
}
