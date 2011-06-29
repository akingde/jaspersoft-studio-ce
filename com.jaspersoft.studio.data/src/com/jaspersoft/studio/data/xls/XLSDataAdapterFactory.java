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
package com.jaspersoft.studio.data.xls;

import org.eclipse.swt.graphics.Image;

import net.sf.jasperreports.data.xls.XlsDataAdapterImpl;

import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterFactory;

public class XLSDataAdapterFactory implements DataAdapterFactory {

	public DataAdapterDescriptor createDataAdapter() {
		return new XLSDataAdapterDescriptor();
	}

	public String getDataAdapterClassName() {
		return XlsDataAdapterImpl.class.getName();
	}

	public String getLabel() {
		return "Microsoft Excel (XLS)";
	}

	/* (non-Javadoc)
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#getDescription()
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Use a Microsoft Excel document as data";
	}

	/* (non-Javadoc)
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#getIcon(int)
	 */
	@Override
	public Image getIcon(int size) {
		// TODO Auto-generated method stub
		if (size == 16)
		{
			return  Activator.getImage("icons/document-excel.png");
		}
		return null;
	}
}
