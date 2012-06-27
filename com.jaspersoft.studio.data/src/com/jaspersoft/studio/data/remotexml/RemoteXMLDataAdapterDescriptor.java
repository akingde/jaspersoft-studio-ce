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
package com.jaspersoft.studio.data.remotexml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.xml.RemoteXmlDataAdapter;
import net.sf.jasperreports.data.xml.RemoteXmlDataAdapterImpl;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignField;

import org.eclipse.swt.graphics.Image;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.data.xml.XMLDataAdapterDescriptor;
import com.jaspersoft.studio.utils.UIUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class RemoteXMLDataAdapterDescriptor extends XMLDataAdapterDescriptor implements IFieldsProvider
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
	public DataAdapterEditor getEditor() {
		return new RemoteXMLDataAdapterEditor();
	}
	
	/* (non-Javadoc)
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#getIcon(int)
	 */
	@Override
	public Image getIcon(int size) {
		// TODO Auto-generated method stub
		if (size == 16)
		{
			return  Activator.getImage("icons/blue-document-code.png");
		}
		return null;
	}

	@Override
	public List<JRDesignField> getFields(DataAdapterService con,
			JasperReportsConfiguration jConfig, JRDataset jDataset)
			throws JRException, UnsupportedOperationException {
		Throwable t=null;
		ArrayList<JRDesignField> fields = new ArrayList<JRDesignField>();
		try {
			String fileName = remoteXmlDataAdapter.getFileName();
			Document doc=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fileName);	
			fields.addAll(getFieldsFromDocument(doc, jConfig, jDataset));
		} catch (SAXException e) {
			t=e;
		} catch (IOException e) {
			t=e;
		} catch (ParserConfigurationException e) {
			t=e;
		} 

		if(t!=null){
			UIUtils.showError(t);
		}
		return fields;
	}
	
	
}
