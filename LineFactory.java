package oop.ex6.main;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class LineFactory {

	String FINAL = "\\s*(final)\\s+";
	String NUMBER_VALUE = "([0-9]+)|([0-9]+\\.?[0-9]+)";
	String BOOLEAN_VALUE = "(true|false)";
	String STRING_VALUE = "([\"].*[\"])";
	String CHAR_VALUE = "([\'].[\'])";

	String VAR_TYPES = "(int|boolean|String|char|double)";
	String VAR_NAME = "\\s*(_[\\w]+)|([A-Za-z]\\w*)\\s*";
	String METHOD_NAME = "([A-Za-z]\\w*)";
	String VALUE = NUMBER_VALUE+"|"+BOOLEAN_VALUE+"|"+STRING_VALUE+"|"+CHAR_VALUE+"|"+VAR_NAME;
	String VARY_ASSIGN = VAR_NAME+"\\s*(=)\\s*"+VALUE+"\\s*";


	Pattern BLANK = Pattern.compile("(//.*)|\\s*");
	Pattern RETURN = Pattern.compile("\\s*return\\s*;\\s*");
	Pattern CLOSE = Pattern.compile("\\s*}\\s*");
	Pattern VAR_INIT = Pattern.compile(FINAL+"?"+VAR_TYPES+"(("+VAR_NAME+"|"+VARY_ASSIGN+"),)*" +
			"("+VAR_NAME+"|"+VARY_ASSIGN+");\\s*");
	Pattern VAR_ASSIGN = Pattern.compile(VARY_ASSIGN);

	Matcher BLANK_MATCHER;
	Matcher RETURN_MATCHER;
	Matcher VAR_INIT_MATCHER;
	Matcher VAR_ASSIGN_MATCHER;
	Matcher METHOD_CALL_MATCHER;
	Matcher METHOD_DEF_MATCHER;
	Matcher CLOSE_MATCHER;
	Matcher BLOCK_MATCHER;

	Matcher[] matcherArray = {BLANK_MATCHER, RETURN_MATCHER, VAR_INIT_MATCHER, VAR_ASSIGN_MATCHER, METHOD_CALL_MATCHER,
			METHOD_DEF_MATCHER, CLOSE_MATCHER, BLOCK_MATCHER};
	Pattern[] patternArray = {BLANK, RETURN, VAR_INIT, VAR_ASSIGN, METHOD_CALL,
			METHOD_DEF, CLOSE, BLOCK};


	LineFactory(){

		for (int i=0; i < patternArray.length; i++){
			matcherArray[i] = patternArray[i].matcher("");
		}
	}

	Line createLine(String lineString){

		for (Matcher m : matcherArray){
			m.reset(lineString);
		}


		if (BLANK_MATCHER.matches()){
			return new Line(LineType.BLANK);
		}
		else if (RETURN_MATCHER.matches()){
			return new Line(LineType.RETURN);
		}
		else if (CLOSE_MATCHER.matches()){
			return new Line(LineType.CLOSE);
		}
		else if (VAR_INIT_MATCHER.matches()){
			return variableHelper(VAR_INIT_MATCHER, LineType.VAR_INIT);
		}
		else if (VAR_ASSIGN_MATCHER.matches()){
			return variableHelper(VAR_ASSIGN_MATCHER, LineType.VAR_ASSIGN);
		}
		else if (METHOD_CALL_MATCHER.matches()){
			return methodCallHelper(VARIABLE_MATCHER);
		}
		else if (METHOD_DEF_MATCHER.matches()){
			return methodDefHelper(VARIABLE_MATCHER);
		}
		else if (BLOCK_MATCHER.matches()){
			return new Line(block);
		}
		throw exception;
	}

	Line variableHelper(Matcher m, LineType initOrAssign){

		int start = 0;
		boolean isFinal = false;

		if (initOrAssign == LineType.VAR_INIT) {
			if (m.group(0) == "final") {
				isFinal = true;
				start = 1;
			}
		}

		VarType myType = VarType.getType(m.group(start));
		ArrayList<Variable> myVars = new ArrayList<>();

		int i = start;
		while(i < m.groupCount()) {

			if (m.group(i + 1).equals("=")) {
				myVars.add(new Variable(myType, m.group(i), m.group(i + 2), isFinal));
				i += 3;
			} else {
				myVars.add(new Variable(myType, m.group(i), m.group(i + 1), isFinal));
				i += 2;
			}
		}
		return new Line(initOrAssign, myVars);
	}
}
