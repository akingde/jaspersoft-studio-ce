package com.jaspersoft.studio.property.section.band;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.property.section.band.messages"; //$NON-NLS-1$
	public static String BandSection_height;
	public static String BandSection_height_tool_tip;
	public static String BandSection_split_type;
	public static String BandSection_split_type_tool_tip;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
