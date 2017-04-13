/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.protocol.restv2;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.KeyStore;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpResponseException;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.BrowserCompatHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.eclipse.core.runtime.IProgressMonitor;
import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.RequestEntityProcessing;
import org.glassfish.jersey.client.spi.Connector;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.Boundary;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import com.jaspersoft.ireport.jasperserver.ws.FileContent;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.Argument;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.InputControlQueryDataRow;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ListItem;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceProperty;
import com.jaspersoft.jasperserver.dto.authority.ClientUser;
import com.jaspersoft.jasperserver.dto.jdbcdrivers.JdbcDriverInfo;
import com.jaspersoft.jasperserver.dto.permissions.RepositoryPermission;
import com.jaspersoft.jasperserver.dto.permissions.RepositoryPermissionListWrapper;
import com.jaspersoft.jasperserver.dto.reports.ReportParameter;
import com.jaspersoft.jasperserver.dto.reports.ReportParameters;
import com.jaspersoft.jasperserver.dto.reports.inputcontrols.InputControlOption;
import com.jaspersoft.jasperserver.dto.reports.inputcontrols.InputControlState;
import com.jaspersoft.jasperserver.dto.reports.inputcontrols.ReportInputControl;
import com.jaspersoft.jasperserver.dto.reports.inputcontrols.ReportInputControlsListWrapper;
import com.jaspersoft.jasperserver.dto.resources.AbstractClientReportUnit;
import com.jaspersoft.jasperserver.dto.resources.ClientFile;
import com.jaspersoft.jasperserver.dto.resources.ClientFile.FileType;
import com.jaspersoft.jasperserver.dto.resources.ClientReferenceableFile;
import com.jaspersoft.jasperserver.dto.resources.ClientReportUnit;
import com.jaspersoft.jasperserver.dto.resources.ClientResource;
import com.jaspersoft.jasperserver.dto.resources.ClientResourceListWrapper;
import com.jaspersoft.jasperserver.dto.resources.ClientResourceLookup;
import com.jaspersoft.jasperserver.dto.serverinfo.ServerInfo;
import com.jaspersoft.jasperserver.jaxrs.client.dto.importexport.ExportTaskDto;
import com.jaspersoft.jasperserver.jaxrs.client.dto.importexport.StateDto;
import com.jaspersoft.jasperserver.jaxrs.client.dto.reports.AttachmentDescriptor;
import com.jaspersoft.jasperserver.jaxrs.client.dto.reports.ExportDescriptor;
import com.jaspersoft.jasperserver.jaxrs.client.dto.reports.OutputResourceDescriptor;
import com.jaspersoft.jasperserver.jaxrs.client.dto.reports.ReportExecutionDescriptor;
import com.jaspersoft.jasperserver.jaxrs.report.InputControlStateListWrapper;
import com.jaspersoft.jasperserver.jaxrs.report.ReportExecutionRequest;
import com.jaspersoft.studio.server.AFinderUI;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.editor.input.InputControlsManager;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.datasource.filter.DatasourcesAllFilter;
import com.jaspersoft.studio.server.model.datasource.filter.IDatasourceFilter;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.protocol.Feature;
import com.jaspersoft.studio.server.protocol.JSSTrustStrategy;
import com.jaspersoft.studio.server.protocol.JdbcDriver;
import com.jaspersoft.studio.server.protocol.ReportExecution;
import com.jaspersoft.studio.server.protocol.Version;
import com.jaspersoft.studio.server.publish.PublishUtil;
import com.jaspersoft.studio.server.utils.HttpUtils;
import com.jaspersoft.studio.server.utils.ResourceDescriptorUtil;
import com.jaspersoft.studio.server.wizard.exp.ExportOptions;
import com.jaspersoft.studio.server.wizard.imp.ImportOptions;
import com.jaspersoft.studio.server.wizard.permission.PermissionOptions;
import com.jaspersoft.studio.utils.Misc;

import net.sf.jasperreports.eclipse.util.FileExtension;

public class RestV2ConnectionJersey extends ARestV2ConnectionJersey {

	private CookieStore cookieStore;

	@Override
	public boolean connect(IProgressMonitor monitor, ServerProfile sp) throws Exception {
		monitor.subTask(Messages.RestV2ConnectionJersey_0);
		super.connect(monitor, sp);
		this.eh = new RESTv2ExceptionHandler(this);

		ClientConfig clientConfig = new ClientConfig();
		// values are in milliseconds
		// clientConfig.property(ClientProperties.READ_TIMEOUT,
		// sp.getTimeout());
		clientConfig.property(ClientProperties.CONNECT_TIMEOUT, sp.getTimeout());
		if (sp.isChunked())
			clientConfig.property(ClientProperties.CHUNKED_ENCODING_SIZE, 1024);
		else
			clientConfig.property(ClientProperties.CHUNKED_ENCODING_SIZE, null);
		clientConfig.property(ApacheClientProperties.PREEMPTIVE_BASIC_AUTHENTICATION, true);

		// config your ssl for apache connector
		SslConfigurator sslConfig = SslConfigurator.newInstance(true);
		clientConfig.property(ApacheClientProperties.SSL_CONFIG, sslConfig);

		SSLContextBuilder builder = SSLContexts.custom();

		final KeyStore trustStore = CertChainValidator.getDefaultTrustStore();

		builder.loadTrustMaterial(trustStore, new JSSTrustStrategy(trustStore));
		SSLContext sslContext = builder.build();

		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,
				new BrowserCompatHostnameVerifier()) {
			@Override
			protected void prepareSocket(SSLSocket socket) throws IOException {
				super.prepareSocket(socket);
				socket.setEnabledProtocols(new String[] { "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2" });
			}
		};

		Registry<ConnectionSocketFactory> ssr = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("https", sslsf).register("http", new PlainConnectionSocketFactory()).build();

		PoolingHttpClientConnectionManager cxMgr = new PoolingHttpClientConnectionManager(ssr);
		cxMgr.setMaxTotal(50);
		cxMgr.setDefaultMaxPerRoute(20);
		clientConfig.property(ApacheClientProperties.CONNECTION_MANAGER, cxMgr);

		// MultiThreadedHttpConnectionManager connectionManager = new
		// MultiThreadedHttpConnectionManager();
		// connectionManager.getParams().setDefaultMaxConnectionsPerHost(20);
		// connectionManager.getParams().setMaxTotalConnections(20);
		// clientConfig.property(ApacheClientProperties.CONNECTION_MANAGER,
		// connectionManager);

