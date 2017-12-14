/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.properties.messages;

import org.eclipse.osgi.util.NLS;

/**
 * Message Bundle class for the tabbed properties view plug-in.
 * 
 * @author Anthony Hunter
 * 
 */
public final class Messages extends NLS {

	private static final String BUNDLE_NAME = "com.jaspersoft.studio.properties.messages.messages";//$NON-NLS-1$

	/**
	 * Constructor for TabbedPropertyMessages.
	 */
	private Messages() {
		// private constructor
	}

	public static String AdvancedPropertySection_restoreDefaultName;

	public static String AdvancedPropertySection_restoreDefaultTooltip;

	public static String CustomDefaultsAction_restoreDefaultChildrenName;

	public static String PropertiesPreferencePage_advancedDefaultTooltip;

	public static String PropertiesPreferencePage_preferenceDescription;

	public static String PropertiesPreferencePage_preferenceTitle;

	public static String PropertiesPreferencePage_singleColumnProperty;

	public static String PropertiesPreferencePage_singleColumnTooltip;
	
	public static String DesignerPreferencePage_advancedDefault;

	/**
	 * Message when a property section extension is in error.
	 */
	public static String SectionDescriptor_Section_error;

	/**
	 * Message when a property section extension causes a class not found
	 * exception.
	 */
	public static String SectionDescriptor_class_not_found_error;

	/**
	 * Message when a property tab extension is in error.
	 */
	public static String TabDescriptor_Tab_error;

	/**
	 * Message when a property tab extension has an unknown category.
	 */
	public static String TabDescriptor_Tab_unknown_category;

	/**
	 * Message when a non existing tab is found in a property section extension.
	 */
	public static String TabbedPropertyRegistry_Non_existing_tab;

	/**
	 * Message when a property contributor extension is in error.
	 */
	public static String TabbedPropertyRegistry_contributor_error;

	public static String TabbedPropertySearch_searchPropertyLabel;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
