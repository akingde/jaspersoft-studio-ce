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
package com.jaspersoft.studio.backward;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.util.JRXmlUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.jaspersoft.studio.ConfigurationManager;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

/**
 * Class that offer the utility methods to build a JRXML with an older version of 
 * JasperReports. It is a singleton class
 * 
 * @author Orlandin Marco
 *
 */
public class JRBackwardManager {
	
	/**
	 * Instance of the class, since it is a singleton
	 */
	public static final JRBackwardManager INSTANCE = new JRBackwardManager();

	/**
	 * Key of the storage where the older version of jasperreports are saved
	 */
	protected static final String JR_COMPILER_STORAGE = "jrCompilers"; //$NON-NLS-1$
	
	/**
	 * Folder searched inside a JR zip that must contains the JR jars
	 */
	private static final String BUILD_FOLDER = "dist"; //$NON-NLS-1$
	
	/**
	 * Folder searched inside a JR zip that must contains the libraries used by JR
	 */
	private static final String LIB_FOLDER = "lib"; //$NON-NLS-1$
	
	/**
	 * Key used in the preference storage to save the last paths used as save location
	 */
	private static final String BACKWARD_PATHS = "backwardPaths"; //$NON-NLS-1$
	
	/**
	 * Map of all the JR version supported. It is used to extract in a fast way the informations
	 * on a JR definition using a version number
	 */
	private HashMap<String, JRDefinition> definitions;
	
	/**
	 * List of all the JR version supported, it contains the same data of the map but it is 
	 * used to have the insertion order
	 */
	private List<JRDefinition> orderedDefinitions;
	
	/**
	 * Storage where the older version of jasperreports are saved
	 */
	private File storage = ConfigurationManager.getStorage(JRBackwardManager.JR_COMPILER_STORAGE);
	
	/**
	 * List of the available destination for a compiled file, each element is an absolute path on the filesystem
	 */
	private List<String> availablePaths;
	
	/**
	 * The path where the file compiled must be placed, null means in the same directory of the source
	 */
	private String actualPath = null;
	
	/**
	 * The properties file
	 */
	private Preferences prefs = PropertiesHelper.INSTANCE_SCOPE.getNode(JaspersoftStudioPlugin.getUniqueIdentifier());
	
