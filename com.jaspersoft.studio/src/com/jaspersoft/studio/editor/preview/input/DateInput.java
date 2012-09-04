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
import java.util.Date;
import java.util.Map;

import org.eclipse.nebula.widgets.cdatetime.CDT;
import org.eclipse.nebula.widgets.cdatetime.CDateTime;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.utils.UIUtils;

public class DateInput extends ADataInput {

	public DateInput() {
		this(false);
	}

	public DateInput(boolean isNumeric) {
		this.isNumeric = isNumeric;
	}

	public boolean isForType(Class<?> valueClass) {
		return Date.class.isAssignableFrom(valueClass);
	}

	@Override
	public void createInput(Composite parent, final IParameter param, final Map<String, Object> params) {
		super.createInput(parent, param, params);
		Class<?> valueClass = param.getValueClass();
		if (Date.class.isAssignableFrom(valueClass)) {
			if (param.getValueClass().equals(java.sql.Date.class)) {
				createDate(parent, param, params);
			} else if (param.getValueClass().equals(java.sql.Time.class)) {
				createTime(parent, param, params);
			} else if (param.getValueClass().equals(java.sql.Timestamp.class)
					|| param.getValueClass().equals(java.util.Date.class)) {
				createTimestamp(parent, param, params);
			}
		}
	}

	protected void createTimestamp(Composite parent, final IParameter param, final Map<String, Object> params) {
		date = new CDateTime(parent, CDT.BORDER | CDT.DATE_SHORT | CDT.TIME_MEDIUM | CDT.DROP_DOWN);

		GridData gd = new GridData();
		gd.horizontalIndent = 8;
		gd.widthHint = 25 * UIUtils.getCharWidth(date);
		date.setLayoutData(gd);

		setMandatory(param, date);
		SelectionAdapter listener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Date sdate = date.getSelection();
				Timestamp d = sdate != null ? new java.sql.Timestamp(sdate.getTime()) : null;
				updateModel(isNumeric ? d.getTime() : d);
			}
		};
		date.addSelectionListener(listener);
		updateInput();
		listener.widgetSelected(null);
	}

	protected void createTime(Composite parent, final IParameter param, final Map<String, Object> params) {
		date = new CDateTime(parent, CDT.BORDER | CDT.TIME_MEDIUM | CDT.DROP_DOWN);
		GridData gd = new GridData();
		gd.horizontalIndent = 8;
		gd.widthHint = 25 * UIUtils.getCharWidth(date);
		date.setLayoutData(gd);

		setMandatory(param, date);

		SelectionAdapter listener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Date sdate = date.getSelection();
				Time d = sdate != null ? new java.sql.Time(sdate.getTime()) : null;
				updateModel(isNumeric ? d.getTime() : d);
			}
		};
		date.addSelectionListener(listener);
		updateInput();
		listener.widgetSelected(null);
	}

	protected void createDate(Composite parent, final IParameter param, final Map<String, Object> params) {
		date = new CDateTime(parent, CDT.BORDER | CDT.DATE_SHORT | CDT.DROP_DOWN);
		GridData gd = new GridData();
		gd.horizontalIndent = 8;
		gd.widthHint = 25 * UIUtils.getCharWidth(date);
		date.setLayoutData(gd);

		setMandatory(param, date);

		updateInput();
		SelectionAdapter listener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Date sdate = date.getSelection();
				Date d = sdate != null ? new java.sql.Date(sdate.getTime()) : null;
				updateModel(isNumeric ? d.getTime() : d);
			}
		};
		date.addSelectionListener(listener);
		listener.widgetSelected(null);
	}

	public void updateInput() {
		Object d = params.get(param.getName());
		if (d != null) {
			if (d instanceof Date) {
				date.setSelection((Date) d);
			} else if (d instanceof Long) {
				date.setSelection(new Date((Long) d));
				isNumeric = true;
			}
		}
	}

	private boolean isNumeric = false;
	private CDateTime date;

}
