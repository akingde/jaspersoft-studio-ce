/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.wizard.pages;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.data.DataAdapterFactory;

public class LLabelProvider extends LabelProvider {
	@Override
	public Image getImage(Object element) {
		if (element instanceof DataAdapterFactory)
			return ((DataAdapterFactory) element).getIcon(16);
		return super.getImage(element);
	}

	@Override
	public String getText(Object element) {
		if (element instanceof DataAdapterFactory)
			return ((DataAdapterFactory) element).getLabel();
		return super.getText(element);
	}
}
