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
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

import org.eclipse.core.resources.IResource;

import com.jasperassistant.designer.viewer.IReportViewer;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.exporter.PDFExporterPreferencePage;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

public class ExportAsPdfAction extends AbstractExportAction {

	public ExportAsPdfAction(IReportViewer viewer, IResource file) {
		super(viewer, file);

		setText(Messages.ExportAsPdfAction_title);
		setToolTipText(Messages.ExportAsPdfAction_tooltip);

		setFileExtensions(new String[] { "*.pdf" }); //$NON-NLS-1$
		setFilterNames(new String[] { Messages.ExportAsPdfAction_filtername });
		setDefaultFileExtension("pdf"); //$NON-NLS-1$
	}

	@Override
	protected JRAbstractExporter getExporter(PropertiesHelper ph) {
		JRPdfExporter exp = new JRPdfExporter();
		// common
		exp.setParameter(JRPdfExporterParameter.PDF_VERSION,
				ph.getCharacter(PDFExporterPreferencePage.NSF_EXPORT_PDF_VERSION));
		exp.setParameter(JRPdfExporterParameter.IS_COMPRESSED, ph.getBoolean(JRPdfExporterParameter.PROPERTY_COMPRESSED));
		exp.setParameter(JRPdfExporterParameter.IS_CREATING_BATCH_MODE_BOOKMARKS,
				ph.getBoolean(JRPdfExporterParameter.PROPERTY_CREATE_BATCH_MODE_BOOKMARKS));
		exp.setParameter(JRPdfExporterParameter.FORCE_SVG_SHAPES,
				ph.getBoolean(JRPdfExporterParameter.PROPERTY_FORCE_SVG_SHAPES));
		exp.setParameter(JRPdfExporterParameter.FORCE_LINEBREAK_POLICY,
				ph.getBoolean(JRPdfExporterParameter.PROPERTY_FORCE_LINEBREAK_POLICY));
		exp.setParameter(JRPdfExporterParameter.IS_TAGGED, ph.getBoolean(JRPdfExporterParameter.PROPERTY_TAGGED));
		exp.setParameter(JRPdfExporterParameter.PRINT_SCALING, ph.getString(JRPdfExporterParameter.PROPERTY_PRINT_SCALING));
		exp.setParameter(JRPdfExporterParameter.TAG_LANGUAGE, ph.getString(JRPdfExporterParameter.PROPERTY_TAG_LANGUAGE));
		exp.setParameter(JRPdfExporterParameter.PDF_JAVASCRIPT,
				ph.getString(JRPdfExporterParameter.PROPERTY_PDF_JAVASCRIPT));
		// metadata
		exp.setParameter(JRPdfExporterParameter.METADATA_AUTHOR,
				ph.getString(PDFExporterPreferencePage.NSF_EXPORT_PDF_METADATA_AUTHOR));
		exp.setParameter(JRPdfExporterParameter.METADATA_CREATOR,
				ph.getString(PDFExporterPreferencePage.NSF_EXPORT_PDF_METADATA_CREATOR));
		exp.setParameter(JRPdfExporterParameter.METADATA_KEYWORDS,
				ph.getString(PDFExporterPreferencePage.NSF_EXPORT_PDF_METADATA_KEYWORDS));
		exp.setParameter(JRPdfExporterParameter.METADATA_SUBJECT,
				ph.getString(PDFExporterPreferencePage.NSF_EXPORT_PDF_METADATA_SUBJECT));
		exp.setParameter(JRPdfExporterParameter.METADATA_TITLE,
				ph.getString(PDFExporterPreferencePage.NSF_EXPORT_PDF_METADATA_TITLE));
		// security
		exp.setParameter(JRPdfExporterParameter.IS_ENCRYPTED, ph.getBoolean(JRPdfExporterParameter.PROPERTY_ENCRYPTED));
		exp.setParameter(JRPdfExporterParameter.IS_128_BIT_KEY, ph.getBoolean(JRPdfExporterParameter.PROPERTY_128_BIT_KEY));
		exp.setParameter(JRPdfExporterParameter.USER_PASSWORD, ph.getString(JRPdfExporterParameter.PROPERTY_USER_PASSWORD));
		exp.setParameter(JRPdfExporterParameter.OWNER_PASSWORD,
				ph.getString(JRPdfExporterParameter.PROPERTY_OWNER_PASSWORD));
		exp.setParameter(JRPdfExporterParameter.PERMISSIONS,
				ph.getString(PDFExporterPreferencePage.NSF_EXPORT_PDF_PERMISSION));

		return exp;
	}
}
