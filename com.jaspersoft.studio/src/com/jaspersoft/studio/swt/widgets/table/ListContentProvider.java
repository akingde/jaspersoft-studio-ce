package com.jaspersoft.studio.swt.widgets.table;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ListContentProvider implements IStructuredContentProvider {

	public void dispose() {

	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}

	public Object[] getElements(Object inputElement) {
		if (inputElement != null && inputElement instanceof List)
			return ((List<?>) inputElement).toArray();
		return new Object[0];
	}

}
