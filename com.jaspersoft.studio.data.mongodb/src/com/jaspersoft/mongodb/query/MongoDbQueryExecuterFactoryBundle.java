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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.query.JRQueryExecuterFactoryBundle;
import net.sf.jasperreports.engine.query.QueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRSingletonCache;

import com.jaspersoft.mongodb.MongoDbDataSource;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class MongoDbQueryExecuterFactoryBundle implements
		JRQueryExecuterFactoryBundle {
	private static final JRSingletonCache<QueryExecuterFactory> cache = new JRSingletonCache<QueryExecuterFactory>(
			QueryExecuterFactory.class);

	private static final MongoDbQueryExecuterFactoryBundle instance = new MongoDbQueryExecuterFactoryBundle();

	private static final String[] languages = new String[] { MongoDbDataSource.QUERY_LANGUAGE };

	private MongoDbQueryExecuterFactoryBundle() {
	}

	public static MongoDbQueryExecuterFactoryBundle getInstance() {
		return instance;
	}

	public String[] getLanguages() {
		return languages;
	}

	public QueryExecuterFactory getQueryExecuterFactory(String language)
			throws JRException {
		if (MongoDbDataSource.QUERY_LANGUAGE.equals(language)) {
			return (QueryExecuterFactory) cache
					.getCachedInstance(MongoDbQueryExecuterFactory.class
							.getName());
		}
		return null;
	}
}
