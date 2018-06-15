package oop.ex6.main;

import java.util.ArrayList;

public class Method extends Scope {

	private String myName;
	private ArrayList<Variable> myParams;
	private MainScope main;

	Method(String myName, ArrayList<Variable> myParams, MainScope main) {
		super();
		this.myName = myName;
		this.myParams = myParams;
		this.main = main;
	}

	ArrayList<Variable> params() {return myParams;}

	String name() {return myName;}

	MainScope main() {return main;}

}
