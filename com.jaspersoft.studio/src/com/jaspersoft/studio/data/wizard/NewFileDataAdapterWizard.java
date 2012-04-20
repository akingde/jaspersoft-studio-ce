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
package com.jaspersoft.studio.data.wizard;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.ide.IDE;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditorPart;
import com.jaspersoft.studio.data.DataAdapterFactory;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.wizard.pages.DataAdapterEditorPage;
import com.jaspersoft.studio.data.wizard.pages.DataAdaptersListPage;
import com.jaspersoft.studio.messages.Messages;

public class NewFileDataAdapterWizard extends Wizard implements INewWizard {
	private ISelection selection;

	private DataAdapterFactory selectedFactory = null;

	private WizardNewFileCreationPage step1;

	private DataAdapterDescriptor dataAdapter = null;
	private DataAdaptersListPage dataAdapterListPage = null;
	private DataAdapterEditorPage dataAdapterEditorPage = null;

	/**
	 * This constructor will set the data adapter wizard. The data adapters list is displayed as first page, then the edit
	 * page is shown.
	 */
	public NewFileDataAdapterWizard() {
		setWindowTitle(Messages.DataAdapterWizard_windowtitle);
	}

	/**
	 * Pass to this constructor a dataAdapter to be edited. This will set the wizard directly to edit page.
	 * 
	 * @param dataAdapter
	 */
	public NewFileDataAdapterWizard(DataAdapterDescriptor dataAdapter) {
		this();
		this.dataAdapter = dataAdapter;
	}

	@Override
	public void addPages() {
		step1 = new WizardNewFileCreationPage("newFilePage1", (IStructuredSelection) selection);//$NON-NLS-1$
		step1.setTitle("DataAdapter File");
		step1.setDescription("Create a DataAdapter in a file");
		step1.setFileExtension("xml");//$NON-NLS-1$
		step1.setFileName("NEW_DATAADAPTER.xml");//$NON-NLS-1$
		addPage(step1);

		if (dataAdapter == null) {
			dataAdapterListPage = new DataAdaptersListPage();
			addPage(dataAdapterListPage);
		}

		dataAdapterEditorPage = new DataAdapterEditorPage();
		if (dataAdapter != null) {
			dataAdapterEditorPage.setEditMode(true);
		}
		addPage(dataAdapterEditorPage);
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page == dataAdapterListPage) {// && event.getTargetPage() == dataAdapterEditorPage) {
			// Update the layout of the editor page with the proper data adapter editor
			// provided by the new data adapter
			DataAdapterFactory factory = dataAdapterListPage.getSelectedFactory();

			java.text.MessageFormat fm = new java.text.MessageFormat(Messages.DataAdapterWizard_newdataadaptername);
			// 1. instance a new dataAdapter using the factory
			DataAdapterDescriptor newDataAdapter = factory.createDataAdapter();
			newDataAdapter.getDataAdapter().setName(fm.format(new Object[] { 1 }));

			// 2. set in the wizard page the data adapter to edit
			if (selectedFactory != factory) {
				dataAdapterEditorPage.setDataAdapter(newDataAdapter);
				selectedFactory = factory;
			}
		}
		return super.getNextPage(page);
	}

	@Override
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);

		if (this.dataAdapter != null) {
			DataAdapterDescriptor editedDataAdapter = DataAdapterManager.cloneDataAdapter(this.dataAdapter);
			dataAdapterEditorPage.setDataAdapter(editedDataAdapter);
		}
	}

	// Save the new adapter using the manager
	@Override
	public boolean performFinish() {
		DataAdapterDescriptor editedDataAdapter = dataAdapterEditorPage.getDataAdapter();

		if (this.dataAdapter == null) {
			this.dataAdapter = editedDataAdapter;
		} else // We are modifying an existing adapter....
		{
			// ... let's update with the adapter just modified ...
			String oldName = this.dataAdapter.getName();
			dataAdapter.setDataAdapter(editedDataAdapter.getDataAdapter());
			dataAdapter.getDataAdapter().setName(oldName);
		}
		final String containerName = step1.getContainerFullPath().toPortableString();
		final String fileName = step1.getFileName();
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					doFinish(containerName, fileName, monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage()); //$NON-NLS-1$
			return false;
		}
		return true;
	}

	private void doFinish(String containerName, String fileName, IProgressMonitor monitor) throws CoreException {
		// create a sample file
		monitor.beginTask("Creating " + fileName, 2); //$NON-NLS-1$
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		if (!resource.exists() || !(resource instanceof IContainer)) {
			String message = "Container \"" + containerName + "\" does not exist."; //$NON-NLS-1$ //$NON-NLS-2$
			IStatus status = new Status(IStatus.ERROR, "com.jaspersoft.studio", IStatus.OK, message, null); //$NON-NLS-1$
			throw new CoreException(status);
		}
		IContainer container = (IContainer) resource;
		final IFile file = container.getFile(new Path(fileName));
		InputStream in = null;
		try {
			String xml = DataAdapterManager.toDataAdapterFile(dataAdapter);
			in = new ByteArrayInputStream(xml.getBytes());
			if (file.exists())
				file.setContents(in, true, true, monitor);
			else
				file.create(in, true, monitor);
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		monitor.worked(1);
		monitor.setTaskName("Opening file for editing..."); //$NON-NLS-1$
		getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				try {
					IDE.openEditor(page, file, DataAdapterEditorPart.ID);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
		});
		monitor.worked(1);
	}

	/**
	 * Returns the new data adapter (or the modified data adapter in case the wizard is used to edit an existing data
	 * adapter). It returns null (or the original data adapter) if the wizard has not been completed. The returned object
	 * is the same used in the constructor in case of editing.
	 * 
	 * @return
	 */
	public DataAdapterDescriptor getDataAdapter() {
		return this.dataAdapter;
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
}
