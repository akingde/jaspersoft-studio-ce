/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.jface.action.Action;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.WorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterFactory;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.designer.AQueryDesigner;
import com.jaspersoft.studio.data.designer.IParameterICContributor;
import com.jaspersoft.studio.data.jdbc.JDBCDriverDefinition;
import com.jaspersoft.studio.data.jdbc.JDBCDriverDefinitionsContainer;
import com.jaspersoft.studio.editor.IEditorContributor;
import com.jaspersoft.studio.editor.action.exporter.IExportedResourceHandler;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionEditorSupportFactory;
import com.jaspersoft.studio.editor.preview.PreviewModeDetails;
import com.jaspersoft.studio.editor.preview.view.report.system.AExporterFactory;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.jface.IFileSelection;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.util.HyperlinkDefaultParameter;
import com.jaspersoft.studio.preferences.bindings.BindingElement;
import com.jaspersoft.studio.preferences.bindings.JSSKeySequence;
import com.jaspersoft.studio.repository.IRepositoryViewProvider;
import com.jaspersoft.studio.statistics.IFirstStartupAction;
import com.jaspersoft.studio.style.view.TemplateViewProvider;
import com.jaspersoft.studio.swt.widgets.WHyperlink;
import com.jaspersoft.studio.swt.widgets.WHyperlink.UIElement;
import com.jaspersoft.studio.templates.TemplateProvider;
import com.jaspersoft.studio.utils.AContributorAction;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JasperDesign;

public class ExtensionManager {

	private static Map<Class<?>, IComponentFactory> factoryByNodeType = new HashMap<Class<?>, IComponentFactory>();

	private List<IEditorContributor> eContributor = new ArrayList<IEditorContributor>();

	private List<String> customHyperlinkTypes;

	private Map<String, List<HyperlinkDefaultParameter>> defaultHyperlinkParametersByCustomType;

	private Map<String, List<WHyperlink.UIElement>> uiElementsIDByCustomType;

	private static final Comparator<IConfigurationElement> extensionSorter = new Comparator<IConfigurationElement>() {

		@Override
		public int compare(IConfigurationElement o1, IConfigurationElement o2) {
			String contributor1 = o1.getContributor().getName();
			String contributor2 = o2.getContributor().getName();
			if (JaspersoftStudioPlugin.PLUGIN_ID.equals(contributor1)) {
				return 2;
			} else if (JaspersoftStudioPlugin.PLUGIN_ID.equals(contributor2)) {
				return -2;
			} else {
				int stringCompare = contributor1.compareTo(contributor2);
				if (stringCompare < 0)
					return -1;
				else if (stringCompare > 0)
					return 1;
				else
					return stringCompare;
			}
		}
	};
	private List<IParameterICContributor> prmICContributors = new ArrayList<IParameterICContributor>();

