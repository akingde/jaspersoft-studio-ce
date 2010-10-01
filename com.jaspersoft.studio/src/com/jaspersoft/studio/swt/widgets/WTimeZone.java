package com.jaspersoft.studio.swt.widgets;

import java.util.TimeZone;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class WTimeZone extends Composite {
	private CCombo combo;
	private String[] timezones;

	public WTimeZone(Composite parent, int style) {
		super(parent, SWT.NONE);
		combo = new CCombo(this, style);
		combo.setItems(getTimeZones());
		setLayout(new GridLayout());
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
		return TimeZone.getTimeZone(timezones[combo.getSelectionIndex()]);
	}
}
