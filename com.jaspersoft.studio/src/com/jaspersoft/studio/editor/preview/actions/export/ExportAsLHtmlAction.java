/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.actions.export;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;

import com.jaspersoft.studio.editor.preview.view.report.swt.IReportViewer;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.exporter.HTMLExporterPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ExportAsLHtmlAction extends AbstractExportAction {

	public ExportAsLHtmlAction(IReportViewer viewer, JasperReportsConfiguration jContext) {
		super(viewer, jContext);

		setText(Messages.ExportAsLHtmlAction_title);
		setToolTipText(Messages.ExportAsLHtmlAction_title);

		setFileExtensions(new String[] { "*.html" }); //$NON-NLS-1$ //$NON-NLS-2$
		setFilterNames(new String[] { Messages.ExportAsHtmlAction_filternames1 });
		setDefaultFileExtension("html"); //$NON-NLS-1$
	}

	@Override
	protected JRAbstractExporter getExporter(JasperReportsConfiguration jContext) {
		HtmlExporter exp = new HtmlExporter(jContext);

		exp.setParameter(JRHtmlExporterParameter.FLUSH_OUTPUT,
				jContext.getPropertyBoolean(JRHtmlExporterParameter.PROPERTY_FLUSH_OUTPUT));
		exp.setParameter(JRHtmlExporterParameter.FRAMES_AS_NESTED_TABLES,
				jContext.getPropertyBoolean(JRHtmlExporterParameter.PROPERTY_FRAMES_AS_NESTED_TABLES));
		exp.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				jContext.getPropertyBoolean(JRHtmlExporterParameter.PROPERTY_REMOVE_EMPTY_SPACE_BETWEEN_ROWS));
		exp.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
				jContext.getPropertyBoolean(JRHtmlExporterParameter.PROPERTY_USING_IMAGES_TO_ALIGN));
		exp.setParameter(JRHtmlExporterParameter.IS_WHITE_PAGE_BACKGROUND,
				jContext.getPropertyBoolean(JRHtmlExporterParameter.PROPERTY_WHITE_PAGE_BACKGROUND));
		exp.setParameter(JRHtmlExporterParameter.IS_WRAP_BREAK_WORD,
				jContext.getPropertyBoolean(JRHtmlExporterParameter.PROPERTY_WRAP_BREAK_WORD));

		exp.setParameter(JRHtmlExporterParameter.SIZE_UNIT,
				jContext.getProperty(JRHtmlExporterParameter.PROPERTY_SIZE_UNIT));

		exp.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML,
				jContext.getProperty(HTMLExporterPreferencePage.NSF_EXPORT_HTML_BETWEEN_PAGES));
		exp.setParameter(JRHtmlExporterParameter.HTML_HEADER,
				jContext.getProperty(HTMLExporterPreferencePage.NSF_EXPORT_HTML_HEADER));
		exp.setParameter(JRHtmlExporterParameter.HTML_FOOTER,
				jContext.getProperty(HTMLExporterPreferencePage.NSF_EXPORT_HTML_FOOTER));

		exp.setParameter(JRHtmlExporterParameter.IMAGES_DIR_NAME,
				jContext.getProperty(HTMLExporterPreferencePage.NSF_EXPORT_HTML_IMAGES_DIR_NAME));
		exp.setParameter(JRHtmlExporterParameter.IMAGES_URI,
				jContext.getProperty(HTMLExporterPreferencePage.NSF_EXPORT_HTML_IMAGES_URI));
		exp.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR,
				jContext.getPropertyBoolean(HTMLExporterPreferencePage.NSF_EXPORT_HTML_IS_OUTPUT_IMAGES_TO_DIR));

		return exp;
	}
}
