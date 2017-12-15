/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.components;

import net.sf.jasperreports.engine.base.JRBaseLine;
import net.sf.jasperreports.engine.base.JRBaseStyle;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class LineSection extends AbstractSection {
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(2, false));

		createWidget4Property(parent, JRBaseStyle.PROPERTY_FILL);
		createWidget4Property(parent, JRBaseLine.PROPERTY_DIRECTION);
	}
	
	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(JRBaseStyle.PROPERTY_FILL, Messages.common_fill);
		addProvidedProperties(JRBaseLine.PROPERTY_DIRECTION, Messages.MLine_direction);
	}
}
