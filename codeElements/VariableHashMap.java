package oop.ex6.main.codeElements;

import oop.ex6.main.Msg;
import oop.ex6.main.SjavacException;

import java.util.ArrayList;
import java.util.HashMap;

public class VariableHashMap {

	private HashMap<String,Variable> variables;

	VariableHashMap(){
		variables = new HashMap<>();
	}

	void add(ArrayList<Variable> varArray) throws SjavacException {

		for (Variable var : varArray) {

			if (variables.containsKey(var.name()))
				throw new SjavacException(Msg.VAR_ALREADY_INIT);
			else
				variables.put(var.name(), var);
		}
	}

	Variable contains(Variable var){

		return variables.get(var.name());
	}
}