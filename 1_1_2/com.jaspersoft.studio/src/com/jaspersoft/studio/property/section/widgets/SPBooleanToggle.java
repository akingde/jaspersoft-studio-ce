/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
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
import com.jaspersoft.studio.property.section.AbstractSection;

public class SPBooleanToggle extends ASPropertyWidget {
	private ToolItem cmb3Bool;

	public SPBooleanToggle(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor, Image image) {
		super(parent, section, pDescriptor);
		if (image != null)
			cmb3Bool.setImage(image);
	}

	@Override
	public Control getControl() {
		return null;
	}

	public void createComponent(Composite parent) {
		cmb3Bool = new ToolItem((ToolBar) parent, SWT.CHECK);
		cmb3Bool.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				section.changeProperty(pDescriptor.getId(), new Boolean(cmb3Bool.getSelection()));
			}
		});
		cmb3Bool.setToolTipText(pDescriptor.getDescription());
	}

	public void setData(APropertyNode pnode, Object b) {
		boolean v = false;
		if (b != null)
			v = (Boolean) b;
		cmb3Bool.setSelection(v);
	}
}
