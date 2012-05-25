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
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.combo.RComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.Misc;

public class SPRCombo extends ASPropertyWidget {
	private CCombo combo;

	public SPRCombo(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	public Control getControl() {
		return combo;
	}

	protected void createComponent(Composite parent) {
		combo = section.getWidgetFactory().createCCombo(parent, SWT.FLAT | SWT.READ_ONLY);

		combo.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				changeProperty(section, combo.getItem(combo.getSelectionIndex()));
			}
		});
		combo.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				changeProperty(section, combo.getText());
			}
		});
		combo.setToolTipText(pDescriptor.getDescription());
	}

	protected void changeProperty(AbstractSection section, Object value) {
		section.changeProperty(pDescriptor.getId(), value);
	}

	public void setData(APropertyNode pnode, Object b) {
		final RComboBoxPropertyDescriptor pd = (RComboBoxPropertyDescriptor) pDescriptor;
		combo.setItems(pd.getItems());
		String str = (String) b;
		String[] items = combo.getItems();
		int selection = 0;
		for (int i = 0; i < items.length; i++) {
			if (items[i].equals(str)) {
				selection = i;
				break;
			}
		}
		if (selection == 0 && pd.getItems().length > 0) {
			str = Misc.nvl(str);
			int oldpos = str.length();
			combo.setItem(0, str);
			combo.setSelection(new Point(oldpos, oldpos));
		} else {
			combo.select(selection);

		}
	}
}
