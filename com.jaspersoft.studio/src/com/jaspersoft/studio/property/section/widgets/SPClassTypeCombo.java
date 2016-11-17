/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.classname.ClassTypeCellEditor;
import com.jaspersoft.studio.property.section.AbstractSection;

public class SPClassTypeCombo<T extends IPropertyDescriptor> extends SPRWCombo<T> {

	protected Button btn;
	private List<Class<?>> classes;

	public void setClassesOfType(List<Class<?>> classes) {
		this.classes = classes;
	}

	public SPClassTypeCombo(Composite parent, AbstractSection section, T pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	public void setReadOnly(boolean readonly) {
		super.setReadOnly(readonly);
		btn.setEnabled(!readonly);
	}

	protected void createComponent(Composite parent) {
		super.createComponent(parent);
		setWidth(parent, 15);
		btn = section.getWidgetFactory().createButton(parent, "...", SWT.PUSH);
		btn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String classname = ClassTypeCellEditor.getJavaClassDialog(btn.getShell(), classes);
				if (classname != null)
					handleTextChanged(section, pDescriptor.getId(), classname);
			}
		});

	}

	protected void setWidth(Composite parent, int chars) {
		int w = getCharWidth(combo) * chars;
		if (w > 100)
			w = 100;
		if (parent.getLayout() instanceof RowLayout) {
			RowData rd = new RowData();
			rd.width = w;
			combo.setLayoutData(rd);
		} else if (parent.getLayout() instanceof GridLayout) {
			GridData rd = new GridData(GridData.FILL_HORIZONTAL);
			rd.minimumWidth = w;
			rd.widthHint = w;
			combo.setLayoutData(rd);
		}
	}

	protected void handleTextChanged(final AbstractSection section, final Object property, String text) {
		if (text != null && text.trim().isEmpty())
			text = null;
		section.changeProperty(property, text);
	}

	@Override
	public void setData(APropertyNode pnode, Object b) {
		btn.setEnabled(pnode.isEditable());
		super.setData(pnode, b);
	}
}
