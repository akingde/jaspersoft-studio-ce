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
package com.jaspersoft.studio.data.xml;

import java.io.File;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.query.JRXPathQueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRXmlUtils;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.w3c.dom.Document;

import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.utils.Misc;

public class XMLDataAdapter extends DataAdapter {

	private String fileName;
	private String selectExpression;
	private boolean useConnection = false;
	private Locale locale = null;
	private String datePattern = null;
	private String numberPattern = null;
	private TimeZone timeZone = null;

	/**
	 * Create a new instance of XMLDataAdapter
	 * 
	 */
	public XMLDataAdapter() {
	}
	
	@Override
	public Map<String, String> getProperties() {
		
		Map<String, String> map = super.getProperties();
		map.put("Filename", Misc.nvl(getFileName(), ""));
		map.put("SelectExpression", Misc.nvl(getSelectExpression(), ""));
		map.put("UseConnection", Misc.nvl("" + isUseConnection(), "false"));
		
		if (getLocale() != null) {
			map.put("Locale_language", Misc.nvl(getLocale().getLanguage(), ""));
			map.put("Locale_country", Misc.nvl(getLocale().getCountry(), ""));
			map.put("Locale_variant", Misc.nvl(getLocale().getVariant(), ""));
		}
		
		map.put("DatePattern", Misc.nvl(getDatePattern(), ""));
		map.put("NumberPattern", Misc.nvl(getNumberPattern(), ""));
		
		if (getTimeZone() != null) {
			map.put("timeZone", Misc.nvl(getTimeZone().getID(), ""));
		}
		
		return map;
	}

	@Override
	public void loadProperties(Map<String, String> map) {
		
		setFileName( (String)map.get("Filename") );
		setSelectExpression( (String)map.get("SelectExpression") );
		setUseConnection( Boolean.valueOf(Misc.nvl(map.get("UseConnection"), "false")).booleanValue() );
		
		String language = (String)map.get("Locale_language");
		String country = (String)map.get("Locale_country");
		String variant = (String)map.get("Locale_variant");
		if (language != null && language.trim().length() > 0) {
			if (country != null && language.trim().length() > 0) {
				if (variant != null && variant.trim().length() > 0) {
					setLocale( new Locale(language, country, variant) );
				} else {
					setLocale( new Locale(language, country) );
				}
			} else {
				setLocale( new Locale(language) );
			}
		}
		
		String datePatternValue = (String)map.get("DatePattern");
		if (datePatternValue != null && datePatternValue.trim().length() >0) {
			setDatePattern(datePatternValue);
		}
		
		String numberPatternvalue = (String)map.get("NumberPattern");
		if (numberPatternvalue != null && numberPatternvalue.trim().length() > 0) {
			setNumberPattern(numberPatternvalue);
		}
		
		String timeZoneId = (String)map.get("timeZone");
		if (timeZoneId != null && timeZoneId.trim().length() > 0) {
			setTimeZone( TimeZone.getTimeZone(timeZoneId) );
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void contributeParameters(Map<String, Object> parameters) throws JRException
	{
		if (isUseConnection()) {
            
			/*
            if (this.getFilename().toLowerCase().startsWith("https://") ||
                this.getFilename().toLowerCase().startsWith("http://") ||
                this.getFilename().toLowerCase().startsWith("file:"))
            {
                map.put(JRXPathQueryExecuterFactory.XML_URL, this.getFilename());
            }
            else
            {
            */
                Document document = JRXmlUtils.parse( new File(getFileName()) );
                parameters.put(JRXPathQueryExecuterFactory.PARAMETER_XML_DATA_DOCUMENT, document);
            //}
            
            
            if (getLocale() != null) {
            	parameters.put(JRXPathQueryExecuterFactory.XML_LOCALE, getLocale());
            }
           
            if (getTimeZone() != null) {
            	parameters.put(JRXPathQueryExecuterFactory.XML_TIME_ZONE, getTimeZone());
            }
           
            if (getDatePattern() != null && getDatePattern().trim().length() > 0) {
            	parameters.put(JRXPathQueryExecuterFactory.XML_DATE_PATTERN, getDatePattern());
            }
           
            if (getNumberPattern() != null && getNumberPattern().trim().length() > 0) {
            	parameters.put(JRXPathQueryExecuterFactory.XML_NUMBER_PATTERN, getNumberPattern());
            }    
        }
		else
		{
			JRXmlDataSource ds = new JRXmlDataSource(fileName, getSelectExpression() ); 
	           
	        if (getLocale()!=null) {
	        	ds.setLocale( getLocale());
	        }
	           
	        if (getTimeZone() != null) {
	        	ds.setTimeZone( getTimeZone());
	        }
	        
	        if (getDatePattern() != null && getDatePattern().trim().length()>0) {
	        	ds.setDatePattern( getDatePattern());
	        }
	           
	        if (getNumberPattern() != null && getNumberPattern().trim().length()>0) {
	        	ds.setNumberPattern( getNumberPattern());
	        }
	           
        	parameters.put(JRParameter.REPORT_DATA_SOURCE, ds);
		}
	}

	@Override
	public DataAdapterEditor getEditor() {
		return new XMLDataAdapterEditor();
	}

	@Override
	public ImageDescriptor getIcon16() {
		return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/XMLDataAdapterIcon-16.gif");
	}

	// GETTERS AND SETTERS
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getSelectExpression() {
		return selectExpression;
	}

	public void setSelectExpression(String selectExpression) {
		this.selectExpression = selectExpression;
	}
	
	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getDatePattern() {
		return datePattern;
	}

	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}

	public String getNumberPattern() {
		return numberPattern;
	}

	public void setNumberPattern(String numberPattern) {
		this.numberPattern = numberPattern;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	public boolean isUseConnection() {
		return useConnection;
	}
	
	public void setUseConnection(boolean useConnection) {
        this.useConnection = useConnection;
    }
}
