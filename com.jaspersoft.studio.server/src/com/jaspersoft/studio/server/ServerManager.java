/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.jaspersoft.studio.ConfigurationManager;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MDummy;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.util.ModelVisitor;
import com.jaspersoft.studio.server.editor.JRSEditorContributor;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.model.server.MServers;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.protocol.IConnection;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.CastorHelper;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRXmlUtils;

public class ServerManager {

	public static final String PREF_TAG = "serverprofiles"; //$NON-NLS-1$

	public static final String SERVERPROFILE = "SERVERPROFILE"; //$NON-NLS-1$

	private static PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			JaspersoftStudioPlugin.getInstance());

	private static HashMap<MServerProfile, String> serverProfiles;

	public static List<MServerProfile> getServerProfiles() {
		if (serverProfiles == null) {
			serverProfiles = new HashMap<MServerProfile, String>();
			loadServerProfiles(new MServers(null));
		}
		return new ArrayList<MServerProfile>(serverProfiles.keySet());
	}

	/**
	 * Save an element on the server file storage.
	 * 
	 * @param serverProfile
	 *            the element to save
	 */
	private static void saveIntoStrage(MServerProfile serverProfile, String fileName) {
		FileOutputStream stream = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(serverProfile.toXML())));
			// Write the parsed document to an xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			File storage = ConfigurationManager.getStorage(PREF_TAG);
			File destination = new File(storage, fileName);
			stream = new FileOutputStream(destination);
			StreamResult result = new StreamResult(stream);
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			FileUtils.closeStream(stream);
		}
	}

	public static List<ServerProfile> getServerList() {
		getServerProfiles();
		List<ServerProfile> servers = new ArrayList<ServerProfile>();
		for (MServerProfile ms : serverProfiles.keySet())
			servers.add(ms.getValue());
		return servers;
	}

	public static String[] getServers() {
		List<ServerProfile> serverList = getServerList();
		String[] res = new String[serverList.size()];
		for (int i = 0; i < res.length; i++)
			res[i] = serverList.get(i).getName();

		return res;
	}

	public static PropertyChangeSupport getPropertyChangeSupport() {
		return propertyChangeSupport;
	}

	public static boolean isUniqueName(MServerProfile sprofile, String name) {
		if (sprofile.getParent() != null && sprofile.getValue().getName().equals(name))
			return true;
		for (MServerProfile sp : getServerProfiles()) {
			if (sp.getValue().getName().equals(name))
				return false;
		}
		return true;
	}

	public static void addServerProfile(MServerProfile adapter) {
		if (!serverProfiles.containsKey(adapter)) {
			ServerNameProvider nameProvider = new ServerNameProvider();
			String resourceName = nameProvider.getFileName(null);
			serverProfiles.put(adapter, resourceName);
			saveIntoStrage(adapter, resourceName);
			propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(adapter, SERVERPROFILE, null, adapter));
		}
	}

	public static void removeServerProfile(MServerProfile adapter) {
		if (serverProfiles.containsKey(adapter)) {
			String fileName = serverProfiles.remove(adapter);
			ConfigurationManager.removeStoregeResource(PREF_TAG, fileName);
			((ANode) adapter.getParent()).removeChild(adapter);
			propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(adapter, SERVERPROFILE, null, adapter));
		}
	}

	public static void saveServerProfile(final MServerProfile adapter) {
		if (serverProfiles.containsKey(adapter)) {
			// It's an edit, remove the old configuration file and save the new
			// one
			String path = serverProfiles.get(adapter);
			ConfigurationManager.removeStoregeResource(PREF_TAG, path);
			saveIntoStrage(adapter, path);
			UIUtils.getDisplay().syncExec(new Runnable() {
				public void run() {
					propertyChangeSupport
							.firePropertyChange(new PropertyChangeEvent(adapter, SERVERPROFILE, null, adapter));
				}
			});
		}
	}

	public static void loadServerProfilesCopy(MServers root) {
		if (serverProfiles.isEmpty())
			loadServerProfiles(root);
		for (MServerProfile msp : serverProfiles.keySet()) {
			MServerProfile newServerProfile = new MServerProfile(root, msp.getValue());
			newServerProfile.setWsClient(msp.getWsClient());
			new MDummy(newServerProfile);
		}
	}

	public static void loadServerProfiles(MServers root) {
		root.removeChildren();
		if (serverProfiles == null)
			serverProfiles = new HashMap<MServerProfile, String>();
		serverProfiles.clear();

		// Convert the old configuration
		ConfigurationManager.convertPropertyToStorage(PREF_TAG, PREF_TAG, new ServerNameProvider());

		// Read the configuration from the file storage
		File[] storageContent = ConfigurationManager.getStorageContent(PREF_TAG);
		for (File storageElement : storageContent) {
			try {
				InputStream inputStream = new FileInputStream(storageElement);
				Reader reader = new InputStreamReader(inputStream, "UTF-8");
				InputSource is = new InputSource(reader);
				is.setEncoding("UTF-8");
				Document document = JRXmlUtils.parse(is);
				Node serverNode = document.getDocumentElement();
				if (serverNode.getNodeType() == Node.ELEMENT_NODE) {
					try {
						ServerProfile sprof = (ServerProfile) CastorHelper.read(serverNode, MServerProfile.MAPPINGFILE);
						MServerProfile sp = new MServerProfile(root, sprof);
						new MDummy(sp);
						serverProfiles.put(sp, storageElement.getName());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			} catch (Exception e) {
				UIUtils.showError(e);
			}
		}
	}

	public static MServerProfile getServerProfile(String key) {
		int ind = key.indexOf(":"); //$NON-NLS-1$
		if (ind > 0) {
			String[] tokens = key.split(":");

			// StringTokenizer st = new StringTokenizer(key, ":");
			String name = new String(Base64.decodeBase64(tokens[0]));
			// String path = tokens[1];
			if (tokens.length > 2) {
				String urls = new String(Base64.decodeBase64(tokens[2]));
				String[] urlt = urls.split("\n");
				String url = urlt[0];
				String user = null;
				if (urlt.length > 1)
					user = urlt[1];
				if (urlt.length >= 2)
					user = urlt[1];
				String organization = null;
				if (urlt.length >= 3)
					organization = urlt[2];
				for (MServerProfile sp : serverProfiles.keySet()) {
					ServerProfile serv = sp.getValue();
					try {
						if (serv.getName().equals(name) && url != null && serv.getUrl().equals(url)) {
							if (user == null)
								return sp;
							if (serv.getUser().equals(user) && (organization == null
									|| (serv.getOrganisation() != null && serv.getOrganisation().equals(organization))))
								return sp;
						}
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	public static IConnection getServer(String url, IProgressMonitor monitor) throws Exception {
		for (MServerProfile sp : getServerProfiles()) {
			if (sp.getValue().getUrl().equals(url))
				return sp.getWsClient(monitor);
		}
		return null;
	}

	public static IConnection getServer(String url, String user, IProgressMonitor monitor) throws Exception {
		MServerProfile msp = getServerByUrl(url, user);
		if (msp != null)
			return msp.getWsClient(monitor);
		return null;
	}

	public static MServerProfile getServerByUrl(String url) {
		for (MServerProfile sp : getServerProfiles()) {
			try {
				if (sp.getValue().getUrl().equals(url))
					return sp;
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static MServerProfile getServerByUrl(String url, String user) {
		MServerProfile res = null;
		for (MServerProfile sp : getServerProfiles()) {
			ServerProfile v = sp.getValue();
			try {
				if (v.getUrl().equals(url)) {
					res = sp;
					if (user != null) {
						if (v.isUseSSO()) {
							if (user.equals(v.getSsoUuid()))
								return sp;
						} else {
							String u = v.getUser()
									+ (!Misc.isNullOrEmpty(v.getOrganisation()) ? "|" + v.getOrganisation() : "");
							if (u.equals(user))
								return sp;
						}
					} else
						return sp;
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	public static int getServerIndexByUrl(String url) {
		int i = 0;
		for (MServerProfile sp : getServerProfiles()) {
			try {
				if (sp.getValue().getUrl().equals(url))
					return i;
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			i++;
		}
		return -1;
	}

	public static int getServerIndexByUrl(String url, String user) {
		int i = 0;
		int j = -1;
		for (MServerProfile sp : getServerProfiles()) {
			ServerProfile v = sp.getValue();
			try {
				if (v.getUrl().equals(url)) {
					j = i;
					if (user != null) {
						String u = v.getUser()
								+ (!Misc.isNullOrEmpty(v.getOrganisation()) ? "|" + v.getOrganisation() : "");
						if (u.equals(user))
							return j;
					} else
						return j;
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			i++;
		}
		return j;
	}

	public static String getKey(AMResource res) {
		return getKey(res, res.getValue().getUriString(), null);
	}

	public static String getKey(AMResource res, String uri, String option) {
		INode n = res.getRoot();
		if (n != null && n instanceof MServerProfile) {
			MServerProfile sp = (MServerProfile) n;
			ServerProfile serv = sp.getValue();
			String srvurl;
			try {
				srvurl = serv.getUrl();
				srvurl += "\n" + serv.getUser();
				if (!Misc.isNullOrEmpty(serv.getOrganisation()))
					srvurl += "\n" + serv.getOrganisation();
				else
					srvurl += "\n";
				if (!Misc.isNullOrEmpty(option))
					srvurl += "\n" + option;
				return Base64.encodeBase64String(serv.getName().getBytes()) + ":" + uri + ":" //$NON-NLS-1$ //$NON-NLS-2$
						+ Base64.encodeBase64String(srvurl.getBytes());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

		}
		return null;
	}

	public static String getVersion(ANode node) {
		INode n = node.getRoot();
		if (n != null && n instanceof MServerProfile) {
			MServerProfile server = (MServerProfile) n;
			ServerProfile srvrd = server.getValue();
			return srvrd.getJrVersion();
		}
		return JRXmlWriterHelper.LAST_VERSION;
	}

	/**
	 * Tries to create a copy of the specified {@link MServerProfile} instance.
	 * <p>
	 * Re-use the {@link ServerProfile} information of the original node. Can be
	 * used for example as input when creating a new treeviewer for repository
	 * exploring.
	 * 
	 * @param original
	 *            the {@link MServerProfile} instance to copy
	 * @return a copy of the original {@link MServerProfile} instance
	 */
	public static MServerProfile getMServerProfileCopy(MServerProfile original) {
		ServerProfile spFound = null;
		for (ServerProfile sp : getServerList()) {
			if (sp.equals(original.getValue())) {
				spFound = sp;
				break;
			}
		}
		if (spFound == null)
			return null;
		MServerProfile newServerProfile = new MServerProfile(new MRoot(null, null), spFound);
		newServerProfile.setWsClient(original.getWsClient());
		return newServerProfile;
	}

	public static MServerProfile getServerProfile(JasperDesign jd, JasperReportsConfiguration jConfig,
			IProgressMonitor monitor) {
		final MRoot root = new MRoot(null, null);
		root.setJasperConfiguration(jConfig);
		MServerProfile sp = null;
		getServerProfiles();
		for (MServerProfile ms : serverProfiles.keySet()) {
			sp = new MServerProfile(root, ms.getValue());
			sp.setWsClient(ms.getWsClient());
			sp.setJasperConfiguration(jConfig);
			new MDummy(sp);
		}

		String[] prop = JRSEditorContributor.getServerURL(jd, (IFile) jConfig.get(FileUtils.KEY_FILE), monitor);
		if (prop != null && !Misc.isNullOrEmpty(prop[0])) {
			for (INode n : root.getChildren()) {
				if (n instanceof MServerProfile) {
					MServerProfile msp = (MServerProfile) n;
					ServerProfile serv = msp.getValue();
					try {
						if (serv.getUrl().equals(prop[0])) {
							sp = msp;
							if (Misc.isNullOrEmpty(prop[1]))
								break;
							String usr = serv.getUser();
							if (!Misc.isNullOrEmpty(serv.getOrganisation()))
								usr += "|" + serv.getOrganisation();
							if (usr.equals(prop[1]))
								break;
						}
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return sp;
	}

	public static List<MServerProfile> getServerProfiles(JasperDesign jd, JasperReportsConfiguration jConfig,
			IProgressMonitor monitor) {
		final MRoot root = new MRoot(null, null);
		root.setJasperConfiguration(jConfig);
		List<MServerProfile> profiles = new ArrayList<MServerProfile>();

		String[] prop = JRSEditorContributor.getServerURL(jd, (IFile) jConfig.get(FileUtils.KEY_FILE), monitor);
		if (prop != null && !Misc.isNullOrEmpty(prop[0])) {
			for (ServerProfile serv : getServerList()) {
				try {
					if (serv.getUrl().equals(prop[0])) {
						if (Misc.isNullOrEmpty(prop[1])) {
							profiles.add(createServerProfile(root, serv, jConfig));
							continue;
						}
						String usr = serv.getUser();
						if (!Misc.isNullOrEmpty(serv.getOrganisation()))
							usr += "|" + serv.getOrganisation();
						if (usr.equals(prop[1])) {
							profiles.add(createServerProfile(root, serv, jConfig));
							continue;
						}
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			}
		}
		return profiles;
	}

	private static MServerProfile createServerProfile(MRoot root, ServerProfile s, JasperReportsConfiguration jConfig) {
		MServerProfile sp = new MServerProfile(root, s);
		sp.setJasperConfiguration(jConfig);
		new MDummy(sp);
		return sp;
	}

	public static void selectIfExists(final IProgressMonitor monitor, AMResource mres) {
		MServerProfile sp = (MServerProfile) mres.getRoot();
		try {
			sp = getServerByUrl(sp.getValue().getUrl());
			selectIfExists(monitor, sp, mres);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public static void selectIfExists(final IProgressMonitor monitor, MServerProfile sp, AMResource mres) {
		if (mres.getParent() instanceof MServerProfile) {
			try {
				WSClientHelper.connectGetData(sp, monitor);
				propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(sp, SERVERPROFILE, null, sp));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			final String puri = ((AMResource) mres.getParent()).getValue().getUriString();
			final String uri = mres.getValue().getUriString();
			if (ModelUtils.isEmpty(sp))
				try {
					WSClientHelper.connectGetData(sp, monitor);
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			new ModelVisitor<AMResource>(sp) {

				@Override
				public boolean visit(INode n) {
					if (n instanceof AMResource) {
						AMResource r = (AMResource) n;
						if (r.getValue().getUriString().equals(puri)) {
							for (INode cn : r.getChildren())
								if (cn instanceof AMResource && ((AMResource) cn).getValue().getUriString().equals(uri))
									doRefresh((AMResource) cn, monitor);
							doRefresh(r, monitor);
						}
					}
					if (monitor.isCanceled())
						stop();
					return true;
				}

				private void doRefresh(AMResource r, IProgressMonitor monitor) {
					try {
						WSClientHelper.refreshResource(r, monitor);
					} catch (Exception e) {
						e.printStackTrace();
					}
					stop();
				}

			};
		}
	}
}
