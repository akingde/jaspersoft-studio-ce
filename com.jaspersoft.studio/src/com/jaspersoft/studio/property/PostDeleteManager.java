/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.ANode;

/**
 * Manager to collect the contributed post delete actions. It works in a similar 
 * way to the post property set actions.
 * 
 * @author Orlandin Marco
 */
public class PostDeleteManager {
	
	private List<IPostDelete> nodeFactory = new ArrayList<IPostDelete>();
	
	public void init() {
		IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(JaspersoftStudioPlugin.PLUGIN_ID, "postDelete"); //$NON-NLS-1$  
		for (IConfigurationElement e : config) {
			try {
				Object o = e.createExecutableExtension("class"); //$NON-NLS-1$
				if (o instanceof IPostDelete){
					nodeFactory.add((IPostDelete) o);
				}
			} catch (CoreException ex) {
				ex.printStackTrace();
				JaspersoftStudioPlugin.getInstance().logError(ex);
			}
		}
	}

	/**
	 * Generate the command to execute for a post delete
	 * 
	 * @param target the target node that was deleted
	 * @param parent the container of the deleted node
	 * @return a list of commands to execute, it can be null
	 */
	public List<Command> postDelete(ANode target, ANode parent) {
		List<Command> cmd = null;
		for (IPostDelete psv : nodeFactory) {
			Command postSetValue = psv.postDelete(target, parent);
			if (postSetValue != null) {
				if (cmd == null)
					cmd = new ArrayList<Command>();
				cmd.add(postSetValue);
			}
		}
		return cmd;
	}
}
