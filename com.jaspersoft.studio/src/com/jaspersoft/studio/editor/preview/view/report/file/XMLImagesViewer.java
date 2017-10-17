/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.view.report.file;

import net.sf.jasperreports.eclipse.viewer.ReportViewer;
import net.sf.jasperreports.engine.JasperPrint;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.preview.actions.export.AExportAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsXmlWithImagesAction;
import com.jaspersoft.studio.preferences.exporter.XMLExporterPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class XMLImagesViewer extends AFileViewer {

	public XMLImagesViewer(Composite parent, JasperReportsConfiguration jContext) {
		super(parent, jContext);
	}

	protected AExportAction createExporter(ReportViewer rptv) {
		return new ExportAsXmlWithImagesAction(rptv, jContext, null);
	}

	protected String getExtension() {
		return ".xml";
	}

	@Override
	public void pageGenerated(JasperPrint arg0, int arg1) {

	}

	@Override
	public void pageUpdated(JasperPrint arg0, int arg1) {

	}

	@Override
	public PreferencePage getPreferencePage() {
		return new XMLExporterPreferencePage();
	}

}
