/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.server.wizard.resource;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.ResourceFactory;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.wizard.resource.page.AddResourcePage;
import com.jaspersoft.studio.server.wizard.resource.page.ResourceDescriptorPage;

public class AddResourceWizard extends Wizard {
	private AddResourcePage page0;

	public AddResourceWizard(ANode parent) {
		super();
		setWindowTitle(Messages.AddResourceWizard_windowtitle);
		setNeedsProgressMonitor(true);
		this.parent = parent;
	}

	private boolean skipFirstPage = false;

	public void setSkipFirstPage(boolean skipFirstPage) {
		this.skipFirstPage = skipFirstPage;
	}

	@Override
	public IWizardPage getStartingPage() {
		if (skipFirstPage && page0 != null)
			return getNextPage(page0);
		return super.getStartingPage();
	}

	private boolean dsonly = false;

	public void setOnlyDatasource(boolean dsonly) {
		this.dsonly = dsonly;
	}

	private boolean ruOnly = false;

	public void setOnlyReportUnit(boolean ruOnly) {
		this.ruOnly = ruOnly;
	}

	@Override
	public void addPages() {
		page0 = new AddResourcePage(parent);
		page0.setOnlyDatasource(dsonly);
		page0.setOnlyReportUnit(ruOnly);
		addPage(page0);

		addPage(new ResourceDescriptorPage());
	}

	private ResourceFactory rfactory = new ResourceFactory();

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page == page0) {
			MResource r = page0.getResource();
			if (r != null) {
				IWizardPage rpage = rfactory.getResourcePage(parent, r);
				if (rpage != null) {
					if (getPage(rpage.getName()) == null)
						addPage(rpage);
					return rpage;
				}
			}
		}
		return super.getNextPage(page);
	}

	private ANode parent;

	public MResource getResource() {
		return page0.getResource();
	}

	@Override
	public boolean performFinish() {
		return true;
	}

}
