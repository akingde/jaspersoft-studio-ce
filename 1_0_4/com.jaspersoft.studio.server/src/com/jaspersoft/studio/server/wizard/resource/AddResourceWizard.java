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

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.server.model.MResource;

public class AddResourceWizard extends Wizard {
	private AddResourcePage page0;

	public AddResourceWizard(MResource parent) {
		super();
		setWindowTitle("Add Resource wizard");
		this.parent = parent;
	}

	@Override
	public void addPages() {
		page0 = new AddResourcePage(parent);
		addPage(page0);
	}

	private MResource parent;

	public MResource getResource() {
		return page0.getResource();
	}

	@Override
	public boolean performFinish() {
		return true;
	}

}