/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.editor.action.exporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.PlatformUI;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.ibm.icu.text.MessageFormat;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.exporter.BaseResource;
import com.jaspersoft.studio.editor.action.exporter.IExportedResourceHandler;
import com.jaspersoft.studio.editor.action.exporter.IResourceDefinition;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.repository.RepositoryView;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.model.server.MServers;
import com.jaspersoft.studio.server.model.server.ServerProfile;

import net.sf.jasperreports.eclipse.ui.util.RunnableOverwriteQuestion;
import net.sf.jasperreports.eclipse.ui.util.RunnableOverwriteQuestion.RESPONSE_TYPE;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.CastorHelper;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Pair;
import net.sf.jasperreports.engine.util.JRXmlUtils;

/**
 * Exporter used to import/export the server connections
 * 
 * @author Orlandin Marco
 *
 */
public class ExportedServersHandler implements IExportedResourceHandler {

	/**
	 * Folder name for the exported connections
	 */
	private static final String EXPORTED_FOLDER_NAME = "jrsConnections"; //$NON-NLS-1$

	/**
	 * Filename used to store metadata of the exported resources
	 */
	private static final String INDEX_FILE_NAME = "index.properties";

	/**
	 * Cache when the list of exportable resource definition is requested, used to
	 * avoid multiple calculation
	 */
	private List<IResourceDefinition> cachedExportableResources = null;

	/**
	 * Cache when the list of importable resource definition is requested, used to
	 * avoid multiple calculation of the same container
	 */
	private Pair<String, List<IResourceDefinition>> cachedImportableResources = null;

	@Override
	public List<IResourceDefinition> getRestorableResources(File exportedContainer) {
		String containerPath = exportedContainer.getAbsolutePath();
		if (cachedImportableResources == null || !cachedImportableResources.getKey().equals(containerPath)) {

			File exportedFolder = new File(exportedContainer, EXPORTED_FOLDER_NAME);
			File indexFile = new File(exportedFolder, INDEX_FILE_NAME);
			if (indexFile.exists()) {
				FileInputStream is = null;
				try {
					List<IResourceDefinition> result = new ArrayList<>();
					is = new FileInputStream(indexFile);
					Properties loadedProperties = new Properties();
					loadedProperties.load(is);
					for (Entry<Object, Object> entry : loadedProperties.entrySet()) {
						BaseResource resource = new BaseResource(entry.getValue().toString());
						resource.setData(entry.getKey());
						result.add(resource);
					}
					cachedImportableResources = new Pair<>(containerPath, result);
				} catch (Exception ex) {
					JaspersoftStudioPlugin.getInstance().logError(ex);
					cachedImportableResources = new Pair<>(containerPath, new ArrayList<>());
				} finally {
					FileUtils.closeStream(is);
				}
			} else {
				cachedImportableResources = new Pair<>(containerPath, new ArrayList<>());
			}
		}
		return cachedImportableResources.getValue();
	}

	@Override
	public List<IResourceDefinition> getExportableResources() {
		if (cachedExportableResources == null) {
			Collection<MServerProfile> profiles = ServerManager.getServerProfiles();
			cachedExportableResources = new ArrayList<>();
			for (MServerProfile profile : profiles) {
				BaseResource resource = new BaseResource(profile.getDisplayText());
				resource.setData(profile);
				cachedExportableResources.add(resource);
			}
		}
		return cachedExportableResources;
	}

