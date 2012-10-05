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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptors.JSSEnumPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;

public class SPToolBarEnum extends ASPropertyWidget {
	private ToolItem[] toolItems;
	private ToolBar toolBar;

	public SPToolBarEnum(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor, Image[] images,
			boolean showText) {
		super(parent, section, pDescriptor);
		if (images != null)
			for (int i = 0; i < toolItems.length; i++) {
				toolItems[i].setImage(images[i]);
				if (!showText)
					toolItems[i].setText("");
			}
	}

	public SPToolBarEnum(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor, Image[] images) {
		super(parent, section, pDescriptor);
		if (images != null)
			for (int i = 0; i < toolItems.length; i++)
				toolItems[i].setImage(images[i]);
	}

	@Override
	public Control getControl() {
		return toolBar;
	}

	protected void createComponent(Composite parent) {
		toolBar = new ToolBar(parent, SWT.FLAT | SWT.WRAP | SWT.RIGHT);

		final JSSEnumPropertyDescriptor pd = (JSSEnumPropertyDescriptor) pDescriptor;
		toolItems = new ToolItem[pd.getJrEnums().length];
		for (int i = 0; i < pd.getJrEnums().length; i++) {
			final int index = i;
			toolItems[i] = new ToolItem(toolBar, SWT.CHECK);
			toolItems[i].setText(pd.getJrEnums()[i].getName());
			toolItems[i].setToolTipText(pd.getJrEnums()[i].getName());
			toolItems[i].addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					int val = index;
					if (pd.getType() != NullEnum.NOTNULL)
						val++;
					propertyChange(section, pd.getId(), toolItems[index].getSelection() ? val : null);
				}
			});
		}
	}

	public void propertyChange(AbstractSection section, Object property, Integer value) {
		section.changeProperty(property, value);
	}

	public void setData(APropertyNode pnode, Object b) {
		int index = 0;
		if (b != null)
			index = ((Number) b).intValue();
		final JSSEnumPropertyDescriptor pd = (JSSEnumPropertyDescriptor) pDescriptor;
		if (pd.getType() != NullEnum.NOTNULL)
			index--;
		for (int i = 0; i < toolItems.length; i++)
			toolItems[i].setSelection(i == index);

	}
}
