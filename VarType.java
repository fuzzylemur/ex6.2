package oop.ex6.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum VarType {

	INT 		(Config.INT_WORD, Config.INT_VALID_VALS),
	STRING 		(Config.STRING_WORD, Config.STRING_VALID_VALS),
	DOUBLE 		(Config.DOUBLE_WORD, Config.DOUBLE_VALID_VALS),
	CHAR 		(Config.CHAR_WORD, Config.CHAR_VALID_VALS),
	BOOLEAN 	(Config.BOOLEAN_WORD, Config.BOOLEAN_VALID_VALS),
	VAR			("", Config.VAR_NAME);

	final String stringRep;
	final Matcher valueMatcher;

	VarType(String stringRep, String patternStr) {

		this.stringRep = stringRep;
		this.valueMatcher = Pattern.compile(patternStr).matcher("");
	}

	static VarType getType(String str) {

		for (VarType type : VarType.values()){
			if (str.equals(type.stringRep))
				return type;
		}
		return null;
	}

	static Matcher getMatcher(VarType type) {
		return type.valueMatcher;
	}

	static String stringRep(VarType type) {return type.stringRep;}
}