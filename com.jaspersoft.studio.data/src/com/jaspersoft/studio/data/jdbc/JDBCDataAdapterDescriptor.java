/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data.jdbc;

import java.util.List;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.jdbc.JdbcDataAdapter;
import net.sf.jasperreports.data.jdbc.JdbcDataAdapterImpl;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignField;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.data.fields.IFieldsProvider;

/*
 * @author gtoffoli
 *
 */
public class JDBCDataAdapterDescriptor extends DataAdapterDescriptor implements
		IFieldsProvider {
	private JdbcDataAdapter jdbcDataAdapter = new JdbcDataAdapterImpl();

	@Override
	public DataAdapter getDataAdapter() {
		return jdbcDataAdapter;
	}

	@Override
	public void setDataAdapter(DataAdapter dataAdapter) {
		this.jdbcDataAdapter = (JdbcDataAdapter) dataAdapter;
	}

	@Override
	public DataAdapterService getDataAdapterService() {
		JDBCDataAdapterService jdbcDataAdapterService = new JDBCDataAdapterService(
				jdbcDataAdapter);
		return jdbcDataAdapterService;
	}

	@Override
	public DataAdapterEditor getEditor() {

		return new JDBCDataAdapterEditor();
	}

	@Override
	public ImageDescriptor getIcon16() {
		return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID,
				"icons/JDBCDataAdapterIcon-16.gif");
	}

	private IFieldsProvider fprovider;

	@Override
	public List<JRDesignField> getFields(DataAdapterService con,
			JRDataset reportDataset) throws JRException,
			UnsupportedOperationException {
		getFieldProvider();
		return fprovider.getFields(con, reportDataset);
	}

	private void getFieldProvider() {
		if (fprovider == null)
			fprovider = new JDBCFieldsProvider();
	}

	@Override
	public boolean supportsGetFieldsOperation() {
		getFieldProvider();
		return fprovider.supportsGetFieldsOperation();
	}
}
