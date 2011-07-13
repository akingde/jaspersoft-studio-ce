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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.XmlUtil;
import net.sf.jasperreports.data.empty.EmptyDataAdapterImpl;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.util.JRXmlUtils;

import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.jaspersoft.studio.ExtensionManager;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.empty.EmptyDataAdapterDescriptor;
import com.jaspersoft.studio.data.empty.EmptyDataAdapterFactory;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.repository.RepositoryManager;

/*
 * The main plugin class to be used in the desktop.
 * 
 * @author Giulio Toffoli (gt78@users.sourceforge.net)
 */
public class DataAdapterManager {

	private static List<DataAdapterFactory> dataAdapterFactories = new ArrayList<DataAdapterFactory>();
	private static List<DataAdapterDescriptor> dataAdapters = new ArrayList<DataAdapterDescriptor>();
	private static PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(new RepositoryManager());

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
		if (adapterClassName == null)
			return null;

		for (DataAdapterFactory factory : dataAdapterFactories) {
			if (adapterClassName.equals(factory.getDataAdapterClassName())) {
				return factory;
			}
		}
		return null; // No factory found for this dataAdpater..
	}

	/***********************
	 ** Data Adapters Part **
	 ***********************/

	/**
	 * Add a DataAdapter to the list of DataAdapters in JaspersoftStudio.
	 * 
	 * @param DataAdapterService
	 */
	public static void addDataAdapter(DataAdapterDescriptor adapter) {
		if (!dataAdapters.contains(adapter)) {
			dataAdapters.add(adapter);
			propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(adapter, "DATAADAPTERS", null, adapter)); //$NON-NLS-1$
			saveDataAdapters();
		}
	}

	/**
	 * Remove the DataAdapter to the list of DataAdapters in JaspersoftStudio.
	 * 
	 * @param DataAdapterService
	 */
	public static void removeDataAdapter(DataAdapterDescriptor adapter) {
		if (dataAdapters.contains(adapter)) {
			dataAdapters.remove(adapter);
			propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(adapter, "DATAADAPTERS", null, adapter)); //$NON-NLS-1$
			saveDataAdapters();
		}
	}

	/**
	 * Return a copy of the list of DataAdapters in JaspersoftStudio.
	 */
	public static List<DataAdapterDescriptor> getDataAdapters() {
		if(!loaded){
			new ExtensionManager().init();
		}
		List<DataAdapterDescriptor> listOfDataAdapters = new ArrayList<DataAdapterDescriptor>();
		listOfDataAdapters.addAll(dataAdapters);
		return listOfDataAdapters;
	}

	/**
	 * Save a changed data adapter. The dataAdapter must be in the list of data adapters. If not, the method will just
	 * return.
	 */
	public static void saveDataAdapter(DataAdapterDescriptor dataAdapter) {
		if (!dataAdapters.contains(dataAdapter))
			return;
		propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(dataAdapter, "DATAADAPTERS", null, dataAdapter)); //$NON-NLS-1$
		saveDataAdapters();
	}

	/**
	 * Calling this method will force saving the list of adapters in the Eclipse preferences
	 */
	public static void saveDataAdapters() {
		Preferences prefs = new InstanceScope().getNode(JaspersoftStudioPlugin.getUniqueIdentifier());

		try {
			StringBuffer xml = new StringBuffer();
			xml.append("<dataAdapters>\n"); //$NON-NLS-1$
			for (DataAdapterDescriptor desc : getDataAdapters()) {
				xml.append(desc.toXml());
			}
			xml.append("</dataAdapters>"); //$NON-NLS-1$

			prefs.put("dataAdapters", xml.toString()); //$NON-NLS-1$ 
			prefs.flush();

		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}
	private static boolean loaded=false;
	/**
	 * Calling this method will force saving the list of adapters in the Eclipse preferences
	 */
	public static void loadDataAdapters() {
		loaded = true;
		// Clear up the list of data adapters...
		dataAdapters.clear();

		Preferences prefs = new InstanceScope().getNode(JaspersoftStudioPlugin.getUniqueIdentifier());

		String xml = prefs.get("dataAdapters", null); //$NON-NLS-1$

		if (xml != null)
		{

			try {
	
				Document document = JRXmlUtils.parse(new InputSource(new StringReader(xml)));
	
				NodeList adapterNodes = document.getDocumentElement().getChildNodes();// .getElementsByTagName("dataAdapter");
	
				for (int i = 0; i < adapterNodes.getLength(); ++i) {
					Node adapterNode = adapterNodes.item(i);
	
					if (adapterNode.getNodeType() == Node.ELEMENT_NODE) {
						// 1. Find out the class of this data adapter...
						String adapterClassName = adapterNode.getAttributes().getNamedItem("class").getNodeValue(); //$NON-NLS-1$
	
						DataAdapterFactory factory = findFactoryByDataAdapterClass(adapterClassName);
	
						if (factory == null) {
							// we should at least log a warning here....
							JaspersoftStudioPlugin
									.getInstance()
									.getLog()
									.log(
											new Status(Status.WARNING, JaspersoftStudioPlugin.getUniqueIdentifier(), Status.OK,
													Messages.DataAdapterManager_nodataadapterfound + adapterClassName, null));
							return;
						}
	
						DataAdapterDescriptor dataAdapterDescriptor = factory.createDataAdapter();
	
						DataAdapter dataAdapter = dataAdapterDescriptor.getDataAdapter();
	
						dataAdapter = (DataAdapter) XmlUtil.read(adapterNode, dataAdapter.getClass());
	
						dataAdapterDescriptor.setDataAdapter(dataAdapter);
	
						// dataAdapter.setName(adapterNode.getAttributes().getNamedItem("name").getNodeValue());
						// Map<String, String> map = new HashMap<String, String>();
						//
						// // Find all the property nodes in the dataAdapter node
						// NodeList parameterNodes = adapterNode.getChildNodes();
						//
						// // For each node, load the parameter name and the parameter value
						// for (int j=0; j < parameterNodes.getLength(); ++j)
						// {
						// Node parameterNode = parameterNodes.item(j);
						// if (parameterNode.getNodeType() == Node.ELEMENT_NODE && "parameter".equals(parameterNode.getNodeName()))
						// {
						// String key = parameterNode.getAttributes().getNamedItem("name").getNodeValue();
						// String value = parameterNode.getTextContent();
						// map.put(key, value);
						// }
						// }
						//
						// dataAdapter.loadProperties(map);
	
						dataAdapters.add(dataAdapterDescriptor);
					}
				}
				
			} catch (JRException e) {
				e.printStackTrace();
			}
		}
		if (dataAdapters.isEmpty()) {
			EmptyDataAdapterFactory edaf = new EmptyDataAdapterFactory();
			EmptyDataAdapterDescriptor edad = edaf.createDataAdapter();
			EmptyDataAdapterImpl dataAdapter = new EmptyDataAdapterImpl();
			dataAdapter.setName(Messages.DataAdapterManager_oneemptyrecord);
			dataAdapter.setRecordCount(1);
			edad.setDataAdapter(dataAdapter);
			dataAdapters.add(edad);
		}
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

		DataAdapterFactory factory = findFactoryByDataAdapterClass(src.getDataAdapter().getClass().getName());
		DataAdapterDescriptor copy = factory.createDataAdapter();
		copy.setDataAdapter(src.getDataAdapter());
		return copy;
	}

	public static PropertyChangeSupport getPropertyChangeSupport() {
		return propertyChangeSupport;
	}

	/**
	 * Check the validity of the data adapter name. It is valid only if it is not null, not empty and not already existed.
	 * 
	 * @param dataAdapterName
	 * @return bool
	 */
	public static boolean isDataAdapterNameValid(String dataAdapterName) {

		if (dataAdapterName == null || "".equals(dataAdapterName.trim())) //$NON-NLS-1$
			return false;

		for (DataAdapterDescriptor dataAdapter : getDataAdapters()) {
			if (dataAdapter.getName().equals(dataAdapterName))
				return false;
		}
		return true;
	}

	public static DataAdapterDescriptor findDataAdapter(String name) {
		for (DataAdapterDescriptor dataAdapter : getDataAdapters()) {
			if (dataAdapter.getName().equals(name))
				return dataAdapter;
		}
		return null;
	}
}
