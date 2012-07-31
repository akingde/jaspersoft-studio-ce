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

package com.jaspersoft.studio.editor.preview.actions.export;

import java.io.File;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.export.JRExportProgressMonitor;
import net.sf.jasperreports.engine.util.JRSaver;

import org.eclipse.swt.custom.BusyIndicator;

import com.jaspersoft.studio.editor.preview.view.report.swt.IReportViewer;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ExportAsJasperReportsAction extends AbstractExportAction {

	public ExportAsJasperReportsAction(IReportViewer viewer, JasperReportsConfiguration jContext) {
		super(viewer, jContext);

		setText(Messages.ExportAsJasperReportsAction_title);
		setToolTipText(Messages.ExportAsJasperReportsAction_tooltip);

		setFileExtensions(new String[] { "*.jrprint" }); //$NON-NLS-1$
		setFilterNames(new String[] { Messages.ExportAsJasperReportsAction_filtername });
		setDefaultFileExtension("jrprint"); //$NON-NLS-1$
	}

	@Override
	protected void exportWithProgress(File file, JRExportProgressMonitor monitor) throws Throwable {
		final java.io.File f = file.getAbsoluteFile();
		final Throwable[] ex = new Throwable[1];
		BusyIndicator.showWhile(null, new Runnable() {
			public void run() {
				try {
					JRSaver.saveObject(getReportViewer().getDocument(), f);
				} catch (Throwable e) {
					ex[0] = e;
				}
			}
		});
		if (ex[0] != null)
			throw ex[0];
	}

	@Override
	protected JRAbstractExporter getExporter(JasperReportsConfiguration jContext) {
		return null;
	}
}
