/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.protocol.soap;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.axis.AxisProperties;
import org.apache.axis.components.net.DefaultCommonsHTTPClientProperties;
import org.apache.commons.codec.binary.Base64;
import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.ireport.jasperserver.ws.FileContent;
import com.jaspersoft.ireport.jasperserver.ws.JServer;
import com.jaspersoft.ireport.jasperserver.ws.WSClient;
import com.jaspersoft.ireport.jasperserver.ws.WSRole;
import com.jaspersoft.ireport.jasperserver.ws.WSUser;
import com.jaspersoft.ireport.jasperserver.ws.permissions.WSObjectPermission;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.Argument;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ListItem;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.authority.ClientUser;
import com.jaspersoft.jasperserver.dto.jdbcdrivers.JdbcDriverInfo;
import com.jaspersoft.jasperserver.dto.permissions.RepositoryPermission;
import com.jaspersoft.jasperserver.dto.resources.ClientResource;
import com.jaspersoft.jasperserver.dto.serverinfo.ServerInfo;
import com.jaspersoft.jasperserver.jaxrs.client.dto.importexport.StateDto;
import com.jaspersoft.studio.server.AFinderUI;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.editor.input.InputControlsManager;
import com.jaspersoft.studio.server.model.datasource.filter.DatasourcesAllFilter;
import com.jaspersoft.studio.server.model.datasource.filter.IDatasourceFilter;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.protocol.Feature;
import com.jaspersoft.studio.server.protocol.IConnection;
import com.jaspersoft.studio.server.protocol.JdbcDriver;
import com.jaspersoft.studio.server.protocol.ReportExecution;
import com.jaspersoft.studio.server.publish.PublishUtil;
import com.jaspersoft.studio.server.utils.Pass;
import com.jaspersoft.studio.server.wizard.exp.ExportOptions;
import com.jaspersoft.studio.server.wizard.imp.ImportOptions;
import com.jaspersoft.studio.server.wizard.permission.PermissionOptions;
import com.jaspersoft.studio.server.wizard.resource.page.selector.SelectorDatasource;

import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRQueryChunk;
import net.sf.jasperreports.engine.design.JRDesignQuery;

public class SoapConnection implements IConnection {
	protected DateFormat dateFormat = SimpleDateFormat.getDateInstance();
	protected DateFormat timestampFormat = SimpleDateFormat.getDateTimeInstance();
	protected DateFormat timeFormat = new SimpleDateFormat("h:mm:ss"); //$NON-NLS-1$
	protected NumberFormat numberFormat = NumberFormat.getInstance();
	private ServerProfile sp;

	public ServerProfile getServerProfile() {
		return sp;
	}

	public Format getDateFormat() {
		return dateFormat;
	}

	public Format getTimestampFormat() {
		return timestampFormat;
	}

	public Format getTimeFormat() {
		return timeFormat;
	}

	public Format getNumberFormat() {
		return numberFormat;
	}

	private WSClient client;
	private ServerInfo serverInfo;

	@Override
	public ServerInfo getServerInfo() {
		return parent.getServerInfo();
	}

	@Override
	public ServerInfo getServerInfo(IProgressMonitor monitor) throws Exception {
		if (serverInfo != null)
			return serverInfo;
		String v = client.getVersion();
		serverInfo = getServerInfo();
		if (serverInfo == null) {
			serverInfo = new ServerInfo();
			serverInfo.setVersion(v);
			// serverInfo
			// .setTimeFormatPattern(((SimpleDateFormat) getTimeFormat())
			// .toPattern());
			serverInfo.setDateFormatPattern(((SimpleDateFormat) getDateFormat()).toPattern());
			serverInfo.setDatetimeFormatPattern(((SimpleDateFormat) getTimestampFormat()).toPattern());
			// serverInfo.setVersion(client.getVersion());
		}
		return serverInfo;
	}

