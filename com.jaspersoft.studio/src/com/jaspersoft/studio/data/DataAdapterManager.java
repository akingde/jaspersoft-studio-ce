/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.customadapters.ConfigurableDataAdapterFactory;
import com.jaspersoft.studio.data.customadapters.ui.AdapterWidgetsDescriptor;
import com.jaspersoft.studio.data.storage.ADataAdapterStorage;
import com.jaspersoft.studio.data.storage.FileDataAdapterStorage;
import com.jaspersoft.studio.data.storage.JRDefaultDataAdapterStorage;
import com.jaspersoft.studio.data.storage.PreferencesDataAdapterStorage;
import com.jaspersoft.studio.preferences.customadapters.CustomDataAdaptersPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.manager.StandardJSONWidgetsDescriptorResolver;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.eclipse.wizard.project.ProjectUtil;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.util.CastorUtil;

/*
 * The main plugin class to be used in the desktop.
 * 
 * @author Giulio Toffoli (gt78@users.sourceforge.net)
 */
public class DataAdapterManager {

	private static Map<String, DataAdapterFactory> dataAdapterFactories = new HashMap<>();

	private static Map<String, DataAdapterFactory> customFactories = new HashMap<>();

	private static Map<Object, ADataAdapterStorage> storages = new HashMap<>();

