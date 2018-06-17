package oop.ex6.main.Lines;

import oop.ex6.main.SjavacException;
import oop.ex6.main.Variable;

import java.util.ArrayList;

public class LineMethodDef extends Line {

	public LineMethodDef(ArrayList<Variable> varArray, String methodName){
		super(varArray);
		this.methodName = methodName;
	}

	public void verifyLine() throws SjavacException {}
}
