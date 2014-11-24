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
package com.jaspersoft.studio.custom.adapter;

/**
 * Container to store the full name of a class, so in the form
 * package.classname
 * 
 * @author Orlandin Marco
 *
 */
public class Pair{
	
	/**
	 * The package value
	 */
	private String packageName;

	/**
	 * The simple name of the class
	 */
	private String className;

	/**
	 * Create the container 
	 * 
	 * @param packageName the package value
	 * @param className the simple name of the class
	 */
	public Pair(String packageName, String className) {
		this.packageName = packageName;
		this.className = className;
	}
	
	/**
	 * Return the package name of the class
	 * 
	 * @return the package as string
	 */
	public String getPackageName() {
		return packageName;
	}
	
	/**
	 * Return the simple class name
	 * 
	 * @return the class name, without the package
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * Return the complete classname in the form package.classname
	 */
	@Override
	public String toString() {
		return packageName + "." + className;
	}
}