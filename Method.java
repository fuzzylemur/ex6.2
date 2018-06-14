package oop.ex6.main;

import java.util.ArrayList;

public class Method {

	private String myName;
	private ArrayList<Variable> myParams;
	private ArrayList<Line> myLines;

	Method(String myName, ArrayList<Variable> myParams) {

		this.myName = myName;
		this.myParams = myParams;
		myLines = new ArrayList<>();

	}

	void addLine(Line lineToAdd){
		myLines.add(lineToAdd);
	}

	ArrayList<Line> lines() {return myLines;}

	ArrayList<Variable> params() {return myParams;}

	String name() {return myName;}

}
