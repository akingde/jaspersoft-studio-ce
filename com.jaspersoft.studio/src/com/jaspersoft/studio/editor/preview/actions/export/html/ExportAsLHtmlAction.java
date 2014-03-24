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
package com.jaspersoft.studio.editor.preview.actions.export.html;

import java.io.File;

import net.sf.jasperreports.eclipse.viewer.IReportViewer;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRExportProgressMonitor;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;

import com.jaspersoft.studio.editor.preview.actions.export.AExportAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportMenuAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ExportAsLHtmlAction extends AExportAction {

	public ExportAsLHtmlAction(IReportViewer viewer, JasperReportsConfiguration jContext, ExportMenuAction parentMenu) {
		super(viewer, jContext, parentMenu);

		setText(Messages.ExportAsLHtmlAction_title);
		setToolTipText(Messages.ExportAsLHtmlAction_title);

		setFileExtensions(new String[] { "*.html" }); //$NON-NLS-1$ //$NON-NLS-2$
		setFilterNames(new String[] { Messages.ExportAsHtmlAction_filternames1 });
		setDefaultFileExtension("html"); //$NON-NLS-1$
	}

	@Override
	protected HtmlExporter getExporter(JasperReportsConfiguration jContext, JRExportProgressMonitor monitor, File file) {
		HtmlExporter exp = new HtmlExporter(jContext);
		SimpleHtmlExporterOutput expOut = new SimpleHtmlExporterOutput(file);
		exp.setExporterOutput(expOut);

		SimpleHtmlReportConfiguration rconf = new SimpleHtmlReportConfiguration();
		setupReportConfiguration(rconf, monitor);
		exp.setConfiguration(rconf);

		// exp.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML,
		// jContext.getProperty(HTMLExporterPreferencePage.NSF_EXPORT_HTML_BETWEEN_PAGES));
		// exp.setParameter(JRHtmlExporterParameter.HTML_HEADER,
		// jContext.getProperty(HTMLExporterPreferencePage.NSF_EXPORT_HTML_HEADER));
		// exp.setParameter(JRHtmlExporterParameter.HTML_FOOTER,
		// jContext.getProperty(HTMLExporterPreferencePage.NSF_EXPORT_HTML_FOOTER));
		//
		// exp.setParameter(JRHtmlExporterParameter.IMAGES_DIR_NAME,
		// jContext.getProperty(HTMLExporterPreferencePage.NSF_EXPORT_HTML_IMAGES_DIR_NAME));
		// exp.setParameter(JRHtmlExporterParameter.IMAGES_URI,
		// jContext.getProperty(HTMLExporterPreferencePage.NSF_EXPORT_HTML_IMAGES_URI));
		// exp.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR,
		// jContext.getPropertyBoolean(HTMLExporterPreferencePage.NSF_EXPORT_HTML_IS_OUTPUT_IMAGES_TO_DIR));

		return exp;
	}
}
