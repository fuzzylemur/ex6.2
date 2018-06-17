package oop.ex6.main;

import java.util.ArrayList;

public class Method extends Scope {

	private String myName;
	private ArrayList<Variable> myParams;

	Method(String name, ArrayList<Variable> params, MainScope main) {
		super(main);
		this.myName = name;
		this.myParams = params;
	}

	public ArrayList<Variable> params() {return myParams;}

	String name() {return myName;}

}
