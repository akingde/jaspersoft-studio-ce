/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
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