	/**
	 * Build the class and add to the list the supported JR version with the URL to dowload them
	 */
	protected JRBackwardManager(){
		definitions = new HashMap<String, JRDefinition>();
		orderedDefinitions = new ArrayList<JRDefinition>();
		loadPreferences();
		addDefinition(new JRDefinition("http://downloads.sourceforge.net/project/jasperreports/jasperreports/JasperReports%206.0.2/jasperreports-6.0.2-project.zip?r=&ts=1425379686&use_mirror=switch", "6.0.2")); //$NON-NLS-1$ //$NON-NLS-2$
		addDefinition(new JRDefinition("http://downloads.sourceforge.net/project/jasperreports/jasperreports/JasperReports%206.0.0/jasperreports-6.0.0-project.zip?r=&ts=1425379810&use_mirror=softlayer-ams", "6.0.0")); //$NON-NLS-1$ //$NON-NLS-2$
		
		addDefinition(new JRDefinition("http://downloads.sourceforge.net/project/jasperreports/jasperreports/JasperReports%205.5.2/jasperreports-5.5.2-project.zip?r=&ts=1425053832&use_mirror=cznic", "5.5.2")); //$NON-NLS-1$ //$NON-NLS-2$
		addDefinition(new JRDefinition("http://downloads.sourceforge.net/project/jasperreports/jasperreports/JasperReports%205.5.1/jasperreports-5.5.1-project.zip?r=&ts=1425379891&use_mirror=cznic", "5.5.1")); //$NON-NLS-1$ //$NON-NLS-2$
		addDefinition(new JRDefinition("http://downloads.sourceforge.net/project/jasperreports/jasperreports/JasperReports%205.5.0/jasperreports-5.5.0-project.zip?r=&ts=1425379937&use_mirror=cznic", "5.5.0")); //$NON-NLS-1$ //$NON-NLS-2$
		addDefinition(new JRDefinition("http://downloads.sourceforge.net/project/jasperreports/jasperreports/JasperReports%205.2.0/jasperreports-5.2.0-project.zip?r=&ts=1425379975&use_mirror=heanet", "5.2.0")); //$NON-NLS-1$ //$NON-NLS-2$
		addDefinition(new JRDefinition("http://downloads.sourceforge.net/project/jasperreports/jasperreports/JasperReports%205.1.2/jasperreports-5.1.2-project.zip?r=&ts=1425380005&use_mirror=heanet", "5.1.2")); //$NON-NLS-1$ //$NON-NLS-2$
		addDefinition(new JRDefinition("http://downloads.sourceforge.net/project/jasperreports/jasperreports/JasperReports%205.1.0/jasperreports-5.1.0-project.zip?r=&ts=1425380040&use_mirror=heanet", "5.1.0")); //$NON-NLS-1$ //$NON-NLS-2$
		addDefinition(new JRDefinition("http://downloads.sourceforge.net/project/jasperreports/jasperreports/JasperReports%205.0.4/jasperreports-5.0.4-project.zip?r=&ts=1425380065&use_mirror=heanet", "5.0.4")); //$NON-NLS-1$ //$NON-NLS-2$
		addDefinition(new JRDefinition("http://downloads.sourceforge.net/project/jasperreports/jasperreports/JasperReports%205.0.1/jasperreports-5.0.1-project.zip?r=&ts=1425380101&use_mirror=kent", "5.0.1")); //$NON-NLS-1$ //$NON-NLS-2$
		
		addDefinition(new JRDefinition("http://downloads.sourceforge.net/project/jasperreports/jasperreports/JasperReports%204.8.0/jasperreports-4.8.0-project.zip?r=&ts=1425380144&use_mirror=vorboss", "4.8.0")); //$NON-NLS-1$ //$NON-NLS-2$
		addDefinition(new JRDefinition("http://downloads.sourceforge.net/project/jasperreports/jasperreports/JasperReports%204.7.0/jasperreports-4.7.0-project.zip?r=&ts=1425380186&use_mirror=garr", "4.7.0")); //$NON-NLS-1$ //$NON-NLS-2$
		addDefinition(new JRDefinition("http://downloads.sourceforge.net/project/jasperreports/jasperreports/JasperReports%204.6.0/jasperreports-4.6.0-project.zip?r=&ts=1425380207&use_mirror=softlayer-ams", "4.6.0")); //$NON-NLS-1$ //$NON-NLS-2$
	  addDefinition(new JRDefinition("http://downloads.sourceforge.net/project/jasperreports/jasperreports/JasperReports%204.5.0/jasperreports-4.5.0-project.zip?r=&ts=1425380237&use_mirror=heanet", "4.5.0")); //$NON-NLS-1$ //$NON-NLS-2$
		addDefinition(new JRDefinition("http://downloads.sourceforge.net/project/jasperreports/jasperreports/JasperReports%204.1.3/jasperreports-4.1.3-project.zip?r=&ts=1425380274&use_mirror=freefr", "4.1.3")); //$NON-NLS-1$ //$NON-NLS-2$
		addDefinition(new JRDefinition("http://downloads.sourceforge.net/project/jasperreports/jasperreports/JasperReports%204.1.1/jasperreports-4.1.1-project.zip?r=&ts=1425380302&use_mirror=kent", "4.1.1")); //$NON-NLS-1$ //$NON-NLS-2$
		addDefinition(new JRDefinition("http://downloads.sourceforge.net/project/jasperreports/jasperreports/JasperReports%204.0.2/jasperreports-4.0.2-project.zip?r=&ts=1425380343&use_mirror=softlayer-ams", "4.0.2")); //$NON-NLS-1$ //$NON-NLS-2$
		addDefinition(new JRDefinition("http://downloads.sourceforge.net/project/jasperreports/jasperreports/JasperReports%204.0.0/jasperreports-4.0.0-project.zip?r=&ts=1425380372&use_mirror=netcologne", "4.0.0")); //$NON-NLS-1$ //$NON-NLS-2$
		
		addDefinition(new JRDefinition("http://downloads.sourceforge.net/project/jasperreports/archive/jasperreports/JasperReports%203.7.5/jasperreports-3.7.5-project.zip?r=&ts=1425380429&use_mirror=kent", "3.7.5")); //$NON-NLS-1$ //$NON-NLS-2$
		addDefinition(new JRDefinition("http://downloads.sourceforge.net/project/jasperreports/archive/jasperreports/JasperReports%203.7.4/jasperreports-3.7.4-project.zip?r=&ts=1425380510&use_mirror=freefr", "3.7.4")); //$NON-NLS-1$ //$NON-NLS-2$
		addDefinition(new JRDefinition("http://downloads.sourceforge.net/project/jasperreports/archive/jasperreports/JasperReports%203.7.2/jasperreports-3.7.2-project.zip?r=&ts=1425380524&use_mirror=freefr", "3.7.2")); //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	/**
	 * Add a JRdefinition both on the map and on the list. The key on the map 
	 * is the version of the definition
	 * 
	 * @param def definition to add, must be not null
	 */
	private void addDefinition(JRDefinition def){
		definitions.put(def.getVersion(), def);
		orderedDefinitions.add(def);
	}
	
	/**
	 * Return all the added definition in the order the were inserted
	 * 
	 * @return a not null list
	 */
	public Collection<JRDefinition> getDefinitions(){
		return orderedDefinitions;
	}
	
	/**
	 * Get the folder on the filesystem containing the file of a specific definition. If
	 * the folder is not present in the storage then it is downloaded otherwise the one
	 * from the storage is returned. When a folder is downloaded an the JR files are
	 * placed in the storage also the JRToolkit.jar is placed with them. This file 
	 * is necessary for the compilation
	 * 
	 * @param version the version of the JR to retrieve
	 * @param monitor monitor used for the download
	 * @return the folder where the jr files of the requested version are
	 * @throws Exception exception when a version of JR must be downloaded but the url
	 * is not valid
	 */
	public File getJRFolder(String version, IProgressMonitor monitor) throws Exception{
		File jrFolder = new File(storage, version);
		if (!checkJRInstallation(jrFolder)){
			File resource = fetchJR(definitions.get(version), monitor);
			if (resource != null && !monitor.isCanceled()){
				unZip(resource, jrFolder, monitor);
				fetchResource("com/jaspersoft/studio/backward/resources/JRToolKit.jar", jrFolder, "JRToolKit.jar"); //$NON-NLS-1$ //$NON-NLS-2$
				if (monitor.isCanceled()) {
					recursiveDelete(jrFolder);
				}
			}
		}
		return jrFolder;
	}
	
	/**
	 * Read a resource from the current plugin and save it with a specific
	 * name inside a specified folder folder. If a file was already define inside 
	 * the specified folder with the same name, then it doesn't do nothing
	 * 
	 * @param path the path of the resource inside the plugin
	 * @param fileName the name of the file that will be created on the specified folder
	 * @param destinationDir directory where the file will be saved
	 */
	private void fetchResource(String path, File destinationDir, String fileName){
		URL url = JaspersoftStudioPlugin.getInstance().getClass().getClassLoader().getResource(path);
		try{
			InputStream is = url.openStream();   
			File tempFile = new File(destinationDir, fileName);
			if (!tempFile.exists()){
				FileOutputStream outputStream = new FileOutputStream(tempFile);
				int read = 0;
				byte[] bytes = new byte[1024];
				while ((read = is.read(bytes)) != -1) {
						outputStream.write(bytes, 0, read);
				}
				outputStream.close();
			}
		}catch(Exception ex){
			JaspersoftStudioPlugin.getInstance().logError(MessageFormat.format(Messages.JRBackwardManager_dowloadError, new Object[]{path}), ex);
		}
	}
	
	/**
	 * Download the zip of a JR from the server and save it on the temp folder
	 * 
	 * @param def the definition of the jr to download, containing the url to the server
	 * @param monitor the monitor to execute the operation. It update the taskname with the abount of MB downloaded
	 * @return the file on the temp folder, zip extension (but depends from the url), containing the JR build of the requested version
	 * @throws Exception launched when something goes wrong with the download
	 */
	private File fetchJR(JRDefinition def, IProgressMonitor monitor) throws Exception{
		URL resource = new URL(def.getResourceURL());
		InputStream is = resource.openStream();   
		String tempDir = System.getProperty("java.io.tmpdir"); //$NON-NLS-1$
		File tempFile = new File(tempDir, "jr"); //$NON-NLS-1$
		int counter = 0;
		while(tempFile.exists()){
			tempFile = new File(tempDir, "jr_"+counter); //$NON-NLS-1$
			counter++;
		}
		FileOutputStream outputStream = new FileOutputStream(tempFile);
		int read = 0;
		byte[] bytes = new byte[1024];
		monitor.setTaskName(MessageFormat.format(Messages.JRBackwardManager_downloadStart, new Object[]{def.getVersion()}));
		int iterationNumber = 1;
		String lastTaskSize = null;
		while (((read = is.read(bytes)) != -1) && !monitor.isCanceled()) {
			outputStream.write(bytes, 0, read);
			BigDecimal bd = new BigDecimal(Float.toString((float)iterationNumber/1024));
      bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);    
      String newSize = bd.toString();
      //update only when the string changes, to avoid the flickering
			if (lastTaskSize == null || !lastTaskSize.equals(newSize)){
				lastTaskSize = newSize;
				monitor.setTaskName(MessageFormat.format(Messages.JRBackwardManager_downloadSize, new Object[]{def.getVersion(), lastTaskSize}));
			}
			iterationNumber++;
		}
		outputStream.close();
		if (monitor.isCanceled()){
			tempFile.delete();
			tempFile = null;
		}
		return tempFile;
	}
	
