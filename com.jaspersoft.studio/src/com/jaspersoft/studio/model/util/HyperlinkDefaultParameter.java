/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.util;

/**
 * Simple class containing information about a default hyperlink parameter.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class HyperlinkDefaultParameter {

		private String name;
		private String defaultValue;
	
		public HyperlinkDefaultParameter(String name, String defaultValue) {
			super();
			this.name = name;
			this.defaultValue = defaultValue;
		}
		
		public String getName() {
			return name;
		}

		public String getDefaultValue() {
			return defaultValue;
		}
	
}
