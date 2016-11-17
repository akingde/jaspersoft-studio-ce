/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.xml.xml;

public class XMLValidationError {
	private String errorMessage;
	private int lineNumber;
	private int columnNumber;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public int getColumnNumber() {
		return columnNumber;
	}

	public void setColumnNumber(int columnNumber) {
		this.columnNumber = columnNumber;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Error on ").append(" line ").append(lineNumber).append(", column ").append(columnNumber).append(": ")
				.append(errorMessage);
		return buf.toString();
	}
}
