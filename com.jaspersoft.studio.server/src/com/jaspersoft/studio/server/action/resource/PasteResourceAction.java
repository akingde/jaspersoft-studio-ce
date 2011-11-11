/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.server.action.resource;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.ui.actions.Clipboard;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.ireport.jasperserver.ws.WSClient;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.MFolder;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.utils.UIUtils;

public class PasteResourceAction extends Action {
	private static final String ID = "PASTERESOURCEDESCRIPTOR";
	private TreeViewer treeViewer;
	private TreeSelection s;

	public PasteResourceAction(TreeViewer treeViewer) {
		super();
		setId(ID);
		setText(Messages.common_paste);
		setToolTipText(Messages.common_paste);
		ISharedImages sharedImages = PlatformUI.getWorkbench()
				.getSharedImages();
		setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
		setDisabledImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE_DISABLED));
		this.treeViewer = treeViewer;
	}

	@Override
	public boolean isEnabled() {
		boolean res = super.isEnabled();
		if (res && Clipboard.getDefault().getContents() != null
				&& Clipboard.getDefault().getContents() instanceof List<?>) {
			List<?> list = (List<?>) Clipboard.getDefault().getContents();
			ANode parent = getSelected();
			for (Object obj : list)
				if (obj instanceof MResource && obj instanceof ICopyable) {
					ICopyable c = (ICopyable) obj;
					if (c.isCopyable2(parent))
						return true;
				}
		}
		return false;
	}

	private ANode getSelected() {
		s = (TreeSelection) treeViewer.getSelection();
		TreePath[] p = s.getPaths();
		for (int i = 0; i < p.length; i++) {
			final Object obj = p[i].getLastSegment();
			if (obj instanceof MResource || obj instanceof MServerProfile) {
				return (ANode) obj;
			}
		}
		return null;
	}

	@Override
	public void run() {
		final ANode parent = getSelected();
		final List<?> list = (List<?>) Clipboard.getDefault().getContents();
		ProgressMonitorDialog pm = new ProgressMonitorDialog(Display
				.getDefault().getActiveShell());
		try {
			pm.run(true, true, new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {
					try {
						doWork(monitor, parent, list);
						Display.getDefault().asyncExec(new Runnable() {

							public void run() {
								treeViewer.refresh(true);
								treeViewer.setSelection(s);
							}
						});
					} catch (Throwable e) {
						throw new InvocationTargetException(e);
					} finally {
						monitor.done();
					}
				}

			});
		} catch (InvocationTargetException e) {
			UIUtils.showError(e);
		} catch (InterruptedException e) {
			UIUtils.showError(e);
		}
	}

	private void doWork(IProgressMonitor monitor, ANode parent, List<?> list)
			throws Exception {
		MServerProfile sp = (MServerProfile) parent.getRoot();
		String dURI = ((MResource) parent).getValue().getUriString();
		WSClient ws = sp.getWsClient();

		monitor.beginTask("Paste elements to: " + dURI, list.size());
		for (Object obj : list) {
			if (obj instanceof MResource && obj instanceof ICopyable) {
				MResource m = (MResource) obj;
				if (m.isCopyable2(parent)) {
					ResourceDescriptor origin = m.getValue();
					String newname = dURI + "/" + origin.getName();
					if (parent instanceof MFolder) {
						if (m.isCut()) {
							ws.move(origin, newname);
							m.setCut(false);
						} else {
							ResourceDescriptor newrd = ws.copy(origin, newname);
							newrd.setLabel(origin.getLabel() + " Copy");
							String newuri = dURI
									+ (dURI.endsWith("/") ? "" : "/")
									+ newrd.getName();
							newrd.setUriString(newuri);

							ws.addOrModifyResource(newrd, null);
						}
					} else if (parent instanceof MReportUnit) {
						ResourceDescriptor prd = (ResourceDescriptor) parent
								.getValue();
						prd.getChildren().add(origin);

						String ruuri = prd.getUriString();
						origin.setParentFolder(ruuri + "_files/"
								+ origin.getName());
						origin.setIsNew(true);
						String oldName = origin.getName();
						String oldLabel = origin.getLabel();

						origin.setName(getRName(oldName, prd.getChildren()));
						origin.setLabel(origin.getName());

						ws.modifyReportUnitResource(prd.getUriString(), origin,
								null);

						origin.setName(oldName);
						origin.setLabel(oldLabel);

						if (m.isCut()) {
							m.setCut(false);
							WSClientHelper.deleteResource(m);
						}
					}
				}
			}
			monitor.worked(1);
			if (monitor.isCanceled())
				break;
		}
		refreshNode(parent, monitor);
	}

	private String getRName(String name, List<?> children) {
		String n = name;
		int j = 0;
		for (int i = 0; i < children.size(); i++) {
			ResourceDescriptor r = (ResourceDescriptor) children.get(i);
			if (n.equals(r.getName())) {
				n = name + "_" + j;
				i = 0;
				j++;
			}
		}
		return n;
	}

	private void refreshNode(INode p, IProgressMonitor monitor)
			throws Exception {
		if (p instanceof MResource)
			WSClientHelper.refreshResource((MResource) p, monitor);
		else if (p instanceof MServerProfile) {
			WSClientHelper.listFolder(((MServerProfile) p),
					((MServerProfile) p).getWsClient(), "/", monitor, 2);
		}
	}
}
