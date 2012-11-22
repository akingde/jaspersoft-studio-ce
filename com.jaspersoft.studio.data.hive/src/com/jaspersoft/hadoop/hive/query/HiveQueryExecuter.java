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
