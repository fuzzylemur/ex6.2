package oop.ex6.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class FileParser {


	ArrayList<String> parseFile(String filePath) {

		ArrayList<String> stringArray = new ArrayList<>();

		File commandFile = new File(filePath);

		try (FileReader reader = new FileReader(commandFile);
			 BufferedReader buffReader = new BufferedReader(reader);
			 LineNumberReader lineReader = new LineNumberReader(buffReader)) {

			String lineStr = lineReader.readLine();
			while (lineStr != null) {
				stringArray.add(lineStr);
			}
			return stringArray;

		} catch (java.io.IOException ex){

		}
		return null;
	}
}
