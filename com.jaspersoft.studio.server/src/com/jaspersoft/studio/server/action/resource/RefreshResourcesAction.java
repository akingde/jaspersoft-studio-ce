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
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.utils.UIUtils;

public class RefreshResourcesAction extends Action {
	private TreeViewer treeViewer;

	public RefreshResourcesAction(TreeViewer treeViewer) {
		super();
		setId(ActionFactory.REFRESH.getId());
		setText("Refresh");
		setToolTipText(Messages.common_delete);
		setImageDescriptor(JaspersoftStudioPlugin
				.getImageDescriptor("icons/eclipseicons/reload.gif"));
		setDisabledImageDescriptor(JaspersoftStudioPlugin
				.getImageDescriptor("icons/eclipseicons/reloaddgif"));
		this.treeViewer = treeViewer;
	}

	@Override
	public void run() {
		final TreeSelection s = (TreeSelection) treeViewer.getSelection();
		TreePath[] p = s.getPaths();
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
								MResource res = (MResource) obj;
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
			}
		}
	}
}
