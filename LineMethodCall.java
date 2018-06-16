package oop.ex6.main;

import java.util.ArrayList;

public class LineMethodCall extends Line {

	LineMethodCall(LineType myType, ArrayList<Variable> varArray, String myName){
		super(myType, varArray);
		this.myName = myName;
	}

	void verifyLine() throws SjavacException {

		Method method = myScope.main().getMethod(myName);

		if (method == null) {
			throw new SjavacException(Msg.NO_METHOD);
		}

		int numParams = method.params().size();
		if (numParams != varArray.size()) {
			throw new SjavacException(Msg.PARAM_NUM);
		}

		for (int i = 0; i < numParams; i++)
			varArray.get(i).setType(method.params().get(i).type());

		method.variables().verifyValues(varArray);
	}
}