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
package com.jaspersoft.studio.server.action.resource;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.MFolder;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;

public class DeleteResourceAction extends Action {
	private TreeViewer treeViewer;

	public DeleteResourceAction(TreeViewer treeViewer) {
		super();
		setId(ActionFactory.DELETE.getId());
		setAccelerator(SWT.DEL);
		setText(Messages.common_delete);
		setToolTipText(Messages.common_delete);
		ISharedImages sharedImages = PlatformUI.getWorkbench()
				.getSharedImages();
		setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
		setDisabledImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED));
		this.treeViewer = treeViewer;
	}

	@Override
	public boolean isEnabled() {
		Object firstElement = ((TreeSelection) treeViewer.getSelection())
				.getFirstElement();
		boolean b = firstElement != null && (firstElement instanceof MResource);
		if (b) {
			MResource mres = (MResource) firstElement;
			int pmask = mres.getValue().getPermissionMask(mres.getWsClient());
			b = b && (pmask == 1 || (pmask & 16) == 16);
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
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {
					monitor.beginTask("Deleting", p.length);
					try {
						Set<MReportUnit> set = new HashSet<MReportUnit>();
						Set<MReportUnit> deleted = new HashSet<MReportUnit>();
						for (int i = 0; i < p.length; i++) {
							final Object obj = p[i].getLastSegment();
							if (obj instanceof MResource) {
								MResource mres = (MResource) obj;
								if (mres.getParent() instanceof MServerProfile
										|| mres.getParent() instanceof MFolder) {
									deleteResource(monitor, (MResource) obj);
									if (mres instanceof MReportUnit)
										deleted.add((MReportUnit) mres);
								} else if (mres.getParent() instanceof MReportUnit) {
									MReportUnit mrunit = (MReportUnit) mres
											.getParent();
									mrunit.getChildren().remove(mres);
									if (deleted.contains(mrunit))
										continue;
									set.add(mrunit);
									ResourceDescriptor toDel = null;
									List<ResourceDescriptor> children = mrunit
											.getValue().getChildren();
									String uri = mres.getValue().getUriString();
									for (ResourceDescriptor rd : children) {
										if (rd.getUriString().equals(uri)) {
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
							} catch (Throwable e) {
								UIUtils.showError(e);
							}
						}
						UIUtils.getDisplay().asyncExec(new Runnable() {

							public void run() {
								treeViewer.refresh(true);
							}
						});
					} finally {
						monitor.done();
					}
				}

				private void deleteResource(IProgressMonitor monitor,
						final MResource mres) {
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
