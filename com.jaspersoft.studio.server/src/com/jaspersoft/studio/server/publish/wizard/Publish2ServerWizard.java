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
package com.jaspersoft.studio.server.publish.wizard;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileExtension;
import net.sf.jasperreports.eclipse.wizard.project.ProjectUtil;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlDigesterFactory;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gef.EditPart;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.part.FileEditorInput;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MDummy;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.publish.FindResources;
import com.jaspersoft.studio.server.publish.action.JrxmlPublishAction;
import com.jaspersoft.studio.utils.SelectionHelper;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.ReportNewWizard;

public class Publish2ServerWizard extends Wizard implements IExportWizard {
	private JasperDesign jDesign;
	private FileSelectionPage page_1;
	private RUnitLocationPage page0;
	private ResourcesPage page1;
	private DatasourceSelectionPage page2;
	private int page;
	private ANode n;
	private JasperReportsConfiguration jrConfig;
	private boolean doFinish = true;

	public Publish2ServerWizard() {
		super();
		setWindowTitle(Messages.Publish2ServerWizard_Title);
		setNeedsProgressMonitor(true);
	}

	public Publish2ServerWizard(ANode n, JasperDesign jDesign, JasperReportsConfiguration jrConfig, int page) {
		this();
		this.jDesign = jDesign;
		this.page = page;
		this.n = n;
		this.jrConfig = jrConfig;
		doFinish = false;
	}

	private void init() {
		if (selection instanceof IStructuredSelection) {
			Object obj = ((IStructuredSelection) selection).getFirstElement();
			if (obj instanceof IFile) {
				IFile file = (IFile) obj;
				jrConfig = new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), file);
				if (file.getFileExtension().equalsIgnoreCase(FileExtension.JRXML) || file.getFileExtension().equalsIgnoreCase(FileExtension.JASPER))
					initJDesign(file);
			}
		}
	}

	private void initJDesign(IFile file) {
		try {
			if (file != null && file.exists()) {
				jrConfig = new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), file);
				jDesign = new JRXmlLoader(JRXmlDigesterFactory.createDigester()).loadXML(file.getContents());
				jrConfig.setJasperDesign(jDesign);
				createRoot();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createRoot() {
		n = new MRoot(null, jDesign);
		ANode sp = null;
		List<ServerProfile> servers = ServerManager.getServerList();
		for (ServerProfile s : servers) {
			sp = new MServerProfile(n, s);
			new MDummy(sp);
		}
	}

	@Override
	public void addPages() {
		if (selection != null)
			init();
		if (jDesign == null) {
			page_1 = new FileSelectionPage(jrConfig);
			addPage(page_1);
		}
		if (n == null)
			createRoot();

		page0 = new RUnitLocationPage(jDesign, n);
		addPage(page0);

		page1 = new ResourcesPage(jrConfig);
		addPage(page1);

		page2 = new DatasourceSelectionPage(jrConfig);
		addPage(page2);
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page_1 != null && jDesign == null && page == page_1) {
			initJDesign(page_1.getFile());
			page0.setValue(jDesign, n);
		}
		final MReportUnit rpunit = getReportUnit();
		if (page == page1) {
			try {
				getContainer().run(false, true, new IRunnableWithProgress() {
					public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
						monitor.beginTask(Messages.Publish2ServerWizard_MonitorName, IProgressMonitor.UNKNOWN);
						try {
							MReportUnit mrunit = getReportUnit();
							n = mrunit;

							boolean find = new FindResources().find(monitor, mrunit, jrConfig, jDesign);
							if (find) {
								Display.getDefault().syncExec(new Runnable() {
									public void run() {
										page1.fillData();
										page2.setPreviousPage(page1);
									}
								});
							} else {
								if (rpunit.getValue().getIsNew())
									Display.getDefault().asyncExec(new Runnable() {
										@Override
										public void run() {
											page2.configurePage(rpunit.getParent(), rpunit);
											page2.setPreviousPage(page0);
											page2.setPageComplete(true);
											getContainer().showPage(page2);
											page2.setPreviousPage(page0);
											page2.setPageComplete(true);
										}
									});
								else if (getContainer() instanceof WizardDialog)
									throw new InterruptedException("finish");
							}
						} catch (Exception e) {
							if (e instanceof InterruptedException)
								throw (InterruptedException) e;
							else
								UIUtils.showError(e);
						} finally {
							monitor.done();
						}
					}
				});
			} catch (InvocationTargetException e) {
				UIUtils.showError(e.getCause());
			} catch (InterruptedException e) {
				if (e.getMessage().equals("finish"))
					closeWizard();
				else
					UIUtils.showError(e.getCause());
			}
			page2.configurePage(rpunit.getParent(), rpunit);
			ResourceDescriptor rdunit = rpunit.getValue();
			if (!rdunit.getIsNew())
				return super.getNextPage(page2);
		}
		return super.getNextPage(page);
	}

	@Override
	public IWizardPage getStartingPage() {
		switch (page) {
		case 1:
			return page0;
		case 2:
			return page1;
		}
		return super.getStartingPage();
	}

	public MReportUnit getReportUnit() {
		return page0.getReportUnit();
	}

	@Override
	public boolean performFinish() {
		if (!doFinish)
			return true;
		try {
			getContainer().run(false, true, new IRunnableWithProgress() {

				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					MReportUnit mru = page0.getReportUnit();
					JrxmlPublishAction action = new JrxmlPublishAction();
					action.setJrConfig(jrConfig);
					action.setMrunit(mru);
					action.publish(mru, jDesign, monitor);
				}
			});
		} catch (InvocationTargetException e) {
			UIUtils.showError(e.getCause());
		} catch (InterruptedException e) {
			UIUtils.showError(e.getCause());
		}

		return true;
	}

	private void closeWizard() {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				((WizardDialog) getContainer()).close();
			}
		});
	}

	private ISelection selection;

	/**
	 * We will accept the selection in the workbench to see if we can initialize
	 * from it.
	 * 
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		if (selection instanceof StructuredSelection) {
			if (selection.getFirstElement() instanceof IProject || selection.getFirstElement() instanceof IFile || selection.getFirstElement() instanceof IFolder) {
				this.selection = selection;
				return;
			}
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
					if (ProjectUtil.isOpen(p) && p.getNature(JavaCore.NATURE_ID) != null) {
						p.open(progressMonitor);
						this.selection = new TreeSelection(new TreePath(new Object[] { p.getFile(ReportNewWizard.NEW_REPORT_JRXML) }));
						return;
					}
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
			for (IProject p : prjs) {
				try {
					if (ProjectUtil.isOpen(p)) {
						p.open(progressMonitor);
						this.selection = new TreeSelection(new TreePath(new Object[] { p.getFile(ReportNewWizard.NEW_REPORT_JRXML) }));
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
