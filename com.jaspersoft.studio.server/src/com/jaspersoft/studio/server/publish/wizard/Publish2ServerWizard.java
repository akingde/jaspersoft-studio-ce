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
import java.lang.reflect.Method;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileExtension;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlDigesterFactory;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
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
import org.eclipse.swt.widgets.Display;
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
import com.jaspersoft.studio.server.publish.FindResources;
import com.jaspersoft.studio.utils.SelectionHelper;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class Publish2ServerWizard extends Wizard implements IExportWizard {
	private JasperDesign jDesign;
	private FileSelectionPage page_1;
	private RUnitLocationPage page0;
	private ResourcesPage page1;
	private DatasourceSelectionPage page2;

	private int startPage;
	private AMJrxmlContainer node;
	private JasperReportsConfiguration jrConfig;

	public Publish2ServerWizard() {
		super();
		setWindowTitle(Messages.Publish2ServerWizard_Title);
		setNeedsProgressMonitor(true);
	}

	public Publish2ServerWizard(JasperDesign jDesign, JasperReportsConfiguration jrConfig, int page) {
		this();
		this.jDesign = jDesign;
		this.startPage = page;
		this.jrConfig = jrConfig;
	}

	private void init() {
		if (selection instanceof IStructuredSelection) {
			Object obj = ((IStructuredSelection) selection).getFirstElement();
			if (obj instanceof IFile) {
				IFile file = (IFile) obj;
				jrConfig = new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), file);
				initJDesign(file);
			}
		}
	}

	private void initJDesign(IFile file) {
		try {
			if (file != null && file.exists()) {
				if (jrConfig == null)
					jrConfig = new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), file);
				else
					jrConfig.init(file);
				String fext = file.getFileExtension();
				if (jDesign == null && fext.equalsIgnoreCase(FileExtension.JRXML) || fext.equalsIgnoreCase(FileExtension.JASPER)) {
					jDesign = new JRXmlLoader(JRXmlDigesterFactory.createDigester()).loadXML(file.getContents());
					jrConfig.setJasperDesign(jDesign);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ANode getNode() {
		if (node == null) {
			ANode mserv = ServerManager.getServerProfile(jDesign, jrConfig);
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

		IWizardContainer c = getContainer();
		if (c instanceof WizardDialog) {
			((WizardDialog) c).addPageChangedListener(new IPageChangedListener() {

				@Override
				public void pageChanged(PageChangedEvent event) {
					if (event.getSelectedPage() == page1) {
						Display.getDefault().asyncExec(new Runnable() {

							@Override
							public void run() {
								AMJrxmlContainer snode = page0.getSelectedNode();
								if (snode == null) {
									page0.setValue(jDesign, getNode());
									snode = page0.getSelectedNode();
								}
								if (node != snode) {
									node = snode;
									doFindDependentResources();
								}
							}
						});
					} else if (event.getSelectedPage() == page2) {
						if (jrConfig.get(JRSEditorContributor.KEY_PUBLISH2JSS, false))
							doFinish();
					}
				}
			});
		}
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page_1 != null && jDesign == null && page == page_1) {
			initJDesign(page_1.getFile());
			page0.setValue(jDesign, getNode());
		}
		if (page == page2) {
			if (node instanceof MJrxml)
				return null;
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
						Display.getDefault().asyncExec(new Runnable() {
							public void run() {
								if (hasDepResources)
									page1.fillData();
								else {
									getContainer().showPage(getNextPage(page1));
									getContainer().updateButtons();
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
		return true;
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
		}
		this.selection = selection;
	}

	private void doFinish() {
		try {
			Method m = getContainer().getClass().getDeclaredMethod("finishPressed", null);
			if (m != null) {
				m.setAccessible(true);
				m.invoke(getContainer());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
