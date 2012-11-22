/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.data.jrdsprovider;

import java.util.List;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.provider.DataSourceProviderDataAdapter;
import net.sf.jasperreports.data.provider.DataSourceProviderDataAdapterImpl;
import net.sf.jasperreports.data.provider.DataSourceProviderDataAdapterService;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignField;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class JrdsProviderDataAdapterDescriptor extends DataAdapterDescriptor
		implements IFieldsProvider {
	private DataSourceProviderDataAdapter dsProviderDataAdapter;

	@Override
	public DataAdapter getDataAdapter() {
		if (dsProviderDataAdapter == null) {
			dsProviderDataAdapter = new DataSourceProviderDataAdapterImpl();
			dsProviderDataAdapter
					.setProviderClass("com.jaspersoft.studio.data.sample.PersonBeansDataSource");
		}
		return dsProviderDataAdapter;
	}

	@Override
	public void setDataAdapter(DataAdapter dataAdapter) {
		dsProviderDataAdapter = (DataSourceProviderDataAdapter) dataAdapter;
	}

	@Override
	public DataAdapterEditor getEditor() {
		return new JrdsProviderDataAdapterEditor();
	}

	private IFieldsProvider fprovider;

	public List<JRDesignField> getFields(DataAdapterService con,
			JasperReportsConfiguration jConfig, JRDataset jDataset)
			throws JRException, UnsupportedOperationException {
		getFieldProvider(jConfig);
		return fprovider.getFields(con, jConfig, jDataset);
	}

	public boolean supportsGetFieldsOperation(JasperReportsConfiguration jConfig) {
		getFieldProvider(jConfig);
		return fprovider.supportsGetFieldsOperation(jConfig);
	}

	private void getFieldProvider(JasperReportsConfiguration jConfig) {
		if (fprovider == null) {
			fprovider = new JRDSProviderFieldsProvider();
			DataSourceProviderDataAdapterService ds = new DataSourceProviderDataAdapterService(
					jConfig, dsProviderDataAdapter);
			try {
				((JRDSProviderFieldsProvider) fprovider).setProvider(ds
						.getProvider());
			} catch (JRException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 */
	@Override
	public Image getIcon(int size) {
		if (size == 16) {
			return Activator.getImage("icons/bean-green.png");
		}
		return null;
	}
}
