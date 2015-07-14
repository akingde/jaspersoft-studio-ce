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
package com.jaspersoft.studio.editor.tools;

/**
 * Interface to define a listener called when the custom toolset is modified
 * 
 * @author Orlandin Marco
 *
 */
public interface ToolModfiedListener {

	/**
	 * The type of operation that has modified the toolset
	 * 
	 * @author Orlandin Marco
	 *
	 */
	public enum OPERATION_TYPE{DELETE, ADD, EDIT};
	
	/**
	 * Method called to notify that some tool in the toolset is changed
	 * 
	 * @param tool the tool that is changed
	 * @param operation define if the tool was added, removed or edited
	 */
	public void toolChanged(MCustomTool tool, OPERATION_TYPE operation);
	
	
}
