/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.secret;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.util.SecretsProvider;
import net.sf.jasperreports.util.SecretsProviderFactory;

/**
 * {@link SecretsProviderFactory} for the Data Adapters sensitive information.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class DataAdaptersSecretsProviderFactory implements SecretsProviderFactory {
	
	private static final List<String> categories;
	private static DataAdaptersSecretsProviderFactory instance;
	private DataAdaptersSecretsProvider dataAdaptersSecretsProvider;
	
	static {
		categories = new ArrayList<String>(1);
		categories.add(DataAdaptersSecretsProvider.SECRET_NODE_ID);
	}

	private DataAdaptersSecretsProviderFactory() {
	}

	public static DataAdaptersSecretsProviderFactory getInstance() {
		if (instance == null)
			instance = new DataAdaptersSecretsProviderFactory();
		return instance;
	}

	@Override
	public SecretsProvider getSecretsProvider(String category) {
		if(categories.contains(category)) {
			if (dataAdaptersSecretsProvider == null)
				dataAdaptersSecretsProvider = new DataAdaptersSecretsProvider();
			return dataAdaptersSecretsProvider;
		}
		return null;
	}

}
