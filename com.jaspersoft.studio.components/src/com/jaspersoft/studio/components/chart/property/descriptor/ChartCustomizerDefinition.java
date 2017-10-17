/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.descriptor;

import com.jaspersoft.studio.components.chart.property.widget.CustomizerWidgetsDescriptor;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

import net.sf.jasperreports.engine.JRChartCustomizer;
import net.sf.jasperreports.engine.util.JRClassLoader;

/**
 * Class to contains the information of a single chart customizer set on the element. The 
 * definition could be a complex chart customizer with a custom UI or a simple customizer 
 * class without UI
 * 
 * @author Orlandin Marco
 *
 */
public class ChartCustomizerDefinition {

	/**
	 * The name of a chart customizer
	 */
	private String name;
	
	/**
	 * The descriptor of the widget controls for the customizer, can be null if no
	 * custom ui is provided
	 */
	private CustomizerWidgetsDescriptor definition;
	
	/**
	 * The class name of a raw customizer, it can be null if the definition is provided
	 * trough a {@link CustomizerWidgetsDescriptor}
	 */
	private String classDefinition;
	
	/**
	 * The key of the customizer
	 */
	private String key;
	
	/**
	 * Flag to know if this custmizer definition was loaded from the proeprties of the element
	 * or from its custmizer class
	 */
	private boolean isPropertiesCustomizer;
	
	/**
	 * Create the customizer definition for a customizer that provide also a UI. The flag to know
	 * if the element was loaded from the properties will have a default value true
	 * 
	 * @param definition A not null {@link CustomizerWidgetsDescriptor} that define the ui and the class of this customizer
	 * @param key The key of the customizer, it will be something like "chartcustomizer.customizerX." where X is an unique associated
	 * to the properties of this specific customizer
	 */
	public ChartCustomizerDefinition(CustomizerWidgetsDescriptor definition, String key) {
		this.definition = definition;
		this.name = definition.getLabel();
		classDefinition = null;
		isPropertiesCustomizer = true;
		this.key = key;
	}
	
	/**
	 * Create the customizer definition for a customizer that is only a raw class
	 * 
	 * @param definition A not null and a full classname that handle this customizer, it should be and implementation of {@link JRChartCustomizer}
	 * @param key The key of the customizer, it will be something like "chartcustomizer.customizerX." where X is an unique associated
	 * to the properties of this specific customizer
	 * @param true if the customizer was loaded from the properties of the element, false otherwise
	 */
	public ChartCustomizerDefinition(String classDefinition, String key, boolean isPropertiesCustmizer) {
		this.definition = null;
		this.classDefinition = classDefinition;
		try{
			this.name = JRClassLoader.loadClassForName(classDefinition).getSimpleName();	
		} catch (Exception ex){
			String[] splitClassname = classDefinition.split("\\.");
			this.name = splitClassname[splitClassname.length-1];
		}	
		this.key = key;
		this.isPropertiesCustomizer = isPropertiesCustmizer;
	}
	
	/**
	 * Check if the customizer is a raw class definition or provided also a UI
	 * 
	 * @return true if it is only a class name, false if it provide also a UI
	 */
	public boolean isOnlyClass(){
		return classDefinition != null;
	}
	
	/**
	 * Get the UI descriptor
	 * 
	 * @return a {@link WidgetsDescriptor}, can be null is isOnlyClass return true
	 */
	public WidgetsDescriptor getDescriptor(){
		return definition;
	}

	/**
	 * Get the classname of the customizer
	 * 
	 * @return a not null string
	 */
	public String getCustomizerClass(){
		if (isOnlyClass()){ 
			return classDefinition;
		} else {
			return definition.getCustomizerClass();
		}
	}
	
	@Override
	public String toString() {
		if (isOnlyClass()){
			return name + "[user defined]";
		} else {
			return name;
		}
	}
	
	/**
	 * Return the key  of this customizer
	 * 
	 * @return a not null key of the customizer
	 */
	public String getKey(){
		return key;
	}
	
	/**
	 * Return the raw class of the customizer
	 * 
	 * @return a full class name, it is null if isOnlyClass return false
	 */
	public String getRawClass(){
		return classDefinition;
	}
	
	/**
	 * Check if the definition was loaded from the properties of an element
	 * 
	 * @return true if the definition was loaded from the properties, false
	 * if it was loaded from the old customizer class
	 */
	public boolean isPropertiesCustomizer(){
		return isPropertiesCustomizer;
	}

	/**
	 * Clone this customizer
	 */
	public ChartCustomizerDefinition clone(){
		if (isOnlyClass()){ 
			return new ChartCustomizerDefinition(classDefinition, key, isPropertiesCustomizer());
		} else {
			return new ChartCustomizerDefinition(definition, key);
		}
	}
	
	/**
	 * Two customizers are equals when all their fields are equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ChartCustomizerDefinition){
			ChartCustomizerDefinition o2 = (ChartCustomizerDefinition)obj;
			return (ModelUtils.safeEquals(key, o2.key) &&
						ModelUtils.safeEquals(definition, o2.definition) &&
							ModelUtils.safeEquals(classDefinition, o2.classDefinition));
		}
		return false;
	}
}
