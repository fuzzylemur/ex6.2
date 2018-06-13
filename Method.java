package oop.ex6.main;

import java.util.ArrayList;

public class Method extends Scope {

	private String myName;
	private ArrayList<Variable> myParams;
	private ArrayList<Line> myLines;

	Method(String myName, ArrayList<Variable> myParams) {

		this.myName = myName;
		this.myParams = myParams;
	}

	void addLine(Line lineToAdd){
		myLines.add(lineToAdd);
	}

	ArrayList<Line> myLines(){return myLines;}
	ArrayList<Variable> myParams(){return myParams;}


}
