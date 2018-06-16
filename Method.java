package oop.ex6.main;

import java.util.ArrayList;

public class Method extends Scope {

	private String myName;
	private ArrayList<Variable> myParams;
	private MainScope main;

	Method(String name, ArrayList<Variable> params, MainScope main) {
		super();
		this.myName = name;
		this.myParams = params;
		this.main = main;
	}

	ArrayList<Variable> params() {return myParams;}

	String name() {return myName;}

	MainScope main() {return main;}

}
