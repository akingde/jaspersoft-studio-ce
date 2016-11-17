/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.pattern.dialog;

import java.text.NumberFormat;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.messages.Messages;

public class CurrencyPattern extends NumericPattern {

	private static String[] patterns = {
		"\u00A4#,##0.##;\u00A4-##0.##",
		"#,##0.##\u00A4;#,##0.##- \u00A4",
		"#,##0.##\u00A4;(#,##0.##) \u00A4",
		"\u00A4#,##0.###;\u00A4(-#,##0.###)",
		"\u00A4#,##0.###;\u00A4(#,##0.###-)" 
	};
	
	public CurrencyPattern(Composite parent, String value) {
		super(parent, NumberFormat.getCurrencyInstance(), value);
		setDescription(Messages.CurrencyPattern_description);
	}
	
	@Override
	protected java.util.List<String> getDefaults() {
		if (dList == null) {
			dList = new ArrayList<String>();
			
			for(String pattern : patterns){
				dList.add(pattern);
				//pattern.replace("\u00A4", currencySymbol);
			}
			
			setPattern(dList.get(0));
		}
		return dList;
	}
}
