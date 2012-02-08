/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */

package com.jaspersoft.studio.utils;

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
}
