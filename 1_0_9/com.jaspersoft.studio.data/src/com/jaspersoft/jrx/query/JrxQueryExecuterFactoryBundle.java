package com.jaspersoft.jrx.query;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.query.JRQueryExecuterFactory;
import net.sf.jasperreports.engine.query.QueryExecuterFactoryBundle;
import net.sf.jasperreports.engine.util.JRSingletonCache;

public class JrxQueryExecuterFactoryBundle implements
		QueryExecuterFactoryBundle {
	private static final JRSingletonCache<JRQueryExecuterFactory> cache = new JRSingletonCache<JRQueryExecuterFactory>(
			JRQueryExecuterFactory.class);

	private static final JrxQueryExecuterFactoryBundle INSTANCE = new JrxQueryExecuterFactoryBundle();

	private JrxQueryExecuterFactoryBundle() {
	}

	/**
	 * 
	 */
	public static JrxQueryExecuterFactoryBundle getInstance() {
		return INSTANCE;
	}

	public String[] getLanguages() {
		return new String[] { PlSqlQueryExecuterFactory.QUERY_LANGUAGE_PLSQL,
				JRXPathQueryExecuterFactory.QUERY_LANGUAGE };
	}

	public JRQueryExecuterFactory getQueryExecuterFactory(String language)
			throws JRException {
		if (language
				.equalsIgnoreCase(PlSqlQueryExecuterFactory.QUERY_LANGUAGE_PLSQL))
			return cache.getCachedInstance(PlSqlQueryExecuterFactory.class
					.getName());
		if (language
				.equalsIgnoreCase(JRXPathQueryExecuterFactory.QUERY_LANGUAGE))
			return cache.getCachedInstance(JRXPathQueryExecuterFactory.class
					.getName());

		return null;
	}

}
