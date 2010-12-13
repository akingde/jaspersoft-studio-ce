package com.jaspersoft.studio.property.descriptor.classname;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.property.descriptor.classname.messages"; //$NON-NLS-1$
	public static String ClassTypeCellEditor_dialog_message;
	public static String ClassTypeCellEditor_open_type;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
