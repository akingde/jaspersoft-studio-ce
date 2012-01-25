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
package com.jaspersoft.studio.server.publish;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.server.model.MReportUnit;

public class Publish2ServerWizard extends Wizard {
	private JasperDesign jDesign;
	private ServerLocationPage page0;

	public Publish2ServerWizard(JasperDesign jDesign) {
		super();
		setWindowTitle("Server profile wizard");
		this.jDesign = jDesign;
	}

	@Override
	public void addPages() {
		page0 = new ServerLocationPage(jDesign);
		addPage(page0);
	}

	public MReportUnit getReportUnit() {
		return page0.getReportUnit();
	}

	@Override
	public boolean performFinish() {
		return true;
	}
}