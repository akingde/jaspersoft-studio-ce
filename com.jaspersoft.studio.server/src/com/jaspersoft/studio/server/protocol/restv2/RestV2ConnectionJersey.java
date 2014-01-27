package com.jaspersoft.studio.server.protocol.restv2;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpResponseException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.filter.HttpBasicAuthFilter;

import com.google.common.io.Files;
import com.jaspersoft.ireport.jasperserver.ws.FileContent;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.Argument;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.InputControlQueryDataRow;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ListItem;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.reports.ReportParameter;
import com.jaspersoft.jasperserver.dto.reports.ReportParameters;
import com.jaspersoft.jasperserver.dto.reports.inputcontrols.InputControlOption;
import com.jaspersoft.jasperserver.dto.reports.inputcontrols.InputControlState;
import com.jaspersoft.jasperserver.dto.reports.inputcontrols.ReportInputControl;
import com.jaspersoft.jasperserver.dto.reports.inputcontrols.ReportInputControlsListWrapper;
import com.jaspersoft.jasperserver.dto.resources.ClientFile;
import com.jaspersoft.jasperserver.dto.resources.ClientReportUnit;
import com.jaspersoft.jasperserver.dto.resources.ClientResource;
import com.jaspersoft.jasperserver.dto.resources.ClientResourceListWrapper;
import com.jaspersoft.jasperserver.dto.resources.ClientResourceLookup;
import com.jaspersoft.jasperserver.dto.serverinfo.ServerInfo;
import com.jaspersoft.jasperserver.jaxrs.report.InputControlStateListWrapper;
import com.jaspersoft.jasperserver.jaxrs.report.ReportExecutionRequest;
import com.jaspersoft.jasperserver.remote.services.ExportExecution;
import com.jaspersoft.jasperserver.remote.services.ReportExecution;
import com.jaspersoft.jasperserver.remote.services.ReportOutputResource;
import com.jaspersoft.studio.server.AFinderUI;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.editor.input.InputControlsManager;
import com.jaspersoft.studio.server.model.datasource.filter.DatasourcesAllFilter;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.protocol.Feature;
import com.jaspersoft.studio.server.protocol.Version;
import com.jaspersoft.studio.server.publish.PublishUtil;
import com.jaspersoft.studio.server.utils.Pass;
import com.jaspersoft.studio.utils.Misc;

public class RestV2ConnectionJersey extends ARestV2ConnectionJersey {

	@Override
	public boolean connect(IProgressMonitor monitor, ServerProfile sp) throws Exception {
		super.connect(monitor, sp);
		this.eh = new RESTv2ExceptionHandler(this);

		ClientConfig clientConfig = new ClientConfig();
		// values are in milliseconds
		// clientConfig.property(ClientProperties.READ_TIMEOUT, sp.getTimeout());
		clientConfig.property(ClientProperties.CONNECT_TIMEOUT, sp.getTimeout());
		if (sp.isChunked())
			clientConfig.property(ClientProperties.CHUNKED_ENCODING_SIZE, 1024);
		clientConfig.property(ApacheClientProperties.PREEMPTIVE_BASIC_AUTHENTICATION, true);

		// config your ssl for apache connector
		SslConfigurator sslConfig = SslConfigurator.newInstance(true);
		clientConfig.property(ApacheClientProperties.SSL_CONFIG, sslConfig);

		connector = new JSSApacheConnector(clientConfig);
		clientConfig.connector(connector);
		// connector.getHttpClient().getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
		// proxy);

		CredentialsProvider cp = (CredentialsProvider) clientConfig.getProperty(ApacheClientProperties.CREDENTIALS_PROVIDER);

		// cp.setCredentials(new AuthScope(proxy.getHost(), proxy.getPort()),
		// credentials);

		Client client = ClientBuilder.newBuilder().withConfig(clientConfig).build();
		String user = sp.getUser();
		if (!Misc.isNullOrEmpty(sp.getOrganisation()))
			user += "|" + sp.getOrganisation();
		client.register(new HttpBasicAuthFilter(user, Pass.getPass(sp.getPass())));
		target = client.target(sp.getUrl() + SUFFIX);
		getServerInfo(monitor);

		return true;
	}

