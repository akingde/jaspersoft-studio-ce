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

package com.jaspersoft.studio.editor.preview.action;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;

import org.eclipse.core.resources.IResource;

import com.jasperassistant.designer.viewer.IReportViewer;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.exporter.HTMLExporterPreferencePage;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

public class ExportAsHtmlAction extends AbstractExportAction {

	public ExportAsHtmlAction(IReportViewer viewer, IResource file) {
		super(viewer, file);

		setText(Messages.ExportAsHtmlAction_title);
		setToolTipText(Messages.ExportAsHtmlAction_tooltip);

		setFileExtensions(new String[] { "*.html", "*.htm" }); //$NON-NLS-1$ //$NON-NLS-2$
		setFilterNames(new String[] { Messages.ExportAsHtmlAction_filternames1, Messages.ExportAsHtmlAction_filternames2 });
		setDefaultFileExtension("html"); //$NON-NLS-1$
	}

	@Override
	protected JRAbstractExporter getExporter(PropertiesHelper ph) {
		JRHtmlExporter exp = new JRHtmlExporter();

		exp.setParameter(JRHtmlExporterParameter.FLUSH_OUTPUT, ph.getBoolean(JRHtmlExporterParameter.PROPERTY_FLUSH_OUTPUT));
		exp.setParameter(JRHtmlExporterParameter.FRAMES_AS_NESTED_TABLES,
				ph.getBoolean(JRHtmlExporterParameter.PROPERTY_FRAMES_AS_NESTED_TABLES));
		exp.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				ph.getBoolean(JRHtmlExporterParameter.PROPERTY_REMOVE_EMPTY_SPACE_BETWEEN_ROWS));
		exp.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
				ph.getBoolean(JRHtmlExporterParameter.PROPERTY_USING_IMAGES_TO_ALIGN));
		exp.setParameter(JRHtmlExporterParameter.IS_WHITE_PAGE_BACKGROUND,
				ph.getBoolean(JRHtmlExporterParameter.PROPERTY_WHITE_PAGE_BACKGROUND));
		exp.setParameter(JRHtmlExporterParameter.IS_WRAP_BREAK_WORD,
				ph.getBoolean(JRHtmlExporterParameter.PROPERTY_WRAP_BREAK_WORD));

		exp.setParameter(JRHtmlExporterParameter.SIZE_UNIT, ph.getString(JRHtmlExporterParameter.PROPERTY_SIZE_UNIT));

		exp.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML,
				ph.getString(HTMLExporterPreferencePage.NSF_EXPORT_HTML_BETWEEN_PAGES));
		exp.setParameter(JRHtmlExporterParameter.HTML_HEADER,
				ph.getString(HTMLExporterPreferencePage.NSF_EXPORT_HTML_HEADER));
		exp.setParameter(JRHtmlExporterParameter.HTML_FOOTER,
				ph.getString(HTMLExporterPreferencePage.NSF_EXPORT_HTML_FOOTER));

		exp.setParameter(JRHtmlExporterParameter.IMAGES_DIR_NAME,
				ph.getString(HTMLExporterPreferencePage.NSF_EXPORT_HTML_IMAGES_DIR_NAME));
		exp.setParameter(JRHtmlExporterParameter.IMAGES_URI,
				ph.getString(HTMLExporterPreferencePage.NSF_EXPORT_HTML_IMAGES_URI));
		exp.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR,
				ph.getBoolean(HTMLExporterPreferencePage.NSF_EXPORT_HTML_IS_OUTPUT_IMAGES_TO_DIR));

		return exp;
	}
}
