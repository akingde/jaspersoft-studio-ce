package com.jaspersoft.studio.server.protocol.restv2;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.eclipse.util.FileUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.eclipse.core.runtime.IProgressMonitor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaspersoft.ireport.jasperserver.ws.FileContent;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.Argument;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.resources.ClientResource;
import com.jaspersoft.jasperserver.dto.resources.ClientResourceListWrapper;
import com.jaspersoft.jasperserver.dto.resources.ClientResourceLookup;
import com.jaspersoft.jasperserver.dto.serverinfo.ServerInfo;
import com.jaspersoft.jasperserver.remote.exception.xml.ErrorDescriptor;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.protocol.ConnectionManager;
import com.jaspersoft.studio.server.protocol.IConnection;
import com.jaspersoft.studio.server.utils.HttpUtils;

public class RestV2Connection implements IConnection {
	public static final String SUFFIX = "rest_v2/";
	public static final String FORMAT = "xml";
	private ServerProfile sp;

	private String url(String suffix) {
		return sp.getUrl() + SUFFIX + suffix;
	}

	private <T> T toObj(Request req, final Class<T> clazz, IProgressMonitor monitor) throws IOException {
		T obj = null;
		ConnectionManager.register(monitor, req);
		try {
			obj = exec.execute(req).handleResponse(new ResponseHandler<T>() {

				public T handleResponse(final HttpResponse response) throws IOException {
					HttpEntity entity = response.getEntity();
					InputStream in = null;
					try {
						in = entity.getContent();
						StatusLine statusLine = response.getStatusLine();
						switch (statusLine.getStatusCode()) {
						case 200:
							if (in == null)
								throw new ClientProtocolException("Response contains no content");
							return mapper.readValue(in, clazz);
						case 204:
							return null;
						case 400:
						case 401:
						case 403:
						case 404:
							ErrorDescriptor ed = mapper.readValue(in, ErrorDescriptor.class);
							if (ed != null)
								throw new ClientProtocolException(MessageFormat.format(ed.getMessage(), (Object[]) ed.getParameters()));
						default:
							throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
						}
					} finally {
						FileUtils.closeStream(in);
					}
				}

			});
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			ConnectionManager.unregister(req);
		}
		return obj;
	}

	private SimpleDateFormat dateFormat;
	private SimpleDateFormat timestampFormat;

	public Date toDate(String sdate) throws ParseException {
		if (sdate == null)
			return null;
		return dateFormat.parse(sdate);
	}

	public Date toTimestamp(String sdate) throws ParseException {
		if (sdate == null)
			return null;
		return timestampFormat.parse(sdate);
	}

	public String date2str(Date d) throws ParseException {
		if (d == null)
			return null;
		return dateFormat.format(d);
	}

	public String timestamp2str(Date d) throws ParseException {
		if (d == null)
			return null;
		return timestampFormat.format(d);
	}

	@Override
	public boolean connect(IProgressMonitor monitor, ServerProfile sp) throws Exception {
		this.sp = sp;

		URL url = sp.getURL();
		HttpHost host = new HttpHost(url.getHost(), url.getPort(), url.getProtocol());

		exec = Executor.newInstance().auth(host, sp.getUser(), sp.getUser());
		exec.authPreemptive(host);
		HttpUtils.setupProxy(exec, url.toURI());
		getServerInfo(monitor);

		return true;
	}

	private ObjectMapper mapper = FORMAT.equals("xml") ? JacksonHelper.getXMLMapper() : JacksonHelper.getJSONMapper();
	private ServerInfo serverInfo;
	private Executor exec;

	@Override
	public ServerInfo getServerInfo(IProgressMonitor monitor) throws Exception {
		if (serverInfo != null)
			return serverInfo;
		serverInfo = toObj(HttpUtils.get(url("serverInfo"), sp), ServerInfo.class, monitor);
		if (serverInfo != null) {
			dateFormat = new SimpleDateFormat(serverInfo.getDateFormatPattern());
			timestampFormat = new SimpleDateFormat(serverInfo.getDatetimeFormatPattern());
		}
		return serverInfo;
	}

