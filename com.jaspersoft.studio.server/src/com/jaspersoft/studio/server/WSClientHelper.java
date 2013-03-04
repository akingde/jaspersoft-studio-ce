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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.ireport.jasperserver.ws.FileContent;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.Argument;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.MFolder;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.datasource.filter.IDatasourceFilter;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.protocol.IConnection;
import com.jaspersoft.studio.server.protocol.ProxyConnection;
import com.jaspersoft.studio.server.wizard.resource.page.selector.SelectorDatasource;

public class WSClientHelper {
	private static Map<IConnection, ServerProfile> clients = new HashMap<IConnection, ServerProfile>();

	public static ServerProfile getServerProfile(IConnection cli) {
		return clients.get(cli);
	}

	public static IConnection connect(MServerProfile msp,
			IProgressMonitor monitor) throws Exception {
		if (monitor != null)
			monitor.subTask("Connecting");
		IConnection c = new ProxyConnection();
		boolean cres = c.connect(msp.getValue());
		if (cres) {
			monitor.subTask("Connected");
			msp.setWsClient(c);
			clients.put(c, msp.getValue());
			return c;
		} else
			monitor.subTask("Not Connected");
		return null;
	}

	public static boolean checkConnection(MServerProfile msp,
			IProgressMonitor monitor) throws Exception {
		monitor.subTask("Connecting");
		IConnection c = new ProxyConnection();
		boolean cres = c.connect(msp.getValue());
		if (cres)
			monitor.subTask("Connected");
		else
			monitor.subTask("Not Connected");
		return cres;
	}

	public static void connectGetData(MServerProfile msp,
			IProgressMonitor monitor) throws Exception {
		msp.removeChildren();
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
			IConnection client, String folderUri, IProgressMonitor monitor,
			int depth) throws Exception {
		ResourceDescriptor rd = new ResourceDescriptor();
		rd.setWsType(ResourceDescriptor.TYPE_FOLDER);
		rd.setUriString(folderUri);
		if (depth < 1) {
			parent.removeChildren();
			return listFolder(parent, -1, client, monitor, rd, depth);
		}
		return null;
	}

