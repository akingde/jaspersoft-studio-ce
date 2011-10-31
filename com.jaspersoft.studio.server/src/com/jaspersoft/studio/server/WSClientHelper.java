package com.jaspersoft.studio.server;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.ireport.jasperserver.ws.JServer;
import com.jaspersoft.ireport.jasperserver.ws.WSClient;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
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

			for (int i = 0; i < WSClientHelper.depth; ++i)
				System.out.print("  ");
			if (r.getWsType().equals(ResourceDescriptor.TYPE_FOLDER)) {
				System.out.println("[" + r.getLabel() + "]");
				WSClientHelper.depth++;
				listFolder(node, client, r.getUriString(), monitor);
				WSClientHelper.depth--;
			} else if (r.getWsType().equals(ResourceDescriptor.TYPE_REPORTUNIT)) {
				r = client.get(r, null);
				List<ResourceDescriptor> children2 = r.getChildren();
				for (ResourceDescriptor res : children2) {
					if (res.getWsType().equals(ResourceDescriptor.TYPE_FOLDER))
						listFolder(node, client, res.getUriString(), monitor);
					else
						ResourceFactory.getResource(node, res);
				}
			} else {
				System.out.println("" + r.getLabel() + "");
			}
		}
		return children;
	}

	public static void saveResource(WSClient client, ResourceDescriptor rd)
			throws Exception {
		client.addOrModifyResource(rd, null);
	}

	static int depth = 0; // This variable is used to print tabs...

}
