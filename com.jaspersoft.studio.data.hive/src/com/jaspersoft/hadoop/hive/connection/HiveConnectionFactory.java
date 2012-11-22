/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.hadoop.hive.connection;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.log4j.Logger;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class HiveConnectionFactory implements PoolableObjectFactory<HiveConnection> {
	private final Logger logger = Logger.getLogger(HiveConnectionFactory.class);

	private String jdbcURL;

	@Override
	public void activateObject(HiveConnection connection) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Activating Hive connection object");
		}
	}

	@Override
	public void destroyObject(HiveConnection connection) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Destroying Hive connection");
		}
		if (connection != null) {
			connection.close();
		}
	}

	@Override
	public HiveConnection makeObject() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Creating Hive connection");
		}
		return new HiveConnection(jdbcURL);
	}

	@Override
	public void passivateObject(HiveConnection connection) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Passivate is not implemented");
		}
	}

	@Override
	public boolean validateObject(HiveConnection connection) {
		if (logger.isDebugEnabled()) {
			logger.debug("Validating Hive connection");
		}
		if (connection != null) {
			return jdbcURL.equals(connection.getJdbcURL()) && connection.test().contains("success");
		}
		return false;
	}

	public void setJdbcURL(String jdbcURL) {
		this.jdbcURL = jdbcURL;
	}
}
