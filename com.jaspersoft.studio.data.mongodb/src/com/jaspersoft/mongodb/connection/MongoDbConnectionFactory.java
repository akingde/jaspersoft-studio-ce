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

package com.jaspersoft.mongodb.connection;

import net.sf.jasperreports.engine.JRException;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.log4j.Logger;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class MongoDbConnectionFactory implements PoolableObjectFactory<MongoDbConnection> {
  private final Logger logger = Logger.getLogger(MongoDbConnectionFactory.class);

  private String mongoURI;

  private String username;

  private String password;

  @Override
  public void activateObject(MongoDbConnection connection) throws Exception {
  }

  @Override
  public void destroyObject(MongoDbConnection connection) throws Exception {
    logger.info("Factory destroy object");
    if (connection != null) {
      connection.close();
    }
  }

  @Override
  public MongoDbConnection makeObject() throws Exception {
    logger.info("Factory make object");
    return new MongoDbConnection(mongoURI, username, password);
  }

  @Override
  public void passivateObject(MongoDbConnection connection) throws Exception {
    if (logger.isDebugEnabled()) {
      logger.debug("Passivate is not implemented");
    }
  }

  @Override
  public boolean validateObject(MongoDbConnection connection) {
    logger.info("Factory validate object");
    if (connection != null) {
      try {
        return (mongoURI != null && mongoURI.equals(connection.getMongoURI()))
            && (username == null ? true : username.equals(connection.getUsername()))
            && (password == null ? true : password.equals(connection.getPassword())) && (connection.test() != null);
      } catch (JRException e) {
        logger.error(e);
      }
    }
    return false;
  }

  public void setMongoURI(String mongoURI) {
    this.mongoURI = mongoURI;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}