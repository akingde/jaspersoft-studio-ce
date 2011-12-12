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

import org.eclipse.swt.graphics.Image;

import net.sf.jasperreports.data.provider.DataSourceProviderDataAdapterImpl;

import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterFactory;

public class JrdsProviderDataAdapterFactory implements DataAdapterFactory {

	public DataAdapterDescriptor createDataAdapter() {
		return new JrdsProviderDataAdapterDescriptor();
	}

	public String getDataAdapterClassName() {
		return DataSourceProviderDataAdapterImpl.class.getName();
	}

	public String getLabel() {
		return "JasperReports DataSource Provider class";
	}

	/* (non-Javadoc)
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#getDescription()
	 */
	public String getDescription() {
		return "Use a java class which implements the JRDataSourceProvider interface";
	}

	/**
	 */
	public Image getIcon(int size) {
		if (size == 16)
		{
			return  Activator.getImage("icons/bean-green.png");
		}
		return null;
	}

}
