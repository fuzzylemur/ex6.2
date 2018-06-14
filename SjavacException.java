package oop.ex6.main;

public class SjavacException extends Exception{

	private static final String MSG = "Exception in line ";
	private static final long serialVersionUID = 1L;

	private String details;
	private int lineNum;

	SjavacException(String details){
		this.details = details;
		this.lineNum = -1;

	}

	SjavacException(String details, int lineNum){
		this.details = details;
		this.lineNum = lineNum;
	}

	void setLineNum(int lineNum){
		this.lineNum = lineNum;
	}

	public String getMessage() {

		if (lineNum == -1)
			return details;
		else
			return MSG + lineNum + ": " + details;
	}
}
