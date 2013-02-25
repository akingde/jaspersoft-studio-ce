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

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.publish.FindResources;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class Publish2ServerWizard extends Wizard {
	private JasperDesign jDesign;
	private RUnitLocationPage page0;
	private ResourcesPage page1;
	private DatasourceSelectionPage page2;
	private int page;
	private ANode n;
	private JasperReportsConfiguration jrConfig;

	public Publish2ServerWizard(ANode n, JasperDesign jDesign,
			JasperReportsConfiguration jrConfig, int page) {
		super();
		setWindowTitle(Messages.Publish2ServerWizard_Title);
		this.jDesign = jDesign;
		this.page = page;
		this.n = n;
		this.jrConfig = jrConfig;
		setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		page0 = new RUnitLocationPage(jDesign, n);
		addPage(page0);

		page1 = new ResourcesPage(jrConfig);
		addPage(page1);

		page2 = new DatasourceSelectionPage(jrConfig);
		addPage(page2);
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		final MReportUnit rpunit = getReportUnit();
		if (page == page1) {
			try {
				getContainer().run(false, true, new IRunnableWithProgress() {
					public void run(IProgressMonitor monitor)
							throws InvocationTargetException,
							InterruptedException {
						monitor.beginTask(
								Messages.Publish2ServerWizard_MonitorName,
								IProgressMonitor.UNKNOWN);
						try {
							MReportUnit mrunit = page0.getReportUnit();
							n = mrunit;

							if (new FindResources().find(monitor, mrunit,
									jrConfig, jDesign)) {
								Display.getDefault().syncExec(new Runnable() {
									public void run() {
										page1.fillData();
										page2.setPreviousPage(page1);
									}
								});
							} else {
								if (rpunit.getValue().getIsNew())
									Display.getDefault().asyncExec(
											new Runnable() {
												@Override
												public void run() {
													page2.configurePage(
															rpunit.getParent(),
															rpunit);
													page2.setPreviousPage(page0);
													page2.setPageComplete(true);
													getContainer().showPage(
															page2);
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
}
