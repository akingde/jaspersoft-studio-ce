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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.query.JRQueryExecuterFactoryBundle;
import net.sf.jasperreports.engine.query.QueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRSingletonCache;

import com.jaspersoft.hadoop.hive.HiveDataSource;

public class HiveQueryExecuterFactoryBundle implements
		JRQueryExecuterFactoryBundle {
	private static final JRSingletonCache<QueryExecuterFactory> cache = new JRSingletonCache<QueryExecuterFactory>(
			QueryExecuterFactory.class);

	private static final HiveQueryExecuterFactoryBundle instance = new HiveQueryExecuterFactoryBundle();

	private static final String[] languages = new String[] { HiveDataSource.QUERY_LANGUAGE };

	private HiveQueryExecuterFactoryBundle() {
	}

	public static HiveQueryExecuterFactoryBundle getInstance() {
		return instance;
	}

	public String[] getLanguages() {
		return languages;
	}

	public QueryExecuterFactory getQueryExecuterFactory(String language)
			throws JRException {
		if (HiveDataSource.QUERY_LANGUAGE.equals(language)) {
			return (QueryExecuterFactory) cache
					.getCachedInstance(HiveQueryExecuterFactory.class.getName());
		}
		return null;
	}
}