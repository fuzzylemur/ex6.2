package oop.ex6.main;

import oop.ex6.main.Lines.Line;

import java.util.ArrayList;

public class Scope {

	protected ScopeVariables myVariables;
	protected ArrayList<Line> myLines;
	protected MainScope main;

	Scope(MainScope main) {
		myLines = new ArrayList<>();
		myVariables = new ScopeVariables();
		this.main = main;
	}

	void addLine(Line lineToAdd){myLines.add(lineToAdd);}

	ArrayList<Line> lines() {return myLines;}

	public ScopeVariables variables() {return myVariables;}

	public MainScope main() {return main;}
}
