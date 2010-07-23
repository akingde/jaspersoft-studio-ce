package com.jaspersoft.studio.property.descriptor.pattern.dialog;

import java.text.NumberFormat;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;

public class CurrencyPattern extends NumericPattern {

	public CurrencyPattern(Composite parent) {
		super(parent, NumberFormat.getCurrencyInstance());
		setDescription("Currency format is used to display monetary values.");
	}

	@Override
	protected java.util.List<String> getDefaults() {
		if (dList == null) {
			dList = new ArrayList<String>();
			dList.add("¤#,##0.###;¤-##0.###");
			dList.add("#,##0.### ¤;#,##0.###- ¤");
			dList.add("#,##0.### ¤;(#,##0.###) ¤");
			dList.add("¤#,##0.###;¤(-#,##0.###)");
			dList.add("¤#,##0.###;¤(#,##0.###-)");
			setPattern(dList.get(0));
		}
		return dList;
	}
}
