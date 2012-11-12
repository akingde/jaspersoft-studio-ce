/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
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

	public List<Command> postSetValue(IPropertySource target, Object prop, Object value) {
		List<Command> cmd = null;
		for (IPostSetValue psv : nodeFactory) {
			Command postSetValue = psv.postSetValue(target, prop, value);
			if (postSetValue != null) {
				if (cmd == null)
					cmd = new ArrayList<Command>();
				cmd.add(postSetValue);
			}
		}
		return cmd;
	}
}
