/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2013 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.data.internal.mondrian;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRValueParameter;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.query.JRAbstractQueryExecuter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.olap4j.CellSet;
import org.olap4j.OlapConnection;
import org.olap4j.OlapException;
import org.olap4j.OlapStatement;
import org.olap4j.layout.CellSetFormatter;
import org.olap4j.layout.RectangularCellSetFormatter;

import com.jaspersoft.studio.data.internal.olap.JROlap4jQueryExecuterFactory;
import com.jaspersoft.studio.data.internal.olap.JROlapResult;


/**
 * @author swood
 * @version $Id: JRMondrianQueryExecuter.java 5879 2013-01-07 20:35:36Z teodord $
 */
public class JROlap4jMondrianQueryExecuter extends JRAbstractQueryExecuter
{
	private static final Log log = LogFactory.getLog(JROlap4jMondrianQueryExecuter.class);

	/*
	 * Class.forName("mondrian.olap4j.MondrianOlap4jDriver");
	 * Connection connection =
2: DriverManager.getConnection(
3: "jdbc:mondrian:" +
4: "JdbcDrivers=sun.jdbc.odbc.JdbcOdbcDriver;" +
5: "Jdbc=jdbc:odbc:MondrianFoodMart;" +
6: "Catalog=file:/mondrian/demo/FoodMart.xml;");
	 */
	public static final String OLAP4J_DRIVER = "olap4jDriver";
	public static final String OLAP4J_URL_PREFIX = "urlPrefix";
	public static final String OLAP4J_JDBC_DRIVERS = "JdbcDrivers";
	public static final String OLAP4J_JDBC_URL = "Jdbc";
	public static final String OLAP4J_JDBC_CATALOG = "Catalog";

	public static final String OLAP4J_USER = "user";
	public static final String OLAP4J_PASSWORD = "password";

	public static final String OLAP4J_MONDRIAN_DRIVER_CLASS = "mondrian.olap4j.MondrianOlap4jDriver";
	public static final String OLAP4J_MONDRIAN_URL_PREFIX = "jdbc:mondrian:";

	Connection rConnection;
	JRMondrianResult monResult;
	
	/**
	 * 
	 */
	public JROlap4jMondrianQueryExecuter(
		JasperReportsContext jasperReportsContext, 
		JRDataset dataset, 
		Map<String,? extends JRValueParameter> parametersMap
		)
	{
		super(jasperReportsContext, dataset, parametersMap);
		
		parseQuery();
	}

	/**
	 * @deprecated Replaced by {@link #JRMondrianQueryExecuter(JasperReportsContext, JRDataset, Map)}.
	 */
	public JROlap4jMondrianQueryExecuter(JRDataset dataset, Map<String,? extends JRValueParameter> parametersMap)
	{
		this(DefaultJasperReportsContext.getInstance(), dataset, parametersMap);
	}

	@Override
	protected String getCanonicalQueryLanguage()
	{
		return JROlap4jQueryExecuterFactory.CANONICAL_LANGUAGE;
	}

	protected String getParameterReplacement(String parameterName)
	{
		return String.valueOf(getParameterValue(parameterName));
	}

	public JRDataSource createDatasource() throws JRException
	{
		JRDataSource dataSource = null;
		
        Properties connectProps = new Properties();
        connectProps.put(OLAP4J_JDBC_DRIVERS, getParameterValue(JROlap4jMondrianQueryExecuterFactory.PARAMETER_JDBC_DRIVERS));
        connectProps.put(OLAP4J_JDBC_URL, getParameterValue(JROlap4jMondrianQueryExecuterFactory.PARAMETER_JDBC_URL));
        connectProps.put(OLAP4J_JDBC_CATALOG, getParameterValue(JROlap4jMondrianQueryExecuterFactory.PARAMETER_CATALOG));
        //connectProps.put(OLAP4J_USER, getParameterValue(JRXmlaQueryExecuterFactory.PARAMETER_XMLA_USER));
        //connectProps.put(OLAP4J_PASSWORD, getParameterValue(JRXmlaQueryExecuterFactory.PARAMETER_XMLA_PASSWORD));
        connectProps.put(OLAP4J_DRIVER, OLAP4J_MONDRIAN_DRIVER_CLASS);
        connectProps.put(OLAP4J_URL_PREFIX, OLAP4J_MONDRIAN_URL_PREFIX);
        
        // load driver  and Connection
        rConnection = null;
        try {
            Class.forName(OLAP4J_MONDRIAN_DRIVER_CLASS);
            rConnection = java.sql.DriverManager.getConnection(OLAP4J_MONDRIAN_URL_PREFIX, connectProps);
        } catch (Throwable t) {
            throw new JRException("error loading Mondrian olap4j driver and getting Connection '" + OLAP4J_MONDRIAN_DRIVER_CLASS + "'", t);
        }

		OlapConnection connection = (OlapConnection) rConnection;
		
		String queryStr = getQueryString();
		if (connection != null && queryStr != null)
		{
			if (log.isDebugEnabled())
			{
				log.debug("MDX query: " + queryStr);
			}
			CellSet result = null;

			try {
				OlapStatement statement = connection.createStatement();

				result = statement.executeOlapQuery(getQueryString());
			} catch (OlapException e) {
				throw new JRException("Error executing query: " + getQueryString(),
						e);
			}

			if (log.isDebugEnabled())
			{
				OutputStream bos = new ByteArrayOutputStream();
				CellSetFormatter formatter = new RectangularCellSetFormatter(true);
				formatter.format(result, new PrintWriter(bos, true));
				log.debug("Result:\n" + bos.toString());
			}
			
			dataSource = new JRMondrianDataSource(dataset, result);
		}

		return dataSource;
	}

	public boolean cancelQuery() throws JRException
	{
		return false;
	}

	public JROlapResult getResult()
	{
		return monResult;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
}
