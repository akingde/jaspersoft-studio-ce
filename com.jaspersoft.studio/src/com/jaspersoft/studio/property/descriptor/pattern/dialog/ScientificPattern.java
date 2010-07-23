package com.jaspersoft.studio.property.descriptor.pattern.dialog;

import java.text.NumberFormat;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;

public class ScientificPattern extends NumericPattern {

	public ScientificPattern(Composite parent) {
		super(parent, NumberFormat.getNumberInstance());
		setDescription("Scientific format is used to display numerical values in scientific notation.");
	}

	@Override
	protected java.util.List<String> getDefaults() {
		if (dList == null) {
			dList = new ArrayList<String>();
			dList.add("0.0##E0");

			setPattern(dList.get(0));
		}
		return dList;
	}
}