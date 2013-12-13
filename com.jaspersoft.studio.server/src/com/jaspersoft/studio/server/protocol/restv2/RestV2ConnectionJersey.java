package com.jaspersoft.studio.server.protocol.restv2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.sf.jasperreports.eclipse.util.FileUtils;

import org.apache.commons.io.IOUtils;
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
import com.jaspersoft.jasperserver.dto.resources.ClientFile;
import com.jaspersoft.jasperserver.dto.resources.ClientResource;
import com.jaspersoft.jasperserver.dto.resources.ClientResourceListWrapper;
import com.jaspersoft.jasperserver.dto.resources.ClientResourceLookup;
import com.jaspersoft.jasperserver.dto.serverinfo.ServerInfo;
import com.jaspersoft.jasperserver.remote.exception.xml.ErrorDescriptor;
import com.jaspersoft.studio.server.model.datasource.filter.DatasourcesAllFilter;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.utils.Pass;
import com.jaspersoft.studio.utils.Misc;

public class RestV2ConnectionJersey extends ARestV2Connection {

	private <T> T toObj(Response res, final Class<T> clazz, IProgressMonitor monitor) throws IOException {
		T r = null;
		try {
			switch (res.getStatus()) {
			case 200:
				r = res.readEntity(clazz);
			case 204:
				break;
			case 400:
			case 403:
			case 404:
			case 409:
			case 500:
				if (res.getHeaderString("Content-Type").equals(MediaType.APPLICATION_XML_TYPE)) {
					ErrorDescriptor ed = res.readEntity(ErrorDescriptor.class);
					if (ed != null)
						throw new ClientProtocolException(MessageFormat.format(ed.getMessage(), (Object[]) ed.getParameters()));
				}
			default:
				throw new HttpResponseException(res.getStatus(), res.getStatusInfo().getReasonPhrase());
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			res.close();
		}
		return r;
	}

	private void readFile(Response res, File f) throws IOException {
		try {
			switch (res.getStatus()) {
			case 200:
				InputStream obj = res.readEntity(InputStream.class);
				if (obj != null) {
					OutputStream out = new FileOutputStream(f);
					try {
						IOUtils.copy((InputStream) obj, out);
					} finally {
						FileUtils.closeStream(out);
					}
				}
			case 204:
				break;
			case 400:
			case 403:
			case 404:
			case 409:
			case 500:
				if (res.getHeaderString("Content-Type").equals(MediaType.APPLICATION_XML_TYPE)) {
					ErrorDescriptor ed = res.readEntity(ErrorDescriptor.class);
					if (ed != null)
						throw new ClientProtocolException(MessageFormat.format(ed.getMessage(), (Object[]) ed.getParameters()));
				}
			default:
				throw new HttpResponseException(res.getStatus(), res.getStatusInfo().getReasonPhrase());
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			res.close();
		}
	}

	private void writeFile(Response res, InputStream in) throws IOException {
		try {
			switch (res.getStatus()) {
			case 200:
				res.readEntity(String.class);
			case 204:
				break;
			case 400:
			case 403:
			case 404:
			case 409:
			case 500:
				if (res.getHeaderString("Content-Type").equals(MediaType.APPLICATION_XML_TYPE)) {
					ErrorDescriptor ed = res.readEntity(ErrorDescriptor.class);
					if (ed != null)
						throw new ClientProtocolException(MessageFormat.format(ed.getMessage(), (Object[]) ed.getParameters()));
				}
			default:
				throw new HttpResponseException(res.getStatus(), res.getStatusInfo().getReasonPhrase());
			}
		} finally {
			FileUtils.closeStream(in);
			res.close();
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

		String user = sp.getUser();
		if (!Misc.isNullOrEmpty(sp.getOrganisation()))
			user += "|" + sp.getOrganisation();
		client.register(new HttpBasicAuthFilter(user, Pass.getPass(sp.getPass())));
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

			System.out.println(tgt.getUri());
			ClientResourceListWrapper resources = toObj(tgt.request().get(), ClientResourceListWrapper.class, monitor);
			if (resources != null) {
				boolean isPublic = false;
				for (ClientResourceLookup crl : resources.getResourceLookups()) {
					if (!isPublic)
						isPublic = crl.getUri().equals("/public");
					ResourceDescriptor nrd = Rest2Soap.getRDLookup(this, crl);
					rds.add(nrd);
					if (nrd.getWsType().equals(ResourceDescriptor.TYPE_CONTENT_RESOURCE)) {
						String name = nrd.getUriString().toLowerCase();
						if (name.endsWith(".png") || name.endsWith(".jpeg") || name.endsWith(".jpg") || name.endsWith(".bmp") || name.endsWith(".tiff") || name.endsWith(".gif"))
							nrd.setWsType(ResourceDescriptor.TYPE_IMAGE);
						else if (name.endsWith(".xml"))
							nrd.setWsType(ResourceDescriptor.TYPE_XML_FILE);
						else if (name.endsWith(".jrxml"))
							nrd.setWsType(ResourceDescriptor.TYPE_JRXML);
						else if (name.endsWith(".jar"))
							nrd.setWsType(ResourceDescriptor.TYPE_CLASS_JAR);
						else if (name.endsWith(".jrtx"))
							nrd.setWsType(ResourceDescriptor.TYPE_STYLE_TEMPLATE);
						else if (name.endsWith(".properties"))
							nrd.setWsType(ResourceDescriptor.TYPE_RESOURCE_BUNDLE);
					}
				}
				// workaround
				if (rd.getUriString().equals("/") && !isPublic) {
					try {
						ResourceDescriptor pub = new ResourceDescriptor();
						pub.setUriString("/public");
						pub.setWsType(ResourceDescriptor.TYPE_FOLDER);
						rds.add(get(monitor, pub, null));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return rds;
	}

	@Override
	public ResourceDescriptor get(IProgressMonitor monitor, ResourceDescriptor rd, File f) throws Exception {
		WebTarget tgt = target.path("resources" + rd.getUriString());
		tgt = tgt.queryParam("expanded", "true");

		System.out.println(tgt.getUri());
		String rtype = WsTypes.INST().toRestType(rd.getWsType());
		ClientResource<?> crl = toObj(tgt.request("application/repository." + rtype + "+" + FORMAT).get(), WsTypes.INST().getType(rtype), monitor);
		if (crl != null) {
			if (f != null && crl instanceof ClientFile) {
				ClientFile cf = (ClientFile) crl;
				tgt = target.path("resources" + rd.getUriString());

				readFile(tgt.request(cf.getType().getMimeType()).header("Accept", cf.getType().getMimeType()).get(), f);
			}
			return Rest2Soap.getRD(this, crl, rd);
		}
		return null;
	}

	@Override
	public ResourceDescriptor get(IProgressMonitor monitor, ResourceDescriptor rd, File outFile, List<Argument> args) throws Exception {
		return get(monitor, rd, outFile);
	}

	@Override
	public ResourceDescriptor move(IProgressMonitor monitor, ResourceDescriptor rd, String destFolderURI) throws Exception {
		String rtype = WsTypes.INST().toRestType(rd.getWsType());

		WebTarget tgt = target.path("resources" + destFolderURI);
		tgt = tgt.queryParam("overwrite", "true");
		tgt = tgt.queryParam("createFolders", "true");

		Response r = tgt.request().header("Content-Location", rd.getUriString()).put(Entity.entity("", MediaType.APPLICATION_XML_TYPE));
		ClientResource<?> crl = toObj(r, WsTypes.INST().getType(rtype), monitor);
		if (crl != null)
			return Rest2Soap.getRD(this, crl, rd);
		return null;
	}

	@Override
	public ResourceDescriptor copy(IProgressMonitor monitor, ResourceDescriptor rd, String destFolderURI) throws Exception {
		String rtype = WsTypes.INST().toRestType(rd.getWsType());

		WebTarget tgt = target.path("resources" + destFolderURI);
		tgt = tgt.queryParam("overwrite", "true");
		tgt = tgt.queryParam("createFolders", "true");

		Response r = tgt.request().header("Content-Location", rd.getUriString()).post(Entity.entity("", MediaType.APPLICATION_XML_TYPE));
		ClientResource<?> crl = toObj(r, WsTypes.INST().getType(rtype), monitor);
		if (crl != null)
			return Rest2Soap.getRD(this, crl, rd);
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
		if (crl != null) {
			if (crl instanceof ClientFile && inputFile != null) {
				ClientFile cf = (ClientFile) crl;
				tgt = target.path("resources" + rd.getUriString());

				Builder h = tgt.request().header("Content-Description", cf.getLabel());
				h = h.header("Content-Disposition", "attachment; filename=" + inputFile.getName());
				InputStream in = new FileInputStream(inputFile);
				writeFile(h.put(Entity.entity(in, cf.getType().getMimeType())), in);
			}
			return Rest2Soap.getRD(this, crl, rd);
		}
		return null;
	}

	@Override
	public ResourceDescriptor modifyReportUnitResource(IProgressMonitor monitor, String rUnitUri, ResourceDescriptor rd, File inFile) throws Exception {
		return addOrModifyResource(monitor, rd, inFile);
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
		List<ResourceDescriptor> rds = new ArrayList<ResourceDescriptor>();
		WebTarget tgt = target.path("resources");
		for (String type : DatasourcesAllFilter.getTypes())
			tgt = tgt.queryParam("type", type);
		tgt = tgt.queryParam("recursive", "false");
		tgt = tgt.queryParam("sortBy", "label");
		tgt = tgt.queryParam("limit", Integer.toString(Integer.MAX_VALUE));

		ClientResourceListWrapper resources = toObj(tgt.request().get(), ClientResourceListWrapper.class, monitor);
		if (resources != null)
			for (ClientResourceLookup crl : resources.getResourceLookups())
				rds.add(Rest2Soap.getRDLookup(this, crl));
		return rds;
	}

}
