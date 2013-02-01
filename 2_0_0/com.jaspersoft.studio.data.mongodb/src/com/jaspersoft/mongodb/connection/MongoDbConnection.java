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
package com.jaspersoft.mongodb.connection;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import net.sf.jasperreports.engine.JRException;

import org.apache.log4j.Logger;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.MongoURI;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class MongoDbConnection implements Connection {
	private Mongo client;

	private MongoURI mongoURIObject;

	private String mongoURI;

	private String username;

	private String password;

	private DB mongoDatabase;

	private final Logger logger = Logger.getLogger(MongoDbConnection.class);

	public MongoDbConnection(String mongoURI, String username, String password)
			throws JRException {
		create(this.mongoURI = mongoURI);
		this.username = username;
		this.password = password;
		setDatabase();
	}

	private void create(String mongoURI) throws JRException {
		close();
		try {
			client = new Mongo(mongoURIObject = new MongoURI(mongoURI));
		} catch (Exception e) {
			logger.error(e);
			throw new JRException(e.getMessage());
		}
	}

	private void setDatabase() throws JRException {
		if (client == null) {
			logger.error("No client");
			return;
		}
		if (mongoDatabase != null) {
			return;
		}
		mongoDatabase = client.getDB(mongoURIObject.getDatabase());
		boolean performaAuthentication = false;
		try {
			mongoDatabase.getCollectionNames();
		} catch (Exception e) {
			String message = e.getMessage();
			if (e instanceof MongoException) {
				if (message != null && message.startsWith("unauthorized db")) {
					performaAuthentication = true;
				} else {
					logger.error(e);
					throw new JRException(message);
				}
			} else {
				logger.error(e);
				throw new JRException(message);
			}
		}
		if (performaAuthentication) {
			if (username != null && password != null) {
				if (!mongoDatabase.authenticate(username,
						password.toCharArray())) {
					throw new JRException(
							"Successful connection but wrong authentication");
				}
			} else {
				throw new JRException(
						"Authentication required but username or password is empty");
			}
		}
	}

	public DB getMongoDatabase() {
		return mongoDatabase;
	}

	public String getMongoURI() {
		return mongoURI;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public void close() {
		if (client != null) {
			client.close();
			client = null;
		}
		mongoURIObject = null;
	}

	@Override
	public boolean isClosed() throws SQLException {
		if (client != null) {
			return false;
		}
		return true;
	}

	public MongoURI getMongoURIObject() {
		return mongoURIObject;
	}

	public Mongo getClient() {
		return client;
	}

	public String test() throws JRException {
		if (mongoURIObject == null) {
			throw new JRException("Invalid mongo URL");
		}

		if (mongoDatabase == null) {
			throw new JRException("No mongo database");
		}

		try {
			return "Connection test successful.\n" + "Mongo database name: "
					+ mongoDatabase.getName();
		} catch (Exception e) {
			logger.error(e);
			throw new JRException(e);
		}
	}

	public void getDatabase(String databaseName) {

	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	@Override
	public Statement createStatement() throws SQLException {
		return null;
	}

	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return null;
	}

	@Override
	public CallableStatement prepareCall(String sql) throws SQLException {
		return null;
	}

	@Override
	public String nativeSQL(String sql) throws SQLException {
		return null;
	}

	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {
	}

	@Override
	public boolean getAutoCommit() throws SQLException {
		return false;
	}

	@Override
	public void commit() throws SQLException {
	}

	@Override
	public void rollback() throws SQLException {
	}

	@Override
	public DatabaseMetaData getMetaData() throws SQLException {
		return null;
	}

	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {
	}

	@Override
	public boolean isReadOnly() throws SQLException {
		return false;
	}

	@Override
	public void setCatalog(String catalog) throws SQLException {
	}

	@Override
	public String getCatalog() throws SQLException {
		return null;
	}

	@Override
	public void setTransactionIsolation(int level) throws SQLException {
	}

	@Override
	public int getTransactionIsolation() throws SQLException {
		return 0;
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		return null;
	}

	@Override
	public void clearWarnings() throws SQLException {
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency)
			throws SQLException {
		return null;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		return null;
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		return null;
	}

	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		return null;
	}

	@Override
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
	}

	@Override
	public void setHoldability(int holdability) throws SQLException {
	}

	@Override
	public int getHoldability() throws SQLException {
		return 0;
	}

	@Override
	public Savepoint setSavepoint() throws SQLException {
		return null;
	}

	@Override
	public Savepoint setSavepoint(String name) throws SQLException {
		return null;
	}

	@Override
	public void rollback(Savepoint savepoint) throws SQLException {
	}

	@Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
	}

	@Override
	public Statement createStatement(int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return null;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return null;
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return null;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
			throws SQLException {
		return null;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
			throws SQLException {
		return null;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames)
			throws SQLException {
		return null;
	}

	@Override
	public Clob createClob() throws SQLException {
		return null;
	}

	@Override
	public Blob createBlob() throws SQLException {
		return null;
	}

	@Override
	public NClob createNClob() throws SQLException {
		return null;
	}

	@Override
	public SQLXML createSQLXML() throws SQLException {
		return null;
	}

	@Override
	public boolean isValid(int timeout) throws SQLException {
		return false;
	}

	@Override
	public void setClientInfo(String name, String value)
			throws SQLClientInfoException {
	}

	@Override
	public void setClientInfo(Properties properties)
			throws SQLClientInfoException {
	}

	@Override
	public String getClientInfo(String name) throws SQLException {
		return null;
	}

	@Override
	public Properties getClientInfo() throws SQLException {
		return null;
	}

	@Override
	public Array createArrayOf(String typeName, Object[] elements)
			throws SQLException {
		return null;
	}

	@Override
	public Struct createStruct(String typeName, Object[] attributes)
			throws SQLException {
		return null;
	}

	public void setSchema(String schema) throws SQLException {
		// TODO Auto-generated method stub

	}

	public String getSchema() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void abort(Executor executor) throws SQLException {
		// TODO Auto-generated method stub

	}

	public void setNetworkTimeout(Executor executor, int milliseconds)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	public int getNetworkTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
}
