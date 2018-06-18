package oop.ex6.main.Lines;

import oop.ex6.main.SjavacException;
import oop.ex6.main.Variable;

import java.util.ArrayList;

public class LineVarInit extends Line {

	public LineVarInit(ArrayList<Variable> varArray){

		super(varArray);
		myType = LineType.VAR_INIT;
	}

	public void verifyLine() throws SjavacException {
		myScope.variables().addVars(varArray);
	}
}
