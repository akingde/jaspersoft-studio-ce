package com.jaspersoft.studio.server.protocol.restv2;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnector;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.filter.HttpBasicAuthFilter;

import com.jaspersoft.ireport.jasperserver.ws.FileContent;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.Argument;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.resources.ClientResource;
import com.jaspersoft.jasperserver.dto.resources.ClientResourceListWrapper;
import com.jaspersoft.jasperserver.dto.resources.ClientResourceLookup;
import com.jaspersoft.jasperserver.dto.serverinfo.ServerInfo;
import com.jaspersoft.jasperserver.remote.exception.xml.ErrorDescriptor;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.utils.Pass;

public class RestV2ConnectionJersey extends ARestV2Connection {

	private <T> T toObj(Response res, final Class<T> clazz, IProgressMonitor monitor) throws IOException {
		try {
			switch (res.getStatus()) {
			case 200:
				return res.readEntity(clazz);
			case 204:
				return null;
			case 400:
			case 404:
				ErrorDescriptor ed = res.readEntity(ErrorDescriptor.class);
				if (ed != null)
					throw new ClientProtocolException(MessageFormat.format(ed.getMessage(), (Object[]) ed.getParameters()));
			default:
				throw new HttpResponseException(res.getStatus(), res.getStatusInfo().getReasonPhrase());
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public boolean connect(IProgressMonitor monitor, ServerProfile sp) throws Exception {
		this.sp = sp;

		ClientConfig clientConfig = new ClientConfig();
		// values are in milliseconds
		clientConfig.property(ClientProperties.READ_TIMEOUT, sp.getTimeout());
		clientConfig.property(ClientProperties.CONNECT_TIMEOUT, sp.getTimeout());

		clientConfig.property(ApacheClientProperties.PREEMPTIVE_BASIC_AUTHENTICATION, true);

		// config your ssl for apache connector
		SslConfigurator sslConfig = SslConfigurator.newInstance(true);
		clientConfig.property(ApacheClientProperties.SSL_CONFIG, sslConfig);

		ApacheConnector connector = new ApacheConnector(clientConfig);
		clientConfig.connector(connector);

		Client client = ClientBuilder.newBuilder().withConfig(clientConfig).build();

		client.register(new HttpBasicAuthFilter(sp.getUser(), Pass.getPass(sp.getPass())));
		target = client.target(sp.getUrl() + SUFFIX);
		getServerInfo(monitor);

		return true;
	}

	private WebTarget target;

	@Override
	public ServerInfo getServerInfo(IProgressMonitor monitor) throws Exception {
		if (serverInfo != null)
			return serverInfo;
		serverInfo = toObj(target.path("serverInfo").request().get(), ServerInfo.class, monitor);
		if (serverInfo != null) {
			dateFormat = new SimpleDateFormat(serverInfo.getDateFormatPattern());
			timestampFormat = new SimpleDateFormat(serverInfo.getDatetimeFormatPattern());
		}
		return serverInfo;
	}

	@Override
	public List<ResourceDescriptor> list(IProgressMonitor monitor, ResourceDescriptor rd) throws Exception {
		List<ResourceDescriptor> rds = new ArrayList<ResourceDescriptor>();
		if (rd.getWsType().equals(ResourceDescriptor.TYPE_REPORTUNIT)) {
			rd = get(monitor, rd, null);
			return rd.getChildren();
		} else {
			WebTarget tgt = target.path("resources");
			tgt = tgt.queryParam("folderUri", rd.getUriString());
			tgt = tgt.queryParam("recursive", "false");
			tgt = tgt.queryParam("sortBy", "label");
			tgt = tgt.queryParam("limit", Integer.toString(Integer.MAX_VALUE));

			ClientResourceListWrapper resources = toObj(tgt.request().get(), ClientResourceListWrapper.class, monitor);
			if (resources != null)
				for (ClientResourceLookup crl : resources.getResourceLookups())
					rds.add(Rest2Soap.getRDLookup(this, crl));
		}
		return rds;
	}

	@Override
	public ResourceDescriptor get(IProgressMonitor monitor, ResourceDescriptor rd, File f) throws Exception {
		WebTarget tgt = target.path("resources" + rd.getUriString());
		tgt = tgt.queryParam("expanded", "true");

		String rtype = WsTypes.INST().toRestType(rd.getWsType());
		ClientResource<?> crl = toObj(tgt.request("application/repository." + rtype + "+" + FORMAT).get(), WsTypes.INST().getType(rtype), monitor);
		if (crl != null)
			return Rest2Soap.getRD(this, crl, rd);
		return null;
	}

	@Override
	public ResourceDescriptor get(IProgressMonitor monitor, ResourceDescriptor rd, File outFile, List<Argument> args) throws Exception {
		WebTarget tgt = target.path("resources" + rd.getUriString());
		tgt = tgt.queryParam("expanded", "true");

		String rtype = WsTypes.INST().toRestType(rd.getWsType());
		ClientResource<?> crl = toObj(tgt.request("application/repository." + rtype + "+" + FORMAT).get(), WsTypes.INST().getType(rtype), monitor);
		if (crl != null)
			return Rest2Soap.getRD(this, crl, rd);
		return null;
	}

	@Override
	public void move(IProgressMonitor monitor, ResourceDescriptor rd, String destFolderURI) throws Exception {
		// URIBuilder ub = new URIBuilder(url("resources" + destFolderURI));
		// ub.addParameter("overwrite", "true");
		// ub.addParameter("createFolders", "true");
		// Request req = HttpUtils.put(ub.build().toASCIIString(), sp);
		// req.setHeader("Content-Location", rd.getUriString());
		// String rtype = WsTypes.INST().toRestType(rd.getWsType());
		// ClientResource<?> crl = toObj(req, WsTypes.INST().getType(rtype),
		// monitor);
		// if (crl != null)
		// Rest2Soap.getRD(this, crl, rd);
	}

	@Override
	public ResourceDescriptor copy(IProgressMonitor monitor, ResourceDescriptor rd, String destFolderURI) throws Exception {
		// URIBuilder ub = new URIBuilder(url("resources" + destFolderURI));
		// ub.addParameter("overwrite", "true");
		// ub.addParameter("createFolders", "true");
		// Request req = HttpUtils.post(ub.build().toASCIIString(), sp);
		// req.setHeader("Content-Location", rd.getUriString());
		// String rtype = WsTypes.INST().toRestType(rd.getWsType());
		// ClientResource<?> crl = toObj(req, WsTypes.INST().getType(rtype),
		// monitor);
		// if (crl != null)
		// return Rest2Soap.getRD(this, crl, rd);
		return null;
	}

	@Override
	public ResourceDescriptor addOrModifyResource(IProgressMonitor monitor, ResourceDescriptor rd, File inputFile) throws Exception {
		String rtype = WsTypes.INST().toRestType(rd.getWsType());
		ClientResource<?> cr = Soap2Rest.getResource(this, rd);

		WebTarget tgt = target.path("resources" + rd.getUriString());
		tgt = tgt.queryParam("createFolders", "true");
		tgt = tgt.queryParam("overwrite", "true");

		Response r = tgt.request().put(Entity.entity(cr, "application/repository." + rtype + "+" + FORMAT));
		ClientResource<?> crl = toObj(r, WsTypes.INST().getType(rtype), monitor);
		if (crl != null)
			return Rest2Soap.getRD(this, crl, rd);
		return null;
	}

	@Override
	public ResourceDescriptor modifyReportUnitResource(IProgressMonitor monitor, String rUnitUri, ResourceDescriptor rd, File inFile) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(IProgressMonitor monitor, ResourceDescriptor rd) throws Exception {
		WebTarget tgt = target.path("resources" + rd.getUriString());
		if (tgt.request().delete().getStatus() == 204)
			System.out.println("Deleted");
	}

	@Override
	public void delete(IProgressMonitor monitor, ResourceDescriptor rd, String reportUnitUri) throws Exception {
		delete(monitor, rd);
	}

	@Override
	public Map<String, FileContent> runReport(IProgressMonitor monitor, ResourceDescriptor rd, Map<String, Object> prm, List<Argument> args) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResourceDescriptor> listDatasources(IProgressMonitor monitor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
