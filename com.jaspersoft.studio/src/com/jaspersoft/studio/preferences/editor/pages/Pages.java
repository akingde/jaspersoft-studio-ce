/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
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
		if (key == null || key.isEmpty() || key.equals("all")) {
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
