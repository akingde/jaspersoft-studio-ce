/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.image;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractRealValueSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;

import net.sf.jasperreports.engine.base.JRBaseStyle;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class ImageAlignSection extends AbstractRealValueSection {
	
	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(4, false));

		CLabel label = getWidgetFactory().createCLabel(parent, Messages.ImageAlignSection_imageAlignLabel, SWT.RIGHT);

		ASPropertyWidget<?> horizontalAlignWidget = createWidget4Property(parent, JRBaseStyle.PROPERTY_HORIZONTAL_IMAGE_ALIGNMENT, false);
		horizontalAlignWidget.setLabel(label);
		
		getWidgetFactory().createCLabel(parent, "", SWT.SEPARATOR | SWT.VERTICAL); //$NON-NLS-1$

		createWidget4Property(parent, JRBaseStyle.PROPERTY_VERTICAL_IMAGE_ALIGNMENT, false);
	}
	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(JRBaseStyle.PROPERTY_HORIZONTAL_IMAGE_ALIGNMENT, Messages.common_horizontal_alignment);
		addProvidedProperties(JRBaseStyle.PROPERTY_VERTICAL_IMAGE_ALIGNMENT, Messages.common_vertical_alignment);
	}
	

}
