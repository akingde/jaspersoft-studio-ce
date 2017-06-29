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
package com.jaspersoft.studio.editor.action.exporter;

/**
 * A definition for a resource that can be exported trough the import/export 
 * configuration function
 * 
 * @author Orlandin Marco
 *
 */
public interface IResourceDefinition {

	/**
	 * Return the name of the resource exported
	 * 
	 * @return a not null human readable name
	 */
	public String getName();
	
	/**
	 * Return an object that is used during the import/export procedure to 
	 * identify the resource that need to be imported and exported. The type
	 * of the object and how it is used depends by the importer itslef
	 * 
	 * @return a generic identifier
	 */
	public Object getData();
	
}
