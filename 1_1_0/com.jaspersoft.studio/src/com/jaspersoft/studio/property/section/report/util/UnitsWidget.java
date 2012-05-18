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
package com.jaspersoft.studio.property.section.report.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class UnitsWidget {

	private CCombo unitc;

	public void createComponent(Composite parent, String label, String toolTip, int span) {
		Label lbl = new Label(parent, SWT.NONE);
		lbl.setText(label);
		lbl.setBackground(parent.getBackground());

		unitc = new CCombo(parent, SWT.BORDER | SWT.SINGLE | SWT.READ_ONLY);
		unitc.setItems(Unit.getUnits());
		unitc.setToolTipText(toolTip);
		unitc.select(0);

		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		unitc.setLayoutData(gd);
	}

	public String getUnit() {
		return Unit.getUnits()[unitc.getSelectionIndex()];
	}

	public void addSelectionListener(SelectionListener listener) {
		unitc.addSelectionListener(listener);
	}

	public void removeSelectionListener(SelectionListener listener) {
		unitc.removeSelectionListener(listener);
	}

	public void setUnit(String key) {
		unitc.select(Unit.getUnitIndex(key));
	}
}
