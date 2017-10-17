/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.view.report.system;

import java.io.File;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JasperPrint;

import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.preview.actions.export.AExportAction;
import com.jaspersoft.studio.editor.preview.stats.Statistics;
import com.jaspersoft.studio.editor.preview.view.control.ReportController;
import com.jaspersoft.studio.editor.preview.view.report.swt.SWTViewer;
import com.jaspersoft.studio.utils.Callback;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public abstract class ASystemViewer extends SWTViewer {

	public ASystemViewer(Composite parent, JasperReportsConfiguration jContext) {
		super(parent, jContext);
	}

	protected abstract String getExtension(JasperPrint jrprint);

	@Override
	public void setJRPRint(final Statistics stats, final JasperPrint jrprint, boolean refresh) {
		super.setJRPRint(stats, jrprint, refresh);
		if (jrprint != null) {
			try {
				final String ext = getExtension(jrprint);
				final AExportAction exp = createExporterAction(rptviewer);
				final File tmpFile = File.createTempFile("report", ext);
				stats.startCount(ReportController.ST_EXPORTTIME);
				UIUtils.getDisplay().asyncExec(new Runnable() {

					@Override
					public void run() {
						try {
							exp.preview(tmpFile, jrprint, new Callback<File>() {

								@Override
								public void completed(File value) {
									stats.endCount(ReportController.ST_EXPORTTIME);
									stats.setValue(ReportController.ST_REPORTSIZE, tmpFile.length());

									Program p = Program.findProgram(ext);
									if (p != null)
										p.execute(tmpFile.getAbsolutePath());
									else
										// TODO here we can propose a better dialog, like open with...(create association, etc.)
										UIUtils.showWarning(String
												.format(
														"No file association defined in your sistem for: %s\nFile is located at: \n\n%s\n\nPlease open it manually or fix file association and retry.",
														ext, tmpFile.getAbsolutePath()));
								}
							});
						} catch (Exception e) {
							UIUtils.showError(e);
						}
					}
				});
			} catch (Exception e) {
				UIUtils.showError(e);
			}
		}
	}
}
