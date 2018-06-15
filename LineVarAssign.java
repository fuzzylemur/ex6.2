package oop.ex6.main;

import java.util.ArrayList;

public class LineVarAssign extends Line{

	private ArrayList<Variable> varArray;

	LineVarAssign(LineType myType, ArrayList<Variable> varArray){
		super(myType);
		this.varArray = varArray;
	}

	void verifyLine(Scope scope) throws SjavacException{
		scope.myVariables.verifyUse(varArray);
	}
}
