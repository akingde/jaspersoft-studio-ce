package com.jaspersoft.studio.model.datasource.file;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.model.datasource.file.messages"; //$NON-NLS-1$
	public static String MFileDataSource_column_names;
	public static String MFileDataSource_field_delimiter;
	public static String MFileDataSource_first_row_as_header;
	public static String MFileDataSource_first_row_as_header_description;
	public static String MFileDataSource_record_delimiter;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
