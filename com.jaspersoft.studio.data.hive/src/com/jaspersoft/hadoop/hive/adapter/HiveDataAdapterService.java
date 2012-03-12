/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.hadoop.hive.adapter;

import java.util.Map;

import net.sf.jasperreports.data.AbstractDataAdapterService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jaspersoft.hadoop.hive.connection.HiveConnection;
import com.jaspersoft.hadoop.hive.connection.HiveConnectionManager;

/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JdbcDataAdapterService.java 4595 2011-09-08 15:55:10Z teodord $
 * @author Eric Diaz
 */
public class HiveDataAdapterService extends AbstractDataAdapterService {
	private static final Log log = LogFactory
			.getLog(HiveDataAdapterService.class);
	private static HiveConnectionManager connectionManager;

	public static HiveConnectionManager getConnectionManager() {
		if (connectionManager == null) {
			System.out.println("Starting Hive Connection Manager");
			connectionManager = new HiveConnectionManager();
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					System.out.println("Stopping Hive Connection Manager");
					if (connectionManager != null)
						connectionManager.shutdown();
					super.run();
				}
			});
		}
		return connectionManager;
	}

	private HiveConnection connection;

	private HiveDataAdapter dataAdapter;

	public HiveDataAdapterService(HiveDataAdapter dataAdapter) {
		this.dataAdapter = dataAdapter;
	}

	@Override
	public void contributeParameters(Map<String, Object> parameters)
			throws JRException {
		if (dataAdapter != null) {
			try {
				getConnectionManager().setJdbcURL(dataAdapter.getUrl());
				connection = connectionManager.borrowConnection();
				parameters.put(JRParameter.REPORT_CONNECTION, connection);
			} catch (Exception e) {
				throw new JRException(e);
			}
		}
	}

	@Override
	public void dispose() {
		try {
			if (connection != null)
				connectionManager.returnConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
			if (log.isErrorEnabled())
				log.error("Error while closing the connection.", e);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		if (connectionManager != null) {
			connectionManager.shutdown();
			System.out.println("Hive connection manager is shutdown");
		}
		super.finalize();
	}

	@Override
	public void test() throws JRException {
		super.test();
		if (connection != null) {
			connection.test();
			connectionManager.returnConnection(connection);
		}
	}
}