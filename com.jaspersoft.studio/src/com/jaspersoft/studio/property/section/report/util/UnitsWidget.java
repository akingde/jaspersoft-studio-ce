package com.jaspersoft.studio.property.section.report.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class UnitsWidget {

	public void createComponent(Composite parent, String label, String toolTip, int span) {
		Label lbl = new Label(parent, SWT.NONE);
		lbl.setText(label);
		lbl.setBackground(parent.getBackground());

		CCombo unitc = new CCombo(parent, SWT.BORDER | SWT.SINGLE | SWT.READ_ONLY);
		unitc.setItems(Unit.getUnits());
		unitc.setToolTipText(toolTip);

		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		unitc.setLayoutData(gd);
	}
}
