package oop.ex6.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class FileParser {


	ArrayList<Line> parseFile(String filePath) {

		ArrayList<Line> lineArray = new ArrayList<>();

		File commandFile = new File(filePath);

		try (FileReader reader = new FileReader(commandFile);
			 BufferedReader buffReader = new BufferedReader(reader);
			 LineNumberReader lineReader = new LineNumberReader(buffReader)) {

			String lineStr = lineReader.readLine();
			while (lineStr != null) {
				lineArray.add(new Line(lineReader.getLineNumber(), lineStr));
			}
			return lineArray;

		} catch (java.io.IOException ex){

		}
		return null;
	}
}
