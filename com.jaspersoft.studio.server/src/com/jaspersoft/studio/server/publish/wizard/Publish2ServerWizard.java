/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.publish.wizard;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.part.FileEditorInput;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.editor.JRSEditorContributor;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMJrxmlContainer;
import com.jaspersoft.studio.server.model.MJrxml;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.publish.FindResources;
import com.jaspersoft.studio.server.publish.Publish;
import com.jaspersoft.studio.server.publish.wizard.page.DatasourceSelectionPage;
import com.jaspersoft.studio.server.publish.wizard.page.FileSelectionPage;
import com.jaspersoft.studio.server.publish.wizard.page.RUnitLocationPage;
import com.jaspersoft.studio.server.publish.wizard.page.ResourcesPage;
import com.jaspersoft.studio.utils.SelectionHelper;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.builder.jdt.JDTUtils;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileExtension;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlDigesterFactory;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class Publish2ServerWizard extends Wizard implements IExportWizard {
	private JasperDesign jDesign;
	private FileSelectionPage page_1;
	private RUnitLocationPage page0;
	private ResourcesPage page1;
	private DatasourceSelectionPage page2;

	private int startPage;
	private AMJrxmlContainer node;
	private JasperReportsConfiguration jrConfig;

	/**
	 * Flag to keep track if the context was created internally to this wizard
	 * or passed from outside. If it was created internally then it is disposed
	 * at the end, otherwise not.
	 */
	private boolean disposeContext = true;

	public Publish2ServerWizard() {
		super();
		setWindowTitle(Messages.Publish2ServerWizard_Title);
		setNeedsProgressMonitor(true);
		JDTUtils.activateLinkedResourcesSupport();
	}

	/**
	 * Create the wizard
	 * 
	 * @param jDesign
	 * @param jrConfig
	 *            a JasperReportsConfiguration, when passed in this way this
	 *            jrConfig is not disposed at the end of the wizard
	 * @param page
	 */
	public Publish2ServerWizard(JasperDesign jDesign, JasperReportsConfiguration jrConfig, int page) {
		this();
		this.jDesign = jDesign;
		this.startPage = page;
		this.jrConfig = jrConfig;
		disposeContext = false;
	}

	private void init() {
		if (selection instanceof IStructuredSelection) {
			Object obj = ((IStructuredSelection) selection).getFirstElement();
			if (obj instanceof IFile) {
				IFile file = (IFile) obj;
				jrConfig = JasperReportsConfiguration.getDefaultJRConfig(file);
				disposeContext = true;
				initJDesign(file);
			}
		}
		if (jrConfig == null) {
			jrConfig = JasperReportsConfiguration.getDefaultJRConfig();
			disposeContext = true;
		}
	}

	@Override
	public void dispose() {
		if (disposeContext) {
			jrConfig.dispose();
		}
		super.dispose();
	}

	private void initJDesign(IFile file) {
		try {
			if (file != null && file.exists()) {
				if (jrConfig == null)
					jrConfig = JasperReportsConfiguration.getDefaultJRConfig(file);
				else
					jrConfig.init(file);
				String fext = file.getFileExtension();
				if (jDesign == null && fext.equalsIgnoreCase(FileExtension.JRXML)
						|| fext.equalsIgnoreCase(FileExtension.JASPER)) {
					jDesign = new JRXmlLoader(jrConfig, JRXmlDigesterFactory.createDigester(jrConfig))
							.loadXML(file.getContents());
					jrConfig.setJasperDesign(jDesign);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ANode getNode() {
		return getNode(new NullProgressMonitor());
	}

	public ANode getNode(IProgressMonitor monitor) {
		if (node == null) {
			ANode mserv = ServerManager.getServerProfile(jDesign, jrConfig, monitor);
			if (mserv == null)
				mserv = new MRoot(null, jDesign);
			mserv.setJasperConfiguration(jrConfig);
			return mserv;
		}
		return node;
	}

	@Override
	public void addPages() {
		if (selection != null)
			init();
		if (jDesign == null) {
			page_1 = new FileSelectionPage(jrConfig);
			addPage(page_1);
		}

		page0 = new RUnitLocationPage(jrConfig, jDesign, getNode());
		addPage(page0);

		page1 = new ResourcesPage(jrConfig);
		addPage(page1);

		page2 = new DatasourceSelectionPage(jrConfig);
		addPage(page2);

		addPageChangeListener();
	}

	private IPageChangedListener pageChangeListener;

	protected void addPageChangeListener() {
		if (pageChangeListener != null)
			return;
		IWizardContainer c = getContainer();
		if (c instanceof WizardDialog) {
			pageChangeListener = new IPageChangedListener() {

				@Override
				public void pageChanged(PageChangedEvent event) {
					if (event.getSelectedPage() == page1) {
						UIUtils.getDisplay().asyncExec(new Runnable() {

							@Override
							public void run() {
								AMJrxmlContainer snode = page0.getSelectedNode();
								page1.setParentResource(snode);
								if (snode == null) {
									page0.setValue(jDesign, getNode());
									snode = page0.getSelectedNode();
								}
								if (snode instanceof MJrxml){
									canFinish = true;
									getContainer().updateButtons();
								}
								if (node != snode) {
									node = snode;
									doFindDependentResources();
								}
							}
						});
					} else if (event.getSelectedPage() == page2 && getStartingPage() == page1) {
						if (jrConfig.get(JRSEditorContributor.KEY_PUBLISH2JSS, false))
							doFinish();
					}
				}
			};
			((WizardDialog) c).addPageChangedListener(pageChangeListener);
		}
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		addPageChangeListener();
		if (page_1 != null && jDesign == null && page == page_1) {
			initJDesign(page_1.getFile());
			page0.setValue(jDesign, getNode());
		}
		if (page == page1) {
			if (!page0.isPageComplete()) {
				page0.setValue(jDesign, getNode());
				return page0;
			}
			if (!(page0.getSelectedNode() instanceof MReportUnit)) {
				canFinish = true;
				return null;
			}
		}
		if (page == page2) {
			if (node instanceof MJrxml) {
				if (node.getParent() instanceof MReportUnit && node.getValue().isMainReport()) {
					page2.configurePage(node.getParent().getParent(), (MReportUnit) node.getParent());
					return super.getNextPage(page);
				}
				return null;
			}
			page2.configurePage(node.getParent(), node);
		}
		return super.getNextPage(page);
	}

	private boolean hasDepResources = false;
	private boolean canFinish = false;

	public void doFindDependentResources() {
		canFinish = true;
		try {
			getContainer().run(false, true, new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					monitor.beginTask(Messages.Publish2ServerWizard_MonitorName, IProgressMonitor.UNKNOWN);
					try {
						hasDepResources = FindResources.find(monitor, node, jDesign);
						UIUtils.getDisplay().asyncExec(new Runnable() {
							public void run() {
								if (hasDepResources)
									page1.fillData(node.getValue().getIsNew());
								else {
									if (node instanceof MReportUnit) {
										IWizardContainer container = getContainer();
										container.showPage(getNextPage(page1));
										container.updateButtons();
									} else if (node instanceof MJrxml) {
										doFinish();
									}
								}
							}
						});
					} catch (Exception e) {
						if (!(e instanceof InterruptedException))
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
	}

	@Override
	public boolean canFinish() {
		return canFinish && super.canFinish();
	}

	@Override
	public boolean performFinish() {
		JDTUtils.restoreLinkedResourcesSupport();
		try {
			getContainer().run(true, true, new IRunnableWithProgress() {

				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					monitor.beginTask(Messages.Publish2ServerWizard_0, IProgressMonitor.UNKNOWN);
					try {
						ANode node = getNode(monitor);
						if (node instanceof AMJrxmlContainer)
							new Publish(jrConfig).publish((AMJrxmlContainer) node, jDesign, monitor);
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

	@Override
	public boolean performCancel() {
		JDTUtils.restoreLinkedResourcesSupport();
		return super.performCancel();
	}

	@Override
	public IWizardPage getStartingPage() {
		switch (startPage) {
		case 1:
			return page0;
		case 2:
			return page1;
		}
		return super.getStartingPage();
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
			if (selection.getFirstElement() instanceof IProject || selection.getFirstElement() instanceof IFile
					|| selection.getFirstElement() instanceof IFolder) {
				this.selection = selection;
				return;
			}
			for (Object obj : selection.toList()) {
				if (obj instanceof EditPart) {
					IEditorInput ein = SelectionHelper.getActiveJRXMLEditor().getEditorInput();
					if (ein instanceof FileEditorInput) {
						this.selection = new TreeSelection(
								new TreePath(new Object[] { ((FileEditorInput) ein).getFile() }));
						return;
					}
				}
			}
		}
		this.selection = selection;
	}

	private void doFinish() {
		try {
			Method m = getContainer().getClass().getDeclaredMethod("finishPressed", null); //$NON-NLS-1$
			if (m != null) {
				m.setAccessible(true);
				m.invoke(getContainer());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
