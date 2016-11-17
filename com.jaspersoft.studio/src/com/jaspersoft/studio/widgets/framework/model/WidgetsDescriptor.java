/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.widgets.framework.manager.panel.IPanelManager;

/**
 * Definition a series of dynamic widgets. A set of wdigets is composed of 
 * a label, a description and a set of sections (each section will contains 
 * the single widgets)
 * 
 * @author Orlandin Marco
 *
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WidgetsDescriptor {
	
	private String label;
	
	private String description;
	
	private String panelManagerClass;
	
	private List<SectionPropertyDescriptor> sections;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<SectionPropertyDescriptor> getSections() {
		if (sections == null){
			sections = new ArrayList<SectionPropertyDescriptor>();
		}
		return sections;
	}

	public void setSections(List<SectionPropertyDescriptor> sections) {
		this.sections = sections;
	}
	
	public String getPanelManagerClass(){
		return panelManagerClass;
	}
	
	public void setPanelManagerClass(String className){
		panelManagerClass = className;
	}

	/**
	 * By default there is not localization support, the implementation can override
	 * to provide localization
	 * 
	 * @param key the key of the string to translate
	 * @return the string to use
	 */
	public String getLocalizedString(String key) {
		return key;
	}
	
	/**
	 * Return all the widgets in every section in a plain view. Essentially 
	 * all the widgets are aggregated and displayed at one
	 * 
	 * @return a not null list of widgets
	 */
	public List<WidgetPropertyDescriptor> getPlainWidgets(){
		List<WidgetPropertyDescriptor> result = new ArrayList<WidgetPropertyDescriptor>();
		for(SectionPropertyDescriptor section : getSections()){
			for(WidgetPropertyDescriptor widget : section.getProperties()){
				result.add(widget);
			}
		}
		return result;
	}
	
	/**
	 * Check if the descriptor has widgets to shown
	 * 
	 * @return true if it has at least on widget, false otherwise
	 */
	public boolean hasWidgets(){
		for(SectionPropertyDescriptor section : getSections()){
			if(!section.getProperties().isEmpty()){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Return an instance of the loaded panel manager. The panel manager must have a constructor with a composite
	 * that will be used a parent of the controls
	 * 
	 * @param parent the composite used to instantiate and {@link IPanelManager}
	 * @return and {@link IPanelManager} if it is defined, or null if it is not defined or it is defined but can not be loaded
	 */ 
	public IPanelManager getPanelManager(Composite parent){
		if (panelManagerClass != null){
			try{
				Class<?> panelManager = Class.forName(panelManagerClass);
				if (IPanelManager.class.isAssignableFrom(panelManager)){
					IPanelManager result = (IPanelManager) panelManager.getConstructor(Composite.class).newInstance(parent);
					return result;
				}
			} catch (Error err){
				JaspersoftStudioPlugin.getInstance().logError(err);
			} catch (Exception ex){
				JaspersoftStudioPlugin.getInstance().logError(ex);
			}
		}
		return null;
	}

}
