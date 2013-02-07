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

package com.jaspersoft.cassandra.connection;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.log4j.Logger;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class CassandraConnectionFactory implements PoolableObjectFactory<CassandraConnection> {
    private final Logger logger = Logger.getLogger(CassandraConnectionFactory.class);

    private String jdbcURL;

    @Override
    public void activateObject(CassandraConnection connection) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("Activating Cassandra connection object");
        }
    }

    @Override
    public void destroyObject(CassandraConnection connection) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("Destroying Cassandra connection");
        }
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public CassandraConnection makeObject() throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("Creating Cassandra connection");
        }
        return new CassandraConnection(jdbcURL);
    }

    @Override
    public void passivateObject(CassandraConnection connection) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("Passivate is not implemented");
        }
    }

    @Override
    public boolean validateObject(CassandraConnection connection) {
        if (logger.isDebugEnabled()) {
            logger.debug("Validating Cassandra connection");
        }
        if (connection != null) {
            return jdbcURL.equals(connection.getJdbcURL()) && (connection.test() != null);
        }
        return false;
    }

    public void setJdbcURL(String jdbcURL) {
        this.jdbcURL = jdbcURL;
    }
}