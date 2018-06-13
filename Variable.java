package oop.ex6.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	boolean validateVariable() {

		if (isFinal && myValue == null)
			return false;

		Matcher m = VarType.getMatcher(myType).reset(myValue);
		return m.matches();
	}

	String name(){return myName;}

	VarType type(){return myType;}

	String value(){return myValue;}

	Boolean isFinal(){return isFinal;}
}
