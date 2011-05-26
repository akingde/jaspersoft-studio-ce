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

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.query.JRXPathQueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRXmlUtils;

import org.w3c.dom.Document;

import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.data.xml.XMLDataAdapter;

public class RemoteXMLDataAdapter extends XMLDataAdapter {
	
	public static final String XML_URL = "XML_URL";

	/**
	 * Create a new instance of RemoteXMLDataAdapter
	 * 
	 */
	public RemoteXMLDataAdapter() {
		super();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void contributeParameters(Map<String, Object> parameters) throws JRException {
		
		if (isUseConnection()) {
			
			System.out.println("Running against: " + getFileName());
	        System.out.flush();
	        if ( getFileName().toLowerCase().startsWith("https://") ||
	             getFileName().toLowerCase().startsWith("http://") ||
	             getFileName().toLowerCase().startsWith("file:")) {
	        	
	        	// JRXPathQueryExecuterFactory.XML_URL not available.
	        	// Once this is available, remove XML_URL from this class.
	        	parameters.put(XML_URL, getFileName());
	        }
	        else {
	        	
	            Document document = JRXmlUtils.parse(new File( getFileName()));
	            parameters.put(JRXPathQueryExecuterFactory.PARAMETER_XML_DATA_DOCUMENT, document);
	        }
	        
	        if (getLocale() != null) {
	        	parameters.put(JRXPathQueryExecuterFactory.XML_LOCALE, getLocale());
	        }
	           
	        if (getTimeZone() != null) {
	        	parameters.put(JRXPathQueryExecuterFactory.XML_TIME_ZONE, getTimeZone());
	        }
	        
	        if (getDatePattern() != null && getDatePattern().trim().length()>0) {
	        	parameters.put(JRXPathQueryExecuterFactory.XML_DATE_PATTERN, getDatePattern());
	        }
	           
	        if (getNumberPattern() != null && getNumberPattern().trim().length()>0) {
	        	parameters.put(JRXPathQueryExecuterFactory.XML_NUMBER_PATTERN, getNumberPattern());
	        }
	    }
	}

	public void test() throws Exception {
		
		URL url = null;
		InputStream is = null;
	    try {
	    	url = new URL(getFileName());
	        if (getFileName().startsWith("file://")) {
	        	is = url.openStream();
	        }
	    }
	    catch (Exception e) {
	    	throw new Exception(e.getMessage());
	    } finally {

	        if (is != null) {
	    	    try { is.close(); } catch (Exception ex){}
	        }
	    }
	}

	public DataAdapterEditor getEditor() {
		return new RemoteXMLDataAdapterEditor();
	}
}
