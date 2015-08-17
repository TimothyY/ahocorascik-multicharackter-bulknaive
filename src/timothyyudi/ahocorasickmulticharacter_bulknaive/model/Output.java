package timothyyudi.ahocorasickmulticharacter_bulknaive.model;

import timothyyudi.ahocorasickmulticharacter_bulknaive.controller.AhoCorasick;

public class Output {

	String outputString;
	int lineNumber,endColumnNumber,outputStartPoint,outputEndPoint;
	
	public Output(Integer hashCode, int lineNumber, int endColumnNumber) {
		super();
		this.outputString = AhoCorasick.fullKeywordMap.get(hashCode);
		this.lineNumber = lineNumber;
		this.outputStartPoint = endColumnNumber - this.outputString.length();
		this.outputEndPoint = endColumnNumber - 1;
	}

	public String getOutputString() {
		return outputString;
	}

	public void setOutputString(String outputString) {
		this.outputString = outputString;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public int getEndColumnNumber() {
		return endColumnNumber;
	}

	public void setEndColumnNumber(int endColumnNumber) {
		this.endColumnNumber = endColumnNumber;
	}

	public int getOutputStartPoint() {
		return outputStartPoint;
	}

	public void setOutputStartPoint(int outputStartPoint) {
		this.outputStartPoint = outputStartPoint;
	}

	public int getOutputEndPoint() {
		return outputEndPoint;
	}

	public void setOutputEndPoint(int outputEndPoint) {
		this.outputEndPoint = outputEndPoint;
	}
	
}
