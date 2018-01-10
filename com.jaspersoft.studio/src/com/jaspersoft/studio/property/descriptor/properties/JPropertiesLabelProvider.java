/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.properties;

import net.sf.jasperreports.engine.JRPropertiesMap;

import org.eclipse.jface.viewers.LabelProvider;

import com.jaspersoft.studio.messages.Messages;
/*
 * @author Chicu Veaceslav
 * 
 */
public class JPropertiesLabelProvider extends LabelProvider {

	public JPropertiesLabelProvider() {
		super();
	}

	@Override
	public String getText(Object element) {
		if (element == null)
			return ""; //$NON-NLS-1$
		if (element instanceof JRPropertiesMap)
			return "[" + Messages.common_properties + ": " + ((JRPropertiesMap) element).getPropertyNames().length + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return element.toString();
	}

}
