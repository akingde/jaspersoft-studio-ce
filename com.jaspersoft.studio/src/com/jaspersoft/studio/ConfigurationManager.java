/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.Util;
import org.eclipse.osgi.service.datalocation.Location;
import org.osgi.service.prefs.Preferences;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;
import com.jaspersoft.studio.statistics.IFirstStartupAction;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.KeyValue;
import net.sf.jasperreports.engine.util.JRXmlUtils;

/**
 * Provide the methods to retrieve the installation path of the application, the path is cached after the first request.
 * It Offers also the method to interact with the files storage that can be used to save the configuration of the
 * application
 * 
 * @author Orlandin Marco
 * 
 */
public class ConfigurationManager {

	// Method to get the eclipse folder

	/**
	 * Where the path is cached
	 */
	private static String cachedPath = null;

	/**
	 * Get the path and cache it values
	 */
	private static void intializePath() {
		String path = null;
		Location configArea = Platform.getInstallLocation();
		String product = Platform.getProduct().getProperty("appName"); //$NON-NLS-1$ 
		if (configArea != null) {
			if (Util.isMac()) {
				path = configArea.getURL().toExternalForm() + "/"; //$NON-NLS-1$ //$NON-NLS-2$
				path = path + product + ".ini"; //$NON-NLS-1$
			} else
				path = configArea.getURL().toExternalForm() + product + ".ini"; //$NON-NLS-1$
		}
		cachedPath = path;
	}

	/**
	 * 
	 * Return the path of the application configuration INI file and cache it. Typically this file is inside the install
	 * location of the application
	 * 
	 * @return String represented a Path in URL format to the configuration file
	 */
	public static String getApplicationConfigurationPath() {
		if (cachedPath == null)
			intializePath();
		return cachedPath;
	}

