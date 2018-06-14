package oop.ex6.main;

import java.util.ArrayList;

class Line {

	private int lineNum;
	private LineType myType;
	private String methodName;
	private ArrayList<Variable> varArray;
	
	Line(LineType myType){
		this.myType = myType;
	}

	Line(LineType myType, ArrayList<Variable> varArray){
		this.myType = myType;
		this.varArray = varArray;
	}

	Line(LineType myType, ArrayList<Variable> varArray, String methodName){
		this.myType = myType;
		this.varArray = varArray;
		this.methodName = methodName;
	}

	void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}

	String methodName() {return methodName;}

	LineType type() {return myType;}

	int num() {return lineNum;}

	ArrayList<Variable> varArray() {return varArray;}


}
