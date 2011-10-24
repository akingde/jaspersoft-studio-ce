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

package com.jaspersoft.studio.preferences.editor.pages;

import java.util.StringTokenizer;

public class Pages {
	private Integer page;

	public Integer getPage() {
		return page;
	}

	public Integer getFrom() {
		return from;
	}

	public Integer getTo() {
		return to;
	}

	private Integer from;
	private Integer to;

	public Pages parseString(String key) {
		if (key.equals("all")) {
		} else if (key.contains(";")) {
			StringTokenizer st = new StringTokenizer(key, ";");
			from = new Integer(0);
			to = new Integer(0);
			try {
				from = new Integer(st.nextToken());
				to = new Integer(st.nextToken());
			} catch (NumberFormatException e) {
			}
			if (to < from)
				to = from;
		} else {
			try {
				page = new Integer(key);
			} catch (NumberFormatException e) {
				page = new Integer(0);
			}
		}
		return this;
	}
}
