package oop.ex6.main.Lines;

import oop.ex6.main.Scope;
import oop.ex6.main.SjavacException;
import oop.ex6.main.Variable;

import java.util.ArrayList;

public abstract class Line {

	protected Scope myScope;
	private int lineNum;
	protected String methodName;
	protected LineType myType;
	protected ArrayList<Variable> varArray;

	public Line(){}

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

	public int num() {return lineNum;}



}