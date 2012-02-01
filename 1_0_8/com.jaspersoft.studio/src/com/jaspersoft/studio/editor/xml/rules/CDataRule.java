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
package com.jaspersoft.studio.editor.xml.rules;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class CDataRule implements IRule {

	IToken fToken;
	StringBuffer buffer = new StringBuffer();
	int charsRead = 0;

	private String matchString;
	private static final String START_MATCH_STRING = "<![CDATA[";
	private static final String END_MATCH_STRING = "]]>";

	public CDataRule(IToken token, boolean start) {
		super();
		this.fToken = token;
		this.matchString = start ? START_MATCH_STRING : END_MATCH_STRING;
	}

	/*
	 * @see IRule#evaluate(ICharacterScanner)
	 */
	public IToken evaluate(ICharacterScanner scanner) {

		buffer.setLength(0);

		charsRead = 0;
		int c = read(scanner);

		if (c == matchString.charAt(0)) {
			do {
				c = read(scanner);
			} while (isOK((char) c));

			if (charsRead == matchString.length()) {
				return fToken;
			} else {
				rewind(scanner);
				return Token.UNDEFINED;
			}

		}

		scanner.unread();
		return Token.UNDEFINED;
	}

	private void rewind(ICharacterScanner scanner) {
		int rewindLength = charsRead;
		while (rewindLength > 0) {
			scanner.unread();
			rewindLength--;
		}
	}

	private int read(ICharacterScanner scanner) {
		int c = scanner.read();
		buffer.append((char) c);
		charsRead++;
		return c;
	}

	private boolean isOK(char c) {
		if (charsRead >= matchString.length())
			return false;
		if (matchString.charAt(charsRead - 1) == c)
			return true;
		else
			return false;
	}
}