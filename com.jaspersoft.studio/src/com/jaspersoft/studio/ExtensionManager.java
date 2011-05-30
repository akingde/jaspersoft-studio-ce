/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.part.WorkbenchPart;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterFactory;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.model.ANode;

public class ExtensionManager {
	public void init() {
		IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(
				"com.jaspersoft.studio", "components"); //$NON-NLS-1$ //$NON-NLS-2$
		for (IConfigurationElement e : config) {
			try {
				Object o = e.createExecutableExtension("ClassFactory"); //$NON-NLS-1$
				if (o instanceof IComponentFactory)
					nodeFactory.add((IComponentFactory) o);
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}

		// List all the extensions that provide a DataAdapterFactory
		config = Platform.getExtensionRegistry().getConfigurationElementsFor("com.jaspersoft.studio", "dataAdapters"); //$NON-NLS-1$ //$NON-NLS-2$
		for (IConfigurationElement e : config) {
			try {
				Object o = e.createExecutableExtension("ClassFactory"); //$NON-NLS-1$
				if (o instanceof DataAdapterFactory)
					DataAdapterManager.addDataAdapterFactory((DataAdapterFactory) o);
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}

		DataAdapterManager.loadDataAdapters();

		for (DataAdapterDescriptor da : DataAdapterManager.getDataAdapters()) {
			System.out.println(da.toXml());
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
			if (lst != null && !lst.isEmpty())
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

	public Command getCreateCommand(ANode parent, ANode child, Rectangle location, int newIndex) {
		for (IComponentFactory f : nodeFactory) {
			Command c = f.getCreateCommand(parent, child, location, newIndex);
			if (c != null)
				return c;
		}
		return null;
	}

	public Command getDeleteCommand(ANode parent, ANode child) {
		for (IComponentFactory f : nodeFactory) {
			Command c = f.getDeleteCommand(parent, child);
			if (c != null)
				return c;
		}
		return null;
	}

	public Command getReorderCommand(ANode parent, ANode child, int newIndex) {
		for (IComponentFactory f : nodeFactory) {
			Command c = f.getReorderCommand(child, parent, newIndex);
			if (c != null)
				return c;
		}
		return null;
	}

	public Command getOrphanCommand(ANode parent, ANode child) {
		for (IComponentFactory f : nodeFactory) {
			Command c = f.getOrphanCommand(parent, child);
			if (c != null)
				return c;
		}
		return null;
	}

	public IFigure createFigure(ANode node) {
		for (IComponentFactory f : nodeFactory) {
			IFigure c = f.createFigure(node);
			if (c != null)
				return c;
		}
		return null;
	}

	public EditPart createEditPart(EditPart context, Object model) {
		for (IComponentFactory f : nodeFactory) {
			EditPart c = f.createEditPart(context, model);
			if (c != null)
				return c;
		}
		return null;
	}

	public List<Action> getActions(WorkbenchPart part) {
		List<Action> lst = new ArrayList<Action>();
		for (IComponentFactory f : nodeFactory) {
			List<Action> l = f.getActions(part);
			if (l != null && !l.isEmpty())
				lst.addAll(l);
		}
		return lst;
	}

	public List<String> getActionIDs() {
		List<String> lst = new ArrayList<String>();
		for (IComponentFactory f : nodeFactory) {
			List<String> l = f.getActionsID();
			if (l != null && !l.isEmpty())
				lst.addAll(l);
		}
		return lst;
	}

	public AbstractVisualEditor getEditor(Object parent) {
		for (IComponentFactory f : nodeFactory) {
			AbstractVisualEditor n = f.getEditor(parent);
			if (n != null)
				return n;
		}
		return null;
	}
}
