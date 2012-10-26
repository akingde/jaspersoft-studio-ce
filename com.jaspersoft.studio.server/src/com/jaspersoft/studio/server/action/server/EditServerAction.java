/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.server.action.server;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.MDummy;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.wizard.ServerProfileWizard;
import com.jaspersoft.studio.server.wizard.ServerProfileWizardDialog;
import com.jaspersoft.studio.utils.UIUtils;

public class EditServerAction extends Action {
	public static final String ID = "editServerAction"; //$NON-NLS-1$
	private TreeViewer treeViewer;

	public EditServerAction(TreeViewer treeViewer) {
		super();
		this.treeViewer = treeViewer;
		setId(ID);
		setText("Edit JasperServer Connection");
		setDescription("Edit JasperServer Connection");
		setToolTipText("Edit JasperServer Connection");
		setImageDescriptor(Activator
				.getImageDescriptor("icons/server--pencil.png")); //$NON-NLS-1$
	}

	@Override
	public boolean isEnabled() {
		Object firstElement = ((TreeSelection) treeViewer.getSelection())
				.getFirstElement();
		return firstElement != null && (firstElement instanceof MServerProfile);
	}

	@Override
	public void run() {
		Object obj = ((TreeSelection) treeViewer.getSelection())
				.getFirstElement();
		if (obj instanceof MServerProfile) {
			final MServerProfile mspold = (MServerProfile) obj;
			ServerProfile sp = mspold.getValue();
			try {
				ServerProfileWizard wizard = new ServerProfileWizard(
						new MServerProfile(null, (ServerProfile) sp.clone()));
				ServerProfileWizardDialog dialog = new ServerProfileWizardDialog(
						Display.getDefault().getActiveShell(), wizard);
				wizard.bindTestButton(dialog);
				dialog.create();
				if (dialog.open() == Dialog.OK) {
					MServerProfile msprof = wizard.getServerProfile();
					mspold.setValue(msprof.getValue());
					mspold.setWsClient(msprof.getWsClient());
					mspold.removeChildren();
					for (INode cn : msprof.getChildren())
						mspold.addChild((ANode) cn);
					ServerManager.saveServerProfile(mspold);
					fillServerProfile(mspold, treeViewer);

				}
			} catch (CloneNotSupportedException e) {
				UIUtils.showError(e);
			}
		}
	}

	public static void fillServerProfile(final MServerProfile mspold,
			final TreeViewer treeViewer) {
		Job job = new Job("Connect To JasperReports Server") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					monitor.beginTask("Connecting to the server",
							IProgressMonitor.UNKNOWN);
					mspold.removeChildren();
					new MDummy(mspold);
					showSelection();
					WSClientHelper.connectGetData(mspold, monitor);
					showSelection();
				} catch (InvocationTargetException e) {
					UIUtils.showError(e);
					return Status.CANCEL_STATUS;
				} catch (Exception e) {
					UIUtils.showError(e);
					return Status.CANCEL_STATUS;
				}
				return Status.OK_STATUS;
			}

			private void showSelection() {
				Display.getDefault().syncExec(new Runnable() {

					@Override
					public void run() {
						treeViewer.refresh(true);
						TreeSelection s = (TreeSelection) treeViewer
								.getSelection();
						TreePath[] p = s.getPaths();
						treeViewer.expandToLevel(p[0], 1);
					}
				});
			}
		};
		job.setSystem(false);
		job.schedule();
	}
}
