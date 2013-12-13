package com.jaspersoft.studio.server.protocol;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.ireport.jasperserver.ws.FileContent;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.Argument;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.serverinfo.ServerInfo;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.model.server.ServerProfile;

public class ProxyConnection implements IConnection {

	private IConnection[] cons = getConnections();

	private IConnection[] getConnections() {
		List<IConnection> c = new ArrayList<IConnection>();
		// c.add(new RestV2Connection());
		// c.add(new RestV2ConnectionJersey());

		c.addAll(Activator.getExtManager().getProtocols());

		return c.toArray(new IConnection[c.size()]);
	}

	private IConnection c;

	@Override
	public boolean connect(IProgressMonitor monitor, ServerProfile sp) throws Exception {
		for (IConnection co : cons) {
			try {
				if (co.connect(monitor, sp)) {
					c = co;
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
				if (co == cons[cons.length - 1] || monitor.isCanceled())
					throw e;
			}
		}
		return false;
	}

	@Override
	public ServerInfo getServerInfo(IProgressMonitor monitor) throws Exception {
		return c.getServerInfo(monitor);
	}

	@Override
	public ResourceDescriptor get(IProgressMonitor monitor, ResourceDescriptor rd, File f) throws Exception {
		return c.get(monitor, rd, f);
	}

	@Override
	public ResourceDescriptor get(IProgressMonitor monitor, ResourceDescriptor rd, File outFile, List<Argument> args) throws Exception {
		return c.get(monitor, rd, outFile, args);
	}

	@Override
	public List<ResourceDescriptor> list(IProgressMonitor monitor, ResourceDescriptor rd) throws Exception {
		return c.list(monitor, rd);
	}

	@Override
	public ResourceDescriptor move(IProgressMonitor monitor, ResourceDescriptor rd, String destFolderURI) throws Exception {
		return c.move(monitor, rd, destFolderURI);
	}

	@Override
	public ResourceDescriptor copy(IProgressMonitor monitor, ResourceDescriptor rd, String destFolderURI) throws Exception {
		return c.copy(monitor, rd, destFolderURI);
	}

	@Override
	public ResourceDescriptor addOrModifyResource(IProgressMonitor monitor, ResourceDescriptor rd, File inputFile) throws Exception {
		return c.addOrModifyResource(monitor, rd, inputFile);
	}

	@Override
	public ResourceDescriptor modifyReportUnitResource(IProgressMonitor monitor, String rUnitUri, ResourceDescriptor rd, File inFile) throws Exception {
		return c.modifyReportUnitResource(monitor, rUnitUri, rd, inFile);
	}

	@Override
	public void delete(IProgressMonitor monitor, ResourceDescriptor rd) throws Exception {
		c.delete(monitor, rd);
	}

	@Override
	public void delete(IProgressMonitor monitor, ResourceDescriptor rd, String reportUnitUri) throws Exception {
		c.delete(monitor, rd, reportUnitUri);
	}

	@Override
	public Map<String, FileContent> runReport(IProgressMonitor monitor, ResourceDescriptor rd, Map<String, Object> prm, List<Argument> args) throws Exception {
		return c.runReport(monitor, rd, prm, args);
	}

	@Override
	public List<ResourceDescriptor> listDatasources(IProgressMonitor monitor) throws Exception {
		return c.listDatasources(monitor);
	}

	@Override
	public String getWebservicesUri() {
		return c.getWebservicesUri();
	}

	@Override
	public String getUsername() {
		return c.getUsername();
	}

	@Override
	public String getPassword() {
		return c.getPassword();
	}

}
