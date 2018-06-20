package oop.ex6.main;

import oop.ex6.main.Lines.Line;

import java.util.ArrayList;

public class Sjavac {

	private CodeSplitter mySplitter;
	private ArrayList<String> stringLines;
	private FileParser myParser;

	private Sjavac() {

		myParser = FileParser.instance();
		mySplitter = CodeSplitter.instance();
	}

	private void verifyScope(Scope scope) throws SjavacException {

		for (Line line : scope.lines()) {
			line.verifyLinePlus();
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

	private void verify(String path) {

		try {
			stringLines = myParser.parseFile(path);
			MainScope main = splitCode(stringLines);
			verifyAll(main);
			System.out.println("0");

		} catch (SjavacException ex) {
			System.out.println("1");
			System.err.println(ex.getMessage());
		}
		catch (java.io.IOException ex){
		System.out.println("2");
		System.err.println(Msg.getString(Msg.IO));
		}
	}

	private MainScope splitCode(ArrayList<String> stringLines) throws SjavacException{

		return mySplitter.splitCode(stringLines);
	}


	public static void main(String[] args){

		if (args.length != 1)
			return;

		Sjavac verifier = new Sjavac();
		verifier.verify(args[0]);
	}
}
