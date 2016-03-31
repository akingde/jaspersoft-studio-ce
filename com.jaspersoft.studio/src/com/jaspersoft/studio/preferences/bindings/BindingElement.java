/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
	
	public String getContext(){
		return context;
	}
	
	/**
	 * Return the default sequence to enable the binded action
	 * 
	 * @return a not null sequence of Keys
	 */
	public JSSKeySequence getDefault(){
		return defaultValue;
	}
}