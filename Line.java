package oop.ex6.main;

import java.util.ArrayList;

class Line {

	int lineNum;
	LineType myType;
	String methodName;
	ArrayList<Variable> varArray;


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

	String methodName(){return methodName;}

	LineType type(){return myType;}

	ArrayList<Variable> varArray(){return varArray;}


}
