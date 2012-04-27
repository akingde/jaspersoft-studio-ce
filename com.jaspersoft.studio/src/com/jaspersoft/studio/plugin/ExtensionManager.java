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
package com.jaspersoft.studio.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.WorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterFactory;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionEditorSupportFactory;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.repository.IRepositoryViewProvider;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ExtensionManager {
	public void init() {
		IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(
				JaspersoftStudioPlugin.PLUGIN_ID, "components"); //$NON-NLS-1$ //$NON-NLS-2$
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
		config = Platform.getExtensionRegistry().getConfigurationElementsFor(JaspersoftStudioPlugin.PLUGIN_ID, "dataAdapters"); //$NON-NLS-1$ //$NON-NLS-2$
		for (IConfigurationElement e : config) {
			try {
				Object o = e.createExecutableExtension("ClassFactory"); //$NON-NLS-1$
				if (o instanceof DataAdapterFactory)
					DataAdapterManager.addDataAdapterFactory((DataAdapterFactory) o);
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}

		config = Platform.getExtensionRegistry().getConfigurationElementsFor(JaspersoftStudioPlugin.PLUGIN_ID, "editorLifecycle"); //$NON-NLS-1$ //$NON-NLS-2$
		for (IConfigurationElement e : config) {
			try {
				Object o = e.createExecutableExtension("ClassFactory"); //$NON-NLS-1$
				if (o instanceof IEditorContributor)
					eContributor.add((IEditorContributor) o);
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}

		DataAdapterManager.getPreferencesStorage();
	}

	public List<IRepositoryViewProvider> getRepositoryProviders() {
		List<IRepositoryViewProvider> paletteGroup = new ArrayList<IRepositoryViewProvider>();
		IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(
				JaspersoftStudioPlugin.PLUGIN_ID, "repositoryview"); //$NON-NLS-1$ //$NON-NLS-2$
		for (IConfigurationElement e : config) {
			try {
				Object o = e.createExecutableExtension("ClassFactory"); //$NON-NLS-1$
				if (o instanceof IRepositoryViewProvider)
					paletteGroup.add((IRepositoryViewProvider) o);
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return paletteGroup;
	}
	
	/**
	 * Returns the support factory for the expression editor.
	 * 
	 * <p>
	 * The method seeks for a custom support factory or a default one (fallback).
	 * 
	 * @return the contributed support factory, null <code>otherwise</code>
	 */
	public IExpressionEditorSupportFactory getExpressionEditorSupportFactory(){
		IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(
				JaspersoftStudioPlugin.PLUGIN_ID, "expressionEditorSupport"); //$NON-NLS-1$ //$NON-NLS-2$
		IExpressionEditorSupportFactory defaultFactory=null;
		boolean defaultFound=false;
		boolean overrideFound=true;
		for(IConfigurationElement el : config){
			if(!defaultFound && "false".equals(el.getAttribute("override"))){
				Object defaultSupportClazz;
				try {
					defaultSupportClazz = el.createExecutableExtension("class");
					if(defaultSupportClazz instanceof IExpressionEditorSupportFactory){
						defaultFactory=(IExpressionEditorSupportFactory) defaultSupportClazz;
					}
				} catch (CoreException e) {
					JaspersoftStudioPlugin.getInstance().getLog().log(
							new Status(IStatus.ERROR, JaspersoftStudioPlugin.PLUGIN_ID, "An error occurred while trying to create the new class.", e));
				}
			}
			else {
				if(!overrideFound && "true".equals(el.getAttribute("override"))){
					overrideFound=true;
					Object overrideClazz;
					try {
						overrideClazz = el.createExecutableExtension("class");
						if(overrideClazz instanceof IExpressionEditorSupportFactory){
							return (IExpressionEditorSupportFactory) overrideClazz;
						}
					} catch (CoreException e) {
						JaspersoftStudioPlugin.getInstance().getLog().log(
								new Status(IStatus.ERROR, JaspersoftStudioPlugin.PLUGIN_ID, "An error occurred while trying to create the new class.", e));
					}
				}
			}
		}

		return defaultFactory;
	}

	public List<PaletteGroup> getPaletteGroups() {
		List<PaletteGroup> paletteGroup = new ArrayList<PaletteGroup>();
		IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(
				JaspersoftStudioPlugin.PLUGIN_ID, "palette"); //$NON-NLS-1$ //$NON-NLS-2$
		for (IConfigurationElement e : config) {
			PaletteGroup p = new PaletteGroup();
			p.setId(e.getAttribute("id")); //$NON-NLS-1$
			p.setName(e.getAttribute("Name")); //$NON-NLS-1$
			p.setImage(e.getAttribute("image")); //$NON-NLS-1$
			p.setAfterGroup(e.getAttribute("afterGroup")); //$NON-NLS-1$
			paletteGroup.add(p);
		}
		return paletteGroup;
	}

	public Map<String, List<PaletteEntry>> getPaletteEntries() {
		Map<String, List<PaletteEntry>> map = new HashMap<String, List<PaletteEntry>>();
		for (IComponentFactory f : nodeFactory) {
			IPaletteContributor ipc = f.getPaletteEntries();
			if (ipc != null) {
				Map<String, List<PaletteEntry>> paletteEntries = ipc.getPaletteEntries();
				if (paletteEntries != null) {
					for (String key : paletteEntries.keySet()) {
						List<PaletteEntry> ol = map.get(key);
						if (ol == null)
							map.put(key, paletteEntries.get(key));
						else
							ol.addAll(paletteEntries.get(key));
					}
				}
			}
		}
		return map;
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

	public AbstractVisualEditor getEditor(Object parent, JasperReportsConfiguration jrContext) {
		for (IComponentFactory f : nodeFactory) {
			AbstractVisualEditor n = f.getEditor(parent, jrContext);
			if (n != null)
				return n;
		}
		return null;
	}

	private List<IEditorContributor> eContributor = new ArrayList<IEditorContributor>();

	public void onLoad(JasperDesign jd, EditorPart editor) {
		for (IEditorContributor f : eContributor)
			f.onLoad(jd, editor);
	}

	public void onSave(JasperReportsConfiguration jrConfig) {
		for (IEditorContributor f : eContributor)
			f.onSave(jrConfig);
	}

	public void onRun() {
		for (IEditorContributor f : eContributor)
			f.onRun();
	}

	public List<AContributorAction> getActions() {
		List<AContributorAction> list = new ArrayList<AContributorAction>();
		for (IEditorContributor f : eContributor) {
			AContributorAction[] actions = f.getActions();
			for (AContributorAction a : actions)
				list.add(a);
		}
		return list;
	}

	public ExpressionContext getExpressionContext4Element(Object jrObject){
		for(IComponentFactory f:nodeFactory){
			ExpressionContext exprContext = f.getElementExpressionContext(jrObject);
			if(exprContext!=null) return exprContext;
		}
		return null;
	}
	
}
