/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.data.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.util.JRXmlUtils;
import net.sf.jasperreports.util.CastorUtil;

import org.eclipse.core.runtime.Status;
import org.osgi.framework.Bundle;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.jaspersoft.studio.ConfigurationManager;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterFactory;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class PreferencesDataAdapterStorage extends ADataAdapterStorage {

	private HashMap<DataAdapterDescriptor, String> fileAdapterMap = new HashMap<DataAdapterDescriptor, String>();

	/**
	 * Key of the data adapter storage
	 */
	public static final String PREF_KEYS_DATA_ADAPTERS = "dataAdapters";//$NON-NLS-1$

	/**
	 * Converter used to get a valid filename for each data adapter
	 */
	private static DataAdapterNameConverter convertDataAdapterName = new DataAdapterNameConverter();

	@Override
	public void findAll() {
		// Do the silent conversion to the new storage system
		ConfigurationManager.convertPropertyToStorage(PREF_KEYS_DATA_ADAPTERS, PREF_KEYS_DATA_ADAPTERS,
				convertDataAdapterName);
		// Read the configuration from the file storage
		File[] storageContent = ConfigurationManager.getStorageContent(PREF_KEYS_DATA_ADAPTERS);
		for (File storageElement : storageContent) {
			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(storageElement);
				Reader reader = new InputStreamReader(inputStream, FileUtils.UTF8_ENCODING);
				InputSource is = new InputSource(reader);
				is.setEncoding(FileUtils.UTF8_ENCODING);
				Document document = JRXmlUtils.parse(is);
				Node adapterNode = document.getDocumentElement();
				if (adapterNode != null) {
					NamedNodeMap attributes = adapterNode.getAttributes();
					if (attributes != null) {
						Node namedItem = attributes.getNamedItem("class");
						if (namedItem != null) {
							String adapterClassName = namedItem.getNodeValue();
							DataAdapterFactory factory = DataAdapterManager.findFactoryByDataAdapterClass(adapterClassName);
							if (factory == null) {
								// we should at least log a warning here....
								JaspersoftStudioPlugin
										.getInstance()
										.getLog()
										.log(
												new Status(Status.WARNING, JaspersoftStudioPlugin.getUniqueIdentifier(), Status.OK,
														Messages.DataAdapterManager_nodataadapterfound + adapterClassName, null));
								continue;
							}

							DataAdapterDescriptor dataAdapterDescriptor = factory.createDataAdapter();
							DataAdapter dataAdapter = dataAdapterDescriptor.getDataAdapter();
							// maybe we should get context for this file?

							inputStream.close();

							inputStream = new FileInputStream(storageElement);
							dataAdapter = (DataAdapter) CastorUtil.getInstance(JasperReportsConfiguration.getDefaultInstance()).read(
									inputStream);
							dataAdapterDescriptor.setDataAdapter(dataAdapter);
							// Always add the data adapter read from the file regardless of the name
							super.forceAddDataAdapter(dataAdapterDescriptor);
							fileAdapterMap.put(dataAdapterDescriptor, storageElement.getName());
						}
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				FileUtils.closeStream(inputStream);
			}
		}
		// At this point I've loaded on the data adapter on the file system
		// Add a list of default data adapters only if none is found.
		if (getDataAdapterDescriptors().size() == 0) {
			Bundle bundle = JaspersoftStudioPlugin.getInstance().getBundle();
			Enumeration<URL> urls = bundle.findEntries("defaults/dataadapter/prefs/", "*.xml", true);
			while (urls.hasMoreElements()) {
				InputStream in = null;
				try {
					in = urls.nextElement().openStream();
					DataAdapterDescriptor dad = FileDataAdapterStorage.readDataADapter(in, null);
					addDataAdapter(dad);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					FileUtils.closeStream(in);
				}
			}
		}
		for (DataAdapterDescriptor dad : JaspersoftStudioPlugin.getDefaultDAManager().getDefaultDAs()) {
			DataAdapterDescriptor oldDa = daDescriptors.get(dad.getName());
			// maybe name is the same, but if type changed, we should change dataadapter
			if (oldDa != null && !oldDa.getClass().getCanonicalName().equals(dad.getClass().getCanonicalName()))
				removeDataAdapter(oldDa);
			// Add new data adapter only if it has a factory
			DataAdapter adapter = dad.getDataAdapter();
			String adapterClassName = adapter.getClass().getName();
			DataAdapterFactory factory = DataAdapterManager.findFactoryByDataAdapterClass(adapterClassName);
			if (factory != null)
				addDataAdapter(dad);
		}
	}

	/**
	 * Save an element on the data adapter file storage. The url is the name of the resource that will be created inside
	 * the storage
	 */
	protected void save(DataAdapterDescriptor adapter) {
		String fileName = convertDataAdapterName.getFileName(null);
		save(adapter, fileName);
	}

	protected void save(DataAdapterDescriptor adapter, String fileName) {
		FileOutputStream stream = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(adapter.toXml(JasperReportsConfiguration
					.getDefaultInstance()))));
			// Write the parsed document to an xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			File storage = ConfigurationManager.getStorage(PREF_KEYS_DATA_ADAPTERS);
			File destination = new File(storage, fileName);
			stream = new FileOutputStream(destination);
			StreamResult result = new StreamResult(stream);
			transformer.transform(source, result);
			fileAdapterMap.put(adapter, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			FileUtils.closeStream(stream);
		}
	}

	/**
	 * Remove a data adapter from the storage, the url is the name of the resource to remove
	 */
	@Override
	public boolean removeDataAdapter(DataAdapterDescriptor da) {
		boolean result = super.removeDataAdapter(da);
		if (result) {
			ConfigurationManager.removeStoregeResource(PREF_KEYS_DATA_ADAPTERS, fileAdapterMap.get(da));
			return true;
		}
		return false;
	}

	/**
	 * Add a data adapter from the file storage. As data adapter file name is used the url, manipulated if necessary to
	 * get a valid an unique file name
	 */
	@Override
	public boolean addDataAdapter(DataAdapterDescriptor adapter) {
		boolean result = super.addDataAdapter(adapter);
		if (result) {
			// The data adapter is unique, save it with a new name
			save(adapter);
			return true;
		}
		return false;
	}

	public boolean editDataAdapter(String oldName, DataAdapterDescriptor adapter) {
		// it is an edit operation, replace its file
		boolean result = super.editDataAdapter(oldName, adapter);
		if (result) {
			String fileName = fileAdapterMap.get(adapter);
			ConfigurationManager.removeStoregeResource(PREF_KEYS_DATA_ADAPTERS, fileName);
			save(adapter, fileName);
			return true;
		}
		return false;
	}

	@Override
	public String getStorageName() {
		return "Global Preferences";
	}
}
