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
package com.jaspersoft.studio.editor.xml.format;

public class XMLFormattingStrategy extends DefaultFormattingStrategy {

	private String initialIndentation;
	boolean lastTagWasOpening;

	public XMLFormattingStrategy() {
		super();
	}

	public void formatterStarts(String initialIndentation) {
		this.initialIndentation = initialIndentation;
	}

	public String format(String content, boolean isLineStart, String indentation, int[] positions) {

		if (isLineStart)
			indentation = initialIndentation;

		content = formatContent(content);

		// if the partition does not contain the start tag then just do indentation
		if (content.indexOf("<") == -1) {
			// just check to see whether we need to indent the next tag
			if (content.indexOf("/>") != -1) {
				lastTagWasOpening = false;
			} else {
				lastTagWasOpening = true;
			}
			return content;
		}

		// start and end tag
		if (content.indexOf("/>") != -1) {

			if (lastTagWasOpening) {
				indentation = indentation + "\t";
			}
			lastTagWasOpening = false;
			return lineSeparator + indentation + content;

		}

		// end tag
		if (content.indexOf("</") != -1) {

			boolean lastOpening = lastTagWasOpening;
			lastTagWasOpening = false;

			// if the last tag was a closing tag we need to reduce the indentation
			if (!lastOpening) {
				if (indentation.length() > 1)
					indentation = indentation.substring(0, indentation.length() - 1);

				// add new line after content
				return lineSeparator + indentation + content;
			} else {
				return content;
			}

		}

		// start tag
		if (content.indexOf("<") != -1) {

			// if the last tag was an opening tag we need to reduce the indentation
			if (lastTagWasOpening) {
				indentation = indentation + "\t";
			}

			lastTagWasOpening = true;

			// add new line after content
			return lineSeparator + indentation + content;
		}
		return content;

	}

	protected String formatContent(String content) {

		String[] contentParts = content.split("\\s+|\r|\n");
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < contentParts.length; i++) {
			buffer.append(contentParts[i].trim()).append(" ");
		}
		buffer.delete(buffer.length() - 1, buffer.length());
		return buffer.toString();
	}

	public void formatterStops() {
	}

}