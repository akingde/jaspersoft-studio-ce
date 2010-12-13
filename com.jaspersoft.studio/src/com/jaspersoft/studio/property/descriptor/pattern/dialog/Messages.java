package com.jaspersoft.studio.property.descriptor.pattern.dialog;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.property.descriptor.pattern.dialog.messages"; //$NON-NLS-1$
	public static String CurrencyPattern_description;
	public static String CustomPattern_description;
	public static String CustomPattern_Formats;
	public static String DatePattern_description;
	public static String DatePattern_template_formats;
	public static String NumericPattern_decimal_places;
	public static String NumericPattern_description;
	public static String NumericPattern_leading_zeroes;
	public static String NumericPattern_use_1000_sperator;
	public static String PatternEditor_pattern;
	public static String PatternPage_description;
	public static String PatternPage_format_pattern;
	public static String PercentagePattern_description;
	public static String ScientificPattern_description;
	public static String TimePattern_description;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
