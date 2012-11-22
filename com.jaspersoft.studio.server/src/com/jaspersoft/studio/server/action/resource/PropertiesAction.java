/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.wizard.resource.ResourceWizard;
import com.jaspersoft.studio.utils.UIUtils;

public class PropertiesAction extends Action {
	private static final String ID = "RESOURCEPROPERTIES";
	private TreeViewer treeViewer;

	public PropertiesAction(TreeViewer treeViewer) {
		super();
		setId(ID);
		setText(Messages.common_properties);
		setDescription(Messages.common_properties);
		setToolTipText(Messages.common_properties);
		this.treeViewer = treeViewer;
	}

	@Override
	public void run() {
		final TreeSelection s = (TreeSelection) treeViewer.getSelection();
		TreePath[] p = s.getPaths();
		for (int i = 0; i < p.length; i++) {
			final Object obj = p[i].getLastSegment();
			if (obj instanceof MResource) {
				try {
					MResource mres = (MResource) obj;
					mres.setValue(WSClientHelper.getResource(mres,
							mres.getValue()));

					ResourceWizard wizard = new ResourceWizard(mres, mres);
					WizardDialog dialog = new WizardDialog(Display.getDefault()
							.getActiveShell(), wizard);
					dialog.create();

					dorun(mres, dialog.open());
				} catch (Exception e) {
					UIUtils.showError(e);
				}
				break;
			}
		}
	}

	private void dorun(final MResource obj, final int result) {
		ProgressMonitorDialog pm = new ProgressMonitorDialog(Display
				.getDefault().getActiveShell());
		try {
			pm.run(true, true, new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {
					try {
						editResource(obj, monitor, result);
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

	private void editResource(final MResource res, IProgressMonitor monitor,
			int result) throws Exception {
		if (result == Dialog.OK) {
			WSClientHelper.saveResource(res, monitor);
		} else {
			WSClientHelper.refreshResource(res, monitor);
		}
	}
}
