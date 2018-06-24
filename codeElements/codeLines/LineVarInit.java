package oop.ex6.main.codeElements.codeLines;

import oop.ex6.main.SjavacException;
import oop.ex6.main.codeElements.Variable;

import java.util.ArrayList;

public class LineVarInit extends Line {

	public LineVarInit(ArrayList<Variable> varArray){

		super(varArray);
		myType = LineType.VAR_INIT;
	}

	public void verifyLine() throws SjavacException {
		for (Variable var : varArray) {
			myScope.variables().verifyValue(var);
		}
		myScope.variables().addVars(varArray);
	}
}
