/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.actions.export;

import java.io.File;

import net.sf.jasperreports.eclipse.viewer.IReportViewer;
import net.sf.jasperreports.engine.export.JRExportProgressMonitor;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.export.SimpleRtfReportConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ExportAsRtfAction extends AExportAction {

	public ExportAsRtfAction(IReportViewer viewer, JasperReportsConfiguration jContext, ExportMenuAction parentMenu) {
		super(viewer, jContext, parentMenu);

		setText(Messages.ExportAsRtfAction_title);
		setToolTipText(Messages.ExportAsRtfAction_tooltip);

		setFileExtensions(new String[] { "*.rtf" }); //$NON-NLS-1$
		setFilterNames(new String[] { Messages.ExportAsRtfAction_filtername });
		setDefaultFileExtension("rtf"); //$NON-NLS-1$
	}

	@Override
	protected JRRtfExporter getExporter(JasperReportsConfiguration jContext, JRExportProgressMonitor monitor, File file) {
		JRRtfExporter exp = new JRRtfExporter(jContext);
		exp.setExporterOutput(new SimpleWriterExporterOutput(file));

		SimpleRtfReportConfiguration rconf = new SimpleRtfReportConfiguration();
		setupReportConfiguration(rconf, monitor);
		exp.setConfiguration(rconf);

		return exp;
	}
}
