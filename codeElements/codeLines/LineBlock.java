package oop.ex6.main.codeElements.codeLines;

import oop.ex6.main.SjavacException;
import oop.ex6.main.codeElements.Variable;

import java.util.ArrayList;

public class LineBlock extends Line {

	public LineBlock(ArrayList<Variable> varArray){
		super(varArray);
		myType = LineType.BLOCK;
	}

	public void verifyLine() throws SjavacException {
		for (Variable var : varArray) {
			myScope.variables().verifyValue(var); // one of them is unnecessary
			//myScope.variables().verifyUse(var);
		}
		myScope.variables().openScope();
	}
}