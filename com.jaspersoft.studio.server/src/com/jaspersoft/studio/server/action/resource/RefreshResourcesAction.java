/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.action.resource;

import java.lang.reflect.InvocationTargetException;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.actions.ActionFactory;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.action.server.EditServerAction;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;

public class RefreshResourcesAction extends Action {
	private TreeViewer treeViewer;

	public RefreshResourcesAction(TreeViewer treeViewer) {
		super();
		setId(ActionFactory.REFRESH.getId());
		setText(Messages.RefreshResourcesAction_0);
		setToolTipText(Messages.common_delete);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/eclipseicons/reload.gif")); //$NON-NLS-1$
		setDisabledImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/eclipseicons/reload.gif")); //$NON-NLS-1$
		this.treeViewer = treeViewer;
	}

	@Override
	public void run() {
		final TreeSelection s = (TreeSelection) treeViewer.getSelection();
		TreePath[] p = s.getPaths();
		for (int i = 0; i < p.length; i++) {
			final Object obj = p[i].getLastSegment();
			if (obj instanceof AMResource) {
				ProgressMonitorDialog pm = new ProgressMonitorDialog(UIUtils.getShell());
				try {
					pm.run(true, true, new IRunnableWithProgress() {
						public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
							try {
								AMResource res = (AMResource) obj;
								WSClientHelper.refreshResource(res, monitor);
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
					UIUtils.showError(e.getCause());
				} catch (InterruptedException e) {
					UIUtils.showError(e);
				}
			} else if (obj instanceof MServerProfile) {
				EditServerAction.fillServerProfile((MServerProfile) obj, treeViewer);
			}
		}
	}
}
