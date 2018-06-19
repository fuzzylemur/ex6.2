package oop.ex6.main;

public class Variable {

	private VarType myType;
	private String myName;
	private String myValue;
	private boolean isFinal;
	public static String ASSIGNED = "assigned";

	Variable(VarType type, String name, String value, boolean isFinal){

		this.myType = type;
		this.myName = name;
		this.myValue = value;
		this.isFinal = isFinal;
	}

	String name() {return myName;}

	public VarType type() {return myType;}

	public void setType(VarType type) {myType = type;}

	String value() {return myValue;}

	boolean isFinal() {return isFinal;}

	boolean isAssigned(){return myValue != null;}

	void setAssigned(){myValue = ASSIGNED;}
}
