/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.parameter;

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.jface.viewers.LabelProvider;

import com.jaspersoft.studio.messages.Messages;
/*
 * @author Chicu Veaceslav
 * 
 */
public class GenericParameterLabelProvider extends LabelProvider {

	public GenericParameterLabelProvider() {
		super();
	}

	@Override
	public String getText(Object element) {
		if (element == null)
			return ""; //$NON-NLS-1$
		if (element.getClass().isArray()){
			int lenght = ((Object[]) element).length;
			return MessageFormat.format(Messages.GenericParameterLabelProvider_parametersCountLabel, new Object[]{lenght});
		}
		if (element instanceof List<?>){
			int lenght = ((List<?>)element).size();
			return MessageFormat.format(Messages.GenericParameterLabelProvider_parametersCountLabel, new Object[]{lenght});
		}
		return element.toString();
	}

}