	@Override
	public File exportContentFolder(List<IResourceDefinition> resourcesToExport) {

		// Create the set of the resources that should be exported
		HashSet<MServerProfile> resourcesToExportSet = new HashSet<>();
		for (IResourceDefinition definition : resourcesToExport) {
			resourcesToExportSet.add((MServerProfile) definition.getData());
		}

		Properties props = new Properties();
		File tempDir = new File(System.getProperty("java.io.tmpdir")); //$NON-NLS-1$
		tempDir.deleteOnExit();
		File destDir = new File(tempDir, EXPORTED_FOLDER_NAME);
		if (destDir.exists())
			FileUtils.recursiveDelete(destDir);
		destDir.mkdirs();
		int index = 0;
		for (MServerProfile serverConnection : ServerManager.getServerProfiles()) {
			if (resourcesToExportSet.contains(serverConnection)) {
				String fileName = "jrsConnection" + index;
				save(serverConnection, new File(destDir, fileName)); // $NON-NLS-1$
				props.put(fileName, serverConnection.getDisplayText());
				index++;
			}
		}

		// Write the index file
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File(destDir, INDEX_FILE_NAME));
			props.store(out, "Exported Server Index");
		} catch (Exception ex) {
			JaspersoftStudioPlugin.getInstance().logError(ex);
		} finally {
			FileUtils.closeStream(out);
		}
		return destDir;
	}

	@Override
	public void restoreContentFolder(File exportedContainer, List<IResourceDefinition> resourcesToImport) {
		File elementsFolder = new File(exportedContainer, EXPORTED_FOLDER_NAME);
		if (elementsFolder.exists()) {
			// Load the exported server
			List<MServerProfile> loadedProfiles = load(elementsFolder, resourcesToImport);
			HashMap<String, MServerProfile> existingProfiles = getExistingProfiles();
			List<String> duplicatedConnection = new ArrayList<>();
			for (MServerProfile profile : loadedProfiles) {
				if (existingProfiles.containsKey(profile.getValue().getName())) {
					duplicatedConnection.add(profile.getValue().getName());
				}
			}

			// Check for duplicate
			RESPONSE_TYPE response = RESPONSE_TYPE.KEEP_BOTH;
			if (!duplicatedConnection.isEmpty())
				response = askOverwrite(duplicatedConnection);

			// Import the server according to the user response
			for (MServerProfile profile : loadedProfiles) {
				String name = profile.getValue().getName();
				if (existingProfiles.containsKey(name)) {
					if (response == RESPONSE_TYPE.KEEP_BOTH) {
						profile.getValue().setName(getName(existingProfiles, name));
						ServerManager.addServerProfile(profile);
					} else if (response == RESPONSE_TYPE.OVERWRITE) {
						ServerManager.removeServerProfile(existingProfiles.get(name));
						ServerManager.addServerProfile(profile);
					}
				} else {
					ServerManager.addServerProfile(profile);
				}
			}
			refreshServers();
		}
	}

	/**
	 * Return the hashmap of the currently defined servers
	 * 
	 * @return a not null hashmap where the key are the serer names, and the value
	 *         the server definitions
	 */
	private HashMap<String, MServerProfile> getExistingProfiles() {
		HashMap<String, MServerProfile> result = new HashMap<>();
		for (MServerProfile profile : ServerManager.getServerProfiles()) {
			result.put(profile.getValue().getName(), profile);
		}
		return result;
	}

	/**
	 * Save the passed server definition as xml into the specified location.
	 * 
	 * @param serverProfile
	 *            the element to convert into xml
	 * @param destination
	 *            the destination of the xml
	 */
	private void save(MServerProfile serverProfile, File destination) {
		FileOutputStream stream = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(serverProfile.toXML())));
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
	 * Read all the files into the passed folder and convert them into server
	 * connections
	 * 
	 * @param serversFolder
	 *            folder containing the xml definition of the servers
	 * @return a not null list of the servers created from XMLs
	 */
	private List<MServerProfile> load(File serversFolder, List<IResourceDefinition> resourcesToImport) {
		List<MServerProfile> result = new ArrayList<>();

		// Create the set of the files to import
		HashSet<String> filesToImport = new HashSet<>();
		for (IResourceDefinition resourceToImport : resourcesToImport)
			filesToImport.add(resourceToImport.getData().toString());

		// Read the configuration from the file storage
		File[] storageContent = serversFolder.listFiles();
		for (File storageElement : storageContent) {
			if (!storageElement.isHidden() && filesToImport.contains(storageElement.getName())) {
				try {
					InputStream inputStream = new FileInputStream(storageElement);
					Reader reader = new InputStreamReader(inputStream, "UTF-8"); //$NON-NLS-1$
					InputSource is = new InputSource(reader);
					is.setEncoding("UTF-8"); //$NON-NLS-1$
					Document document = JRXmlUtils.parse(is);
					Node serverNode = document.getDocumentElement();
					if (serverNode.getNodeType() == Node.ELEMENT_NODE) {
						try {
							ServerProfile sprof = (ServerProfile) CastorHelper.read(serverNode,
									MServerProfile.MAPPINGFILE);
							MServerProfile sp = new MServerProfile(null, sprof);
							result.add(sp);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				} catch (Exception e) {
					UIUtils.showError(e);
				}
			}
		}
		return result;
	}

	@Override
	public String getResourceNameExport() {
		int serverNumbers = ServerManager.getServerProfiles().size();
		return "JasperReports Server Connections (" + serverNumbers + ")"; //$NON-NLS-1$
	}

	@Override
	public String getResourceNameImport(File exportedContainer) {
		return "JasperReports Server Connections (" + getRestorableResources(exportedContainer).size() + ")"; //$NON-NLS-1$
	}

	/**
	 * Show a question dialog with the buttons overwrite, skip and keep both, no and
	 * cancel. It is executed in the graphic thread so this method dosen't need to
	 * be called inside a the display thread.
	 *
	 * @param connectionNames
	 *            the list of the overlapping names, this will be used to build the
	 *            message and show which elements are overlapping, must be not null
	 * @return a not null enum that can be Overwrite, Skip or Keep Both
	 */
	private RESPONSE_TYPE askOverwrite(List<String> connectionNames) {
		String baseMessage = Messages.ExportedServersHandler_overlappingMessage;
		StringBuilder message = new StringBuilder("\n");
		int index = 1;
		for (String adapter : connectionNames) {
			message.append(adapter);
			message.append(index == connectionNames.size() ? ".\n" : ",\n"); //$NON-NLS-1$ //$NON-NLS-2$
			index++;
		}
		String composedMessage = MessageFormat.format(baseMessage, new Object[] { message.toString() });
		return RunnableOverwriteQuestion.showQuestion(Messages.ExportedServersHandler_overlappingDescription,
				composedMessage);
	}

	/**
	 * Return an unique name for the imported resource, not already used by the
	 * others
	 * 
	 * @param existingServers
	 *            the existing resources, the search of the name will be test
	 *            against this map, must be not null
	 * @param baseName
	 *            the base name used into the search
	 * @return a not null unique name for the new resource
	 */
	private String getName(HashMap<String, MServerProfile> existingServers, String baseName) {
		int index = 1;
		String newName = baseName + "_" + index; //$NON-NLS-1$
		while (existingServers.containsKey(newName)) {
			index++;
			newName = baseName + "_" + index; //$NON-NLS-1$
		}
		return newName;
	}

	/**
	 * Refresh the server node in the repository view, if it is visible
	 */
	protected void refreshServers() {
		// Get the treeview and the MServers node from the repository view if it is
		// available
		RepositoryView view = getRepositoryView();
		TreeViewer treeViewer = null;
		MServers serversNode = null;
		if (view != null) {
			treeViewer = view.getTreeViewer();
			MRoot root = (MRoot) treeViewer.getInput();
			List<INode> lst = root.getChildren();
			for (INode n : lst) {
				if (n instanceof MServers) {
					serversNode = (MServers) n;
					break;
				}
			}
		}
		if (serversNode != null) {
			ServerManager.loadServerProfiles(serversNode);
			treeViewer.refresh();
		}
	}

	/**
	 * Return the repository view of the application
	 * 
	 * @return the RepositoryView, could be null
	 */
	private RepositoryView getRepositoryView() {
		return (RepositoryView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.findView("com.jaspersoft.studio.Repository"); //$NON-NLS-1$
	}
}
