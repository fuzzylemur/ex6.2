package oop.ex6.main;

import java.util.ArrayList;

public class LineVarInit extends Line{

	private ArrayList<Variable> varArray;

	LineVarInit(LineType myType, ArrayList<Variable> varArray){
		super(myType);
		this.varArray = varArray;
	}

	void verifyLine(Scope scope) throws SjavacException{
		scope.myVariables.add(varArray);
	}
}
