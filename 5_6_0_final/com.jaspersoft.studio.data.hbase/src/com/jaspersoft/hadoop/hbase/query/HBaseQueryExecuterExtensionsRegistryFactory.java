package com.jaspersoft.hadoop.hbase.query;

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
public class HBaseQueryExecuterExtensionsRegistryFactory implements ExtensionsRegistryFactory {
	private static final ExtensionsRegistry defaultExtensionsRegistry = new ExtensionsRegistry() {
		@SuppressWarnings("unchecked")
		public <T> List<T> getExtensions(Class<T> extensionType) {
			if (JRQueryExecuterFactoryBundle.class.equals(extensionType))
				return (List<T>) Collections.singletonList(HBaseQueryExecuterFactoryBundle.getInstance());
			return null;
		}
	};

	public ExtensionsRegistry createRegistry(String registryId, JRPropertiesMap properties) {
		return defaultExtensionsRegistry;
	}
}