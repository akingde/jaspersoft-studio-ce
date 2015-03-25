/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.data.secret;

import java.util.Collections;
import java.util.List;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.extensions.ExtensionsRegistry;
import net.sf.jasperreports.extensions.ExtensionsRegistryFactory;
import net.sf.jasperreports.util.SecretsProviderFactory;

/**
 * {@link ExtensionsRegistryFactory} for the Data Adapters sensitive information.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class DataAdaptersSecretsExtensionsRegistryFactory implements ExtensionsRegistryFactory {
	private static final ExtensionsRegistry defaultExtensionsRegistry = new ExtensionsRegistry() {
		@Override
		public <T> List<T> getExtensions(Class<T> extensionType) {
			if (SecretsProviderFactory.class.equals(extensionType)) {
				@SuppressWarnings("unchecked")
				List<T> extensions = (List<T>) Collections.singletonList(DataAdaptersSecretsProviderFactory.getInstance());
				return extensions;
			}
			return null;
		}
	};

	public ExtensionsRegistry createRegistry(String registryId, JRPropertiesMap properties) {
		return defaultExtensionsRegistry;
	}
}
