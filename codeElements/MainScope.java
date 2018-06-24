package oop.ex6.main.codeElements;

import oop.ex6.main.Msg;
import oop.ex6.main.SjavacException;

import java.util.HashMap;

public class MainScope extends Scope{

	private HashMap<String, Method> myMethods;

	public MainScope() {
		super(null);
		myMethods = new HashMap<>();
	}

	public void addMethod(Method method) throws SjavacException {

		if (myMethods.containsKey(method.name()))
			throw new SjavacException(Msg.METHOD_OVERLOAD);

		myMethods.put(method.name(), method);
	}

	public Method getMethod(String name) {

		return myMethods.get(name);
	}

	public HashMap<String, Method> getMethods() {return myMethods;}
}
