/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.band.rv;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.band.JRBandDTO;

public class RVPropertyEditor extends Wizard {
	private JRBandDTO value;
	private ReturnValuesPropertyPage page0;

	public JRBandDTO getValue() {
		JRBandDTO v = null;
		if (page0 != null)
			v = page0.getDto();
		else
			v = value;
		if (v != null) {
			JRBandDTO vclone = new JRBandDTO();
			vclone.setjConfig(v.getjConfig());
			vclone.setBand(v.getBand());
			vclone.setValue(v.getValue());
			return vclone;
		}
		return null;
	}

	public void setValue(JRBandDTO value) {
		if (page0 != null)
			page0.setDto(value);
		this.value = value;
	}

	public RVPropertyEditor() {
		super();
		setWindowTitle(Messages.common_return_values);
		setNeedsProgressMonitor(false);
	}

	@Override
	public void addPages() {
		page0 = new ReturnValuesPropertyPage();
		page0.setDto(value);
		addPage(page0);
	}

	@Override
	public boolean performFinish() {
		return true;
	}

}
