package com.jaspersoft.studio.editor.preview.view.report.html;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.preview.actions.export.AbstractExportAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsRtfAction;
import com.jaspersoft.studio.editor.preview.view.report.swt.ReportViewer;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

public class RTFViewer extends ASystemViewer {

	public RTFViewer(Composite parent, PropertiesHelper ph) {
		super(parent, ph);
	}

	@Override
	protected AbstractExportAction createExporter(ReportViewer rptv) {
		return new ExportAsRtfAction(rptv, getPropertiesHelper());
	}

	@Override
	protected String getExtension() {
		return ".rtf";
	}

}
