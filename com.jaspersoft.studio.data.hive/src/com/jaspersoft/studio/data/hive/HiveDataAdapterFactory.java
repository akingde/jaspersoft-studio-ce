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

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.hadoop.hive.adapter.HiveDataAdapter;
import com.jaspersoft.hadoop.hive.adapter.HiveDataAdapterImplementation;
import com.jaspersoft.hadoop.hive.adapter.HiveDataAdapterService;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterFactory;

/**
 * @author gtoffoli
 * 
 */
public class HiveDataAdapterFactory implements DataAdapterFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#createDataAdapter()
	 */
	public DataAdapterDescriptor createDataAdapter() {
		HiveDataAdapterDescriptor descriptor = new HiveDataAdapterDescriptor();
		descriptor.getDataAdapter().setUrl(
				"jdbc:hive://lovalhost:10000/default");
		return descriptor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.data.DataAdapterFactory#getDataAdapterClassName()
	 */
	public String getDataAdapterClassName() {
		return HiveDataAdapterImplementation.class.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#getDescription()
	 */
	public String getLabel() {
		return "Hive Connection";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#getDescription()
	 */
	public String getDescription() {
		return "Use HiveQL queries to get data from a hive instance";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#getIcon(int)
	 */
	public Image getIcon(int size) {
		if (size == 16) {
			return Activator.getImage("icons/hive.png");
		}
		return null;
	}

	public DataAdapterService createDataAdapterService(DataAdapter dataAdapter) {
		if (dataAdapter instanceof HiveDataAdapter)
			return new HiveDataAdapterService((HiveDataAdapter) dataAdapter);
		return null;
	}
}