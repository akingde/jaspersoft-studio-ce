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
package com.jaspersoft.studio.data.jdbc;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.utils.Misc;

/**
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
	
	
//    /**
//     * Load a JDBC driver in the list of drivers managed by the JDBCDataAdapter.
//     * 
//     * @param driverClass
//     * @throws ClassNotFoundException
//     */
//	private static void loadDriver(String driverClass) throws ClassNotFoundException {
//
//            if (driverClass == null) return;
//
//            try {
//                    Driver theDriver = (Driver)Class.forName(driverClass, false, getDriversClassLoader()).newInstance();
//                    if (!isDriverLoaded(theDriver)) {
//                            synchronized (drivers) {
//                                    drivers.add(theDriver);
//                            }
//                    }
//            } catch (Throwable t) {
//                    throw new ClassNotFoundException(driverClass);
//            }
//    }
//
//	/**
//	 * Check if the specified driver is already loaded in the list of drivers or not.
//	 * @param theDriver
//	 * @return
//	 */
//    private static boolean isDriverLoaded(Driver theDriver) {
//		int version = (theDriver.getMajorVersion()*10) + theDriver.getMinorVersion();
//
//		for (Driver driver : drivers) {
//			int thisVersion = (driver.getMajorVersion()*10) + driver.getMinorVersion();
//			if (thisVersion == version && (driver.getClass().getName().equals(theDriver.getClass().getName())))
//				return true;
//		}
//		return false;
//	}
//
//    /**
//     * Find the most appropriate driver for a specific JDBC url.
//     * This check is useful to test if the user is trying to use a wrong Jdbc url with a particular jdbc driver class.
//     * 
//     * @param jdbcURL
//     * @return
//     * @throws SQLException
//     */
//    public static Driver getSuitableDriver(String jdbcURL) throws SQLException {
//
//		for (Driver driver : drivers)
//        {
//			try {
//				if (driver.acceptsURL(jdbcURL)) return driver;
//			} catch (Throwable t) {
//                            // Do nothing....
//			}
//        }
//		return null;
//	}

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
    
    
    public JDBCDataAdapter() {
    	setName("New JDBC Data Adapter");
    }
    

    /** This method returns an instanced java.sql.Connection to the database.
     *  If an exception occurs, the method return null.
     *  
     *  @return a new connction, otherwise it returns null.
     */
    public java.sql.Connection getConnection()  {
    	
    	try {
    		return getConnectionImpl();
    	} catch (Exception ex)
    	{
    		// we need to decide what to do with this exception
    	}
    	return null;
    }
    
    private java.sql.Connection getConnectionImpl() throws Exception {
        
            // Try the java connection...
            try {
                
            		java.sql.Driver driver = null;
            		driver = (java.sql.Driver)(Class.forName( getJDBCDriver(), true, getDriverClassLoader())).newInstance();
                
                    java.util.Properties connectProps = new java.util.Properties();
                    
                    if ((password == null || password.equals("") ) && !isSavePassword())
                    {
                        password = getPassword();
                    }
                    
                    connectProps.setProperty("user", username);
                    connectProps.setProperty("password", password);
                    
                    Connection conn = driver.connect( url, connectProps); 
                    
//                    if ( (this.getJDBCDriver().toLowerCase().indexOf("oracle") >= 0) && (IReportManager.getInstance().getProperty("oracle_language","").trim().length() > 0 ||
//                        IReportManager.getInstance().getProperty("oracle_territory","").trim().length() > 0) )
//                    {
//	                    Statement stmt = null;
//	                    try {
//		                    stmt = conn.createStatement();
//		                    if (IReportManager.getInstance().getProperty("oracle_language","").trim().length() > 0)
//				    stmt.execute("ALTER SESSION SET NLS_LANGUAGE = '" + IReportManager.getInstance().getProperty("oracle_language","").trim() + "'");
//				    if (IReportManager.getInstance().getProperty("oracle_territory","").trim().length() > 0)
//				    stmt.execute("ALTER SESSION SET NLS_TERRITORY='" + IReportManager.getInstance().getProperty("oracle_territory","").trim() + "'");
//				    
//	                    } catch (Exception ex)
//	                    {
//	                    	ex.printStackTrace();
//	        	    }
//	        	    finally {
//		        	    if (stmt != null) stmt.close();
//	        	    }
//                    }
        
                    return conn;
			
            }
//            catch (NoClassDefFoundError ex)
//            {
//                    showErrorMessage(Misc.formatString(  // "messages.connection.noClassDefFoundError",
//                                "{0}\nNoClassDefFoundError!!\nCheck your classpath!\n{1}",
//                                new Object[]{""+ this.getName(), ""+ex.getMessage()}),
//                                "Exception", ex); //"message.title.exception"
//                    
//                    return null;					
//			} 
//			catch (ClassNotFoundException ex)
//			{
//			    showErrorMessage(Misc.formatString( // "messages.connection.classNotFoundError",
//		                            "{0}\nClassNotFoundError!\nMsg: {1}\nPossible not found class: {2}\nCheck your classpath!",
//		                            new Object[]{""+ this.getName(), ""+ex.getMessage(), "" + this.getJDBCDriver()}),
//		                            "Exception", ex);
//				return null;				
//			} 
//			catch (java.sql.SQLException ex)
//			{
//				if (!savePassword) password = null;
//				showErrorMessage(Misc.formatString( // "messages.connection.sqlError",
//		                            "{0}\nSQL problems: {1}\n{2}",
//		                            new Object[]{""+ this.getName(), ""+ex.getMessage(), "" + url}),
//		                            "Exception", ex);
//				return null;					
//			} 
			catch (Exception ex)
			{
//				showErrorMessage(Misc.formatString( // "messages.connection.generalError",
//		                            "{0}\nGeneral problem: {1}\nPlease check your username and password. The DBMS is running?!",
//		                            new Object[]{""+ this.getName(), ""+ex.getMessage()}),
//		                            "Exception", ex);
				
				ex.printStackTrace();
				throw ex;
			}
    }    
   
    
    @Override
    public boolean isJDBCConnection() {
        return true;
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
        
    /**
     * Test this JDBC connection. An exception is thrown if the getConnection method fails.
     * The type of the exception is the original produce inside the getConnectionImpl
     * method.
     */
    @Override
    public void test() throws Exception
    {
        // Try the JDBC connection...
    	Connection conn = null;
        
    	try {
        		conn = getConnectionImpl();
        } finally {
            // Clean up
            if( conn!=null ) try{ conn.close(); } catch(Exception e) { /* anyone really care? */ }
        }
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
