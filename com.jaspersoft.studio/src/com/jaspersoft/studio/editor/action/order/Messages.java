package com.jaspersoft.studio.editor.action.order;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.editor.action.order.messages"; //$NON-NLS-1$
	public static String BringBackwardAction_send_backward;
	public static String BringBackwardAction_send_backward_tool_tip;
	public static String BringForwardAction_bring_forward;
	public static String BringForwardAction_bring_forward_tool_tip;
	public static String BringToBackAction_send_to_back;
	public static String BringToBackAction_send_to_back_tool_tip;
	public static String BringToFrontAction_bring_to_front;
	public static String BringToFrontAction_bring_to_front_tool_tip;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
