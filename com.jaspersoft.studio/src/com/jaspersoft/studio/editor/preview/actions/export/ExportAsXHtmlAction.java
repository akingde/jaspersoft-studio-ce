/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */

package com.jaspersoft.studio.editor.preview.actions.export;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRXhtmlExporter;

import com.jaspersoft.studio.editor.preview.view.report.swt.IReportViewer;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.exporter.HTMLExporterPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ExportAsXHtmlAction extends AbstractExportAction {

	public ExportAsXHtmlAction(IReportViewer viewer, JasperReportsConfiguration jContext) {
		super(viewer, jContext);

		setText(Messages.ExportAsXHtmlAction_title);
		setToolTipText(Messages.ExportAsXHtmlAction_tooltip);

		setFileExtensions(new String[] { "*.xhtml" }); //$NON-NLS-1$ //$NON-NLS-2$
		setFilterNames(new String[] { Messages.ExportAsXHtmlAction_filternames });
		setDefaultFileExtension("xhtml"); //$NON-NLS-1$
	}

	@Override
	protected JRAbstractExporter getExporter(JasperReportsConfiguration jContext) {
		JRXhtmlExporter exp = new JRXhtmlExporter(jContext);

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
