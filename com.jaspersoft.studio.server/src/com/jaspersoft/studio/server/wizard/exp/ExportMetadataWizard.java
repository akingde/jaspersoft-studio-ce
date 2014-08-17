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
package com.jaspersoft.studio.server.wizard.exp;

import java.lang.reflect.InvocationTargetException;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.jasperserver.remote.services.async.StateDto;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.protocol.IConnection;
import com.jaspersoft.studio.server.wizard.imp.ImportOptions;

public class ExportMetadataWizard extends Wizard {
	private ExportMetadataPage page0;
	private StructuredSelection selection;

	public ExportMetadataWizard(StructuredSelection selection) {
		super();
		setWindowTitle(Messages.FindResourceWizard_0);
		setNeedsProgressMonitor(true);
		this.selection = selection;
	}

	@Override
	public void addPages() {
		page0 = new ExportMetadataPage();
		addPage(page0);
	}

	@Override
	public boolean performFinish() {
		try {
			getContainer().run(true, true, new IRunnableWithProgress() {

				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					monitor.beginTask("Exporting", IProgressMonitor.UNKNOWN);
					try {
						IConnection conn = null;
						Object firstElement = selection.getFirstElement();
						if (firstElement != null) {
							if (firstElement instanceof MServerProfile)
								conn = ((MServerProfile) firstElement).getWsClient();
							else if (firstElement instanceof MResource)
								conn = ((MResource) firstElement).getWsClient();
							if (conn != null) {
								ExportOptions opt = page0.getValue();
								while (opt.getState() == null || opt.getState().getPhase().equals("inprogress")) {
									StateDto state = conn.exportMetaData(opt, monitor);

									monitor.setTaskName(state.getMessage());
									if (monitor.isCanceled())
										break;
								}
								if (opt.getState() != null)
									UIUtils.showInformation(opt.getState().getMessage());
							}
						}
					} catch (Exception e) {
						UIUtils.showError(e);
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
		return true;
	}

}
