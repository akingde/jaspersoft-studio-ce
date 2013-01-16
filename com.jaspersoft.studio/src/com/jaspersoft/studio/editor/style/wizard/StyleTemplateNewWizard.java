/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
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
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.gef.EditPart;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.SelectionHelper;
import com.jaspersoft.studio.wizards.ContextData;
import com.jaspersoft.studio.wizards.ContextHelpIDs;

/*
 * This is a sample new wizard. Its role is to create a new file resource in the provided container. If the container
 * resource (a folder or a project) is selected in the workspace when the wizard is opened, it will accept it as the
 * target container. The wizard creates one file with the extension "jrxml". If a sample multi-page editor (also
 * available as a template) is registered for the same extension, it will be able to open it.
 */

public class StyleTemplateNewWizard extends Wizard implements INewWizard {
	public static final String WIZARD_ID = "com.jaspersoft.studio.wizards.StyleTemplateNewWizard";
	private static final String NEW_STYLE_JRTX = Messages.StyleTemplateNewWizard_0;
	private WizardNewFileCreationPage step1;
	private ISelection selection;

	/**
	 * Constructor for ReportNewWizard.
	 */
	public StyleTemplateNewWizard() {
		super();
		setWindowTitle(Messages.StyleTemplateNewWizard_wizardtitle);
		setNeedsProgressMonitor(true);
	}
	
	/**
	 * Extends the original WizardNewFileCreationPage to implements the method to have a contextual help
	 * @author Orlandin Marco
	 *
	 */
	private class WizardHelpNewFileCreationPage extends WizardNewFileCreationPage implements ContextData{
		
		public WizardHelpNewFileCreationPage(String pageName, IStructuredSelection selection) {
			super(pageName, selection);
		}

		/**
		 * Set and show the help data 
		 */
		@Override
		public void performHelp() {
			PlatformUI.getWorkbench().getHelpSystem().displayHelp(ContextHelpIDs.wizardStyleTemplatePath);
		}
		
		/**
		 * Set the help data that should be seen in this step
		 */
		@Override
		public void setHelpData(){
			PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), ContextHelpIDs.wizardStyleTemplatePath);
		}
		
		@Override
		protected void setControl(Control newControl) {
			super.setControl(newControl);
			newControl.addListener(SWT.Help, new Listener() {			
				@Override
				public void handleEvent(Event event) {
					performHelp();	
				}
			});
			setHelpData();
		};
	}

	/**
	 * Adding the page to the wizard.
	 */
	public void addPages() {
		step1 = new WizardHelpNewFileCreationPage("newFilePage1", (IStructuredSelection) selection);//$NON-NLS-1$
		step1.setTitle(Messages.StyleTemplateNewWizard_title);
		step1.setDescription(Messages.StyleTemplateNewWizard_description);
		step1.setFileExtension("jrtx");//$NON-NLS-1$
		setupNewFileName();
		addPage(step1);
	}

	public void setupNewFileName() {
		String filename = NEW_STYLE_JRTX;
		if (selection != null) {
			if (selection instanceof TreeSelection) {
				TreeSelection s = (TreeSelection) selection;
				if (s.getFirstElement() instanceof IFile) {
					IFile file = (IFile) s.getFirstElement();

					String f = file.getProjectRelativePath().removeLastSegments(1).toOSString() + Messages.StyleTemplateNewWizard_2 + filename;

					int i = 1;
					while (file.getProject().getFile(f).exists()) {
						filename = Messages.StyleTemplateNewWizard_3 + i + Messages.StyleTemplateNewWizard_4;
						f = file.getProjectRelativePath().removeLastSegments(1).toOSString() + Messages.StyleTemplateNewWizard_5 + filename;
						i++;
					}
				}
			}
			step1.setFileName(filename);
		}
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
		InputStream in = null;
		try {
			in = openContentStream();
			if (file.exists()) {
				file.setContents(in, true, true, monitor);
			} else {
				file.create(in, true, monitor);
			}
			reportFile = file;
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

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		if (selection instanceof StructuredSelection) {
			for (Object obj : selection.toList()) {
				if (obj instanceof EditPart) {
					IEditorInput ein = SelectionHelper.getActiveJRXMLEditor().getEditorInput();
					if (ein instanceof FileEditorInput) {
						this.selection = new TreeSelection(new TreePath(new Object[] { ((FileEditorInput) ein).getFile() }));
						return;
					}
				}
			}
			IProgressMonitor progressMonitor = new NullProgressMonitor();
			IProject[] prjs = ResourcesPlugin.getWorkspace().getRoot().getProjects();
			for (IProject p : prjs) {
				try {
					if (p.isAccessible() && p.getNature(JavaCore.NATURE_ID) != null) {
						p.open(progressMonitor);
						this.selection = new TreeSelection(new TreePath(new Object[] { p.getFile(NEW_STYLE_JRTX) }));
						return;
					}
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
			for (IProject p : prjs) {
				try {
					if (p.isAccessible()) {
						p.open(progressMonitor);
						this.selection = new TreeSelection(new TreePath(new Object[] { p.getFile(Messages.StyleTemplateNewWizard_6) }));
						return;
					}
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
		}
		this.selection = selection;
	}
}
