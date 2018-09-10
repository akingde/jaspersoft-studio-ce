/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.properties.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.properties.Activator;
import com.jaspersoft.studio.properties.messages.Messages;

import net.sf.jasperreports.eclipse.preferences.IPreferenceExtendablePage;
import net.sf.jasperreports.eclipse.preferences.IPreferencePageExtension;

/**
 * Preference page used to show a flag to decide if in the properties view we should force to show
 * only one column and to show the advanced tab as default
 * 
 * @author Orlandin Marco
 *
 */
public class PropertiesPreferencePage implements IPreferencePageExtension {
	
	/**
	 * Id of the property
	 */
	public static final String SINGLE_COLUMN_ID = "com.jaspersoft.studio.properties.preferences.PropertiesPreferencePage.single_column"; //$NON-NLS-1$

	/**
	 * Key for the property to set the advanced tab always as default in the
	 * properties view
	 */
	public static final String P_DEFAULT_ADVANCED_TAB = "defaultAdvanced"; //$NON-NLS-1$
	
	/**
	 * Initialize the default values of the store, by default the single column property
	 * is disable
	 * 
	 * @param store the current not null preference store
	 */
	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(SINGLE_COLUMN_ID, false);
	}
	

	@Override
	public void createContributedFields(Composite parent, IPreferenceExtendablePage page) {
		
		Label separator = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		separator.setLayoutData(gd);
		
		BooleanFieldEditor be =new BooleanFieldEditor(P_DEFAULT_ADVANCED_TAB, Messages.DesignerPreferencePage_advancedDefault, parent) {
			
			/**
			 * Force to use the storage of the Preferences plugin to store this property, otherwise it will
			 * be used the storage of the host page
			 */
			@Override
			public IPreferenceStore getPreferenceStore() {
				return Activator.getDefault().getPreferenceStore();
			}
		};
		
		be.getDescriptionControl(parent).setToolTipText(Messages.PropertiesPreferencePage_advancedDefaultTooltip);
		page.addField(be);
		
		be = new BooleanFieldEditor(SINGLE_COLUMN_ID, Messages.PropertiesPreferencePage_singleColumnProperty, parent){
			
			/**
			 * Force to use the storage of the Preferences plugin to store this property, otherwise it will
			 * be used the storage of the host page
			 */
			@Override
			public IPreferenceStore getPreferenceStore() {
				return Activator.getDefault().getPreferenceStore();
			}
			
		};
		be.getDescriptionControl(parent).setToolTipText(Messages.PropertiesPreferencePage_singleColumnTooltip);
		page.addField(be);
	}

	@Override
	public void performApply() {	
	}

	@Override
	public void performCancel() {
	}

	@Override
	public void performDefaults() {		
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(SINGLE_COLUMN_ID, false);
		store.setDefault(P_DEFAULT_ADVANCED_TAB, false);
	}


	@Override
	public void initDefaultProperties(IPreferenceStore store) {	
	}
}
