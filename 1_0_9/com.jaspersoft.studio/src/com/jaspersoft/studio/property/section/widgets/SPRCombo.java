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
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.section.AbstractSection;

public class SPRCombo {
	protected CCombo rcombo;

	public SPRCombo(Composite parent, AbstractSection section, String property, String tooltip, String[] items) {
		createComponent(parent, section, property, tooltip, items);
	}

	public void createComponent(Composite parent, final AbstractSection section, final String property, String tooltip,
			String[] items) {
		rcombo = new CCombo(parent, SWT.BORDER | SWT.FLAT | SWT.READ_ONLY);
		rcombo.setItems(items);
		rcombo.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				changeProperty(section, property);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		rcombo.setToolTipText(tooltip);
	}

	protected void changeProperty(AbstractSection section, final String property) {
		section.changeProperty(property, rcombo.getSelectionIndex());
	}

	public void setData(Integer b) {
		rcombo.select(b);

	}

	public void setData(Integer b, String[] items) {
		rcombo.setItems(items);
		rcombo.select(b);

	}
}
