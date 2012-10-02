/*
 * Copyright (C) 2005 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License  as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero  General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

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