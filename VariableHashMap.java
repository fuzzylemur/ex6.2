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

			if (variables.containsKey(var.name()))
				throw new SjavacException(Msg.VAR_ALREADY_INIT);		//TODO legal maybe?
			else
				variables.put(var.name(), var);
		}
	}

	int contains(Variable var) {

		Variable ans = variables.get(var.name());
		if (ans != null && ans.type() == var.type()) {
			if (!ans.isFinal())
				return 1;
			else
				return 0;
		} else
			return -1;
	}

	Variable getVariable(Variable var){
		return variables.get(var.name());
	}
}