/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.swt.widgets;

import java.util.TimeZone;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

public class WTimeZone extends Composite {
	private Combo combo;
	private String[] timezones;

	public WTimeZone(Composite parent, int style) {
		super(parent, SWT.NONE);
		combo = new Combo(this, style);
		combo.setItems(getTimeZones());
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		setLayout(layout);
		combo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	@Override
	public void setToolTipText(String string) {
		super.setToolTipText(string);
		combo.setToolTipText(string);
	}

	public void addSelectionListener(SelectionListener m) {
		combo.addSelectionListener(m);
	}

	public void removeSelectionListener(SelectionListener m) {
		combo.removeSelectionListener(m);
	}

	private String[] getTimeZones() {
		timezones = TimeZone.getAvailableIDs();
		return timezones;
	}

	public void setSelection(TimeZone timeZone) {
		String timeZoneID = timeZone.getID();
		for (int i = 0; i < timezones.length; i++) {
			if (timeZoneID.equals(timezones[i])) {
				combo.select(i);
				break;
			}
		}
	}

	public TimeZone getTimeZone() {
		if (combo.getSelectionIndex() >= 0 && combo.getSelectionIndex() < timezones.length)
			return TimeZone.getTimeZone(timezones[combo.getSelectionIndex()]);
		return TimeZone.getDefault();
	}
}
