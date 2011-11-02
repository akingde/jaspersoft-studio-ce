package com.jaspersoft.studio.server;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.ireport.jasperserver.ws.JServer;
import com.jaspersoft.ireport.jasperserver.ws.WSClient;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
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
		server.setUsername(sp.getUser());
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
		monitor.subTask("Listing " + rd.getUriString());

		List<ResourceDescriptor> children = client.list(rd);

		for (ResourceDescriptor r : children) {
			ANode node = ResourceFactory.getResource(parent, r);
			if (r.getWsType().equals(ResourceDescriptor.TYPE_FOLDER)) {
				listFolder(node, client, r.getUriString(), monitor);
			} else if (r.getWsType().equals(ResourceDescriptor.TYPE_REPORTUNIT)) {
				r = client.get(r, null);
				List<ResourceDescriptor> children2 = r.getChildren();
				for (ResourceDescriptor res : children2) {
					if (res.getWsType().equals(ResourceDescriptor.TYPE_FOLDER))
						listFolder(node, client, res.getUriString(), monitor);
					else
						ResourceFactory.getResource(node, res);
				}
			}
		}
		return children;
	}

	public static ResourceDescriptor getResource(MResource res,
			ResourceDescriptor rd) throws Exception {
		MServerProfile sp = (MServerProfile) res.getRoot();
		return sp.getWsClient().get(rd, null);
	}

	public static void saveResource(WSClient client, ResourceDescriptor rd)
			throws Exception {
		client.addOrModifyResource(rd, null);
	}

	public static void deleteResource(MResource res) throws Exception {
		ResourceDescriptor rd = res.getValue();
		MServerProfile sp = (MServerProfile) res.getRoot();
		INode n = res.isInsideReportUnit();
		if (n instanceof MReportUnit)
			sp.getWsClient().delete(rd,
					((MReportUnit) n).getValue().getUriString());
		else
			sp.getWsClient().delete(rd);
		((ANode) res.getParent()).removeChild(res);
	}

	static int depth = 0; // This variable is used to print tabs...

}
