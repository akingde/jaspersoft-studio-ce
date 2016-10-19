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
package com.jaspersoft.studio.server.dnd;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.TreeItem;

import com.jaspersoft.studio.dnd.NodeTransfer;
import com.jaspersoft.studio.dnd.NodeTreeDropAdapter;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.action.resource.PasteResourceAction;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.MFolder;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.server.MServerProfile;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

/**
 * A target drop listener that creates a generic file resource element when
 * something is dropped on the JRS repository tree.
 * 
 * @author veaceslav chicu
 * 
 */
public class ResourceDropTargetListener extends NodeTreeDropAdapter implements TransferDropTargetListener {

	public ResourceDropTargetListener(TreeViewer treeViewer) {
		super(treeViewer);
	}

	@Override
	public boolean performDrop(Object data) {
		if (data == null)
			return false;
		final List<AMResource> mc = new ArrayList<AMResource>();
		if (data.getClass().isArray()) {
			Object[] ar = (Object[]) data;
			for (Object obj : ar)
				if (obj instanceof AMResource)
					mc.add((AMResource) obj);
		} else if (data instanceof AMResource)
			mc.add((AMResource) data);
		final List<AMResource> droppedObjects = getDroppedObjects();
		Job job = new Job("Moving elements") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				IStatus status = Status.OK_STATUS;
				monitor.beginTask("Moving elements", IProgressMonitor.UNKNOWN);
				try {
					Object target = getCurrentTarget();
					if (target instanceof ANode) {
						List<INode> toRefresh = new ArrayList<INode>();
						if (target instanceof MFolder) {
							MFolder f = (MFolder) target;
							for (AMResource amr : droppedObjects) {
								try {
									toRefresh.add(amr.getParent());
									((MFolder) target).getWsClient().move(monitor, amr.getValue(),
											f.getValue().getUriString());
								} catch (Exception e) {
									UIUtils.showError(e);
								}
							}
						} else if (target instanceof MServerProfile) {
							for (AMResource amr : droppedObjects) {
								try {
									toRefresh.add(amr.getParent());
									((MFolder) target).getWsClient().move(monitor, amr.getValue(), "/");
								} catch (Exception e) {
									UIUtils.showError(e);
								}
							}
						} else if (target instanceof MReportUnit) {

						}
						for (INode n : toRefresh)
							try {
								if (!hasParent(toRefresh, (AMResource) n))
									PasteResourceAction.refreshNode(n, monitor);
							} catch (Exception e) {
								UIUtils.showError(e);
							}
						try {
							WSClientHelper.refreshResource((AMResource) target, monitor);
						} catch (Exception e) {
							UIUtils.showError(e);
						}
					}
				} finally {
					monitor.done();
				}
				return status;
			}
		};
		job.setPriority(Job.LONG);
		job.setUser(true);
		job.schedule();
		return true;
	}

	private List<AMResource> getDroppedObjects() {
		TreeViewer viewer = (TreeViewer) getViewer();
		List<AMResource> res = new ArrayList<AMResource>();
		for (Object obj : viewer.getStructuredSelection().toList())
			if (obj instanceof AMResource)
				res.add((AMResource) obj);
		List<AMResource> toDel = new ArrayList<AMResource>();
		for (AMResource amr : res)
			if (hasParent(res, amr))
				toDel.add(amr);
		res.removeAll(toDel);
		return res;
	}

	private boolean hasParent(List<? extends INode> lst, AMResource amr) {
		INode parent = amr.getParent();
		while (parent != null) {
			if (lst.contains(parent))
				return true;
			parent = parent.getParent();
		}
		return false;
	}

	@Override
	public boolean isEnabled(DropTargetEvent event) {
		if (event.item instanceof TreeItem) {
			TreeItem item = (TreeItem) event.item;
			Object d = item.getData();
			if (d instanceof MReportUnit)
				return false;
			if (d instanceof AMResource && ResourceDragSourceListener.isDragable(((AMResource) d).getParent())) {
				TreeViewer viewer = (TreeViewer) getViewer();
				for (Object obj : viewer.getStructuredSelection().toArray()) {
					if (obj instanceof AMResource) {
						if (!PasteResourceAction.isSameServer((AMResource) d, (AMResource) obj))
							return false;
						continue;
					}
					return false;
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public Transfer getTransfer() {
		return NodeTransfer.getInstance();
	}

}
