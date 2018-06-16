package oop.ex6.main;

import java.util.ArrayList;

public class LineVarInit extends Line {

	LineVarInit(LineType myType, ArrayList<Variable> varArray){
		super(myType, varArray);
	}

	void verifyLine() throws SjavacException {
		myScope.variables().addVars(varArray);
	}
}
