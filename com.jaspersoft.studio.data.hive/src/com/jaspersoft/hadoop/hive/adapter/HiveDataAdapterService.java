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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import net.sf.jasperreports.data.AbstractDataAdapterService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JdbcDataAdapterService.java 4595 2011-09-08 15:55:10Z teodord $
 */
public class HiveDataAdapterService extends AbstractDataAdapterService {
	private static final Log log = LogFactory
			.getLog(HiveDataAdapterService.class);
	private Connection con;

	public HiveDataAdapterService(HiveDataAdapter jdbcDataAdapter) {
		super(jdbcDataAdapter);
	}

	public HiveDataAdapter getHibernateDataAdapter() {
		return (HiveDataAdapter) getDataAdapter();
	}

	@Override
	public void contributeParameters(Map<String, Object> parameters)
			throws JRException {
		HiveDataAdapter hiveDA = getHibernateDataAdapter();
		if (hiveDA != null) {
			try {
				// Class.forName(hiveDA.getDriver());

				// String pass = hiveDA.getPassword();
				// if (pass == null)
				// pass = "";
				// String user = hiveDA.getUsername();
				// if (user == null)
				// user = "";
				String url = hiveDA.getUrl();
				con = new com.jaspersoft.hadoop.hive.HiveConnection(url);

				parameters.put(JRParameter.REPORT_CONNECTION, con);
			} catch (Exception e) {
				throw new JRException(e);
			}
		}
	}

	@Override
	public void dispose() {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			if (log.isErrorEnabled())
				log.error("Error while closing the connection.", e);
		}
	}
}
