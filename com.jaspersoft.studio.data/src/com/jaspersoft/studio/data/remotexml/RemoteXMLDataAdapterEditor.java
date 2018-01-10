/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.remotexml;

import net.sf.jasperreports.engine.JasperReportsContext;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;

public class RemoteXMLDataAdapterEditor implements DataAdapterEditor {
	
	RemoteXMLDataAdapterComposite composite = null;

	public void setDataAdapter(DataAdapterDescriptor dataAdapter) {
		if (dataAdapter instanceof RemoteXMLDataAdapterDescriptor) {
			this.composite.setDataAdapter((RemoteXMLDataAdapterDescriptor)dataAdapter);
		}
	}

	public DataAdapterDescriptor getDataAdapter() {
		return this.composite.getDataAdapter();
	}

	public ADataAdapterComposite getComposite(Composite parent, int style, WizardPage wizardPage, JasperReportsContext jrContext) {
		if (this.composite == null) {
			composite = new RemoteXMLDataAdapterComposite(parent, style, jrContext);
		}
		return this.composite;
	}

	public String getHelpContextId() {
		return this.composite.getHelpContextId();
	}
}
