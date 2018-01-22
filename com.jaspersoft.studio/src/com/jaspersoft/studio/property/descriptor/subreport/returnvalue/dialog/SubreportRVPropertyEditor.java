/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.subreport.returnvalue.dialog;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.JReportsDTO;

public class SubreportRVPropertyEditor extends Wizard {
	private JReportsDTO value;
	private SubreportRVPropertyPage page0;

	public JReportsDTO getValue() {
		JReportsDTO v = null;
		if (page0 != null)
			v = page0.getDto();
		else
			v = value;
		if (v != null) {
			JReportsDTO vclone = new JReportsDTO();
			vclone.setjConfig(v.getjConfig());
			vclone.setSubreport(v.getSubreport(), v.getDataset());
			vclone.setValue(v.getValue());
			return vclone;
		}
		return null;
	}

	public void setValue(JReportsDTO value) {
		if (page0 != null)
			page0.setDto(value);
		this.value = value;
	}

	public SubreportRVPropertyEditor() {
		super();
		setWindowTitle(Messages.common_properties);
		setNeedsProgressMonitor(false);
	}

	@Override
	public void addPages() {
		page0 = new SubreportRVPropertyPage();
		page0.setDto(value);
		addPage(page0);
	}

	@Override
	public boolean performFinish() {
		return true;
	}

}
