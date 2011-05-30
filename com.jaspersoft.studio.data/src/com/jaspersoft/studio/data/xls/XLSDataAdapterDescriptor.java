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

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.XlsDataAdapter;
import net.sf.jasperreports.data.XlsDataAdapterService;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;

public class XLSDataAdapterDescriptor extends DataAdapterDescriptor 
{
	private XlsDataAdapter xlsDataAdapter = new XlsDataAdapter();
	
	@Override
	public DataAdapter getDataAdapter() {
		return xlsDataAdapter;
	}

	@Override
	public void setDataAdapter(DataAdapter dataAdapter) {
		this.xlsDataAdapter = (XlsDataAdapter)dataAdapter;
	}

	@Override
	public DataAdapterService getDataAdapterService() {
		XlsDataAdapterService xlsDataAdapterService = new XlsDataAdapterService();
		xlsDataAdapterService.setDataAdapter(xlsDataAdapter);
		return xlsDataAdapterService;
	}

	@Override
	public DataAdapterEditor getEditor() {
		return new XLSDataAdapterEditor();
	}

	@Override
	public ImageDescriptor getIcon16() {
		return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/XLSDataAdapterIcon-16.gif");
	}
}
