/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.mongodb;

import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.mongodb.MongoDbDataSource;
import com.jaspersoft.mongodb.adapter.MongoDbDataAdapter;
import com.jaspersoft.mongodb.adapter.MongoDbDataAdapterImpl;
import com.jaspersoft.studio.data.AWizardDataEditorComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.IWizardDataEditorProvider;
import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.data.ui.WizardQueryEditorComposite;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignField;

/**
 * 
 * @author gtoffoli
 * @author Eric Diaz
 * 
 */
public class MongoDbDataAdapterDescriptor extends DataAdapterDescriptor
		implements IFieldsProvider, IWizardDataEditorProvider {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	private IFieldsProvider fieldsProvider;

	@Override
	public MongoDbDataAdapter getDataAdapter() {
		if (dataAdapter == null)
			dataAdapter = new MongoDbDataAdapterImpl();
		return (MongoDbDataAdapter) dataAdapter;
	}

	@Override
	public MongoDbDataAdapterEditor getEditor() {
		return new MongoDbDataAdapterEditor();
	}

	@Override
	public Image getIcon(int size) {
		if (size == 16) {
			return Activator.getDefault().getImage(Activator.ICON_NAME);
		}
		return null;
	}

	public List<JRDesignField> getFields(DataAdapterService con, JasperReportsConfiguration jConfig,
			JRDataset reportDataset) throws JRException, UnsupportedOperationException {
		getFieldProvider();
		return fieldsProvider.getFields(con, jConfig, reportDataset);
	}

	private void getFieldProvider() {
		if (fieldsProvider == null)
			fieldsProvider = new MongoDbFieldsProvider();
	}

	public boolean supportsGetFieldsOperation(JasperReportsConfiguration jConfig) {
		getFieldProvider();
		return fieldsProvider.supportsGetFieldsOperation(jConfig);
	}

	@Override
	public AWizardDataEditorComposite createDataEditorComposite(Composite parent, WizardPage page) {
		return new WizardQueryEditorComposite(parent, page, this, MongoDbDataSource.QUERY_LANGUAGE);
	}

	@Override
	public String[] getLanguages() {
		return new String[] { MongoDbDataSource.QUERY_LANGUAGE };
	}
}
