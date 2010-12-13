package com.jaspersoft.studio.model.style.command;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.model.style.command.messages"; //$NON-NLS-1$
	public static String CreateStyleCommand_style_name;
	public static String CreateStyleCommand_style_name_dialog;
	public static String OrphanConditionalStyleCommand_orphan_child;
	public static String ReorderConditionalStyleCommand_reorder_elements;
	public static String ReorderStyleCommand_reorder_elements;
	public static String ReorderStyleTemplateCommand_reorder_elements;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
