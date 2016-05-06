/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
			vclone.setSubreport(v.getSubreport());
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
