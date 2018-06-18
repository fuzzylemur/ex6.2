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

	VariableHashMap getMap(){return myStack.peek();}

	void addMap(VariableHashMap map){
		myStack.pop();
		myStack.push(map);
		myStack.push(new VariableHashMap());
	}

	public void addVars(ArrayList<Variable> varArray) throws SjavacException {
		myStack.peek().add(varArray);
	}

	public void verifyAssign(Variable varToCheck) throws SjavacException {
		// finds the type of the assigned variable.
		VarType myType = findType(varToCheck);
		varToCheck.setType(myType);
		verifyValue(varToCheck);
	}

	private VarType findType(Variable var) throws SjavacException{

		for (VariableHashMap vars : myStack) {
			Variable ans = vars.contains(var);
			if (ans != null) {
				System.out.println(ans.type() + "  " + ans.isFinal());
				if (ans.isFinal())
					throw new SjavacException(Msg.VAR_FINAL_ASSIGN);
				else
					return ans.type();
			}
		}
		throw new SjavacException(Msg.VAR_NO_INIT);
	}

	public void verifyUse(Variable varToCheck) throws SjavacException {

		for (VariableHashMap vars : myStack){

			Variable ans = vars.contains(varToCheck);
			if (ans != null)
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
		if (m.matches() && !value.matches(VarType.BOOLEAN.valuePattern)) {
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
