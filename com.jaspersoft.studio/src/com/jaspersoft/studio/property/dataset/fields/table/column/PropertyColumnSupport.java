/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.fields.table.column;

import org.apache.commons.beanutils.PropertyUtils;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.dataset.fields.table.TColumn;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;

public class PropertyColumnSupport extends EditingSupport {
	protected CellEditor editor;
	protected ColumnViewer viewer;
	protected TColumn c;

	public PropertyColumnSupport(ColumnViewer viewer, TColumn c) {
		super(viewer);
		this.c = c;
		this.viewer = viewer;
		editor = createCellEditor();
	}

	protected CellEditor createCellEditor() {
		return new TextCellEditor((Composite) viewer.getControl()) {
			@Override
			protected void doSetValue(Object value) {
				if (value == null)
					value = "";
				if (!(value instanceof String))
					value = value.toString();
				super.doSetValue(value);
			}
		};
	}

	@Override
	protected void setValue(Object element, Object value) {
		if (element != null)
			try {
				if (value instanceof String && ((String) value).isEmpty()
						&& !c.getPropertyType().equals(String.class.getName()))
					value = null;
				PropertyUtils.setProperty(element, c.getPropertyName(), value);
				viewer.update(element, null);
			} catch (Exception e) {
				UIUtils.showError(e);
			}
	}

	@Override
	protected Object getValue(Object element) {
		try {
			if (element != null && !element.getClass().isArray())
				return PropertyUtils.getProperty(element, c.getPropertyName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getText(Object element) {
		return Misc.nvl(getValue(element), "");
	}

	public String getToolTipText(Object element) {
		String tt = getText(element);
		if (!Misc.isNullOrEmpty(c.getDescription()))
			tt += "\n" + c.getDescription();
		return tt;
	}

	public Image getImage(Object element) {
		return null;
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		return editor;
	}

	@Override
	protected boolean canEdit(Object element) {
		return !c.isReadOnly();
	}
}