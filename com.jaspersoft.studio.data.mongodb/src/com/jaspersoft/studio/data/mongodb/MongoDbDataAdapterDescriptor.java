/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.data.mongodb;

import java.util.List;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignField;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.mongodb.adapter.MongoDbDataAdapter;
import com.jaspersoft.mongodb.adapter.MongoDbDataAdapterImplementation;
import com.jaspersoft.studio.data.AWizardDataEditorComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.IWizardDataEditorProvider;
import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * 
 * @author gtoffoli
 * @author Eric Diaz
 * 
 */
public class MongoDbDataAdapterDescriptor extends DataAdapterDescriptor
		implements IFieldsProvider, IWizardDataEditorProvider {
	private MongoDbDataAdapter dataAdapter = new MongoDbDataAdapterImplementation();

	private IFieldsProvider fieldsProvider;

	@Override
	public MongoDbDataAdapter getDataAdapter() {
		return dataAdapter;
	}

	@Override
	public void setDataAdapter(DataAdapter dataAdapter) {
		this.dataAdapter = (MongoDbDataAdapter) dataAdapter;
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

	public List<JRDesignField> getFields(DataAdapterService con,
			JasperReportsConfiguration jConfig, JRDataset reportDataset)
			throws JRException, UnsupportedOperationException {
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
	public AWizardDataEditorComposite createDataEditorComposite(
			Composite parent, WizardPage page) {
		return new MongoDBWizardDataEditorComposite(parent, page, this);
	}
}
