package oop.ex6.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public enum VarType {

	INT 		("int", 		"-?[0-9]+", 				null),
	STRING 		("String", 		"\".*\"", 					null),
	CHAR 		("char", 		"\'.\'", 					null),
	DOUBLE 		("double",		"-?[0-9]+\\.[0-9]+", 		new VarType[] {INT}),
	BOOLEAN 	("boolean", 	"true|false", 				new VarType[] {INT, DOUBLE}),

	VAR_NAME	("name", 		"_[\\w]+|[A-Za-z]\\w*",		null);

	final String stringRep;
	public final String valuePattern;
	final VarType[] validCasts;
	Matcher valueMatcher;

	VarType(String stringRep, String valuePattern, VarType[] validCasts) {

		this.stringRep = stringRep;
		this.valuePattern = valuePattern;
		this.validCasts = validCasts;
	}

	static VarType getType(String str) {

		for (VarType type : VarType.values()){
			if (str.equals(type.stringRep))
				return type;
		}
		return null;
	}

	static Matcher getMatcher(VarType type) {

		if (type.valueMatcher != null)
			return type.valueMatcher;

		StringBuilder patternStr = new StringBuilder("(?:");
		patternStr.append(type.valuePattern);

		if (type.validCasts != null) {
			for (VarType cast : type.validCasts)
				patternStr.append("|").append(cast.valuePattern);
		}
		patternStr.append(")");

		type.valueMatcher = Pattern.compile(patternStr.toString()).matcher("");
		return type.valueMatcher;
	}

	public static String getStringRep(VarType type) {return type.stringRep;}

	public boolean equals(VarType toCompare){

		if (this == toCompare) {
			return true;

		} else if (this.validCasts == null) {
			return false;

		} else {
			for (VarType type : this.validCasts)
				if (toCompare == type) return true;
		}
		return false;
	}
}