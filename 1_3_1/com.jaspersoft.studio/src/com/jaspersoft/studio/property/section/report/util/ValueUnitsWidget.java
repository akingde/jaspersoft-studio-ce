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
package com.jaspersoft.studio.property.section.report.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

public class ValueUnitsWidget {

	private final class SpinerSelectionListener implements SelectionListener {
		public void widgetSelected(SelectionEvent e) {
			unit.setValue(new Float(val.getSelection() / Math.pow(10, digits)).floatValue(),
					Unit.getUnits()[unitc.getSelectionIndex()]);
		}

		public void widgetDefaultSelected(SelectionEvent e) {
			widgetSelected(e);
		}
	}

	private Unit unit = new Unit(0, Unit.PX);
	private int max = Integer.MAX_VALUE;
	private int digits = 0;
	private Combo unitc;
	private Spinner val;
	private SpinerSelectionListener spinerSelection;

	public void setMax(int max) {
		this.max = max;
	}

	public void createComponent(Composite parent, String label, String toolTip) {
		Label lbl = new Label(parent, SWT.NONE);
		lbl.setText(label);
		lbl.setBackground(parent.getBackground());

		val = new Spinner(parent, SWT.BORDER | SWT.RIGHT);
		val.setToolTipText(toolTip);
		GridData gd = new GridData();
		gd.widthHint = 80;
		val.setLayoutData(gd);

		unitc = new Combo(parent, SWT.BORDER | SWT.SINGLE | SWT.READ_ONLY);
		unitc.setItems(Unit.getUnits());
		unitc.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				String u = Unit.getUnits()[unitc.getSelectionIndex()];
				if (unit.setUnit(u)) {
					setSpinerValue(u);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		spinerSelection = new SpinerSelectionListener();
		val.addSelectionListener(spinerSelection);

		unitc.select(0);
		setSpinerValue(unit.getUnit());
	}

	private void setSpinerValue(String u) {
		digits = u.equals(Unit.PX) ? 0 : 4;

		val.setDigits(digits);
		val.setMinimum(0);
		val.setMaximum(max);
		val.setIncrement(1);
		val.removeSelectionListener(spinerSelection);
		val.setSelection((int) Math.round(unit.getValue(u) * Math.pow(10, digits)));
		val.addSelectionListener(spinerSelection);
	}

	public void addSelectionListener(SelectionListener listener) {
		val.addSelectionListener(listener);
	}

	public void removeSelectionListener(SelectionListener listener) {
		val.removeSelectionListener(listener);
	}

	public void setUnit(String u) {
		if (unit.setUnit(u)) {
			unitc.select(Unit.getUnitIndex(u));
			setSpinerValue(u);
		}
	}

	public int getValue() {
		return unit.getPxValue();
	}

	public void setValue(int px) {
		unit.setValue(px, Unit.PX);
		setSpinerValue(unit.getUnit());
	}
}
