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

package com.jaspersoft.hadoop.hive.query;

import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRValueParameter;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.query.JRJdbcQueryExecuter;
import net.sf.jasperreports.engine.query.JRSqlInClause;
import net.sf.jasperreports.engine.query.JRSqlNotInClause;

import org.apache.log4j.Logger;

import com.jaspersoft.hadoop.hive.HiveDataSource;
import com.jaspersoft.hadoop.hive.connection.HiveConnection;

/**
 * Hive query executer for HiveQL queries. <br/>
 * 
 * @author Eric Diaz
 * 
 */
public class HiveQueryExecuter extends JRJdbcQueryExecuter {
	private final static Logger logger = Logger
			.getLogger(HiveQueryExecuter.class);

	public HiveQueryExecuter(JasperReportsContext jasperReportsContext,
			JRDataset dataset,
			Map<String, ? extends JRValueParameter> parameters)
			throws JRException {
		super(jasperReportsContext, dataset, parameters);
		HiveConnection hiveConnection = (HiveConnection) ((Map<?, ?>) getParameterValue(JRParameter.REPORT_PARAMETERS_MAP))
				.get(JRParameter.REPORT_CONNECTION);
		if (hiveConnection == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("REPORT_PARAMETERS_MAP: "
						+ ((Map<?, ?>) getParameterValue(JRParameter.REPORT_PARAMETERS_MAP))
								.keySet());
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Direct parameters: " + parameters.keySet());
			}
			JRValueParameter valueParameter = parameters
					.get(JRParameter.REPORT_CONNECTION);
			if (valueParameter == null) {
				throw new JRException("No Hive connection");
			}
			hiveConnection = (HiveConnection) valueParameter.getValue();
			if (hiveConnection == null) {
				throw new JRException("No Hive connection");
			}
		}
		connection = hiveConnection.getSqlConnection();
	}

	protected void registerFunctions() {
		registerClauseFunction(CLAUSE_ID_IN, JRSqlInClause.instance());
		registerClauseFunction(CLAUSE_ID_NOTIN, JRSqlNotInClause.instance());
	}

	/**
	 * Creates a new {@link HiveDataSource} that will run the query and process
	 * the results
	 */
	public JRDataSource createDatasource() throws JRException {
		super.createDatasource();
		try {
			return new HiveDataSource(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
			throw new JRException(e.getMessage());
		}
	}
}