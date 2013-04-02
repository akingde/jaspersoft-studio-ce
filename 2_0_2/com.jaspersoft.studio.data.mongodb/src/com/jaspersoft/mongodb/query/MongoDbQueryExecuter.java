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
package com.jaspersoft.mongodb.query;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JRValueParameter;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.query.JRAbstractQueryExecuter;

import org.apache.log4j.Logger;

import com.jaspersoft.mongodb.MongoDbDataSource;
import com.jaspersoft.mongodb.connection.MongoDbConnection;

/**
 * A query executer for pseudo mongodb queries<br/>
 * This implementation process report parameters
 * 
 * @author Eric Diaz
 * 
 */
public class MongoDbQueryExecuter extends JRAbstractQueryExecuter {
	private final static Logger logger = Logger
			.getLogger(MongoDbQueryExecuter.class);

	private Map<String, ? extends JRValueParameter> reportParameters;

	private Map<String, Object> parameters;

	private MongoDbQueryWrapper wrapper;

	private boolean directParameters;

	public MongoDbQueryExecuter(JasperReportsContext jasperReportsContext,
			JRDataset dataset,
			Map<String, ? extends JRValueParameter> parameters)
			throws JRException {
		this(jasperReportsContext, dataset, parameters, false);
	}

	public MongoDbQueryExecuter(JasperReportsContext jasperReportsContext,
			JRDataset dataset,
			Map<String, ? extends JRValueParameter> parameters,
			boolean directParameters) {
		super(jasperReportsContext, dataset, parameters);
		this.directParameters = directParameters;
		this.reportParameters = parameters;
		this.parameters = new HashMap<String, Object>();
		parseQuery();
	}

	/**
	 * Method not implemented
	 */
	@Override
	public boolean cancelQuery() throws JRException {
		logger.warn("Cancel not implemented");
		return false;
	}

	@Override
	public void close() {
		wrapper = null;
	}

	private MongoDbConnection processConnection(JRValueParameter valueParameter)
			throws JRException {
		if (valueParameter == null) {
			throw new JRException("No MongoDB connection");
		}
		return (MongoDbConnection) valueParameter.getValue();
	}

	/**
	 * Creates a new {@link MongoDbDataSource} from the report parameters and
	 * the query string
	 */
	public JRDataSource createDatasource() throws JRException {
		MongoDbConnection connection = (MongoDbConnection) ((Map<?, ?>) getParameterValue(JRParameter.REPORT_PARAMETERS_MAP))
				.get(JRParameter.REPORT_CONNECTION);
		if (connection == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("REPORT_PARAMETERS_MAP: "
						+ ((Map<?, ?>) getParameterValue(JRParameter.REPORT_PARAMETERS_MAP))
								.keySet());
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Direct parameters: " + reportParameters.keySet());
			}
			connection = processConnection(reportParameters
					.get(JRParameter.REPORT_CONNECTION));
			if (connection == null) {
				throw new JRException("No MongoDB connection");
			}
		}
		wrapper = new MongoDbQueryWrapper(getQueryString(), connection,
				parameters);
		return new MongoDbDataSource(wrapper);
	}

	/**
	 * Replacement of parameters
	 */
	@Override
	protected String getParameterReplacement(String parameterName) {
		Object parameterValue = reportParameters.get(parameterName);
		if (parameterValue == null) {
			throw new JRRuntimeException("Parameter \"" + parameterName
					+ "\" does not exist.");
		}
		if (parameterValue instanceof JRValueParameter) {
			parameterValue = ((JRValueParameter) parameterValue).getValue();
		}
		return processParameter(parameterName, parameterValue);
	}

	private String processParameter(String parameterName, Object parameterValue) {
		if (parameterValue instanceof Collection) {
			StringBuilder builder = new StringBuilder();
			builder.append("[");
			for (Object value : (Collection<?>) parameterValue) {
				if (value instanceof String) {
					builder.append("'");
					builder.append(value);
					builder.append("'");
				} else {
					builder.append(String.valueOf(value));
				}
				builder.append(", ");
			}
			if (builder.length() > 2) {
				builder.delete(builder.length() - 2, builder.length());
			}
			builder.append("]");
			return builder.toString();
		}
		parameters.put(parameterName, parameterValue);
		return generateParameterObject(parameterName);
	}

	private String generateParameterObject(String parameterName) {
		return "{'" + parameterName + "':null}";
	}

	public String getProcessedQueryString() {
		return getQueryString();
	}

	@Override
	protected Object getParameterValue(String parameterName,
			boolean ignoreMissing) {
		try {
			return super.getParameterValue(parameterName, ignoreMissing);
		} catch (Exception e) {
			if (e.getMessage()
					.endsWith(
							"cannot be cast to net.sf.jasperreports.engine.JRValueParameter")
					&& directParameters) {
				return reportParameters.get(parameterName);
			}
		}
		return null;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}
}
