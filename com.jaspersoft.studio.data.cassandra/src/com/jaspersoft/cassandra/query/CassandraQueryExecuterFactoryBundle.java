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
