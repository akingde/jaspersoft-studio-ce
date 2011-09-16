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

public class SP3Boolean {
	private CCombo cmb3Bool;

	public SP3Boolean(Composite parent, AbstractSection section,
			String property, String tooltip) {
		createComponent(parent, section, property, tooltip);
	}

	public void createComponent(Composite parent,
			final AbstractSection section, final String property, String tooltip) {
		cmb3Bool = new CCombo(parent, SWT.BORDER | SWT.FLAT | SWT.READ_ONLY);
		cmb3Bool.setItems(new String[] { "NULL", "TRUE", "FALSE" });
		cmb3Bool.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				Boolean bval = null;
				switch (cmb3Bool.getSelectionIndex()) {
				case 1:
					bval = Boolean.TRUE;
					break;
				case 2:
					bval = Boolean.FALSE;
					break;
				}

				section.changeProperty(property, bval);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		cmb3Bool.setToolTipText(tooltip);
	}

	public void setData(Boolean b) {
		if (b == null)
			cmb3Bool.select(0);
		else if (b)
			cmb3Bool.select(1);
		else
			cmb3Bool.select(2);
	}
}
