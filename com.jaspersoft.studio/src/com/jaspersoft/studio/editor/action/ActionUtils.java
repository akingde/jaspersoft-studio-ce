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
package com.jaspersoft.studio.editor.action;

import java.util.List;

import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;

/**
 * Utility methods for actions, menu and action registry.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class ActionUtils {

	/**
	 * Appends a list of actions to an existing menu group.
	 * 
	 * @param menuMgr the menu manager
	 * @param actionIDs the list of action IDs
	 * @param registry the action registry
	 * @param groupName the group name
	 */
	public static void appendActionToGroup(
			IMenuManager menuMgr, List<?> actionIDs, ActionRegistry registry, String groupName) {
		for(Object actionID : actionIDs){
			IAction action = registry.getAction(actionID);
			if(action!=null && action.isEnabled()) {
				menuMgr.appendToGroup(groupName, action);
			}
		}
	}
	
}
