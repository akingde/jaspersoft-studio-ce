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
package com.jaspersoft.studio.server.action.resource;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.utils.UIUtils;

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
		return firstElement != null && (firstElement instanceof MResource);
	}

	@Override
	public void run() {
		TreeSelection s = (TreeSelection) treeViewer.getSelection();
		TreePath[] p = s.getPaths();
		if (!UIUtils.showDeleteConfirmation())
			return;
		for (int i = 0; i < p.length; i++) {
			final Object obj = p[i].getLastSegment();
			if (obj instanceof MResource) {
				ProgressMonitorDialog pm = new ProgressMonitorDialog(Display
						.getDefault().getActiveShell());
				try {
					pm.run(true, true, new IRunnableWithProgress() {
						public void run(IProgressMonitor monitor)
								throws InvocationTargetException,
								InterruptedException {
							try {
								WSClientHelper.deleteResource((MResource) obj);
								Display.getDefault().asyncExec(new Runnable() {

									public void run() {
										treeViewer.refresh(true);
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
		}
	}
}
