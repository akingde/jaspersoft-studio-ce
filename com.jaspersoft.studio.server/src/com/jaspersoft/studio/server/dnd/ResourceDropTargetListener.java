/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.dnd;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.TreeItem;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.dnd.NodeTransfer;
import com.jaspersoft.studio.dnd.NodeTreeDropAdapter;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.action.resource.PasteResourceAction;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.MFolder;
import com.jaspersoft.studio.server.model.MReference;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.protocol.IConnection;

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
		final List<AMResource> mc = new ArrayList<>();
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
						List<INode> toRefresh = new ArrayList<>();
						if (((ANode) target).getParent() instanceof MReportUnit
								|| (!(target instanceof MFolder) && ((ANode) target).getParent() instanceof MFolder)
						// || ((ANode) target).getParent() instanceof MServerProfile
						)
							target = ((ANode) target).getParent();

						if (target instanceof MFolder) {
							MFolder f = (MFolder) target;
							for (AMResource amr : droppedObjects) {
								try {
									if (amr.getParent() == f)
										continue;
									toRefresh.add(amr.getParent());
									((MFolder) target).getWsClient().move(monitor, amr.getValue(),
											f.getValue().getUriString());
									toRefresh.add((INode) target);
								} catch (Exception e) {
									UIUtils.showError(e);
								}
							}
						} else if (target instanceof MServerProfile) {
							for (AMResource amr : droppedObjects) {
								try {
									toRefresh.add(amr.getParent());
									((MServerProfile) target).getWsClient().move(monitor, amr.getValue(), "/");
								} catch (Exception e) {
									UIUtils.showError(e);
								}
							}
						} else if (target instanceof MReportUnit) {
							IConnection c = ((MReportUnit) target).getWsClient();
							ResourceDescriptor prd = ((MReportUnit) target).getValue();
							for (AMResource amr : droppedObjects) {
								toRefresh.add(amr.getParent());

								try {
									PasteResourceAction.putIntoReportUnit(monitor, (MReportUnit) target, c,
											amr.getValue());
								} catch (Exception e) {
									UIUtils.showError(e);
								}
							}
							try {
								c.addOrModifyResource(monitor, prd, null);
							} catch (Exception e) {
								UIUtils.showError(e);
							}
						}
						for (INode n : toRefresh)
							try {
								if (!hasParent(toRefresh, n) || n instanceof MServerProfile)
									PasteResourceAction.refreshNode(n, monitor);
							} catch (Exception e) {
								UIUtils.showError(e);
							}
						try {
							if (target instanceof MReference)
								target = ((ANode) target).getParent();
							if (target instanceof AMResource)
								WSClientHelper.refreshResource((AMResource) target, monitor);
							else if (target instanceof MServerProfile)
								WSClientHelper.listFolder((MServerProfile) target,
										((MServerProfile) target).getWsClient(), "/", monitor, 0);
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
		List<AMResource> res = new ArrayList<>();
		TreeSelection sel = (TreeSelection) viewer.getSelection();
		for (Object obj : sel.toList())
			if (obj instanceof AMResource)
				res.add((AMResource) obj);
		List<AMResource> toDel = new ArrayList<>();
		for (AMResource amr : res)
			if (hasParent(res, amr))
				toDel.add(amr);
		res.removeAll(toDel);
		return res;
	}

	private boolean hasParent(List<? extends INode> lst, INode amr) {
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
				TreeSelection sel = (TreeSelection) viewer.getSelection();
				for (Object obj : sel.toArray()) {
					if (obj instanceof AMResource) {
						if (d instanceof AMResource
								&& !PasteResourceAction.isSameServer((AMResource) d, (AMResource) obj))
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
