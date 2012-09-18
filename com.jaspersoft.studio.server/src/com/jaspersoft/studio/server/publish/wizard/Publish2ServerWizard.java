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
package com.jaspersoft.studio.server.publish.wizard;

import java.lang.reflect.InvocationTargetException;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.publish.FindResources;
import com.jaspersoft.studio.utils.UIUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class Publish2ServerWizard extends Wizard {
	private JasperDesign jDesign;
	private RUnitLocationPage page0;
	private ResourcesPage page1;
	private DatasourceSelectionPage page2;
	private int page;
	private ANode n;
	JasperReportsConfiguration jrConfig;

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
		if (page == page1) {
			try {
				getContainer().run(true, true, new IRunnableWithProgress() {
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

									}
								});
							} else {
								Display.getDefault().syncExec(new Runnable() {

									@Override
									public void run() {
										page2.configurePage(getReportUnit()
												.getParent(), getReportUnit());
										getContainer().showPage(page2);
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
			} catch (InvocationTargetException e) {
				UIUtils.showError(e.getCause());
			} catch (InterruptedException e) {
				UIUtils.showError(e.getCause());
			}

			// configure page 2
			page2.configurePage(getReportUnit().getParent(), getReportUnit());
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
}