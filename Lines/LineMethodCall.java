package oop.ex6.main.Lines;

import oop.ex6.main.Method;
import oop.ex6.main.Msg;
import oop.ex6.main.SjavacException;
import oop.ex6.main.Variable;

import java.util.ArrayList;

public class LineMethodCall extends Line {

	public LineMethodCall(ArrayList<Variable> varArray, String methodName){
		super(varArray);
		this.methodName = methodName;
		this.myType = LineType.METHOD_CALL;
	}

	public void verifyLine() throws SjavacException {

		Method method = myScope.main().getMethod(methodName);

		if (method == null) {
			throw new SjavacException(Msg.NO_METHOD);
		}

		int numParams = method.params().size();
		if (numParams != varArray.size()) {
			throw new SjavacException(Msg.PARAM_NUM);
		}

		for (int i = 0; i < numParams; i++)
			varArray.get(i).setType(method.params().get(i).type());

		for (Variable var : varArray)
			myScope.variables().verifyValue(var);
	}
}