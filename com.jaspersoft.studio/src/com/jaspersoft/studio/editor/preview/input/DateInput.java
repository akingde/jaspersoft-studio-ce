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
package com.jaspersoft.studio.editor.preview.input;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;

public class DateInput implements IDataInput {

	public DateInput(boolean isNumeric) {
		this.isNumeric = isNumeric;
	}

	public boolean isForType(Class<?> valueClass) {
		if (Date.class.isAssignableFrom(valueClass))
			return true;
		return false;
	}

	public boolean createInput(Composite parent, final IParameter param, final Map<String, Object> params) {
		Class<?> valueClass = param.getValueClass();
		if (Date.class.isAssignableFrom(valueClass)) {
			if (param.getValueClass().equals(java.sql.Date.class)) {
				createDate(parent, param, params);
			} else if (param.getValueClass().equals(java.sql.Time.class)) {
				createDateTime(parent, param, params);
			} else if (param.getValueClass().equals(java.sql.Timestamp.class)
					|| param.getValueClass().equals(java.util.Date.class)) {
				createTimestamp(parent, param, params);
			}
			return true;
		}
		return false;

	}

	protected void createTimestamp(Composite parent, final IParameter param, final Map<String, Object> params) {
		Composite c = new Composite(parent, SWT.NONE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalIndent = 8;
		c.setLayoutData(gd);
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		c.setLayout(layout);
		c.setBackground(c.getDisplay().getSystemColor(SWT.COLOR_WHITE));

		ADataInput.setMandatory(param, c);

		final DateTime date = new DateTime(c, SWT.DATE | SWT.LONG | SWT.BORDER);
		final DateTime time = new DateTime(c, SWT.TIME | SWT.LONG | SWT.BORDER);
		SelectionAdapter listener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Timestamp d = java.sql.Timestamp
						.valueOf(String
								.format(
										"%04d-%02d-%02d %02d:%02d:%02d", date.getYear(), date.getMonth(), date.getDay(), time.getHours(), time.getMinutes(), time.getSeconds())); //$NON-NLS-1$ 
				if (isNumeric)
					params.put(param.getName(), d.getTime());
				else
					params.put(param.getName(), d);
			}
		};
		date.addSelectionListener(listener);
		time.addSelectionListener(listener);
		Object d = params.get(param.getName());
		if (d != null) {
			GregorianCalendar cal = new GregorianCalendar();
			if (d instanceof Date) {
				cal.setTimeInMillis(((Date) d).getTime());
			} else if (d instanceof Long) {
				cal.setTimeInMillis((Long) d);
				isNumeric = true;
			}

			date.setYear(cal.get(Calendar.YEAR));
			date.setMonth(cal.get(Calendar.MONTH) + 1);
			date.setDay(cal.get(Calendar.DATE));

			time.setHours(cal.get(Calendar.HOUR));
			time.setMinutes(cal.get(Calendar.MINUTE));
			time.setSeconds(cal.get(Calendar.SECOND));
		}
		listener.widgetSelected(null);
	}

	protected void createDateTime(Composite parent, final IParameter param, final Map<String, Object> params) {
		final DateTime time = new DateTime(parent, SWT.TIME | SWT.LONG | SWT.BORDER);
		GridData gd = new GridData();
		gd.horizontalIndent = 8;
		time.setLayoutData(gd);
		ADataInput.setMandatory(param, time);
		SelectionAdapter listener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Time d = java.sql.Time.valueOf(String.format(
						"%02d:%02d:%02d", time.getHours(), time.getMinutes(), time.getSeconds())); //$NON-NLS-1$ 
				if (isNumeric)
					params.put(param.getName(), d.getTime());
				else
					params.put(param.getName(), d);
			}
		};
		time.addSelectionListener(listener);
		Object d = params.get(param.getName());
		if (d != null) {
			GregorianCalendar cal = new GregorianCalendar();
			if (d instanceof Date) {
				cal.setTimeInMillis(((Date) d).getTime());
			} else if (d instanceof Long) {
				cal.setTimeInMillis((Long) d);
				isNumeric = true;
			}

			time.setHours(cal.get(Calendar.HOUR));
			time.setMinutes(cal.get(Calendar.MINUTE));
			time.setSeconds(cal.get(Calendar.SECOND));
		}
		listener.widgetSelected(null);
	}

	protected void createDate(Composite parent, final IParameter param, final Map<String, Object> params) {
		final DateTime date = new DateTime(parent, SWT.DATE | SWT.LONG | SWT.BORDER);
		GridData gd = new GridData();
		gd.horizontalIndent = 8;
		date.setLayoutData(gd);
		ADataInput.setMandatory(param, date);

		Object d = params.get(param.getName());
		if (d != null) {
			GregorianCalendar cal = new GregorianCalendar();
			if (d instanceof Date) {
				cal.setTimeInMillis(((Date) d).getTime());
			} else if (d instanceof Long) {
				cal.setTimeInMillis((Long) d);
				isNumeric = true;
			}

			date.setYear(cal.get(Calendar.YEAR));
			date.setMonth(cal.get(Calendar.MONTH) + 1);
			date.setDay(cal.get(Calendar.DATE));
		}
		SelectionAdapter listener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Date d = java.sql.Date.valueOf(String.format("%04d-%02d-%02d", date.getYear(), date.getMonth(), date.getDay())); //$NON-NLS-1$ 
				if (isNumeric)
					params.put(param.getName(), d.getTime());
				else
					params.put(param.getName(), d);
			}
		};
		date.addSelectionListener(listener);
		listener.widgetSelected(null);
	}

	private boolean isNumeric = false;

	public boolean isLabeled() {
		return false;
	}
}
