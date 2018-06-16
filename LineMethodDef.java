package oop.ex6.main;

import java.util.ArrayList;

public class LineMethodDef extends Line {

	LineMethodDef(LineType myType, ArrayList<Variable> varArray, String myName){
		super(myType, varArray);
		this.myName = myName;
	}

	void verifyLine() throws SjavacException {}
}
