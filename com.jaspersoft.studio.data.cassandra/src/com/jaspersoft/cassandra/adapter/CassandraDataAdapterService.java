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
package com.jaspersoft.cassandra.adapter;

import java.util.Map;

import net.sf.jasperreports.data.AbstractDataAdapterService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperReportsContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jaspersoft.cassandra.connection.CassandraConnection;

/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: CassandraDataAdapterService.java 17364 2012-03-28 02:29:51Z
 *          ediaz $
 * @author Eric Diaz
 */
public class CassandraDataAdapterService extends AbstractDataAdapterService {
	private static final Log log = LogFactory
			.getLog(CassandraDataAdapterService.class);

	private CassandraConnection connection;

	private CassandraDataAdapter dataAdapter;

	public CassandraDataAdapterService(
			JasperReportsContext jasperReportsContext,
			CassandraDataAdapter dataAdapter) {
		super(jasperReportsContext, dataAdapter);
		this.dataAdapter = dataAdapter;
	}

	@Override
	public void contributeParameters(Map<String, Object> parameters)
			throws JRException {
		if (connection != null) {
			dispose();
		}
		if (dataAdapter != null) {
			try {
				createConnection();
				parameters.put(JRParameter.REPORT_CONNECTION, connection);
			} catch (Exception e) {
				throw new JRException(e);
			}
		}
	}

	private void createConnection() throws JRException {
		try {
			connection = new CassandraConnection(dataAdapter.getJdbcURL());
		} catch (Exception e) {
			e.printStackTrace();
			throw new JRException(e);
		}
	}

	@Override
	public void dispose() {
		try {
			if (connection != null)
				connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			if (log.isErrorEnabled())
				log.error("Error while closing the connection.", e);
		}
	}

	@Override
	public void test() throws JRException {
		try {
			if (connection != null) {
			} else {
				createConnection();
			}
			connection.test();
		} finally {
			dispose();
		}
	}
}