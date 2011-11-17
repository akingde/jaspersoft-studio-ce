package com.jaspersoft.studio.editor.preview.view.report.html;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.preview.actions.export.AbstractExportAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsXlsxAction;
import com.jaspersoft.studio.editor.preview.view.report.swt.ReportViewer;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

public class XlsViewer extends ASystemViewer {

	public XlsViewer(Composite parent, PropertiesHelper ph) {
		super(parent, ph);
	}

	@Override
	protected AbstractExportAction createExporter(ReportViewer rptv) {
		return new ExportAsXlsxAction(rptv, getPropertiesHelper());
	}

	@Override
	protected String getExtension() {
		return ".xlsx";
	}

}
