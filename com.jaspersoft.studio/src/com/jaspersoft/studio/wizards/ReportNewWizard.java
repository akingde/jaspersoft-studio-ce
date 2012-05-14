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

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
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
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.property.dataset.wizard.DatasetWizard;
import com.jaspersoft.studio.property.dataset.wizard.WizardDataSourcePage;
import com.jaspersoft.studio.property.dataset.wizard.WizardFieldsGroupByPage;
import com.jaspersoft.studio.property.dataset.wizard.WizardFieldsPage;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.SelectionHelper;
import com.jaspersoft.studio.utils.UIUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.report.ReportGenerator;

public class ReportNewWizard extends JSSWizard implements IWorkbenchWizard, INewWizard {
	private static final String NEW_REPORT_JRXML = "NEW_REPORT.jrxml";
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

	public ReportNewWizard(IWizard parentWizard, IWizardPage fallbackPage) {
		super(parentWizard, fallbackPage);
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
		step1.setTitle(Messages.ReportNewWizard_0);
		step1.setDescription(Messages.ReportNewWizardPage_description);
		step1.setFileExtension("jrxml");//$NON-NLS-1$
		setupNewFileName();
		addPage(step1);

		step2 = new WizardDataSourcePage(null, jConfig);
		addPage(step2);

		step3 = new WizardFieldsPage();
		addPage(step3);

		step4 = new WizardFieldsGroupByPage();
		addPage(step4);
	}

	public void setupNewFileName() {
		String filename = NEW_REPORT_JRXML;
		if (selection != null) {
			if (selection instanceof TreeSelection) {
				TreeSelection s = (TreeSelection) selection;
				if (s.getFirstElement() instanceof IFile) {
					IFile file = (IFile) s.getFirstElement();

					String f = file.getProjectRelativePath().removeLastSegments(1).toOSString() + "/" + filename;

					int i = 1;
					while (file.getProject().getFile(f).exists()) {
						filename = "NEW_REPORT_" + i + ".jrxml";
						f = file.getProjectRelativePath().removeLastSegments(1).toOSString() + "/" + filename;
						i++;
					}
				}
			}
			step1.setFileName(filename);
		}
	}

