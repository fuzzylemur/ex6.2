package oop.ex6.main;

import java.util.ArrayList;

abstract class Line {

	private int lineNum;
	private LineType myType;

	Line(LineType myType){
		this.myType = myType;
	}

	Line(LineType myType, ArrayList<Variable> varArray, String methodName){
		this.myType = myType;
	}

	abstract void verifyLine(Scope scope) throws SjavacException;

	void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}

	LineType type() {return myType;}

	int num() {return lineNum;}



}
