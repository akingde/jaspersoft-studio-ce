/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;

public class SPBooleanToggle extends ASPropertyWidget<CheckBoxPropertyDescriptor> {
	private ToolItem cmb3Bool;

	private Composite parent;

	public SPBooleanToggle(Composite parent, AbstractSection section, CheckBoxPropertyDescriptor pDescriptor, Image image) {
		super(parent, section, pDescriptor);
		if (image != null)
			cmb3Bool.setImage(image);
	}

	@Override
	public Control getControl() {
		return parent;
	}

	@Override
	public Control getControlToBorder() {
		return parent;
	}

	protected PaintListener getPaintControlListener() {
		return DefaultWidgetsHighlighters.getWidgetForType(ToolItem.class);
	}

	public void createComponent(Composite parent) {
		this.parent = parent;
		cmb3Bool = new ToolItem((ToolBar) parent, SWT.CHECK);
		cmb3Bool.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				section.changeProperty(pDescriptor.getId(), new Boolean(cmb3Bool.getSelection()));
			}
		});
		cmb3Bool.setToolTipText(pDescriptor.getDescription());
	}

	public void setData(APropertyNode pnode, Object b) {
		createContextualMenu(pnode);
		cmb3Bool.setEnabled(pnode.isEditable());
		boolean v = false;
		if (b != null)
			v = (Boolean) b;
		cmb3Bool.setSelection(v);
	}
}
