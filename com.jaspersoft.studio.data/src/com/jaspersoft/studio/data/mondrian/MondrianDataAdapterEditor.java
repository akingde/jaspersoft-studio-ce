/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.mondrian;

import net.sf.jasperreports.engine.JasperReportsContext;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.jdbc.JDBCDataAdapterEditor;

/*
 * @author gtoffoli
 *
 */
public class MondrianDataAdapterEditor extends JDBCDataAdapterEditor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.data.DataAdapterEditor#getComposite(org.eclipse
	 * .swt.widgets.Composite, int)
	 */
	public ADataAdapterComposite getComposite(Composite parent, int style,
 WizardPage wizardPage, JasperReportsContext jrContext) {
		if (composite == null || composite.getParent() != parent) {
			if (composite != null)
				composite.dispose();
			composite = new MondrianDataAdapterComposite(parent, style, jrContext);
			composite.setContextId("adapter_mondrian");
		}
		return composite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.data.DataAdapterEditor#setDataAdapter(com.jaspersoft
	 * .studio.data.DataAdapter)
	 */
	public void setDataAdapter(DataAdapterDescriptor dataAdapter) {
		if (composite != null
				&& dataAdapter instanceof MondrianDataAdapterDescriptor) {
			composite
					.setDataAdapter((MondrianDataAdapterDescriptor) dataAdapter);
		}
	}
	
}
