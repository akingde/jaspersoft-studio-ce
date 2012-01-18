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

import net.sf.jasperreports.charts.type.TimePeriodEnum;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.EnumHelper;

public class SPRComboTimePeriod {
	private CCombo theme;

	public SPRComboTimePeriod(Composite parent, AbstractSection section, String property, String tooltip) {
		createComponent(parent, section, property, tooltip);
	}

	public void createComponent(Composite parent, final AbstractSection section, final String property, String tooltip) {
		theme = new CCombo(parent, SWT.BORDER | SWT.FLAT | SWT.READ_ONLY);
		theme.setItems(EnumHelper.getEnumNames(TimePeriodEnum.values(), NullEnum.NULL));
		theme.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				if (theme.getSelectionIndex() == 0)
					section.changeProperty(property, null);
				else {
					TimePeriodEnum tpe = TimePeriodEnum.values()[theme.getSelectionIndex() - 1];
					section.changeProperty(property, tpe.getTimePeriod());
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		theme.setToolTipText(tooltip);
	}

	public void setData(Class<?> b) {
		if (b == null)
			theme.select(0);
		else {
			TimePeriodEnum tpe = TimePeriodEnum.getByValue(b);
			if (tpe == null) {
				theme.select(0);
			} else {
				TimePeriodEnum[] tpevalues = TimePeriodEnum.values();
				for (int i = 0; i < tpevalues.length; i++) {
					if (tpe.equals(tpevalues[i])) {
						theme.select(i + 1);
						break;
					}
				}
			}
		}
	}
}
