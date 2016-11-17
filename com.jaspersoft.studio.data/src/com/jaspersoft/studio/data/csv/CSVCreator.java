/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.csv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.jasperreports.data.csv.CsvDataAdapterImpl;
import net.sf.jasperreports.eclipse.util.DataFileUtils;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.adapter.IDataAdapterCreator;

/**
 * Creator to build a JSS CSV data adapter from the xml definition of an iReport CSV 
 * data adapter
 * 
 * @author Orlandin Marco
 */
public class CSVCreator implements IDataAdapterCreator {

	private class ColumnName implements Comparable<ColumnName>{
		
		private String name;
		
		private int index;
		
		public ColumnName(int index, String name){
			this.index = index;
			this.name = name;
		}

		@Override
		public int compareTo(ColumnName o) {
			return index - o.getIndex();
		}
		
		public int getIndex(){
			return index;
		}
		
		public String getName(){
			return name;
		}
	}
	
	@Override
	public DataAdapterDescriptor buildFromXML(Document docXML) {
		CsvDataAdapterImpl result = new CsvDataAdapterImpl();
		
		NamedNodeMap rootAttributes = docXML.getChildNodes().item(0).getAttributes();
		String connectionName = rootAttributes.getNamedItem("name").getTextContent();
		result.setName(connectionName);
		
		NodeList children = docXML.getChildNodes().item(0).getChildNodes();
		List<ColumnName> columnNames = new ArrayList<ColumnName>();

		for(int i=0; i<children.getLength(); i++){
			Node node = children.item(i);
			if (node.getNodeName().equals("connectionParameter")){
				String paramName = node.getAttributes().getNamedItem("name").getTextContent();
	
				String textContent = node.getTextContent();
				if (paramName.startsWith("COLUMN_")){
					int index = Integer.parseInt(paramName.substring(paramName.lastIndexOf("_")+1));
					columnNames.add(new ColumnName(index, textContent));
				}			
				if (paramName.equals("fieldDelimiter")) {
					result.setFieldDelimiter(textContent);
				}
				if (paramName.equals("queryExecuterMode")) {
					result.setQueryExecuterMode(textContent.equals("true"));
				}
				if (paramName.equals("useFirstRowAsHeader")) {
					result.setUseFirstRowAsHeader(textContent.equals("true"));
				}
				if (paramName.equals("customDateFormat")) {
					result.setDatePattern(textContent);
				}
				if (paramName.equals("Filename")) {
					result.setDataFile(DataFileUtils.getDataFile(textContent));
				}
				if (paramName.equals("recordDelimiter")) {
					result.setRecordDelimiter(textContent);
				}
				
			}
		}
		
		Collections.sort(columnNames);
		List<String> names = new ArrayList<String>();
		for(ColumnName col : columnNames)
			names.add(col.getName());
		result.setColumnNames(names);
		CSVDataAdapterDescriptor desc = new CSVDataAdapterDescriptor();
		desc.setDataAdapter(result);
		return desc;
	}

	@Override
	public String getID() {
		return "com.jaspersoft.ireport.designer.connection.JRCSVDataSourceConnection";
	}

}
