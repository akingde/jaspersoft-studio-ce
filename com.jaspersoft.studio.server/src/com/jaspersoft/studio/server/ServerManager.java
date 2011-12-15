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
package com.jaspersoft.studio.server;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.data.XmlUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.util.JRXmlUtils;

import org.osgi.service.prefs.Preferences;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.model.server.MServers;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.utils.UIUtils;

public class ServerManager {
	private static final String PREF_TAG = "serverprofiles";
	private static final String SERVERPROFILE = "SERVERPROFILE";
	private static List<MServerProfile> serverProfiles = new ArrayList<MServerProfile>();
	private static PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			JaspersoftStudioPlugin.getInstance());

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

			propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(
					adapter, SERVERPROFILE, null, adapter));
			saveServerProfiles();
		}
	}

	public static void removeServerProfile(MServerProfile adapter) {
		if (serverProfiles.contains(adapter)) {
			serverProfiles.remove(adapter);
			((ANode) adapter.getParent()).removeChild(adapter);
			propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(
					adapter, SERVERPROFILE, null, adapter));
			saveServerProfiles();
		}
	}

	public static void saveServerProfile(MServerProfile adapter) {
		if (serverProfiles.contains(adapter)) {
			propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(
					adapter, SERVERPROFILE, null, adapter));
			saveServerProfiles();
		}
	}

	public static void saveServerProfiles() {
		Preferences prefs = PropertiesHelper.INSTANCE_SCOPE
				.getNode(JaspersoftStudioPlugin.getUniqueIdentifier());

		try {
			StringBuffer xml = new StringBuffer();
			xml.append("<serverprofiles>\n");

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

		Preferences prefs = PropertiesHelper.INSTANCE_SCOPE
				.getNode(JaspersoftStudioPlugin.getUniqueIdentifier());

		String xml = prefs.get(PREF_TAG, null); //$NON-NLS-1$

		if (xml != null) {
			try {

				Document document = JRXmlUtils.parse(new InputSource(
						new StringReader(xml)));

				NodeList adapterNodes = document.getDocumentElement()
						.getChildNodes();// .getElementsByTagName("dataAdapter");

				for (int i = 0; i < adapterNodes.getLength(); ++i) {
					Node adapterNode = adapterNodes.item(i);

					if (adapterNode.getNodeType() == Node.ELEMENT_NODE) {
						ServerProfile sprof = (ServerProfile) XmlUtil.read(
								adapterNode, MServerProfile.MAPPINGFILE);

						serverProfiles.add(new MServerProfile(root, sprof));
					}
				}

			} catch (JRException e) {
				UIUtils.showError(e);
			}
		}
	}

	public static MServerProfile getServerProfile(String key) {
		int ind = key.indexOf(":");
		if (ind > 0) {
			String name = key.substring(0, ind);
			for (MServerProfile sp : serverProfiles) {
				if (sp.getValue().getName().equals(name))
					return sp;
			}
		}
		return null;
	}

	public static String getKey(MResource res) {
		INode n = res.getRoot();
		if (n != null && n instanceof MServerProfile) {
			MServerProfile sp = (MServerProfile) res.getRoot();
			return sp.getValue().getName() + ":"
					+ res.getValue().getUriString();
		}
		return null;
	}
}
