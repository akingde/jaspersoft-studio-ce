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
package com.jaspersoft.studio;

import org.w3c.dom.Node;

/**
 * Interface for a class used to migrated the old storage based 
 * on properties on the new storage based on the filesystem.
 * This interface recieve each node read from the properties
 * for a specific name and provide a file name for it
 * 
 * @author Orlandin Marco
 *
 */
public interface IConversionFilenameProvider {
	
	/**
	 * Return the name for the configuration of a single element
	 * 
	 * @param configurationElementNode the converted element from the properties file
	 * @return the filename for the entry in the new storage. The name should be not null
	 * and a valid name (no invalid char and unique filename) for the current filesystem. 
	 */
	public String getFileName(Node configurationElementNode);
	
}
