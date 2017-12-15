/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.section.AbstractSection;

public interface IPropertyDescriptorWidget {

	/**
	 * Return widget that will be used in the section
	 * 
	 * @param parent
	 * @param section
	 * @return
	 */
	public ASPropertyWidget<?> createWidget(Composite parent, AbstractSection section);
}
