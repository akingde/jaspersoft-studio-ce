/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
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
		if (parent.getLayout() instanceof GridLayout) {
			GridData gd = new GridData();
			gd.widthHint = 200;
			combo.setLayoutData(gd);
		}
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
