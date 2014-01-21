package com.jaspersoft.studio.server.protocol.soap;

import java.io.File;
import java.text.DateFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.axis.AxisProperties;
import org.apache.axis.components.net.DefaultCommonsHTTPClientProperties;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

import com.jaspersoft.ireport.jasperserver.ws.FileContent;
import com.jaspersoft.ireport.jasperserver.ws.JServer;
import com.jaspersoft.ireport.jasperserver.ws.WSClient;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.Argument;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.resources.ClientResource;
import com.jaspersoft.jasperserver.dto.serverinfo.ServerInfo;
import com.jaspersoft.studio.server.AFinderUI;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.editor.input.InputControlsManager;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.protocol.Feature;
import com.jaspersoft.studio.server.protocol.IConnection;
import com.jaspersoft.studio.server.wizard.resource.page.selector.SelectorDatasource;

public class SoapConnection implements IConnection {
	protected DateFormat dateFormat = SimpleDateFormat.getDateInstance();
	protected DateFormat timestampFormat = SimpleDateFormat.getTimeInstance();
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

	public Format getNumberFormat() {
		return numberFormat;
	}

	private WSClient client;
	private ServerInfo serverInfo;

	@Override
	public ServerInfo getServerInfo(IProgressMonitor monitor) throws Exception {
		if (serverInfo != null)
			return serverInfo;
		serverInfo = new ServerInfo();
		serverInfo.setVersion("4.5");
		// serverInfo.setVersion(client.getVersion());
		return serverInfo;
	}

	@Override
	public boolean connect(IProgressMonitor monitor, ServerProfile sp) throws Exception {
		JServer server = new JServer();
		this.sp = sp;
		setupJServer(server, sp);

		client = server.getWSClient();
		if (getServerInfo(monitor) == null)
			return false;
		return true;
	}

	private static void setupJServer(JServer server, ServerProfile sp) {
		AxisProperties.setProperty(DefaultCommonsHTTPClientProperties.MAXIMUM_CONNECTIONS_PER_HOST_PROPERTY_KEY, "4");
		server.setName(sp.getName());
		String rurl = sp.getUrl();
		if (rurl.endsWith("services/repository/"))
			rurl = rurl.substring(0, rurl.length() - 1);
		if (!rurl.endsWith("services/repository"))
			rurl += "services/repository";
		server.setUrl(rurl);
		String username = sp.getUser();
		if (sp.getOrganisation() != null && !sp.getOrganisation().trim().isEmpty())
			username += "|" + sp.getOrganisation();
		server.setUsername(username);
		server.setPassword(sp.getPass());
		server.setTimeout(sp.getTimeout());
		server.setChunked(sp.isChunked());
	}

	@Override
	public ResourceDescriptor get(IProgressMonitor monitor, ResourceDescriptor rd, File f) throws Exception {
		return client.get(rd, f);
	}

	@Override
	public List<ResourceDescriptor> list(IProgressMonitor monitor, ResourceDescriptor rd) throws Exception {
		return client.list(rd);
	}

	@Override
	public ResourceDescriptor get(IProgressMonitor monitor, ResourceDescriptor rd, File outFile, List<Argument> args) throws Exception {
		return client.get(rd, outFile, args);
	}

	@Override
	public ResourceDescriptor move(IProgressMonitor monitor, ResourceDescriptor rd, String destFolderURI) throws Exception {
		client.move(rd, destFolderURI);
		return rd;
	}

	@Override
	public ResourceDescriptor copy(IProgressMonitor monitor, ResourceDescriptor rd, String destFolderURI) throws Exception {
		return client.copy(rd, destFolderURI);
	}

	@Override
	public ResourceDescriptor addOrModifyResource(IProgressMonitor monitor, ResourceDescriptor rd, File inputFile) throws Exception {
		return client.addOrModifyResource(rd, inputFile);
	}

	@Override
	public ResourceDescriptor modifyReportUnitResource(IProgressMonitor monitor, String rUnitUri, ResourceDescriptor rd, File inFile) throws Exception {
		return client.modifyReportUnitResource(rUnitUri, rd, inFile);
	}

	@Override
	public void delete(IProgressMonitor monitor, ResourceDescriptor rd) throws Exception {
		client.delete(rd);
	}

	@Override
	public void delete(IProgressMonitor monitor, ResourceDescriptor rd, String reportUnitUri) throws Exception {
		client.delete(rd, reportUnitUri);
	}

	@Override
	public Map<String, FileContent> runReport(IProgressMonitor monitor, ResourceDescriptor rd, Map<String, Object> prm, List<Argument> args) throws Exception {
		return client.runReport(rd, prm, args);
	}

	@Override
	public List<ResourceDescriptor> listDatasources(IProgressMonitor monitor) throws Exception {
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

	@Override
	public void findResources(IProgressMonitor monitor, AFinderUI callback) throws Exception {
		throw new UnsupportedOperationException("Search not implemented for SOAP protocol.");
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
	public void reorderInputControls(String uri, List<ResourceDescriptor> rd, IProgressMonitor monitor) throws Exception {
	}

	@Override
	public ResourceDescriptor initInputControls(String uri, IProgressMonitor monitor) throws Exception {
		ResourceDescriptor rdrepunit = WSClientHelper.getReportUnit(monitor, uri);
		List<ResourceDescriptor> list = list(monitor, rdrepunit);
		List<ResourceDescriptor> inputcontrols = new ArrayList<ResourceDescriptor>();
		String dsUri = null;
		for (ResourceDescriptor sub_rd : list) {
			String wsType = sub_rd.getWsType();
			if (wsType.equals(ResourceDescriptor.TYPE_INPUT_CONTROL))
				inputcontrols.add(sub_rd);
			else if (wsType.equals(ResourceDescriptor.TYPE_DATASOURCE))
				dsUri = sub_rd.getReferenceUri();
			else if (SelectorDatasource.isDatasource(sub_rd))
				dsUri = sub_rd.getUriString();
		}
		for (int i = 0; i < inputcontrols.size(); ++i) {
			ResourceDescriptor ic = inputcontrols.get(i);
			if (InputControlsManager.isICQuery(ic)) {
				inputcontrols.remove(ic);

				String dsUriQuery = InputControlsManager.getDataSourceQueryURI(dsUri, ic);
				ic.setResourceProperty(ResourceDescriptor.PROP_QUERY_DATA, null);
				// Ask to add values to the control....
				List<Argument> args = new ArrayList<Argument>();
				args.add(new Argument(Argument.IC_GET_QUERY_DATA, dsUriQuery));
				args.add(new Argument(Argument.RU_REF_URI, uri));
				ic = get(new NullProgressMonitor(), ic, null, args);

				inputcontrols.add(i, ic);
			}
		}
		return rdrepunit;
	}
}
