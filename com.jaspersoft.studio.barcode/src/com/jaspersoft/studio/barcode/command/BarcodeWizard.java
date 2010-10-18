package com.jaspersoft.studio.barcode.command;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.barcode.model.MBarcode;

public class BarcodeWizard extends Wizard {
	private BarcodeWizardPage page0;
	private MBarcode barcode;

	public BarcodeWizard() {
		super();
		setWindowTitle("Barcode Wizard");
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