	/**
	 * 
	 * Return the file of the application configuration INI file and cache it. Typically this file is inside the install
	 * location of the application
	 * 
	 * @return a java.io.File pointing at the file resource of the configuration INI of the current application or null if
	 *         something goes wrong
	 */
	public static File getApplicationConfigurationFile() {
		String configurationPath = getApplicationConfigurationPath();
		try {
			File configurationFile = new File(new URL(configurationPath).getFile());
			return configurationFile;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Return the current installation folder where the application configuration file is placed
	 * 
	 * @return String represented a Path in URL format to the location of the configuration file, or null if it can't be
	 *         found
	 */
	public static String getApplicationFolder() {
		if (cachedPath == null)
			intializePath();
		return cachedPath != null ? new File(cachedPath).getParent() : null;
	}

	/**
	 * Check if the configuration file of the application exist and if it can written or read
	 * 
	 * @return true if the file is accessible for r\w operation, false otherwise
	 */
	public static boolean isConfigurationAccessible() {
		File configurationFile = getApplicationConfigurationFile();
		if (configurationFile != null) {
			if (configurationFile.exists() && configurationFile.isFile()) {
				return configurationFile.canRead() && configurationFile.canWrite();
			}
		}
		return false;
	}

	/**
	 * Check if the configuration file of the application exist and if it can written or read. Also if the file is not
	 * accessible a warning message is shown
	 * 
	 * @return true if the file is accessible for r\w operation, false otherwise
	 */
	public static boolean isConfigurationAccessibleWithMessage() {
		boolean accessible = isConfigurationAccessible();
		if (!accessible) {
			String configurationPath = getApplicationConfigurationFile().getAbsolutePath();
			UIUtils.showWarning(Messages.ConfigurationManager_notAccessibleTitle,
					MessageFormat.format(Messages.ConfigurationManager_notAccessibleMessage, new Object[] { configurationPath }));
		}
		return accessible;
	}

	// Methods to get the installation dependent storage

	/**
	 * Folder containing the configurations of all the JSS installed on the system
	 */
	private static File jssDataFolder = null;

	/**
	 * Configuration folder of the running JSS installation
	 */
	private static File appDataFolder = null;

	/**
	 * Name of the folder that will contains the information of all the JSS installations on the current machine. This
	 * folder will be placed in the "AppData like" folder on the current operative system
	 */
	private static final String JSS_APPLICATION_ID = "Jaspersoft Studio"; //$NON-NLS-1$

	/**
	 * Name of the file where the path of a JSS installation will be save. The installation path bind a JSS instance to a
	 * configuration folder
	 */
	private static final String PATH_FILE = ".path"; //$NON-NLS-1$

	/**
	 * Get the folder where all the configurations of the current installed JSS are saved. The folder can change between
	 * the various operative systems. The location is cached after the first time it is read
	 * 
	 * @return a not null folder where the configuration of all the JSS are saved
	 */
	private static File getJSSDataFolder() {
		if (jssDataFolder == null) {
			String workingDirectory;
			if (Util.isWindows()) {
				workingDirectory = System.getenv("AppData"); //$NON-NLS-1$
			} else if (Util.isLinux()) {
				workingDirectory = System.getProperty("user.home"); //$NON-NLS-1$
				if (!workingDirectory.endsWith("/")) //$NON-NLS-1$
					workingDirectory += "/"; //$NON-NLS-1$
				workingDirectory += ".config"; //$NON-NLS-1$
			} else {
				workingDirectory = System.getProperty("user.home"); //$NON-NLS-1$
				workingDirectory += "/Library/Application Support"; //$NON-NLS-1$
			}
			jssDataFolder = new File(workingDirectory, JSS_APPLICATION_ID);
			jssDataFolder.mkdirs();
		}
		return jssDataFolder;
	}

	/**
	 * Get the configuration folder of the currently running JSS installation. If a folder is not found a new one is
	 * created. This folder is cached to avoid to search it each time it is requested. If the folder is created it is
	 * supposed to be the first start of the application, so the contributed actions to be executed at the first start are
	 * run.
	 * 
	 * @param the
	 *          name of the folder inside the installation dependent directory of JSS. If a directory with that name
	 *          doesn't exist then it is created
	 * @return a not null folder where the configuration of the currently running JSS are saved
	 */
	private static File getAppDataFolder() {
		if (appDataFolder == null) {
			Location configArea = Platform.getInstallLocation();
			if (configArea != null) {
				String path = configArea.getURL().toExternalForm();
				File appFolder = getJSSDataFolder();
				if (appFolder != null)
					for (File file : appFolder.listFiles()) {
						String appId = getAppId(file);
						if (appId != null && appId.equals(path)) {
							appDataFolder = file;
							break;
						}
					}
				// If the appDataFolder is null a new one is created
				if (appDataFolder == null) {
					// For backward compatibility try to used the original UUID if available
					PropertiesHelper ph = PropertiesHelper.getInstance();
					String startingUUID = ph.getString("UUID", null); //$NON-NLS-1$
					if (startingUUID == null) {
						// In the old version JSS stored an unique UUID in the preferences, now this is not
						// used anymore but if there is that uuid stored try to reuse it for the folder name
						// otherwise a random one is generated
						startingUUID = UUID.randomUUID().toString();
					}
					appDataFolder = new File(appFolder, startingUUID);
					while (appDataFolder.exists()) {
						appDataFolder = new File(appFolder, UUID.randomUUID().toString());
					}
					appDataFolder.mkdir();
					writeAppId(appDataFolder, path);
					// Folder just created, it is the first startup
					for (IFirstStartupAction action : JaspersoftStudioPlugin.getExtensionManager().getFirstStartupActions()) {
						action.executeFirstStartupAction(appDataFolder);
					}
				}
			}
		}
		return appDataFolder;
	}

	/**
	 * Search inside the the application dependent storage for a folder with a specific name and return it. This folder
	 * can be used to store informations related to a specific installation (like statistics for example). If the folder
	 * doesn't exist it is created
	 * 
	 * @param folderName
	 *          a not null folder name
	 * @return a not null and existing folder with the specified name defined inside the installation dependent storage
	 */
	public static File getAppDataFolder(String folderName) {
		File dataFolder = new File(getAppDataFolder(), folderName);
		dataFolder.mkdir();
		return dataFolder;
	}

	/**
	 * Get the unique UUID associated with the current eclipse installation
	 * 
	 * @return a not null string representing the unique UUID of this JSS installation
	 */
	public static String getInstallationUUID() {
		return getAppDataFolder().getName();
	}

	/**
	 * Search a .path file inside the passed folder and read the first line. It should be the installation path of a JSS,
	 * which is also the id of that installation
	 * 
	 * @param appDataFolder
	 *          the folder, must be a JSS configuration folder
	 * @return the path of a JSS installation, that is its identifier. Null if the passed folder is not valid
	 */
	private static String getAppId(File appDataFolder) {
		File textFile = new File(appDataFolder, PATH_FILE);
		if (textFile.exists()) {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(textFile.getAbsolutePath()));
				String sCurrentLine = reader.readLine();
				return sCurrentLine;
			} catch (Exception e) {
				e.printStackTrace();
				JaspersoftStudioPlugin.getInstance().logError(Messages.UsageManager_errorPathFile, e);
			} finally {
				FileUtils.closeStream(reader);
			}
		}
		return null;
	}

