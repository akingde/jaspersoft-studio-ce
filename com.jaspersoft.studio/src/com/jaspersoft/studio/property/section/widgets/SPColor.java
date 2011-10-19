/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.color.ColorLabelProvider;
import com.jaspersoft.studio.property.section.AbstractSection;

public class SPColor {
	private ToolItem foreButton;
	private ColorLabelProvider colorLabelProvider = new ColorLabelProvider(null);

	public SPColor(Composite parent, AbstractSection section, String property, String tooltip) {
		createComponent(parent, section, property, tooltip);
	}

	public void createComponent(final Composite parent, final AbstractSection section, final String property,
			String tooltip) {
		ToolBar toolBar = new ToolBar(parent, SWT.FLAT | SWT.WRAP | SWT.LEFT);
		toolBar.setBackground(parent.getBackground());

		foreButton = new ToolItem(toolBar, SWT.PUSH);
		foreButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ColorDialog cd = new ColorDialog(parent.getShell());
				cd.setText(Messages.ColorsSection_element_forecolor);
				cd.setRGB((RGB) section.getElement().getPropertyValue(property));
				RGB newColor = cd.open();
				if (newColor != null)
					changeProperty(section, property, newColor);
			}
		});
		foreButton.setToolTipText(tooltip);
		// foreButton.setWidth(toolBar.get)
		toolBar.pack();
	}

	private APropertyNode parent;

	public void setData(APropertyNode parent, RGB b) {
		this.parent = parent;
		// if (b != null)
		foreButton.setImage(colorLabelProvider.getImage(b));
		// else
		// foreButton.setImage(null);
	}

	public void setData(RGB b) {
		setData(null, b);
	}

	private void changeProperty(final AbstractSection section, final String property, RGB newColor) {
		if (parent == null)
			section.changeProperty(property, newColor);
		else
			section.changePropertyOn(property, newColor, parent);
	}
}