		connector = new JSSApacheConnectorFactory();
		clientConfig.connectorProvider(connector);

		// clientConfig.connector(connector);
		HttpUtils.setupProxy(clientConfig, HttpUtils.toSafeUri(sp.getURL()));

		clientConfig.property(ClientProperties.REQUEST_ENTITY_PROCESSING, RequestEntityProcessing.BUFFERED);
		clientConfig.register(JacksonFeature.class).register(ClientQueryMapperProvider.class);

		client = ClientBuilder.newBuilder().withConfig(clientConfig).build();
		client.register(MultiPartFeature.class);

		if (sp.isLogging()) {
			logger = java.util.logging.Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
			// FileHandler fh = new FileHandler(home + File.separator +
			// "jssjrs.log", true);
			// fh.setFormatter(new SimpleFormatter());
			// logger.addHandler(fh);
			logger.setLevel(Level.FINEST);
			client.register(new org.glassfish.jersey.filter.LoggingFilter(logger, true));
		}
		// client.register(JacksonFeature.class);
		// String user = sp.getUser();
		// if (!Misc.isNullOrEmpty(sp.getOrganisation()))
		// user += "|" + sp.getOrganisation();
		// client.register(new HttpBasicAuthFilter(user,
		// Pass.getPass(sp.getPass())));
		String url = sp.getUrl().trim();
		if (url.endsWith("/services/repository/")) //$NON-NLS-1$
			url = url.substring(0, url.lastIndexOf("/services/repository/")); //$NON-NLS-1$
		else if (url.endsWith("services/repository")) //$NON-NLS-1$
			url = url.substring(0, url.lastIndexOf("/services/repository")); //$NON-NLS-1$
		if (!url.endsWith("/")) //$NON-NLS-1$
			url += "/"; //$NON-NLS-1$
		try {
			target = client.target(url + "j_spring_security_check"); //$NON-NLS-1$
			target = target.queryParam("forceDefaultRedirect", "false"); //$NON-NLS-1$ //$NON-NLS-2$
			if (sp.isUseSSO()) {
				String token = CASUtil.getToken(sp, monitor);
				target = target.queryParam("ticket", token); //$NON-NLS-1$
			} else {
				target = target.queryParam("j_username", sp.getUser()); //$NON-NLS-1$
				target = target.queryParam("j_password", parent.getPassword(monitor)); //$NON-NLS-1$
				if (monitor.isCanceled())
					return false;
			}
			target = target.queryParam("orgId", sp.getOrganisation()); //$NON-NLS-1$
			if (!Misc.isNullOrEmpty(sp.getLocale()))
				target = target.queryParam("userLocale", Locale.getDefault().toString()); //$NON-NLS-1$ //$NON-NLS-2$
			if (!Misc.isNullOrEmpty(sp.getTimeZone()))
				target = target.queryParam("userTimezone", TimeZone.getDefault().getID()); //$NON-NLS-1$ //$NON-NLS-2$

			Builder req = target.request();
			toObj(connector.get(req, monitor), String.class, monitor);
		} catch (Exception e) {
			if (logger != null)
				logger.log(Level.SEVERE, e.getMessage(), e);
			throw e;
		} finally {
			// ok, now check others
			target = client.target(url + SUFFIX);
		}
		getServerInfo(monitor);
		try {
			getServerProfile().setClientUser(null);
			getServerProfile().setClientUser(getUser(monitor));
		} catch (HttpResponseException e) {
			if (!(e.getMessage().contains("Access") || e.getMessage().contains("Forbidden")
					|| e.getMessage().contains("resource.not.found")))
				throw e;
		} catch (Exception e) {
			Activator.getDefault().logError(e);
		}
		return serverInfo != null && serverInfo.getVersion().compareTo("5.5") >= 0; //$NON-NLS-1$
	}

	@Override
	public ServerInfo getServerInfo(IProgressMonitor monitor) throws Exception {
		if (serverInfo != null)
			return serverInfo;
		Builder req = target.path("serverInfo").request(); //$NON-NLS-1$
		serverInfo = toObj(connector.get(req, monitor), ServerInfo.class, monitor);
		if (serverInfo != null) {
			// serverInfo
			// .setTimeFormatPattern(((SimpleDateFormat) getTimeFormat())
			// .toPattern());
			dateFormat = new SimpleDateFormat(serverInfo.getDateFormatPattern());
			timestampFormat = new SimpleDateFormat(serverInfo.getDatetimeFormatPattern());
			if (sp.getJrVersion().equals("last")) //$NON-NLS-1$
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
			try {
				return getInputControls(rd.getParentFolder() + "/" + rd.getName(), monitor); //$NON-NLS-1$
			} catch (HttpResponseException e) {
				if (e.getStatusCode() == 403)
					return new ArrayList<ResourceDescriptor>();
				throw e;
			}
		} else if (rd.getWsType().equals(ResourceDescriptor.TYPE_DASHBOARD)) {
			try {
				return getInputControls(rd.getParentFolder() + "/" + rd.getName(), monitor); //$NON-NLS-1$
			} catch (HttpResponseException e) {
				if (e.getStatusCode() == 403)
					return new ArrayList<ResourceDescriptor>();
				throw e;
			}
		} else if (rd.getWsType().equals(ResourceDescriptor.TYPE_REPORT_OPTIONS)) {
			try {
				return getInputControls(rd.getParentFolder() + "/" + rd.getName(), monitor); //$NON-NLS-1$
			} catch (HttpResponseException e) {
				if (e.getStatusCode() == 403)
					return new ArrayList<ResourceDescriptor>();
				throw e;
			}
		} else {
			WebTarget tgt = target.path("resources"); //$NON-NLS-1$
			tgt = tgt.queryParam("folderUri", rd.getUriString()); //$NON-NLS-1$
			tgt = tgt.queryParam("recursive", "false"); //$NON-NLS-1$ //$NON-NLS-2$
			tgt = tgt.queryParam("sortBy", "label"); //$NON-NLS-1$ //$NON-NLS-2$
			// tgt = tgt.queryParam("type",
			// WsTypes.INST().toRestType(rd.getWsType()));
			tgt = tgt.queryParam("limit", 0); //$NON-NLS-1$

			Builder req = tgt.request();
			ClientResourceListWrapper resources = toObj(connector.get(req, monitor), ClientResourceListWrapper.class,
					monitor);
			if (resources != null) {
				boolean isPublic = false;
				for (ClientResourceLookup crl : resources.getResourceLookups()) {
					if (!isPublic)
						isPublic = crl.getUri().equals("/public"); //$NON-NLS-1$
					ResourceDescriptor nrd = Rest2Soap.getRDLookup(this, crl);
					rds.add(nrd);
					if (nrd.getWsType().equals(ResourceDescriptor.TYPE_CONTENT_RESOURCE)) {
						String name = nrd.getUriString().toLowerCase();
						if (FileExtension.isImage(name))
							nrd.setWsType(ResourceDescriptor.TYPE_IMAGE);
						if (FileExtension.isFont(name))
							nrd.setWsType(ResourceDescriptor.TYPE_FONT);
						else if (name.endsWith(".xml")) //$NON-NLS-1$
							nrd.setWsType(ResourceDescriptor.TYPE_XML_FILE);
						else if (name.endsWith(FileExtension.PointJRXML))
							nrd.setWsType(ResourceDescriptor.TYPE_JRXML);
						else if (name.endsWith(".jar")) //$NON-NLS-1$
							nrd.setWsType("." + ResourceDescriptor.TYPE_CLASS_JAR); //$NON-NLS-1$
						else if (name.endsWith(FileExtension.PointJRTX))
							nrd.setWsType(ResourceDescriptor.TYPE_STYLE_TEMPLATE);
						else if (name.endsWith("." + ResourceDescriptor.TYPE_CSS_FILE)) //$NON-NLS-1$
							nrd.setWsType(ResourceDescriptor.TYPE_CSS_FILE);
						else if (name.endsWith(".js")) //$NON-NLS-1$
							nrd.setWsType(ResourceDescriptor.TYPE_CONTENT_RESOURCE);
						else if (name.endsWith(".properties")) //$NON-NLS-1$
							nrd.setWsType(ResourceDescriptor.TYPE_RESOURCE_BUNDLE);
					}
				}
				// workaround
				if (rd.getUriString().equals("/") && !isPublic) { //$NON-NLS-1$
					try {
						ResourceDescriptor pub = new ResourceDescriptor();
						pub.setUriString("/public"); //$NON-NLS-1$
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
		if (rd.getUriString() == null || rd.getUriString().contains("<")) //$NON-NLS-1$
			throw new Exception("wrong url"); //$NON-NLS-1$
		String uri = rd.getUriString();
		if (uri.startsWith("repo://")) //$NON-NLS-1$
			uri = uri.substring(5);
		if (!uri.startsWith("/")) //$NON-NLS-1$
			uri = "/" + uri; //$NON-NLS-1$

		WebTarget tgt = target.path("resources" + uri); //$NON-NLS-1$
		Boolean b = rd.getResourcePropertyValueAsBoolean("expand"); //$NON-NLS-1$
		if (b != null && !b)
			tgt = tgt.queryParam("expanded", "false"); //$NON-NLS-1$ //$NON-NLS-2$
		else
			tgt = tgt.queryParam("expanded", "true"); //$NON-NLS-1$ //$NON-NLS-2$

		String rtype = WsTypes.INST().toRestType(rd.getWsType());
		Builder req = null;
		if (rtype == null)
			req = tgt.request("application/repository.file+" + FORMAT); //$NON-NLS-1$
		else
			req = tgt.request("application/repository." + rtype + "+" + FORMAT); //$NON-NLS-1$ //$NON-NLS-2$
		Object obj = toObj(connector.get(req, monitor), WsTypes.INST().getType(rtype), monitor);
		if (obj instanceof ClientResource<?>) {
			ClientResource<?> crl = (ClientResource<?>) obj;
			if (rd.getWsType() == null)
				rd.setWsType(WsTypes.INST().getRestType(crl));
			if (crl != null) {
				if (f != null && crl instanceof ClientFile) {
					ClientFile cf = (ClientFile) crl;
					tgt = target.path("resources" + uri); //$NON-NLS-1$
					try {
						req = tgt.request(cf.getType().getMimeType()).header("Accept", cf.getType().getMimeType()); //$NON-NLS-1$
						readFile(connector.get(req, monitor), f, monitor);
					} catch (HttpResponseException e) {
						if (e.getStatusCode() == 500)
							;// jrs 5.5 returns 500 if file is not existing, a
								// bug
						// for newer versions, we should show the error
					}
				}
				rd = Rest2Soap.getRD(this, crl);
				if (crl instanceof ClientFile)
					setMainReportFlag(monitor, rd, (ClientFile) crl);
				return rd;
			}
		} else if (obj instanceof String) {
			rd.setData(((String) obj).getBytes());
			rd.setIsReference(false);
			rd.setHasData(true);
			return rd;
		}
		return null;
	}

	private void setMainReportFlag(IProgressMonitor monitor, ResourceDescriptor rd, ClientFile cf)
			throws IOException, Exception {
		if (monitor.isCanceled())
			return;
		if (cf.getType().equals(FileType.jrxml)) {
			String uri = rd.getParentFolder();
			if (rd.getParentFolder().endsWith("_files")) //$NON-NLS-1$
				uri = uri.substring(0, uri.length() - "_files".length()); //$NON-NLS-1$
			WebTarget tgt = target.path("resources" + uri); //$NON-NLS-1$
			tgt = tgt.queryParam("expanded", "false"); //$NON-NLS-1$ //$NON-NLS-2$

			Builder req = tgt.request("application/repository.file+" + FORMAT); //$NON-NLS-1$
			Object obj = toObj(connector.get(req, monitor), (Class<?>) null, monitor);
			if (obj instanceof AbstractClientReportUnit)
				rd.setMainReport(((AbstractClientReportUnit<?>) obj).getJrxml().getUri().equals(cf.getUri()));
		}
	}

	@Override
	public ResourceDescriptor move(IProgressMonitor monitor, ResourceDescriptor rd, String destFolderURI)
			throws Exception {
		String wsType = rd.getWsType();
		String rtype = WsTypes.INST().toRestType(wsType);

		WebTarget tgt = target.path("resources" + destFolderURI); //$NON-NLS-1$
		tgt = tgt.queryParam("overwrite", "true"); //$NON-NLS-1$ //$NON-NLS-2$
		tgt = tgt.queryParam("createFolders", "true"); //$NON-NLS-1$ //$NON-NLS-2$

		Builder req = tgt.request().header("Content-Location", rd.getUriString());// .header("Content-Length", //$NON-NLS-1$
		// "0");
		Response r = connector.put(req, Entity.entity("", MediaType.APPLICATION_XML_TYPE), monitor); //$NON-NLS-1$
		ClientResource<?> crl = toObj(r, WsTypes.INST().getType(rtype), monitor);
		if (crl != null) {
			rd = new ResourceDescriptor();
			rd.setWsType(wsType);
			rd.setUriString(crl.getUri());
			return doGet(monitor, rd, crl);
		}
		return null;
	}

	@Override
	public ResourceDescriptor copy(IProgressMonitor monitor, ResourceDescriptor rd, String destFolderURI)
			throws Exception {
		if (rd.getParentFolder().equals(destFolderURI))
			return rd;
		String wsType = rd.getWsType();
		String rtype = WsTypes.INST().toRestType(wsType);

		WebTarget tgt = target.path("resources" + destFolderURI); //$NON-NLS-1$
		tgt = tgt.queryParam("overwrite", "true"); //$NON-NLS-1$ //$NON-NLS-2$
		tgt = tgt.queryParam("createFolders", "true"); //$NON-NLS-1$ //$NON-NLS-2$

		Builder req = tgt.request().header("Content-Location", rd.getUriString()); //$NON-NLS-1$
		Response r = connector.post(req, Entity.entity("", MediaType.APPLICATION_XML_TYPE), monitor); //$NON-NLS-1$
		ClientResource<?> crl = toObj(r, WsTypes.INST().getType(rtype), monitor);
		if (crl != null) {
			rd = new ResourceDescriptor();
			rd.setWsType(wsType);
			rd.setUriString(crl.getUri());
			return doGet(monitor, rd, crl);
		}
		return null;
	}

	private void fillFiles(ClientFile cf, IProgressMonitor monitor) throws IOException, Exception {
		if (cf.getContent() == null) {
			WebTarget tgt = target.path("resources" + cf.getUri()); //$NON-NLS-1$
			try {
				Builder req = tgt.request(cf.getType().getMimeType()).header("Accept", //$NON-NLS-1$
						cf.getType().getMimeType());
				byte[] bytes = readFile(connector.get(req, monitor), monitor);
				if (bytes != null)
					cf.setContent(new String(Base64.encodeBase64(bytes)));
			} catch (HttpResponseException e) {
				if (e.getStatusCode() == 500)
					;// jrs 5.5 returns 500 if file is not existing, a
						// bug
				// for newer versions, we should show the error
			}
		}
	}

	@Override
	public ResourceDescriptor addOrModifyResource(IProgressMonitor monitor, ResourceDescriptor rd, File inFile)
			throws Exception {
		prepareResource(monitor, rd, inFile);

		String rtype = WsTypes.INST().toRestType(rd.getWsType());
		ClientResource<?> cr = Soap2Rest.getResource(this, rd);
		Response r = null;
		if (cr instanceof ClientReportUnit) {
			ClientReportUnit cru = (ClientReportUnit) cr;
			if (cru.getJrxml() == null) {
				ResourceDescriptor newrd = get(monitor, rd, inFile);
				for (ResourceDescriptor rc : newrd.getChildren()) {
					if (rc.getWsType().equals(ResourceDescriptor.TYPE_JRXML) && rc.isMainReport())
						rd.getChildren().add(rc);
				}
				cr = Soap2Rest.getResource(this, newrd);
				cru = (ClientReportUnit) cr;
			}
			if (cru.getJrxml() instanceof ClientFile)
				fillFiles((ClientFile) cru.getJrxml(), monitor);
			for (ClientReferenceableFile crf : cru.getFiles().values())
				if (crf instanceof ClientFile)
					fillFiles((ClientFile) crf, monitor);
		}

		if (rd.getWsType().equals(ResourceDescriptor.TYPE_REPORT_OPTIONS)) {
			ResourceProperty resprop = ResourceDescriptorUtil.getProperty("PROP_RU_URI", rd.getProperties()); //$NON-NLS-1$
			WebTarget tgt = target.path("reports" + resprop.getValue() + "/options"); //$NON-NLS-1$ //$NON-NLS-2$
			tgt = tgt.queryParam("label", rd.getLabel()); //$NON-NLS-1$
			tgt = tgt.queryParam("overwrite", "true"); //$NON-NLS-1$ //$NON-NLS-2$

			ReportParameters rprms = new ReportParameters(new ArrayList<ReportParameter>());

			Builder req = tgt.request();

			r = connector.post(req, Entity.entity(rprms, MediaType.APPLICATION_XML_TYPE), monitor);

			try {
				toObj(r, String.class, monitor);
			} catch (HttpResponseException e) {
				if (e.getStatusCode() == 409) {
					rd.setVersion(parent.get(monitor, rd, null).getVersion());
					return addOrModifyResource(monitor, rd, inFile);
				} else
					throw e;
			}
			return doGet(monitor, rd, cr);
		}
		WebTarget tgt = target.path("resources" + rd.getUriString()); //$NON-NLS-1$
		tgt = tgt.queryParam("createFolders", "true"); //$NON-NLS-1$ //$NON-NLS-2$
		tgt = tgt.queryParam("overwrite", "true"); //$NON-NLS-1$ //$NON-NLS-2$

		Builder req = tgt.request();
		r = connector.put(req, Entity.entity(cr, "application/repository." + rtype + "+" + FORMAT), monitor); //$NON-NLS-1$ //$NON-NLS-2$

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

	public JdbcDriverInfo getJdbcDrivers(IProgressMonitor monitor) throws Exception {
		WebTarget tgt = target.path("jdbcDrivers"); //$NON-NLS-1$
		return toObj(connector.get(tgt.request(), monitor), JdbcDriverInfo.class, monitor);
	}

	public void uploadJdbcDrivers(JdbcDriver driver, IProgressMonitor monitor) throws Exception {
		monitor.setTaskName("Uploading drivers"); //$NON-NLS-1$
		if (Misc.isNullOrEmpty(driver.getPaths()))
			return;
		FormDataMultiPart entity = new FormDataMultiPart();
		entity.type(Boundary.addBoundary(MediaType.MULTIPART_FORM_DATA_TYPE));
		entity.field("className", driver.getClassname()); //$NON-NLS-1$
		for (int i = 0; i < driver.getPaths().size(); i++) {
			File file = new File(driver.getPaths().get(i));
			if (file.exists())
				entity.bodyPart(new FileDataBodyPart("file_" + i, file)); //$NON-NLS-1$
		}

		WebTarget tgt = target.path("jdbcDrivers"); //$NON-NLS-1$
		Builder req = tgt.request();
		eh.handleException(connector.post(req, Entity.entity(entity, entity.getMediaType()), monitor), monitor);
	}

	private void prepareResource(IProgressMonitor monitor, ResourceDescriptor rd, File inFile) throws Exception {
		if (!rd.getIsNew() && rd.getChildren() != null) {
			for (ResourceDescriptor r : rd.getChildren()) {
				if (!r.getIsNew() && r.isDirty1()) {
					addOrModifyResource(monitor, r, null);
					r.setDirty(false);
				} else
					prepareResource(monitor, r, null);
			}
		}
	}

	private ResourceDescriptor doGet(IProgressMonitor monitor, ResourceDescriptor rd, ClientResource<?> crl)
			throws Exception, ParseException {
		boolean refresh = false;
		if (WsTypes.INST().isContainerType(crl.getClass()))
			refresh = true;
		if (monitor.isCanceled())
			return rd;
		if (refresh)
			rd = parent.get(monitor, rd, null);
		else
			rd = Rest2Soap.getRD(this, crl, rd);
		return rd;
	}

	@Override
	public ResourceDescriptor modifyReportUnitResource(IProgressMonitor monitor, ResourceDescriptor runit,
			ResourceDescriptor rd, File inFile) throws Exception {
		if (rd.getIsReference() && (rd.hasDirtyChildren() || rd.getHasData())) {
			// ResourceDescriptor r = new ResourceDescriptor();
			// r.setUriString(Misc.nvl(rd.getReferenceUri(),
			// rd.getUriString()));
			// r.setWsType(rd.getWsType());
			// ResourceDescriptor ref = parent.get(monitor, r, null);
			// ref.setHasData(true);
			// ref.setData(rd.getData());
			return addOrModifyResource(monitor, rd, inFile);
		}
		runit = parent.get(monitor, runit, null);
		PublishUtil.setChild(runit, rd);
		return addOrModifyResource(monitor, runit, inFile);
	}

	@Override
	public void delete(IProgressMonitor monitor, ResourceDescriptor rd) throws Exception {
		WebTarget tgt = target.path("resources" + rd.getUriString()); //$NON-NLS-1$
		Response res = connector.delete(tgt.request(), monitor);
		try {
			switch (res.getStatus()) {
			case 204:
				System.out.println("Deleted"); //$NON-NLS-1$
				break;
			default:
				eh.handleException(res, monitor);
			}
		} finally {
			res.close();
		}
	}

	@Override
	public ResourceDescriptor delete(IProgressMonitor monitor, ResourceDescriptor rd, ResourceDescriptor runit)
			throws Exception {
		ResourceDescriptor rdrem = null;
		for (ResourceDescriptor r : runit.getChildren())
			if (r.getUriString().equals(rd.getUriString())) {
				rdrem = r;
				break;
			}
		if (rdrem != null) {
			runit.getChildren().remove(rdrem);
			return addOrModifyResource(monitor, runit, null);
		}
		return runit;
	}

	private CookieStore getCookieStore() {
		if (cookieStore == null) {
			Connector c = ((ClientConfig) target.getConfiguration()).getConnector();

			try {
				Method m = c.getClass().getMethod("getCookieStore"); //$NON-NLS-1$
				if (m != null)
					m.setAccessible(true);
				cookieStore = (CookieStore) m.invoke(c);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return cookieStore;
	}

	@Override
	public ReportExecution runReport(IProgressMonitor monitor, ReportExecution repExec) throws Exception {
		Map<String, FileContent> map = new HashMap<String, FileContent>();
		ReportExecutionDescriptor res = null;
		WebTarget tgt = null;
		Builder req = null;
		if (repExec.getRequestId() != null && !repExec.getStatus().equals("refresh")) { //$NON-NLS-1$
			// if (repExec.getStatus().equals("refresh")) {
			// tgt = target.path("reportExecutions/" + repExec.getRequestId() +
			// "/exports");
			// req = tgt.request();
			// ExportExecutionOptions ed = new ExportExecutionOptions();
			// ed.setAttachmentsPrefix("");
			// ed.setPages("1");
			// for (Argument arg : repExec.getArgs()) {
			// if (arg.getName().equals(Argument.RUN_OUTPUT_FORMAT)) {
			// ed.setOutputFormat(arg.getValue());
			// break;
			// }
			// }
			// Response r = connector.post(req, Entity.entity(ed,
			// MediaType.APPLICATION_XML_TYPE), monitor);
			// res = toObj(r, ReportExecutionExport.class, monitor);
			// } else {
			tgt = target.path("reportExecutions/" + repExec.getRequestId()); //$NON-NLS-1$
			req = tgt.request();
			Response r = connector.get(req, monitor);
			res = toObj(r, ReportExecutionDescriptor.class, monitor);
			// }
		} else {
			ReportExecutionRequest rer = new ReportExecutionRequest();
			rer.setReportUnitUri(repExec.getReportURI());
			rer.setAsync(true);
			rer.setFreshData(true);
			rer.setIgnorePagination(false);
			rer.setAllowInlineScripts(true);
			rer.setInteractive(true);
			rer.setAttachmentsPrefix(""); //$NON-NLS-1$
			String format = null;
			for (Argument arg : repExec.getArgs()) {
				if (arg.getName().equals(Argument.RUN_OUTPUT_FORMAT)) {
					format = arg.getValue();
					break;
				}
			}
			if (format == null)
				format = Argument.RUN_OUTPUT_FORMAT_JRPRINT;
			if (format.equals(Argument.RUN_OUTPUT_FORMAT_JRPRINT))
				rer.setPages("1"); //$NON-NLS-1$
			rer.setOutputFormat(format);

			// rer.setTransformerKey(transformerKey);
			rer.setSaveDataSnapshot(false);
			Map<String, Object> prm = repExec.getPrm();
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
			// if (!getServerInfo().getVersion().equals("5.6.0"))
			if (rer.getOutputFormat().equals(Argument.RUN_OUTPUT_FORMAT_HTML)) {
				String rourl = "reports" + repExec.getReportURI() + ".html"; //$NON-NLS-1$ //$NON-NLS-2$
				if (rer.getParameters() != null) {
					String del = "?"; //$NON-NLS-1$
					for (ReportParameter rp : rer.getParameters().getReportParameters()) {
						rourl += del;
						rourl += rp.getName() + "="; //$NON-NLS-1$
						for (String v : rp.getValues()) {
							rourl += v;
						}
						del = "&"; //$NON-NLS-1$
					}
				}
				repExec.setBaseUrl(target.getUri().toASCIIString());
				repExec.setPathUrl(rourl);
				repExec.setReportOutputCookie(getCookieStore().getCookies());
				repExec.setStatus("ready"); //$NON-NLS-1$
				return repExec;
			}

			tgt = target.path("reportExecutions"); //$NON-NLS-1$
			req = tgt.request();
			Response r = connector.post(req, Entity.entity(rer, MediaType.APPLICATION_XML_TYPE), monitor);
			res = toObj(r, ReportExecutionDescriptor.class, monitor);
		}
		if (res != null && res.getErrorDescriptor() == null) {
			if (res.getExports() != null) {
				int i = 0;
				for (ExportDescriptor ee : res.getExports()) {
					// System.out.println(ee.getOutputResource());
					if (ee.getStatus().equals("queued")) //$NON-NLS-1$
						continue;

					tgt = target.path(
							"reportExecutions/" + res.getRequestId() + "/exports/" + ee.getId() + "/outputResource"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					if (ee.getOutputResource() != null)
						req = tgt.request(ee.getOutputResource().getContentType());
					else
						req = tgt.request();
					Response r = connector.get(req, monitor);
					if (ee.getOutputResource() == null) {
						OutputResourceDescriptor or = new OutputResourceDescriptor();
						or.setContentType(r.getHeaderString("Content-Type")); //$NON-NLS-1$
						or.setFileName("file"); //$NON-NLS-1$
						ee.setOutputResource(or);
					}
					byte[] d = readFile(r, monitor);
					addFileContent(d, map, ee.getOutputResource(), "attachment-" + i++, "report"); //$NON-NLS-1$ //$NON-NLS-2$
					if (ee.getAttachments() != null)
						for (AttachmentDescriptor ror : ee.getAttachments()) {
							tgt = target.path("reportExecutions/" + res.getRequestId() + "/exports/" + ee.getId() //$NON-NLS-1$ //$NON-NLS-2$
									+ "/attachments/" + ror.getFileName()); //$NON-NLS-1$
							req = tgt.request(ror.getContentType());
							d = readFile(connector.get(req, monitor), monitor);
							addFileContent(d, map, ror, "attachment-" + i++, ror.getFileName()); //$NON-NLS-1$
						}
				}
			} else {
				tgt = target.path("reportExecutions/" + res.getRequestId() + "/exports/" //$NON-NLS-1$ //$NON-NLS-2$
						+ Argument.RUN_OUTPUT_FORMAT_JRPRINT + "/outputResource"); //$NON-NLS-1$
				req = tgt.request(MediaType.APPLICATION_OCTET_STREAM_TYPE);
				byte[] d = readFile(connector.get(req, monitor), monitor);
				FileContent content = new FileContent();
				content.setData(d);
				content.setMimeType(MediaType.APPLICATION_OCTET_STREAM_TYPE.toString());
				content.setName("attachment-0"); //$NON-NLS-1$

				map.put("jasperPrint", content); //$NON-NLS-1$
				// addFileContent(d, map, , "attachment-0", "jasperPrint");
			}
		}
		repExec.setFiles(map);
		repExec.setCurrentPage(res.getCurrentPage());
		repExec.setErrorDescriptor(res.getErrorDescriptor());
		repExec.setRequestId(res.getRequestId());
		repExec.setStatus(res.getStatus());
		repExec.setTotalPages(res.getTotalPages());
		// System.out.println(res.getStatus() + " : " + res.getTotalPages() +
		// " : "
		// + map.size());
		return repExec;
	}

	@Override
	public void cancelReport(IProgressMonitor monitor, ReportExecution repExec) throws Exception {
		WebTarget tgt = target.path("reportExecutions/" + repExec.getRequestId() + "/status"); //$NON-NLS-1$ //$NON-NLS-2$
		Builder req = tgt.request();
		connector.put(req, Entity.entity("<status>cancelled</status>", MediaType.APPLICATION_XML_TYPE), monitor); //$NON-NLS-1$
	}

	private void addFileContent(byte[] d, Map<String, FileContent> map, AttachmentDescriptor r, String id, String key) {
		if (d != null) {
			FileContent content = new FileContent();
			content.setData(d);
			content.setMimeType(r.getContentType());
			content.setName(Misc.nvl(r.getFileName(), id));

			map.put(key, content);
		}
	}

	private void addFileContent(byte[] d, Map<String, FileContent> map, OutputResourceDescriptor r, String id,
			String key) {
		if (d != null) {
			FileContent content = new FileContent();
			content.setData(d);
			content.setMimeType(r.getContentType());
			content.setName(Misc.nvl(r.getFileName(), id));

			map.put(key, content);
		}
	}

	@Override
	public List<ResourceDescriptor> listDatasources(IProgressMonitor monitor, IDatasourceFilter f) throws Exception {
		List<ResourceDescriptor> rds = new ArrayList<ResourceDescriptor>();
		WebTarget tgt = target.path("resources"); //$NON-NLS-1$
		if (f == null)
			f = new DatasourcesAllFilter();
		for (String type : f.getFilterTypes())
			tgt = tgt.queryParam("type", WsTypes.INST().toRestType(type)); //$NON-NLS-1$
		tgt = tgt.queryParam("sortBy", "label"); //$NON-NLS-1$ //$NON-NLS-2$
		tgt = tgt.queryParam("limit", 0); //$NON-NLS-1$

		Builder req = tgt.request();
		ClientResourceListWrapper resources = toObj(connector.get(req, monitor), ClientResourceListWrapper.class,
				monitor);
		if (resources != null)
			for (ClientResourceLookup crl : resources.getResourceLookups())
				rds.add(Rest2Soap.getRDLookup(this, crl));
		return rds;
	}

	@Override
	public void findResources(IProgressMonitor monitor, AFinderUI callback) throws Exception {
		if (callback.getText() == null) {
			callback.showResults(null);
			return;
		}
		WebTarget tgt = target.path("resources"); //$NON-NLS-1$
		tgt = tgt.queryParam("q", callback.getText()); //$NON-NLS-1$
		for (String type : callback.getTypes())
			tgt = tgt.queryParam("type", type); //$NON-NLS-1$
		if (!callback.isShowHidden())
			tgt = tgt.queryParam("showHiddenItems", "false");
		tgt = tgt.queryParam("sortBy", "label"); //$NON-NLS-1$ //$NON-NLS-2$
		tgt = tgt.queryParam("limit", 0); //$NON-NLS-1$

		Builder req = tgt.request();
		ClientResourceListWrapper resources = toObj(connector.get(req, monitor), ClientResourceListWrapper.class,
				monitor);
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
		case IMPORTMETADATA:
		case EXPORTMETADATA:
			return true;
		}
		return super.isSupported(f);
	}

	@Override
	public void getBundle(Map<String, String> map, String name, IProgressMonitor monitor) throws Exception {
		Builder req = target.path("bundles/" + name).request(MediaType.APPLICATION_JSON_TYPE); //$NON-NLS-1$
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
		Builder req = target.path("reports/" + uri.replaceFirst("/", "") + "/inputControls").request(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		try {
			ReportInputControlsListWrapper m = toObj(connector.get(req, monitor), ReportInputControlsListWrapper.class,
					monitor);
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
	public void reorderInputControls(String uri, List<ResourceDescriptor> rds, IProgressMonitor monitor)
			throws Exception {
		Builder req = target.path("reports" + uri + "/inputControls").request(); //$NON-NLS-1$ //$NON-NLS-2$
		Response res = connector.get(req, monitor);
		ReportInputControlsListWrapper crl = toObj(res, ReportInputControlsListWrapper.class, monitor);
		if (crl != null) {
			List<ReportInputControl> ics = new ArrayList<ReportInputControl>();
			for (ResourceDescriptor r : rds) {
				String ruri = "repo:" + r.getUriString(); //$NON-NLS-1$
				for (ReportInputControl ric : crl.getInputParameters()) {
					if (ruri.equals(ric.getUri()))
						ics.add(ric);
				}
			}
			ReportInputControlsListWrapper wrapper = new ReportInputControlsListWrapper(ics);
			req = target.path("reports" + uri + "/inputControls").request(); //$NON-NLS-1$ //$NON-NLS-2$
			Response r = connector.put(req, Entity.entity(wrapper, MediaType.APPLICATION_XML_TYPE), monitor);
			toObj(r, ReportInputControlsListWrapper.class, monitor);
		}
	}

	@Override
	public ResourceDescriptor initInputControls(String uri, String type, IProgressMonitor monitor) throws Exception {
		uri = WSClientHelper.getReportUnitUri(uri);
		ResourceDescriptor rdunit = new ResourceDescriptor();
		rdunit.setUriString(uri);
		rdunit.setWsType(type);
		// rdunit = parent.get(monitor, rdunit, null);
		// if (monitor.isCanceled())
		// return rdunit;
		Builder req = target.path("reports" + uri + "/inputControls").request(); //$NON-NLS-1$ //$NON-NLS-2$
		Response r = connector.get(req, monitor);
		ReportInputControlsListWrapper crl = toObj(r, ReportInputControlsListWrapper.class, monitor);
		if (crl != null) {
			for (ReportInputControl ric : crl.getInputParameters())
				rdunit.getChildren().add(Rest2Soap.getInputControl(this, ric, new ResourceDescriptor()));
			for (ResourceDescriptor rd : rdunit.getChildren()) {
				if (rd.getWsType().equals(ResourceDescriptor.TYPE_INPUT_CONTROL)) {
					for (ReportInputControl ric : crl.getInputParameters()) {
						InputControlState ics = ric.getState();
						if (ics.getId().equals(rd.getName())) {
							setInputControlState(rd, ics);
							break;
						}
					}
				}
			}
		}
		return rdunit;
	}

	public void setInputControlState(ResourceDescriptor rd, InputControlState ics) {
		if (ics.getValue() != null)
			rd.setValue(ics.getValue());
		else if (ics.getOptions() != null) {
			if (InputControlsManager.isICQuery(rd)) {
				List<InputControlQueryDataRow> qvalues = new ArrayList<InputControlQueryDataRow>();
				for (InputControlOption ico : ics.getOptions()) {
					InputControlQueryDataRow dr = new InputControlQueryDataRow();
					dr.setValue(ico.getValue());
					List<String> cols = new ArrayList<String>();
					for (String s : ico.getLabel().split("\\s\\|\\s")) //$NON-NLS-1$
						cols.add(s);
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

	public ReportParameters convertInputControls(List<ResourceDescriptor> ics) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<ReportParameter> lrp = new ArrayList<ReportParameter>();
		for (ResourceDescriptor rd : ics) {
			Map<String, Object> icMap = rd.getIcValues();
			for (String key : icMap.keySet())
				setMap(key, map, icMap.get(key));
		}
		for (String key : map.keySet()) {
			ReportParameter r = new ReportParameter();
			r.setName(key);
			r.setValues(map.get(key));
			lrp.add(r);
		}
		return new ReportParameters(lrp);
	}

	@Override
	public List<ResourceDescriptor> cascadeInputControls(ResourceDescriptor runit, List<ResourceDescriptor> ics,
			IProgressMonitor monitor) throws Exception {
		if (ics.isEmpty())
			return ics;
		String ctrls = ""; //$NON-NLS-1$
		String del = ""; //$NON-NLS-1$
		for (ResourceDescriptor rd : ics) {
			ctrls += del + rd.getName();
			del = ";"; //$NON-NLS-1$
		}

		Builder req = target.path("reports" + runit.getUriString() + "/inputControls/" + ctrls + "/values").request(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		Response r = connector.post(req, Entity.entity(convertInputControls(ics), MediaType.APPLICATION_XML_TYPE),
				monitor);
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
				String str = val2String(obj);
				if (!vals.contains(str))
					vals.add(str);
			}
		} else {
			String str = val2String(value);
			if (!vals.contains(str))
				vals.add(str);
		}
	}

	private String val2String(Object val) {
		if (val instanceof java.sql.Date)
			return getDateFormat().format(val);
		if (val instanceof java.sql.Timestamp)
			return getTimestampFormat().format(val);
		if (val instanceof java.sql.Time)
			return getTimeFormat().format(val);
		return val.toString();
	}

	@Override
	public StateDto importMetaData(ImportOptions options, IProgressMonitor monitor) throws Exception {
		if (options.getState() != null) {
			WebTarget tgt = target.path("import/" + options.getState().getId() + "/state"); //$NON-NLS-1$ //$NON-NLS-2$

			Builder req = tgt.request();
			Response r = connector.get(req, monitor);
			StateDto state = toObj(r, StateDto.class, monitor);
			options.setState(state);
		} else {
			WebTarget tgt = target.path("import"); //$NON-NLS-1$
			tgt = tgt.queryParam("update", options.isUpdate()); //$NON-NLS-1$
			if (options.isUpdate())
				tgt = tgt.queryParam("skipUserUpdate", options.isSkipUserUpdates()); //$NON-NLS-1$
			tgt = tgt.queryParam("includeAccessEvents", options.isInclAccessEvents()); //$NON-NLS-1$
			tgt = tgt.queryParam("includeAuditEvents", options.isInclAuditEvents()); //$NON-NLS-1$
			tgt = tgt.queryParam("includeMonitoringEvents", options.isInclMonitorEvents()); //$NON-NLS-1$
			tgt = tgt.queryParam("includeServerSettings", options.isInclSrvSettings()); //$NON-NLS-1$

			File file = new File(options.getFile());

			Builder req = tgt.request();
			Response r = connector.post(req, Entity.entity(file, "application/zip"), monitor); //$NON-NLS-1$
			StateDto state = toObj(r, StateDto.class, monitor);
			options.setState(state);
		}
		return options.getState();
	}

	@Override
	public StateDto exportMetaData(ExportOptions options, IProgressMonitor monitor) throws Exception {
		if (options.getState() != null) {
			WebTarget tgt = target.path("export/" + options.getState().getId() + "/state"); //$NON-NLS-1$ //$NON-NLS-2$

			Builder req = tgt.request();
			Response r = connector.get(req, monitor);
			StateDto state = toObj(r, StateDto.class, monitor);
			options.setState(state);
			if (state.getPhase().equals("finished")) { //$NON-NLS-1$
				tgt = target.path("export/" + options.getState().getId() + "/export.zip"); //$NON-NLS-1$ //$NON-NLS-2$

				req = tgt.request();
				r = connector.get(req, monitor);
				monitor.subTask(Messages.RestV2ConnectionJersey_175 + options.getFile());
				File file = new File(options.getFile());
				readFile(r, file, monitor);
			}
		} else {
			WebTarget tgt = target.path("export"); //$NON-NLS-1$

			ExportTaskDto taskDTO = new ExportTaskDto();
			List<String> parameters = options.getParameters();
			if (!parameters.isEmpty())
				taskDTO.setParameters(parameters);
			if (!options.getRoles().isEmpty())
				taskDTO.setRoles(options.getRoles());
			if (!options.getJobs().isEmpty())
				taskDTO.setScheduledJobs(options.getJobs());
			if (!options.getUsers().isEmpty())
				taskDTO.setUsers(options.getUsers());
			if (!options.getPaths().isEmpty())
				taskDTO.setUris(options.getPaths());

			Builder req = tgt.request();
			Response r = connector.post(req, Entity.entity(taskDTO, MediaType.APPLICATION_JSON_TYPE), monitor);
			StateDto state = toObj(r, StateDto.class, monitor);
			options.setState(state);
		}
		return options.getState();
	}

	@Override
	public List<RepositoryPermission> getPermissions(ResourceDescriptor rd, IProgressMonitor monitor,
			PermissionOptions options) throws Exception {
		WebTarget tgt = target.path("permissions" + rd.getUriString()); //$NON-NLS-1$
		tgt = tgt.queryParam("effectivePermissions", options.isEffectivePermissions()); //$NON-NLS-1$
		tgt = tgt.queryParam("recipientType", options.isRecipientTypeUser() ? "user" : "role"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		if (options.getRecipientId() != null)
			tgt = tgt.queryParam("recipientId", options.getRecipientId()); //$NON-NLS-1$
		tgt = tgt.queryParam("resolveAll", options.isResolveAll()); //$NON-NLS-1$

		Builder req = tgt.request();
		Response r = connector.get(req, monitor);
		RepositoryPermissionListWrapper state = toObj(r, RepositoryPermissionListWrapper.class, monitor);

		return state.getPermissions();
	}

	@Override
	public ClientUser getUser(IProgressMonitor monitor) throws Exception {
		String path = ""; //$NON-NLS-1$
		if (!Misc.isNullOrEmpty(sp.getOrganisation()))
			path += "organizations/" + sp.getOrganisation() + "/"; //$NON-NLS-1$ //$NON-NLS-2$
		String usr = sp.getUser();
		if (sp.isUseSSO())
			usr = CASUtil.getSSO(sp, monitor).getUser();
		path += "users/" + usr; //$NON-NLS-1$
		WebTarget tgt = target.path(path);

		Builder req = tgt.request();
		Response r = connector.get(req, monitor);
		return toObj(r, ClientUser.class, monitor);
	}

	@Override
	public List<RepositoryPermission> setPermissions(ResourceDescriptor rd, List<RepositoryPermission> perms,
			PermissionOptions options, IProgressMonitor monitor) throws Exception {
		for (RepositoryPermission rp : perms) {
			WebTarget tgt = target.path("permissions" + rd.getUriString()); //$NON-NLS-1$
			tgt = tgt.matrixParam("recipient", rp.getRecipient()); //$NON-NLS-1$

			Builder req = tgt.request();
			if (rp.getMask() == -1) {
				try {
					Response r = connector.delete(req, monitor);
					toObj(r, String.class, monitor);
				} catch (HttpResponseException e) {
					if (e.getStatusCode() != 404 && e.getStatusCode() != 204)
						throw e;
				}
			} else if (rp.getUri() != null && rd.getUriString().equals(rp.getUri())) {
				Response r = connector.put(req, Entity.entity(rp, MediaType.APPLICATION_XML_TYPE), monitor);
				toObj(r, RepositoryPermission.class, monitor);
			}
		}
		return getPermissions(rd, monitor, options);
	}

}
