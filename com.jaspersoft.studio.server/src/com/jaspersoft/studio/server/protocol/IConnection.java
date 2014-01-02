package com.jaspersoft.studio.server.protocol;

import java.io.File;
import java.text.Format;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.ireport.jasperserver.ws.FileContent;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.Argument;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.resources.ClientResource;
import com.jaspersoft.jasperserver.dto.serverinfo.ServerInfo;
import com.jaspersoft.studio.server.AFinderUI;
import com.jaspersoft.studio.server.model.server.ServerProfile;

public interface IConnection {

	public Format getDateFormat();

	public Format getTimestampFormat();

	public Format getNumberFormat();

	public boolean connect(IProgressMonitor monitor, ServerProfile sp) throws Exception;

	public ServerInfo getServerInfo(IProgressMonitor monitor) throws Exception;

	public String getWebservicesUri();

	public String getUsername();

	public String getPassword();

	public ResourceDescriptor get(IProgressMonitor monitor, ResourceDescriptor rd, File f) throws Exception;

	public ResourceDescriptor get(IProgressMonitor monitor, ResourceDescriptor rd, File outFile, java.util.List<Argument> args) throws Exception;

	public List<ResourceDescriptor> list(IProgressMonitor monitor, ResourceDescriptor rd) throws Exception;

	public ResourceDescriptor move(IProgressMonitor monitor, ResourceDescriptor rd, String destFolderURI) throws Exception;

	public ResourceDescriptor copy(IProgressMonitor monitor, ResourceDescriptor rd, String destFolderURI) throws Exception;

	public ResourceDescriptor addOrModifyResource(IProgressMonitor monitor, ResourceDescriptor rd, File inputFile) throws Exception;

	public ResourceDescriptor modifyReportUnitResource(IProgressMonitor monitor, String rUnitUri, ResourceDescriptor rd, File inFile) throws Exception;

	public void delete(IProgressMonitor monitor, ResourceDescriptor rd) throws Exception;

	public void delete(IProgressMonitor monitor, ResourceDescriptor rd, String reportUnitUri) throws Exception;

	public Map<String, FileContent> runReport(IProgressMonitor monitor, ResourceDescriptor rd, java.util.Map<String, Object> prm, List<Argument> args) throws Exception;

	public List<ResourceDescriptor> listDatasources(IProgressMonitor monitor) throws Exception;

	public void findResources(IProgressMonitor monitor, AFinderUI callback) throws Exception;

	public ResourceDescriptor toResourceDescriptor(ClientResource<?> rest) throws Exception;

	public boolean isSupported(Feature f);
}
