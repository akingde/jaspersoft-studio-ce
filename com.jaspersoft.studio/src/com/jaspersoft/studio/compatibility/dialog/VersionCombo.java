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
package com.jaspersoft.studio.compatibility.dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;

public class VersionCombo {
	private String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
		for (int i = 0; i < versions.length; i++)
			if (versions[i][1].equals(version)) {
				combo.select(i);
				break;
			}
	}

	private static final String[][] versions = JRXmlWriterHelper.getVersions();
	private Combo combo;

	private String[] getItems() {
		String[] r = new String[versions.length];
		for (int i = 0; i < versions.length; i++)
			r[i] = versions[i][0];
		return r;
	}

	private int getVersionIndex() {
		for (int i = 0; i < versions.length; i++)
			if (versions[i][1].equals(version))
				return i;
		return 0;
	}

	public VersionCombo(Composite container) {
		combo = new Combo(container, SWT.SINGLE | SWT.READ_ONLY);
		combo.setItems(getItems());
		combo.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				int ind = combo.getSelectionIndex();
				if (ind >= 0 && ind < versions.length)
					version = versions[ind][1];
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		combo.select(getVersionIndex());
	}

	public Combo getControl() {
		return combo;
	}

	public static String getJrVersion(String v) {
		for (int i = 0; i < versions.length; i++)
			if (versions[i][0].equals(v))
				return versions[i][1];
		return null;
	}

	public static String getLabelVersion(String v) {
		for (int i = 0; i < versions.length; i++)
			if (versions[i][1].equals(v))
				return versions[i][0];
		return null;
	}

}
