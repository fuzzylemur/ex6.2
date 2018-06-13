package oop.ex6.main;

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

	static Matcher getMatcher(LineType type) { return type.lineMatcher; }
}

