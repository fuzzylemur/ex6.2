package oop.ex6.main;

import oop.ex6.main.Lines.*;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class LineFactory {

	LineFactory(){}

	Line createLine(String lineString) throws SjavacException {

		String cleanStr = cleanLine(lineString);
		if (cleanStr.equals(""))
			return new LineComment();

		LineType chosenType = determineLineType(cleanStr);
		Matcher chosenMatcher = LineType.getMatcher(chosenType);

		switch(chosenType) {

			case COMMENT:
				return new LineComment();

			case RETURN:
				return new LineReturn();

			case CLOSE:
				return new LineClose();

			case VAR_INIT:
				return initHelper(chosenMatcher);

			case VAR_ASSIGN:
				return assignHelper(chosenMatcher);

			case METHOD_CALL:
				return methodCallHelper(chosenMatcher);

			case METHOD_DEF:
				return methodDefHelper(chosenMatcher);

			case BLOCK:
				return blockHelper(chosenMatcher);
		}
		throw new SjavacException(Msg.LINE_FORMAT);						// TODO necessary?
	}

	private String cleanLine(String lineString) {

		String[] split = lineString.split("\\s+");

		for (int i=0; i < split.length; i++){
			if (split[i].matches(LineType.getReservedWords()))
				split[i] += " ";
		}
		return String.join("", split);
	}

	private LineType determineLineType(String lineString) throws SjavacException{

		for (LineType type : LineType.values()){
			if (LineType.getMatcher(type).reset(lineString).matches()) {
				return type;
			}
		}
		throw new SjavacException(Msg.LINE_FORMAT);
	}

	private Line initHelper(Matcher m){

		int start = 0;
		boolean isFinal = false;

		if (m.group(1).equals("final")) {
			isFinal = true;
			start = 1;
		}

		VarType myType = VarType.getType(m.group(start));
		ArrayList<Variable> myVars = new ArrayList<>();

		String[] split = m.group(start+1).split(",");

		for (String element : split){
			String[] split2 = element.split("=",2);
			if (split2.length == 1)
				myVars.add(new Variable(myType, split2[0], null, isFinal));
			else
				myVars.add(new Variable(myType, split2[0], split2[1], isFinal));
		}
		return new LineVarInit(myVars);
	}

	private Line assignHelper(Matcher m) {

		String[] split = m.group(1).split("=");
		Variable var = new Variable(null, split[0], split[1], false);
		return new LineVarAssign(var);
	}


	private Line blockHelper(Matcher m) {

		ArrayList<Variable> myVars = new ArrayList<>();

		String[] split = m.group(1).split("&&|\\|\\|");

		for (String element : split){
			myVars.add(new Variable(VarType.BOOLEAN, null , element, false));
		}
		return new LineBlock(myVars);
	}

	private Line methodDefHelper(Matcher m) {

		ArrayList<Variable> myVars = new ArrayList<>();

		String[] split = m.group(2).split(",");

		for (String element : split){
			String[] split2 = element.split(" ");
			if (split2.length == 3)
				myVars.add(new Variable(VarType.getType(split2[1]), split2[2], null, true));
			else
				myVars.add(new Variable(VarType.getType(split2[1]), split2[2], null, false));
		}

		return new LineMethodDef(myVars, m.group(1));
	}

	private Line methodCallHelper(Matcher m) {

		ArrayList<Variable> myVars = new ArrayList<>();

		String[] split = m.group(2).split(",");

		for (String element : split){
			myVars.add(new Variable(null, null, element, false));

		}
		return new LineMethodCall(myVars, m.group(1));
	}

}
