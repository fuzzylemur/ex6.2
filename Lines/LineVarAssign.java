package oop.ex6.main.Lines;

import oop.ex6.main.SjavacException;
import oop.ex6.main.Variable;

import java.util.ArrayList;

public class LineVarAssign extends Line {

	public LineVarAssign(Variable var){
		super();
		varArray.add(var);
		myType = LineType.VAR_ASSIGN;
	}

	public void verifyLine() throws SjavacException {
		myScope.variables().verifyValues(varArray);
		myScope.variables().verifyUse(varArray.get(0), true);
	}
}
