package oop.ex6.main;

public class Sjavac {

	private FileParser myParser;

	Sjavac(String filePath) {

		myParser = new FileParser();
		myParser.parseFile(filePath);
	}

	public static void main(String[] args){

	}
}
