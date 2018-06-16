package oop.ex6.main;

import java.util.ArrayList;

public class LineBlock extends Line {

	LineBlock(LineType myType, ArrayList<Variable> varArray){
		super(myType, varArray);
	}

	void verifyLine() throws SjavacException {
		for (Variable var : varArray) {
			myScope.variables().verifyUse(var, false);
		}
		myScope.variables().openScope();
	}
}
