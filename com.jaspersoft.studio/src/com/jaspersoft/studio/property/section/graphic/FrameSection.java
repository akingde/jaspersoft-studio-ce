/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
		addProvidedProperties(MFrame.PROPERTY_SHOW_OUT_OF_BOUND, Messages.MFrame_showOutOfBounds);
		addProvidedProperties(JRDesignFrame.PROPERTY_BORDER_SPLIT_TYPE, Messages.MFrame_splitType);
	}
}
