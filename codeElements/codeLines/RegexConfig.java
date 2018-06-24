package oop.ex6.main.codeElements.codeLines;

import oop.ex6.main.codeElements.VarType;

public class RegexConfig {

	private static final String TYPES = getAllTypesPattern();
	private static final String VALUES = getAllValuesPattern();
	private static final String VAR_NAME = VarType.VAR_NAME.valuePattern;
	private static final String FINAL = "final";
	public static final String VAR_DELIM = ",";

	private static final String ASSIGN = "(?:\\s*"+VAR_NAME+"\\s*=\\s*"+VALUES+"\\s*)";
	private static final String NAME_OR_ASSIGN = "(?:\\s*"+VAR_NAME+"\\s*|"+ASSIGN+")";
	private static final String VARIABLES = oneOrMore(NAME_OR_ASSIGN, VAR_DELIM);

	private static final String IF_WHILE = "(?:if|while)";
	public static final String COND_DELIM = "(?:\\s*&\\s*&\\s*|\\s*\\|\\s*\\|\\s*)";
	private static final String CONDITIONS = oneOrMore(VALUES, COND_DELIM);

	private static final String METHOD_NAME = "[A-Za-z]\\w*";
	private static final String METHOD_WORD = "void";
	private static final String PARAM = "(?:"+FINAL+" )?\\s*(?:"+TYPES+" )\\s*(?:"+VAR_NAME+")";
	private static final String PARAMS = oneOrMore(PARAM, VAR_DELIM);
	private static final String CALL_VALUES = oneOrMore(VALUES, VAR_DELIM);

	static final String COMMENT_LINE = "//.*|\\s*";
	static final String RETURN_LINE = "\\s*return\\s*;\\s*";
	static final String CLOSE_LINE = "\\s*\\}\\s*";

	static final String[] VAR_INIT = new String[]
			{"(?:("+FINAL+")\\s)?","(?:("+TYPES+")\\s)","("+VARIABLES+")",";"};

	static final String[] VAR_ASSIGN = new String[]
			{"("+ASSIGN+")",";"};

	static final String[] METHOD_DEF = new String[]
			{METHOD_WORD+" ","("+METHOD_NAME+")","\\(","("+PARAMS+")?","\\)","\\{"};

	static final String[] METHOD_CALL = new String[]
			{"("+METHOD_NAME+")","\\(","("+CALL_VALUES+")?","\\)",";"};

	static final String[] BLOCK_LINE = new String[]
			{IF_WHILE,"\\(","("+CONDITIONS+")","\\)","\\{"};


	private static String oneOrMore(String element, String delimiter) {

		return "(?:"+element+"\\s*(?:"+delimiter+"\\s*"+element+")*)";
	}

	private static String getAllTypesPattern() {

		StringBuilder patternStr = new StringBuilder("(?:");
		for (VarType type : VarType.values()) {
			if (type == VarType.VAR_NAME)
				continue;
			patternStr.append(VarType.getStringRep(type)).append("|");
		}
		patternStr.deleteCharAt(patternStr.length()-1);
		patternStr.append(")");
		return patternStr.toString();
	}

	private static String getAllValuesPattern() {

		StringBuilder patternStr = new StringBuilder("(?:");
		for (VarType type : VarType.values()) {
			patternStr.append(type.valuePattern).append("|");
		}
		patternStr.deleteCharAt(patternStr.length()-1);
		patternStr.append(")");

		return patternStr.toString();
	}
}
