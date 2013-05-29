/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.data.jdbc;

import net.sf.jasperreports.data.jdbc.JdbcDataAdapterImpl;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.adapter.IDataAdapterCreator;

/**
 * Creator to build a JSS JDBC data adapter from the xml definition of an iReport JDBC 
 * data adapter
 * 
 * @author Orlandin Marco
 */
public class JDBCCreator implements IDataAdapterCreator {

	@Override
	public DataAdapterDescriptor buildFromXML(Document docXML) {
		JdbcDataAdapterImpl result = new JdbcDataAdapterImpl();
		
		NamedNodeMap rootAttributes = docXML.getChildNodes().item(0).getAttributes();
		String connectionName = rootAttributes.getNamedItem("name").getTextContent();
		result.setName(connectionName);
		
		NodeList children = docXML.getChildNodes().item(0).getChildNodes();
		for(int i=0; i<children.getLength(); i++){
			Node node = children.item(i);
			if (node.getNodeName().equals("connectionParameter")){
				String paramName = node.getAttributes().getNamedItem("name").getTextContent();
				
				if (paramName.equals("ServerAddress")) result.setServerAddress(node.getTextContent());
				if (paramName.equals("SavePassword")) result.setSavePassword(node.getTextContent().equals("true"));
				if (paramName.equals("Url")) result.setUrl(node.getTextContent());
				if (paramName.equals("Database")) result.setDatabase(node.getTextContent());
				if (paramName.equals("Password")) result.setPassword(node.getTextContent());
				if (paramName.equals("Username")) result.setUsername(node.getTextContent());
				if (paramName.equals("JDBCDriver")) result.setDriver(node.getTextContent());
			}
		}
		JDBCDataAdapterDescriptor desc = new JDBCDataAdapterDescriptor();
		desc.setDataAdapter(result);
		return desc;
	}

	@Override
	public String getID() {
		return "com.jaspersoft.ireport.designer.connection.JDBCConnection";
	}

}
