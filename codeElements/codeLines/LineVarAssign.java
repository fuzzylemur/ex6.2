package oop.ex6.main.codeElements.codeLines;

import oop.ex6.main.SjavacException;
import oop.ex6.main.codeElements.Variable;

public class LineVarAssign extends Line {

	public LineVarAssign(Variable var){
		super();
		varArray.add(var);
		myType = LineType.VAR_ASSIGN;
	}

	public void verifyLine() throws SjavacException {
		myScope.variables().verifyAssign(varArray.get(0));
	}
}
