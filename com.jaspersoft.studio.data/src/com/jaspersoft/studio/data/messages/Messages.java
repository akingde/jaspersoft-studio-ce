package com.jaspersoft.studio.data.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.data.messages.messages"; //$NON-NLS-1$
	public static String BeanMappingTool_errormessage;
	public static String BeanMappingTool_labeltitle;
	public static String BeanMappingTool_selectfieldstitle;
	public static String BeanMappingTool_toolname;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
