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
package com.jaspersoft.studio.data;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.XmlUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;

import com.jaspersoft.studio.data.storage.ADataAdapterStorage;
import com.jaspersoft.studio.data.storage.FileDataAdapterStorage;
import com.jaspersoft.studio.data.storage.PreferencesDataAdapterStorage;

/*
 * The main plugin class to be used in the desktop.
 * 
 * @author Giulio Toffoli (gt78@users.sourceforge.net)
 */
public class DataAdapterManager {

	private static List<DataAdapterFactory> dataAdapterFactories = new ArrayList<DataAdapterFactory>();

	/*******************************
	 ** Data Adapter Factories Part **
	 *******************************/

	/**
	 * Add a DataAdapterFactory to the list of DataAdapterFactories in JaspersoftStudio. The new type of data adapter will
	 * then be visible when a new data adapter is created.
	 * 
	 * @param factory
	 */
	public static void addDataAdapterFactory(DataAdapterFactory factory) {
		if (!dataAdapterFactories.contains(factory)
				&& findFactoryByDataAdapterClass(factory.getDataAdapterClassName()) == null) {
			dataAdapterFactories.add(factory);
		}
	}

	/**
	 * Remove the DataAdapterFactory to the list of DataAdapterFactories in JaspersoftStudio.
	 * 
	 * @param factory
	 */
	public static void removeDataAdapterFactory(DataAdapterFactory factory) {
		if (dataAdapterFactories.contains(factory)) {
			dataAdapterFactories.remove(factory);
		}
	}

	/**
	 * Return a copy of the list of DataAdapterFactories in JaspersoftStudio.
	 */
	public static synchronized List<DataAdapterFactory> getDataAdapterFactories() {
		List<DataAdapterFactory> listOfDataAdapterFactories = new ArrayList<DataAdapterFactory>();
		listOfDataAdapterFactories.addAll(dataAdapterFactories);
		return listOfDataAdapterFactories;
	}

	/**
	 * 
	 * @param adapterClassName
	 * @return
	 */
	public static DataAdapterFactory findFactoryByDataAdapterClass(String adapterClassName) {
		if (adapterClassName == null || adapterClassName.isEmpty())
			return null;

		for (DataAdapterFactory factory : dataAdapterFactories) {
			if (adapterClassName.equals(factory.getDataAdapterClassName())) {
				return factory;
			}
		}
		return null; // No factory found for this dataAdpater..
	}

	private static Map<Object, ADataAdapterStorage> storages = new HashMap<Object, ADataAdapterStorage>();

	public static ADataAdapterStorage[] getDataAdapter(IFile file) {
		ADataAdapterStorage[] st = new ADataAdapterStorage[file == null ? 1 : 2];
		st[0] = getPreferencesStorage();
		if (file != null)
			st[1] = getProjectStorage(file.getProject());
		return st;
	}

	public static ADataAdapterStorage getProjectStorage(IProject key) {
		ADataAdapterStorage s = storages.get(key);
		if (s == null) {
			s = new FileDataAdapterStorage(key);
			storages.put(key, s);
		}
		return s;
	}

	public static ADataAdapterStorage getPreferencesStorage() {
		ADataAdapterStorage s = storages.get("PREFERENCES");
		if (s == null) {
			s = new PreferencesDataAdapterStorage();
			storages.put("PREFERENCES", s);
		}
		return s;
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
	// List<DataAdapterDescriptor> listOfDataAdapters = new ArrayList<DataAdapterDescriptor>();
	// listOfDataAdapters.addAll(dataAdapters);
	// return listOfDataAdapters;
	// }

	public static String toDataAdapterFile(DataAdapterDescriptor dataAdapter) {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" + dataAdapter.toXml();
	}

	/**
	 * Creates a copy of a data adapter looking for the right Factory.
	 * 
	 * A NullPointerException is raised is the dataAdapter is null or if a suitable DataAdapterFactory is not found.
	 * 
	 * @param dataAdapter
	 * @return
	 */
	public static DataAdapterDescriptor cloneDataAdapter(DataAdapterDescriptor src) {
		DataAdapter srcDataAdapter = src.getDataAdapter();
		DataAdapterFactory factory = findFactoryByDataAdapterClass(srcDataAdapter.getClass().getName());
		DataAdapterDescriptor copy = factory.createDataAdapter();
		srcDataAdapter = (DataAdapter) XmlUtil.read(new ByteArrayInputStream(src.toXml().getBytes()),
				srcDataAdapter.getClass());
		copy.setDataAdapter(srcDataAdapter);
		return copy;
	}

}
