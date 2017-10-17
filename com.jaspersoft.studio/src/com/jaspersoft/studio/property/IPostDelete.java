/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.ANode;

/**
 * Interface to implement to define a post delete action. This commands are
 * generated when a delete command is executed
 * 
 * @author Orlandin Marco
 *
 */
public interface IPostDelete {
	
	/**
	 * Generate a post delete command
	 * 
	 * @param target the deleted element
	 * @param parent the parent of the delete element
	 * @return a command to execute after the delete action or null if no command should be executed
	 */
	public Command postDelete(ANode target, ANode parent);
}
