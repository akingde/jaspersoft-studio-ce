package com.jaspersoft.studio.components.chart.property.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.section.AbstractSection;

public class Btn3Boolean {
	private CCombo cmb3Bool;

	public Btn3Boolean(Composite parent, AbstractSection section,
			String property, String tooltip) {
		createComponent(parent, section, property, tooltip);
	}

	public void createComponent(Composite parent,
			final AbstractSection section, final String property, String tooltip) {
		cmb3Bool = new CCombo(parent, SWT.BORDER | SWT.FLAT | SWT.READ_ONLY);
		cmb3Bool.setItems(new String[] { "NULL", "TRUE", "FALSE" });
		cmb3Bool.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				Boolean bval = null;
				switch (cmb3Bool.getSelectionIndex()) {
				case 1:
					bval = Boolean.TRUE;
					break;
				case 2:
					bval = Boolean.FALSE;
					break;
				}

				section.changeProperty(property, bval);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		cmb3Bool.setToolTipText(tooltip);
	}

	public void setData(Boolean b) {
		if (b == null)
			cmb3Bool.select(0);
		else if (b)
			cmb3Bool.select(1);
		else
			cmb3Bool.select(2);
	}
}
