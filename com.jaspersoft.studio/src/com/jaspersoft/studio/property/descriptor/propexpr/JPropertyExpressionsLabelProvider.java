/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.propexpr;

import org.eclipse.jface.viewers.LabelProvider;

import com.jaspersoft.studio.messages.Messages;

/*
 * @author Chicu Veaceslav
 */
public class JPropertyExpressionsLabelProvider extends LabelProvider {

	public JPropertyExpressionsLabelProvider() {
		super();
	}

	@Override
	public String getText(Object element) {
		if (element == null)
			return ""; //$NON-NLS-1$
		if (element instanceof PropertyExpressionsDTO) {
			PropertyExpressionsDTO dto = (PropertyExpressionsDTO) element;
			return "[" + Messages.common_properties + ": " + dto.getProperties().size() + "]";
		} 
		return element.toString();
	}

}
