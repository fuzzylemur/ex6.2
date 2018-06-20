package oop.ex6.main;

public class SjavacException extends Exception{

	private static final String PREFIX = "Exception in line ";
	private static final long serialVersionUID = 1L;

	private Msg msg;
	private int lineNum;

	public SjavacException(Msg message){
		this.msg = message;
		this.lineNum = -1;

	}

	public SjavacException(Msg message, int lineNum){
		this.msg = message;
		this.lineNum = lineNum;
	}

	public void setLineNum(int lineNum) {this.lineNum = lineNum;}

	public String getMessage() {

		if (lineNum == -1)
			return Msg.getString(msg);
		else
			return PREFIX + lineNum + ": " + Msg.getString(msg);
	}
}
