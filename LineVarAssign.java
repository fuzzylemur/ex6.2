package oop.ex6.main;

import java.util.ArrayList;

public class LineVarAssign extends Line {

	LineVarAssign(LineType myType, ArrayList<Variable> varArray){
		super(myType, varArray);
	}

	void verifyLine() throws SjavacException {
		myScope.variables().verifyUse(varArray.get(0), true);
	}
}
