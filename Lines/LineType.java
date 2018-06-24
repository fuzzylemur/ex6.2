package oop.ex6.main.Lines;

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

	public static Matcher getMatcher(LineType type) {
		return type.lineMatcher;
	}
}


