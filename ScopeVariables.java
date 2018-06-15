package oop.ex6.main;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;

public class ScopeVariables {

	private Stack<VariableHashMap> myStack;

	ScopeVariables(){
		myStack = new Stack<>();
	}

	void addVars(ArrayList<Variable> varArray) throws SjavacException {
		myStack.peek().add(varArray);
	}

	private void verifyUse(Variable varToCheck, boolean assign) throws SjavacException {

		for (VariableHashMap vars : myStack){
			int ans = vars.contains(varToCheck);

			if (ans == 0) {
				if (assign)
					throw new SjavacException(Msg.VAR_FINAL_ASSIGN);
				else
					return;
			}

			else if (ans == 1)
				return;
		}
		throw new SjavacException(Msg.VAR_NO_INIT);
	}

	private void verifyValues(ArrayList<Variable> varArray) throws SjavacException{

		if (varArray == null)
			return;

		for (Variable var : varArray){

			String value = var.value();

			if (value == null) {
				if (var.isFinal())
					throw new SjavacException(Msg.VAR_FINAL_NO_VALUE);
				continue;
			}

			Matcher m = VarType.getMatcher(VarType.VAR_NAME).reset(value);
			if (m.matches()) {
				Variable refVar = new Variable(var.type(), value, null, false);
				verifyUse(refVar, false);
				return;
			}

			m = VarType.getMatcher(var.type()).reset(value);
			if (!m.matches())
				throw new SjavacException(Msg.VAR_INVALID_VALUE);
		}
	}
}
