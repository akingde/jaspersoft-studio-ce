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
package com.jaspersoft.studio.property.descriptors;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPPixel;

/**
 * Work the same way as the PixelPropertyDescriptor but dosen't allow
 * empty\null value
 * 
 * 
 * @author Orlandin Marco
 * 
 */
public class NotNullPixelPropertyDescriptor extends PixelPropertyDescriptor {

	/**
	 * Instantiates a new integer property descriptor.
	 * 
	 * @param id
	 *          the id
	 * @param displayName
	 *          the display name
	 */
	public NotNullPixelPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	@Override
	public ASPropertyWidget<PixelPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
		SPPixel spNumber = new SPPixel(parent, section, this, SPPixel.PERSISTENT);
		return spNumber;
	}
}
