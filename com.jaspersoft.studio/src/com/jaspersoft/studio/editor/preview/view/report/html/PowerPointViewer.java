package com.jaspersoft.studio.editor.preview.view.report.html;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.preview.actions.export.AbstractExportAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsPptxAction;
import com.jaspersoft.studio.editor.preview.view.report.swt.ReportViewer;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

public class PowerPointViewer extends ASystemViewer {

	public PowerPointViewer(Composite parent, PropertiesHelper ph) {
		super(parent, ph);
	}

	@Override
	protected AbstractExportAction createExporter(ReportViewer rptv) {
		return new ExportAsPptxAction(rptv, getPropertiesHelper());
	}

	@Override
	protected String getExtension() {
		return ".pptx";
	}

}
