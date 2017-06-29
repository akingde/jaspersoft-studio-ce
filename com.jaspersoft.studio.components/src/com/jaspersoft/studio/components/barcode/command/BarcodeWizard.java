/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.barcode.command;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.components.barcode.messages.Messages;
import com.jaspersoft.studio.components.barcode.model.MBarcode;

public class BarcodeWizard extends Wizard {
	private BarcodeWizardPage page0;
	private MBarcode barcode;

	public BarcodeWizard() {
		super();
		setWindowTitle(Messages.common_barcode_wizard);
	}

	@Override
	public void addPages() {
		page0 = new BarcodeWizardPage();
		addPage(page0);
	}

	public MBarcode getBarcode() {
		if (page0 != null)
			return page0.getBarcode();
		return barcode;
	}

	@Override
	public boolean performFinish() {
		return true;
	}

}
