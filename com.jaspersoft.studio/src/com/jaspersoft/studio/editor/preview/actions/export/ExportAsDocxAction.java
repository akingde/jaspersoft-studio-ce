/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.actions.export;

import java.io.File;

import net.sf.jasperreports.eclipse.viewer.IReportViewer;
import net.sf.jasperreports.engine.export.JRExportProgressMonitor;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleDocxReportConfiguration;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ExportAsDocxAction extends AExportAction {

	public ExportAsDocxAction(IReportViewer viewer, JasperReportsConfiguration jContext, ExportMenuAction parentMenu) {
		super(viewer, jContext, parentMenu);

		setText(Messages.ExportAsDocxAction_title);
		setToolTipText(Messages.ExportAsDocxAction_tooltip);

		setFileExtensions(new String[] { "*.docx" }); //$NON-NLS-1$
		setFilterNames(new String[] { Messages.ExportAsDocxAction_filtername });
		setDefaultFileExtension("docx"); //$NON-NLS-1$
	}

	@Override
	protected JRDocxExporter getExporter(JasperReportsConfiguration jContext, JRExportProgressMonitor monitor, File file) {
		JRDocxExporter exp = new JRDocxExporter(jContext);
		exp.setExporterOutput(new SimpleOutputStreamExporterOutput(file));

		SimpleDocxReportConfiguration rconf = new SimpleDocxReportConfiguration();
		setupReportConfiguration(rconf, monitor);
		exp.setConfiguration(rconf);

		return exp;
	}
}
