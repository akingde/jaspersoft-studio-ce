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
package com.jaspersoft.studio.data.hive;

import java.util.List;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignField;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.data.hive.adapter.HiveDataAdapter;
import com.jaspersoft.studio.data.hive.adapter.HiveDataAdapterImpl;
import com.jaspersoft.studio.data.jdbc.JDBCFieldsProvider;

/*
 * @author gtoffoli
 *
 */
public class HiveDataAdapterDescriptor extends DataAdapterDescriptor implements
		IFieldsProvider {
	private HiveDataAdapter jdbcDataAdapter = new HiveDataAdapterImpl();

	
	@Override
	public DataAdapter getDataAdapter() {
		return jdbcDataAdapter;
	}

	@Override
	public void setDataAdapter(DataAdapter dataAdapter) {
		this.jdbcDataAdapter = (HiveDataAdapter) dataAdapter;
	}

	@Override
	public HiveDataAdapterEditor getEditor() {
		return new HiveDataAdapterEditor();
	}

	/* (non-Javadoc)
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#getIcon(int)
	 */
	@Override
	public Image getIcon(int size) {
		if (size == 16)
		{
			return  Activator.getImage("icons/hive.png");
		}
		return null;
	}

	private IFieldsProvider fprovider;

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

	public boolean supportsGetFieldsOperation() {
		getFieldProvider();
		return fprovider.supportsGetFieldsOperation();
	}
}
