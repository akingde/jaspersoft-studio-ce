/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class SqlStandaloneSetup extends SqlStandaloneSetupGenerated{

	public static void doSetup() {
		new SqlStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