	public void init() {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(JaspersoftStudioPlugin.PLUGIN_ID, "components"); //$NON-NLS-1$
		for (IConfigurationElement e : config) {
			try {
				Object o = e.createExecutableExtension("ClassFactory"); //$NON-NLS-1$
				if (o instanceof IComponentFactory) {
					IComponentFactory compFactory = (IComponentFactory) o;
					nodeFactory.add(compFactory);
					for (Class<?> cl : compFactory.getKnownClasses()) {
						factoryByNodeType.put(cl, compFactory);
					}
				}
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}

		// List all the extensions that provide a DataAdapterFactory
		config = Platform.getExtensionRegistry().getConfigurationElementsFor(JaspersoftStudioPlugin.PLUGIN_ID,
				"dataAdapters"); //$NON-NLS-1$
		for (IConfigurationElement e : config) {
			try {
				Object o = e.createExecutableExtension("ClassFactory"); //$NON-NLS-1$
				if (o instanceof DataAdapterFactory)
					DataAdapterManager.addDataAdapterFactory((DataAdapterFactory) o);
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}

		config = Platform.getExtensionRegistry().getConfigurationElementsFor(JaspersoftStudioPlugin.PLUGIN_ID,
				"editorLifecycle"); //$NON-NLS-1$
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

	public void createParameterICUI(Composite parent, JRDesignParameter prm, AQueryDesigner designer) {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(JaspersoftStudioPlugin.PLUGIN_ID, "parameterIC"); //$NON-NLS-1$
		prmICContributors.clear();
		for (IConfigurationElement e : config) {
			try {
				prmICContributors.add((IParameterICContributor) e.createExecutableExtension("ICParameterContributor")); //$NON-NLS-1$
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}
		for (IParameterICContributor pic : prmICContributors)
			pic.createUI(parent, prm, designer);
	}

	public void refreshICUI(JRDesignParameter prm) {
		for (IParameterICContributor pic : prmICContributors)
			pic.refresh(prm);
	}

	public List<IRepositoryViewProvider> getRepositoryProviders() {
		List<IRepositoryViewProvider> paletteGroup = new ArrayList<IRepositoryViewProvider>();
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(JaspersoftStudioPlugin.PLUGIN_ID, "repositoryview"); //$NON-NLS-1$
		TreeMap<String, IRepositoryViewProvider> map = new TreeMap<String, IRepositoryViewProvider>();
		int i = 0;
		for (IConfigurationElement e : config) {
			try {
				String weight = e.getAttribute("ContextMenuWeight");
				if (weight == null) {
					weight = Integer.toString(i);
					i++;
				}
				Object o = e.createExecutableExtension("ClassFactory"); //$NON-NLS-1$
				if (o instanceof IRepositoryViewProvider)
					map.put(weight, (IRepositoryViewProvider) o);
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}
		for (Map.Entry<String, IRepositoryViewProvider> entry : map.entrySet())
			paletteGroup.add(entry.getValue());
		return paletteGroup;
	}

	/**
	 * Returns the list of {@link JDBCDriverDefinition} items contributed by different plug-ins through the
	 * extension-point <code>com.jaspersoft.studio.jdbcDriverDefinitions</code>.
	 * 
	 * @return the list of JDBC driver definitions contributed
	 */
	public List<JDBCDriverDefinition> getJDBCDriverDefinitions() {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(JaspersoftStudioPlugin.PLUGIN_ID, "jdbcDriverDefinitions"); //$NON-NLS-1$
		List<JDBCDriverDefinition> driverDefinitions = new ArrayList<JDBCDriverDefinition>();
		for (IConfigurationElement el : config) {
			JDBCDriverDefinitionsContainer container;
			try {
				Object clazz = el.createExecutableExtension("class");
				if (clazz instanceof JDBCDriverDefinitionsContainer) {
					container = (JDBCDriverDefinitionsContainer) clazz;
					driverDefinitions.addAll(container.getJDBCDriverDefinitions());
				}
			} catch (CoreException e) {
				JaspersoftStudioPlugin.getInstance().logError(e);
			}
		}
		return driverDefinitions;
	}

	/**
	 * Returns the support factory for the expression editor.
	 * 
	 * <p>
	 * The method seeks for a custom support factory or a default one (fallback).
	 * 
	 * @return the contributed support factory, null <code>otherwise</code>
	 */
	public IExpressionEditorSupportFactory getExpressionEditorSupportFactory() {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(JaspersoftStudioPlugin.PLUGIN_ID, "expressionEditorSupport"); //$NON-NLS-1$
		IExpressionEditorSupportFactory defaultFactory = null;
		boolean defaultFound = false;
		boolean overrideFound = true;
		for (IConfigurationElement el : config) {
			if (!defaultFound && "false".equals(el.getAttribute("override"))) {
				Object defaultSupportClazz;
				try {
					defaultSupportClazz = el.createExecutableExtension("class");
					if (defaultSupportClazz instanceof IExpressionEditorSupportFactory) {
						defaultFactory = (IExpressionEditorSupportFactory) defaultSupportClazz;
					}
				} catch (CoreException e) {
					JaspersoftStudioPlugin.getInstance().getLog().log(new Status(IStatus.ERROR, JaspersoftStudioPlugin.PLUGIN_ID,
							"An error occurred while trying to create the new class.", e));
				}
			} else {
				if (!overrideFound && "true".equals(el.getAttribute("override"))) {
					overrideFound = true;
					Object overrideClazz;
					try {
						overrideClazz = el.createExecutableExtension("class");
						if (overrideClazz instanceof IExpressionEditorSupportFactory) {
							return (IExpressionEditorSupportFactory) overrideClazz;
						}
					} catch (CoreException e) {
						JaspersoftStudioPlugin.getInstance().getLog().log(new Status(IStatus.ERROR,
								JaspersoftStudioPlugin.PLUGIN_ID, "An error occurred while trying to create the new class.", e));
					}
				}
			}
		}

		return defaultFactory;
	}

	/**
	 * Returns the list of contributed template provider, on the extension point templateProviderSupport
	 * 
	 * @return the list of contributed template provider, it can be empty but not null
	 */
	public List<TemplateProvider> getTemplateProviders() {

		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(JaspersoftStudioPlugin.PLUGIN_ID, "templateProviderSupport"); //$NON-NLS-1$

		ArrayList<TemplateProvider> providersList = new ArrayList<TemplateProvider>();
		for (IConfigurationElement el : config) {

			Object defaultSupportClazz;
			try {
				defaultSupportClazz = el.createExecutableExtension("providerClass");
				if (defaultSupportClazz instanceof TemplateProvider) {
					providersList.add((TemplateProvider) defaultSupportClazz);
				}
			} catch (CoreException e) {
				JaspersoftStudioPlugin.getInstance().getLog().log(new Status(IStatus.ERROR, JaspersoftStudioPlugin.PLUGIN_ID,
						"An error occurred while trying to create the new class.", e));
			}
		}
		return providersList;
	}

	/**
	 * A list of the contributed Tab to visualize a series of Template Styles
	 */
	private ArrayList<TemplateViewProvider> stylesViewList = null;

	/**
	 * Return a list of the contributed Tab to visualize a series of Template Styles. The read styles are cached after the
	 * first time they are red
	 * 
	 * @return a not null list of TemplateViewProvider
	 */
	public List<TemplateViewProvider> getStylesViewProvider() {
		if (stylesViewList == null) {
			IConfigurationElement[] config = Platform.getExtensionRegistry()
					.getConfigurationElementsFor(JaspersoftStudioPlugin.PLUGIN_ID, "stylesViewContributor");
			stylesViewList = new ArrayList<TemplateViewProvider>();
			for (IConfigurationElement el : config) {

				Object defaultSupportClazz;
				try {
					defaultSupportClazz = el.createExecutableExtension("providerClass");
					if (defaultSupportClazz instanceof TemplateViewProvider) {
						stylesViewList.add((TemplateViewProvider) defaultSupportClazz);
					}
				} catch (CoreException e) {
					JaspersoftStudioPlugin.getInstance().getLog().log(new Status(IStatus.ERROR, JaspersoftStudioPlugin.PLUGIN_ID,
							"An error occurred while trying to create the new class.", e));
				}
			}
		}
		return stylesViewList;
	}

	/**
	 * A list of the contributed exporter factories
	 */
	private ArrayList<AExporterFactory> exportersFactories = null;

	/**
	 * Return a list of the contributed exporter factories, that can be accessed from the preview. An exporter factory
	 * define how the generated report is exported to another format
	 * 
	 * @return a not null list of AExporterFactory
	 */
	public List<AExporterFactory> getExportersFactories() {
		if (exportersFactories == null) {
			IConfigurationElement[] config = Platform.getExtensionRegistry()
					.getConfigurationElementsFor(JaspersoftStudioPlugin.PLUGIN_ID, "exporterFactory");
			exportersFactories = new ArrayList<AExporterFactory>();
			for (IConfigurationElement el : config) {
				Object defaultSupportClazz;
				try {
					defaultSupportClazz = el.createExecutableExtension("factoryClass");
					if (defaultSupportClazz instanceof AExporterFactory) {
						AExporterFactory factory = (AExporterFactory) defaultSupportClazz;
						exportersFactories.add(factory);
						String rawSeparateBefore = el.getAttribute("separatorBefore");
						Boolean separatorBeofre = rawSeparateBefore == null ? false : Boolean.valueOf(rawSeparateBefore);
						factory.setSeparatorPlacedBefore(separatorBeofre);
					}
				} catch (CoreException e) {
					JaspersoftStudioPlugin.getInstance().getLog().log(new Status(IStatus.ERROR, JaspersoftStudioPlugin.PLUGIN_ID,
							"An error occurred while trying to create the new class.", e));
				}
			}
		}
		return exportersFactories;
	}

	/**
	 * Return a list of the contributed actions, this action are the one that must be executed the first time JSS is
	 * started. Between all the contributed actions are returned only the ones that are not overridden by another startup
	 * action.
	 * 
	 * @return a not null list of IFirstStartupAction
	 */
	public Collection<IFirstStartupAction> getFirstStartupActions() {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(JaspersoftStudioPlugin.PLUGIN_ID, "firstStartupActions");
		ArrayList<String> firstStartupActions = new ArrayList<String>();
		HashMap<String, IFirstStartupAction> actionMap = new HashMap<String, IFirstStartupAction>();
		HashSet<String> overridenAction = new HashSet<String>();
		for (IConfigurationElement el : config) {
			Object defaultSupportClazz;
			try {
				defaultSupportClazz = el.createExecutableExtension("actionClass");
				if (defaultSupportClazz instanceof IFirstStartupAction) {
					IFirstStartupAction action = (IFirstStartupAction) defaultSupportClazz;
					String actionID = el.getAttribute("actionId");
					firstStartupActions.add(actionID);
					actionMap.put(actionID, action);
					String overridenActionId = el.getAttribute("overrideActionId");
					if (overridenActionId != null) {
						overridenAction.add(overridenActionId);
					}
				}
			} catch (CoreException e) {
				JaspersoftStudioPlugin.getInstance().getLog().log(new Status(IStatus.ERROR, JaspersoftStudioPlugin.PLUGIN_ID,
						"An error occurred while trying to create the new class.", e));
			}
		}
		// Remove the overriden actions
		for (String actiondId : firstStartupActions) {
			if (overridenAction.contains(actiondId))
				actionMap.remove(actiondId);
		}
		return actionMap.values();
	}

	/**
	 * Static variable to cache the loaded binding after the first time they are requested
	 */
	private static HashMap<String, BindingElement> contributedBindings = null;

	/**
	 * Return a list of the contributed key bindings, these are the keybindings contributed to the studio keybindings
	 * manager. The key binding of a specific action can be read from the returned map using its id
	 * 
	 * @return a not null list Map of binding where the key is the id of the binded key and the value is the definition of
	 *         the binding key
	 */
	public static HashMap<String, BindingElement> getContributedBindings() {
		if (contributedBindings == null) {
			IConfigurationElement[] config = Platform.getExtensionRegistry()
					.getConfigurationElementsFor(JaspersoftStudioPlugin.PLUGIN_ID, "bindings");

			contributedBindings = new HashMap<String, BindingElement>();
			for (IConfigurationElement el : config) {
				try {
					String bindingID = el.getAttribute("id");
					String bindingSequence = el.getAttribute("sequence");
					String bindingContext = el.getAttribute("context");
					String bindinDescription = el.getAttribute("description");
					String bidningName = el.getAttribute("name");
					JSSKeySequence bindingKeySequence = JSSKeySequence.getInstance(bindingSequence);
					BindingElement element = new BindingElement(bindingID, bidningName, Misc.nvl(bindinDescription),
							bindingContext, bindingKeySequence);
					contributedBindings.put(bindingID, element);
				} catch (Exception ex) {
					ex.printStackTrace();
					JaspersoftStudioPlugin.getInstance().logError(ex);
				}
			}
		}
		return contributedBindings;
	}

	/**
	 * List of handler used to export or import Jaspersoft Studio resources. It is used as cache for the contributed items
	 */
	private static List<IExportedResourceHandler> contributedExporters = null;

	/**
	 * Return the List of handler used to export or import Jaspersoft Studio resources.
	 * 
	 * @return A not null list of IExportedResourceHandler
	 */
	public static List<IExportedResourceHandler> getContributedExporters() {
		if (contributedExporters == null) {
			IConfigurationElement[] config = Platform.getExtensionRegistry()
					.getConfigurationElementsFor(JaspersoftStudioPlugin.PLUGIN_ID, "resourceExporter");

			contributedExporters = new ArrayList<IExportedResourceHandler>();
			List<IConfigurationElement> configList = new ArrayList<IConfigurationElement>(Arrays.asList(config));
			Collections.sort(configList, extensionSorter);
			Collections.reverse(configList);
			for (IConfigurationElement el : configList) {
				Object defaultSupportClazz;
				try {
					defaultSupportClazz = el.createExecutableExtension("exporterClass");
					if (defaultSupportClazz instanceof IExportedResourceHandler) {
						IExportedResourceHandler handler = (IExportedResourceHandler) defaultSupportClazz;
						contributedExporters.add(handler);
					}
				} catch (CoreException e) {
					JaspersoftStudioPlugin.getInstance().getLog().log(new Status(IStatus.ERROR, JaspersoftStudioPlugin.PLUGIN_ID,
							"An error occurred while trying to create the new class.", e));
				}
			}
		}
		return contributedExporters;
	}

	public List<PaletteGroup> getPaletteGroups() {
		List<PaletteGroup> paletteGroup = new ArrayList<PaletteGroup>();
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(JaspersoftStudioPlugin.PLUGIN_ID, "palette"); //$NON-NLS-1$
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

	public List<IFileSelection> getFileSelectors() {
		List<IFileSelection> fileSelectors = new ArrayList<IFileSelection>();
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(JaspersoftStudioPlugin.PLUGIN_ID, "fileSelectors"); //$NON-NLS-1$
		for (IConfigurationElement e : config)
			try {
				Object o = e.createExecutableExtension("ClassFactory"); //$NON-NLS-1$
				if (o instanceof IFileSelection) {
					IFileSelection compFactory = (IFileSelection) o;
					fileSelectors.add(compFactory);
				}
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		return fileSelectors;
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

	public Command getStretchToContent(ANode node) {
		for (IComponentFactory f : getPrioritizedFactoryList(node)) {
			Command c = f.getStretchToContent(node);
			if (c != null)
				return c;
		}
		return null;
	}

	private List<IComponentFactory> getPrioritizedFactoryList(Object obj) {
		if (obj != null) {
			IComponentFactory selectedFactory = factoryByNodeType.get(obj.getClass());
			if (selectedFactory != null) {
				List<IComponentFactory> copyLst = new ArrayList<IComponentFactory>(nodeFactory.size());
				copyLst.addAll(nodeFactory);
				copyLst.remove(selectedFactory);
				copyLst.add(0, selectedFactory);
				return copyLst;
			}
		}
		return nodeFactory;
	}

	public Command getCreateCommand(ANode parent, ANode child, Rectangle location, int newIndex) {
		for (IComponentFactory f : getPrioritizedFactoryList(child)) {
			Command c = f.getCreateCommand(parent, child, location, newIndex);
			if (c != null) {
				return c;
			}
		}
		return null;
	}

	public Command getDeleteCommand(ANode parent, ANode child) {
		for (IComponentFactory f : getPrioritizedFactoryList(child)) {
			Command c = f.getDeleteCommand(parent, child);
			if (c != null)
				return c;
		}
		return null;
	}

	public Command getReorderCommand(ANode parent, ANode child, int newIndex) {
		for (IComponentFactory f : getPrioritizedFactoryList(child)) {
			Command c = f.getReorderCommand(child, parent, newIndex);
			if (c != null)
				return c;
		}
		return null;
	}

	public Command getOrphanCommand(ANode parent, ANode child) {
		for (IComponentFactory f : getPrioritizedFactoryList(child)) {
			Command c = f.getOrphanCommand(parent, child);
			if (c != null)
				return c;
		}
		return null;
	}

	public IFigure createFigure(ANode node) {
		for (IComponentFactory f : getPrioritizedFactoryList(node)) {
			IFigure c = f.createFigure(node);
			if (c != null)
				return c;
		}
		return null;
	}

	public EditPart createEditPart(EditPart context, Object model) {
		for (IComponentFactory f : getPrioritizedFactoryList(model)) {
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

	public void onInitContext(JasperReportsConfiguration jConfig) {
		for (IEditorContributor f : eContributor)
			f.onInitContext(jConfig);
	}

	public void onLoad(JasperDesign jd, EditorPart editor) {
		for (IEditorContributor f : eContributor)
			f.onLoad(jd, editor);
	}

	public void onRename(IFile oldName, IFile newName, JasperReportsContext jrConfig, IProgressMonitor monitor) {
		for (IEditorContributor f : eContributor)
			f.onRename(oldName, newName, jrConfig, monitor);
	}

	public void onSave(JasperReportsConfiguration jrConfig, IProgressMonitor monitor) {
		for (IEditorContributor f : eContributor)
			f.onSave(jrConfig, monitor);
	}

	public void onSaveAs(IFile oldName, IFile newName, JasperReportsConfiguration jrConfig, IProgressMonitor monitor) {
		for (IEditorContributor f : eContributor)
			f.onSaveAs(oldName, newName, jrConfig, monitor);
	}

	public String getTitleToolTip(JasperReportsConfiguration jrConfig, String tooltip) {
		for (IEditorContributor f : eContributor) {
			String s = f.getTitleToolTip(jrConfig, tooltip);
			if (s != null && !s.isEmpty())
				tooltip = s;
		}
		return tooltip;
	}

	public void onRun(JasperReportsConfiguration jrConfig, JasperReport jr, Map<String, Object> params) {
		for (IEditorContributor f : eContributor)
			f.onRun(jrConfig, jr, params);
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

	public ExpressionContext getExpressionContext4Element(Object jrObject) {
		for (IComponentFactory f : nodeFactory) {
			ExpressionContext exprContext = f.getElementExpressionContext(jrObject);
			if (exprContext != null)
				return exprContext;
		}
		return null;
	}

	/**
	 * Looks for contributions related to the specified preview mode ID.
	 * 
	 * @param previewModeID
	 *          the preview mode identifier
	 * @return the list of contributed information
	 */
	public List<PreviewModeDetails> getAllPreviewModeDetails(String previewModeID) {
		IConfigurationElement[] elements = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(JaspersoftStudioPlugin.PLUGIN_ID, PreviewModeDetails.EXTENSION_POINT_ID); // $NON-NLS-1$
		List<PreviewModeDetails> allDetails = new ArrayList<PreviewModeDetails>();
		for (IConfigurationElement ce : elements) {
			if (previewModeID.equals(ce.getAttribute("modeID")) || previewModeID == null) {
				Object clazz;
				try {
					clazz = ce.createExecutableExtension("class");
					if (clazz instanceof PreviewModeDetails) {
						allDetails.add((PreviewModeDetails) clazz);
					}
				} catch (CoreException e) {
					JaspersoftStudioPlugin.getInstance().logError("An error occurred while trying to create the new class.", e);
				}
			}
		}
		return allDetails;
	}

	/**
	 * @return all the contributions for all the possible preview modes.
	 */
	public List<PreviewModeDetails> getAllPreviewModeDetails() {
		return getAllPreviewModeDetails(null);
	}

	/**
	 * Return the list of all contributed hyperlink types.
	 * <p>
	 * 
	 * A plugin can contribute custom hyperlink through the extension point with id
	 * <code>com.jaspersoft.studio.hyperlinkTypes</code>.
	 * 
	 * @return list of custom hyperlink types
	 */
	public List<String> getContributedHyperlinkTypes() {
		if (customHyperlinkTypes == null) {
			IConfigurationElement[] contributedElements = Platform.getExtensionRegistry()
					.getConfigurationElementsFor("com.jaspersoft.studio.hyperlinkTypes"); //$NON-NLS-1$
			if (contributedElements.length != 0) {
				customHyperlinkTypes = new ArrayList<String>(contributedElements.length);
				defaultHyperlinkParametersByCustomType = new HashMap<String, List<HyperlinkDefaultParameter>>(
						contributedElements.length);
				uiElementsIDByCustomType = new HashMap<String, List<WHyperlink.UIElement>>(contributedElements.length);
				for (IConfigurationElement el : contributedElements) {
					String type = el.getAttribute("type"); //$NON-NLS-1$
					customHyperlinkTypes.add(type);
					// lookup for default parameters
					IConfigurationElement[] elements = el.getChildren("parameters");
					if (elements.length == 1) {
						IConfigurationElement[] params = elements[0].getChildren();
						List<HyperlinkDefaultParameter> defaultParametersLst = new ArrayList<HyperlinkDefaultParameter>(
								params.length);
						for (IConfigurationElement p : params) {
							String parameterName = p.getAttribute("name");
							String defaultValue = p.getAttribute("defaultValue");
							defaultParametersLst.add(new HyperlinkDefaultParameter(parameterName, defaultValue));
						}
						defaultHyperlinkParametersByCustomType.put(type, defaultParametersLst);
					} else {
						defaultHyperlinkParametersByCustomType.put(type, new ArrayList<HyperlinkDefaultParameter>(0));
					}
					// lookup for ui elements
					elements = el.getChildren("uiElements");
					if (elements.length == 1) {
						IConfigurationElement[] uiElements = elements[0].getChildren("uiElement");
						List<WHyperlink.UIElement> uiElementsList = new ArrayList<WHyperlink.UIElement>(uiElements.length);
						for (IConfigurationElement u : uiElements) {
							String id = u.getAttribute("id");
							try {
								uiElementsList.add(WHyperlink.UIElement.valueOf(id));
							} catch (NullPointerException e1) {
								JaspersoftStudioPlugin.getInstance().logWarning(NLS
										.bind("Custom Hyperlink Type {0} - The attribute id for the uiElement tag can not be null", type));
							} catch (IllegalArgumentException e2) {
								JaspersoftStudioPlugin.getInstance().logWarning(
										NLS.bind("Custom Hyperlink Type {0} - The value {1} for the attribute id is not valid", type, id));
							}
						}
						uiElementsIDByCustomType.put(type, uiElementsList);
					} else {
						uiElementsIDByCustomType.put(type, new ArrayList<WHyperlink.UIElement>(0));
					}
				}
			} else {
				// No custom type found
				customHyperlinkTypes = new ArrayList<String>(0);
				defaultHyperlinkParametersByCustomType = new HashMap<String, List<HyperlinkDefaultParameter>>(0);
				uiElementsIDByCustomType = new HashMap<String, List<WHyperlink.UIElement>>(0);
			}
		}
		return customHyperlinkTypes;
	}

	/**
	 * Returns a list, maybe empty, of default parameters that can be suggested when choosing the specified hyperlink
	 * type.
	 * 
	 * @param hyperlinkType
	 * @return the list of default parameters, if any, associated to the hyperlink
	 */
	public List<HyperlinkDefaultParameter> getDefaultParametersForCustomHyperlink(String hyperlinkType) {
		if (defaultHyperlinkParametersByCustomType == null) {
			// Init the custom hyperlink types extension point information
			getContributedHyperlinkTypes();
		}
		List<HyperlinkDefaultParameter> list = defaultHyperlinkParametersByCustomType.get(hyperlinkType);
		return list != null ? list : new ArrayList<HyperlinkDefaultParameter>(0);
	}

	/**
	 * Returns a list, maybe empty, of ids that refer to UI elements that are supposed to be used in dialogs, widgets and
	 * composites that allow the final user to modify hyperlinks.
	 * 
	 * @param hyperlinkType
	 * @return the list of ui elements, if any, associated to the hyperlink
	 */
	public List<UIElement> getUIElementsForCustomHyperlink(String hyperlinkType) {
		if (uiElementsIDByCustomType == null) {
			// Init the custom hyperlink types extension point information
			getContributedHyperlinkTypes();
		}
		List<UIElement> list = uiElementsIDByCustomType.get(hyperlinkType);
		// When the list is null then there is no contribution at plugin.xml level
		// As fallback solution we return all the available UI elements
		return list != null ? list : Arrays.asList(UIElement.values());
	}
}
