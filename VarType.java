package oop.ex6.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public enum VarType {

	INT 		("int", 		"-?[0-9]+", 				null),
	STRING 		("string", 		"\".*\"", 					null),
	DOUBLE 		("double",		"-?[0-9]+\\.[0-9]+", 		new VarType[] {INT}),
	CHAR 		("char", 		"\'.\'", 					null),
	BOOLEAN 	("boolean", 	"[true|false]", 			new VarType[] {INT, DOUBLE}),

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

	public static String getAllTypesPattern() {

		StringBuilder patternStr = new StringBuilder("(?:");
		for (VarType type : VarType.values()) {
			if (type == VarType.VAR_NAME)
				continue;
			patternStr.append(type.stringRep).append("|");
		}
		patternStr.deleteCharAt(patternStr.length()-1);
		patternStr.append(")");
		return patternStr.toString();
	}

	public static String getAllValuesPattern() {

		StringBuilder patternStr = new StringBuilder("(?:");
		for (VarType type : VarType.values()) {
			patternStr.append(type.valuePattern).append("|");
		}
		patternStr.deleteCharAt(patternStr.length()-1);
		patternStr.append(")");

		return patternStr.toString();
	}

	static String stringRep(VarType type) {return type.stringRep;}

	// TODO EQUALS
}