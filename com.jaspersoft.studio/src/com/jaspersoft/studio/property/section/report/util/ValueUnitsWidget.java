package com.jaspersoft.studio.property.section.report.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

public class ValueUnitsWidget {

	private Unit unit = new Unit(0, Unit.PX);
	private int max = Integer.MAX_VALUE;
	private int digits = 0;
	private CCombo unitc;
	private Spinner val;

	public void setMax(int max) {
		this.max = max;
	}

	public void createComponent(Composite parent, String label, String toolTip) {
		Label lbl = new Label(parent, SWT.NONE);
		lbl.setText(label);
		lbl.setBackground(parent.getBackground());

		val = new Spinner(parent, SWT.BORDER);
		val.setToolTipText(toolTip);
		GridData gd = new GridData();
		gd.widthHint = 60;
		val.setLayoutData(gd);

		unitc = new CCombo(parent, SWT.BORDER | SWT.SINGLE | SWT.READ_ONLY);
		unitc.setItems(Unit.getUnits());
		unitc.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				String u = Unit.getUnits()[unitc.getSelectionIndex()];
				unit.setUnit(u);
				digits = u.equals(Unit.PX) ? 0 : 4;

				int v = (int) Math.round(unit.getValue() * Math.pow(10, digits));
				val.setValues(v, 0, max, digits, 1, 10);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		val.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				double v = unitc.getSelectionIndex() / Math.pow(10, digits);
				unit.setValue(new Float(v).floatValue(), Unit.getUnits()[unitc.getSelectionIndex()]);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		val.setSelection(0);
		unitc.select(0);
	}
}
