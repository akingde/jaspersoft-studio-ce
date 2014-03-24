/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.actions.export;

import java.io.File;

import net.sf.jasperreports.eclipse.viewer.IReportViewer;
import net.sf.jasperreports.engine.export.JRExportProgressMonitor;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ExportAsPdfAction extends AExportAction {

	public ExportAsPdfAction(IReportViewer viewer, JasperReportsConfiguration jContext, ExportMenuAction parentMenu) {
		super(viewer, jContext, parentMenu);

		setText(Messages.ExportAsPdfAction_title);
		setToolTipText(Messages.ExportAsPdfAction_tooltip);

		setFileExtensions(new String[] { "*.pdf" }); //$NON-NLS-1$
		setFilterNames(new String[] { Messages.ExportAsPdfAction_filtername });
		setDefaultFileExtension("pdf"); //$NON-NLS-1$
	}

	@Override
	protected JRPdfExporter getExporter(JasperReportsConfiguration jContext, JRExportProgressMonitor monitor, File file) {
		JRPdfExporter exp = new JRPdfExporter(jContext);
		exp.setExporterOutput(new SimpleOutputStreamExporterOutput(file));

		SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
		// metadata
		// exp.setParameter(JRPdfExporterParameter.METADATA_AUTHOR,
		// jContext.getProperty(PDFExporterPreferencePage.NSF_EXPORT_PDF_METADATA_AUTHOR));
		// exp.setParameter(JRPdfExporterParameter.METADATA_CREATOR,
		// jContext.getProperty(PDFExporterPreferencePage.NSF_EXPORT_PDF_METADATA_CREATOR));
		// exp.setParameter(JRPdfExporterParameter.METADATA_KEYWORDS,
		// jContext.getProperty(PDFExporterPreferencePage.NSF_EXPORT_PDF_METADATA_KEYWORDS));
		// exp.setParameter(JRPdfExporterParameter.METADATA_SUBJECT,
		// jContext.getProperty(PDFExporterPreferencePage.NSF_EXPORT_PDF_METADATA_SUBJECT));
		// exp.setParameter(JRPdfExporterParameter.METADATA_TITLE,
		// jContext.getProperty(PDFExporterPreferencePage.NSF_EXPORT_PDF_METADATA_TITLE));
		// security
		// String perm = jContext.getProperty(PDFExporterPreferencePage.NSF_EXPORT_PDF_PERMISSION);
		// if (!Misc.isNullOrEmpty(perm))
		// exp.setParameter(JRPdfExporterParameter.PERMISSIONS, new Integer(perm));
		exp.setConfiguration(conf);

		SimplePdfReportConfiguration rconf = new SimplePdfReportConfiguration();
		setupReportConfiguration(rconf, monitor);
		exp.setConfiguration(rconf);

		return exp;
	}
}
