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
package com.jaspersoft.studio.editor.xml.scanners;

import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

import com.jaspersoft.studio.editor.xml.rules.NonMatchingRule;
import com.jaspersoft.studio.editor.xml.rules.StartTagRule;
import com.jaspersoft.studio.editor.xml.rules.XMLTextPredicateRule;


public class XMLPartitionScanner extends RuleBasedPartitionScanner
{

	public final static String XML_DEFAULT = "__xml_default";
	public final static String XML_COMMENT = "__xml_comment";
	public final static String XML_PI = "__xml_pi";
	public final static String XML_DOCTYPE = "__xml_doctype";
	public final static String XML_CDATA = "__xml_cdata";
	public final static String XML_START_TAG = "__xml_start_tag";
	public final static String XML_END_TAG = "__xml_end_tag";
	public final static String XML_TEXT = "__xml_text";

public XMLPartitionScanner()
{

	IToken xmlComment = new Token(XML_COMMENT);
	IToken xmlPI = new Token(XML_PI);
	IToken startTag = new Token(XML_START_TAG);
	IToken endTag = new Token(XML_END_TAG);
	IToken docType = new Token(XML_DOCTYPE);
	IToken text = new Token(XML_TEXT);

	IPredicateRule[] rules = new IPredicateRule[7];

	rules[0] = new NonMatchingRule();
	rules[1] = new MultiLineRule("<!--", "-->", xmlComment);
	rules[2] = new MultiLineRule("<?", "?>", xmlPI);
	rules[3] = new MultiLineRule("</", ">", endTag);
	rules[4] = new StartTagRule(startTag);
	rules[5] = new MultiLineRule("<!DOCTYPE", ">", docType);
	rules[6] = new XMLTextPredicateRule(text);

	setPredicateRules(rules);
}
}