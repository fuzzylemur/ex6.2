package oop.ex6.main;

import java.util.ArrayList;

public class LineMethodCall extends Line{

	private ArrayList<Variable> varArray;
	private String myName;

	LineMethodCall(LineType myType, ArrayList<Variable> varArray, String myName){
		super(myType);
		this.varArray = varArray;
		this.myName = myName;
	}

	void verifyLine(Scope scope) throws SjavacException{

		Method method = scope.main().getMethod(name);

		if (method == null) {
			throw new SjavacException(Msg.NO_METHOD);
		}

		int numParams = method.params().size();
		if (numParams != line.varArray().size()) {
			throw new SjavacException(Msg.PARAM_NUM);
		}

		for (int i = 0; i < numParams; i++)
			line.varArray().get(i).setType(method.params().get(i).type());

		verifyValues(line.varArray());
	}
}