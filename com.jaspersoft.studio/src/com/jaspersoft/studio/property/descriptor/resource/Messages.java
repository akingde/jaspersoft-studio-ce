package com.jaspersoft.studio.property.descriptor.resource;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.property.descriptor.resource.messages"; //$NON-NLS-1$
	public static String ResourceCellEditor_open_resource;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
