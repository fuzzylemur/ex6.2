package oop.ex6.main;

import oop.ex6.main.codeElements.codeLines.Line;
import oop.ex6.main.codeElements.MainScope;
import oop.ex6.main.codeElements.Method;
import oop.ex6.main.codeElements.Scope;

import java.util.ArrayList;

public class Sjavac {

	private static final String OUT_VALID = "0";
	private static final String OUT_INVALID = "1";
	private static final String OUT_IO = "2";

	private CodeSplitter mySplitter;
	private FileParser myParser;

	private Sjavac() {

		myParser = FileParser.instance();
		mySplitter = CodeSplitter.instance();
	}

	private void verifyScope(Scope scope) throws SjavacException {

		for (Line curLine : scope.lines()) {
			curLine.verifyLinePlus();
		}
	}

	private void verifyAll(MainScope main) throws SjavacException {

		verifyScope(main);

		for (Method method : main.getMethods().values()){
			method.variables().addMap(main.variables().getMap()); // Adds the global variables to the method dataBase
			method.variables().addVars(method.params());     // Adds the variables initialized in the method def line

			verifyScope(method);
		}
	}

	private void verify(String filePath) {

		try {
			ArrayList<String> stringLines = myParser.parseFile(filePath);
			MainScope main = mySplitter.splitCode(stringLines);

			verifyAll(main);
			System.out.println(OUT_VALID);
		}
		catch (SjavacException ex) {
			System.out.println(OUT_INVALID);
			System.err.println(ex.getMessage());
		}
		catch (java.io.IOException ex){
			System.out.println(OUT_IO);
			System.err.println(Msg.getString(Msg.IO));
		}
	}

	public static void main(String[] args){

		if (args.length != 1)
			return;

		Sjavac verifier = new Sjavac();
		verifier.verify(args[0]);
	}
}
