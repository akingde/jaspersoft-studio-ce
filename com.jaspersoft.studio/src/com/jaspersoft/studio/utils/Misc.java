/*******************************************************************************
 * Copyright (C) 2005 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.utils;

import net.sf.jasperreports.engine.JRExpression;

public class Misc {

	/**
	 * Return def if object is null, otherwise it return obj.toString().
	 * 
	 * @param obj
	 * @param def
	 * @return
	 */
	public static String nvl(Object obj, String def) {
		if (obj == null)
			return def;
		else
			return obj.toString();
	}

	public static String nvl(String obj) {
		return nvl(obj, "");
	}

	public static Object nvl(Object obj, Object def) {
		if (obj == null)
			return def;
		else
			return obj.toString();
	}

	/**
	 * Don't use it. It does not work well in general, it just does the job for the purposes it has been created for. The
	 * function replaces with \n \r \t \\ sequences newlines, tabs etc...
	 * 
	 * @param str
	 * @return
	 */
	public static String addSlashesString(String str) {
		if (str == null)
			return str;

		String newStr = "";
		for (int i = 0; i < str.length(); ++i) {
			char c = str.charAt(i);
			switch (c) {
			case '\n':
				newStr += "\\n";
				break;
			case '\r':
				newStr += "\\r";
				break;
			case '\t':
				newStr += "\\t";
				break;
			case '\\':
				newStr += "\\\\";
				break;
			default:
				newStr += c;
			}
		}
		return newStr;
	}

	/**
	 * Don't use it. It does not work well in general, it just does the job for the purposes it has been created for. The
	 * function parse \n \r \t \\ characters replacing the sequences with real newline, tabs etc...
	 * 
	 * @param str
	 * @return
	 */
	public static String removeSlashesString(String str) {

		if (str == null)
			return str;

		String newStr = "";
		for (int i = 0; i < str.length(); ++i) {
			char c = str.charAt(i);
			if (c == '\\' && str.length() > i + 1) {
				i++;
				char c2 = str.charAt(i);
				switch (c2) {
				case 'n':
					newStr += "\n";
					break;
				case 'r':
					newStr += "\r";
					break;
				case 't':
					newStr += "\t";
					break;
				case '\\':
					newStr += "\\";
					break;
				default: {
					newStr += c;
					newStr += c2;
				}
				}
			} else {
				newStr += c;
			}
		}

		return newStr;
	}

	public static int indexOf(String[] array, String key) {
		for (int i = 0; i < array.length; i++)
			if (array[i].equalsIgnoreCase(key))
				return i;
		return -1;
	}

	public static String nullValue(String value) {
		if (value != null && value.trim().isEmpty())
			return null;

		return value;
	}

	public static String strReplace(String s1, String s2, String s3) {
		String string = "";
		string = "";

		if (s2 == null || s3 == null || s2.length() == 0)
			return s3;

		int pos_i = 0; // posizione corrente.
		int pos_f = 0; // posizione corrente finale

		int len = s2.length();
		while ((pos_f = s3.indexOf(s2, pos_i)) >= 0) {
			string += s3.substring(pos_i, pos_f) + s1;
			// +string.substring(pos+ s2.length());
			pos_f = pos_i = pos_f + len;

		}

		string += s3.substring(pos_i);
		return string;
	}

	public static String getExpressionText(JRExpression exp) {
		if (exp == null)
			return "";
		if (exp.getText() == null)
			return "";
		return exp.getText();
	}

	public static boolean compare(String a, String b, boolean caseSensitive) {
		if (caseSensitive)
			return a.equals(b);
		return a.equalsIgnoreCase(b);
	}
}
