/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.xml.format;

public class DocTypeFormattingStrategy extends DefaultFormattingStrategy {

	public String format(String content, boolean isLineStart, String indentation, int[] positions) {
		return lineSeparator + content;
	}

}
