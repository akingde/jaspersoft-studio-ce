package com.jaspersoft.hadoop.hive;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.query.JRQueryExecuterFactory;
import net.sf.jasperreports.engine.query.QueryExecuterFactoryBundle;
import net.sf.jasperreports.engine.util.JRSingletonCache;

public class HiveQueryExecuterFactoryBundle implements
		QueryExecuterFactoryBundle {
	private static final JRSingletonCache<JRQueryExecuterFactory> cache = new JRSingletonCache<JRQueryExecuterFactory>(
			JRQueryExecuterFactory.class);

	private static final HiveQueryExecuterFactoryBundle INSTANCE = new HiveQueryExecuterFactoryBundle();

	private HiveQueryExecuterFactoryBundle() {
	}

	/**
	 * 
	 */
	public static HiveQueryExecuterFactoryBundle getInstance() {
		return INSTANCE;
	}

	public String[] getLanguages() {
		return new String[] { HiveDataSource.QUERY_LANGUAGE };
	}

	public JRQueryExecuterFactory getQueryExecuterFactory(String language)
			throws JRException {
		for (String lang : getLanguages()) {
			if (lang.equalsIgnoreCase(language))
				return cache.getCachedInstance(HiveQueryExecuterFactory.class
						.getName());
		}
		return null;
	}

}
