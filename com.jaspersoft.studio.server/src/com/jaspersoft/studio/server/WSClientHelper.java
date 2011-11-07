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
		WSClientHelper.listFolder(msp, connect(msp, monitor), "/", monitor);
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
			WSClient client, String folderUri, IProgressMonitor monitor)
			throws Exception {
		ResourceDescriptor rd = new ResourceDescriptor();
		rd.setWsType(ResourceDescriptor.TYPE_FOLDER);
		rd.setUriString(folderUri);
		return listFolder(parent, -1, client, monitor, rd);
	}

	private static List<ResourceDescriptor> listFolder(ANode parent, int index,
			WSClient client, IProgressMonitor monitor, ResourceDescriptor rd)
			throws Exception {
		monitor.subTask("Listing " + rd.getUriString());

		List<ResourceDescriptor> children = client.list(rd);

		for (ResourceDescriptor r : children) {
			ANode node = ResourceFactory.getResource(parent, r, index);
			if (r.getWsType().equals(ResourceDescriptor.TYPE_FOLDER)) {
				listFolder(node, client, r.getUriString(), monitor);
			} else if (r.getWsType().equals(ResourceDescriptor.TYPE_REPORTUNIT)) {
				r = client.get(r, null);
				List<ResourceDescriptor> children2 = r.getChildren();
				for (ResourceDescriptor res : children2) {
					if (res.getWsType().equals(ResourceDescriptor.TYPE_FOLDER))
						listFolder(node, client, res.getUriString(), monitor);
					else
						ResourceFactory.getResource(node, res, index);
				}
			}
		}
		return children;
	}

	public static ResourceDescriptor getResource(MResource res,
			ResourceDescriptor rd) throws Exception {
		return getResource(res, rd, null);
	}

	public static ResourceDescriptor getResource(MResource res,
			ResourceDescriptor rd, String file) throws Exception {
		MServerProfile sp = (MServerProfile) res.getRoot();
		File f = null;
		if (file != null)
			f = new File(file);
		return sp.getWsClient().get(rd, f);
	}

	public static void saveResource(MResource res, IProgressMonitor monitor)
			throws Exception {
		INode n = res.getRoot();
		if (n != null && n instanceof MServerProfile) {
			MServerProfile sp = (MServerProfile) n;
			File file = null;
			if (res instanceof AFileResource)
				file = ((AFileResource) res).getFile();
			ResourceDescriptor rd = res.getValue();
			if (rd.getIsNew()) {
				rd.setUriString(rd.getParentFolder() + "/" + rd.getName());
			}

			sp.getWsClient().addOrModifyResource(rd, file);

			refreshResource((MResource) res.getParent(), monitor);
		}

	}

	public static void deleteResource(MResource res) throws Exception {
		ResourceDescriptor rd = res.getValue();
		MServerProfile sp = (MServerProfile) res.getRoot();
		if (!rd.getIsNew()) {
			INode n = res.getReportUnit();
			if (n instanceof MReportUnit)
				sp.getWsClient().delete(rd,
						((MReportUnit) n).getValue().getUriString());
			else
				sp.getWsClient().delete(rd);
		}
		((ANode) res.getParent()).removeChild(res);
	}

	public static void refreshResource(final MResource res,
			IProgressMonitor monitor) throws Exception {
		ResourceDescriptor rd = res.getValue();
		MServerProfile sp = (MServerProfile) res.getRoot();
		ResourceDescriptor newrd = sp.getWsClient().get(rd, null);
		if (newrd != null) {
			res.setValue(newrd);
			res.removeChildren();

			listFolder(res, -1, sp.getWsClient(), monitor, newrd);
		} else {
			connectGetData((MServerProfile) res.getRoot(), monitor);
		}

		Display.getDefault().asyncExec(new Runnable() {

			public void run() {
				ServerManager.getPropertyChangeSupport().firePropertyChange(
						new PropertyChangeEvent(res, "MODEL", null, res));
			}
		});

	}

	public static Map<String, Object> runReportUnit(MReportUnit res)
			throws Exception {
		ResourceDescriptor rd = res.getValue();
		MServerProfile sp = (MServerProfile) res.getRoot();

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("parameter1", "A");

		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(Argument.RUN_OUTPUT_FORMAT,
				Argument.RUN_OUTPUT_FORMAT_PDF));

		return sp.getWsClient().runReport(rd, parameters, args);
	}

	static int depth = 0; // This variable is used to print tabs...

}
