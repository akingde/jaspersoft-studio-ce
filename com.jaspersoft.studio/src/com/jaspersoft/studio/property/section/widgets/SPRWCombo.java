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
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.Misc;

public class SPRWCombo {
	private Combo theme;

	public SPRWCombo(Composite parent, AbstractSection section, String property, String tooltip, String[] items) {
		createComponent(parent, section, property, tooltip, items);
	}

	public void createComponent(Composite parent, final AbstractSection section, final String property, String tooltip,
			String[] items) {
		theme = new Combo(parent, SWT.BORDER | SWT.FLAT);
		theme.setItems(items);
		if (parent.getLayout() instanceof RowLayout) {
			RowData rd = new RowData();
			rd.width = 100;
			theme.setLayoutData(rd);
		} else if (parent.getLayout() instanceof GridLayout) {
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.widthHint = 200;
			theme.setLayoutData(gd);
		}
		theme.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				section.changeProperty(property, theme.getItem(theme.getSelectionIndex()));
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		theme.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				section.changeProperty(property, theme.getText());
			}
		});
		theme.setToolTipText(tooltip);
	}

	public void setData(String b) {
		String[] items = theme.getItems();
		int selection = 0;
		for (int i = 0; i < items.length; i++) {
			if (items[i].equals(b)) {
				selection = i;
				break;
			}
		}
		if (selection == 0) {
			b = Misc.nvl(b);
			int oldpos = b.length();
			theme.setItem(0, b);
			theme.setSelection(new Point(oldpos, oldpos));
		} else {
			theme.select(selection);

		}
	}
}
