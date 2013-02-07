package com.jaspersoft.cassandra.query;

import java.util.Collections;
import java.util.List;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.query.JRQueryExecuterFactoryBundle;
import net.sf.jasperreports.extensions.ExtensionsRegistry;
import net.sf.jasperreports.extensions.ExtensionsRegistryFactory;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class CassandraQueryExecuterExtensionsRegistryFactory implements
		ExtensionsRegistryFactory {
	private static final ExtensionsRegistry defaultExtensionsRegistry = new ExtensionsRegistry() {
		private List<CassandraQueryExecuterFactoryBundle> list = Collections
				.singletonList(CassandraQueryExecuterFactoryBundle
						.getInstance());

		@SuppressWarnings("unchecked")
		@Override
		public <T> List<T> getExtensions(Class<T> extensionType) {
			if (JRQueryExecuterFactoryBundle.class.equals(extensionType)) {
				return (List<T>) list;
			}
			return null;
		}
	};

	public ExtensionsRegistry createRegistry(String registryId,
			JRPropertiesMap properties) {
		return defaultExtensionsRegistry;
	}
}