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
package com.jaspersoft.cassandra.query;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.query.JRQueryExecuterFactoryBundle;
import net.sf.jasperreports.engine.query.QueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRSingletonCache;

import com.jaspersoft.cassandra.CassandraDataSource;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class CassandraQueryExecuterFactoryBundle implements
		JRQueryExecuterFactoryBundle {
	private static final JRSingletonCache<QueryExecuterFactory> cache = new JRSingletonCache<QueryExecuterFactory>(
			QueryExecuterFactory.class);

	private static final CassandraQueryExecuterFactoryBundle instance = new CassandraQueryExecuterFactoryBundle();

	private static final String[] languages = new String[] { CassandraDataSource.QUERY_LANGUAGE };

	private CassandraQueryExecuterFactoryBundle() {
	}

	public static CassandraQueryExecuterFactoryBundle getInstance() {
		return instance;
	}

	public String[] getLanguages() {
		return languages;
	}

	public QueryExecuterFactory getQueryExecuterFactory(String language)
			throws JRException {
		if (CassandraDataSource.QUERY_LANGUAGE.equals(language)) {
			return (QueryExecuterFactory) cache
					.getCachedInstance(CassandraQueryExecuterFactory.class
							.getName());
		}
		return null;
	}

}
