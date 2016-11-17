/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.fonts.wizard;

import net.sf.jasperreports.engine.fonts.FontFamily;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.messages.Messages;

public class FontConfigWizard extends Wizard {
	private FontFamily font;

	private FontFamilyPage step1;
	private FontMappingPage step2;
	private FontLocalesPage step3;

	public FontConfigWizard() {
		super();
		setWindowTitle(Messages.FontConfigWizard_0);
	}

	public FontFamily getFont() {
		return font;
	}

	public void setFont(FontFamily font) {
		this.font = font;
	}

	@Override
	public void addPages() {
		step1 = new FontFamilyPage(font);
		addPage(step1);

		step2 = new FontMappingPage(font);
		addPage(step2);

		step3 = new FontLocalesPage(font);
		addPage(step3);
	}

	@Override
	public boolean performFinish() {
		return true;
	}

}
