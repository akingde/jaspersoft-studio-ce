/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.section.theme;

import net.sf.jasperreports.chartthemes.simple.PlotSettings;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.chart.model.theme.MPlotSettings;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.text.FontSection;

public class CTPlotDisplayFontSection extends FontSection {
	@Override
	protected APropertyNode getModelFromEditPart(Object item) {
		APropertyNode md = super.getModelFromEditPart(item);
		if (md instanceof MPlotSettings)
			return (APropertyNode) md.getPropertyValue(PlotSettings.PROPERTY_displayFont);
		return md;
	}

	protected Composite createFontSection(Composite parent) {
		return getWidgetFactory().createSection(parent, "Display Font", true, 4);
	}
}
