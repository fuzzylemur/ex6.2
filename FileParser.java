package oop.ex6.main;

import java.io.*;
import java.util.ArrayList;

public class FileParser {

	private static FileParser myInstance = new FileParser();

	private FileParser(){}

	/* @return the class one and only instance */
	static FileParser instance() {return myInstance;}


	ArrayList<String> parseFile(String filePath) throws IOException {

		ArrayList<String> stringArray = new ArrayList<>();

		File commandFile = new File(filePath);

		try (FileReader reader = new FileReader(commandFile);
			 BufferedReader buffReader = new BufferedReader(reader);
			 LineNumberReader lineReader = new LineNumberReader(buffReader)) {

			String lineStr = lineReader.readLine();
			while (lineStr != null) {
				stringArray.add(lineStr);
				lineStr = lineReader.readLine();
			}
			return stringArray;
		}
	}
}
