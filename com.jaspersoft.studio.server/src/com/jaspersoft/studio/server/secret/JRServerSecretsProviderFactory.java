/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.secret;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.util.SecretsProvider;
import net.sf.jasperreports.util.SecretsProviderFactory;

/**
 * {@link SecretsProviderFactory} for the JasperReports Server sensitive information.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class JRServerSecretsProviderFactory implements SecretsProviderFactory {
	
	private static final List<String> categories;
	private static JRServerSecretsProviderFactory instance;
	private JRServerSecretsProvider jRServerSecretsProvider;
	
	static {
		categories = new ArrayList<String>(1);
		categories.add(JRServerSecretsProvider.SECRET_NODE_ID);
	}

	private JRServerSecretsProviderFactory() {
	}

	public static JRServerSecretsProviderFactory getInstance() {
		if (instance == null)
			instance = new JRServerSecretsProviderFactory();
		return instance;
	}

	@Override
	public SecretsProvider getSecretsProvider(String category) {
		if(categories.contains(category)) {
			if (jRServerSecretsProvider == null)
				jRServerSecretsProvider = new JRServerSecretsProvider();
			return jRServerSecretsProvider;
		}
		return null;
	}

}
