package oop.ex6.main;

public class Config {

	static final String VAR_NAME = "(_[\\w]+|[A-Za-z]\\w*)";
	static final String FINAL = "(final )";

	static final String INT_WORD = "(int )";
	static final String INT_VAL = "(-?[0-9]+)";
	static final String INT_VALID_VALS = INT_VAL;

	static final String DOUBLE_WORD = "(double )";
	static final String DOUBLE_VAL = "(-?[0-9]+\\.[0-9]+)";
	static final String DOUBLE_VALID_VALS = DOUBLE_VAL+"|"+INT_VAL;

	static final String STRING_WORD = "(String )";
	static final String STRING_VAL = "(\".*\")";
	static final String STRING_VALID_VALS = STRING_VAL;

	static final String CHAR_WORD = "(char )";
	static final String CHAR_VAL = "(\'.\')";
	static final String CHAR_VALID_VALS = CHAR_VAL;

	static final String BOOLEAN_WORD = "(boolean )";
	static final String BOOLEAN_VAL = "(true|false)";
	static final String BOOLEAN_VALID_VALS = BOOLEAN_VAL+"|"+DOUBLE_VAL+"|"+INT_VAL;

	static final String TYPES = INT_WORD+"|"+DOUBLE_WORD+"|"+STRING_WORD+"|"+CHAR_WORD+"|"+BOOLEAN_WORD;
	static final String VALUES = INT_VAL+"|"+DOUBLE_VAL+"|"+STRING_VAL+"|"+CHAR_VAL+"|"+BOOLEAN_VAL;

	static final String VAR_ASSIGN = VAR_NAME+"="+VALUES+";";
	static final String NAME_OR_ASSIGN = VAR_NAME+"|"+VAR_ASSIGN;
	static final String VAR_INIT = FINAL+"?"+TYPES+"(?:"+NAME_OR_ASSIGN+",)*"+NAME_OR_ASSIGN+";";

	static final String METHOD_NAME = "([A-Za-z]\\w*)";
	static final String METHOD_WORD = "void ";

	static final String PARAM = FINAL+"?"+TYPES+VAR_NAME;
	static final String METHOD_DEF = METHOD_WORD+METHOD_NAME+"\\("+PARAM+"(?:,"+PARAM+")*\\)\\{";
	static final String METHOD_CALL = METHOD_NAME+"\\("+VALUES+"(?:,"+VALUES+")*\\)\\{";

	static final String BLOCK_LINE = "(?:if|while)\\("+BOOLEAN_VALID_VALS+"(?:(?:&&|\\|\\|)"+BOOLEAN_VALID_VALS+")*\\{";

	static final String COMMENT_LINE = "//.*";
	static final String RETURN_LINE = "return;";
	static final String CLOSE_LINE = "\\}";

	static final String RESERVED_WORDS = FINAL+"|"+TYPES+"|"+METHOD_WORD;

	static final String MSG_VAR_ALREADY_INIT = "Variable already initialized";
	static final String MSG_LINE_FORMAT = "Unknown line format";
	static final String MSG_IO = "IO exception";
	static final String MSG_MISSING_RETURN = "Missing return at method end";
	static final String MSG_INVALID_MAIN_LINE = "Invalid line for main";
	static final String MSG_DEF_IN_METHOD = "Can't define method inside method";
	static final String MSG_VAR_INVALID_VALUE = "Invalid value for this variable type";
	static final String MSG_VAR_FINAL_NO_VALUE = "Final variable needs to be assigned value";
	static final String MSG_VAR_FINAL_ASSIGN = "Final variable can't be assigned again";
	static final String MSG_VAR_NO_VALUE = "Variable hasn't been assigned a value yet";
	static final String MSG_SCOPE_OPEN = "Too many scopes opened";
	static final String MSG_SCOPE_CLOSED = "Too many scopes closed";

}
