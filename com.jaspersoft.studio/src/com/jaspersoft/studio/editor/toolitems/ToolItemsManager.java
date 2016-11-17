/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.toolitems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.IPreferenceStore;

import com.jaspersoft.studio.JaspersoftStudioPlugin;



public class ToolItemsManager {
	
	/**
	 * Map with the default visibilities for the contributed controls. If an item is 
	 * not in this map then its default visibility is false
	 */
	private static final Map<String, Boolean> staticDefaultValues = new HashMap<String, Boolean>();
	
	static{
		staticDefaultValues.put("com.jaspersoft.studio.graphic.bring", false);
		staticDefaultValues.put("com.jaspersoft.studio.graphic.text", true);
		staticDefaultValues.put("com.jaspersoft.studio.graphic.create", true);
		staticDefaultValues.put("com.jaspersoft.studio.graphic.align", false);
		staticDefaultValues.put("com.jaspersoft.studio.graphic.size", true);
		staticDefaultValues.put("com.jaspersoft.studio.graphic.borders", false);
		staticDefaultValues.put("com.jaspersoft.studio.graphic.image", true);
		staticDefaultValues.put("com.jaspersoft.studio.graphic.exporters", false);
		staticDefaultValues.put("com.jaspersoft.studio.graphic.movebands", false);
		staticDefaultValues.put("com.jaspersoft.studio.components.tableactions", true);
		staticDefaultValues.put("com.jaspersoft.studio.components.crosstabaction", true);
		
	}
	
	/**
	 * Cache of the toolbar items loaded from the extension point
	 */
	private List<ToolItemsSet> sets = new ArrayList<ToolItemsSet>();

	/**
	 * Map to pair every control contributed to the toolbar with the id
	 * of the toolbar which it belong
	 */
	private Map<String, String> itemsSetMap = new HashMap<String, String>();

	public boolean isToolbarVisible(String controlId){
		IPreferenceStore store = JaspersoftStudioPlugin.getInstance().getPreferenceStore();
		String toolbarID = itemsSetMap.get(controlId);
		if (toolbarID == null) return false;
		else {
			return store.getBoolean(toolbarID);
		}
	}
	
	public void init() {
		IConfigurationElement[] cToolItemSets = Platform.getExtensionRegistry().getConfigurationElementsFor("com.jaspersoft.studio.toolitemsets");
		for (IConfigurationElement toolbar : cToolItemSets) {
			IConfigurationElement[] controls = toolbar.getChildren("toolitem");
			String toolbarId = toolbar.getAttribute("id");
			ToolItemsSet set = new ToolItemsSet();
			set.setId(toolbarId);
			set.setName(toolbar.getAttribute("label"));
			//Set the default visibility
			Boolean defaultVisible = staticDefaultValues.get(toolbarId);
			if (defaultVisible != null) set.setVisible(defaultVisible);
			else set.setVisible(false);
				
			sets.add(set);
			for(IConfigurationElement control : controls){
				String controlId = control.getAttribute("id");
				itemsSetMap.put(controlId, toolbarId);
				set.addControlConfiguration(control);
			}
		}
	}
	
	public List<ToolItemsSet> getSets() {
		return sets;
	}
	
	public String getParentToolbarId(String controlId){
		return itemsSetMap.get(controlId);
	}

}
