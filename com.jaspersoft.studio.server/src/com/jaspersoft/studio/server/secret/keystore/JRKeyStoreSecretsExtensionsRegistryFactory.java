/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.secret.keystore;

import java.util.Collections;
import java.util.List;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.extensions.ExtensionsRegistry;
import net.sf.jasperreports.extensions.ExtensionsRegistryFactory;
import net.sf.jasperreports.util.SecretsProviderFactory;

/**
 * {@link ExtensionsRegistryFactory} for the JasperReports Server sensitive
 * information.
 * 
 * @author Veaceslav Chicu (chicuslavic@users.sourceforge.net)
 *
 */
public class JRKeyStoreSecretsExtensionsRegistryFactory implements ExtensionsRegistryFactory {
	private static final ExtensionsRegistry defaultExtensionsRegistry = new ExtensionsRegistry() {
		public <T> List<T> getExtensions(Class<T> extensionType) {
			if (SecretsProviderFactory.class.equals(extensionType))
				return (List<T>) Collections.singletonList(JRKeyStoreSecretsProviderFactory.getInstance());
			return null;
		}
	};

	public ExtensionsRegistry createRegistry(String registryId, JRPropertiesMap properties) {
		return defaultExtensionsRegistry;
	}
}