	/**
	 * Return if the file is necessary, so if in the first or second level is inside the folder
	 * dist or lib
	 * 
	 * @param pathComponents the complete path of a file inside the zip. 
	 * Each segment is a folder and the last one is the file
	 * @return true if this file of the zip must be unpacked inside the storage, false otherwise
	 */
	private boolean isNecessary(String[] pathComponents ){
		if (pathComponents.length>1){
			if (BUILD_FOLDER.equals(pathComponents[1]) || BUILD_FOLDER.equals(pathComponents[0])) return true;
			if (LIB_FOLDER.equals(pathComponents[1]) || LIB_FOLDER.equals(pathComponents[0])) return true;
		}
		return false;
	}
	
	/**
	 * Unzip a JR package downloaded from the server, but only the dist and folder are unzipped and
	 * mixed togheter in the destination
	 * 
	 * @param zipFile file to unzip
	 * @param outputFolder folder where the unzipped files must be placed
	 * @param monitor monitor for the operation, can be cancelled
	 */
	private void unZip(File zipFile, File outputFolder, IProgressMonitor monitor) {
		byte[] buffer = new byte[1024];
		try {
			if (!outputFolder.exists()) {
				outputFolder.mkdir();
			}
			// get the zip file content
			ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
			// get the zipped file list entry
			ZipEntry ze = zis.getNextEntry();
			monitor.setTaskName(Messages.JRBackwardManager_extracting);
			while (ze != null && !monitor.isCanceled()) {
				if (!ze.isDirectory()){
					String fileName = ze.getName();
					String[] pathComponents = fileName.split("/"); //$NON-NLS-1$
					if (isNecessary(pathComponents)){
						File newFile = new File(outputFolder, pathComponents[pathComponents.length-1]);
						new File(newFile.getParent()).mkdirs();
						FileOutputStream fos = new FileOutputStream(newFile);
						int len;
						while ((len = zis.read(buffer)) > 0) {
							fos.write(buffer, 0, len);
						}
						fos.close();
					}
				}
				ze = zis.getNextEntry();
			}
			zis.closeEntry();
			zis.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Get the fullpath of a JR inside the storage with a specific version
	 * 
	 * @param def definition of the requested JR
	 * @return the path to the requested version inside the storage or null if it is 
	 * not available
	 */
	public String getJRPath(JRDefinition def){
		File jrPath = new File(storage, def.getVersion());
		if (jrPath.exists()){
			return jrPath.getAbsolutePath();
		}
		return null;
	}
	
	/**
	 * Check if the current folder exist and is not empty
	 * 
	 * @param jrFolder the folder to check, must be not null
	 * @return true if the folder exist and it is not empty, false otherwise
	 */
	private boolean checkJRInstallation(File jrFolder){
		return jrFolder.exists() && jrFolder.listFiles().length > 0;
	}
	
	/**
	 * Check if a specific JR version is installed inside the storage
	 * 
	 * @param def definition of the JR to check, must be not null
	 * @return true if the JR version is available and can be used for 
	 * the compilation, false otherwise
	 */
	public boolean checkJRInstallation(JRDefinition def){
		File jrFolder = new File(storage, def.getVersion());
		return checkJRInstallation(jrFolder);
	}
	
	/**
	 * Get a list of all the JR available inside the storage and
	 * that can be used for the compilation
	 * 
	 * @return a not null list of all the installed version
	 */
	public List<JRDefinition> getInstallerJRs(){
		List<JRDefinition> result = new ArrayList<JRDefinition>();
		for(File resource : storage.listFiles()){
			String version = resource.getName();
			JRDefinition def = definitions.get(version);
			if (def != null) result.add(def);
		}
		return result;
	}
	
	/**
	 * Recursively delete all the files from a folder and then
	 * the folder itself
	 * 
	 * @param f element to delete
	 */
	private void recursiveDelete(File f) {
	  if (f.isDirectory()) {
	    for (File c : f.listFiles())
	    	recursiveDelete(c);
	  }
	  if (!f.delete()){
	   JaspersoftStudioPlugin.getInstance().logWarning(MessageFormat.format(Messages.JRBackwardManager_deleteError, new Object[]{f}));
	  }
	}
	
	/**
	 * Delete a JR version from the storage, if it is saved inside
	 * 
	 * @param def the definition that contains the version of the JR to delete
	 * from the storage, must be not null
	 */
	public void deleteJR(JRDefinition def){
		File jrPath = new File(storage, def.getVersion());
		if (jrPath.exists()) recursiveDelete(jrPath);
	}
	
	/**
	 * Get the current destination folder, where the compiled
	 * file must be placed
	 * 
	 * @return the destination folder or null if the compiled 
	 * must be placed in the same folder of the source file
	 */
	public String getDestinationFolder(){
		return actualPath; 
	}
	
	/**
	 * Return all the defined possible destination folders
	 * 
	 * @return not null list of all the defined destination folders
	 */
	public List<String> getDestinationFolders(){
		return availablePaths;
	}
	
	/**
	 * Add a destination path. If the path are more than 8 the older ones are removed
	 * 
	 * @param path the path to destination folder
	 * @param isCurrentSelection true if the added path is the one that should be actually ins
	 * use, false otherwise
	 */
	public void addDestinationPath(String path, boolean isCurrentSelection){
		if (!availablePaths.contains(path)) {
			availablePaths.add(0,path);
		}
		while(availablePaths.size()>8){
			String removed = availablePaths.remove(availablePaths.size()-1);
			if (removed.equals(actualPath)) actualPath = null;
		}
		if (isCurrentSelection){
			actualPath = path;
		}
		savePreferences();
	}
	
	/**
	 * Set the current path for the compiled file, must be a folder. 
	 * Could be null to use the same path of the source file
	 * 
	 * @param path the aboslute path of a folder on the filesystem or null
	 */
	public void setDestinationPath(String path){
		actualPath = path;
		savePreferences();
	}
	
	/**
	 * Save inside the application properties file the list of all the destination paths
	 * as an xml string
	 */
	private void savePreferences(){
		String xmlData = getXMLData();
		prefs.put(BACKWARD_PATHS, xmlData);
		try {
			prefs.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the xml string with the destination paths for the compiled file
	 * 
	 * @return xml definition
	 */
	private String getXMLData() {
		String result = "<savePaths>"; //$NON-NLS-1$
		for(String path : availablePaths){
			result+="<location path=\""+ path + "\" default=\""; //$NON-NLS-1$ //$NON-NLS-2$
			result+=path.equals(actualPath) + "\"/>"; //$NON-NLS-1$
		}
		result += "</savePaths>"; //$NON-NLS-1$
		return result;
	}
	
	/**
	 * Load the list of the template set from the preferences file. If one of them has the default 
	 * attribute set to true then it will be the selected default. If more of them has the attribute
	 * to true only the last one will be the default. A template set is added to the available ones
	 * only if it's file exist
	 */
	private void loadPreferences(){
		String paths = prefs.get(BACKWARD_PATHS, ""); //$NON-NLS-1$
		availablePaths = new ArrayList<String>();
		if (!paths.isEmpty()){
			try {
				Document document = JRXmlUtils.parse(new InputSource(new StringReader(paths)));
				NodeList adapterNodes = document.getDocumentElement().getChildNodes();
				for (int i = 0; i < adapterNodes.getLength(); ++i) {
					Node adapterNode = adapterNodes.item(i);
					if (adapterNode.getNodeType() == Node.ELEMENT_NODE) {
						String path = adapterNode.getAttributes().getNamedItem("path").getNodeValue(); //$NON-NLS-1$
						String defaultPath = adapterNode.getAttributes().getNamedItem("default").getNodeValue(); //$NON-NLS-1$
						availablePaths.add(path);
						if (Boolean.parseBoolean(defaultPath)) actualPath = path;
					}
				}
			} catch (JRException e) {
				e.printStackTrace();
			}
		}
	}
}
