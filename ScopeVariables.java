package oop.ex6.main;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;

public class ScopeVariables {

	private Stack<VariableHashMap> myStack;

	ScopeVariables(){
		myStack = new Stack<>();
		myStack.push(new VariableHashMap());
	}

	public void addVars(ArrayList<Variable> varArray) throws SjavacException {
		for (Variable var : varArray) {
			verifyValue(var);
		}
		myStack.peek().add(varArray);
	}

	public void verifyAssign(Variable varToCheck) throws SjavacException {
		// finds the type of the assigned variable.
		VarType myType = findType(varToCheck);
		varToCheck.setType(myType);
		verifyValue(varToCheck);

		for (VariableHashMap vars : myStack){

			int ans = vars.contains(varToCheck);

			if (ans == 0)
				throw new SjavacException(Msg.VAR_FINAL_ASSIGN);
			else if (ans == 1)
				return;
		}
		throw new SjavacException(Msg.VAR_NO_INIT);
	}

	private VarType findType(Variable var){

		for (VariableHashMap vars : myStack) {
			Variable ans = vars.getVariable(var);
			if (ans != null) {
				return ans.type();
			}
		}
		return null;
	}

	public void verifyUse(Variable varToCheck) throws SjavacException {

		for (VariableHashMap vars : myStack){

			int ans = vars.contains(varToCheck);

			if (ans == 0 || ans == 1)
				return;
		}
		throw new SjavacException(Msg.VAR_NO_INIT);
	}

	public void verifyValue(Variable var) throws SjavacException {

		if (var == null)
			return;

		String value = var.value();

		if (value == null) {
			if (var.isFinal()) // assign null value to final var is very bad.
				throw new SjavacException(Msg.VAR_FINAL_NO_VALUE);
			return;
		}

		Matcher m = VarType.getMatcher(VarType.VAR_NAME).reset(value);
		if (m.matches()) {
			Variable refVar = new Variable(var.type(), var.value(), null, false);
			verifyUse(refVar);
		} else {
			m = VarType.getMatcher(var.type()).reset(value);
			if (!m.matches())
				throw new SjavacException(Msg.VAR_INVALID_VALUE);
		}
	}

	public void openScope(){
		myStack.push(new VariableHashMap());
	}

	public void closeScope(){
		myStack.pop();
	}
}
