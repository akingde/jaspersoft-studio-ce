/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.ireport.jasperserver.ws.JServer;
import com.jaspersoft.ireport.jasperserver.ws.WSClient;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.Argument;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.wizard.resource.page.SelectorDatasource;

public class WSClientHelper {

	public static WSClient connect(MServerProfile msp, IProgressMonitor monitor)
			throws Exception {
		monitor.subTask("Connecting");
		JServer server = msp.getWsClient() == null ? new JServer() : msp
				.getWsClient().getServer();
		ServerProfile sp = msp.getValue();
		server.setName(sp.getName());
		server.setUrl(sp.getUrl());
		String username = sp.getUser();
		if (sp.getOrganisation() != null)
			username += "|" + sp.getOrganisation();
		server.setUsername(username);
		server.setPassword(sp.getPass());
		if (msp.getWsClient() == null)
			msp.setWsClient(new WSClient(server));
		return msp.getWsClient();
	}

	public static boolean checkConnection(MServerProfile msp,
			IProgressMonitor monitor) throws Exception {
		ResourceDescriptor rd = new ResourceDescriptor();
		rd.setWsType(ResourceDescriptor.TYPE_FOLDER);
		rd.setUriString("/");
		connect(msp, monitor).list(rd);
		monitor.subTask("Connected");
		return true;
	}

	public static void connectGetData(MServerProfile msp,
			IProgressMonitor monitor) throws Exception {
		WSClientHelper.listFolder(msp, connect(msp, monitor), "/", monitor, 0);
	}

	/**
	 * This function shows how to create a folder in the root directory.
	 * Subfolders can be created just specifying a proper Uri string i.e.
	 * rd.setUriString("/this/is/my/new/folder");
	 * 
	 * @param client
	 * @param folderLabel
	 * @param folderName
	 * @throws IOException
	 */
	public static List<ResourceDescriptor> listFolder(ANode parent,
			WSClient client, String folderUri, IProgressMonitor monitor,
			int depth) throws Exception {
		ResourceDescriptor rd = new ResourceDescriptor();
		rd.setWsType(ResourceDescriptor.TYPE_FOLDER);
		rd.setUriString(folderUri);
		if (depth < 2)
			return listFolder(parent, -1, client, monitor, rd, depth);
		return null;
	}

	private static List<ResourceDescriptor> listFolder(ANode parent, int index,
			WSClient client, IProgressMonitor monitor, ResourceDescriptor rd,
			int depth) throws Exception {
		monitor.subTask("Listing " + rd.getUriString());
		depth++;

		List<ResourceDescriptor> children = client.list(rd);

		for (ResourceDescriptor r : children) {
			if (rd.getWsType().equals(ResourceDescriptor.TYPE_REPORTUNIT)) {
				if (SelectorDatasource.isDatasource(r))
					continue;
			}
			ANode node = ResourceFactory.getResource(parent, r, index);
			if (r.getWsType().equals(ResourceDescriptor.TYPE_FOLDER)) {
				listFolder(node, client, r.getUriString(), monitor, depth);
			} else if (r.getWsType().equals(ResourceDescriptor.TYPE_REPORTUNIT)) {
				r = client.get(r, null);
				List<ResourceDescriptor> children2 = r.getChildren();
				for (ResourceDescriptor res : children2) {
					if (SelectorDatasource.isDatasource(res))
						continue;
					if (res.getWsType().equals(ResourceDescriptor.TYPE_FOLDER))
						listFolder(node, client, res.getUriString(), monitor,
								depth);
					else
						ResourceFactory.getResource(node, res, index);
				}
			}
		}
		return children;
	}

	public static ResourceDescriptor getResource(ANode res,
			ResourceDescriptor rd) throws Exception {
		return getResource(res, rd, (String) null);
	}

	public static ResourceDescriptor getResource(ANode res,
			ResourceDescriptor rd, String file) throws Exception {
		File f = null;
		if (file != null)
			f = new File(file);
		return getResource(res, rd, f);
	}

	public static ResourceDescriptor getResource(ANode res,
			ResourceDescriptor rd, File f) throws Exception {
		MServerProfile sp = (MServerProfile) res.getRoot();
		return sp.getWsClient().get(rd, f);
	}

