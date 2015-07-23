/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.section.graphic;

import net.sf.jasperreports.engine.design.JRDesignFrame;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.frame.MFrame;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractRealValueSection;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class FrameSection extends AbstractRealValueSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		parent.setLayout(new GridLayout(1, false));
		//create the widget for the button to show or not the elements outside the frame
		createWidget4Property(parent, MFrame.PROPERTY_SHOW_OUT_OF_BOUND, false);
		Composite splitContainer = new Composite(parent, SWT.NONE);
		GridLayout splitLayout = new GridLayout(2, false);
		splitLayout.marginWidth = 0;
		splitLayout.marginHeight = 0;
		splitContainer.setLayout(splitLayout);
		createWidget4Property(splitContainer, JRDesignFrame.PROPERTY_BORDER_SPLIT_TYPE);
	}

	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(MFrame.PROPERTY_SHOW_OUT_OF_BOUND, Messages.LocationSection_xCoordinateLabel);
		addProvidedProperties(JRDesignFrame.PROPERTY_BORDER_SPLIT_TYPE, Messages.MFrame_showOutOfBounds);
	}
}
