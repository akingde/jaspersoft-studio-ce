/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.secret;

import net.sf.jasperreports.data.AbstractDataAdapterService;
import net.sf.jasperreports.eclipse.secret.EclipseSecretsProvider;
import net.sf.jasperreports.util.SecretsProvider;

import com.jaspersoft.studio.JaspersoftStudioPlugin;

/**
 * {@link SecretsProvider} for the Data Adapters sensitive information.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class DataAdaptersSecretsProvider extends EclipseSecretsProvider {
	
	public static final String SECRET_NODE_ID = AbstractDataAdapterService.SECRETS_CATEGORY;
	
	@Override
	public boolean hasSecret(String key) {
		// Ensure back-compatibility
		return true;
	}
	
	@Override
	public String getSecretNodeId() {
		return SECRET_NODE_ID;
	}
	
	@Override
	public String getSecret(String key) {
		return JaspersoftStudioPlugin.shouldUseSecureStorage() ? super.getSecret(key) : key;
	}
}
