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
package com.jaspersoft.studio.data.customjrds;

import org.eclipse.swt.graphics.Image;

import net.sf.jasperreports.data.ds.DataSourceDataAdapterImpl;

import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterFactory;

public class CustomJrdsDataAdapterFactory implements DataAdapterFactory {

	public DataAdapterDescriptor createDataAdapter() {
		return new CustomJrdsDataAdapterDescriptor();
	}

	public String getDataAdapterClassName() {
		return DataSourceDataAdapterImpl.class.getName();
	}

	public String getLabel() {
		return "Custom implementaion of JRDataSource";
	}

	/* (non-Javadoc)
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#getDescription()
	 */
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Use your own java class to instance a JRDataSource";
	}

	/* (non-Javadoc)
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#getIcon(int)
	 */
	public Image getIcon(int size) {
		// TODO Auto-generated method stub
		if (size == 16)
		{
			return  Activator.getImage("icons/bean-green.png");
		}
		return null;
	}

}
