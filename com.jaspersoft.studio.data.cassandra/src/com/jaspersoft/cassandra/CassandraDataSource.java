/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.cassandra;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import org.apache.log4j.Logger;

/**
 * An implementation of a data source that uses an empty query and parameters
 * 
 * @author Eric Diaz
 * 
 */
public class CassandraDataSource implements JRDataSource {
	private ResultSet resultSet;

	public static final String DRIVER_NAME = "org.apache.cassandra.cql.jdbc.CassandraDriver";

	public static final String CASSANDRA_CONNECTION = "com.jaspersoft.cassandra.connection";

	public static final String QUERY_LANGUAGE = "CQL";

	private final static Logger logger = Logger.getLogger(CassandraDataSource.class);

	public CassandraDataSource(ResultSet resultSet) throws ClassNotFoundException {
		this.resultSet = resultSet;
		if (logger.isDebugEnabled()) {
			logger.debug("New Cassandra Data Source");
		}
	}

	/**
	 * Gets the field value for the current position.
	 */
	public Object getFieldValue(JRField field) throws JRException {
		if (resultSet == null) {
			return null;
		}
		try {
			return resultSet.getObject(field.getName());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new JRException(e.getMessage());
		}
	}

	/**
	 * Tries to position the cursor on the next element in the data source.
	 */
	public boolean next() throws JRException {
		if (resultSet == null) {
			return false;
		}
		boolean next = false;
		try {
			next = resultSet.next();
			if (!next) {
				resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return next;
	}
}
