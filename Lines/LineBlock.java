package oop.ex6.main.Lines;

import oop.ex6.main.SjavacException;
import oop.ex6.main.Variable;

import java.util.ArrayList;

public class LineBlock extends Line {

	public LineBlock(ArrayList<Variable> varArray){
		super(varArray);
	}

	public void verifyLine() throws SjavacException {
		for (Variable var : varArray) {
			myScope.variables().verifyValue(var); // one of them is unnecessary
			myScope.variables().verifyUse(var);
		}
		myScope.variables().openScope();
	}
}