	/**
	 * Write a .path file inside the passed folder and put as first line the passed path. It should be the installation
	 * path of the current JSS, which is also the id of that installation
	 * 
	 * @param appDataFolder
	 *          the folder, must be a JSS configuration folder
	 * @param installationPath
	 *          the path of the current JSS installation, that is its identifier
	 */
	private static void writeAppId(File appDataFolder, String installationPath) {
		File textFile = new File(appDataFolder, PATH_FILE);
		if (textFile.exists())
			textFile.delete();
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(textFile.getAbsolutePath()));
			writer.write(installationPath);
		} catch (IOException e) {
			e.printStackTrace();
			JaspersoftStudioPlugin.getInstance().logError(Messages.UsageManager_errorPathFile, e);
		} finally {
			FileUtils.closeStream(writer);
		}
	}

	// Methods to get the workspace dependent storage

	/**
	 * Return a storage with a specific name, if the storage doesn't exist then it is created. A storage is a specific
	 * folder in the filesystem.
	 * 
	 * @param storageName
	 *          the name of the storage\folder
	 * @return a file to the requested storage
	 */
	public static File getStorage(String storageName) {
		IPath configurationPath = JaspersoftStudioPlugin.getInstance().getStateLocation();
		File configurationFolder = configurationPath.toFile();
		File storage = new File(configurationFolder, storageName);
		if (!storage.exists())
			storage.mkdir();
		return storage;
	}

	/**
	 * Return a list to all the content of a storage. If the storage dosen't exist then it is created.
	 * <p>
	 * 
	 * NOTE: hidden files are not considered.
	 * 
	 * @param storageName
	 *          the name of the storage
	 * @return a not null array of file that map the content of the storage folder
	 */
	public static File[] getStorageContent(String storageName) {
		File storage = getStorage(storageName);
		List<File> result = new ArrayList<File>();
		File[] listFiles = storage.listFiles();
		for (File f : listFiles) {
			if (!f.isHidden()) {
				result.add(f);
			}
		}
		return result.toArray(new File[result.size()]);
	}

	/**
	 * Remove a resource contained into a specified storage. If the storage doesnt' exist it is created.
	 * 
	 * @param storageName
	 *          the name of the storage
	 * @param resourceName
	 *          the name of the resource to delete
	 * @return the result of the delete operation
	 */
	public static boolean removeStoregeResource(String storageName, String resourceName) {
		File storage = getStorage(storageName);
		File resource = new File(storage, resourceName);
		if (resource.exists())
			return resource.delete();
		else
			return false;
	}

	/**
	 * Return a resource from the storage. If the storage dosen't exist then it is created
	 * 
	 * @param storageName
	 *          the name of the storage
	 * @param resourceName
	 *          the name of the resource
	 * @return the requested resource or null if it can't be found
	 */
	public static File getStorageResource(String storageName, String resourceName) {
		return getStorageResource(getStorage(storageName), resourceName);
	}

	/**
	 * Return a resource from the storage. Since the storage is accessed with a direct file handle to it then it must be
	 * already existing
	 * 
	 * @param storageName
	 *          and handle to the storage
	 * @param resourceName
	 *          the name of the resource
	 * @return the requested resource or null if it can't be found
	 */
	public static File getStorageResource(File resourceStorage, String resourceName) {
		File resource = new File(resourceStorage, resourceName);
		if (resource.exists())
			return resource;
		return null;
	}

	/**
	 * Utility method used to convert the old setting storage based on the preferences on the setting storage based on
	 * file, this is done silently to migrate the old settings to the new storage system
	 * 
	 * @param preferenceKey
	 *          the key of properties that contains the configuration that must be converted
	 * @param storageName
	 *          the storage where the new configuration is placed
	 * @param nameProvider
	 *          the provider for the name of the new storage files.
	 */
	public static void convertPropertyToStorage(String preferenceKey, String storageName,
			IConversionFilenameProvider nameProvider) {
		Preferences prefs = PropertiesHelper.INSTANCE_SCOPE.getNode(JaspersoftStudioPlugin.getUniqueIdentifier());
		String xml = prefs.get(preferenceKey, null);
		if (xml == null)
			return;
		List<File> createtElements = new ArrayList<File>();
		StringReader sr = new StringReader(xml);
		try {
			Document document = JRXmlUtils.parse(new InputSource(sr));
			NodeList configurationNodes = document.getDocumentElement().getChildNodes();
			File storage = getStorage(storageName);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			for (int i = 0; i < configurationNodes.getLength(); ++i) {
				Node configurationNode = configurationNodes.item(i);
				if (configurationNode.getNodeType() == Node.ELEMENT_NODE) {
					DOMSource source = new DOMSource(configurationNode);
					String name = nameProvider.getFileName(configurationNode);
					if (name != null) {
						File xmlTargetFile = new File(storage, name);
						if (!xmlTargetFile.exists()) {
							createtElements.add(xmlTargetFile);
							FileOutputStream stream = new FileOutputStream(xmlTargetFile);
							try {
								StreamResult result = new StreamResult(stream);
								transformer.transform(source, result);
							} finally {
								FileUtils.closeStream(stream);
							}
						} else
							throw new Exception("File " + xmlTargetFile.getAbsolutePath() + " already exist");
					}
				}
			}
			prefs.remove(preferenceKey);
		} catch (Exception e) {
			FileUtils.closeStream(sr);
			JaspersoftStudioPlugin.getInstance().logError("Error converting the element", e);
			// Do the revert of the created files
			for (File createdElement : createtElements)
				if (createdElement.exists())
					createdElement.delete();
			throw new RuntimeException(e);
		}
	}

	// Methods to get the configuration file and change it
	
	private static final String CMD_VMARGS = "-vmargs"; //$NON-NLS-1$
	
	private static final String CMD_NL = "-nl"; //$NON-NLS-1$

	/**
	 * Convert a list of strings to a single string, each string except the 
	 * last has a new line after it 
	 * 
	 * @param list the list of string
	 * @return a single string with all the string in the list concatenated
	 */
	private static String listToString(List<String> list){
		StringBuilder result = new StringBuilder();
		if (!list.isEmpty()){
			String separator = System.getProperty("line.separator");
			String lastLine = null;
			//sarch the las line with a value
			for(int i = list.size() -1 ; i>=0; i--){
				String currentLine = list.get(i);
				if (currentLine != null){
					lastLine = currentLine;
					break;
				}
			}
			for (String outLine : list) {
				if (outLine != null) {
					result.append(outLine);
					if (outLine != lastLine){
						result.append(separator);
					}
				}
			}
		}
		return result.toString();
	}

	/**
	 * Read the configuration file of the application and rewrite it with a new
	 * regional code if the code is changed then it is also requested a platform
	 * restart. The regional code will be set at the place of the old code if
	 * found, otherwise before the first parameter found between -clean, -vm,
	 * -vmargs. If none of this parameters are found then it is set at the end
	 * of the file
	 * 
	 * @param locale the new locale
	 * @return true if the change to the configuration file went good, false otherwise
	 */
	public static boolean changeLocale(String locale) {
		boolean fileChanged = false;
		if (ConfigurationManager.isConfigurationAccessibleWithMessage()){
			File configurationFile = ConfigurationManager.getApplicationConfigurationFile();
			BufferedReader in = null; 
			BufferedWriter out = null;
			try {
				in = new BufferedReader(new FileReader(configurationFile));
				String line = in.readLine();
				List<String> configLines = new ArrayList<String>();
				int localePosition = -1;
				int lineNumber = 0;
				while (line != null) {
					if (line.equals("-nl"))localePosition = lineNumber + 1; //$NON-NLS-1$
					else if (localePosition == -1
							&& (line.equals(CMD_VMARGS) || line.equals("-clean") || line.equals("-vm"))) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						configLines.add(CMD_NL); //$NON-NLS-1$
						configLines.add(""); //$NON-NLS-1$
						localePosition = lineNumber + 1;
					}
					configLines.add(line);
					lineNumber++;
					line = in.readLine();
				}
				if (localePosition != -1) {
					if (configLines.get(localePosition).equals(locale)) {
						FileUtils.closeStream(in);
						FileUtils.closeStream(out);
						// The file has already the right regional code, there
						// is no need to restart eclipse
						return false;
					} else{
						//Change the locale value
						configLines.set(localePosition, locale);
					}
				} else {
					configLines.add("-nl"); //$NON-NLS-1$
					configLines.add(locale);
				}
				fileChanged = writeConfigurationFile(listToString(configLines));
			} catch (Exception ex){
				ex.printStackTrace();
				JaspersoftStudioPlugin.getInstance().logError(ex);
				// Configuration file not found, show an error message
				MessageDialog.openWarning(UIUtils.getShell(),
						Messages.SwitchLanguageHandler_errorTitle,
						MessageFormat.format(
								Messages.SwitchLanguageHandler_errorMessage,
								new Object[] { configurationFile.getAbsolutePath() }));
			} finally {
				FileUtils.closeStream(in);
				FileUtils.closeStream(out);
				
			}
		}
		return fileChanged;
	}
	
	/**
	 * Read the configuration file of the application and rewrite it with new
	 * vmargs. This arguments will override the value of the currently existing one,
	 * otherwise it will be added.
	 * 
	 * @param the pair of name\value of the vmargs. If a value of a vmarg is null
	 * it is removed form the file if present
	 * @return true if the file was rewritten correctly, false otherwise
	 */
	public static boolean changeVMArgs(KeyValue<String, String>[] vmargs) {
		boolean fileChanged = false;
		if (ConfigurationManager.isConfigurationAccessibleWithMessage()){
			File configurationFile = ConfigurationManager.getApplicationConfigurationFile();
			BufferedReader in = null; 
			BufferedWriter out = null;
			try {
				in = new BufferedReader(new FileReader(configurationFile));
				String line = in.readLine();
				List<String> configLines = new ArrayList<String>();
				int vmArgsPosition = -1;
				int lineNumber = 0;
				HashMap<String, Integer> argsMap = new HashMap<String, Integer>();
				while (line != null) {
					if (vmArgsPosition == -1 && line.equals(CMD_VMARGS)){
						vmArgsPosition = lineNumber;
					}
					configLines.add(line);
					if (vmArgsPosition != -1 && line.contains("=")){
						String[] nameValue = line.split("=");
						argsMap.put(nameValue[0], lineNumber);
					}
					lineNumber++;
					line = in.readLine();
				}
				if (vmArgsPosition != -1) {
					for(KeyValue<String, String> vmarg : vmargs){
						Integer existingIndex = argsMap.get(vmarg.key);
						if (existingIndex != null){
							//There is already an argument with the same name in the file
							if (vmarg.value == null){
								//need to remove it
								configLines.set(existingIndex, null);
							} else{
								//Need to update the value
								configLines.set(existingIndex, vmarg.key + "=" + vmarg.value);
							}
						} else {
							//There is not an argument with the same name in the file
							if (vmarg.value != null){
								//Add it at the end
								configLines.add(vmarg.key + "=" + vmarg.value);
							}
						}
					}
				} else {
					configLines.add(CMD_VMARGS); //$NON-NLS-1$
					for(KeyValue<String, String> vmarg : vmargs){
						configLines.add(vmarg.key + "=" + vmarg.value);
					}
				}
				fileChanged = ConfigurationManager.writeConfigurationFile(listToString(configLines));
			} catch (Exception ex){
				ex.printStackTrace();
				JaspersoftStudioPlugin.getInstance().logError(ex);
				// Configuration file not found, show an error message
				MessageDialog.openWarning(UIUtils.getShell(),
						Messages.SwitchLanguageHandler_errorTitle,
						MessageFormat.format(
								Messages.SwitchLanguageHandler_errorMessage,
								new Object[] { configurationFile.getAbsolutePath() }));
			} finally {
				FileUtils.closeStream(in);
				FileUtils.closeStream(out);
				
			}
		}
		return fileChanged;
	}

	/**
	 * Write a configuration of eclipse to the configuration file. Before to do this it create a backup
	 * 
	 * @param configurationContent
	 *          the new configuration
	 * @return true if the configuration was written correctly, false otherwise
	 */
	public static boolean writeConfigurationFile(String configurationContent) {
		if (isConfigurationAccessibleWithMessage()) {
			try {
				// create a backup first
				File fini = getApplicationConfigurationFile();
				if (fini.exists()) {
					try {
						org.apache.commons.io.FileUtils.copyFile(fini, new File(fini.toString() + ".bak"));
					} catch (IOException e) {
						JaspersoftStudioPlugin.getInstance().logError("Unable to create bak file for the .ini file", e);
					}
				}

				if (!fini.exists()) {
					fini.getParentFile().mkdirs();
					fini.createNewFile();
				}
				org.apache.commons.io.FileUtils.writeStringToFile(fini, configurationContent);
				return true;
			} catch (IOException e) {
				UIUtils.showError(e);
			}
		}
		return false;
	}
	
	/**
	 * @return the current workspace location
	 */
	public static String getCurrentWorkspaceLocation() {
		return ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
	}
}
