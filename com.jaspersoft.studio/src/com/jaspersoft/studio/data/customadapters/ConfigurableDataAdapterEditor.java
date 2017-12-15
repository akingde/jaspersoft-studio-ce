/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.customadapters;

import net.sf.jasperreports.engine.JasperReportsContext;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.data.customadapters.ui.AdapterWidgetsDescriptor;

public class ConfigurableDataAdapterEditor implements DataAdapterEditor {
	
	private AdapterWidgetsDescriptor descriptor;
	
	private ConfigurableDataAdapterComposite composite = null;
	
	public ConfigurableDataAdapterEditor(AdapterWidgetsDescriptor descriptor) {
		this.descriptor = descriptor;
	}

	public void setDataAdapter(DataAdapterDescriptor dataAdapter) {
		if (composite != null && dataAdapter instanceof ConfigurableDataAdapterDescriptor) {
			this.composite.setDataAdapter((ConfigurableDataAdapterDescriptor)dataAdapter);
		}
	}

	public DataAdapterDescriptor getDataAdapter() {
		return this.composite.getDataAdapter();
	}

	public ADataAdapterComposite getComposite(Composite parent, int style, WizardPage wizardPage, JasperReportsContext jrContext) {
		if (composite == null) {
			composite = new ConfigurableDataAdapterComposite(parent, style, jrContext, descriptor);
		}
		return composite;
	}

	public String getHelpContextId() {
		return this.composite.getHelpContextId();
	}
}
