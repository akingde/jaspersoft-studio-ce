/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.swt.widgets.table;

import java.util.Collection;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ListContentProvider implements IStructuredContentProvider {
	@Override
	public void dispose() {
		// nothing to do
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// nothing to do
	}

	public Object[] getElements(Object inputElement) {
		if (inputElement != null && inputElement instanceof List)
			return ((List<?>) inputElement).toArray();
		// If it's not a list check if it is a least a collection
		else if (inputElement != null && inputElement instanceof Collection)
			return ((Collection<?>) inputElement).toArray();
		return new Object[0];
	}

}
