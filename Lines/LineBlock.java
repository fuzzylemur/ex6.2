package oop.ex6.main.Lines;

import oop.ex6.main.SjavacException;
import oop.ex6.main.Variable;

import java.util.ArrayList;

public class LineBlock extends Line {

	public LineBlock(ArrayList<Variable> varArray){
		super(varArray);
	}

	public void verifyLine() throws SjavacException {

		myScope.variables().verifyValues(varArray);
		for (Variable var : varArray) {
			myScope.variables().verifyUse(var, false);
		}
		myScope.variables().openScope();
	}
}
