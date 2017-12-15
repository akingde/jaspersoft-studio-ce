/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.customadapters.ui;

import java.lang.reflect.Constructor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jaspersoft.studio.data.customadapters.IAdapterPropertyHandler;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

/**
 * Class used to deserialize a data adapter definition file
 * 
 * @author Orlandin Marco
 *
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AdapterWidgetsDescriptor extends WidgetsDescriptor {
	
	/**
	 * The class of the data adapter
	 */
	private String adapterClass;
	
	/**
	 * The optional implementation of the {@link IAdapterPropertyHandler}
	 */
	private String propertyHandlerClass;

	/**
	 * The path of the icon
	 */
	private String iconPath;
	
	/**
	 * The description of the data adapter
	 */
	private String description;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAdapterClass(){
		return adapterClass;
	}
	
	public void setAdapterClass(String adapterClass){
		this.adapterClass = adapterClass;
	}

	@Override
	public String toString() {
		return getLabel();
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
	
	public String getPropertySetterClass() {
		return propertyHandlerClass;
	}

	public void setPropertyHandlerClass(String propertyHandlerClass) {
		this.propertyHandlerClass = propertyHandlerClass;
	}
	
	/**
	 * If defined return the {@link IAdapterPropertyHandler} to handle the get and
	 * set of the properties of this data adapter
	 * 
	 * @param jConfig a not null {@link JasperReportsConfiguration} used to load and instantiate the {@link IAdapterPropertyHandler} class
	 * @return a {@link IAdapterPropertyHandler} object or null if the class is not defined or can not be loaded
	 */
	@JsonIgnore
	public IAdapterPropertyHandler getAdapterPropertyHandler(JasperReportsConfiguration jConfig) {
		String propertySetterClass = getPropertySetterClass();
		if (propertySetterClass != null && !propertySetterClass.isEmpty()) {
			try {
				Class<?> clazz = jConfig.getClassLoader().loadClass(propertySetterClass);
				Constructor<?> ctor = clazz.getConstructor();
				return (IAdapterPropertyHandler)ctor.newInstance();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
}
