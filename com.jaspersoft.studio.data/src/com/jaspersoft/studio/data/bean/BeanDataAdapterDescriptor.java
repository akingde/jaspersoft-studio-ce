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
package com.jaspersoft.studio.data.bean;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.bean.BeanDataAdapter;
import net.sf.jasperreports.data.bean.BeanDataAdapterImpl;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;

public class BeanDataAdapterDescriptor extends DataAdapterDescriptor {
	private BeanDataAdapter beanDataAdapter;

	@Override
	public BeanDataAdapter getDataAdapter() {
		if (beanDataAdapter == null) {
			beanDataAdapter = new BeanDataAdapterImpl();
			beanDataAdapter
					.setFactoryClass("com.jaspersoft.studio.data.sample.SampleJRDataSourceFactory");
			beanDataAdapter.setMethodName("createBeanCollection");
		}
		return beanDataAdapter;
	}

	@Override
	public void setDataAdapter(DataAdapter dataAdapter) {
		this.beanDataAdapter = (BeanDataAdapter) dataAdapter;
	}

	@Override
	public DataAdapterEditor getEditor() {
		return new BeanDataAdapterEditor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#getIcon(int)
	 */
	@Override
	public Image getIcon(int size) {
		if (size == 16) {
			return Activator.getImage("icons/beans.png");
		}
		return null;
	}
}
