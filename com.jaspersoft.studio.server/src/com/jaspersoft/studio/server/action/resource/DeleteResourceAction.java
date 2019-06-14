/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.action.resource;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.MFolder;
import com.jaspersoft.studio.server.model.MJrxml;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.server.MServerProfile;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

public class DeleteResourceAction extends Action {

	public static final String ID = "com.jaspersoft.studio.server.action.resource.deleteResourceAction";

	private TreeViewer treeViewer;

	public DeleteResourceAction(TreeViewer treeViewer) {
		super();
		setId(ID);
		setText(Messages.common_delete);
		setToolTipText(Messages.common_delete);
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED));
		this.treeViewer = treeViewer;
	}

	@Override
	public boolean isEnabled() {
		Object firstElement = ((TreeSelection) treeViewer.getSelection()).getFirstElement();
		boolean b = (firstElement instanceof AMResource);
		if (b) {
			AMResource mres = (AMResource) firstElement;
			int pmask = mres.getValue().getPermissionMask(mres.getWsClient());
			b = b && (pmask == 1 || (pmask & 16) == 16);
			if (AddResourceAction.isSpecialFolder(mres))
				return false;
			if (firstElement instanceof MJrxml && ((MJrxml) firstElement).getValue().isMainReport())
				return false;
		}
		return b;
	}

	@Override
	public void run() {
		TreeSelection s = (TreeSelection) treeViewer.getSelection();
		final TreePath[] p = s.getPaths();
		if (!UIUtils.showDeleteConfirmation())
			return;
		ProgressMonitorDialog pm = new ProgressMonitorDialog(UIUtils.getShell());
		try {
			pm.run(true, true, new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					monitor.beginTask(Messages.DeleteResourceAction_0, p.length);
					try {
						Set<ANode> toRefresh = new HashSet<>();
						Set<MReportUnit> set = new HashSet<>();
						Set<MReportUnit> deleted = new HashSet<>();
						for (int i = 0; i < p.length; i++) {
							final Object obj = p[i].getLastSegment();
							if (obj instanceof AMResource) {
								if (((ANode) obj).getParent() != null)
									toRefresh.add(((ANode) obj).getParent());
								AMResource mres = (AMResource) obj;
								if (mres.getParent() instanceof MServerProfile || mres.getParent() instanceof MFolder) {
									deleteResource(monitor, (AMResource) obj);
									if (mres instanceof MReportUnit)
										deleted.add((MReportUnit) mres);
								} else if (mres.getParent() instanceof MReportUnit) {
									MReportUnit mrunit = (MReportUnit) mres.getParent();
									mrunit.getChildren().remove(mres);
									if (deleted.contains(mrunit))
										continue;
									set.add(mrunit);
									ResourceDescriptor toDel = null;
									List<ResourceDescriptor> children = mrunit.getValue().getChildren();
									String uri = mres.getValue().getUriString();
									for (ResourceDescriptor rd : children) {
										if (rd.getUriString() != null && rd.getUriString().equals(uri)) {
											toDel = rd;
											break;
										}
									}
									if (toDel != null)
										children.remove(toDel);
								}
							}
						}
						for (MReportUnit mrunit : set) {
							if (deleted.contains(mrunit))
								continue;
							try {
								monitor.subTask(mrunit.getDisplayText());
								WSClientHelper.save(monitor, mrunit);
								deleted.add(mrunit);
								WSClientHelper.refreshResource(mrunit, monitor);
							} catch (Throwable e) {
								UIUtils.showError(e);
							}
						}
						for (ANode n : toRefresh) {
							if (n instanceof AMResource)
								try {
									WSClientHelper.refreshResource((AMResource) n, monitor);
								} catch (Exception e) {
									e.printStackTrace();
								}
							else if (n instanceof MServerProfile)
								try {
									WSClientHelper.connectGetData((MServerProfile) n, monitor);
								} catch (Exception e) {
									e.printStackTrace();
								}
						}
						UIUtils.getDisplay().asyncExec(() -> treeViewer.refresh(true));
					} finally {
						monitor.done();
					}
				}

				private void deleteResource(IProgressMonitor monitor, final AMResource mres) {
					try {
						monitor.subTask(mres.getDisplayText());
						WSClientHelper.deleteResource(monitor, mres);
					} catch (Throwable e) {
						UIUtils.showError(e);
					}
				}
			});
		} catch (InvocationTargetException e) {
			UIUtils.showError(e.getCause());
		} catch (InterruptedException e) {
			UIUtils.showError(e);
		}
	}
}
