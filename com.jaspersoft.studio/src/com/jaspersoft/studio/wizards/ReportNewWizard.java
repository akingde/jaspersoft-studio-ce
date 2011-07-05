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
package com.jaspersoft.studio.wizards;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRImage;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

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
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.dataset.wizard.DatasetWizard;
import com.jaspersoft.studio.property.dataset.wizard.WizardDataSourcePage;
import com.jaspersoft.studio.property.dataset.wizard.WizardFieldsGroupByPage;
import com.jaspersoft.studio.property.dataset.wizard.WizardFieldsPage;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.UIUtils;
import com.jaspersoft.studio.wizards.report.ReportGenerator;

public class ReportNewWizard extends Wizard {
	private ReportTemplatesWizardPage step0;
	private NewFileCreationWizard step1;
	private WizardDataSourcePage step2;
	private WizardFieldsPage step3;
	private WizardFieldsGroupByPage step4;
	private ISelection selection;

	/**
	 * Constructor for ReportNewWizard.
	 */
	public ReportNewWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	/**
	 * Adding the page to the wizard.
	 */

	@Override
	public void addPages() {
		step0 = new ReportTemplatesWizardPage();
		addPage(step0);

		step1 = new NewFileCreationWizard("newFilePage1", (IStructuredSelection) selection);//$NON-NLS-1$
		step1.setTitle("Report file");
		step1.setDescription(Messages.ReportNewWizardPage_description);
		step1.setFileExtension("jrxml");//$NON-NLS-1$
		step1.setFileName("NEW_REPORT.jrxml");//$NON-NLS-1$
		addPage(step1);

		step2 = new WizardDataSourcePage(null);
		addPage(step2);

		step3 = new WizardFieldsPage();
		addPage(step3);

		step4 = new WizardFieldsGroupByPage();
		addPage(step4);
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page == step0)
			step1.validatePage();
		if (page == step2) {
			IResource r = ResourcesPlugin.getWorkspace().getRoot().findMember(step1.getContainerFullPath());
			step2.setFile(r.getProject().getFile(step1.getContainerFullPath() + "/" + step1.getFileName()));
		}
		if (page == step3) {
			try {
				JRDesignDataset dataset = step2.getDataset();
				if (dataset != null && dataset.getFieldsList() != null) {
					step3.setFields(new ArrayList<Object>(dataset.getFieldsList()));
				}
			} catch (UnsupportedOperationException e) {
				e.printStackTrace();
			}
		}
		if (page == step4 && step3.getFields() != null)
			step4.setFields(new ArrayList<Object>(step3.getFields()));
		return super.getNextPage(page);
	}

	/**
	 * This method is called when 'Finish' button is pressed in the wizard. We will create an operation and run it using
	 * wizard as execution context.
	 */
	@Override
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
		reportFile = container.getFile(new Path(fileName));
		try {
			InputStream stream = openContentStream(monitor);
			if (reportFile.exists()) {
				reportFile.setContents(stream, true, true, monitor);
			} else {
				reportFile.create(stream, true, monitor);
			}
			stream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		monitor.worked(1);
		monitor.setTaskName("Opening file for editing..."); //$NON-NLS-1$
		getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				try {
					IDE.openEditor(page, reportFile, true);
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

	private InputStream openContentStream(IProgressMonitor monitor) {
		JasperDesign jd = null;
		if (step0.getTemplate() != null) {
			URL obj = step0.getTemplate();
			try {
				jd = JRXmlLoader.load(obj.openStream());

				copyTemplateResources(monitor, jd, reportFile);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JRException e) {
				e.printStackTrace();
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		if (jd == null) {
			jd = new JasperDesign();
			jd.setPageWidth(800);
			jd.setPageHeight(1200);
			jd.setTopMargin(30);
			jd.setBottomMargin(30);
			jd.setLeftMargin(25);
			jd.setRightMargin(25);
			jd.setName(Messages.ReportNewWizard_new_report);

			JRDesignQuery jrDesignQuery = new JRDesignQuery();
			jrDesignQuery.setLanguage("sql"); //$NON-NLS-1$
			jrDesignQuery.setText(""); //$NON-NLS-1$
			jd.setQuery(jrDesignQuery);

			JRDesignBand jb = new JRDesignBand();
			jb.setHeight(100);
			jd.setPageHeader(jb);

			jb = new JRDesignBand();
			jb.setHeight(200);
			((JRDesignSection) jd.getDetailSection()).addBand(jb);

			jb = new JRDesignBand();
			jb.setHeight(100);
			jd.setPageFooter(jb);
		}

		DatasetWizard.setUpDataset(jd.getMainDesignDataset(), step2, step3, step4);
		new ReportGenerator().processTemplate(jd, step3.getFields(), step4.getFields());

		String contents;
		try {
			contents = JRXmlWriterHelper.writeReport(jd, reportFile, false);
			return new ByteArrayInputStream(contents.getBytes());
		} catch (Exception e) {
			UIUtils.showError(e);
		}
		// String contents = JasperCompileManager.writeReportToXml(jd);
		return null;
	}

	private void copyTemplateResources(final IProgressMonitor monitor, final JasperDesign jd, final IFile repFile)
			throws CoreException, IOException {
		Job job = new Job("Copy template resources") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				List<JRDesignElement> list = ModelUtils.getAllGElements(jd);
				for (JRDesignElement el : list) {
					if (el instanceof JRImage) {
						JRImage im = (JRImage) el;
						String str = ExpressionUtil.eval(im.getExpression(), jd);
						if (str != null) {// resolv image
							Enumeration<?> en = JaspersoftStudioPlugin.getInstance().getBundle().findEntries("templates", str, true);
							while (en.hasMoreElements()) {
								URL uimage = (URL) en.nextElement();
								IFile f = repFile.getParent().getFile(new Path(str));
								try {
									f.create(uimage.openStream(), true, monitor);
								} catch (CoreException e) {
									e.printStackTrace();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
				return Status.OK_STATUS;
			}
		};
		job.setPriority(Job.SHORT);
		job.schedule();
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
