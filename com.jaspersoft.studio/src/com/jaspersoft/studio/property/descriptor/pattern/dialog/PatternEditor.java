/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.pattern.dialog;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.messages.Messages;

public class PatternEditor extends Wizard {
	private String value;
	private PatternPage page0;

	public String getValue() {
		if (page0 != null)
			return page0.getValue();
		return value;
	}

	public void setValue(String value) {
		if (page0 != null)
			page0.setValue(value);
		this.value = value;
	}

	public PatternEditor() {
		super();
		setWindowTitle(Messages.common_pattern);
		setNeedsProgressMonitor(false);
	}

	@Override
	public void addPages() {
		page0 = new PatternPage(Messages.common_pattern);
		page0.setValue(value);
		page0.setDatePatterns(datePatterns);
		page0.setNumberPatterns(numberPatterns);
		addPage(page0);
	}

	@Override
	public boolean performFinish() {
		return true;
	}

	private boolean datePatterns = true;
	private boolean numberPatterns = true;

	public boolean isDatePatterns() {
		return datePatterns;
	}

	public void setDatePatterns(boolean datePatterns) {
		this.datePatterns = datePatterns;
	}

	public boolean isNumberPatterns() {
		return numberPatterns;
	}

	public void setNumberPatterns(boolean numberPatterns) {
		this.numberPatterns = numberPatterns;
	}
}
