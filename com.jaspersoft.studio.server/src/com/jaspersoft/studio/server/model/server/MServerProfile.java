/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.server.model.server;

import java.io.IOException;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.util.CastorUtil;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.jasperserver.dto.serverinfo.ServerInfo;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.server.ServerIconDescriptor;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.export.AExporter;
import com.jaspersoft.studio.server.protocol.Callback;
import com.jaspersoft.studio.server.protocol.IConnection;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/* 
 * 
 * @author schicu
 *
 */
public class MServerProfile extends ANode {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	public static final String MAPPINGFILE = "com/jaspersoft/studio/server/model/server/ServerProfileImpl.xml";

	public MServerProfile(ANode parent, ServerProfile server) {
		super(parent, -1);
		setValue(server);
	}

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	@Override
	public ServerProfile getValue() {
		return (ServerProfile) super.getValue();
	}

	@Override
	public INode getRoot() {
		return this;
	}

	@Override
	public void setJasperConfiguration(JasperReportsConfiguration jConfig) {
		super.setJasperConfiguration(jConfig);
		if (getParent() != null)
			((ANode) getParent().getRoot()).setJasperConfiguration(jConfig);
	}

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new ServerIconDescriptor("server"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		ServerProfile v = getValue();
		if (v != null && v.getName() != null && !v.getName().isEmpty())
			return v.getName();
		return getIconDescriptor().getTitle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getImagePath()
	 */
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getToolTip()
	 */
	@Override
	public String getToolTip() {
		ServerProfile v = getValue();
		if (v != null && v.getName() != null && !v.getName().isEmpty()) {
			String tt = v.getName();
			if (v.getUrl() != null)
				tt += "\n" + v.getUrl();
			if (v.getUser() != null)
				tt += "\nUser: " + v.getUser();
			String ci = getConnectionInfo();
			if (!Misc.isNullOrEmpty(ci))
				tt += "\n\n" + ci;
			return tt;
		}
		return getIconDescriptor().getTitle();
	}

	public String getConnectionInfo() {
		String tt = "";
		if (wsClient != null) {
			try {
				ServerInfo info = wsClient.getServerInfo(null);
				tt += "Version: " + info.getVersion();
				tt += "\nEdition: " + Misc.nvl(info.getEditionName()) + " " + (info.getEdition() != null ? info.getEdition() : "");
				tt += "\nBuild: " + Misc.nvl(info.getBuild());
				tt += "\nLicence Type: " + Misc.nvl(info.getLicenseType());
				tt += "\nExpiration: " + Misc.nvl(info.getExpiration());
				tt += "\nFeatures: " + Misc.nvl(info.getFeatures());
				tt += "\nDate Format: " + Misc.nvl(info.getDateFormatPattern());
				tt += "\nTimestamp Format: " + Misc.nvl(info.getDatetimeFormatPattern());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return tt;
	}

	public String toXML() {
		return CastorUtil.write(getValue(), MAPPINGFILE);
	}

	private transient IConnection wsClient;

	public IConnection getWsClient(final Callback<IConnection> c) {
		if (wsClient == null) {
			Job job = new Job("Building report") {
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					monitor.beginTask("", IProgressMonitor.UNKNOWN);
					try {
						getWsClient(monitor);
					} catch (Exception e) {
						// UIUtils.showError(e);
					} finally {
						if (c != null) {
							UIUtils.getDisplay().asyncExec(new Runnable() {

								@Override
								public void run() {
									c.completed(wsClient);
								}
							});
						}
					}
					return Status.OK_STATUS;
				}
			};
			job.setPriority(Job.SHORT);
			job.schedule();
		}
		return wsClient;
	}

	public IConnection getWsClient() {
		return wsClient;
	}

	public IConnection getWsClient(IProgressMonitor monitor) throws Exception {
		if (wsClient == null)
			WSClientHelper.connect(this, monitor);
		return wsClient;
	}

	public void setWsClient(IConnection wsClient) {
		this.wsClient = wsClient;
	}

	@Override
	public void setValue(Object value) {
		super.setValue(value);
		resetTmpPaths();
	}

	protected void resetTmpPaths() {
		tmpDir = null;
		AExporter.fileurimap.clear();
	}

	private transient IFolder tmpDir;

	public void setProjectPath(String projectPath) {
		getValue().setProjectPath(projectPath);
		resetTmpPaths();
	}

	public IFolder getTmpDir(IProgressMonitor monitor) throws IOException, CoreException {
		if (tmpDir == null || !tmpDir.exists()) {
			String prjpath = getValue().getProjectPath();
			if (prjpath != null && !prjpath.trim().isEmpty()) {
				String path = prjpath.trim();
				if (path.charAt(0) == '/')
					path = path.substring(1);
				int indx = path.indexOf("/");
				String ppath = path.substring(0, indx);
				String fpath = path.substring(indx);
				IProject prj = ResourcesPlugin.getWorkspace().getRoot().getProject(ppath);
				tmpDir = prj.getFolder(fpath);
			} else
				tmpDir = FileUtils.getInProjectFolder(FileUtils.createTempDir(getValue().getName().replace(" ", "") + "-").toURI(), monitor);
			if (!tmpDir.exists())
				tmpDir.create(true, true, monitor);
		}
		return tmpDir;
	}
}
