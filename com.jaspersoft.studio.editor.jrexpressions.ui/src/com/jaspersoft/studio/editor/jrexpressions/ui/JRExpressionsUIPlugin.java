/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.jrexpressions.ui;

import org.eclipse.jface.preference.IPreferenceStore;
import org.osgi.framework.BundleContext;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.preferences.ExpressionEditorPreferencePage;


/**
 * Plug-in activator that extends that generated one {@link JRExpressionsActivator}.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class JRExpressionsUIPlugin extends JRExpressionsActivator {

	/** Plug-in identifier */
	public static final String PLUGIN_ID = "com.jaspersoft.studio.editor.jrexpressions.ui"; //$NON-NLS-1$

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		initDefaultPreferences();
	}

	/*
	 * Initializes preferences information.
	 */
	private void initDefaultPreferences() {
		// Override already defined default in ExpressionEditorPreferencePage class
		IPreferenceStore store=JaspersoftStudioPlugin.getInstance().getPreferenceStore();
		store.setDefault(ExpressionEditorPreferencePage.P_INCLUDE_FUCTIONS_LIBRARY_IMPORTS, false); //$//$NON-NLS-1$
	}
	
	
}
