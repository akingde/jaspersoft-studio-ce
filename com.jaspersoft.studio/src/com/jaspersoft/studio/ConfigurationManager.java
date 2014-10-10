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
 * Provide the methods to retrieve the path to the configuration file, the 
 * path is cached after the first request
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
		String product = Platform.getProduct().getName();
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
	public static String getPath(){
		if (cachedPath == null) intializePath();
		return cachedPath;
	}
	
	public static File getStorage(String storageName){
	  IPath configurationPath = JaspersoftStudioPlugin.getInstance().getStateLocation();
	  File configurationFolder = configurationPath.toFile();
	  File storage = new File(configurationFolder, storageName);
	  if (!storage.exists()) storage.mkdir();
	  return storage;
	}
	
	public static File[] getStorageContent(String storageName){
		File storage = getStorage(storageName);
		return storage.listFiles();
	}
	
	public static boolean removeStoregeResource(String storageName, String resourceName){
		File storage = getStorage(storageName);
		File resource = new File(storage, resourceName);
		if (resource.exists()) return resource.delete();
		else return false;
	}
	
	public static File getStorageResource(String storageName, String resourceName){
		return getStorageResource(getStorage(storageName), resourceName);
	}
	
	public static File getStorageResource(File resourceStorage, String resourceName){
		File resource = new File(resourceStorage, resourceName);
		if (resource.exists()) return resource;
		return null;
	}
	
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
						File xmlTargetFile = new File(storage, nameProvider.getFileName(configurationNode));
						if (!xmlTargetFile.exists()){
							createtElements.add(xmlTargetFile);
							StreamResult result = new StreamResult(xmlTargetFile);
							transformer.transform(source, result);
						} else {
							throw new Exception("File "+xmlTargetFile.getAbsolutePath()+" already exist");
						}
					}
				}
				prefs.remove(preferenceKey);
			} catch (Exception e) {
				//Do the revert of the created files
				for(File createdElement : createtElements){
					if (createdElement.exists()) createdElement.delete();
				}
				throw new RuntimeException(e);
			}
		}
	}
}
