/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.bean;

import net.sf.jasperreports.engine.JasperReportsContext;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;

public class BeanDataAdapterEditor implements DataAdapterEditor {

	BeanDataAdapterComposite composite = null;

	public void setDataAdapter(DataAdapterDescriptor dataAdapter) {
		if (composite != null && dataAdapter instanceof BeanDataAdapterDescriptor) {
			composite.setDataAdapter((BeanDataAdapterDescriptor) dataAdapter);
		}
	}

	public DataAdapterDescriptor getDataAdapter() {
		return composite.getDataAdapter();
	}

	public ADataAdapterComposite getComposite(Composite parent, int style, WizardPage wizardPage, JasperReportsContext jrContext) {
		if (composite == null) {
			composite = new BeanDataAdapterComposite(parent, style, jrContext);
		}
		return composite;
	}

	public String getHelpContextId() {
		return composite.getHelpContextId();
	}

}