	private static List<ResourceDescriptor> listFolder(ANode parent, int index,
			IConnection client, IProgressMonitor monitor,
			ResourceDescriptor rd, int depth) throws Exception {
		monitor.subTask("Listing " + rd.getUriString());
		depth++;

		List<ResourceDescriptor> children = client.list(rd);
		if (parent instanceof MResource
				&& ((MResource) parent).getValue().getWsType()
						.equals(ResourceDescriptor.TYPE_FOLDER)) {
			Collections.sort(children, new Comparator<ResourceDescriptor>() {

				@Override
				public int compare(ResourceDescriptor arg0,
						ResourceDescriptor arg1) {
					if (arg0.getLabel() == null)
						return -1;
					if (arg1.getLabel() == null)
						return 1;
					return arg0.getLabel().compareTo(arg1.getLabel());
				}
			});
		}

		Set<String> set = new HashSet<String>();
		for (ResourceDescriptor r : children) {
			if (set.contains(r.getUriString()))
				continue;
			set.add(r.getUriString());
			if (rd.getWsType().equals(ResourceDescriptor.TYPE_REPORTUNIT)) {
				if (SelectorDatasource.isDatasource(r))
					continue;
			}
			MResource node = ResourceFactory.getResource(parent, r, index);
			if (r.getWsType().equals(ResourceDescriptor.TYPE_FOLDER)) {
				listFolder(node, client, r.getUriString(), monitor, depth);
			} else if (r.getWsType().equals(ResourceDescriptor.TYPE_REPORTUNIT)) {
				r = client.get(r, null);
				Set<String> setRU = new HashSet<String>();
				List<ResourceDescriptor> children2 = r.getChildren();
				for (ResourceDescriptor res : children2) {
					if (setRU.contains(res.getUriString()))
						continue;
					setRU.add(res.getUriString());
					if (SelectorDatasource.isDatasource(res))
						continue;
					if (res.getWsType().equals(ResourceDescriptor.TYPE_FOLDER))
						listFolder(node, client, res.getUriString(), monitor,
								depth);
					else
						ResourceFactory.getResource(node, res, index);
					if (monitor.isCanceled())
						return children;
				}
			}
			if (monitor.isCanceled())
				return children;
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

	public static ResourceDescriptor getResource(IConnection cl,
			ResourceDescriptor rd, File f) throws Exception {
		return cl.get(rd, f);
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

	public static ResourceDescriptor saveResource(MResource res,
			IProgressMonitor monitor, boolean refresh) throws Exception {
		INode n = res.getRoot();
		ResourceDescriptor rd = null;
		if (n != null && n instanceof MServerProfile) {
			MServerProfile sp = (MServerProfile) n;
			rd = res.getValue();
			if (rd.getIsNew()) {
				rd.setUriString(rd.getParentFolder() + "/" + rd.getName());
			}
			File file = null;
			if (res instanceof AFileResource)
				file = ((AFileResource) res).getFile();
			rd.setHasData(file != null);

			MReportUnit mru = res.getReportUnit();
			IConnection cli = sp.getWsClient();
			System.out.println("saving: " + rd.getUriString());
			if (mru != null && res != mru) {
				String ruuri = mru.getValue().getUriString();

				if (rd.getWsType()
						.equals(ResourceDescriptor.TYPE_INPUT_CONTROL)
						&& !rd.getIsNew()) {
					rd = cli.addOrModifyResource(rd, file);
				} else {
					rd = cli.modifyReportUnitResource(ruuri, rd, file);
				}
			} else
				rd = cli.addOrModifyResource(rd, file);
			if (refresh && res.getParent() instanceof MResource) {
				// standard resource creation inside an existing MResource
				refreshResource((MResource) res.getParent(), monitor);
			} else if (res.getParent() instanceof MServerProfile) {
				// resource created inside the root folder
				connectGetData((MServerProfile) res.getParent(), monitor);
			}
		}
		return rd;

	}

	public static void deleteResource(MResource res) throws Exception {
		ResourceDescriptor rd = res.getValue();
		MServerProfile sp = (MServerProfile) res.getRoot();
		if (!rd.getIsNew()) {
			MReportUnit n = res.getReportUnit();
			IConnection wsClient = sp.getWsClient();
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
				if (res instanceof MFolder || res instanceof MReportUnit) {
					res.removeChildren();

					listFolder(res, -1, sp.getWsClient(), monitor, newrd, 0);
				}
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

	public static Map<String, FileContent> runReportUnit(MReportUnit res)
			throws Exception {
		ResourceDescriptor rd = res.getValue();
		MServerProfile sp = (MServerProfile) res.getRoot();

		Map<String, Object> parameters = new HashMap<String, Object>();

		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(Argument.RUN_OUTPUT_FORMAT,
				Argument.RUN_OUTPUT_FORMAT_JRPRINT));

		return sp.getWsClient().runReport(rd, parameters, args);
	}

	public static Map<String, FileContent> runReportUnit(String uri,
			Map<String, Object> parameters) throws Exception {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(Argument.RUN_OUTPUT_FORMAT,
				Argument.RUN_OUTPUT_FORMAT_JRPRINT));

		ResourceDescriptor rd = new ResourceDescriptor();
		rd.setUriString(getReportUnitUri(uri));

		return getClient(uri).runReport(rd, parameters, args);
	}

	public static ResourceDescriptor getReportUnit(String uri) throws Exception {
		ResourceDescriptor rd = new ResourceDescriptor();
		rd.setUriString(getReportUnitUri(uri));

		return getClient(uri).get(rd, null);
	}

	public static String getReportUnitUri(String uri) {
		return uri.substring(uri.indexOf(":") + 1);
	}

	public static IConnection getClient(String uri) {
		MServerProfile sp = ServerManager.getServerProfile(uri);
		return sp.getWsClient();
	}

	public static MResource findSelected(List<INode> list,
			IProgressMonitor monitor, String prunit, IConnection cli)
			throws Exception {
		int maxl = 0;
		int pos = -1;
		for (int i = 0; i < list.size(); i++) {
			if (!(list.get(i) instanceof MResource))
				continue;
			MResource mr = (MResource) list.get(i);
			String uri = mr.getValue().getUriString();
			if (prunit.equals(uri))
				return mr;
			if (prunit.startsWith(uri) && prunit.length() >= uri.length()) {
				if (maxl < uri.length()) {
					maxl = uri.length();
					pos = i;
				}
			}
		}
		if (pos >= 0) {
			MResource mr = (MResource) list.get(pos);
			String uri = mr.getValue().getUriString();
			listFolder(mr, cli, uri, monitor, 0);
			return findSelected(mr.getChildren(), monitor, prunit, cli);
		}
		return null;
	}

	public static List<ResourceDescriptor> getDatasourceList(IConnection c,
			IDatasourceFilter f) throws Exception {
		List<ResourceDescriptor> list = c.listDatasources();
		if (f != null) {
			List<ResourceDescriptor> toremove = new ArrayList<ResourceDescriptor>();
			for (ResourceDescriptor rd : list)
				if (!f.isDatasource(rd))
					toremove.add(rd);
			list.removeAll(toremove);
		}
		return list;
	}

	public static MServerProfile getDatasourceListTree(MServerProfile sp,
			IDatasourceFilter f) throws Exception {
		List<ResourceDescriptor> list = getDatasourceList(sp.getWsClient(), f);
		sp.removeChildren();
		for (ResourceDescriptor r : list)
			addDataSource(sp, r);
		return sp;
	}

	private static void addDataSource(MServerProfile sp, ResourceDescriptor r) {
		String url = r.getUriString();
		StringTokenizer st = new StringTokenizer(url, "/");
		String turl = "/";
		ANode parent = sp;
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			String sf = turl + token;
			if (sf.equals(url)) {
				ResourceFactory.getResource(parent, r, -1);
				break;
			}
			MResource child = null;
			for (INode node : parent.getChildren()) {
				if (node instanceof MResource) {
					MResource mr = (MResource) node;
					if (mr.getValue().getUriString().equals(sf)) {
						child = mr;
						break;
					}
				}
			}
			if (child == null) {
				ResourceDescriptor rd = new ResourceDescriptor();
				rd.setName(token);
				rd.setLabel(token);
				rd.setUriString(sf);
				rd.setWsType(ResourceDescriptor.TYPE_FOLDER);
				child = ResourceFactory.getResource(parent, rd, -1);
				child.removeChildren();
			}
			parent = child;
			turl = sf + "/";
		}

	}
}