	public static ResourceDescriptor getReference(ANode root,
			ResourceDescriptor rd) throws Exception {
		MServerProfile sp = (MServerProfile) root.getRoot();
		if (rd.getReferenceUri() != null) {
			String ref = rd.getReferenceUri();
			int ldel = ref.lastIndexOf("/");
			String pfolder = ref.substring(0, ldel - 1);
			String file = ref.substring(ldel + 1, ref.length());

			ResourceDescriptor r = new ResourceDescriptor();
			r.setParentFolder(pfolder);
			r.setName(file);
			r.setUriString(rd.getReferenceUri());
			return sp.getWsClient().get(r, null);
		}
		return null;
	}

	public static void saveResource(MResource res, IProgressMonitor monitor)
			throws Exception {
		saveResource(res, monitor, true);
	}

	public static void saveResource(MResource res, IProgressMonitor monitor,
			boolean refresh) throws Exception {
		INode n = res.getRoot();
		if (n != null && n instanceof MServerProfile) {
			MServerProfile sp = (MServerProfile) n;
			ResourceDescriptor rd = res.getValue();
			if (rd.getIsNew()) {
				rd.setUriString(rd.getParentFolder() + "/" + rd.getName());
			}
			File file = null;
			if (res instanceof AFileResource)
				file = ((AFileResource) res).getFile();
			rd.setHasData(file != null);

			MReportUnit mru = res.getReportUnit();
			if (mru != null && res != mru) {
				String ruuri = ((ResourceDescriptor) mru.getValue())
						.getUriString();
				rd.setParentFolder(ruuri + "_files/" + rd.getName());

				sp.getWsClient().modifyReportUnitResource(ruuri, rd, file);
			} else
				sp.getWsClient().addOrModifyResource(rd, file);
			if (refresh)
				refreshResource((MResource) res.getParent(), monitor);
		}

	}

	public static void deleteResource(MResource res) throws Exception {
		ResourceDescriptor rd = res.getValue();
		MServerProfile sp = (MServerProfile) res.getRoot();
		if (!rd.getIsNew()) {
			MReportUnit n = res.getReportUnit();
			WSClient wsClient = sp.getWsClient();
			if (n != null && !(res instanceof MReportUnit))
				wsClient.delete(rd, ((MReportUnit) n).getValue().getUriString());
			else
				wsClient.delete(rd);
		}
		((ANode) res.getParent()).removeChild(res);
	}

	public static void refreshResource(final MResource res,
			IProgressMonitor monitor) throws Exception {
		ResourceDescriptor rd = res.getValue();
		INode n = res.getRoot();
		if (n != null && n instanceof MServerProfile) {
			MServerProfile sp = (MServerProfile) res.getRoot();
			ResourceDescriptor newrd = sp.getWsClient().get(rd, null);
			if (newrd != null) {
				res.setValue(newrd);
				res.removeChildren();

				listFolder(res, -1, sp.getWsClient(), monitor, newrd, 0);
			} else {
				connectGetData((MServerProfile) res.getRoot(), monitor);
			}

			Display.getDefault().syncExec(new Runnable() {

				public void run() {
					ServerManager.getPropertyChangeSupport()
							.firePropertyChange(
									new PropertyChangeEvent(res, "MODEL", null,
											res));
				}
			});
		} else {
			// posible problem?
		}

	}

	public static Map<String, Object> runReportUnit(MReportUnit res)
			throws Exception {
		ResourceDescriptor rd = res.getValue();
		MServerProfile sp = (MServerProfile) res.getRoot();

		Map<String, Object> parameters = new HashMap<String, Object>();

		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(Argument.RUN_OUTPUT_FORMAT,
				Argument.RUN_OUTPUT_FORMAT_JRPRINT));

		return sp.getWsClient().runReport(rd, parameters, args);
	}

	public static Map<String, Object> runReportUnit(String uri,
			Map<String, Object> parameters) throws Exception {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(Argument.RUN_OUTPUT_FORMAT,
				Argument.RUN_OUTPUT_FORMAT_JRPRINT));

		ResourceDescriptor rd = new ResourceDescriptor();
		rd.setUriString(uri.substring(uri.indexOf(":") + 1));

		return getClient(uri).runReport(rd, parameters, args);
	}

	public static ResourceDescriptor getReportUnit(String uri) throws Exception {
		ResourceDescriptor rd = new ResourceDescriptor();
		rd.setUriString(uri.substring(uri.indexOf(":") + 1));

		return getClient(uri).get(rd, null);
	}

	public static WSClient getClient(String uri) {
		MServerProfile sp = ServerManager.getServerProfile(uri);
		return sp.getWsClient();
	}

}
