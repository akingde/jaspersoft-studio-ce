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

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLValidationErrorHandler extends DefaultHandler {

	private List<XMLValidationError> errorList = new ArrayList<XMLValidationError>();
	private Locator locator;

	public XMLValidationErrorHandler() {
	}

	public void error(SAXParseException e) throws SAXException {

		handleError(e, false);

	}

	public void setDocumentLocator(Locator locator) {
		this.locator = locator;
	}

	private void handleError(SAXParseException e, boolean isFatal) {
		XMLValidationError validationError = nextError(e, isFatal);
		errorList.add(validationError);
		System.out.println(validationError.toString());

	}

	protected XMLValidationError nextError(SAXParseException e, boolean isFatal) {
		String errorMessage = e.getMessage();

		int lineNumber = locator.getLineNumber();
		int columnNumber = locator.getColumnNumber();

		log(this, (isFatal ? "FATAL " : "Non-Fatal") + "Error on line " + lineNumber + ", column " + columnNumber + ": "
				+ errorMessage);

		XMLValidationError validationError = new XMLValidationError();
		validationError.setLineNumber(lineNumber);
		validationError.setColumnNumber(columnNumber);
		validationError.setErrorMessage(errorMessage);
		return validationError;
	}

	private void log(XMLValidationErrorHandler handler, String string) {
	}

	public void fatalError(SAXParseException e) throws SAXException {
		handleError(e, true);
	}

	public List<XMLValidationError> getErrorList() {
		return errorList;
	}

}
