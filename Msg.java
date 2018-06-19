package oop.ex6.main;

public enum Msg {

	IO 						("IO exception"),
	VAR_ALREADY_INIT 		("Variable already initialized"),
	VAR_INVALID_VALUE 		("Invalid value for this variable type"),
	VAR_FINAL_NO_VALUE 		("Final variable needs to be assigned value"),
	VAR_FINAL_ASSIGN 		("Final variable can't be assigned again"),
	VAR_NO_ASSIGN           ("Can't use a variable that has not been assigned a value"),
	VAR_WRONG_TYPE          ("Use in wrong type of variable"),
	VAR_NO_INIT 			("Variable hasn't been initialized"),
	LINE_FORMAT 			("Unknown line format"),
	MISSING_RETURN 			("Missing return at method end"),
	INVALID_MAIN_LINE 		("Invalid line for main"),
	DEF_IN_METHOD 			("Can't define method inside method"),
	METHOD_OVERLOAD 		("Can't name two methods with the same name"),
	NO_METHOD 				("No such method was defined"),
	PARAM_NUM 				("Wrong number of parameters"),
	SCOPE_OPEN 				("Too many scopes opened"),
	SCOPE_CLOSED 			("Too many scopes closed");

	String msgString;

	Msg(String msgString){this.msgString = msgString;}

	static String getString(Msg m) {return m.msgString;}
}
