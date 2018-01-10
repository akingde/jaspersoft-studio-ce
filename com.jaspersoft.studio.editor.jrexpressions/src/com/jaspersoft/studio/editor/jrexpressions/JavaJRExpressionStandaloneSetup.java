/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.jrexpressions;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class JavaJRExpressionStandaloneSetup extends JavaJRExpressionStandaloneSetupGenerated{

	public static void doSetup() {
		new JavaJRExpressionStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

