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
package com.jaspersoft.studio.data.remotexml;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.xml.RemoteXmlDataAdapter;
import net.sf.jasperreports.data.xml.RemoteXmlDataAdapterImpl;
import net.sf.jasperreports.data.xml.RemoteXmlDataAdapterService;

import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.data.xml.XMLDataAdapterDescriptor;

public class RemoteXMLDataAdapterDescriptor extends XMLDataAdapterDescriptor 
{
	private RemoteXmlDataAdapter remoteXmlDataAdapter = new RemoteXmlDataAdapterImpl();
	
	@Override
	public DataAdapter getDataAdapter() {
		return remoteXmlDataAdapter;
	}

	@Override
	public void setDataAdapter(DataAdapter dataAdapter) {
		this.remoteXmlDataAdapter = (RemoteXmlDataAdapter)dataAdapter;
	}

	@Override
	public DataAdapterService getDataAdapterService() {
		RemoteXmlDataAdapterService remoteXmlDataAdapterService = new RemoteXmlDataAdapterService();
		remoteXmlDataAdapterService.setDataAdapter(remoteXmlDataAdapter);
		return remoteXmlDataAdapterService;
	}

	@Override
	public DataAdapterEditor getEditor() {
		return new RemoteXMLDataAdapterEditor();
	}
}
