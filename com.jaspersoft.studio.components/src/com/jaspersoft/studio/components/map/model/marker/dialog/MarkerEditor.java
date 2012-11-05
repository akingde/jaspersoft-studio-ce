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
package com.jaspersoft.studio.components.map.model.marker.dialog;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.components.map.model.marker.MarkersDTO;

public class MarkerEditor extends Wizard {
	private MarkersDTO value;
	private MarkerPage page0;

	public MarkersDTO getValue() {
		if (page0 != null)
			return page0.getValue();
		return value;
	}

	public void setValue(MarkersDTO value) {
		if (page0 != null)
			page0.setValue(value);
		this.value = value;
	}

	public MarkerEditor() {
		super();
		setWindowTitle("Map Markers");
		setNeedsProgressMonitor(false);
	}

	@Override
	public void addPages() {
		page0 = new MarkerPage("mapmarkers"); //$NON-NLS-1$
		page0.setValue(value);
		addPage(page0);
	}

	@Override
	public boolean performFinish() {
		return true;
	}

}
