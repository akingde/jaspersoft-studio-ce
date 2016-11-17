/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.bindings;

/**
 * Definition of a binding element
 * 
 * @author Orlandin Marco
 */
public class BindingElement {
	
	/**
	 * The id of the binding action
	 */
	private String id;
	
	/**
	 * The name of the binding action
	 */
	private String name;
	
	/**
	 * The description of the binding action
	 */
	private String description;
	
	/**
	 * The context in which this binding is executed
	 */
	private String context;
	
	/**
	 * The default sequence to enable the binded action
	 */
	private JSSKeySequence defaultValue;

	public BindingElement(String id, String name, String description, String context, JSSKeySequence defaultValue) {
		this.name = name;
		this.description = description;
		this.id = id;
		this.context = context;
		this.defaultValue = defaultValue;
	}

	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}


	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Return the binding of the current element
	 * 
	 * @return a not string to define when this binding is active 
	 */
	public String getContext(){
		return context;
	}
	
	/**
	 * Return the default sequence to enable the binded action
	 * 
	 * @return a not null sequence of strokes, defined as default keys
	 * for this binding
	 */
	public JSSKeySequence getDefault(){
		return defaultValue;
	}
}
