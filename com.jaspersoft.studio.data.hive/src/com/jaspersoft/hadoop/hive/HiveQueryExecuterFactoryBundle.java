package com.jaspersoft.hadoop.hive;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.query.JRQueryExecuterFactory;
import net.sf.jasperreports.engine.query.QueryExecuterFactoryBundle;
import net.sf.jasperreports.engine.util.JRSingletonCache;

import com.jaspersoft.hadoop.hive.HiveQueryExecuterFactory;

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
		return new String[] { "hive" };
	}

	public JRQueryExecuterFactory getQueryExecuterFactory(String language)
			throws JRException {
		return (JRQueryExecuterFactory) cache
				.getCachedInstance(HiveQueryExecuterFactory.class.getName());
	}

}
