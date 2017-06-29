/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.actions.export;

import java.io.File;

import net.sf.jasperreports.eclipse.viewer.IReportViewer;
import net.sf.jasperreports.engine.export.JRExportProgressMonitor;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.export.SimpleReportExportConfiguration;
import net.sf.jasperreports.export.SimpleXmlExporterOutput;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ExportAsXmlWithImagesAction extends AExportAction {

	public ExportAsXmlWithImagesAction(IReportViewer viewer, JasperReportsConfiguration jContext,
			ExportMenuAction parentMenu) {
		super(viewer, jContext, parentMenu);

		setText(Messages.ExportAsXmlWithImagesAction_title);
		setToolTipText(Messages.ExportAsXmlWithImagesAction_tooltip);

		setFileExtensions(new String[] { "*.xml", "*.jrpxml" }); //$NON-NLS-1$ //$NON-NLS-2$
		setFilterNames(new String[] { Messages.ExportAsXmlWithImagesAction_filtername,
				Messages.ExportAsXmlWithImagesAction_filtername1 });
		setDefaultFileExtension("xml"); //$NON-NLS-1$
	}

	@Override
	protected JRXmlExporter getExporter(JasperReportsConfiguration jContext, JRExportProgressMonitor monitor, File file) {
		JRXmlExporter exp = new JRXmlExporter(jContext);
		SimpleXmlExporterOutput expOut = new SimpleXmlExporterOutput(file);
		expOut.setEmbeddingImages(Boolean.TRUE);
		exp.setExporterOutput(expOut);

		SimpleReportExportConfiguration rconf = new SimpleReportExportConfiguration();
		setupReportConfiguration(rconf, monitor);
		exp.setConfiguration(rconf);

		return exp;
	}
}
