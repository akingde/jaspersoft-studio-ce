/*******************************************************************************
 * Copyright (C) 2005 - 2015 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
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
