/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.xml.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;

import org.apache.xerces.parsers.SAXParser;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/**
 * Performs DTD validation on supplied XML document
 */
public class XMLParser {

	private ErrorHandler errorHandler;
	private ContentHandler contentHandler;

	public XMLParser() {
		super();
	}

	public void setErrorHandler(ErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
	}

	public void setContentHandler(ContentHandler contentHandler) {
		this.contentHandler = contentHandler;
	}

	public static final String VALIDATION_FEATURE = "http://xml.org/sax/features/validation";

	/**
	 * Does DTD-based validation on File
	 */
	public void doParse(File xmlFilePath) throws RuntimeException {

		InputSource inputSource = null;
		try {
			inputSource = new InputSource(new FileReader(xmlFilePath));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		doParse(inputSource);

	}

	/**
	 * Does DTD-based validation on text
	 */
	public void doParse(String xmlText) throws RuntimeException {

		InputSource inputSource = new InputSource(new StringReader(xmlText));
		doParse(inputSource);

	}

	/**
	 * Does DTD-based validation on inputSource
	 */
	public void doParse(InputSource inputSource) throws RuntimeException {

		try {
			XMLReader reader = new SAXParser();
			reader.setErrorHandler(errorHandler);
			reader.setContentHandler(contentHandler);
			reader.setFeature(VALIDATION_FEATURE, true);
			reader.parse(inputSource);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
