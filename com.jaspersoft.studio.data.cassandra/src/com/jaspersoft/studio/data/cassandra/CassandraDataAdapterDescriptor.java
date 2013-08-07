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
/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data.cassandra;

import java.util.List;

import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignField;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.cassandra.adapter.CassandraDataAdapter;
import com.jaspersoft.cassandra.adapter.CassandraDataAdapterImplementation;
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
public class CassandraDataAdapterDescriptor extends DataAdapterDescriptor implements IFieldsProvider, IWizardDataEditorProvider {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private IFieldsProvider fieldsProvider;

	@Override
	public CassandraDataAdapter getDataAdapter() {
		if (dataAdapter == null)
			dataAdapter = new CassandraDataAdapterImplementation();
		return (CassandraDataAdapter) dataAdapter;
	}

	@Override
	public CassandraDataAdapterEditor getEditor() {
		return new CassandraDataAdapterEditor();
	}

	@Override
	public Image getIcon(int size) {
		if (size == 16)
			return Activator.getDefault().getImage("icons/cassandra.png");
		return null;
	}

	public List<JRDesignField> getFields(DataAdapterService con, JasperReportsConfiguration jConfig, JRDataset reportDataset) throws JRException, UnsupportedOperationException {
		getFieldProvider();
		return fieldsProvider.getFields(con, jConfig, reportDataset);
	}

	private void getFieldProvider() {
		if (fieldsProvider == null)
			fieldsProvider = new CassandraFieldsProvider();
	}

	@Override
	public boolean supportsGetFieldsOperation(JasperReportsConfiguration jConfig) {
		getFieldProvider();
		return fieldsProvider.supportsGetFieldsOperation(jConfig);
	}

	@Override
	public AWizardDataEditorComposite createDataEditorComposite(Composite parent, WizardPage page) {
		return new CassandraWizardDataEditorComposite(parent, page, this);
	}
}
