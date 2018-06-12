package oop.ex6.main;

public class Variable {

	VarType myType;
	String myName;
	String myValue;
	boolean isFinal;

	Variable(VarType type, String name, String value, boolean isFinal){
		myType = type;
		myName = name;
		myValue = value;
		this.isFinal = isFinal;
	}
}
