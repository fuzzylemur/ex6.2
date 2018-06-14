package oop.ex6.main;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class LineFactory {

	LineFactory(){}

	Line createLine(String lineString) throws SjavacException {

		String cleanStr = cleanLine(lineString);
		if (cleanStr.equals(""))
			return new Line(LineType.COMMENT);

		LineType chosenType = determineLineType(cleanStr);
		Matcher chosenMatcher = LineType.getMatcher(chosenType);

		switch(chosenType) {

			case COMMENT:
				return new Line(LineType.COMMENT);

			case RETURN:
				return new Line(LineType.RETURN);

			case CLOSE:
				return new Line(LineType.CLOSE);

			case VAR_INIT:
				return variableHelper(chosenMatcher, LineType.VAR_INIT);

			case VAR_ASSIGN:
				return variableHelper(chosenMatcher, LineType.VAR_ASSIGN);

			case METHOD_CALL:
				return methodCallHelper(chosenMatcher);

			case METHOD_DEF:
				return methodDefHelper(chosenMatcher);

			case BLOCK:
				return blockHelper(chosenMatcher);
		}
		throw new SjavacException(Config.MSG_LINE_FORMAT);						// TODO necessary?
	}

	private String cleanLine(String lineString) {

		String[] split = lineString.split("\\s+");

		for (int i=0; i < split.length; i++){
			if (split[i].matches(Config.RESERVED_WORDS))
				split[i] = split[i].concat(" ");
		}
		return String.join("", split);
	}

	private LineType determineLineType(String lineString) throws SjavacException{

		for (LineType type : LineType.values()){
			if (LineType.getMatcher(type).reset(lineString).matches()) {
				return type;
			}
		}
		throw new SjavacException(Config.MSG_LINE_FORMAT);
	}

	private Line variableHelper(Matcher m, LineType type){

		int start = 0;
		boolean isFinal = false;

		if (type == LineType.VAR_INIT) {
			if (m.group(0).equals(Config.FINAL)) {
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
		return new Line(type, myVars);
	}

	private Line blockHelper(Matcher m) {

		ArrayList<Variable> myVars = new ArrayList<>();
		Matcher varMatcher = VarType.getMatcher(VarType.VAR);

		int i=0;
		while (i < m.groupCount()) {

			varMatcher.reset(m.group(i));
			if (varMatcher.matches())
				myVars.add(new Variable(VarType.BOOLEAN, m.group(i), null, false));
		}
		return new Line(LineType.BLOCK, myVars);
	}

	private Line methodDefHelper(Matcher m) {

		ArrayList<Variable> myVars = new ArrayList<>();

		int i = 1;
		while(i < m.groupCount()) {

			if (m.group(i).equals(Config.FINAL)) {
				myVars.add(new Variable(VarType.getType(m.group(i+1)), m.group(i+2),null,true));
				i += 3;
			} else {
				myVars.add(new Variable(VarType.getType(m.group(i)), m.group(i+1),null,false));
				i += 2;
			}
		}
		return new Line(LineType.METHOD_DEF, myVars, m.group(0));
	}

	private Line methodCallHelper(Matcher m) {

		ArrayList<Variable> myVars = new ArrayList<>();
		Matcher varMatcher = VarType.getMatcher(VarType.VAR);

		int i = 1;
		while (i < m.groupCount()) {

			varMatcher.reset(m.group(i));
			if (varMatcher.matches())
				myVars.add(new Variable(VarType.VAR, m.group(i), null, false));
			else
				myVars.add(new Variable(null,null, m.group(i), false));
		}
		return new Line(LineType.METHOD_CALL, myVars, m.group(0));
	}

}
