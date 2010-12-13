package com.jaspersoft.studio.model.scriptlet;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.model.scriptlet.messages"; //$NON-NLS-1$
	public static String MScriptlet_class;
	public static String MScriptlet_class_description;
	public static String MScriptlet_description;
	public static String MScriptlet_description_description;
	public static String MScriptlet_name;
	public static String MScriptlet_name_description;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
