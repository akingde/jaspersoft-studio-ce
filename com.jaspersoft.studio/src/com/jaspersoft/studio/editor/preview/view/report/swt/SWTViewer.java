/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.preview.view.report.swt;

import net.sf.jasperreports.engine.JasperPrint;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.editor.preview.view.report.ExportMenu;
import com.jaspersoft.studio.editor.preview.view.report.IJRPrintable;
import com.jaspersoft.studio.editor.preview.view.report.swt.action.FirstPageAction;
import com.jaspersoft.studio.editor.preview.view.report.swt.action.LastPageAction;
import com.jaspersoft.studio.editor.preview.view.report.swt.action.NextPageAction;
import com.jaspersoft.studio.editor.preview.view.report.swt.action.PreviousPageAction;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

public class SWTViewer extends APreview implements IJRPrintable {

	protected ReportViewer rptviewer;

	public SWTViewer(Composite parent, PropertiesHelper ph) {
		super(parent, ph);
	}

	@Override
	protected Control createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		composite.setLayout(layout);

		rptviewer = new ReportViewer();
		Control ctrl = rptviewer.createControl(composite);
		ctrl.setLayoutData(new GridData(GridData.FILL_BOTH));

		return composite;
	}

	@Override
	public void contribute2ToolBar(IToolBarManager tmanager) {
		tmanager.add(new FirstPageAction(rptviewer));
		tmanager.add(new PreviousPageAction(rptviewer));
		tmanager.add(new NextPageAction(rptviewer));
		tmanager.add(new LastPageAction(rptviewer));
		tmanager.add(new Separator());

		tmanager.add(ExportMenu.getExportMenu(rptviewer, getPropertiesHelper()));
	}

	protected JasperPrint jrprint;

	public void setJRPRint(JasperPrint jrprint) {
		if (this.jrprint == null || this.jrprint != jrprint) {
			rptviewer.setDocument(jrprint);
			rptviewer.gotoFirstPage();
		}
		this.jrprint = jrprint;
	}

}
