package oop.ex6.main;

import java.util.HashMap;

public class MainScope extends Scope{

	HashMap<String, Method> myMethods;

	MainScope() {
		super();
		myMethods = new HashMap<>();
	}

	void addMethod(Method method) throws SjavacException{

		if (myMethods.containsKey(method.name()))
			throw new SjavacException(Msg.METHOD_OVERLOAD);

		myMethods.put(method.name(), method);
	}

	Method getMethod(String name) {

		return myMethods.get(name);
	}
}
