/*******************************************************************************
 * Copyright (C) 2005 - 2016 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.data.jdbc;

import java.util.ArrayList;
import java.util.List;

/**
 * Container for the JDBC driver definitions provided by the Community Edition of Jaspersoft Studio.
 *  
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class CommunityJDBCDriverDefinitions implements JDBCDriverDefinitionsContainer {
	
	private static final List<JDBCDriverDefinition> driverDefinitions;
	
	static {
		driverDefinitions = new ArrayList<JDBCDriverDefinition>();
		driverDefinitions.add(new JDBCDriverDefinition("HSQLDB (server)", //$NON-NLS-1$
				"org.hsqldb.jdbcDriver", "jdbc:hsqldb:hsql://{0}")); //$NON-NLS-1$ //$NON-NLS-2$
		driverDefinitions.add(new JDBCDriverDefinition("HSQLDB (file)", "org.hsqldb.jdbcDriver", //$NON-NLS-1$ //$NON-NLS-2$
						"jdbc:hsqldb:[PATH_TO_DB_FILES]/{1}")); //$NON-NLS-1$
		driverDefinitions.add(new JDBCDriverDefinition("H2 (file)", "org.h2.Driver", //$NON-NLS-1$ //$NON-NLS-2$
						"jdbc:h2:file:{1}")); //$NON-NLS-1$
		driverDefinitions.add(new JDBCDriverDefinition("H2 (tco)", "org.h2.Driver", //$NON-NLS-1$ //$NON-NLS-2$
						"jdbc:h2:tcp://{0}:9101/~/{1}"));//$NON-NLS-1$
		driverDefinitions.add(new JDBCDriverDefinition("SQLite (file)", "org.sqlite.JDBC", //$NON-NLS-1$ //$NON-NLS-2$
						"jdbc:sqlite:[PATH_TO_DB_FILES]/{1}")); //$NON-NLS-1$
		driverDefinitions.add(new JDBCDriverDefinition("DerbyDB (Embedded)", "org.apache.derby.jdbc.EmbeddedDriver", //$NON-NLS-1$ //$NON-NLS-2$
						"jdbc:derby:/{1}"));//$NON-NLS-1$	
		driverDefinitions.add(new JDBCDriverDefinition("DerbyDB (Client)", "org.apache.derby.jdbc.ClientDriver", //$NON-NLS-1$ //$NON-NLS-2$
						"jdbc:derby://{0}[:1527]/{1}"));//$NON-NLS-1$	
		driverDefinitions.add(new JDBCDriverDefinition("Cloudscape", "COM.cloudscape.JDBCDriver", //$NON-NLS-1$ //$NON-NLS-2$
						"jdbc:cloudscape:/{1}")); //$NON-NLS-1$
		driverDefinitions.add(new JDBCDriverDefinition("Firebird", "org.firebirdsql.jdbc.FBDriver", //$NON-NLS-1$ //$NON-NLS-2$
								"jdbc:firebirdsql://{0}:3050/{1}")); //$NON-NLS-1$
		driverDefinitions.add(new JDBCDriverDefinition("IBM DB2 (Type 4)", //$NON-NLS-1$
						"com.ibm.db2.jcc.DB2Driver", "jdbc:db2://{0}/{1}")); //$NON-NLS-1$ //$NON-NLS-2$
		driverDefinitions.add(new JDBCDriverDefinition("IBM DB2 (IBM Cloudscape® server)", //$NON-NLS-1$
						"com.ibm.db2.jcc.DB2Driver", "jdbc:db2j:net://{0}/{1}")); //$NON-NLS-1$ //$NON-NLS-2$
		driverDefinitions.add(new JDBCDriverDefinition("IBM DB2 (IDS data source)", //$NON-NLS-1$
						"com.ibm.db2.jcc.DB2Driver", "jdbc:ids://{0}/{1}")); //$NON-NLS-1$ //$NON-NLS-2$
		driverDefinitions.add(new JDBCDriverDefinition("inetdae7", "com.inet.tds.TdsDriver", //$NON-NLS-1$ //$NON-NLS-2$
						"jdbc:inetdae7:{0}:1433/{1}")); //$NON-NLS-1$
		driverDefinitions.add(new JDBCDriverDefinition("Informix", "com.informix.jdbc.IfxDriver", //$NON-NLS-1$ //$NON-NLS-2$
						"jdbc:informix-sqli://{0}:informixserver={1}")); //$NON-NLS-1$
		driverDefinitions.add(new JDBCDriverDefinition("Ingres", "com.ingres.jdbc.IngresDriver", //$NON-NLS-1$ //$NON-NLS-2$
						"jdbc:ingres://{0}:II7/{1}")); //$NON-NLS-1$
		driverDefinitions.add(new JDBCDriverDefinition("JDBC-ODBC Bridge", //$NON-NLS-1$
						"sun.jdbc.odbc.JdbcOdbcDriver", "jdbc:odbc:{1}", "DSNAME")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		driverDefinitions.add(new JDBCDriverDefinition("JDBC-ODBC Bridge", //$NON-NLS-1$
						"com.ms.jdbc.odbc.JdbcOdbcDriver", "jdbc:odbc:{1}", //$NON-NLS-1$ //$NON-NLS-2$
						"DSNAME")); //$NON-NLS-1$
		driverDefinitions.add(new JDBCDriverDefinition("MS SQLServer (2000)", //$NON-NLS-1$
						"com.microsoft.jdbc.sqlserver.SQLServerDriver", //$NON-NLS-1$
						"jdbc:microsoft:sqlserver://{0}:1433;DatabaseName={1}")); //$NON-NLS-1$
		driverDefinitions.add(new JDBCDriverDefinition("MS SQLServer (2005-2012)", //$NON-NLS-1$
						"com.microsoft.sqlserver.jdbc.SQLServerDriver", //$NON-NLS-1$
						"jdbc:sqlserver://{0}:1433;databaseName={1}")); //$NON-NLS-1$
		driverDefinitions.add(new JDBCDriverDefinition("MS SQLServer", //$NON-NLS-1$
						"net.sourceforge.jtds.jdbc.Driver", //$NON-NLS-1$
						"jdbc:jtds:sqlserver://{0}/{1}")); //$NON-NLS-1$
		driverDefinitions.add(new JDBCDriverDefinition("MS SQLServer", //$NON-NLS-1$
						"com.merant.datadirect.jdbc.sqlserver.SQLServerDriver", //$NON-NLS-1$
						"jdbc:sqlserver://{0}:1433/{1}")); //$NON-NLS-1$
		driverDefinitions.add(new JDBCDriverDefinition("MS SQLServer", //$NON-NLS-1$
						"com.internetcds.jdbc.tds.Driver", //$NON-NLS-1$
						"jdbc:freetds:sqlserver://{0}/{1}")); //$NON-NLS-1$
		driverDefinitions.add(new JDBCDriverDefinition("MySQL", "org.gjt.mm.mysql.Driver", //$NON-NLS-1$ //$NON-NLS-2$
						"jdbc:mysql://{0}/{1}")); //$NON-NLS-1$
		driverDefinitions.add(new JDBCDriverDefinition("MySQL", "com.mysql.jdbc.Driver", //$NON-NLS-1$ //$NON-NLS-2$
						"jdbc:mysql://{0}/{1}")); //$NON-NLS-1$
		driverDefinitions.add(new JDBCDriverDefinition("MariaDB", "org.mariadb.jdbc.Driver", //$NON-NLS-1$ //$NON-NLS-2$
						"jdbc:mariadb://{0}:3306/{1}")); //$NON-NLS-1$
		driverDefinitions.add(new JDBCDriverDefinition("Oracle", //$NON-NLS-1$
						"oracle.jdbc.driver.OracleDriver", //$NON-NLS-1$
						"jdbc:oracle:thin:@{0}:1521:{1}")); //$NON-NLS-1$
		driverDefinitions.add(new JDBCDriverDefinition("PostgreSQL", "org.postgresql.Driver", //$NON-NLS-1$ //$NON-NLS-2$
						"jdbc:postgresql://{0}:5432/{1}")); //$NON-NLS-1$
		driverDefinitions.add(new JDBCDriverDefinition("Sybase", //$NON-NLS-1$
						"net.sourceforge.jtds.jdbc.Driver", //$NON-NLS-1$
						"jdbc:jtds:sybase://{0}/{1}")); //$NON-NLS-1$		
		driverDefinitions.add(new JDBCDriverDefinition("Sybase", //$NON-NLS-1$
						"com.sybase.jdbc4.jdbc.SybDriver", //$NON-NLS-1$
						"jdbc:sybase:Tds:{0}:2638/{1}")); //$NON-NLS-1$
		driverDefinitions.add(new JDBCDriverDefinition("Hadoop Hive", "org.apache.hadoop.hive.jdbc.HiveDriver", //$NON-NLS-1$ //$NON-NLS-2$
						"jdbc:hive://{0}:10000/default")); //$NON-NLS-1$
		driverDefinitions.add(new JDBCDriverDefinition("Vertica", "com.vertica.Driver", //$NON-NLS-1$ //$NON-NLS-2$
						"jdbc:vertica://{0}:5433/{1}")); //$NON-NLS-1$
		driverDefinitions.add(new JDBCDriverDefinition("Vertica", "com.vertica.jdbc.Driver", //$NON-NLS-1$ //$NON-NLS-2$
						"jdbc:vertica://{0}:5433/{1}")); //$NON-NLS-1$ 
		driverDefinitions.add(new JDBCDriverDefinition("Mondrian", "mondrian.olap4j.MondrianOlap4jDriver", //$NON-NLS-1$ //$NON-NLS-2$
						"jdbc:mondrian:")); //$NON-NLS-1$ 
		driverDefinitions.add(new JDBCDriverDefinition("OLAP4J", "org.olap4j.driver.xmla.XmlaOlap4jDriver", //$NON-NLS-1$ //$NON-NLS-2$
						"jdbc:xmla:")); //$NON-NLS-1$
	}

	@Override
	public List<JDBCDriverDefinition> getJDBCDriverDefinitions() {
		return driverDefinitions;
	}

}
