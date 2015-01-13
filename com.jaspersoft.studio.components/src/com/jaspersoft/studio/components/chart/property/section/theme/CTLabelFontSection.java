/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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