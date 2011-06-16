/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data.jrdsprovider;

import java.util.List;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.provider.DataSourceProviderDataAdapter;
import net.sf.jasperreports.data.provider.DataSourceProviderDataAdapterImpl;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignField;

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
}
