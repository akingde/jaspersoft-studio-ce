/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.server.wizard;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.wizard.pages.ServerProfilePage;
import com.jaspersoft.studio.utils.UIUtils;

public class ServerProfileWizard extends Wizard {
	private ServerProfilePage page0;

	public ServerProfileWizard(MServerProfile sprofile) {
		super();
		setWindowTitle("Server profile wizard");
		this.serverProfile = sprofile;
		setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		page0 = new ServerProfilePage(serverProfile);
		addPage(page0);

	}

	private MServerProfile serverProfile;

	public MServerProfile getServerProfile() {
		return serverProfile;
	}

	@Override
	public boolean performFinish() {
		Job job = new Job("Connect To JasperReports Server") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					return connect(false, monitor);
				} catch (InvocationTargetException e) {
					UIUtils.showError(e);
				}
				return Status.CANCEL_STATUS;
			}
		};
		job.setSystem(true);
		job.schedule();

		return true;
	}

	public void bindTestButton(ServerProfileWizardDialog c) {
		c.addTestListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleConnect(true);
			}
		});

	}

	private void handleConnect(final boolean onlycheck) {
		try {
			getContainer().run(true, true, new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {
					connect(onlycheck, monitor);
				}

			});
		} catch (InvocationTargetException e) {
			UIUtils.showError(e);
		} catch (InterruptedException e) {
			UIUtils.showError(e);
		}
	}

	private void connectionOK() {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				MessageDialog.openInformation(getShell(),
						"Connection to JasperServer", "Successful");
			}
		});
	}

	private IStatus connect(final boolean onlycheck, IProgressMonitor monitor)
			throws InvocationTargetException {
		try {
			monitor.beginTask("Connecting to the server",
					IProgressMonitor.UNKNOWN);
			if (onlycheck) {
				if (WSClientHelper.checkConnection(serverProfile, monitor))
					connectionOK();
			} else
				WSClientHelper.connectGetData(serverProfile, monitor);
		} catch (Throwable e) {
			throw new InvocationTargetException(e);
		} finally {
			monitor.done();
		}
		return Status.OK_STATUS;
	}
}