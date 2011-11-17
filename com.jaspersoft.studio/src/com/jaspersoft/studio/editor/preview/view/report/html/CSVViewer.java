package com.jaspersoft.studio.editor.preview.view.report.html;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.preview.actions.export.AbstractExportAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsCsvAction;
import com.jaspersoft.studio.editor.preview.view.report.swt.ReportViewer;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

public class CSVViewer extends AFileViewer {

	public CSVViewer(Composite parent, PropertiesHelper ph) {
		super(parent, ph);
	}

	protected AbstractExportAction createExporter(ReportViewer rptv) {
		return new ExportAsCsvAction(rptv, getPropertiesHelper());
	}

	protected String getExtension() {
		return ".csv";
	}

}