	private JasperReportsConfiguration jConfig = new JasperReportsConfiguration(
			DefaultJasperReportsContext.getInstance(), null);
	public static final String REPORT_FILE = "REPORTFILEWIZARD";
	public static final String REPORT_DESIGN = "REPORTDESIGNWIZARD";

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page == step0)
			step1.validatePage();
		if (page == step2) {
			IResource r = ResourcesPlugin.getWorkspace().getRoot().findMember(step1.getContainerFullPath());

			IFile file = r.getProject().getFile(
					step1.getContainerFullPath() + Messages.ReportNewWizard_1 + step1.getFileName());
			jConfig.init(file);
			jConfig.setJasperDesign(getJasperDesign());

			if (getConfig() != null) {
				getConfig().put(REPORT_FILE, file);
				getConfig().put(REPORT_DESIGN, getJasperDesign());
			}
			step2.setFile(jConfig);
			run(true, true, new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					monitor.beginTask("Getting fields from datasource", IProgressMonitor.UNKNOWN);
					try {
						// if we don't have fields, call getFields from the QueryDesigner automatically
						if (step3.getFields() == null || step3.getFields().isEmpty())
							step2.getFields(monitor);

						final JRDesignDataset dataset = step2.getDataset();
						if (dataset != null && dataset.getFieldsList() != null) {
							Display.getDefault().syncExec(new Runnable() {

								public void run() {
									step3.setFields(new ArrayList<Object>(dataset.getFieldsList()));
								}
							});

						}
					} catch (Exception e) {
						UIUtils.showError(e);
					} finally {
						monitor.done();
					}
				}
			});
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

		try {
			getContainer().run(true, true, new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					monitor.beginTask(Messages.ReportNewWizard_2, IProgressMonitor.UNKNOWN);
					try {
						doFinish(containerName, fileName, monitor);
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
			UIUtils.showError(e.getCause());
		}
		return super.performFinish();
	}

	/**
	 * The worker method. It will find the container, create the file if missing or just replace its contents, and open
	 * the editor on the newly created file.
	 */

	private void doFinish(String containerName, String fileName, IProgressMonitor monitor) throws CoreException {
		// create a sample file
		monitor.beginTask(Messages.ReportNewWizard_3 + fileName, 2);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		if (!resource.exists() || !(resource instanceof IContainer)) {
			throwCoreException(String.format(Messages.ReportNewWizard_4, containerName));
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
		monitor.setTaskName(Messages.ReportNewWizard_5);
		Display.getDefault().asyncExec(new Runnable() {
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
	private JasperDesign jDesign;

	public IFile getReportFile() {
		return reportFile;
	}

	/**
	 * We will initialize file contents with a sample text.
	 */

	private InputStream openContentStream(IProgressMonitor monitor) {
		jDesign = null;
		if (step0.getTemplate() != null) {
			URL obj = step0.getTemplate();
			try {
				jDesign = JRXmlLoader.load(obj.openStream());

				copyTemplateResources(monitor, jDesign, reportFile);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JRException e) {
				e.printStackTrace();
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		getJasperDesign();
		Display.getDefault().syncExec(new Runnable() {

			public void run() {
				DataAdapterDescriptor dataAdapter = step2.getDataAdapter();
				if (dataAdapter != null)
					jDesign.setProperty(MReport.DEFAULT_DATAADAPTER, dataAdapter.getName());

				DatasetWizard.setUpDataset(jDesign.getMainDesignDataset(), step2, step3, step4);
				new ReportGenerator().processTemplate(jDesign, step3.getFields(), step4.getFields());

			}
		});
		try {
			String contents = JRXmlWriterHelper.writeReport(jConfig, jDesign, reportFile, false);
			return new ByteArrayInputStream(contents.getBytes());
		} catch (Exception e) {
			UIUtils.showError(e);
		}
		return null;
	}

	protected JasperDesign getJasperDesign() {
		if (jDesign == null) {
			jDesign = new JasperDesign();
			jDesign.setPageWidth(800);
			jDesign.setPageHeight(1200);
			jDesign.setTopMargin(30);
			jDesign.setBottomMargin(30);
			jDesign.setLeftMargin(25);
			jDesign.setRightMargin(25);
			jDesign.setName(Messages.ReportNewWizard_new_report);

			JRDesignQuery jrDesignQuery = new JRDesignQuery();
			jrDesignQuery.setLanguage("sql"); //$NON-NLS-1$
			jrDesignQuery.setText(""); //$NON-NLS-1$
			jDesign.setQuery(jrDesignQuery);

			JRDesignBand jb = new JRDesignBand();
			jb.setHeight(100);
			jDesign.setPageHeader(jb);

			jb = new JRDesignBand();
			jb.setHeight(200);
			((JRDesignSection) jDesign.getDetailSection()).addBand(jb);

			jb = new JRDesignBand();
			jb.setHeight(100);
			jDesign.setPageFooter(jb);
		}
		return jDesign;
	}

	private void copyTemplateResources(final IProgressMonitor monitor, final JasperDesign jd, final IFile repFile)
			throws CoreException, IOException {
		monitor.subTask(Messages.ReportNewWizard_6);
		try {
			List<JRDesignElement> list = ModelUtils.getAllGElements(jd);
			for (JRDesignElement el : list) {
				if (el instanceof JRImage) {
					JRImage im = (JRImage) el;
					String str = ExpressionUtil.eval(im.getExpression(), jConfig);
					if (str != null) {// resolv image
						Enumeration<?> en = JaspersoftStudioPlugin.getInstance().getBundle()
								.findEntries(Messages.ReportNewWizard_7, str, true);
						while (en.hasMoreElements()) {
							URL uimage = (URL) en.nextElement();
							IFile f = repFile.getParent().getFile(new Path(str));
							try {
								if (!f.exists())
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
		} catch (Exception e) {
			UIUtils.showError(e);
		} finally {
			monitor.done();
		}
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
						this.selection = new TreeSelection(new TreePath(new Object[] { p.getFile(NEW_REPORT_JRXML) }));
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
						this.selection = new TreeSelection(new TreePath(new Object[] { p.getFile("file") }));
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
