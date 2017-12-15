/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.secret.keystore;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.util.SecretsProvider;
import net.sf.jasperreports.util.SecretsProviderFactory;

/**
 * {@link SecretsProviderFactory} for the JasperReports Server sensitive
 * information.
 * 
 * @author veaceslav chicu (chicuslavic@users.sourceforge.net)
 *
 */
public class JRKeyStoreSecretsProviderFactory implements SecretsProviderFactory {

	private static final List<String> categories;
	private static JRKeyStoreSecretsProviderFactory instance;
	private JRKeyStoreSecretsProvider jRServerSecretsProvider;

	static {
		categories = new ArrayList<String>(1);
		categories.add(JRKeyStoreSecretsProvider.SECRET_NODE_ID);
	}

	private JRKeyStoreSecretsProviderFactory() {
	}

	public static JRKeyStoreSecretsProviderFactory getInstance() {
		if (instance == null)
			instance = new JRKeyStoreSecretsProviderFactory();
		return instance;
	}

	@Override
	public SecretsProvider getSecretsProvider(String category) {
		if (categories.contains(category)) {
			if (jRServerSecretsProvider == null)
				jRServerSecretsProvider = new JRKeyStoreSecretsProvider();
			return jRServerSecretsProvider;
		}
		return null;
	}

}
