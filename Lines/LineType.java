package oop.ex6.main.Lines;

import oop.ex6.main.VarType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LineType {

	COMMENT			(Config.COMMENT_LINE),
	RETURN			(Config.RETURN_LINE),
	VAR_INIT		(Config.VAR_INIT),
	VAR_ASSIGN		(Config.VAR_ASSIGN),
	METHOD_CALL		(Config.METHOD_CALL),
	METHOD_DEF		(Config.METHOD_DEF),
	CLOSE			(Config.CLOSE_LINE),
	BLOCK			(Config.BLOCK_LINE);

	final Matcher lineMatcher;

	LineType(String patternStr) {

		this.lineMatcher = Pattern.compile(patternStr).matcher("");
	}

	public static Matcher getMatcher(LineType type) { return type.lineMatcher; }

	public static String getReservedWords() {return Config.RESERVED_WORDS;}

	private static class Config {

		static final String TYPES = VarType.getAllTypesPattern();
		static final String VALUES = VarType.getAllValuesPattern();
		static final String VAR_NAME = VarType.VAR_NAME.valuePattern;
		static final String FINAL = "final";
		static final String CONDITIONS = "if|while";

		static final String ASSIGN = "(?:"+VAR_NAME+"="+VALUES+")";
		static final String NAME_OR_ASSIGN = "(?:"+VAR_NAME+"|"+ASSIGN+")";

		static final String VAR_ASSIGN = "(("+ASSIGN+"));";
		static final String VAR_INIT = "(?:("+FINAL+") )?("+TYPES+") (((?:"+NAME_OR_ASSIGN+",)*"+NAME_OR_ASSIGN+"));";
		static final String BLOCK_LINE = "(?:"+CONDITIONS+")\\(("+VALUES+"((?:(?:&&|\\|\\|)"+VALUES+")*))\\)\\{";

		static final String METHOD_NAME = "[A-Za-z]\\w*";
		static final String METHOD_WORD = "void";

		static final String PARAM = "(?:"+FINAL+" )?(?:"+TYPES+" )(?:"+VAR_NAME+")";
		static final String ONE_OR_MORE_PARAMS = "("+PARAM+"(?:,"+PARAM+")*)";
		static final String METHOD_DEF = METHOD_WORD+" ("+METHOD_NAME+")\\(("+ONE_OR_MORE_PARAMS+")?\\)\\{";

		static final String ONE_OR_MORE_VALUES = "("+VALUES+"(?:,"+VALUES+")*)";
		static final String METHOD_CALL = "("+METHOD_NAME+")\\(("+ONE_OR_MORE_VALUES+")?\\)\\;";

		static final String COMMENT_LINE = "//BLA";
		static final String RETURN_LINE = "return;";
		static final String CLOSE_LINE = "\\}";

		static final String RESERVED_WORDS = FINAL+"|"+TYPES+"|"+METHOD_WORD+"|"+VAR_NAME;
	}
}
