package oop.ex6.main;

import java.util.ArrayList;


public class Scope {

	boolean mainScope;
	boolean isMethod;
	private Scope myParent;

	ArrayList<Line> myLines;
	ArrayList<Variable> myVariables;
	ArrayList<Scope> myScopes;

	boolean validateVariable(VarType varType, String varName) {

		for (Variable var : myVariables) {
			if (varName.equals(var.myName) && varType.equals(var.myType)){
				return true;
			}
		}
		if (myParent == null) {
			return false;
		}
		return myParent.validateVariable(varType, varName);
	}
}
