package com.jaspersoft.studio.editor.preview.view.report.html;

import java.io.File;

import net.sf.jasperreports.engine.JasperPrint;

import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.preview.actions.export.AbstractExportAction;
import com.jaspersoft.studio.editor.preview.view.report.swt.ReportViewer;
import com.jaspersoft.studio.editor.preview.view.report.swt.SWTViewer;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;
import com.jaspersoft.studio.utils.UIUtils;

public abstract class ASystemViewer extends SWTViewer {

	public ASystemViewer(Composite parent, PropertiesHelper ph) {
		super(parent, ph);
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
