/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;

/**
 * A single widget definition, it contains all the informations to build 
 * a {@link ItemPropertyDescription} from this
 * 
 * @author Orlandin Marco
 *
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WidgetPropertyDescriptor {
	
	private String name;
	
	private String label;
	
	private String description;
	
	private boolean mandatory;
	
	private String defaultValue;
	
	private String fallbackValue;
	
	private boolean readOnly = false;
	
	private String min;
	
	private String max;
	
	private String type;
	
	private String[][] comboOptions;

	/**
	 * get the combo options, they are structured always an array of array,
	 * (Typically a String[][2]), where the first array is the number of options
	 * in the combo and the second one are the pair stored value/shown string
	 */
	public String[][] getComboOptions() {
		return comboOptions;
	}
	
	public String[] getComboValues(){
		String[][] comboOptions = getComboOptions();
		if (comboOptions != null){
			String[] comboValues = new String[comboOptions.length];
			for(int i = 0; i < comboOptions.length; i++){
				comboValues[i] = comboOptions[i][0];
			}
			return comboValues;
		}
		return new String[0];
	}
	
	public String[] getComboTexts(){
		String[][] comboOptions = getComboOptions();
		if (comboOptions != null){
			String[] comboTexts = new String[comboOptions.length];
			for(int i = 0; i < comboOptions.length; i++){
				comboTexts[i] = comboOptions[i][1];
			}
			return comboTexts;
		}
		return new String[0];
	}

	/**
	 * Set the combo options, they are structured always an array of array,
	 * (Typically a String[][2]), where the first array is the number of options
	 * in the combo and the second one are the pair stored value/shown string
	 * 
	 * @param comboOptions typically an String[][2]
	 */
	public void setComboOptions(String[][] comboOptions) {
		this.comboOptions = comboOptions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFallbackValue() {
		return fallbackValue;
	}

	public void setFallbackValue(String fallbackValue) {
		this.fallbackValue = fallbackValue;
	}
}
