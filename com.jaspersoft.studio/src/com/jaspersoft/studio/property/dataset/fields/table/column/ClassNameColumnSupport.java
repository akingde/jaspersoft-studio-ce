/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.fields.table.column;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.dataset.fields.table.TColumn;
import com.jaspersoft.studio.property.descriptor.classname.ClassTypeComboCellEditor;

public class ClassNameColumnSupport extends PropertyColumnSupport {

	public ClassNameColumnSupport(ColumnViewer viewer, TColumn c) {
		super(viewer, c);
	}

	@Override
	protected CellEditor createCellEditor() {
		return new ClassTypeComboCellEditor((Composite) viewer.getControl());
	}

}
