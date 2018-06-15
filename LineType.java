package oop.ex6.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum LineType {

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

	static Matcher getMatcher(LineType type) { return type.lineMatcher; }

	static String getReservedWords() {return Config.RESERVED_WORDS;}

	private static class Config {

		static final String TYPES = VarType.getAllTypesPattern();
		static final String VALUES = VarType.getAllValuesPattern();
		static final String VAR_NAME = "("+VarType.VAR_NAME.stringRep+")";
		static final String FINAL = "(final )";
		static final String CALL_VALUES = VALUES+"|"+VAR_NAME;


		static final String VAR_ASSIGN = VAR_NAME+"="+CALL_VALUES+";";
		static final String NAME_OR_ASSIGN = VAR_NAME+"|"+VAR_ASSIGN;
		static final String VAR_INIT = FINAL+"?"+TYPES+"(?:"+NAME_OR_ASSIGN+",)*"+NAME_OR_ASSIGN+";";

		static final String METHOD_NAME = "([A-Za-z]\\w*)";
		static final String METHOD_WORD = "void ";

		static final String PARAM = FINAL+"?"+TYPES+VAR_NAME;
		static final String METHOD_DEF = METHOD_WORD+METHOD_NAME+"\\("+PARAM+"(?:,"+PARAM+")*\\)\\{";

		static final String METHOD_CALL = METHOD_NAME+"\\("+CALL_VALUES+"(?:,"+CALL_VALUES+")*\\)\\{";

		static final String BLOCK_LINE = "(?:if|while)\\("+CALL_VALUES+"(?:(?:&&|\\|\\|)"+CALL_VALUES+")*\\{";

		static final String COMMENT_LINE = "//.*";
		static final String RETURN_LINE = "return;";
		static final String CLOSE_LINE = "\\}";

		static final String RESERVED_WORDS = FINAL+"|"+TYPES+"|"+METHOD_WORD;
	}
}

