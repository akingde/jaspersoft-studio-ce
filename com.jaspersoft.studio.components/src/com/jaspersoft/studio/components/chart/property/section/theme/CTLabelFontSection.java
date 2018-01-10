/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.section.theme;

import net.sf.jasperreports.chartthemes.simple.AxisSettings;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.chart.model.theme.MAxisSettings;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.text.FontSection;

public class CTLabelFontSection extends FontSection {
	@Override
	protected APropertyNode getModelFromEditPart(Object item) {
		APropertyNode md = super.getModelFromEditPart(item);
		if (md instanceof MAxisSettings)
			return (APropertyNode) md.getPropertyValue(AxisSettings.PROPERTY_labelFont);
		return md;
	}

	protected Composite createFontSection(Composite parent) {
		return getWidgetFactory().createSection(parent, "Label Font", true, 4);
	}
}
