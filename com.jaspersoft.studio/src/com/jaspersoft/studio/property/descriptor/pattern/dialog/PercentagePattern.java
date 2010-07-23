package com.jaspersoft.studio.property.descriptor.pattern.dialog;

import java.text.NumberFormat;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;

public class PercentagePattern extends NumericPattern {

	public PercentagePattern(Composite parent) {
		super(parent, NumberFormat.getPercentInstance());
		setDescription("Percent format multiplies numerical values by 100 and displays the result \nwith a percent symbol.");
	}

	@Override
	protected java.util.List<String> getDefaults() {
		if (dList == null) {
			dList = new ArrayList<String>();
			dList.add("#,##0.### %");
			dList.add("#,##0.### \u2030");
			dList.add("#,##0.### %;(#,##0.###) %");
			dList.add("#,##0.### %;(-#,##0.###) %");
			dList.add("#,##0.### %;(#,##0.###-) %");
			setPattern(dList.get(0));
		}
		return dList;
	}
}
