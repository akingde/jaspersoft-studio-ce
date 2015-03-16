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
package com.jaspersoft.studio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.jasperreports.engine.util.JRXmlUtils;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.util.Util;
import org.eclipse.osgi.service.datalocation.Location;
import org.osgi.service.prefs.Preferences;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.jaspersoft.studio.preferences.util.PropertiesHelper;

/**
 * Provide the methods to retrieve the installation path of the application, the 
 * path is cached after the first request.
 * It Offers also the method to interact with the files storage that can be 
 * used to save the configuration of the application
 * 
 * @author Orlandin Marco
 *
 */
public class ConfigurationManager {
	
	/**
	 * Where the path is cached
	 */
	private static String cachedPath = null;
	
	/**
	 * Get the path and cache it values
	 */
	private static void intializePath(){
		String path = null;
		Location configArea = Platform.getInstallLocation();
		String product = Platform.getProduct().getProperty("appName"); //$NON-NLS-1$ 
		if (configArea != null) {
			if (Util.isMac()) {
				path = configArea.getURL().toExternalForm() + "/" + product + ".app/Contents/MacOS/";
				path = path + product + ".ini";
			}
			else path = configArea.getURL().toExternalForm() + product + ".ini"; //$NON-NLS-1$
		}
		cachedPath = path;
	}
	
	/**
	 * 
	 * Return the path of the configuration file and cache it. Typically
	 * this file is inside the install location of the application
	 * 
	 * @return String represented a Path in URL format to the configuration file
	 */
	public static String getInstallationPath(){
		if (cachedPath == null) intializePath();
		return cachedPath;
	}
	
	/**
	 * Return a storage with a specific name, if the storage doesn't exist then
	 * it is created. A storage is a specific folder in the filesystem.
	 * 
	 * @param storageName the name of the storage\folder
	 * @return a file to the requested storage
	 */
	public static File getStorage(String storageName){
	  IPath configurationPath = JaspersoftStudioPlugin.getInstance().getStateLocation();
	  File configurationFolder = configurationPath.toFile();
	  File storage = new File(configurationFolder, storageName);
	  if (!storage.exists()) storage.mkdir();
	  return storage;
	}
	
	/**
	 * Return a list to all the content of a storage. If the storage
	 * dosen't exist then it is created.
	 * <p>
	 * 
	 * NOTE: hidden files are not considered.
	 * 
	 * @param storageName the name of the storage
	 * @return a not null array of file that map the content of the storage folder
	 */
	public static File[] getStorageContent(String storageName){
		File storage = getStorage(storageName);
		List<File> result = new ArrayList<File>();
		File[] listFiles = storage.listFiles();
		for(File f : listFiles) {
			if(!f.isHidden()) {
				result.add(f);
			}
		}
		return result.toArray(new File[result.size()]);
	}
	
	/**
	 * Remove a resource contained into a specified storage. If
	 * the storage doesnt' exist it is created. 
	 * 
	 * @param storageName the name of the storage 
	 * @param resourceName the name of the resource to delete
	 * @return the result of the delete operation
	 */
	public static boolean removeStoregeResource(String storageName, String resourceName){
		File storage = getStorage(storageName);
		File resource = new File(storage, resourceName);
		if (resource.exists()) return resource.delete();
		else return false;
	}
	
	/**
	 * Return a resource from the storage. If the storage dosen't exist then
	 * it is created
	 * 
	 * @param storageName the name of the storage 
	 * @param resourceName the name of the resource
	 * @return the requested resource or null if it can't be found
	 */
	public static File getStorageResource(String storageName, String resourceName){
		return getStorageResource(getStorage(storageName), resourceName);
	}
	
	/**
	 * Return a resource from the storage. Since the storage is accessed with 
	 * a direct file handle to it then it must be already existing
	 * 
	 * @param storageName and handle to the storage
	 * @param resourceName the name of the resource
	 * @return the requested resource or null if it can't be found
	 */
	public static File getStorageResource(File resourceStorage, String resourceName){
		File resource = new File(resourceStorage, resourceName);
		if (resource.exists()) return resource;
		return null;
	}
	
	/**
	 * Utility method used to convert the old setting storage based on the preferences
	 * on the setting storage based on file, this is done silently to migrate the old
	 * settings to the new storage system
	 * 
	 * @param preferenceKey the key of properties that contains the configuration that must be converted
	 * @param storageName the storage where the new configuration is placed
	 * @param nameProvider the provider for the name of the new storage files.
	 */
	public static void convertPropertyToStorage(String preferenceKey, String storageName, IConversionFilenameProvider nameProvider){
		Preferences prefs = PropertiesHelper.INSTANCE_SCOPE.getNode(JaspersoftStudioPlugin.getUniqueIdentifier());
		String xml = prefs.get(preferenceKey, null);
		List<File> createtElements = new ArrayList<File>();
		if (xml != null) {
			try {
				Document document = JRXmlUtils.parse(new InputSource(new StringReader(xml)));
				NodeList configurationNodes = document.getDocumentElement().getChildNodes();
				File storage = getStorage(storageName);
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				for (int i = 0; i < configurationNodes.getLength(); ++i) {
					Node configurationNode = configurationNodes.item(i);
					if (configurationNode.getNodeType() == Node.ELEMENT_NODE) {
						DOMSource source = new DOMSource(configurationNode);
						String name = nameProvider.getFileName(configurationNode);
						if (name != null){
							File xmlTargetFile = new File(storage,name);
							if (!xmlTargetFile.exists()){
								createtElements.add(xmlTargetFile);
								FileOutputStream stream = new FileOutputStream(xmlTargetFile);
								StreamResult result = new StreamResult(stream);
								transformer.transform(source, result);
								stream.close();
							} else {
								throw new Exception("File "+xmlTargetFile.getAbsolutePath()+" already exist");
							}
						}
					}
				}
				prefs.remove(preferenceKey);
			} catch (Exception e) {
				JaspersoftStudioPlugin.getInstance().logError("Error converting the element",e);
				//Do the revert of the created files
				for(File createdElement : createtElements){
					if (createdElement.exists()) createdElement.delete();
				}
				throw new RuntimeException(e);
			}
		}
	}
}
