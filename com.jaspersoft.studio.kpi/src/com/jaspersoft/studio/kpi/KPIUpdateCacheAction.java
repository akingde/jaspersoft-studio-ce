/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.kpi;


import java.util.LinkedHashMap;
import java.util.Map;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.jasperserver.dto.resources.AbstractClientReportUnit;
import com.jaspersoft.jasperserver.dto.resources.ClientResourceListWrapper;
import com.jaspersoft.jasperserver.dto.resources.ClientResourceLookup;
import com.jaspersoft.studio.kpi.messages.Messages;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.protocol.restv2.ARestV2Connection;
import com.jaspersoft.studio.server.protocol.restv2.RestV2ConnectionJersey;

public class KPIUpdateCacheAction extends Action {
	private static final String ID = "KPI_UPDATE_CAHCE_ACTION"; //$NON-NLS-1$
	private TreeViewer treeViewer;

	public KPIUpdateCacheAction(TreeViewer treeViewer) {
		super();
		setId(ID);
		setText(Messages.KPIUpdateCacheAction_updateCacheTitle);
		setToolTipText(Messages.KPIUpdateCacheAction_updateCacheTooltip);
		setImageDescriptor(Activator.getImageDescriptor("icons/key.png")); //$NON-NLS-1$
		setDisabledImageDescriptor(Activator.getImageDescriptor("icons/key.png")); //$NON-NLS-1$
		this.treeViewer = treeViewer;
	}

	@Override
	public boolean isEnabled() {
		// TODO: check the jasperreports server
		return true;
	}

	@Override
	public void run() {
		final TreeSelection s = (TreeSelection) treeViewer.getSelection();
		TreePath[] p = s.getPaths();
		for (int i = 0; i < p.length; i++) {
			Object obj = p[i].getLastSegment();
			if (obj instanceof MServerProfile) {
				
				
				final MServerProfile node = (MServerProfile) obj;
				
				Job job = new Job("Updating KPI Cache") { //$NON-NLS-1$
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						monitor.beginTask("Update KPI cache", IProgressMonitor.UNKNOWN); //$NON-NLS-1$
						try {
							
							final RestV2ConnectionJersey client = new RestV2ConnectionJersey();
							client.connect(monitor, node.getWsClient().getServerProfile());
							
							// for each report unit check if it has a KPI sub report...
							WebTarget tgt = client.getTarget().path("resources"); //$NON-NLS-1$
							
							tgt = tgt.queryParam("recursive", "true"); //$NON-NLS-1$ //$NON-NLS-2$
							tgt = tgt.queryParam("type", "reportUnit"); //$NON-NLS-1$ //$NON-NLS-2$
							tgt = tgt.queryParam("limit", 0); //$NON-NLS-1$
							
							Builder builder = tgt.request();
							
							ClientResourceListWrapper resources = client.toObj(builder.get(), ClientResourceListWrapper.class, monitor);
							
							Map<String, String> kpiReportUnits = new LinkedHashMap<String, String>();
							
							for (ClientResourceLookup resource : resources.getResourceLookups())
							{
								// Check if this resource has a KPI...
								String reportUnitKpiUri = resource.getUri() + "_files/KPI"; //$NON-NLS-1$
								
								WebTarget tgt2 = client.getTarget().path("resources" + reportUnitKpiUri); //$NON-NLS-1$
								tgt = tgt2.queryParam("expanded", "false"); //$NON-NLS-1$ //$NON-NLS-2$
								
								Builder req = tgt.request("application/repository.reportUnit+" + ARestV2Connection.FORMAT); //$NON-NLS-1$
								
								try {
									Object obj = client.toObj(req.get(), (Class<?>) null,monitor);
									if (obj != null && obj instanceof AbstractClientReportUnit)
									{
										kpiReportUnits.put(reportUnitKpiUri, resource.getUri());
									}
								} catch (Exception ex)
								{
									// KPI not found...
								}
							}
								
							KPIUtils.updateKPICache(client, kpiReportUnits, true);
							
							UIUtils.getDisplay().asyncExec(new Runnable() {
								
								@Override
								public void run() {
									MessageDialog.openInformation(UIUtils.getShell(), "KPI Cache Update", "KPI cache successfully updated."); //$NON-NLS-1$ //$NON-NLS-2$
								}
							});
							
							
						} catch (Exception e) {
							UIUtils.showError(e);
						} finally {
							monitor.done();
						}
						return Status.OK_STATUS;
					}
				};
				job.setPriority(Job.LONG);
				job.schedule();

				break;
			}
		}
	}
}