package oop.ex6.main;

public class Variable {

	private VarType myType;
	private String myName;
	private String myValue;
	private String key;
	private boolean isFinal;

	Variable(VarType type, String name, String value, boolean isFinal){

		this.myType = type;
		this.myName = name;
		this.myValue = value;
		this.isFinal = isFinal;
	}

	String name() {return myName;}

	VarType type() {return myType;}

	void setType(VarType type) {myType = type;}

	String value() {return myValue;}

	Boolean isFinal() {return isFinal;}

	String key() {return VarType.stringRep(myType)+myName;}
}
