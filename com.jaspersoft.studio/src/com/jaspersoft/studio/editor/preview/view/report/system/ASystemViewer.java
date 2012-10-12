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

import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.preview.actions.export.AbstractExportAction;
import com.jaspersoft.studio.editor.preview.stats.Statistics;
import com.jaspersoft.studio.editor.preview.view.control.ReportControler;
import com.jaspersoft.studio.editor.preview.view.report.swt.ReportViewer;
import com.jaspersoft.studio.editor.preview.view.report.swt.SWTViewer;
import com.jaspersoft.studio.utils.UIUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public abstract class ASystemViewer extends SWTViewer {

	public ASystemViewer(Composite parent, JasperReportsConfiguration jContext) {
		super(parent, jContext);
	}

	protected abstract AbstractExportAction createExporter(ReportViewer rptv);

	protected abstract String getExtension(JasperPrint jrprint);

	@Override
	public void setJRPRint(Statistics stats, JasperPrint jrprint) {
		boolean same = this.jrprint == jrprint;
		super.setJRPRint(stats, jrprint);
		if (this.jrprint != null && !same) {
			try {
				String ext = getExtension(jrprint);
				File tmpFile = File.createTempFile("report", ext);
				AbstractExportAction exp = createExporter(rptviewer);
				stats.startCount(ReportControler.ST_EXPORTTIME);
				exp.export(tmpFile);
				stats.endCount(ReportControler.ST_EXPORTTIME);
				stats.setValue(ReportControler.ST_REPORTSIZE, tmpFile.length());

				Program p = Program.findProgram(ext);
				if (p != null)
					p.execute(tmpFile.getAbsolutePath());
				else
					// TODO here we can propose a better dialog, like open with...(create association, etc.)
					UIUtils
							.showWarning(String
									.format(
											"No file association defined in your sistem for: %s\nFile is located at: \n\n%s\n\nPlease open it manually or fix file association and retry.",
											ext, tmpFile.getAbsolutePath()));
			} catch (Exception e) {
				UIUtils.showError(e);
			}
		}
	}
}
