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

import java.util.Map;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRValueParameter;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.query.JRQueryExecuter;
import net.sf.jasperreports.engine.query.QueryExecuterFactory;

/**
 * Query executer factory for MongoDB pseudo queries. <br/>
 * This factory creates a {@link MongoDbQueryExecuter}
 * 
 * @author Eric Diaz
 * 
 */
public class MongoDbQueryExecuterFactory implements QueryExecuterFactory {
	/**
	 * Creates a {@link MongoDbQueryExecuter}.<br/>
	 * <br/>
	 * This method is called at fill time for reports/datasets having a query
	 * supported by this factory (MongoDbQuery).
	 */
	public JRQueryExecuter createQueryExecuter(JRDataset dataset,
			Map<String, ? extends JRValueParameter> parameters)
			throws JRException {
		return new MongoDbQueryExecuter(
				DefaultJasperReportsContext.getInstance(), dataset, parameters);
	};

	/**
	 * Method not implemented
	 */
	public Object[] getBuiltinParameters() {
		return null;
	}

	public boolean supportsQueryParameterType(String queryParameterType) {
		return true;
	}

	@Override
	public JRQueryExecuter createQueryExecuter(
			JasperReportsContext jasperReportsContext, JRDataset dataset,
			Map<String, ? extends JRValueParameter> parameters)
			throws JRException {
		return new MongoDbQueryExecuter(jasperReportsContext, dataset,
				parameters);
	}

}
