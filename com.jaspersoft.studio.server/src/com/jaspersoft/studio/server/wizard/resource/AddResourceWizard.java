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
package com.jaspersoft.studio.server.wizard.resource;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.ResourceFactory;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.wizard.resource.page.AddResourcePage;
import com.jaspersoft.studio.server.wizard.resource.page.ResourceDescriptorPage;

public class AddResourceWizard extends Wizard {
	private AddResourcePage page0;

	public AddResourceWizard(ANode parent) {
		super();
		setWindowTitle("Add Resource wizard");
		this.parent = parent;
	}

	private boolean dsonly = false;

	public void setOnlyDatasource(boolean dsonly) {
		this.dsonly = dsonly;
	}

	@Override
	public void addPages() {
		page0 = new AddResourcePage(parent);
		page0.setOnlyDatasource(dsonly);
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