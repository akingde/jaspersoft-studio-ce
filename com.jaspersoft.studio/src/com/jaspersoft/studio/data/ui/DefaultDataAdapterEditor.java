/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.ui;

import net.sf.jasperreports.engine.JasperReportsContext;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;

public class DefaultDataAdapterEditor implements DataAdapterEditor {
	
	  DefaultDataAdapterEditorComposite composite = null;
	  
	  public void setDataAdapter(DataAdapterDescriptor dataAdapter) {
	  	  if (dataAdapter == null) { 
	  		    // dataAdapter should never be null
	  	  }
	  	  this.composite.setDataAdapter(dataAdapter);
	  }

	  public DataAdapterDescriptor getDataAdapter() {
		    return this.composite.getDataAdapter();
	  }

	public ADataAdapterComposite getComposite(Composite parent, int style, WizardPage wizardPage,
			JasperReportsContext jrContext) {
		    if (composite == null) {
			composite = new DefaultDataAdapterEditorComposite(parent, style, wizardPage, jrContext);
		    }
		    return composite;
	  }

	  public String getHelpContextId() {
		    return composite.getHelpContextId();
	  }
}
