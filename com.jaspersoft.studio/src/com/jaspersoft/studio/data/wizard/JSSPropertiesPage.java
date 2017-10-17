/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.wizard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import net.sf.jasperreports.engine.JRPropertiesUtil.PropertySuffix;

import com.jaspersoft.studio.data.adapter.IReportDescriptor;
import com.jaspersoft.studio.preferences.util.PreferencesUtils;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;
import com.jaspersoft.studio.wizards.ContextHelpIDs;

/**
 * Page of the wizard used to import the properties from a Jaspersoft Studio Workspace into
 * the actual one
 * 
 * @author Orlandin Marco
 *
 */
public class JSSPropertiesPage extends ShowPropertiesPage {
	
	protected JSSPropertiesPage() {
		super();
	}
	
	/**
	 * Return the value of a property
	 * 
	 * @param key key of the property
	 * @return value of the property
	 */
	public String getProperyValue(String key){
		return prop.getProperty(key);
	}
	
	/**
	 *  Return the keys of all the properties available for the import.
	 * The properties are deserialized from the string inside the configuration file. At this properties 
	 * are also added the default one if they are not specified
	 */
	@Override
	protected Collection<Object> getInFields(){
		List<Object> readKeys = new ArrayList<Object>();
		IReportDescriptor selectedConfig = ((ImportJSSAdapterWizard)getWizard()).getSelectedConfiguration();
		String propertyString = selectedConfig.getConfiguration().getProperty(PreferencesUtils.NET_SF_JASPERREPORTS_JRPROPERTIES);
		prop = PreferencesUtils.loadJasperReportsProperties(propertyString);
		Set<String> storedKeys = prop.stringPropertyNames();
		for(String key :storedKeys){
				readKeys.add(key);
		}
		//Add the default one
		List<PropertySuffix> lst = PropertiesHelper.DPROP.getProperties("");
		for (PropertySuffix ps : lst) {
			if (prop.getProperty(ps.getKey()) == null) {
				readKeys.add(ps.getKey());
				prop.setProperty(ps.getKey(), ps.getValue());
			}
		}
		return readKeys;
	}
	
	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_IMPORT_WORKSPACE;
	}
}
