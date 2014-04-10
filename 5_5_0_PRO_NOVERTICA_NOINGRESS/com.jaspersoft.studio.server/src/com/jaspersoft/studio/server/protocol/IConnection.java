package com.jaspersoft.studio.server.protocol;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.jaspersoft.ireport.jasperserver.ws.FileContent;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.Argument;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.model.server.ServerProfile;

public interface IConnection {
	public boolean connect(ServerProfile sp) throws Exception;

	public ServerInfo getServerInfo() throws Exception;

	public String getWebservicesUri();

	public String getUsername();

	public String getPassword();

	public ResourceDescriptor get(ResourceDescriptor rd, File f)
			throws Exception;

	public ResourceDescriptor get(ResourceDescriptor rd, File outFile,
			java.util.List<Argument> args) throws Exception;

	public List<ResourceDescriptor> list(ResourceDescriptor rd)
			throws Exception;

	public void move(ResourceDescriptor rd, String destFolderURI)
			throws Exception;

	public ResourceDescriptor copy(ResourceDescriptor rd, String destFolderURI)
			throws Exception;

	public ResourceDescriptor addOrModifyResource(ResourceDescriptor rd,
			File inputFile) throws Exception;

	public ResourceDescriptor modifyReportUnitResource(String rUnitUri,
			ResourceDescriptor rd, File inFile) throws Exception;

	public void delete(ResourceDescriptor rd) throws Exception;

	public void delete(ResourceDescriptor rd, String reportUnitUri)
			throws Exception;

	public Map<String, FileContent> runReport(ResourceDescriptor rd,
			java.util.Map<String, Object> prm, List<Argument> args)
			throws Exception;

	public List<ResourceDescriptor> listDatasources() throws Exception;
}
