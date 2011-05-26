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
package com.jaspersoft.studio.data.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import net.sf.jasperreports.engine.query.JRCsvQueryExecuterFactory;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.utils.Misc;

public class CSVDataAdapter extends DataAdapter {
	
	private String fileName;
	private String recordDelimiter = "\n";
	private String fieldDelimiter = ",";
	private boolean useFirstRowAsHeader = false;
    private String customDatePattern = null;
    private boolean qeMode = false;
    private List<String> columnNames = new ArrayList<String>();
    
	@Override
	public Map<String, String> getProperties() {
		
		Map<String, String> map = super.getProperties();
        map.put("fileName", Misc.nvl(this.getFileName(), "") );    
        map.put("recordDelimiter", Misc.nvl(  Misc.addSlashesString(this.getRecordDelimiter()), "\n") );
        map.put("fieldDelimiter", Misc.nvl( Misc.addSlashesString(this.getFieldDelimiter()), "") );
        map.put("useFirstRowAsHeader", Misc.nvl("" + this.isUseFirstRowAsHeader(), "") );
        map.put("customDatePattern", Misc.nvl(this.getCustomDatePattern(), "") );
        map.put("queryExecuterMode", ""+isQeMode() );
        
        for (int i=0; i < getColumnNames().size(); ++i)
        {
        	map.put("COLUMN_" + i,getColumnNames().get(i));
        }
        
        return map;
	}
	
	@Override
	public void loadProperties(Map<String, String> map) {
		
		setFileName( (String)map.get("fileName") );
		
		String recordDelimiter = (String)map.get("recordDelimiter");
		if (recordDelimiter != null) {
			setRecordDelimiter( Misc.removeSlashesString(recordDelimiter) );
		}
		
		String fieldDelimiter = (String)map.get("fieldDelimiter");
		if (fieldDelimiter != null) {
			setFieldDelimiter( Misc.removeSlashesString(fieldDelimiter) );
		}
		
		setUseFirstRowAsHeader( ((String)map.get("useFirstRowAsHeader")).equals("true") );
		
		setCustomDatePattern( (String)map.get("customDatePattern") );
		
		if (map.get("queryExecuterMode") != null) {
			setQeMode( ((String)map.get("queryExecuterMode")).equals("true"));
		}
		
		int i = 0;
        List<String> listColumnNames = new ArrayList<String>();
        while (map.containsKey("COLUMN_" + i))
        {
        	listColumnNames.add( (String)map.get("COLUMN_" + i) );
        	i++;
        }
        setColumnNames(listColumnNames);
	}
	
	@Override
	public void contributeParameters(Map<String, Object> parameters) throws JRException
	{
		if (isQeMode())//FIXME check this
		{	
			System.out.println("Setting parameters for the CSV query executer");
			parameters.put(JRCsvQueryExecuterFactory.CSV_FILE, new File(getFileName()));

			if (getCustomDatePattern() != null && getCustomDatePattern().length() > 0)
            {
            	parameters.put( JRCsvQueryExecuterFactory.CSV_DATE_FORMAT, new SimpleDateFormat(getCustomDatePattern()) );
            }
 
            // This particular setting must be fixed in JR first
            //map.put( JRCsvQueryExecuterFactory.CSV_FIELD_DELIMITER, new java.lang.Character( getFieldDelimiter().charAt(0) ));
			parameters.put( JRCsvQueryExecuterFactory.CSV_RECORD_DELIMITER, getRecordDelimiter());
			parameters.put( JRCsvQueryExecuterFactory.CSV_USE_FIRST_ROW_AS_HEADER, new Boolean(isUseFirstRowAsHeader()));

            if (!isUseFirstRowAsHeader())
            {
                String[] names = new String[getColumnNames().size()];
                for (int i=0; i < names.length; ++i )
                {
                    names[i] = "" + getColumnNames().get(i);
                }
                parameters.put( JRCsvQueryExecuterFactory.CSV_COLUMN_NAMES_ARRAY, names);
            }
        }
		else
		{
			JRCsvDataSource ds = null;
			try
			{
				ds = new JRCsvDataSource(new File(getFileName()));
			}
			catch (FileNotFoundException e)
			{
				throw new JRException(e);
			}
	        
			if (this.getCustomDatePattern() != null && this.getCustomDatePattern().length() > 0)
	        {
				ds.setDateFormat( new SimpleDateFormat(getCustomDatePattern()) );
	        }
	        
	        ds.setFieldDelimiter( getFieldDelimiter().charAt(0) );
	        
	        ds.setRecordDelimiter( getRecordDelimiter() );
	        
	        ds.setUseFirstRowAsHeader( isUseFirstRowAsHeader() );
	        
	        if (!isUseFirstRowAsHeader())
	        {
	            String[] names = new String[getColumnNames().size()];
	            for (int i=0; i< names.length; ++i )
	            {
	            	names[i] = "" + getColumnNames().get(i);
	            }
	            ds.setColumnNames( names );
	        }
	        
            parameters.put(JRParameter.REPORT_DATA_SOURCE, ds);
		}
	}
	
	@Override
	public DataAdapterEditor getEditor() {
		return new CSVDataAdapterEditor();
	}

	@Override
	public ImageDescriptor getIcon16() {
		//return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/XLSDataAdapterIcon-16.gif");
		return super.getIcon16();
	}
	
	/*
	 * GETTERS AND SETTERS
	 */
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean isUseFirstRowAsHeader() {
		return useFirstRowAsHeader;
	}

	public void setUseFirstRowAsHeader(boolean useFirstRowAsHeader) {
		this.useFirstRowAsHeader = useFirstRowAsHeader;
	}
	
	public String getRecordDelimiter() {
		return recordDelimiter;
	}

	public void setRecordDelimiter(String recordDelimiter) {
		this.recordDelimiter = recordDelimiter;
	}
	
	public String getFieldDelimiter() {
		return fieldDelimiter;
	}

	public void setFieldDelimiter(String fieldDelimiter) {
		this.fieldDelimiter = fieldDelimiter;
	}

	public String getCustomDatePattern() {
		return customDatePattern;
	}

	public void setCustomDatePattern(String customDatePattern) {
		this.customDatePattern = customDatePattern;
	}

	public boolean isQeMode() {
		return qeMode;
	}

	public void setQeMode(boolean qeMode) {
		this.qeMode = qeMode;
	}

	public List<String> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
	}
}
