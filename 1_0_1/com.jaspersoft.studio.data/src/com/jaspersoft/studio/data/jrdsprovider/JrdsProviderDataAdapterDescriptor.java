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
package com.jaspersoft.studio.data.jrdsprovider;

import java.util.List;

import org.eclipse.swt.graphics.Image;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.provider.DataSourceProviderDataAdapter;
import net.sf.jasperreports.data.provider.DataSourceProviderDataAdapterImpl;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignField;

import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.data.fields.IFieldsProvider;

public class JrdsProviderDataAdapterDescriptor extends DataAdapterDescriptor
		implements IFieldsProvider {
	private DataSourceProviderDataAdapter dsProviderDataAdapter = new DataSourceProviderDataAdapterImpl();

	@Override
	public DataAdapter getDataAdapter() {
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
			JRDataset reportDataset) throws JRException,
			UnsupportedOperationException {
		getFieldProvider();
		return fprovider.getFields(con, reportDataset);
	}

	public boolean supportsGetFieldsOperation() {
		getFieldProvider();
		return fprovider.supportsGetFieldsOperation();
	}

	private void getFieldProvider() {
		if (fprovider == null)
			fprovider = new JRDSProviderFieldsProvider();
	}
	
	/**
	 */
	@Override
	public Image getIcon(int size) {
		// TODO Auto-generated method stub
		if (size == 16)
		{
			return  Activator.getImage("icons/bean-green.png");
		}
		return null;
	}
}
