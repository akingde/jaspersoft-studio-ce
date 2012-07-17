
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

