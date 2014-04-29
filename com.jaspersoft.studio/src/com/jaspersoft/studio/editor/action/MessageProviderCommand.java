/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.action;


/**
 * Some commands, like the delete command can return a message to indicate if their execution can 
 * have impact on other object. This method can be used from an action to know if a command has 
 * "something to say" about its execution and eventually ask a confirm with a prompt
 * 
 * @author Orlandin Marco
 *
 */
public interface MessageProviderCommand {
	
	/**
	 * Return a textual message if the command want to notify something or null otherwise
	 * @return  a textual message if the command want to notify something or null otherwise
	 */
		public String getMessage();
		
}
