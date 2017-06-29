/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.subreport.parameter;

import java.util.Collection;
import java.util.Map;

import net.sf.jasperreports.engine.JRPropertiesMap;

import org.eclipse.jface.viewers.LabelProvider;

import com.jaspersoft.studio.messages.Messages;

/*
 * @author Chicu Veaceslav
 */
public class SubreportPropertiesLabelProvider extends LabelProvider {

	public SubreportPropertiesLabelProvider() {
		super();
	}

	@Override
	public String getText(Object element) {
		if (element == null)
			return ""; //$NON-NLS-1$
		if (element instanceof JRPropertiesMap)
			return Messages.SubreportPropertiesLabelProvider_numbers_of_parameters
					+ ((JRPropertiesMap) element).getPropertyNames().length;
		if (element instanceof Collection)
			return Messages.SubreportPropertiesLabelProvider_numbers_of_parameters + ((Collection<?>) element).size();
		if (element instanceof Map)
			return Messages.SubreportPropertiesLabelProvider_numbers_of_parameters + ((Map<?, ?>) element).size();
		if (element.getClass().isArray())
			return Messages.SubreportPropertiesLabelProvider_numbers_of_parameters + ((Object[]) element).length;
		return element.toString();
	}

}
