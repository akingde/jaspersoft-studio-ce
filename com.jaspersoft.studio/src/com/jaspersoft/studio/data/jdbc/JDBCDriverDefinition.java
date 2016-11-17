/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.jdbc;

import java.text.MessageFormat;

/**
 * Definition for a generic JDBC driver, usually proposed in the JDBC data adapter dialog.
 */
public class JDBCDriverDefinition implements Comparable<JDBCDriverDefinition> {
    
	private String urlPattern = "";
    private String dbName = "";
    private String driverName = "";
    private String defaultDBName = "MYDATABASE";
    private String defaultServer = "localhost";

	public JDBCDriverDefinition(String dbName, String driverName, String urlPattern) {
		this(dbName, driverName, urlPattern, "MYDATABASE", "localhost");
	}
		
	public JDBCDriverDefinition(String dbName, String driverName, String urlPattern, String defaultDBName) {
		this(dbName, driverName, urlPattern, defaultDBName, "localhost");
	}

	public JDBCDriverDefinition(String dbName, String driverName, String urlPattern, String defaultDBName,
			String defaultServer) {
		this.dbName = dbName;
		this.driverName = driverName;
		this.urlPattern = urlPattern;
		this.defaultDBName = defaultDBName;
		this.defaultServer = defaultServer;
	}

	/**
	 * Returns the driver URL given the server and database information
	 * 
	 * @param server the server address
	 * @param database the database name
	 * @return the driver URL
	 */
	public String getUrl(String server, String database) {
		if (database == null || database.trim().length() == 0) {
			database = getDefaultDBName();
		}
		database = database.trim();

		if (server == null || server.trim().length() == 0) {
			server = getDefaultServer();
		}
		server = server.trim();
		return MessageFormat.format(getUrlPattern(), new Object[] { server, database });
	}

	/**
	 * Checks if the driver is availble in the specified ClassLoader.
	 * 
	 * @param cl the classloader
	 * @return <code>true</code> if the driver is available, <code>false</code> otherwise
	 */
	public boolean isAvailable(ClassLoader cl) {
		try {
			Class.forName(getDriverName(), false, cl);
		} catch (ClassNotFoundException ex) {
			return false;
		} catch (Throwable ex) {
			return false;
		}
		return true;
	}

	/**
	 * @return the URL pattern for the driver
	 */
	public String getUrlPattern() {
		return urlPattern;
	}

	/**
	 * Sets the URL pattern for the driver definition.
	 * 
	 * @param urlPattern the urlPattern to set
	 */
	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}

	/**
	 * @return the database name
	 */
	public String getDbName() {
		return dbName;
	}

	/**
	 * Sets the database name for the driver definition.
	 * 
	 * @param dbName the database name to set
	 */
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	/**
	 * @return the driver name
	 */
	public String getDriverName() {
		return driverName;
	}

	/**
	 * Sets the name for the driver definition.
	 * 
	 * @param driverName the driver name
	 */
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	/**
	 * @return the default database name
	 */
	public String getDefaultDBName() {
		return defaultDBName;
	}

	/**
	 * Sets the default database name for the driver definition.
	 * 
	 * @param defaultDBName the default database name
	 */
	public void setDefaultDBName(String defaultDBName) {
		this.defaultDBName = defaultDBName;
	}

	/**
	 * @return the default server
	 */
	public String getDefaultServer() {
		return defaultServer;
	}

	@Override
	public String toString() {
		return dbName + " (" + driverName + ")";
	}

	@Override
	public int compareTo(JDBCDriverDefinition o) {
		return (o + "").compareTo(toString());
	}

}
