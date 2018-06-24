package oop.ex6.main.codeElements.codeLines;

import oop.ex6.main.SjavacException;
import oop.ex6.main.codeElements.Variable;

import java.util.ArrayList;

public class LineMethodDef extends Line {

	public LineMethodDef(ArrayList<Variable> varArray, String methodName){
		super(varArray);
		this.methodName = methodName;
		myType = LineType.METHOD_DEF;
	}

	public void verifyLine() throws SjavacException {}
}
