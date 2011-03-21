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
package com.jaspersoft.studio.editor.style.wizard;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRSimpleTemplate;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.xml.JRXmlTemplateWriter;

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
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.ide.IDE;

import com.jaspersoft.studio.messages.Messages;

/**
 * This is a sample new wizard. Its role is to create a new file resource in the provided container. If the container
 * resource (a folder or a project) is selected in the workspace when the wizard is opened, it will accept it as the
 * target container. The wizard creates one file with the extension "jrxml". If a sample multi-page editor (also
 * available as a template) is registered for the same extension, it will be able to open it.
 */

public class StyleTemplateNewWizard extends Wizard implements INewWizard {
	private WizardNewFileCreationPage step1;
	private ISelection selection;

	/**
	 * Constructor for ReportNewWizard.
	 */
	public StyleTemplateNewWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	/**
	 * Adding the page to the wizard.
	 */

	public void addPages() {
		step1 = new WizardNewFileCreationPage("newFilePage1", (IStructuredSelection) selection);//$NON-NLS-1$
		step1.setTitle(Messages.StyleTemplateNewWizard_title);
		step1.setDescription(Messages.StyleTemplateNewWizard_description);
		step1.setFileExtension("jrtx");//$NON-NLS-1$
		step1.setFileName("NEW_STYLE.jrtx");//$NON-NLS-1$
		addPage(step1);

	}

	/**
	 * This method is called when 'Finish' button is pressed in the wizard. We will create an operation and run it using
	 * wizard as execution context.
	 */
	public boolean performFinish() {
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

	/**
	 * The worker method. It will find the container, create the file if missing or just replace its contents, and open
	 * the editor on the newly created file.
	 */

	private void doFinish(String containerName, String fileName, IProgressMonitor monitor) throws CoreException {
		// create a sample file
		monitor.beginTask("Creating " + fileName, 2); //$NON-NLS-1$
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		if (!resource.exists() || !(resource instanceof IContainer)) {
			throwCoreException("Container \"" + containerName + "\" does not exist."); //$NON-NLS-1$ //$NON-NLS-2$
		}
		IContainer container = (IContainer) resource;
		final IFile file = container.getFile(new Path(fileName));
		try {
			InputStream stream = openContentStream();

			if (file.exists()) {
				file.setContents(stream, true, true, monitor);
			} else {
				file.create(stream, true, monitor);
			}
			stream.close();
			reportFile = file;
		} catch (IOException e) {
			e.printStackTrace();
		}
		monitor.worked(1);
		monitor.setTaskName("Opening file for editing..."); //$NON-NLS-1$
		getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				try {
					IDE.openEditor(page, file, true);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
		});
		monitor.worked(1);
	}

	private IFile reportFile;

	public IFile getReportFile() {
		return reportFile;
	}

	/**
	 * We will initialize file contents with a sample text.
	 */

	private InputStream openContentStream() {
		try {
			JRSimpleTemplate tmp = new JRSimpleTemplate();

			JRDesignStyle jrDesignStyle = new JRDesignStyle();
			jrDesignStyle.setName("SimpleStyle"); //$NON-NLS-1$
			tmp.addStyle(jrDesignStyle);
			String contents = JRXmlTemplateWriter.writeTemplate(tmp);
			return new ByteArrayInputStream(contents.getBytes());
		} catch (JRException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void throwCoreException(String message) throws CoreException {
		IStatus status = new Status(IStatus.ERROR, "com.jaspersoft.studio", IStatus.OK, message, null); //$NON-NLS-1$
		throw new CoreException(status);
	}

	/**
	 * We will accept the selection in the workbench to see if we can initialize from it.
	 * 
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
}