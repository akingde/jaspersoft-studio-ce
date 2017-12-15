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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.classname.ClassTypeCellEditor;
import com.jaspersoft.studio.property.section.AbstractSection;

public class SPClassType<T extends IPropertyDescriptor> extends SPText<T> {

	protected Button btn;
	private List<Class<?>> classes;

	public void setClassesOfType(List<Class<?>> classes) {
		this.classes = classes;
	}

	public SPClassType(Composite parent, AbstractSection section, T pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	public void setReadOnly(boolean readonly) {
		super.setReadOnly(readonly);
		btn.setEnabled(!readonly);
	}
	
	@Override
	protected int getStyle() {
		return SWT.BORDER;
	}

	protected void createComponent(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.verticalSpacing = 0;
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		container.setLayout(layout);
		super.createComponent(container);
		GridData textData = new GridData(GridData.FILL_HORIZONTAL);
		textData.minimumWidth = 50;
		ftext.setLayoutData(textData);
		btn = section.getWidgetFactory().createButton(container, "...", SWT.PUSH);
		btn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String classname = ClassTypeCellEditor.getJavaClassDialog(btn.getShell(), classes);
				if (classname != null)
					handleTextChanged(section, pDescriptor.getId(), classname);
			}
		});

	}
	
	@Override
	public Control getControl() {
		return btn.getParent();
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