	@Override
	public boolean connect(IProgressMonitor monitor, ServerProfile sp) throws Exception {
		monitor.subTask(Messages.SoapConnection_1);
		JServer server = new JServer();
		this.sp = sp;
		if (setupJServer(server, sp, monitor)) {
			if (sp.isLogging()) {
				System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog"); //$NON-NLS-1$ //$NON-NLS-2$
				System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true"); //$NON-NLS-1$ //$NON-NLS-2$
				System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.wire", "debug"); //$NON-NLS-1$ //$NON-NLS-2$
				System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.impl.conn", "debug"); //$NON-NLS-1$ //$NON-NLS-2$
				System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.impl.client", "debug"); //$NON-NLS-1$ //$NON-NLS-2$
				System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.client", "debug"); //$NON-NLS-1$ //$NON-NLS-2$
				System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "debug"); //$NON-NLS-1$ //$NON-NLS-2$
			} else {
				System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog"); //$NON-NLS-1$ //$NON-NLS-2$
				System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true"); //$NON-NLS-1$ //$NON-NLS-2$
				System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.wire", "ERROR"); //$NON-NLS-1$ //$NON-NLS-2$
				System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.impl.conn", "ERROR"); //$NON-NLS-1$ //$NON-NLS-2$
				System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.impl.client", "ERROR"); //$NON-NLS-1$ //$NON-NLS-2$
				System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.client", "ERROR"); //$NON-NLS-1$ //$NON-NLS-2$
				System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "ERROR"); //$NON-NLS-1$ //$NON-NLS-2$
			}
			if (monitor.isCanceled())
				return false;
			client = server.getWSClient();
			if (getServerInfo(monitor) == null)
				return false;
			return true;
		}
		return false;
	}

	private boolean setupJServer(JServer server, ServerProfile sp, IProgressMonitor monitor) throws Exception {
		AxisProperties.setProperty(DefaultCommonsHTTPClientProperties.MAXIMUM_CONNECTIONS_PER_HOST_PROPERTY_KEY, "4"); //$NON-NLS-1$
		server.setName(sp.getName());
		String rurl = sp.getUrl();
		if (rurl.endsWith("services/repository/")) //$NON-NLS-1$
			rurl = rurl.substring(0, rurl.length() - 1);
		if (!rurl.endsWith("services/repository")) //$NON-NLS-1$
			rurl += "services/repository"; //$NON-NLS-1$
		server.setUrl(rurl);
		String username = sp.getUser();
		if (sp.getOrganisation() != null && !sp.getOrganisation().trim().isEmpty())
			username += "|" + sp.getOrganisation(); //$NON-NLS-1$
		server.setUsername(username);
		server.setPassword(parent.getPassword(monitor));
		server.setTimeout(sp.getTimeout());
		server.setChunked(sp.isChunked());
		server.setMime(sp.isMime());
		return true;
	}

	private static Set<String> wstypes = new HashSet<String>();
	static {
		wstypes.add(ResourceDescriptor.TYPE_CSS_FILE);
		wstypes.add(ResourceDescriptor.TYPE_JSON_FILE);
	}

	@Override
	public ResourceDescriptor get(IProgressMonitor monitor, ResourceDescriptor rd, File f) throws Exception {
		try {
			if (rd.getUriString() == null || rd.getUriString().contains("<")) //$NON-NLS-1$
				throw new Exception(Messages.SoapConnection_0);
			rd = client.get(rd, f);
			sortReportUnit(rd, rd.getChildren());
		} catch (Exception e) {
			if (wstypes.contains(rd.getWsType())) {
				throw new Exception(String.format(Messages.SoapConnection_37, rd.getWsType()), e);
			}
			throw e;
		}
		return rd;
	}

	protected void sortReportUnit(ResourceDescriptor rd, List<ResourceDescriptor> children) {
		if (rd.getWsType().equals(ResourceDescriptor.TYPE_REPORTUNIT)
				|| rd.getWsType().equals(ResourceDescriptor.TYPE_ADHOC_DATA_VIEW)
				|| rd.getWsType().equals(ResourceDescriptor.TYPE_DOMAIN_TOPICS)
				|| rd.getWsType().equals(ResourceDescriptor.TYPE_REPORT_OPTIONS))
			Collections.sort(children, new Comparator<ResourceDescriptor>() {

				@Override
				public int compare(ResourceDescriptor arg0, ResourceDescriptor arg1) {
					if (arg0.getLabel() == arg1.getLabel())
						return 0;
					if (arg0.getLabel() == null)
						return -1;
					if (arg1.getLabel() == null)
						return 1;
					String wsType0 = arg0.getWsType();
					String wsType1 = arg1.getWsType();
					if (wsType0.equals(wsType1)) {
						if (wsType0.equals(ResourceDescriptor.TYPE_JRXML)) {
							if (arg0.isMainReport())
								return -1;
							if (arg1.isMainReport())
								return 1;
							return arg0.getLabel().compareToIgnoreCase(arg1.getLabel());
						} else if (wsType0.equals(ResourceDescriptor.TYPE_INPUT_CONTROL))
							// ignore input controls
							return 0;
						return arg0.getLabel().compareToIgnoreCase(arg1.getLabel());
					}
					if (DatasourcesAllFilter.getTypes().contains(wsType0))
						return -1;
					if (DatasourcesAllFilter.getTypes().contains(wsType1))
						return 1;
					if (wsType0.equals(ResourceDescriptor.TYPE_JRXML))
						return -1;
					if (wsType1.equals(ResourceDescriptor.TYPE_JRXML))
						return 1;
					if (wsType0.equals(ResourceDescriptor.TYPE_QUERY))
						return -1;
					if (wsType1.equals(ResourceDescriptor.TYPE_QUERY))
						return 1;
					if (wsType0.equals(ResourceDescriptor.TYPE_INPUT_CONTROL))
						return -1;
					if (wsType1.equals(ResourceDescriptor.TYPE_INPUT_CONTROL))
						return 1;

					return wsType0.compareTo(wsType1);
				}
			});
	}

	@Override
	public List<ResourceDescriptor> list(IProgressMonitor monitor, ResourceDescriptor rd) throws Exception {
		List<ResourceDescriptor> list = client.list(rd);
		sortReportUnit(rd, list);
		return list;
	}

	@Override
	public ResourceDescriptor move(IProgressMonitor monitor, ResourceDescriptor rd, String destFolderURI)
			throws Exception {
		client.move(rd, destFolderURI);
		ResourceDescriptor nrd = new ResourceDescriptor();
		nrd.setWsType(rd.getWsType());
		nrd.setUriString(destFolderURI + "/" + rd.getName()); //$NON-NLS-1$
		return parent.get(monitor, nrd, null);
	}

	@Override
	public ResourceDescriptor copy(IProgressMonitor monitor, ResourceDescriptor rd, String destFolderURI)
			throws Exception {
		if (rd.getParentFolder().equals(destFolderURI))
			return rd;
		destFolderURI = destFolderURI + "/" + rd.getName(); //$NON-NLS-1$
		return client.copy(rd, destFolderURI);
	}

	@Override
	public ResourceDescriptor addOrModifyResource(IProgressMonitor monitor, ResourceDescriptor rd, File inputFile)
			throws Exception {
		rd.fixStructure();
		if (rd.getIsReference())
			rd.setWsType(ResourceDescriptor.TYPE_REFERENCE);
		List<ResourceDescriptor> children = rd.getChildren();
		ResourceDescriptor mainDs = null;
		if (rd.getWsType().equals(ResourceDescriptor.TYPE_REPORTUNIT)) {
			for (ResourceDescriptor r : children) {
				if (rd.getIsNew() && SelectorDatasource.isDatasource(r))
					mainDs = r;
				if (r.isMainReport()
						|| (r.getWsType().equals(ResourceDescriptor.TYPE_JRXML) && r.getName().equals("main_jrxml"))) { //$NON-NLS-1$
					if (r.getFile() != null) {
						inputFile = r.getFile();
						r.setData(null);
					} else if (r.getHasData() && r.getData() != null) {
						inputFile = writeToTemp(r.getData());
						r.setData(null);
					} else if (inputFile == null && !rd.getIsNew() && !r.getIsReference()) {
						inputFile = FileUtils.createTempFile("res", "jrxml"); //$NON-NLS-1$ //$NON-NLS-2$
						int ind = children.indexOf(r);
						r = get(monitor, r, inputFile);
						r.setHasData(true);
						r.setData(FileUtils.readFileAsAString(inputFile).getBytes());
						children.set(ind, r);
					}
					r.setMainReport(true);
					break;
				}
			}
			if (mainDs != null)
				rd.getChildren().remove(mainDs);
		}
		rd = client.addOrModifyResource(rd, inputFile);
		List<ResourceDescriptor> oldChildren = list(monitor, rd);
		List<ResourceDescriptor> toDel = new ArrayList<ResourceDescriptor>();
		for (ResourceDescriptor r : oldChildren) {
			boolean exists = false;
			for (ResourceDescriptor newr : children) {
				if (newr.getUriString() == null || r.getUriString() == null)
					continue;
				if (r.getWsType().equals(newr.getWsType()) && r.getUriString().equals(newr.getUriString())) {
					newr.setIsNew(false);
					exists = true;
					break;
				}
			}
			if (!exists && r.getWsType() != null && !SelectorDatasource.isDatasource(r))
				toDel.add(r);
		}
		for (ResourceDescriptor r : toDel) {
			rd = delete(monitor, r, rd);
			if (monitor.isCanceled())
				return rd;
		}
		if (rd.getWsType().equals(ResourceDescriptor.TYPE_REPORTUNIT)) {
			rd = get(monitor, rd, null);

			for (ResourceDescriptor r : children) {
				try {
					if (SelectorDatasource.isDatasource(r))
						continue;
					if (r.isMainReport())
						continue;
					if (r.getWsType().equals(ResourceDescriptor.TYPE_INPUT_CONTROL)) {
						if (r.getIsReference())
							r.setUriString(rd.getUriString() + "_files/" + r.getName()); //$NON-NLS-1$
						if (!r.getIsNew())
							r = client.addOrModifyResource(r, null);
						else
							client.modifyReportUnitResource(rd.getUriString(), r, null);
					} else {
						if (r.isMainReport())
							continue;
						File f = r.getFile();
						if (f == null && r.getHasData() && r.getData() != null) {
							f = writeToTemp(r.getData());
							r.setData(null);
							r.setHasData(true);
						}
						r = client.modifyReportUnitResource(rd.getUriString(), r, f);
					}
					rd.getChildren().add(r);
				} catch (Exception e) {
					// not all types are working
					e.printStackTrace();
				}
			}
			if (mainDs != null) {
				rd = get(monitor, rd, null);
				mainDs.setIsNew(false);
				rd.getChildren().add(0, mainDs);
				for (ResourceDescriptor r : rd.getChildren()) {
					if (r.getWsType().equals(ResourceDescriptor.TYPE_JRXML) && r.isMainReport())
						r.setHasData(true);
				}
				client.addOrModifyResource(rd, inputFile);
			} else {
				// ResourceDescriptor mds = null;
				// for (ResourceDescriptor r : oldChildren) {
				// if (SelectorDatasource.isDatasource(r)) {
				// mds = r;
				// break;
				// }
				// }
				// if (mds != null) {
				// rd.getChildren().remove(mds);
				// client.addOrModifyResource(rd, null);
				// }
			}
		}
		return rd;
	}

	@Override
	public ResourceDescriptor modifyReportUnitResource(IProgressMonitor monitor, ResourceDescriptor runit,
			ResourceDescriptor rd, File inFile) throws Exception {
		rd.fixStructure();
		if (rd.getIsReference()) {
			if (!rd.getWsType().equals(ResourceDescriptor.TYPE_REFERENCE)) {
				rd.setIsReference(false);
				return client.addOrModifyResource(rd, inFile);
			}
		}
		return client.modifyReportUnitResource(runit.getUriString(), rd, inFile);
	}

	@Override
	public void delete(IProgressMonitor monitor, ResourceDescriptor rd) throws Exception {
		client.delete(rd);
	}

	@Override
	public ResourceDescriptor delete(IProgressMonitor monitor, ResourceDescriptor rd, ResourceDescriptor runit)
			throws Exception {
		client.delete(rd, runit.getUriString());
		return runit;
	}

	@Override
	public ReportExecution runReport(IProgressMonitor monitor, ReportExecution repExec) throws Exception {
		repExec.setStatus("ready"); //$NON-NLS-1$
		repExec.setFiles(new HashMap<String, FileContent>());
		repExec.setFiles(client.runReport(repExec.getResourceDescriptor(), repExec.getPrm(), repExec.getArgs()));
		return repExec;
	}

	@Override
	public void cancelReport(IProgressMonitor monitor, ReportExecution repExec) throws Exception {
	}

	@Override
	public List<ResourceDescriptor> listDatasources(IProgressMonitor monitor, IDatasourceFilter f) throws Exception {
		List<ResourceDescriptor> list = client.listDatasources();
		if (f != null) {
			List<ResourceDescriptor> toremove = new ArrayList<ResourceDescriptor>();
			for (ResourceDescriptor rd : list)
				if (!f.isDatasource(rd))
					toremove.add(rd);
			list.removeAll(toremove);
		}
		return list;
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
	public String getPassword(IProgressMonitor monitor) throws Exception {
		if (parent != null)
			return parent.getPassword(monitor);
		return Pass.getPass(sp.getPass());
	}

	@Override
	public void findResources(IProgressMonitor monitor, AFinderUI callback) throws Exception {
		throw new UnsupportedOperationException(Messages.SoapConnection_45);
	}

	@Override
	public ResourceDescriptor toResourceDescriptor(ClientResource<?> rest) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isSupported(Feature f) {
		return false;
	}

	@Override
	public void reorderInputControls(String uri, List<ResourceDescriptor> rds, IProgressMonitor monitor)
			throws Exception {
		ResourceDescriptor runit = new ResourceDescriptor();
		runit.setUriString(uri);
		runit = get(monitor, runit, null);

		List<ResourceDescriptor> toDel = new ArrayList<ResourceDescriptor>();
		for (ResourceDescriptor r : runit.getChildren()) {
			if (r.getWsType().equals(ResourceDescriptor.TYPE_INPUT_CONTROL)) {
				delete(monitor, r, runit);
				toDel.add(r);
			}
		}
		runit.getChildren().removeAll(toDel);
		for (ResourceDescriptor r : rds) {
			r.setIsNew(true);
			if (!r.getParentFolder().endsWith("_files")) { //$NON-NLS-1$
				r.setIsReference(true);
				r.setReferenceUri(r.getUriString());
				r.setParentFolder(uri + "_files"); //$NON-NLS-1$
			}
			r.setUriString(uri + "_files/" + r.getName()); //$NON-NLS-1$
			PublishUtil.setChild(runit, r);
		}
		addOrModifyResource(monitor, runit, null);
	}

	@Override
	public ResourceDescriptor initInputControls(String uri, String type, IProgressMonitor monitor) throws Exception {
		ResourceDescriptor rdrepunit = WSClientHelper.getReportUnit(monitor, uri);
		// List<ResourceDescriptor> list = list(monitor, rdrepunit);
		List<ResourceDescriptor> inputcontrols = new ArrayList<ResourceDescriptor>();
		Set<String> icNames = new HashSet<String>();
		String dsUri = null;
		for (ResourceDescriptor sub_rd : rdrepunit.getChildren()) {
			String wsType = sub_rd.getWsType();
			if (wsType.equals(ResourceDescriptor.TYPE_INPUT_CONTROL)) {
				inputcontrols.add(sub_rd);
				icNames.add(sub_rd.getName());
			} else if (SelectorDatasource.isDatasource(sub_rd) && sub_rd.getIsReference())
				dsUri = sub_rd.getReferenceUri();
			else if (SelectorDatasource.isDatasource(sub_rd))
				dsUri = sub_rd.getUriString();
		}

		for (int i = 0; i < inputcontrols.size(); ++i) {
			ResourceDescriptor ic = inputcontrols.get(i);
			if (InputControlsManager.isICQuery(ic)) {
				String dsUriQuery = getDataSourceQueryURI(dsUri, ic);
				ic.setResourceProperty(ResourceDescriptor.PROP_QUERY_DATA, null);
				// Ask to add values to the control....
				List<Argument> args = new ArrayList<Argument>();
				args.add(new Argument(Argument.IC_GET_QUERY_DATA, dsUriQuery));
				args.add(new Argument(Argument.RU_REF_URI, uri));
				ic = client.get(ic, null, args);
				cascadingDependencies(ic, icNames);
			} else if (InputControlsManager.isICListOfValues(ic) && !ic.getChildren().isEmpty()) {
				ResourceDescriptor rd2 = (ResourceDescriptor) ic.getChildren().get(0);
				if (rd2.getWsType().equals(ResourceDescriptor.TYPE_REFERENCE)) {
					ResourceDescriptor tmpRd = new ResourceDescriptor();
					tmpRd.setUriString(rd2.getReferenceUri());
					tmpRd = get(monitor, tmpRd, null);
					ic.setListOfValues(tmpRd.getListOfValues());
				} else
					ic.setListOfValues(rd2.getListOfValues());
			}
			for (int j = 0; j < rdrepunit.getChildren().size(); j++) {
				ResourceDescriptor r = rdrepunit.getChildren().get(j);
				if (r.getName() != null && r.getName().equals(ic.getName()))
					rdrepunit.getChildren().set(j, ic);
			}
		}
		return rdrepunit;
	}

	private void cascadingDependencies(ResourceDescriptor ic, Set<String> icNames) {
		List<ResourceDescriptor> children = ic.getChildren();
		for (ResourceDescriptor sub_ic : children) {
			if (!InputControlsManager.isRDQuery(sub_ic))
				continue;
			String queryString = sub_ic.getSql();
			String lang = sub_ic.getResourceProperty(ResourceDescriptor.PROP_QUERY_LANGUAGE).getValue();
			if (!Misc.isNullOrEmpty(queryString)) {
				List<String> parameters = new ArrayList<String>();
				JRDesignQuery query = new JRDesignQuery();
				query.setText(queryString);
				if (lang != null)
					query.setLanguage(lang);
				for (JRQueryChunk chunk : query.getChunks()) {
					switch (chunk.getType()) {
					case JRQueryChunk.TYPE_TEXT:
						break;
					case JRQueryChunk.TYPE_PARAMETER_CLAUSE:
					case JRQueryChunk.TYPE_PARAMETER:
						String paramName = chunk.getText().trim();
						if (!parameters.contains(paramName) && icNames.contains(paramName))
							parameters.add(paramName);
						break;
					case JRQueryChunk.TYPE_CLAUSE_TOKENS:
						String[] tokens = chunk.getTokens();
						if (tokens.length > 2) {
							for (String t : tokens) {
								t = t.trim();
								if (!parameters.contains(t) && icNames.contains(t))
									parameters.add(t);
							}
						}
						break;
					}
				}
				if (!parameters.isEmpty())
					ic.setMasterInputControls(parameters);
			}
			break;
		}
	}

	@Override
	public List<ResourceDescriptor> cascadeInputControls(ResourceDescriptor runit, List<ResourceDescriptor> ics,
			IProgressMonitor monitor) throws Exception {
		String dsUri = null;
		for (ResourceDescriptor sub_rd : runit.getChildren()) {
			if (SelectorDatasource.isDatasource(sub_rd) && sub_rd.getIsReference())
				dsUri = sub_rd.getReferenceUri();
			else if (SelectorDatasource.isDatasource(sub_rd))
				dsUri = sub_rd.getUriString();
		}
		String ruri = runit.getUriString();
		List<ResourceDescriptor> res = new ArrayList<ResourceDescriptor>();
		for (ResourceDescriptor rd : ics)
			res.add(updateControl(ruri, dsUri, rd, monitor));
		return res;
	}

	private ResourceDescriptor updateControl(String runit, String dsUri, ResourceDescriptor rd,
			IProgressMonitor monitor) throws Exception {
		List<Argument> args = new ArrayList<Argument>();

		args.add(new Argument(Argument.IC_GET_QUERY_DATA, getDataSourceQueryURI(dsUri, rd)));
		args.add(new Argument(Argument.RU_REF_URI, runit));

		rd.getParameters().clear();
		rd.setResourceProperty(ResourceDescriptor.PROP_QUERY_DATA, null);
		Map<String, Object> parameters = rd.getIcValues();
		for (String key : parameters.keySet()) {
			Object value = parameters.get(key);
			if (value == null)
				continue;
			if (value instanceof Collection)
				for (String item : ((Collection<String>) value)) {
					ListItem l = new ListItem(key, item);
					l.setIsListItem(true);
					rd.getParameters().add(l);
				}
			else
				rd.getParameters().add(new ListItem(key, value));
		}
		return client.get(rd, null, args);
	}

	private static String getDataSourceQueryURI(String dsUri, ResourceDescriptor ic) {
		String dsUriQuery = null;
		// reset query data...
		// Look if this query has a specific datasource...
		for (int k = 0; dsUriQuery == null && k < ic.getChildren().size(); ++k) {
			ResourceDescriptor sub_ic = (ResourceDescriptor) ic.getChildren().get(k);
			if (InputControlsManager.isRDQuery(sub_ic))
				for (int k2 = 0; k2 < sub_ic.getChildren().size(); ++k2) {
					ResourceDescriptor sub_sub_ic = (ResourceDescriptor) sub_ic.getChildren().get(k2);
					if (SelectorDatasource.isDatasource(sub_sub_ic)) {
						dsUriQuery = sub_sub_ic.getUriString();
						break;
					}
				}
		}
		if (dsUriQuery == null)
			dsUriQuery = dsUri;
		return dsUriQuery;
	}

	private IConnection parent;

	@Override
	public void setParent(IConnection parent) {
		this.parent = parent;
	}

	public static File writeToTemp(byte[] b64data) throws IOException {
		File inputFile = FileUtils.createTempFile("save", "jrxml"); //$NON-NLS-1$ //$NON-NLS-2$
		inputFile.deleteOnExit();
		org.apache.commons.io.FileUtils.write(inputFile, new String(Base64.decodeBase64(b64data)));
		return inputFile;
	}

	@Override
	public StateDto importMetaData(ImportOptions options, IProgressMonitor monitor) throws Exception {
		return null;
	}

	@Override
	public StateDto exportMetaData(ExportOptions options, IProgressMonitor monitor) throws Exception {
		return null;
	}

	@Override
	public Integer getPermissionMask(ResourceDescriptor rd, IProgressMonitor monitor) throws Exception {
		return 1;

		// FIXME, we could claculate manually the effective permission for a
		// resource

		// WSObjectPermission[] m =
		// client.getPermissionsManagement().getPermissionsForObject("repo:" +
		// rd.getUriString());
		// int min = 100;
		// for (WSObjectPermission p : m) {
		// int pmask = p.getPermissionMask();
		// if (pmask == 1) {
		// min = 1;
		// break;
		// }
		// min = Math.min(pmask, min);
		// }
		// if (min == 100)
		// min = 0;
		// rd.setPermissionMask(min);
		// return min;
	}

	@Override
	public List<RepositoryPermission> getPermissions(ResourceDescriptor rd, IProgressMonitor monitor,
			PermissionOptions options) throws Exception {
		WSObjectPermission[] m = client.getPermissionsManagement().getPermissionsForObject("repo:" + rd.getUriString()); //$NON-NLS-1$
		List<RepositoryPermission> perms = new ArrayList<RepositoryPermission>();
		for (WSObjectPermission p : m) {
			String uri = p.getUri();
			if (uri != null)
				uri = uri.replaceAll("repo:", ""); //$NON-NLS-1$ //$NON-NLS-2$
			Object prec = p.getPermissionRecipient();
			if (prec instanceof WSRole && !options.isRecipientTypeUser())
				perms.add(new RepositoryPermission(uri, ((WSRole) prec).getRoleName(), p.getPermissionMask()));
			else if (prec instanceof WSUser && options.isRecipientTypeUser())
				perms.add(new RepositoryPermission(uri, ((WSUser) prec).getUsername(), p.getPermissionMask()));
		}
		return perms;
	}

	@Override
	public ClientUser getUser(IProgressMonitor monitor) throws Exception {
		return null;
	}

	@Override
	public List<RepositoryPermission> setPermissions(ResourceDescriptor rd, List<RepositoryPermission> perms,
			PermissionOptions options, IProgressMonitor monitor) throws Exception {
		return perms;
	}

	@Override
	public void uploadJdbcDrivers(JdbcDriver driver, IProgressMonitor monitor) throws Exception {

	}

	@Override
	public JdbcDriverInfo getJdbcDrivers(IProgressMonitor monitor) throws Exception {
		return null;
	}

}