	static {
		// Listener used to clear the cache when something about the data adapters
		// definition change in the preferences
		JaspersoftStudioPlugin.getInstance().getPreferenceStore()
				.addPropertyChangeListener(new IPropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent event) {
						if (event.getProperty().equals(CustomDataAdaptersPreferencePage.CUSTOMDATAADAPTERS)) {
							customFactories.clear();
							loadCustomFactories();
						}
					}
				});
		loadCustomFactories();
	}

	/**
	 * Load the definitions of all the custom configurable data adapters
	 */
	protected static void loadCustomFactories() {
		String definitions = JaspersoftStudioPlugin.getInstance().getPreferenceStore()
				.getString(CustomDataAdaptersPreferencePage.CUSTOMDATAADAPTERS);
		StringTokenizer st = new StringTokenizer(definitions, File.pathSeparator + "\n\r"); //$NON-NLS-1$
		Set<String> pathsList = new LinkedHashSet<>();
		while (st.hasMoreTokens())
			pathsList.add(st.nextToken());
		if (!pathsList.isEmpty()) {
			StandardJSONWidgetsDescriptorResolver resolver = new StandardJSONWidgetsDescriptorResolver(
					AdapterWidgetsDescriptor.class);
			for (String definition : pathsList) {
				File file = new File(definition);
				if (file.exists()) {
					AdapterWidgetsDescriptor loadedDecriptor = (AdapterWidgetsDescriptor) resolver
							.loadDescriptor(JasperReportsConfiguration.getDefaultInstance(), file.getAbsolutePath());
					if (loadedDecriptor != null) {
						ConfigurableDataAdapterFactory factory = new ConfigurableDataAdapterFactory(loadedDecriptor);
						customFactories.put(loadedDecriptor.getAdapterClass(), factory);
					}
				}
			}
		}
	}

	/*******************************
	 ** Data Adapter Factories Part **
	 *******************************/

	/**
	 * Add a DataAdapterFactory to the list of DataAdapterFactories in
	 * JaspersoftStudio. The new type of data adapter will then be visible when a
	 * new data adapter is created.
	 * 
	 * @param factory
	 */
	public static void addDataAdapterFactory(DataAdapterFactory factory) {
		if (!dataAdapterFactories.containsKey(factory.getDataAdapterClassName()))
			dataAdapterFactories.put(factory.getDataAdapterClassName(), factory);
	}

	/**
	 * Remove the DataAdapterFactory to the list of DataAdapterFactories in
	 * JaspersoftStudio.
	 * 
	 * @param factory
	 */
	public static void removeDataAdapterFactory(DataAdapterFactory factory) {
		String cl = factory.getDataAdapterClassName();
		DataAdapterFactory f = dataAdapterFactories.get(cl);
		if (f != null && factory == f)
			dataAdapterFactories.remove(cl);
	}

	/**
	 * Return a copy of the list of DataAdapterFactories in JaspersoftStudio.
	 */
	public static synchronized List<DataAdapterFactory> getDataAdapterFactories() {

		// Let's sort the list based on the description. Please note that the
		// description may be localized,
		// so not all the languages have the same order if assumptions are done.

		DataAdapterFactory[] factories = dataAdapterFactories.values()
				.toArray(new DataAdapterFactory[dataAdapterFactories.size()]);

		Arrays.sort(factories, new Comparator<DataAdapterFactory>() {

			@Override
			public int compare(DataAdapterFactory df1, DataAdapterFactory df2) {

				String name1 = (df1 == null) ? "" : df1.getLabel();
				String name2 = (df2 == null) ? "" : df2.getLabel();
				return name1.compareTo(name2);
			}
		});

		List<DataAdapterFactory> listOfDataAdapterFactories = new ArrayList<>();
		listOfDataAdapterFactories.addAll(Arrays.asList(factories));
		listOfDataAdapterFactories.addAll(customFactories.values());

		return listOfDataAdapterFactories;
	}

	/**
	 * 
	 * @param adapterClassName
	 * @return
	 */
	public static DataAdapterFactory findFactoryByDataAdapterClass(String adapterClassName) {
		if (Misc.isNullOrEmpty(adapterClassName))
			return null;
		DataAdapterFactory factory = dataAdapterFactories.get(adapterClassName);
		if (factory != null)
			return factory;
		else
			return customFactories.get(adapterClassName);
	}

	public static ADataAdapterStorage[] getDataAdapter(IFile file, IProject project,
			JasperReportsConfiguration jConfig) {
		List<ADataAdapterStorage> st = new ArrayList<>();
		st.add(getPreferencesStorage());
		if (file != null) {
			project = file.getProject();
		}
		if (project != null) {
			st.add(getProjectStorage(project));
		}
		if (jConfig != null && jConfig.getJasperDesign() != null) {
			st.add(getJRDefaultStorage(jConfig));
		}
		return st.toArray(new ADataAdapterStorage[st.size()]);
	}

	public static ADataAdapterStorage[] getDataAdapter(IFile file, JasperReportsConfiguration jConfig) {
		return getDataAdapter(file, null, jConfig);
	}

	public static ADataAdapterStorage getProjectStorage(IProject key) {
		ADataAdapterStorage s = storages.get(key);
		if (s == null) {
			s = new FileDataAdapterStorage(key);
			s.findAll();
			s.getDataAdapterDescriptors();
			storages.put(key, s);
		}
		return s;
	}

	/**
	 * Get the storage for the handling of the default data adapters
	 * 
	 * @return a not null default storage for the specified configuration
	 */
	public static JRDefaultDataAdapterStorage getJRDefaultStorage(JasperReportsConfiguration key) {
		ADataAdapterStorage s = storages.get(key);
		if (s == null) {
			s = new JRDefaultDataAdapterStorage(key);
			s.getDataAdapterDescriptors();
			storages.put(key, s);
		}
		return (JRDefaultDataAdapterStorage) s;
	}

	public static ADataAdapterStorage getPreferencesStorage() {
		ADataAdapterStorage s = storages.get("PREFERENCES");
		if (s == null) {
			s = new PreferencesDataAdapterStorage();
			storages.put("PREFERENCES", s);
			s.getDataAdapterDescriptors();
		}
		return s;
	}

	public static List<ADataAdapterStorage> getProjectStorages() {
		List<ADataAdapterStorage> das = new ArrayList<>();
		for (IProject prj : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
			if (ProjectUtil.isOpen(prj))
				das.add(getProjectStorage(prj));
		}
		return das;
	}

	/***********************
	 ** Data Adapters Part **
	 ***********************/

	/**
	 * Return a copy of the list of DataAdapters in JaspersoftStudio.
	 */
	// public static List<DataAdapterDescriptor> getDataAdapters() {
	// if (!loaded) {
	// new ExtensionManager().init();
	// }
	// List<DataAdapterDescriptor> listOfDataAdapters = new
	// ArrayList<DataAdapterDescriptor>();
	// listOfDataAdapters.addAll(dataAdapters);
	// return listOfDataAdapters;
	// }

	public static String toDataAdapterFile(DataAdapterDescriptor dataAdapter, JasperReportsContext jrContext) {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" + dataAdapter.toXml(jrContext);
	}

	/**
	 * Creates a copy of a data adapter looking for the right Factory.
	 * 
	 * A NullPointerException is raised is the dataAdapter is null or if a suitable
	 * DataAdapterFactory is not found.
	 * 
	 * @param dataAdapter
	 * @return
	 */
	public static DataAdapterDescriptor cloneDataAdapter(DataAdapterDescriptor src, JasperReportsContext jrContext) {
		DataAdapter srcDataAdapter = src.getDataAdapter();
		DataAdapterFactory factory = findFactoryByDataAdapterClass(srcDataAdapter.getClass().getName());
		DataAdapterDescriptor copy = factory.createDataAdapter();
		copy.setName(src.name);
		srcDataAdapter = (DataAdapter) CastorUtil.getInstance(jrContext)
				.read(new ByteArrayInputStream(src.toXml(jrContext).getBytes()));
		copy.setDataAdapter(srcDataAdapter);
		return copy;
	}
}
