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

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool.Config;
import org.apache.log4j.Logger;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class HiveConnectionManager {
    private GenericObjectPool<HiveConnection> connectionsPool;

    private Config poolConfiguration;

    private HiveConnectionFactory connectionFactory;

    private final Logger logger = Logger.getLogger(HiveConnectionManager.class);

    public HiveConnectionManager() {
        connectionFactory = new HiveConnectionFactory();
        poolConfiguration = new Config();
        poolConfiguration.testOnBorrow = true;
        poolConfiguration.testWhileIdle = true;
        poolConfiguration.whenExhaustedAction = GenericObjectPool.WHEN_EXHAUSTED_GROW;
        poolConfiguration.maxActive = 4;
        poolConfiguration.maxIdle = 2;
        poolConfiguration.minIdle = 1;
    }

    private GenericObjectPool<HiveConnection> startConnectionsPool() {
        if (connectionsPool == null) {
            connectionsPool = new GenericObjectPool<HiveConnection>(connectionFactory, poolConfiguration);
        }
        return connectionsPool;
    }

    public HiveConnection borrowConnection() throws Exception {
        if (connectionsPool == null) {
            startConnectionsPool();
        }
        if (connectionsPool == null) {
            logger.error("No connection pool created");
            return null;
        }
        return connectionsPool.borrowObject();
    }

    public void returnConnection(HiveConnection connection) {
        if (connectionsPool == null) {
            logger.error("No connection pool created");
            return;
        }
        try {
            connectionsPool.returnObject(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        if (connectionsPool != null) {
            try {
                connectionsPool.clear();
                connectionsPool.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setMaxActive(int maxActive) {
        poolConfiguration.maxActive = maxActive;
    }

    public void setMaxIdle(int maxIdle) {
        poolConfiguration.maxIdle = maxIdle;
    }

    public void setMinIdle(int minIdle) {
        poolConfiguration.minIdle = minIdle;
    }

    public void setJdbcURL(String jdbcURL) {
        connectionFactory.setJdbcURL(jdbcURL);
    }

    protected GenericObjectPool<HiveConnection> getConnectionsPool() {
        return connectionsPool;
    }
}