/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
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
package com.jaspersoft.studio.editor.preview.input;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import net.sf.jasperreports.engine.design.JRDesignParameter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;

public class DateInput implements IDataInput {
	public boolean isForType(Class<?> valueClass) {
		if (Date.class.isAssignableFrom(valueClass))
			return true;
		return false;
	}

	public boolean createInput(Composite parent, final JRDesignParameter param, Class<?> valueClass,
			final Map<String, Object> params) {
		if (Date.class.isAssignableFrom(valueClass)) {
			if (param.getValueClass().equals(java.sql.Date.class)) {
				final DateTime date = new DateTime(parent, SWT.DATE | SWT.LONG | SWT.BORDER);
				date.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						params.put(param.getName(), java.sql.Date.valueOf(String.format("%04d", date.getYear()) + "-" //$NON-NLS-1$ //$NON-NLS-2$
								+ String.format("%02d", date.getMonth()) + "-" + String.format("%02d", date.getDay()))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					}
				});
				if (params.get(param.getName()) != null) {
					Date d = (Date) params.get(param.getName());
					GregorianCalendar cal = new GregorianCalendar();
					cal.setTimeInMillis(d.getTime());

					date.setYear(cal.get(Calendar.YEAR));
					date.setMonth(cal.get(Calendar.MONTH) + 1);
					date.setDay(cal.get(Calendar.DATE));
				}
			} else if (param.getValueClass().equals(java.sql.Time.class)) {
				final DateTime time = new DateTime(parent, SWT.TIME | SWT.LONG | SWT.BORDER);
				time.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						params.put(param.getName(), java.sql.Time.valueOf(String.format("%02d", //$NON-NLS-1$
								String.format("%02d", time.getHours()) + ":" + String.format("%02d", time.getMinutes()) + ":" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
										+ String.format("%02d", time.getSeconds())))); //$NON-NLS-1$

					}
				});
				if (params.get(param.getName()) != null) {
					Date d = (Date) params.get(param.getName());
					GregorianCalendar cal = new GregorianCalendar();
					cal.setTimeInMillis(d.getTime());

					time.setHours(cal.get(Calendar.HOUR));
					time.setMinutes(cal.get(Calendar.MINUTE));
					time.setSeconds(cal.get(Calendar.SECOND));
				}
			} else if (param.getValueClass().equals(java.sql.Timestamp.class)
					|| param.getValueClass().equals(java.util.Date.class)) {
				Composite c = new Composite(parent, SWT.NONE);
				c.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
				GridLayout layout = new GridLayout(2, false);
				layout.marginWidth = 0;
				c.setLayout(layout);
				c.setBackground(c.getDisplay().getSystemColor(SWT.COLOR_WHITE));

				final DateTime date = new DateTime(c, SWT.DATE | SWT.LONG | SWT.BORDER);
				final DateTime time = new DateTime(c, SWT.TIME | SWT.LONG | SWT.BORDER);
				SelectionAdapter listener = new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						String timestamp = String.format("%04d", date.getYear()) + "-" + String.format("%02d", date.getMonth()) + "-" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
								+ String.format("%02d", date.getDay()) + " " + String.format("%02d", time.getHours()) + ":" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
								+ String.format("%02d", time.getMinutes()) + ":" + String.format("%02d", time.getSeconds()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						params.put(param.getName(), java.sql.Timestamp.valueOf(timestamp));
					}
				};
				date.addSelectionListener(listener);
				time.addSelectionListener(listener);
				Object val = params.get(param.getName());
				if (val != null && val instanceof Date) {
					Date d = (Date) val;
					GregorianCalendar cal = new GregorianCalendar();
					cal.setTimeInMillis(d.getTime());

					date.setYear(cal.get(Calendar.YEAR));
					date.setMonth(cal.get(Calendar.MONTH) + 1);
					date.setDay(cal.get(Calendar.DATE));

					time.setHours(cal.get(Calendar.HOUR));
					time.setMinutes(cal.get(Calendar.MINUTE));
					time.setSeconds(cal.get(Calendar.SECOND));
				}
			}
			return true;
		}
		return false;

	}
}
