package oop.ex6.main;

import java.util.ArrayList;
import java.util.HashMap;

public class ScopeVars {

	private HashMap<String,Variable> variables;

	public ScopeVars(){
		variables = new HashMap<>();
	}

	public ScopeVars(ArrayList<Variable> vars) throws Exception{
		variables = new HashMap<>();
		add(vars);
	}

	public void add(ArrayList<Variable> varArray) throws Exception{

		for (Variable var : varArray) {
			if (variables.containsKey(var.name()))
				throw Exception;
			else
				variables.put(var.name(), var);
		}
	}

	public int contains(Variable var){

		Variable ans = variables.get(var.name());
		if ((ans != null) && (ans.type().equals(var.type()))){
			if (!ans.isFinal())
				return 1;
			else
				return 0;
		} else
			return -1;
	}
}