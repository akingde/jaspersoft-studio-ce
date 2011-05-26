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
package com.jaspersoft.studio.data.jdbc;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.utils.Misc;
/*
 * @author gtoffoli
 *
 */
public class JDBCDataAdapter extends DataAdapter {

	@Override
	public DataAdapterEditor getEditor() {
		
		return new JDBCDataAdapterEditor();
	}

	/**
	 * This is a list of loaded drivers.
	 * 
	 */
	private static final List<Driver> drivers = new ArrayList<Driver>();
	
	/**
	 * This classloader is used to load JDBC drivers available in the set of
	 * paths provided by classpathPaths.
	 */
	private ClassLoader driverClassLoader = null;
	
	/**
	 * Same as getDriversClassLoader(false) 
	 * @return
	 */
	public ClassLoader getDriverClassLoader()
	{
		return getDriverClassLoader(false);
	}
	
	/**
	 * Return the classloader, an URLClassLoader made up with all the paths
	 * defined to look for Drivers (mainly jars).
	 * 
	 * @param reload - if true, it forces a classloader rebuilt with the set of paths in classpathPaths.
	 * @return
	 */
	public ClassLoader getDriverClassLoader(boolean reload)
	{
		if (driverClassLoader == null || reload)
		{
			List<URL> urls = new ArrayList<URL>();
			for (String cp : getClasspathPaths())
			{
				File f = new File(cp);
				if (f.exists())
				{
					try {
						urls.add( f.toURI().toURL());
					} catch (MalformedURLException e) {
						//e.printStackTrace();
						// We don't care if the entry cannot be found.
					}
				}
			}
			
			driverClassLoader = new URLClassLoader( urls.toArray(new URL[urls.size()]) );
		}
		
		return driverClassLoader;
	}
	
	/**
	 * The set of paths that compose the classpath to look for JDBC drivers.
	 * Actually this path should be extended with a better classloader
	 * which can include other paths provided by JSS core.
	 */
	private List<String> classpathPaths = new ArrayList<String>();
	
	
    private String jdbcDriver;
    
    private String username;
    
    private String password = null;
    
    private String url;
    
    private String database;
    
    private boolean savePassword;
    
    /**
     * Holds value of property serverAddress.
     */
    private String serverAddress;
    
    /** Creates a new instance of JDBCConnection */
    
    
    private Map<String, Object> parameters;

    public JDBCDataAdapter() {
    	setName("New JDBC Data Adapter");
    }
    

	@Override
	public void contributeParameters(Map<String, Object> parameters) throws JRException
	{
        Connection conn = null; 

        try {
    		Driver driver = null;
    		driver = (Driver)(Class.forName( getJDBCDriver(), true, getDriverClassLoader())).newInstance();
        
            Properties connectProps = new Properties();
            
            if ((password == null || password.equals("") ) && !isSavePassword())
            {
                password = getPassword();
            }
            
            connectProps.setProperty("user", username);
            connectProps.setProperty("password", password);
            
            conn = driver.connect( url, connectProps); 
        }
		catch (Exception ex)
		{ 
			throw new JRException(ex);
		}

		parameters.put(JRParameter.REPORT_CONNECTION, conn);
	}
	
    /** Getter for property database.
     * @return Value of property database.
     *
     */
    public java.lang.String getDatabase() {
        return database;
    }
    
    /** Setter for property database.
     * @param database New value of property database.
     *
     */
    public void setDatabase(java.lang.String database) {
        this.database = database;
    }
    
    /** Getter for property JDBCDriver.
     * @return Value of property JDBCDriver.
     *
     */
    public java.lang.String getJDBCDriver() {
        return jdbcDriver;
    }
    
    /** Setter for property JDBCDriver.
     * @param JDBCDriver New value of property JDBCDriver.
     *
     */
    public void setJDBCDriver(java.lang.String JDBCDriver) {
        this.jdbcDriver = JDBCDriver;
    }
    
