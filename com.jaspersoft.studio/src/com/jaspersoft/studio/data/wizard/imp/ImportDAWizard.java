/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.data.wizard.imp;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.MDataAdapters;
import com.jaspersoft.studio.data.storage.ADataAdapterStorage;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ImportDAWizard extends Wizard implements IImportWizard {
	private ImportDAPage page0;
	private MDataAdapters das;

	public ImportDAWizard(MDataAdapters das) {
		super();
		setNeedsProgressMonitor(true);
		setHelpAvailable(true);
		setWindowTitle(Messages.ImportDAWizard_0);
		this.das = das;
	}

	@Override
	public void addPages() {
		page0 = new ImportDAPage();
		addPage(page0);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {

	}

	@Override
	public boolean performFinish() {
		final boolean overwrite = page0.isOverwrite();
		final List<DataAdapterDescriptor> adapters = page0.getDataAdapterDescriptors();
		try {
			getContainer().run(false, true, new IRunnableWithProgress() {

				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					monitor.beginTask(Messages.ImportDAWizard_1, adapters.size());
					ADataAdapterStorage storage = das.getValue();
					for (DataAdapterDescriptor da : adapters) {
						monitor.subTask(da.getName());
						if (overwrite)
							storage.removeDataAdapter(da);
						DataAdapterDescriptor cloneDataAdapter = DataAdapterManager.cloneDataAdapter(da,
								JasperReportsConfiguration.getDefaultInstance());
						cloneDataAdapter.setName(null);
						storage.addDataAdapter(cloneDataAdapter);
						monitor.internalWorked(1);
						if (monitor.isCanceled())
							break;
					}
				}
			});
		} catch (InvocationTargetException e) {
			UIUtils.showError(e);
		} catch (InterruptedException e) {
			UIUtils.showError(e);
		}

		return true;
	}

}
