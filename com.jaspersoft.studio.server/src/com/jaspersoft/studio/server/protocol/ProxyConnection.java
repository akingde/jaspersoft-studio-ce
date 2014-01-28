package com.jaspersoft.studio.server.protocol;

import java.io.File;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpResponseException;
import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.ireport.jasperserver.ws.FileContent;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.Argument;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.resources.ClientResource;
import com.jaspersoft.jasperserver.dto.serverinfo.ServerInfo;
import com.jaspersoft.studio.server.AFinderUI;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.model.server.ServerProfile;

public class ProxyConnection implements IConnection {
	public Format getDateFormat() {
		return c.getDateFormat();
	}

	public Format getTimestampFormat() {
		return c.getTimestampFormat();
	}

	public Format getNumberFormat() {
		return c.getNumberFormat();
	}

	private IConnection[] cons = getConnections();

	private IConnection[] getConnections() {
		List<IConnection> c = new ArrayList<IConnection>();
		// c.add(new RestV2Connection());
		// c.add(new RestV2ConnectionJersey());

		c.addAll(Activator.getExtManager().getProtocols());
		for (IConnection con : c)
			con.setParent(this);
		return c.toArray(new IConnection[c.size()]);
	}

	private IConnection c;
	private IConnection soap;

	@Override
	public boolean connect(IProgressMonitor monitor, ServerProfile sp) throws Exception {
		for (IConnection co : cons) {
			try {
				if (c == null && co.connect(monitor, sp))
					c = co;
				if (soap == null && co.getClass().getName().toUpperCase().contains("SOAP")) {
					if (c == co)
						soap = co;
					else if (co.connect(monitor, sp))
						soap = co;
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return c != null;
	}

	@Override
	public ServerInfo getServerInfo(IProgressMonitor monitor) throws Exception {
		return c.getServerInfo(monitor);
	}

	private boolean useSoap(IProgressMonitor monitor, ResourceDescriptor rd) throws Exception {
		String v = c.getServerInfo(monitor).getVersion();
		if (c != soap && v.compareTo("5.5") > 0 && v.compareTo("5.6") < 0 && rd.getWsType().equals(ResourceDescriptor.TYPE_REFERENCE))
			return true;
		return false;
	}

	@Override
	public ResourceDescriptor get(IProgressMonitor monitor, ResourceDescriptor rd, File f) throws Exception {
		if (useSoap(monitor, rd))
			rd = soap.get(monitor, rd, f);
		else
			try {
				rd = c.get(monitor, rd, f);
			} catch (Exception e) {
				if (e instanceof HttpResponseException) {
					HttpResponseException he = (HttpResponseException) e;
					if (he.getStatusCode() == 500 && he.getMessage().contains("Unexpected error")) {
						rd = soap.get(monitor, rd, f);
						rd.setDirty(false);
						return rd;
					}
				}
				throw e;
			}
		rd.setDirty(false);
		return rd;
	}

	@Override
	public List<ResourceDescriptor> list(IProgressMonitor monitor, ResourceDescriptor rd) throws Exception {
		List<ResourceDescriptor> list = null;
		// String v = c.getServerInfo(monitor).getVersion();
		// if (c != soap && v.compareTo("5.5") > 0 && v.compareTo("6") < 0)
		// list = soap.list(monitor, rd);
		// else
		try {
			list = c.list(monitor, rd);
		} catch (Exception e) {
			if (e instanceof HttpResponseException) {
				HttpResponseException he = (HttpResponseException) e;
				if (he.getStatusCode() == 500 && he.getMessage().contains("Unexpected error")) {
					list = soap.list(monitor, rd);
					for (ResourceDescriptor r : list)
						r.setDirty(false);
					return list;
				}
			}
			throw e;
		}
		for (ResourceDescriptor r : list)
			r.setDirty(false);
		return list;
	}

	@Override
	public ResourceDescriptor move(IProgressMonitor monitor, ResourceDescriptor rd, String destFolderURI) throws Exception {
		rd = c.move(monitor, rd, destFolderURI);
		rd.setDirty(false);
		return rd;
	}

	@Override
	public ResourceDescriptor copy(IProgressMonitor monitor, ResourceDescriptor rd, String destFolderURI) throws Exception {
		rd = c.copy(monitor, rd, destFolderURI);
		rd.setDirty(false);
		return rd;
	}

	@Override
	public ResourceDescriptor addOrModifyResource(IProgressMonitor monitor, ResourceDescriptor rd, File inputFile) throws Exception {
		rd = c.addOrModifyResource(monitor, rd, inputFile);
		rd.setDirty(false);
		return rd;
	}

	@Override
	public ResourceDescriptor modifyReportUnitResource(IProgressMonitor monitor, ResourceDescriptor runit, ResourceDescriptor rd, File inFile) throws Exception {
		rd = c.modifyReportUnitResource(monitor, runit, rd, inFile);
		rd.setDirty(false);
		return rd;
	}

	@Override
	public void delete(IProgressMonitor monitor, ResourceDescriptor rd) throws Exception {
		c.delete(monitor, rd);
	}

	@Override
	public void delete(IProgressMonitor monitor, ResourceDescriptor rd, ResourceDescriptor runit) throws Exception {
		c.delete(monitor, rd, runit);
	}

	@Override
	public Map<String, FileContent> runReport(IProgressMonitor monitor, ResourceDescriptor rd, Map<String, Object> prm, List<Argument> args) throws Exception {
		return c.runReport(monitor, rd, prm, args);
	}

	@Override
	public List<ResourceDescriptor> listDatasources(IProgressMonitor monitor) throws Exception {
		List<ResourceDescriptor> list = c.listDatasources(monitor);
		for (ResourceDescriptor r : list)
			r.setDirty(false);
		return list;
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

	@Override
	public void findResources(IProgressMonitor monitor, AFinderUI callback) throws Exception {
		c.findResources(monitor, callback);
	}

	@Override
	public ResourceDescriptor toResourceDescriptor(ClientResource<?> rest) throws Exception {
		return c.toResourceDescriptor(rest);
	}

	@Override
	public boolean isSupported(Feature f) {
		return c.isSupported(f);
	}

	@Override
	public void reorderInputControls(String uri, List<ResourceDescriptor> rd, IProgressMonitor monitor) throws Exception {
		c.reorderInputControls(uri, rd, monitor);
	}

	@Override
	public ServerProfile getServerProfile() {
		return c.getServerProfile();
	}

	@Override
	public ResourceDescriptor initInputControls(String uri, IProgressMonitor monitor) throws Exception {
		return c.initInputControls(uri, monitor);
	}

	@Override
	public List<ResourceDescriptor> cascadeInputControls(ResourceDescriptor runit, List<ResourceDescriptor> ics, IProgressMonitor monitor) throws Exception {
		return c.cascadeInputControls(runit, ics, monitor);
	}

	@Override
	public void setParent(IConnection parent) {
	}

}
