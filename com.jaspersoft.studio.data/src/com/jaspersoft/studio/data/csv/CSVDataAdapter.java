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
package com.jaspersoft.studio.data.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import net.sf.jasperreports.engine.query.JRCsvQueryExecuterFactory;

import com.jaspersoft.studio.data.DataAdapter;
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
	public boolean isJDBCConnection() {
		return false;
	}
	
	@Override
	public boolean isJRDataSource() {
		if (isQeMode()) {
			return false;
		}
		return true;
	}
	
	@Override
	public Connection getConnection() {
		return null;
	}
	
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
	public JRDataSource getJRDataSource() {
		
		try {
			return getJRDataSourceImpl();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found exception: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
		catch (JRException e) {
			System.out.println("JRException: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
		catch (IOException e) {
			System.out.println("IOException: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return super.getJRDataSource();
	}
	
	/**
     * Real getJRDataSource implementation
	 * @throws IOException 
	 * @throws JRException 
	 * @throws FileNotFoundException 
     */
	public JRDataSource getJRDataSourceImpl() throws FileNotFoundException, IOException, JRException {
		
		JRCsvDataSource ds = new JRCsvDataSource(new File(getFileName()));
        
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
        
        return ds;
	}
	
	@Override
	public Map<Object, Object> getSpecialParameters(Map map) throws JRException {
		
		if (isQeMode())
		{	
			System.out.println("Setting parameters for the CSV query executer");
            map.put( JRCsvQueryExecuterFactory.CSV_FILE, new File(getFileName()) );
            if (getCustomDatePattern() != null && getCustomDatePattern().length() > 0)
            {
                map.put( JRCsvQueryExecuterFactory.CSV_DATE_FORMAT, new SimpleDateFormat(getCustomDatePattern()) );
            }
 
            // This particular setting must be fixed in JR first
            //map.put( JRCsvQueryExecuterFactory.CSV_FIELD_DELIMITER, new java.lang.Character( getFieldDelimiter().charAt(0) ));
            map.put( JRCsvQueryExecuterFactory.CSV_RECORD_DELIMITER, getRecordDelimiter());
            map.put( JRCsvQueryExecuterFactory.CSV_USE_FIRST_ROW_AS_HEADER, new Boolean(isUseFirstRowAsHeader()));

            if (!isUseFirstRowAsHeader())
            {
                String[] names = new String[getColumnNames().size()];
                for (int i=0; i < names.length; ++i )
                {
                    names[i] = "" + getColumnNames().get(i);
                }
                map.put( JRCsvQueryExecuterFactory.CSV_COLUMN_NAMES_ARRAY, names);
            }
		}
		
		return map;
	}
	
	@Override
	public void test() throws Exception {
		
		String csv_file = getFileName();
        CSVDataAdapter con = new CSVDataAdapter();
        java.io.File f = new java.io.File(csv_file);
        
        if ( !f.exists() )
        {
        	throw new Exception("CSV file " + csv_file + " not found.");
        }
        
        con.setFileName( csv_file );
        con.getJRDataSource();
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
