package oop.ex6.main;

import java.util.ArrayList;

public class Method extends Scope {

	private String myName;
	private ArrayList<Variable> myParams;

	Method(String myName, ArrayList<Variable> myParams) {

		this.myName = myName;
		this.myParams = myParams;
		this.myVariables.addAll(myParams);
	}
}