	@Override
	public List<ResourceDescriptor> list(IProgressMonitor monitor, ResourceDescriptor rd) throws Exception {
		URIBuilder ub = new URIBuilder(url("resources"));
		ub.addParameter("folderUri", rd.getUriString());
		ub.addParameter("recursive", "false");
		ub.addParameter("sortBy", "label");
		ub.addParameter("limit", Integer.toString(Integer.MAX_VALUE));

		ClientResourceListWrapper resources = toObj(HttpUtils.get(ub.build().toASCIIString(), sp), ClientResourceListWrapper.class, monitor);
		List<ResourceDescriptor> rds = new ArrayList<ResourceDescriptor>();
		if (resources != null)
			for (ClientResourceLookup crl : resources.getResourceLookups())
				rds.add(Rest2Soap.getRDLookup(this, crl));
		return rds;
	}

	@Override
	public ResourceDescriptor get(IProgressMonitor monitor, ResourceDescriptor rd, File f) throws Exception {
		URIBuilder ub = new URIBuilder(url("resources" + rd.getUriString()));
		ub.addParameter("expanded", "true");
		Request req = HttpUtils.get(ub.build().toASCIIString(), sp);
		String rtype = WsTypes.INST().toRestType(rd.getWsType());
		req.setHeader("Accept", "application/repository." + rtype + "+" + FORMAT);
		ClientResource<?> crl = toObj(req, WsTypes.INST().getType(rtype), monitor);
		if (crl != null)
			return Rest2Soap.getRD(this, crl, rd);
		return null;
	}

	@Override
	public ResourceDescriptor get(IProgressMonitor monitor, ResourceDescriptor rd, File outFile, List<Argument> args) throws Exception {
		URIBuilder ub = new URIBuilder(url("resources" + rd.getUriString()));
		ub.addParameter("expanded", "true");
		Request req = HttpUtils.get(ub.build().toASCIIString(), sp);
		String rtype = WsTypes.INST().toRestType(rd.getWsType());
		req.setHeader("Accept", "application/repository." + rtype + "+" + FORMAT);
		ClientResource<?> crl = toObj(req, WsTypes.INST().getType(rtype), monitor);
		if (crl != null)
			return Rest2Soap.getRD(this, crl, rd);
		return null;
	}

	@Override
	public void move(IProgressMonitor monitor, ResourceDescriptor rd, String destFolderURI) throws Exception {
		URIBuilder ub = new URIBuilder(url("resources" + destFolderURI));
		ub.addParameter("overwrite", "true");
		ub.addParameter("createFolders", "true");
		Request req = HttpUtils.put(ub.build().toASCIIString(), sp);
		req.setHeader("Content-Location", rd.getUriString());
		String rtype = WsTypes.INST().toRestType(rd.getWsType());
		ClientResource<?> crl = toObj(req, WsTypes.INST().getType(rtype), monitor);
		if (crl != null)
			Rest2Soap.getRD(this, crl, rd);
	}

	@Override
	public ResourceDescriptor copy(IProgressMonitor monitor, ResourceDescriptor rd, String destFolderURI) throws Exception {
		URIBuilder ub = new URIBuilder(url("resources" + destFolderURI));
		ub.addParameter("overwrite", "true");
		ub.addParameter("createFolders", "true");
		Request req = HttpUtils.post(ub.build().toASCIIString(), sp);
		req.setHeader("Content-Location", rd.getUriString());
		String rtype = WsTypes.INST().toRestType(rd.getWsType());
		ClientResource<?> crl = toObj(req, WsTypes.INST().getType(rtype), monitor);
		if (crl != null)
			return Rest2Soap.getRD(this, crl, rd);
		return null;
	}

	@Override
	public ResourceDescriptor addOrModifyResource(IProgressMonitor monitor, ResourceDescriptor rd, File inputFile) throws Exception {
		URIBuilder ub = new URIBuilder(url("resources" + rd.getUriString()));
		ub.addParameter("createFolders", "true");
		ub.addParameter("overwrite", "true");
		Request req = HttpUtils.put(ub.build().toASCIIString(), sp);
		String rtype = WsTypes.INST().toRestType(rd.getWsType());
		ContentType ct = ContentType.create("application/repository." + rtype + "+" + FORMAT);
		req.bodyString(mapper.writeValueAsString(Soap2Rest.getResource(this, rd)), ct);
		ClientResource<?> crl = toObj(req, WsTypes.INST().getType(rtype), monitor);
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
		Request req = HttpUtils.delete(url("resources" + rd.getUriString()), sp);
		if (exec.execute(req).returnResponse().getStatusLine().getStatusCode() == 204)
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

	@Override
	public String getWebservicesUri() {
		return sp.getUrl();
	}

	@Override
	public String getUsername() {
		return sp.getUser();
	}

	@Override
	public String getPassword() {
		return sp.getPass();
	}

}
