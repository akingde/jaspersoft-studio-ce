/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.exp;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.jasperserver.jaxrs.client.dto.importexport.StateDto;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.protocol.IConnection;
import com.jaspersoft.studio.server.protocol.restv2.ARestV2Connection;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

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
					monitor.beginTask(Messages.ExportMetadataWizard_0, IProgressMonitor.UNKNOWN);
					try {
						IConnection conn = null;
						Object firstElement = selection.getFirstElement();
						if (firstElement != null) {
							if (firstElement instanceof MServerProfile)
								conn = ((MServerProfile) firstElement).getWsClient();
							else if (firstElement instanceof AMResource)
								conn = ((AMResource) firstElement).getWsClient();
							if (conn != null) {
								ExportOptions opt = page0.getValue();
								if (new File(opt.getFile()).exists() && !UIUtils.showConfirmation("Confirmation",
										String.format(
												"File %s already exists.\nPlease confirm you want we overwrite it.",
												opt.getFile()))) {
									return;
								}
								for (Object obj : selection.toList()) {
									if (obj instanceof MServerProfile)
										opt.getPaths().add("/"); //$NON-NLS-1$
									else if (obj instanceof AMResource) {
										String uri = ((AMResource) obj).getValue().getUriString();
										opt.getPaths().add(uri);
									}
								}

								while (opt.getState() == null || opt.getState().getPhase().equals("inprogress")) { //$NON-NLS-1$
									StateDto state = conn.exportMetaData(opt, monitor);

									monitor.setTaskName(state.getMessage());
									if (monitor.isCanceled())
										break;
								}
								if (opt.getState() != null)
									if (opt.getState().getErrorDescriptor() != null)
										UIUtils.showInformation(((ARestV2Connection) conn).getEh().buildMessage(monitor,
												"", opt.getState().getErrorDescriptor())); //$NON-NLS-1$
									else
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
			return false;
		} catch (InterruptedException e) {
			UIUtils.showError(e);
			return false;
		}
		return true;
	}

}
