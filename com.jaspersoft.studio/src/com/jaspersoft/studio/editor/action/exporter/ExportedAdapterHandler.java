/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.exporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.runtime.Status;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterFactory;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.storage.ADataAdapterStorage;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.eclipse.ui.util.RunnableOverwriteQuestion;
import net.sf.jasperreports.eclipse.ui.util.RunnableOverwriteQuestion.RESPONSE_TYPE;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.util.JRXmlUtils;
import net.sf.jasperreports.util.CastorUtil;

/**
 * Exported used to import/export the data adapter definition
 * 
 * @author Orlandin Marco
 *
 */
public class ExportedAdapterHandler implements IExportedResourceHandler {

	/**
	 * Name of the folder where the definition of the data adapters will be stored 
	 * in the exported container
	 */
	private static final String EXPORTED_FOLDER_NAME = "dataAdapters"; //$NON-NLS-1$

	@Override
	public boolean hasRestorableResources(File exportedContainer) {
		File exportedFolder = new File(exportedContainer, EXPORTED_FOLDER_NAME);
		return (exportedFolder.exists() && exportedFolder.list().length > 0);
	}

	@Override
	public String getResourceName() {
		return "Data Adapters"; //$NON-NLS-1$
	}

	@Override
	public boolean hasExportableResources() {
		ADataAdapterStorage storage = DataAdapterManager.getPreferencesStorage();
		return (storage.getDataAdapterDescriptors().size() > 0);
	}
	
	@Override
	public File exportContentFolder() {
		ADataAdapterStorage storage = DataAdapterManager.getPreferencesStorage();
		File tempDir = new File(System.getProperty("java.io.tmpdir")); //$NON-NLS-1$
		tempDir.deleteOnExit();
		File destDir = new File (tempDir, EXPORTED_FOLDER_NAME);
		if (destDir.exists()) FileUtils.recursiveDelete(destDir);
		destDir.mkdirs();
		int index = 0;
		for(DataAdapterDescriptor descriptor : storage.getDataAdapterDescriptors()){
			save(descriptor, new File(destDir, "dataAdapter" + index)); //$NON-NLS-1$
			index++;
		}
		return destDir;
	}

	@Override
	public void restoreContentFolder(File exportedContainer) {
		File srcDir = new File (exportedContainer, EXPORTED_FOLDER_NAME);
		if (srcDir.exists()){
			//Search the files to load
			List<File> notHiddenFiles = new ArrayList<File>();
			List<DataAdapterDescriptor> foundDataAdapters = new ArrayList<DataAdapterDescriptor>();
			File[] listFiles = srcDir.listFiles();
			for (File f : listFiles) {
				if (!f.isHidden()) {
					notHiddenFiles.add(f);
				}
			}
			
			//Convert the loaded textual file to data adapters
			ADataAdapterStorage storage = DataAdapterManager.getPreferencesStorage();
			for (File storageElement : notHiddenFiles) {
				deserializeDataAdapter(storageElement, foundDataAdapters);
			}
			
			//Check for duplicates
			Map<String, DataAdapterDescriptor> existingAdapters = storage.getDescriptors();
			List<String> duplicatedAdapters = new ArrayList<String>();
			for(DataAdapterDescriptor adapter : foundDataAdapters){
				String name = adapter.getName().trim();
				if (existingAdapters.containsKey(name)){
					duplicatedAdapters.add(name);
				}
			}
			
			//Import the selected adapters
			RESPONSE_TYPE response = RESPONSE_TYPE.KEEP_BOTH;
			if (duplicatedAdapters.size() > 0){
				response = askOverwrite(duplicatedAdapters);
			}
			for(DataAdapterDescriptor adapter : foundDataAdapters){
				String name = adapter.getName();
				if (existingAdapters.containsKey(name)){
					if (response == RESPONSE_TYPE.KEEP_BOTH){
						String newName = getName(existingAdapters, name);
						adapter.setName(newName);
						adapter.getDataAdapter().setName(newName);
						storage.addDataAdapter(adapter);
					} else if (response == RESPONSE_TYPE.OVERWRITE){
						storage.removeDataAdapter(existingAdapters.get(name));
						storage.addDataAdapter(adapter);
					}
				} else {
					storage.addDataAdapter(adapter);
				}
			}			
		}
	}
	
	/**
	 * Return an unique name for the imported resource, not already used by the others
	 * 
	 * @param existingAdapters the existing resources, the search of the name will be test against this map, must be
	 * not null
	 * @param baseName the base name used into the search
	 * @return a not null unique name for the new resource
	 */
	private String getName(Map<String, DataAdapterDescriptor> existingAdapters, String baseName){
		int index = 1;
		String newName = baseName + "_" + index; //$NON-NLS-1$
		while(existingAdapters.containsKey(newName)){
			index++;
			newName = baseName + "_" + index; //$NON-NLS-1$
		}
		return newName;
	}
	
	/**
	 * Convert a data adapter into a textual file 
	 * 
	 * @param adapter the adapter to convert
	 * @param destination the file that will contains the textual definition
	 */
	protected void save(DataAdapterDescriptor adapter, File destination) {
		FileOutputStream stream = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(adapter.toXml(JasperReportsConfiguration.getDefaultInstance()))));
			// Write the parsed document to an xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			stream = new FileOutputStream(destination);
			StreamResult result = new StreamResult(stream);
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			FileUtils.closeStream(stream);
		}
	}
	
	/**
	 * Convert the textual definition of a data adapter to a data adapter class type and 
	 * add it to the passed list
	 * 
	 * @param storageElement the file pointing to the textual definition of the data adapter
	 * @param foundDataAdapters the list where the read data adapter will be added, nothing will be added
	 * it something goes wrong
	 */
	private void deserializeDataAdapter(File storageElement, List<DataAdapterDescriptor> foundDataAdapters){
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
					Node namedItem = attributes.getNamedItem("class"); //$NON-NLS-1$
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
						} else {
							DataAdapterDescriptor dataAdapterDescriptor = factory.createDataAdapter();
							DataAdapter dataAdapter = dataAdapterDescriptor.getDataAdapter();
							// maybe we should get context for this file?
	
							inputStream = new FileInputStream(storageElement);
							dataAdapter = (DataAdapter) CastorUtil.getInstance(JasperReportsConfiguration.getDefaultInstance()).read(inputStream);
							dataAdapterDescriptor.setDataAdapter(dataAdapter);
							foundDataAdapters.add(dataAdapterDescriptor);
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			FileUtils.closeStream(inputStream);
		}

	}
	
	/**
	 * Show a question dialog with the buttons overwrite, skip and  keep both,
	 * no and cancel. It is executed in the graphic thread so this method dosen't need to be called
	 * inside a the display thread.
	 *
	 * @param connectionNames the list of the overlapping names, this will be used to build the message and show
	 * which elements are overlapping, must be not null
	 * @return a not null enum that can be Overwrite, Skip or Keep Both
	 */
	private RESPONSE_TYPE askOverwrite(List<String> adapters) {
		String baseMessage = Messages.ExportedAdapterHandler_overlappingMessage;
		StringBuilder message = new StringBuilder();
		int index = 1;
		for(String adapter : adapters){
			message.append(adapter);
			message.append(index == adapters.size() ? "" : ","); //$NON-NLS-1$ //$NON-NLS-2$
			index ++;
		}
		String composedMessage = MessageFormat.format(baseMessage, new Object[]{message.toString()});
		return RunnableOverwriteQuestion.showQuestion(Messages.ExportedAdapterHandler_overlappingTitle, composedMessage);
	}

}
