/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.map.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.components.map.messages.messages"; //$NON-NLS-1$
	public static String MapDatasetSection_DeleteConfirmationMsg;
	public static String MapDatasetSection_DeleteConfirmationTitle;
	public static String MapDatasetSection_UseMarkersDatasetBtn;
	public static String MapWizard_map_wizard;
	public static String MMap_common_map_properties;
	public static String MMap_evaluation_group;
	public static String MMap_evaluation_group_description;
	public static String MMap_evaluation_time;
	public static String MMap_evaluation_time_description;
	public static String MMap_latitude_description;
	public static String MMap_latitude;
	public static String MMap_longitude;
	public static String MMap_longitude_description;
	public static String MMap_zoom;
	public static String MMap_zoom_description;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
