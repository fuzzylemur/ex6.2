package oop.ex6.main;

import java.util.ArrayList;

public class Scope {

	protected ScopeVariables myVariables;
	protected ArrayList<Line> myLines;

	Scope() {
		myLines = new ArrayList<>();
	}

	void addLine(Line lineToAdd){
		myLines.add(lineToAdd);
	}

	ArrayList<Line> lines() {return myLines;}
}
