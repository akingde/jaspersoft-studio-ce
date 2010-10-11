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
package com.jaspersoft.studio;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.ANode;

public class ExtensionManager {
	public void init() {
		IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(
				"com.jaspersoft.studio", "components");
		for (IConfigurationElement e : config) {
			try {
				Object o = e.createExecutableExtension("ClassFactory");
				if (o instanceof IComponentFactory)
					nodeFactory.add((IComponentFactory) o);
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	private List<IComponentFactory> nodeFactory = new ArrayList<IComponentFactory>();

	public ANode createNode(ANode parent, Object jrObject, int newIndex) {
		for (IComponentFactory f : nodeFactory) {
			ANode n = f.createNode(parent, jrObject, newIndex);
			if (n != null)
				return n;
		}
		return null;
	}

	public List<?> getChildren4Element(Object jrObject) {
		for (IComponentFactory f : nodeFactory) {
			List<?> lst = f.getChildren4Element(jrObject);
			if (lst != null)
				return lst;
		}
		return null;
	}

	public List<Class<?>> getPaletteEntries() {
		List<Class<?>> list = new ArrayList<Class<?>>();
		for (IComponentFactory f : nodeFactory) {
			List<Class<?>> lst = f.getPaletteEntries();
			if (lst != null)
				list.addAll(lst);
		}
		return list;
	}

	public Command getCreateCommand(ANode parent, ANode child, Point location, int newIndex) {
		for (IComponentFactory f : nodeFactory) {
			Command c = f.getCreateCommand(parent, child, location, newIndex);
			if (c != null)
				return c;
		}
		return null;
	}
}
