package com.jaspersoft.studio.rcp.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.rcp.messages.messages"; //$NON-NLS-1$
	public static String ApplicationActionBarAdvisor_file;
	public static String ApplicationActionBarAdvisor_help;
	public static String ApplicationActionBarAdvisor_window;
	public static String ApplicationWorkbenchWindowAdvisor_jasper_open_studio;
	public static String Startup_jss_project;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
