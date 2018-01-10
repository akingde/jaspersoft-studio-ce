/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.theme;

import com.jaspersoft.studio.preferences.DesignerPreferencePage;
import com.jaspersoft.studio.preferences.RulersGridPreferencePage;

public class DarkTheme extends UITheme {
	public static final String NAME = "dark";

	public DarkTheme() {
		setName(NAME);
		addProperty(RulersGridPreferencePage.P_PAGE_GRID_COLOR, "255,255,255");
		addProperty(DesignerPreferencePage.P_CONTAINER_MARGIN_COLOR, "0,255,0");
		addProperty(DesignerPreferencePage.P_PAGE_BACKGROUND, "0,0,0");
		addProperty(DesignerPreferencePage.P_PAGE_MARGIN_COLOR, "255,0,0");
		addProperty(DesignerPreferencePage.P_ELEMENT_DESIGN_BORDER_COLOR, "255,255,0");
	}
}
