/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.theme;

import net.sf.jasperreports.chartthemes.simple.ChartThemeSettings;
import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MRoot;

public class MChartThemeSettings extends ANode {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MChartThemeSettings(MRoot root, ChartThemeSettings cts) {
		super(root, -1);
		setValue(cts);
	}

	@Override
	public ImageDescriptor getImagePath() {
		return null;
	}

	@Override
	public ChartThemeSettings getValue() {
		return (ChartThemeSettings) super.getValue();
	}

	@Override
	public String getDisplayText() {
		return "Chart Theme";
	}

}
