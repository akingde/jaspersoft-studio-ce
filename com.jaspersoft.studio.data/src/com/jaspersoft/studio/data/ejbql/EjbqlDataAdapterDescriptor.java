/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.ejbql;

import net.sf.jasperreports.data.ejbql.EjbqlDataAdapter;
import net.sf.jasperreports.data.ejbql.EjbqlDataAdapterImpl;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.AWizardDataEditorComposite;
import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.data.IWizardDataEditorProvider;

public class EjbqlDataAdapterDescriptor extends DataAdapterDescriptor implements IWizardDataEditorProvider {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	@Override
	public EjbqlDataAdapter getDataAdapter() {
		if (dataAdapter == null)
			dataAdapter = new EjbqlDataAdapterImpl();
		return (EjbqlDataAdapter) dataAdapter;
	}

	@Override
	public DataAdapterEditor getEditor() {
		return new EjbqlDataAdapterEditor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#getIcon(int)
	 */
	@Override
	public Image getIcon(int size) {
		if (size == 16) {
			return Activator.getDefault().getImage("icons/bean.png"); //$NON-NLS-1$
		}
		return null;
	}

	@Override
	public AWizardDataEditorComposite createDataEditorComposite(Composite parent, WizardPage page) {
		return new EjbqlWizardDataEditorComposite(parent, page, this);
	}

	@Override
	public String[] getLanguages() {
		return new String[] { JRJpaQueryExecuterFactory.QUERY_LANGUAGE_EJBQL };
	}
}
