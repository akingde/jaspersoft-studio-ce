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
package com.jaspersoft.studio.editor.preview.view.report.system;

import java.io.File;

import net.sf.jasperreports.engine.JasperPrint;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.editor.preview.actions.export.AbstractExportAction;
import com.jaspersoft.studio.editor.preview.view.report.swt.ReportViewer;
import com.jaspersoft.studio.editor.preview.view.report.swt.SWTViewer;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;
import com.jaspersoft.studio.utils.UIUtils;

public abstract class ASystemViewer extends SWTViewer {

	public ASystemViewer(Composite parent, PropertiesHelper ph) {
		super(parent, ph);
	}

	@Override
	protected Control createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.BORDER);
		composite.setLayout(new GridLayout());

		Button lbl = new Button(composite, SWT.PUSH);
		lbl.setText("The report is opened in an external editor.\n Press here to open it again.");
		GridData layoutData = new GridData(SWT.CENTER,SWT.CENTER,true,true,1,1);

		GC gc =new GC(lbl);
		FontMetrics fontMetrics = gc.getFontMetrics();
		int h = fontMetrics.getHeight();
		gc.dispose();
		layoutData.heightHint=h*2 + 50;
		lbl.setLayoutData(layoutData);

		rptviewer = new ReportViewer();

		return composite;
	}

	@Override
	public void contribute2ToolBar(IToolBarManager tmanager) {
	}

	protected abstract AbstractExportAction createExporter(ReportViewer rptv);

	protected abstract String getExtension();

	@Override
	public void setJRPRint(JasperPrint jrprint) {
		super.setJRPRint(jrprint);
		if (this.jrprint != null) {
			try {
				File tmpFile = File.createTempFile("report", getExtension());
				AbstractExportAction exp = createExporter(rptviewer);
				exp.export(tmpFile);

				Program p = Program.findProgram(getExtension());
				if (p != null)
					p.execute(tmpFile.getAbsolutePath());
			} catch (Exception e) {
				UIUtils.showError(e);
			}
		}
	}

}
