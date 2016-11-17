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
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.JaspersoftStudioPlugin;

public class PostSetValueManager {
	public void init() {
		IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(
				JaspersoftStudioPlugin.PLUGIN_ID, "postsetvalue"); //$NON-NLS-1$  
		for (IConfigurationElement e : config) {
			try {
				Object o = e.createExecutableExtension("ClassFactory"); //$NON-NLS-1$
				if (o instanceof IPostSetValue)
					nodeFactory.add((IPostSetValue) o);
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	private List<IPostSetValue> nodeFactory = new ArrayList<IPostSetValue>();

	public List<Command> postSetValue(IPropertySource target, Object prop, Object newValue, Object oldValue) {
		List<Command> cmd = null;
		for (IPostSetValue psv : nodeFactory) {
			Command postSetValue = psv.postSetValue(target, prop, newValue, oldValue);
			if (postSetValue != null) {
				if (cmd == null)
					cmd = new ArrayList<Command>();
				cmd.add(postSetValue);
			}
		}
		return cmd;
	}
}
