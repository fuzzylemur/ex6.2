package oop.ex6.main;

public class LineClose extends Line {

	public LineClose(LineType myType){
		super(myType);
	}

	void verifyLine() throws SjavacException {
		myScope.variables().closeScope();
	}
}
