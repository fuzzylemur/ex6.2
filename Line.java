package oop.ex6.main;

import java.util.ArrayList;

abstract class Line {

	protected Scope myScope;
	private int lineNum;
	protected String myName;
	private LineType myType;
	protected ArrayList<Variable> varArray;


	Line(LineType myType){
		this.myType = myType;
	}

	Line(LineType myType, ArrayList<Variable> varArray){
		this.myType = myType;
		this.varArray = varArray;
	}

	abstract void verifyLine() throws SjavacException;

	void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}

	LineType type() {return myType;}

	void setScope(Scope scope){myScope = scope;}

	ArrayList<Variable> varArray(){return varArray;}
	String name(){return myName;}

	int num() {return lineNum;}



}
