package com.jaspersoft.studio.server.protocol.soap;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.axis.AxisProperties;
import org.apache.axis.components.net.DefaultCommonsHTTPClientProperties;

import com.jaspersoft.ireport.jasperserver.ws.FileContent;
import com.jaspersoft.ireport.jasperserver.ws.JServer;
import com.jaspersoft.ireport.jasperserver.ws.WSClient;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.Argument;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.protocol.IConnection;
import com.jaspersoft.studio.server.protocol.ServerInfo;

public class SoapConnection implements IConnection {
	private WSClient client;
	private ServerInfo serverInfo;

	@Override
	public ServerInfo getServerInfo() throws Exception {
		if (serverInfo != null)
			return serverInfo;
		serverInfo = new ServerInfo();
		serverInfo.setVersion("4.5");
		client.getVersion();
		return serverInfo;
	}

	@Override
	public boolean connect(ServerProfile sp) throws Exception {
		JServer server = new JServer();
		setupJServer(server, sp);

		client = server.getWSClient();
		if (getServerInfo() == null)
			return false;
		return true;
	}

	private static void setupJServer(JServer server, ServerProfile sp) {
		AxisProperties
				.setProperty(
						DefaultCommonsHTTPClientProperties.MAXIMUM_CONNECTIONS_PER_HOST_PROPERTY_KEY,
						"4");
		server.setName(sp.getName());
		server.setUrl(sp.getUrl() + "services/repository");
		String username = sp.getUser();
		if (sp.getOrganisation() != null
				&& !sp.getOrganisation().trim().isEmpty())
			username += "|" + sp.getOrganisation();
		server.setUsername(username);
		server.setPassword(sp.getPass());
		server.setTimeout(sp.getTimeout());
		server.setChunked(sp.isChunked());
	}

	@Override
	public ResourceDescriptor get(ResourceDescriptor rd, File f)
			throws Exception {
		return client.get(rd, f);
	}

	@Override
	public List<ResourceDescriptor> list(ResourceDescriptor rd)
			throws Exception {
		return client.list(rd);
	}

	@Override
	public ResourceDescriptor get(ResourceDescriptor rd, File outFile,
			List<Argument> args) throws Exception {
		return client.get(rd, outFile);
	}

	@Override
	public void move(ResourceDescriptor rd, String destFolderURI)
			throws Exception {
		client.move(rd, destFolderURI);
	}

	@Override
	public ResourceDescriptor copy(ResourceDescriptor rd, String destFolderURI)
			throws Exception {
		return client.copy(rd, destFolderURI);
	}

	@Override
	public ResourceDescriptor addOrModifyResource(ResourceDescriptor rd,
			File inputFile) throws Exception {
		return client.addOrModifyResource(rd, inputFile);
	}

	@Override
	public ResourceDescriptor modifyReportUnitResource(String rUnitUri,
			ResourceDescriptor rd, File inFile) throws Exception {
		return client.modifyReportUnitResource(rUnitUri, rd, inFile);
	}

	@Override
	public void delete(ResourceDescriptor rd) throws Exception {
		client.delete(rd);
	}

	@Override
	public void delete(ResourceDescriptor rd, String reportUnitUri)
			throws Exception {
		client.delete(rd, reportUnitUri);
	}

	@Override
	public Map<String, FileContent> runReport(ResourceDescriptor rd,
			Map<String, Object> prm, List<Argument> args) throws Exception {
		return client.runReport(rd, prm, args);
	}

	@Override
	public List<ResourceDescriptor> listDatasources() throws Exception {
		return client.listDatasources();
	}

	@Override
	public String getWebservicesUri() {
		return client.getWebservicesUri();
	}

	@Override
	public String getUsername() {
		return client.getUsername();
	}

	@Override
	public String getPassword() {
		return client.getPassword();
	}
}
