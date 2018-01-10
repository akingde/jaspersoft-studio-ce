/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.editor.sorttable.model;

import org.eclipse.jface.viewers.IStructuredContentProvider;

public interface ITableContentProvider extends IStructuredContentProvider {

	public Object getColumnValue(Object element, int columnIndex);

}
