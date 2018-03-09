/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ListItem;

public class LovLabelProvider extends LabelProvider implements ITableLabelProvider {

	public String getColumnText(Object element, int columnIndex) {
		ListItem dto = (ListItem) element;
		if (dto != null) {
			if (columnIndex == 0)
				return dto.getLabel();
			if (columnIndex == 1 && dto.getValue() != null)
				return dto.getValue().toString();
		}
		return ""; //$NON-NLS-1$
	}

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}
}