	@Override
	public ServerInfo getServerInfo(IProgressMonitor monitor) throws Exception {
		if (serverInfo != null)
			return serverInfo;
		Builder req = target.path("serverInfo").request();
		serverInfo = toObj(connector.get(req, monitor), ServerInfo.class, monitor);
		if (serverInfo != null) {
			dateFormat = new SimpleDateFormat(serverInfo.getDateFormatPattern());
			timestampFormat = new SimpleDateFormat(serverInfo.getDatetimeFormatPattern());
			sp.setJrVersion(Version.setJRVersion(serverInfo));
		}
		return serverInfo;
	}

	@Override
	public List<ResourceDescriptor> list(IProgressMonitor monitor, ResourceDescriptor rd) throws Exception {
		List<ResourceDescriptor> rds = new ArrayList<ResourceDescriptor>();
		if (rd.getWsType().equals(ResourceDescriptor.TYPE_REPORTUNIT)) {
			rd = parent.get(monitor, rd, null);
			return rd.getChildren();
		} else if (rd.getWsType().equals(ResourceDescriptor.TYPE_DOMAIN_TOPICS)) {
			rd = parent.get(monitor, rd, null);
			return rd.getChildren();
		} else if (rd.getWsType().equals(ResourceDescriptor.TYPE_ADHOC_DATA_VIEW)) {
			return getInputControls(rd.getParentFolder() + "/" + rd.getName(), monitor);
		} else if (rd.getWsType().equals(ResourceDescriptor.TYPE_DASHBOARD)) {
			return getInputControls(rd.getParentFolder() + "/" + rd.getName(), monitor);
		} else if (rd.getWsType().equals(ResourceDescriptor.TYPE_REPORT_OPTIONS)) {
			return getInputControls(rd.getParentFolder() + "/" + rd.getName(), monitor);
		} else {
			WebTarget tgt = target.path("resources");
			tgt = tgt.queryParam("folderUri", rd.getUriString());
			tgt = tgt.queryParam("recursive", "false");
			tgt = tgt.queryParam("sortBy", "label");
			tgt = tgt.queryParam("limit", Integer.toString(Integer.MAX_VALUE));

			Builder req = tgt.request();
			ClientResourceListWrapper resources = toObj(connector.get(req, monitor), ClientResourceListWrapper.class, monitor);
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
						rds.add(parent.get(monitor, pub, null));
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
		String uri = rd.getUriString();
		if (!uri.startsWith("/"))
			uri = "/" + uri;
		WebTarget tgt = target.path("resources" + uri.replaceAll("repo:", ""));
		tgt = tgt.queryParam("expanded", "true");

		String rtype = WsTypes.INST().toRestType(rd.getWsType());
		Builder req = tgt.request("application/repository." + rtype + "+" + FORMAT);
		ClientResource<?> crl = toObj(connector.get(req, monitor), WsTypes.INST().getType(rtype), monitor);
		if (crl != null) {
			if (f != null && crl instanceof ClientFile) {
				ClientFile cf = (ClientFile) crl;
				tgt = target.path("resources" + uri);

				req = tgt.request(cf.getType().getMimeType()).header("Accept", cf.getType().getMimeType());
				readFile(connector.get(req, monitor), f, monitor);
			}
			return Rest2Soap.getRD(this, crl, rd);
		}
		return null;
	}

	@Override
	public ResourceDescriptor move(IProgressMonitor monitor, ResourceDescriptor rd, String destFolderURI) throws Exception {
		String rtype = WsTypes.INST().toRestType(rd.getWsType());

		WebTarget tgt = target.path("resources" + destFolderURI);
		tgt = tgt.queryParam("overwrite", "true");
		tgt = tgt.queryParam("createFolders", "true");

		Builder req = tgt.request().header("Content-Location", rd.getUriString());
		Response r = connector.put(req, Entity.entity("", MediaType.APPLICATION_XML_TYPE), monitor);
		ClientResource<?> crl = toObj(r, WsTypes.INST().getType(rtype), monitor);
		if (crl != null)
			return doGet(monitor, rd, crl);
		return null;
	}

	@Override
	public ResourceDescriptor copy(IProgressMonitor monitor, ResourceDescriptor rd, String destFolderURI) throws Exception {
		String rtype = WsTypes.INST().toRestType(rd.getWsType());

		WebTarget tgt = target.path("resources" + destFolderURI);
		tgt = tgt.queryParam("overwrite", "true");
		tgt = tgt.queryParam("createFolders", "true");

		Builder req = tgt.request().header("Content-Location", rd.getUriString());
		Response r = connector.post(req, Entity.entity("", MediaType.APPLICATION_XML_TYPE), monitor);
		ClientResource<?> crl = toObj(r, WsTypes.INST().getType(rtype), monitor);
		if (crl != null)
			return doGet(monitor, rd, crl);
		return null;
	}

	@Override
	public ResourceDescriptor addOrModifyResource(IProgressMonitor monitor, ResourceDescriptor rd, File inFile) throws Exception {
		String rtype = WsTypes.INST().toRestType(rd.getWsType());
		ClientResource<?> cr = Soap2Rest.getResource(this, rd);
		if (inFile != null) {
			if (cr instanceof ClientFile) {
				ClientFile crf = (ClientFile) cr;
				crf.setType(WsTypes.getFileType(crf.getType(), Files.getFileExtension(inFile.getName())));
				// crf.setContent(Base64.encodeBase64String(FileUtils.getBytes(inFile)));
			} else if (cr instanceof ClientReportUnit) {
				// ClientReferenceableFile mainjrxml = ((ClientReportUnit)
				// cr).getJrxml();
				// if (mainjrxml instanceof ClientFile)
				// ((ClientFile)
				// mainjrxml).setContent(Base64.encodeBase64String(FileUtils.getBytes(inFile)));
			}
		}

		WebTarget tgt = target.path("resources" + rd.getUriString());
		tgt = tgt.queryParam("createFolders", "true");
		tgt = tgt.queryParam("overwrite", "true");

		Builder req = tgt.request();
		Response r = connector.put(req, Entity.entity(cr, "application/repository." + rtype + "+" + FORMAT), monitor);
		ClientResource<?> crl = null;
		try {
			crl = toObj(r, WsTypes.INST().getType(rtype), monitor);
		} catch (HttpResponseException e) {
			if (e.getStatusCode() == 409) {
				rd.setVersion(parent.get(monitor, rd, null).getVersion());
				return addOrModifyResource(monitor, rd, inFile);
			} else
				throw e;
		}
		if (crl != null && !monitor.isCanceled())
			rd = doGet(monitor, rd, crl);

		return rd;
	}

	private ResourceDescriptor doGet(IProgressMonitor monitor, ResourceDescriptor rd, ClientResource<?> crl) throws Exception, ParseException {
		boolean refresh = false;
		if (WsTypes.INST().isContainerType(crl.getClass()))
			refresh = true;
		if (refresh && !monitor.isCanceled())
			rd = parent.get(monitor, rd, null);
		else
			rd = Rest2Soap.getRD(this, crl, rd);
		return rd;
	}

	@Override
	public ResourceDescriptor modifyReportUnitResource(IProgressMonitor monitor, ResourceDescriptor runit, ResourceDescriptor rd, File inFile) throws Exception {
		runit = parent.get(monitor, runit, null);
		PublishUtil.setChild(runit, rd);
		return addOrModifyResource(monitor, runit, inFile);
	}

	@Override
	public void delete(IProgressMonitor monitor, ResourceDescriptor rd) throws Exception {
		WebTarget tgt = target.path("resources" + rd.getUriString());
		Response res = connector.delete(tgt.request(), monitor);
		try {
			switch (res.getStatus()) {
			case 204:
				System.out.println("Deleted");
				break;
			default:
				eh.handleException(res, monitor);
			}
		} finally {
			res.close();
		}
	}

	@Override
	public void delete(IProgressMonitor monitor, ResourceDescriptor rd, ResourceDescriptor runit) throws Exception {
		ResourceDescriptor rdrem = null;
		for (ResourceDescriptor r : runit.getChildren())
			if (r.getUriString().equals(rd.getUriString())) {
				rdrem = r;
				break;
			}
		if (rdrem != null) {
			runit.getChildren().remove(rdrem);
			addOrModifyResource(monitor, runit, null);
		}
	}

	@Override
	public Map<String, FileContent> runReport(IProgressMonitor monitor, ResourceDescriptor rd, Map<String, Object> prm, List<Argument> args) throws Exception {
		Map<String, FileContent> map = new HashMap<String, FileContent>();

		ReportExecutionRequest rer = new ReportExecutionRequest();
		rer.setReportUnitUri(rd.getUriString());
		rer.setAsync(false);
		rer.setFreshData(true);
		rer.setIgnorePagination(false);
		rer.setInteractive(false);
		String ouput = Argument.RUN_OUTPUT_FORMAT_JRPRINT;
		rer.setOutputFormat(ouput);
		// rer.setTransformerKey(transformerKey);
		rer.setSaveDataSnapshot(false);
		if (prm != null && !prm.isEmpty()) {
			List<ReportParameter> list = new ArrayList<ReportParameter>();
			for (String key : prm.keySet()) {
				ReportParameter rprm = new ReportParameter();
				rprm.setName(key);
				Object item = prm.get(key);
				List<String> vals = new ArrayList<String>();
				if (item instanceof Collection<?>) {
					Collection<?> c = (Collection<?>) item;
					for (Object obj : c)
						vals.add(toRestString(obj));
				} else
					vals.add(toRestString(item));
				rprm.setValues(vals);
				list.add(rprm);
			}
			rer.setParameters(new ReportParameters(list));
		}
		WebTarget tgt = target.path("reportExecutions");

		Builder req = tgt.request();
		Response r = connector.post(req, Entity.entity(rer, MediaType.APPLICATION_XML_TYPE), monitor);
		ReportExecution res = toObj(r, ReportExecution.class, monitor);
		if (res != null && res.getErrorDescriptor() == null) {
			if (res.getExportsSet() != null) {
				int i = 0;
				for (ExportExecution ee : res.getExportsSet()) {
					tgt = target.path("reportExecutions/" + res.getRequestId() + "/exports/" + ee.getId() + "/outputResource");
					req = tgt.request(ee.getOutputResource().getContentType());
					byte[] d = readFile(connector.get(req, monitor), monitor);
					addFileContent(d, map, ee.getOutputResource(), "attachment-" + i++, "jasperPrint");
					if (ee.getAttachmentsSet() != null)
						for (ReportOutputResource ror : ee.getAttachmentsSet()) {
							tgt = target.path("reportExecutions/" + res.getRequestId() + "/exports/" + ee.getId() + "/attachments/" + ror.getFileName());
							req = tgt.request(ror.getContentType());
							d = readFile(connector.get(req, monitor), monitor);
							addFileContent(d, map, ror, "attachment-" + i++, "attachment-" + i++);
						}
				}
			}
		}
		return map;
	}

	private void addFileContent(byte[] d, Map<String, FileContent> map, ReportOutputResource r, String id, String key) {
		if (d != null) {
			FileContent content = new FileContent();
			content.setData(d);
			content.setMimeType(r.getContentType());
			content.setName(Misc.nvl(r.getFileName(), id));

			map.put(key, content);
		}
	}

	@Override
	public List<ResourceDescriptor> listDatasources(IProgressMonitor monitor) throws Exception {
		List<ResourceDescriptor> rds = new ArrayList<ResourceDescriptor>();
		WebTarget tgt = target.path("resources");
		for (String type : DatasourcesAllFilter.getTypes())
			tgt = tgt.queryParam("type", type);
		tgt = tgt.queryParam("sortBy", "label");
		tgt = tgt.queryParam("limit", Integer.toString(Integer.MAX_VALUE));

		Builder req = tgt.request();
		ClientResourceListWrapper resources = toObj(connector.get(req, monitor), ClientResourceListWrapper.class, monitor);
		if (resources != null)
			for (ClientResourceLookup crl : resources.getResourceLookups())
				rds.add(Rest2Soap.getRDLookup(this, crl));
		return rds;
	}

	@Override
	public void findResources(IProgressMonitor monitor, AFinderUI callback) throws Exception {
		connector.closeLastRequest();
		if (callback.getText() == null) {
			callback.showResults(null);
			return;
		}
		WebTarget tgt = target.path("resources");
		tgt = tgt.queryParam("q", callback.getText());
		for (String type : callback.getTypes())
			tgt = tgt.queryParam("type", type);
		tgt = tgt.queryParam("sortBy", "label");
		tgt = tgt.queryParam("limit", Integer.toString(Integer.MAX_VALUE));

		Builder req = tgt.request();
		ClientResourceListWrapper resources = toObj(connector.get(req, monitor), ClientResourceListWrapper.class, monitor);
		callback.showResults(resources != null ? resources.getResourceLookups() : null);
	}

	@Override
	public boolean isSupported(Feature f) {
		switch (f) {
		case SEARCHREPOSITORY:
		case UPDATEDATE:
		case TIMEZONE:
		case PERMISSION:
		case DATASOURCENAME:
		case INPUTCONTROLS_ORDERING:
		case MAXLENGHT:
			return true;
		}
		return super.isSupported(f);
	}

	@Override
	public void getBundle(Map<String, String> map, String name, IProgressMonitor monitor) {
		Builder req = target.path("bundles/" + name).request(MediaType.APPLICATION_JSON_TYPE);
		try {
			GenericType<Map<String, String>> type = new GenericType<Map<String, String>>() {
			};
			Map<String, String> m = toObj(connector.get(req, monitor), type, monitor);
			if (m != null)
				map.putAll(m);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ResourceDescriptor> getInputControls(String uri, IProgressMonitor monitor) throws Exception {
		List<ResourceDescriptor> rds = new ArrayList<ResourceDescriptor>();
		Builder req = target.path("reports/" + uri.replaceFirst("/", "") + "/inputControls").request();
		try {
			ReportInputControlsListWrapper m = toObj(connector.get(req, monitor), ReportInputControlsListWrapper.class, monitor);
			if (m != null) {
				for (ReportInputControl ric : m.getInputParameters())
					rds.add(Rest2Soap.getInputControl(this, ric, new ResourceDescriptor()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rds;
	}

	@Override
	public void reorderInputControls(String uri, List<ResourceDescriptor> rds, IProgressMonitor monitor) throws Exception {
		Builder req = target.path("reports" + uri + "/inputControls").request();
		Response res = connector.get(req, monitor);
		ReportInputControlsListWrapper crl = toObj(res, ReportInputControlsListWrapper.class, monitor);
		if (crl != null) {
			List<ReportInputControl> ics = new ArrayList<ReportInputControl>();
			for (ResourceDescriptor r : rds) {
				for (ReportInputControl ric : crl.getInputParameters()) {
					if (r.getName().equals(ric.getId()))
						ics.add(ric);
				}
			}
			ReportInputControlsListWrapper wrapper = new ReportInputControlsListWrapper(ics);

			req = target.path("reports" + uri + "/inputControls").request();
			Response r = connector.put(req, Entity.entity(wrapper, MediaType.APPLICATION_XML_TYPE), monitor);
			toObj(r, ReportInputControlsListWrapper.class, monitor);
		}
	}

	@Override
	public ResourceDescriptor initInputControls(String uri, IProgressMonitor monitor) throws Exception {
		uri = WSClientHelper.getReportUnitUri(uri);
		ResourceDescriptor rdunit = new ResourceDescriptor();
		rdunit.setUriString(uri);
		rdunit.setWsType(ResourceDescriptor.TYPE_REPORTUNIT);
		rdunit = parent.get(monitor, rdunit, null);
		if (monitor.isCanceled())
			return rdunit;
		Builder req = target.path("reports" + uri + "/inputControls").request();
		Response r = connector.get(req, monitor);
		ReportInputControlsListWrapper crl = toObj(r, ReportInputControlsListWrapper.class, monitor);
		if (crl != null)
			for (ResourceDescriptor rd : rdunit.getChildren()) {
				if (rd.getWsType().equals(ResourceDescriptor.TYPE_INPUT_CONTROL)) {
					for (ReportInputControl ric : crl.getInputParameters()) {
						InputControlState ics = ric.getState();
						rd.setMasterInputControls(ric.getMasterDependencies());
						if (ics.getId().equals(rd.getName())) {
							setInputControlState(rd, ics);
							break;
						}
					}
				}
			}
		return rdunit;
	}

	private void setInputControlState(ResourceDescriptor rd, InputControlState ics) {
		if (ics.getValue() != null)
			rd.setValue(ics.getValue());
		else if (ics.getOptions() != null) {
			if (InputControlsManager.isICQuery(rd)) {
				List<InputControlQueryDataRow> qvalues = new ArrayList<InputControlQueryDataRow>();
				for (InputControlOption ico : ics.getOptions()) {
					InputControlQueryDataRow dr = new InputControlQueryDataRow();
					dr.setValue(ico.getValue());
					List<String> cols = new ArrayList<String>();
					StringTokenizer st = new StringTokenizer(ico.getLabel(), " | ");
					while (st.hasMoreElements())
						cols.add(st.nextToken());
					dr.setColumnValues(cols);
					dr.setSelected(ico.isSelected());
					qvalues.add(dr);
				}
				rd.setQueryData(qvalues);
			} else if (InputControlsManager.isICListOfValues(rd)) {
				List<ListItem> qvalues = new ArrayList<ListItem>();
				for (InputControlOption ico : ics.getOptions()) {
					ListItem dr = new ListItem();
					dr.setValue(ico.getValue());
					dr.setLabel(ico.getLabel());
					dr.setSelected(ico.isSelected());
					qvalues.add(dr);
				}
				rd.setListOfValues(qvalues);
			}
		}
	}

	@Override
	public List<ResourceDescriptor> cascadeInputControls(ResourceDescriptor runit, List<ResourceDescriptor> ics, IProgressMonitor monitor) throws Exception {
		if (ics.isEmpty())
			return ics;
		String ctrls = "";
		String del = "";
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<ReportParameter> lrp = new ArrayList<ReportParameter>();
		for (ResourceDescriptor rd : ics) {
			Map<String, Object> icMap = rd.getIcValues();
			for (String key : icMap.keySet())
				setMap(key, map, icMap.get(key));
			ctrls += del + rd.getName();
			del = ";";
		}
		for (String key : map.keySet()) {
			ReportParameter r = new ReportParameter();
			r.setName(key);
			r.setValues(map.get(key));
			lrp.add(r);
		}
		Builder req = target.path("reports" + runit.getUriString() + "/inputControls/" + ctrls + "/values").request();
		Response r = connector.post(req, Entity.entity(new ReportParameters(lrp), MediaType.APPLICATION_XML_TYPE), monitor);
		InputControlStateListWrapper crl = toObj(r, InputControlStateListWrapper.class, monitor);
		if (crl != null) {
			for (ResourceDescriptor rd : ics) {
				if (rd.getWsType().equals(ResourceDescriptor.TYPE_INPUT_CONTROL))
					for (InputControlState state : crl.getInputControlStateList()) {
						if (state.getId().equals(rd.getName())) {
							setInputControlState(rd, state);
							break;
						}
					}
			}
		}
		return ics;
	}

	private void setMap(String key, Map<String, List<String>> map, Object value) {
		List<String> vals = map.get(key);
		if (vals == null) {
			vals = new ArrayList<String>();
			map.put(key, vals);
		}
		if (value instanceof Collection) {
			for (Object obj : (Collection<?>) value) {
				String str = obj.toString();
				if (!vals.contains(str))
					vals.add(str);
			}
		} else {
			String str = value.toString();
			if (!vals.contains(str))
				vals.add(str);
		}
	}
}
