package com.jaspersoft.studio.editor.menu;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.editor.menu.messages"; //$NON-NLS-1$
	public static String AppContextMenuProvider_align_components;
	public static String AppContextMenuProvider_align_to_container;
	public static String AppContextMenuProvider_order;
	public static String AppContextMenuProvider_size_components;
	public static String AppContextMenuProvider_size_to_container;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
