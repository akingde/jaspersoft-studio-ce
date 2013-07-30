/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.server;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRXmlUtils;
import net.sf.jasperreports.util.CastorUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.osgi.service.prefs.Preferences;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MDummy;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.util.ModelVisitor;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;
import com.jaspersoft.studio.server.editor.JRSEditorContributor;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.model.server.MServers;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.protocol.IConnection;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ServerManager {
	private static final String PREF_TAG = "serverprofiles"; //$NON-NLS-1$
	private static final String SERVERPROFILE = "SERVERPROFILE"; //$NON-NLS-1$
	private static List<MServerProfile> serverProfiles = new ArrayList<MServerProfile>();
	private static PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(JaspersoftStudioPlugin.getInstance());

	public static List<ServerProfile> getServerList() {
		if (serverProfiles.isEmpty())
			loadServerProfiles(new MServers(null));
		List<ServerProfile> servers = new ArrayList<ServerProfile>();
		for (MServerProfile ms : serverProfiles)
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
		if (sprofile.getValue().getName().equals(name))
			return true;
		for (MServerProfile sp : serverProfiles) {
			if (sp.getValue().getName().equals(name))
				return false;
		}
		return true;
	}

	public static void addServerProfile(MServerProfile adapter) {
		if (!serverProfiles.contains(adapter)) {
			serverProfiles.add(adapter);

			propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(adapter, SERVERPROFILE, null, adapter));
			saveServerProfiles();
		}
	}

	public static void removeServerProfile(MServerProfile adapter) {
		if (serverProfiles.contains(adapter)) {
			serverProfiles.remove(adapter);
			((ANode) adapter.getParent()).removeChild(adapter);
			propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(adapter, SERVERPROFILE, null, adapter));
			saveServerProfiles();
		}
	}

	public static void saveServerProfile(MServerProfile adapter) {
		if (serverProfiles.contains(adapter)) {
			propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(adapter, SERVERPROFILE, null, adapter));
			saveServerProfiles();
		}
	}

	public static void saveServerProfiles() {
		Preferences prefs = PropertiesHelper.INSTANCE_SCOPE.getNode(JaspersoftStudioPlugin.getUniqueIdentifier());

		try {
			StringBuffer xml = new StringBuffer();
			xml.append("<serverprofiles>\n"); //$NON-NLS-1$

			for (MServerProfile desc : serverProfiles) {
				xml.append(desc.toXML());
			}

			xml.append("</serverprofiles>"); //$NON-NLS-1$

			prefs.put(PREF_TAG, xml.toString()); //$NON-NLS-1$ 
			prefs.flush();

		} catch (Exception e) {
			UIUtils.showError(e);
		}
	}

	public static void loadServerProfiles(MServers root) {
		root.removeChildren();
		serverProfiles.clear();

		Preferences prefs = PropertiesHelper.INSTANCE_SCOPE.getNode(JaspersoftStudioPlugin.getUniqueIdentifier());

		String xml = prefs.get(PREF_TAG, null); //$NON-NLS-1$

		if (xml != null) {
			try {
				Document document = JRXmlUtils.parse(new InputSource(new StringReader(xml)));

				NodeList adapterNodes = document.getDocumentElement().getChildNodes();// .getElementsByTagName("dataAdapter");

				for (int i = 0; i < adapterNodes.getLength(); ++i) {
					Node adapterNode = adapterNodes.item(i);

					if (adapterNode.getNodeType() == Node.ELEMENT_NODE) {
						try {
							ServerProfile sprof = (ServerProfile) CastorUtil.read(adapterNode, MServerProfile.MAPPINGFILE);

							MServerProfile sp = new MServerProfile(root, sprof);
							new MDummy(sp);
							serverProfiles.add(sp);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}

			} catch (JRException e) {
				UIUtils.showError(e);
			}
		}
	}

	public static MServerProfile getServerProfile(String key) {
		int ind = key.indexOf(":"); //$NON-NLS-1$
		if (ind > 0) {
			String name = key.substring(0, ind);
			for (MServerProfile sp : serverProfiles) {
				if (sp.getValue().getName().equals(name))
					return sp;
			}
		}
		return null;
	}

	public static IConnection getServer(String url, IProgressMonitor monitor) throws Exception {
		for (MServerProfile sp : serverProfiles) {
			if (sp.getValue().getUrl().equals(url)) {
				IConnection wsClient = sp.getWsClient();
				if (wsClient == null)
					wsClient = WSClientHelper.connect(sp, monitor);
				return wsClient;
			}
		}
		return null;
	}

	public static MServerProfile getServerByUrl(String url) {
		for (MServerProfile sp : serverProfiles) {
			if (sp.getValue().getUrl().equals(url))
				return sp;
		}
		return null;
	}

	public static int getServerIndexByUrl(String url) {
		int i = 0;
		for (MServerProfile sp : serverProfiles) {
			if (sp.getValue().getUrl().equals(url))
				return i;
			i++;
		}
		return -1;
	}

	public static String getKey(MResource res) {
		INode n = res.getRoot();
		if (n != null && n instanceof MServerProfile) {
			MServerProfile sp = (MServerProfile) n;
			return sp.getValue().getName() + ":" //$NON-NLS-1$
					+ res.getValue().getUriString();
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
	 *          the {@link MServerProfile} instance to copy
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
		try {
			WSClientHelper.connectGetData(newServerProfile, new NullProgressMonitor());
		} catch (Exception e) {
			UIUtils.showError(Messages.ServerManager_ErrorMessage1, e);
		}
		return newServerProfile;
	}

	public static MServerProfile getServerProfile(JasperDesign jd, JasperReportsConfiguration jConfig) {
		final MRoot root = new MRoot(null, null);
		MServerProfile sp = null;
		List<ServerProfile> servers = getServerList();
		for (ServerProfile s : servers) {
			sp = new MServerProfile(root, s);
			new MDummy(sp);
		}

		String prop = JRSEditorContributor.getServerURL(jd, (IFile) jConfig.get(FileUtils.KEY_FILE));
		if (prop != null && !prop.isEmpty()) {
			for (INode n : root.getChildren()) {
				if (n instanceof MServerProfile && ((MServerProfile) n).getValue().getUrl().equals(prop)) {
					return (MServerProfile) n;
				}
			}
		}
		return sp;
	}

	public static void selectIfExists(final IProgressMonitor monitor, MResource mres) {
		MServerProfile sp = (MServerProfile) mres.getRoot();
		sp = getServerByUrl(sp.getValue().getUrl());
		if (mres.getParent() instanceof MServerProfile) {
			try {
				WSClientHelper.connectGetData(sp, monitor);
				propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(sp, SERVERPROFILE, null, sp));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			final String puri = ((MResource) mres.getParent()).getValue().getUriString();
			final String uri = mres.getValue().getUriString();
			new ModelVisitor<MResource>(sp) {

				@Override
				public boolean visit(INode n) {
					if (n instanceof MResource) {
						MResource r = (MResource) n;
						if (r.getValue().getUriString().equals(puri)) {
							for (INode cn : r.getChildren())
								if (((MResource) cn).getValue().getUriString().equals(uri)) {
									doRefresh((MResource) cn, monitor);
								}
							doRefresh(r, monitor);
						}
					}
					if (monitor.isCanceled())
						stop();
					return true;
				}

				private void doRefresh(MResource r, IProgressMonitor monitor) {
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
