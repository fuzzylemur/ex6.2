package oop.ex6.main;

import oop.ex6.main.Lines.*;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class LineFactory {

	private static LineFactory myInstance = new LineFactory();

	private LineFactory(){}

	/* @return the class one and only instance */
	static LineFactory instance() {return myInstance;}

	Line createLine(String lineString) throws SjavacException {

		LineType chosenType = determineLineType(lineString);
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
		throw new SjavacException(Msg.LINE_FORMAT);
	}

	private LineType determineLineType(String lineString) throws SjavacException{

		for (LineType type : LineType.values()){
			if (LineType.getMatcher(type).reset(lineString).matches()) {

				//System.out.println(type.name());
				//for (int i = 0; i < LineType.getMatcher(type).groupCount(); i++) {
				//	System.out.println(i + ": " + LineType.getMatcher(type).group(i));
				//}

				return type;
			}
		}
		throw new SjavacException(Msg.LINE_FORMAT);
	}

	private Line initHelper(Matcher m){

		boolean isFinal = false;

		if (m.group(1) != null) {
			isFinal = true;
		}

		VarType myType = VarType.getType(m.group(2));
		ArrayList<Variable> myVars = new ArrayList<>();

		String[] split = clean(m.group(3)).split(RegexConfig.VAR_DELIM);

		for (String element : split){
			String[] split2 = element.split("=");
			if (split2.length == 1)
				myVars.add(new Variable(myType, split2[0], null, isFinal));
			else
				myVars.add(new Variable(myType, split2[0], split2[1], isFinal));
		}
		return new LineVarInit(myVars);
	}

	private Line assignHelper(Matcher m) {

		String[] split = clean(m.group(1)).split("=");
		Variable var = new Variable(null, split[0], split[1], false);

		return new LineVarAssign(var);
	}


	private Line blockHelper(Matcher m) {

		ArrayList<Variable> myVars = new ArrayList<>();

		String[] split = clean(m.group(1)).split(RegexConfig.COND_DELIM);

		for (String element : split){
			myVars.add(new Variable(VarType.BOOLEAN, null , element, false));
		}										// TODO split cases with var name
		return new LineBlock(myVars);
	}

	private Line methodDefHelper(Matcher m) {

		ArrayList<Variable> myVars = new ArrayList<>();

		if (m.group(2) != null) {

			String[] split = m.group(2).split(RegexConfig.VAR_DELIM);
			for (String element : split) {
				String[] split2 = element.split("\\s");
				if (split2.length == 3)
					myVars.add(new Variable(VarType.getType(split2[1]), split2[2],
							Variable.ASSIGNED, true));
				else
					myVars.add(new Variable(VarType.getType(split2[0]), split2[1],
							Variable.ASSIGNED, false));
			}
		}

		return new LineMethodDef(myVars, m.group(1));
	}

	private Line methodCallHelper(Matcher m) {

		ArrayList<Variable> myVars = new ArrayList<>();

		if (m.group(2) != null) {

			String[] split = clean(m.group(2)).split(RegexConfig.VAR_DELIM);

			for (String element : split)
				myVars.add(new Variable(null, null, element, false));

		}
		return new LineMethodCall(myVars, m.group(1));
	}

	private String clean(String str) {
		return str.replaceAll("\\s","");
	}

}
