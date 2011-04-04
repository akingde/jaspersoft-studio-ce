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
package com.jaspersoft.studio.data.xls;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRXlsDataSource;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.utils.Misc;

public class XLSDataAdapter extends DataAdapter {

	private boolean useFirstRowAsHeader = false;
    private String customDatePattern = null;
    private String customNumberPattern = null;
    private String fileName;
	private List<String> columnNames = new ArrayList<String>();
	private List<Integer> columnIndexes = new ArrayList<Integer>();
	
	/**
	 * Create a new instance of XMLDataAdapter
	 */
	public XLSDataAdapter() {
	
	}

	@Override
	public boolean isJDBCConnection() {
		return false;
	}

	@Override
	public Connection getConnection() {
		return null;
	}

	@Override
	public Map<String, String> getProperties() {
		Map<String, String> map = super.getProperties();
        map.put("fileName", Misc.nvl(this.getFileName() ,"") );    
        map.put("useFirstRowAsHeader", Misc.nvl("" + this.isUseFirstRowAsHeader(), "") );
        map.put("customDatePattern", Misc.nvl(this.getCustomDatePattern(), "") );
        map.put("customNumberPattern", Misc.nvl(this.getCustomNumberPattern(), "") );
        
        for (int i=0; i < getColumnNames().size(); ++i)
        {
        	map.put("COLUMN_" + i,getColumnNames().get(i));
        }

        for (int i=0; i< getColumnIndexes().size(); ++i)
        {
        	map.put("INDEX_" + i,getColumnIndexes().get(i) + "");
        }
        
        return map;
	}

	@Override
	public void loadProperties(Map<String, String> map) {
		this.setFileName( (String)map.get("fileName") );
        this.setUseFirstRowAsHeader( ((String)map.get("useFirstRowAsHeader")).equals("true") );
        this.setCustomDatePattern( (String)map.get("customDatePattern") );
        this.setCustomNumberPattern( (String)map.get("customNumberPattern") );

        int i = 0;
        List<String> listColumnNames = new ArrayList<String>();
        while (map.containsKey("COLUMN_" + i))
        {
        	listColumnNames.add( (String)map.get("COLUMN_" + i) );
           i++;
        }
        this.setColumnNames(listColumnNames);

        i = 0;
        List<Integer> listColumnIndexes = new ArrayList<Integer>();
        while (map.containsKey("INDEX_" + i))
        {
        	listColumnIndexes.add( new Integer( map.get("INDEX_" + i)+"") );
           i++;
        }
        this.setColumnIndexes(listColumnIndexes);
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
	public JRDataSource getJRDataSourceImpl() throws FileNotFoundException, JRException, IOException {
		
		JRXlsDataSource ds = new JRXlsDataSource(new File(getFileName()));
		
		if (this.getCustomDatePattern() != null && this.getCustomDatePattern().length() > 0)
        {
			ds.setDateFormat(new SimpleDateFormat(this.getCustomDatePattern()));
        }
        if (this.getCustomNumberPattern() != null && this.getCustomNumberPattern().length() > 0)
        {
            ds.setNumberFormat(new DecimalFormat(this.getCustomNumberPattern()));
        }

        ds.setUseFirstRowAsHeader( isUseFirstRowAsHeader());

        if (!isUseFirstRowAsHeader())
        {
            String[] names = new String[getColumnNames().size()];
            int[] indexes = new int[getColumnNames().size()];

            for (int i=0; i< names.length; ++i )
            {
                names[i] = "" + getColumnNames().get(i);
                indexes[i] = (getColumnIndexes().size() > i) ? getColumnIndexes().get(i) : i;
            }
            ds.setColumnNames( names, indexes);
        }

        return ds;
	}
	
	@Override
	public void test() throws Exception {
		
		String csv_file = getFileName();    
        XLSDataAdapter con = new XLSDataAdapter();
        java.io.File f = new java.io.File(csv_file);
        if (!f.exists())
        {
        	throw new Exception("Excel file " + csv_file + " not found.");
        }
            
        con.setFileName( csv_file );
        con.getJRDataSource();
	}
	
	@Override
	public DataAdapterEditor getEditor() {
		return new XLSDataAdapterEditor();
	}

	@Override
	public ImageDescriptor getIcon16() {
		return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/XLSDataAdapterIcon-16.gif");
	}
	
	/*
	 *  GETTERS AND SETTERS
	 */
    public String getCustomDatePattern() {
		return customDatePattern;
	}

	public String getCustomNumberPattern() {
		return customNumberPattern;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String filename) {
		this.fileName = filename;
	}
	
    public boolean isUseFirstRowAsHeader() {
        return useFirstRowAsHeader;
    }
    
    public List<String> getColumnNames() {
        return columnNames;
    }

	public List<Integer> getColumnIndexes() {
        return columnIndexes;
    }
    
    public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
	}

	public void setColumnIndexes(List<Integer> columnIndexes) {
		this.columnIndexes = columnIndexes;
	}

	public void setUseFirstRowAsHeader(boolean useFirstRowAsHeader) {
		this.useFirstRowAsHeader = useFirstRowAsHeader;
	}

	public void setCustomDatePattern(String customDatePattern) {
		this.customDatePattern = customDatePattern;
	}

	public void setCustomNumberPattern(String customNumberPattern) {
		this.customNumberPattern = customNumberPattern;
	}
}
