/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.mondrian;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

import net.sf.jasperreports.data.mondrian.MondrianDataAdapterImpl;
import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.data.AWizardDataEditorComposite;
import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.data.jdbc.JDBCDataAdapterDescriptor;
import com.jaspersoft.studio.data.ui.WizardQueryEditorComposite;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/*
 * @author gtoffoli
 *
 */
public class MondrianDataAdapterDescriptor extends JDBCDataAdapterDescriptor {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MondrianDataAdapterDescriptor() {
		setDataAdapter(new MondrianDataAdapterImpl());
	}

	@Override
	public DataAdapterEditor getEditor() {
		return new MondrianDataAdapterEditor();
	}

	@Override
	public void getFieldProvider() {
		if (fprovider == null)
			fprovider = new MondrianFieldsProvider();
	}

	@Override
	public boolean supportsGetFieldsOperation(JasperReportsConfiguration jConfig) {
		getFieldProvider();
		return fprovider.supportsGetFieldsOperation(jConfig);
	}
	
	@Override
	public AWizardDataEditorComposite createDataEditorComposite(Composite parent, WizardPage page) {
		return new WizardQueryEditorComposite(parent, page, this, "mdx");
	}

}
