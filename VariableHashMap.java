package oop.ex6.main;

import java.util.ArrayList;
import java.util.HashMap;

public class VariableHashMap {

	private HashMap<String,Variable> variables;

	VariableHashMap(){
		variables = new HashMap<>();
	}

	void add(ArrayList<Variable> varArray) throws SjavacException {

		for (Variable var : varArray) {

			if (variables.containsKey(var.key()))
				throw new SjavacException(Msg.VAR_ALREADY_INIT);		//TODO legal maybe?
			else
				variables.put(var.key(), var);
		}
	}

	int contains(Variable var){

		Variable ans = variables.get(var.key());
		if ((ans != null) && (ans.type().equals(var.type()))){
			if (!ans.isFinal())
				return 1;
			else
				return 0;
		} else
			return -1;
	}
}