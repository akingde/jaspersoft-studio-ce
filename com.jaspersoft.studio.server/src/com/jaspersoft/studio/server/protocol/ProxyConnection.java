package com.jaspersoft.studio.server.protocol;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.jaspersoft.ireport.jasperserver.ws.FileContent;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.Argument;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.protocol.restv2.RestV2Connection;
import com.jaspersoft.studio.server.protocol.soap.SoapConnection;

public class ProxyConnection implements IConnection {

	private IConnection[] cons = new IConnection[] { new RestV2Connection(),
			new SoapConnection() };

	private IConnection c;

	@Override
	public boolean connect(ServerProfile sp) throws Exception {
		for (IConnection co : cons) {
			try {
				if (co.connect(sp)) {
					c = co;
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public ServerInfo getServerInfo() throws Exception {
		return c.getServerInfo();
	}

	@Override
	public ResourceDescriptor get(ResourceDescriptor rd, File f)
			throws Exception {
		return c.get(rd, f);
	}

	@Override
	public ResourceDescriptor get(ResourceDescriptor rd, File outFile,
			List<Argument> args) throws Exception {
		return c.get(rd, outFile, args);
	}

	@Override
	public List<ResourceDescriptor> list(ResourceDescriptor rd)
			throws Exception {
		return c.list(rd);
	}

	@Override
	public void move(ResourceDescriptor rd, String destFolderURI)
			throws Exception {
		c.move(rd, destFolderURI);
	}

	@Override
	public ResourceDescriptor copy(ResourceDescriptor rd, String destFolderURI)
			throws Exception {
		return c.copy(rd, destFolderURI);
	}

	@Override
	public ResourceDescriptor addOrModifyResource(ResourceDescriptor rd,
			File inputFile) throws Exception {
		return c.addOrModifyResource(rd, inputFile);
	}

	@Override
	public ResourceDescriptor modifyReportUnitResource(String rUnitUri,
			ResourceDescriptor rd, File inFile) throws Exception {
		return c.modifyReportUnitResource(rUnitUri, rd, inFile);
	}

	@Override
	public void delete(ResourceDescriptor rd) throws Exception {
		c.delete(rd);
	}

	@Override
	public void delete(ResourceDescriptor rd, String reportUnitUri)
			throws Exception {
		c.delete(rd, reportUnitUri);
	}

	@Override
	public Map<String, FileContent> runReport(ResourceDescriptor rd,
			Map<String, Object> prm, List<Argument> args) throws Exception {
		return c.runReport(rd, prm, args);
	}

	@Override
	public List<ResourceDescriptor> listDatasources() throws Exception {
		return c.listDatasources();
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
