/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.color.ColorLabelProvider;
import com.jaspersoft.studio.property.section.AbstractSection;

public class SPColor extends ASPropertyWidget {
	private ToolItem foreButton;
	private ColorLabelProvider colorLabelProvider = new ColorLabelProvider(null);

	public SPColor(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	public Control getControl() {
		return toolBar;
	}

	protected void createComponent(Composite parent) {
		toolBar = new ToolBar(parent, SWT.FLAT | SWT.WRAP | SWT.LEFT);
		toolBar.setBackground(parent.getBackground());

		foreButton = new ToolItem(toolBar, SWT.PUSH);
		foreButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ColorDialog cd = new ColorDialog(toolBar.getShell());
				cd.setText(Messages.ColorsSection_element_forecolor);
				cd.setRGB((RGB) section.getElement().getPropertyValue(pDescriptor.getId()));
				RGB newColor = cd.open();
				if (newColor != null)
					changeProperty(section, pDescriptor.getId(), newColor);
			}
		});
		foreButton.setToolTipText(pDescriptor.getDescription());
		toolBar.pack();
	}

	private APropertyNode parent;
	private ToolBar toolBar;

	public void setData(APropertyNode parent, RGB b) {
		this.parent = parent;
		foreButton.setImage(colorLabelProvider.getImage(b));
	}

	public void setData(APropertyNode pnode, Object b) {
		setData(null, (RGB) b);
	}

	private void changeProperty(final AbstractSection section, final Object property, RGB newColor) {
		if (parent == null)
			section.changeProperty(property, newColor);
		else
			section.changePropertyOn(property, newColor, parent);
	}
}
