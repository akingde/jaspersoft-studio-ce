/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.combo;

import org.eclipse.jface.viewers.LabelProvider;
/*
 * @author Chicu Veaceslav
 * 
 */
public class RComboBoxLabelProvider extends LabelProvider {

	public RComboBoxLabelProvider(String[] labels) {
		super();
	}

	@Override
	public String getText(Object element) {
		if (element == null)
			return ""; //$NON-NLS-1$
		if (element instanceof String) {
			if (element == null || element.equals("")) //$NON-NLS-1$
				return ""; //$NON-NLS-1$
			return (String) element;
		}
		return element.toString();
	}

}
