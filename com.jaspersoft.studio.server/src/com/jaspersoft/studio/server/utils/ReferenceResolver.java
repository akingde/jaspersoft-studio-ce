package com.jaspersoft.studio.server.utils;

import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.ireport.jasperserver.ws.WSClient;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.server.model.MReference;
import com.jaspersoft.studio.server.model.server.MServerProfile;

public class ReferenceResolver {
	public static ResourceDescriptor resolveReference(MReference res,
			IProgressMonitor monitor) throws Exception {
		INode n = res.getRoot();
		if (n != null && n instanceof MServerProfile) {
			MServerProfile sp = (MServerProfile) res.getRoot();
			return resolveReference(sp.getWsClient(), res.getValue(), monitor);
		}
		return null;
	}

	public static ResourceDescriptor resolveReference(WSClient wsc,
			ResourceDescriptor refrd, IProgressMonitor monitor)
			throws Exception {
		ResourceDescriptor rd = new ResourceDescriptor();
		rd.setUriString(refrd.getReferenceUri());
		rd.setIsNew(false);

		rd = wsc.get(rd, null);
		if (monitor != null && monitor.isCanceled())
			return rd;
		if (rd.getIsReference())
			resolveReference(wsc, rd, monitor);
		return rd;
	}
}
