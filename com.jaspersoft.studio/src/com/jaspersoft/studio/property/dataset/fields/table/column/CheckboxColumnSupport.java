/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.fields.table.column;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.property.dataset.fields.table.TColumn;

public class CheckboxColumnSupport extends PropertyColumnSupport {

	public CheckboxColumnSupport(ColumnViewer viewer, TColumn c) {
		super(viewer, c);
	}

	protected CellEditor createCellEditor() {
		return new CheckboxCellEditor((Composite) viewer.getControl());
	}

	@Override
	public Image getImage(Object element) {
		if (element != null && (Boolean) element)
			return JaspersoftStudioPlugin.getInstance().getImage("icons/CheckboxCellEditorHelper-checked.16x16.png");
		return null;
	}

}