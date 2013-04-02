package com.jaspersoft.studio.server.protocol.restv2;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaspersoft.ireport.jasperserver.ws.FileContent;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.Argument;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.protocol.IConnection;
import com.jaspersoft.studio.server.protocol.ServerInfo;
import com.jaspersoft.studio.server.utils.HttpUtils;

public class RestV2Connection implements IConnection {
	public static final String SUFFIX = "rest_v2/";
	private ServerProfile sp;

	@Override
	public boolean connect(ServerProfile sp) throws Exception {
		this.sp = sp;
		client = new HttpClient();
		getServerInfo();

		return true;
	}

	private ServerInfo serverInfo;
	private HttpClient client;

	@Override
	public ServerInfo getServerInfo() throws Exception {
		if (serverInfo != null)
			return serverInfo;

		HttpMethod m = HttpUtils.get(client, sp.getUrl() + SUFFIX
				+ "serverInfo");
		ObjectMapper mapper = new ObjectMapper();
		serverInfo = mapper.readValue(m.getResponseBodyAsStream(),
				ServerInfo.class);

		return serverInfo;
	}

	@Override
	public ResourceDescriptor get(ResourceDescriptor rd, File f)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResourceDescriptor> list(ResourceDescriptor rd)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResourceDescriptor get(ResourceDescriptor rd, File outFile,
			List<Argument> args) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void move(ResourceDescriptor rd, String destFolderURI)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public ResourceDescriptor copy(ResourceDescriptor rd, String destFolderURI)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResourceDescriptor addOrModifyResource(ResourceDescriptor rd,
			File inputFile) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResourceDescriptor modifyReportUnitResource(String rUnitUri,
			ResourceDescriptor rd, File inFile) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(ResourceDescriptor rd) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(ResourceDescriptor rd, String reportUnitUri)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, FileContent> runReport(ResourceDescriptor rd,
			Map<String, Object> prm, List<Argument> args) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResourceDescriptor> listDatasources() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWebservicesUri() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

}