    /** Getter for property password.
     * 
     * If the value is not set, this method popups a dialog to ask for a password.
     * 
     * @return Value of property password.
     *
     */
    public java.lang.String getPassword() {
        
        if (isSavePassword()) return password;
        else
        {
            // Ask for password...
            try {
                return null; //PasswordDialog.askPassword();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return "";
    }
    
    /** Setter for property password.
     * @param password New value of property password.
     *
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }
    
    /** Getter for property savePassword.
     * @return Value of property savePassword.
     *
     */
    public boolean isSavePassword() {
        return savePassword;
    }
    
    /** Setter for property savePassword.
     * @param savePassword New value of property savePassword.
     *
     */
    public void setSavePassword(boolean savePassword) {
        this.savePassword = savePassword;
    }
    
    /** Getter for property url.
     * @return Value of property url.
     *
     */
    public java.lang.String getUrl() {
        return url;
    }
    
    /** Setter for property url.
     * @param url New value of property url.
     *
     */
    public void setUrl(java.lang.String url) {
        this.url = url;
    }
    
    /** Getter for property username.
     * @return Value of property username.
     *
     */
    public java.lang.String getUsername() {
        return username;
    }
    
    /** Setter for property username.
     * @param username New value of property username.
     *
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }
	
	@Override
	public ImageDescriptor getIcon16() {
		return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/JDBCDataAdapterIcon-16.gif");
	}
    
    /*
     *  This method returns all the properties used by this connection
     */
    @Override
    public java.util.Map<String,String> getProperties()
    {    
        java.util.Map<String,String> map = new java.util.HashMap<String,String>();
        map.put("JDBCDriver", Misc.nvl(this.getJDBCDriver(),"") );
        map.put("Url", Misc.nvl(this.getUrl(),""));
        map.put("Database", Misc.nvl(this.getDatabase(),""));
        map.put("Username", Misc.nvl(this.getUsername(),""));
        if (this.isSavePassword())
            map.put("Password", Misc.nvl(this.getPassword(),""));
        else map.put("Password","");
        map.put("SavePassword", ""+this.isSavePassword());
        map.put("ServerAddress", Misc.nvl(this.getServerAddress(),"") );
        
        // Build the classpath using the local path separator char...
        StringBuffer paths = new StringBuffer();
        for (String path : getClasspathPaths())
        {
        	if (paths.length() > 0) paths.append(File.pathSeparator);
        	paths.append(path);
        }
        map.put("classPaths", paths.toString() );
        
        return map;
    }
    
    @Override
    public void loadProperties(java.util.Map<String,String> map)
    {
        this.setJDBCDriver( (String)map.get("JDBCDriver"));
        this.setUrl( (String)map.get("Url"));
        this.setDatabase( (String)map.get("Database"));
        this.setUsername( (String)map.get("Username"));
        this.setSavePassword(  (""+map.get("SavePassword")).equals("true") );
        if (this.isSavePassword())
            this.setPassword( Misc.nvl((String)map.get("Password"),""));
        this.setServerAddress( Misc.nvl((String)map.get("ServerAddress"),"") );  
        
        List<String> paths = new ArrayList<String>();
        paths.addAll( Arrays.asList(Misc.nvl((String)map.get("classPaths"),"").split(File.pathSeparator)));
        paths.remove(""); // remove empty paths...
        this.setClasspathPaths(paths);
    }
    
    /**
     * Getter for property serverAddress.
     * @return Value of property serverAddress.
     */
    public String getServerAddress() {
        return this.serverAddress;
    }
    
    /**
     * Setter for property serverAddress.
     * @param serverAddress New value of property serverAddress.
     */
    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }
        
    public String getQueryLanguage() {
        return "SQL";
    }

	/**
	 * @param classpathPaths the classpathPaths to set
	 */
	public void setClasspathPaths(List<String> classpathPaths) {
		this.classpathPaths = classpathPaths;
		driverClassLoader = null;
	}

	/**
	 * @return the classpathPaths
	 */
	public List<String> getClasspathPaths() {
		return classpathPaths;
	}

    /*
    public List<JRDesignField> readFields(String query) throws Exception {
        
        SQLFieldsProvider provider = new SQLFieldsProvider();
        List<JRDesignField> result = new ArrayList<JRDesignField>();
        JRDesignDataset dataset = new JRDesignDataset(true);
        JRDesignQuery dquery = new JRDesignQuery();
        dquery.setLanguage("SQL");
        dquery.setText(query);
        dataset.setQuery(dquery);
        JRField[] fields = provider.getFields(this, dataset, new HashMap());
        for (int i=0; i<fields.length; ++i)
        {
            result.add((JRDesignField)fields[i]);
        }
        
        return result;
    }
    
    
    public boolean supportsDesign() {
        return true;
    }

    public String designQuery(String query) {
        
        try {
            SQLFieldsProvider provider = new SQLFieldsProvider();
            return provider.designQuery(this, query, null);
        } catch (Exception ex)
        {
            return query;
        }
    }
    */
}
