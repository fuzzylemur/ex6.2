package oop.ex6.main.codeElements.codeLines;

import oop.ex6.main.SjavacException;
import oop.ex6.main.codeElements.Scope;
import oop.ex6.main.codeElements.Variable;

import java.util.ArrayList;

public abstract class Line {

	private int lineNum;
	Scope myScope;
	String methodName;
	protected LineType myType;
	ArrayList<Variable> varArray;

	public Line(){
		varArray = new ArrayList<>();
	}

	public Line(ArrayList<Variable> varArray){
		this.varArray = varArray;
	}

	public abstract void verifyLine() throws SjavacException;

	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}

	public LineType type() {return myType;}

	public void setScope(Scope scope){myScope = scope;}

	public ArrayList<Variable> varArray(){return varArray;}

	public String methodName(){return methodName;}

	public void verifyLinePlus() throws SjavacException {

		try {
			verifyLine();

		} catch (SjavacException ex) {
			ex.setLineNum(lineNum);
			throw ex;
		}
	}



}