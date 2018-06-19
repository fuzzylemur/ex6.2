package oop.ex6.main.Lines;

import oop.ex6.main.VarType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LineType {

	COMMENT			(RegexConfig.COMMENT_LINE),
	RETURN			(RegexConfig.RETURN_LINE),
	VAR_INIT		(RegexConfig.VAR_INIT),
	VAR_ASSIGN		(RegexConfig.VAR_ASSIGN),
	METHOD_CALL		(RegexConfig.METHOD_CALL),
	METHOD_DEF		(RegexConfig.METHOD_DEF),
	CLOSE			(RegexConfig.CLOSE_LINE),
	BLOCK			(RegexConfig.BLOCK_LINE);

	final Matcher lineMatcher;

	LineType(String patternStr) {

		this.lineMatcher = Pattern.compile(patternStr).matcher("");
	}

	LineType(String[] array) {

		StringBuilder patternStr = new StringBuilder("\\s*");

		for (String element : array)
			patternStr.append(element).append("\\s*");

		this.lineMatcher = Pattern.compile(patternStr.toString()).matcher("");
	}

	public static Matcher getMatcher(LineType type) { return type.lineMatcher; }

	public static class RegexConfig {

		static final String TYPES = VarType.getAllTypesPattern();
		static final String VALUES = VarType.getAllValuesPattern();
		static final String VAR_NAME = VarType.VAR_NAME.valuePattern;
		static final String FINAL = "final";
		static final String VAR_DELIM = ",";

		static final String ASSIGN = "(?:\\s*"+VAR_NAME+"\\s*=\\s*"+VALUES+"\\s*)";
		static final String NAME_OR_ASSIGN = "(?:\\s*"+VAR_NAME+"\\s*|"+ASSIGN+")";
		static final String VARIABLES = oneOrMore(NAME_OR_ASSIGN, VAR_DELIM);

		static final String IF_WHILE = "(?:if|while)";
		public static final String COND_DELIM = "(?:\\s*&\\s*&\\s*|\\s*\\|\\s*\\|\\s*)";
		static final String CONDITIONS = oneOrMore(VALUES, COND_DELIM);

		static final String METHOD_NAME = "[A-Za-z]\\w*";
		static final String METHOD_WORD = "void";
		static final String PARAM = "(?:"+FINAL+" )?\\s*(?:"+TYPES+" )\\s*(?:"+VAR_NAME+")";
		static final String PARAMS = oneOrMore(PARAM, VAR_DELIM);
		static final String CALL_VALUES = oneOrMore(VALUES, VAR_DELIM);

		static final String COMMENT_LINE = "//.*|\\s*";
		static final String RETURN_LINE = "\\s*return\\s*;\\s*";
		static final String CLOSE_LINE = "\\s*\\}\\s*";

		static final String[] VAR_INIT = new String[]
				{"(?:("+FINAL+") )?","(?:("+TYPES+") )","("+VARIABLES+")",";"};

		static final String[] VAR_ASSIGN = new String[]
				{"("+ASSIGN+")",";"};

		static final String[] METHOD_DEF = new String[]
				{METHOD_WORD+" ","("+METHOD_NAME+")","\\(","("+PARAMS+")","\\)","\\{"};

		static final String[] METHOD_CALL = new String[]
				{"("+METHOD_NAME+")","\\(","("+CALL_VALUES+")","\\)",";"};

		static final String[] BLOCK_LINE = new String[]
				{IF_WHILE,"\\(","("+CONDITIONS+")","\\)","\\{"};


		static String oneOrMore(String element, String delimiter) {

			return "(?:"+element+"\\s*(?:"+delimiter+"\\s*"+element+")*)";
		}
	}
}
