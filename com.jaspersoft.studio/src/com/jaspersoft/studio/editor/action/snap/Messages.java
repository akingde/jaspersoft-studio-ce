package com.jaspersoft.studio.editor.action.snap;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.editor.action.snap.messages"; //$NON-NLS-1$
	public static String ShowGridAction_show_grid;
	public static String ShowGridAction_show_grid_tool_tip;
	public static String SizeGridAction_grid_editor;
	public static String SizeGridAction_grid_size;
	public static String SizeGridAction_grid_space_height_tool_tip;
	public static String SizeGridAction_grid_space_width_tool_tip;
	public static String SizeGridAction_set_grid_size;
	public static String SizeGridAction_set_grid_size_tool_tip;
	public static String SizeGridAction_spacing_x;
	public static String SizeGridAction_spacing_y;
	public static String SnapToGridAction_snap_to_grid;
	public static String SnapToGridAction_snap_to_grid_tool_tip;
	public static String SnapToGuidesAction_show_grid;
	public static String SnapToGuidesAction_show_grid_tool_tip;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
