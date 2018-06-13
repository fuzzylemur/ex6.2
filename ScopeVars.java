package oop.ex6.main;

import java.util.ArrayList;
import java.util.HashMap;

public class ScopeVars {

	private HashMap<String,Variable> variables;

	public ScopeVars(){
		variables = new HashMap<>();
	}

	public void add(ArrayList<Variable> varArray) throws Exception{

		for (Variable var : varArray) {
			if (variables.containsKey(var.getName()))
				throw Exception;
			else
				variables.put(var.getName(), var);
		}
	}

	public int contains(Variable var){

		Variable ans = variables.get(var.getName());
		if ((ans != null) && (ans.getType().equals(var.getType()))){
			if (!ans.isFinal())
				return 1;
			else
				return 0;
		} else
			return -1;
	}
}