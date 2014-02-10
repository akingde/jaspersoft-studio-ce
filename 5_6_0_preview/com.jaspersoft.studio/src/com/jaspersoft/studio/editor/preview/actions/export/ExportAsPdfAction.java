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

import net.sf.jasperreports.eclipse.viewer.IReportViewer;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.exporter.PDFExporterPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ExportAsPdfAction extends AbstractExportAction {

	public ExportAsPdfAction(IReportViewer viewer, JasperReportsConfiguration jContext, ExportMenuAction parentMenu) {
		super(viewer, jContext, parentMenu);

		setText(Messages.ExportAsPdfAction_title);
		setToolTipText(Messages.ExportAsPdfAction_tooltip);

		setFileExtensions(new String[] { "*.pdf" }); //$NON-NLS-1$
		setFilterNames(new String[] { Messages.ExportAsPdfAction_filtername });
		setDefaultFileExtension("pdf"); //$NON-NLS-1$
	}

	@Override
	protected JRAbstractExporter getExporter(JasperReportsConfiguration jContext) {
		JRPdfExporter exp = new JRPdfExporter(jContext);
		// common
		exp.setParameter(JRPdfExporterParameter.PDF_VERSION,
				jContext.getPropertyCharacter(JRPdfExporterParameter.PROPERTY_PDF_VERSION));
		exp.setParameter(JRPdfExporterParameter.PDFA_CONFORMANCE,
				jContext.getProperty(JRPdfExporterParameter.PROPERTY_PDFA_CONFORMANCE));
		exp.setParameter(JRPdfExporterParameter.PDFA_ICC_PROFILE_PATH,
				jContext.getPropertyCharacter(JRPdfExporterParameter.PROPERTY_PDFA_ICC_PROFILE_PATH));

		exp.setParameter(JRPdfExporterParameter.IS_COMPRESSED,
				jContext.getPropertyBoolean(JRPdfExporterParameter.PROPERTY_COMPRESSED));
		exp.setParameter(JRPdfExporterParameter.IS_CREATING_BATCH_MODE_BOOKMARKS,
				jContext.getPropertyBoolean(JRPdfExporterParameter.PROPERTY_CREATE_BATCH_MODE_BOOKMARKS));
		exp.setParameter(JRPdfExporterParameter.FORCE_SVG_SHAPES,
				jContext.getPropertyBoolean(JRPdfExporterParameter.PROPERTY_FORCE_SVG_SHAPES));
		exp.setParameter(JRPdfExporterParameter.FORCE_LINEBREAK_POLICY,
				jContext.getPropertyBoolean(JRPdfExporterParameter.PROPERTY_FORCE_LINEBREAK_POLICY));
		exp.setParameter(JRPdfExporterParameter.IS_TAGGED,
				jContext.getPropertyBoolean(JRPdfExporterParameter.PROPERTY_TAGGED));
		exp.setParameter(JRPdfExporterParameter.PRINT_SCALING,
				jContext.getProperty(JRPdfExporterParameter.PROPERTY_PRINT_SCALING));
		exp.setParameter(JRPdfExporterParameter.TAG_LANGUAGE,
				jContext.getProperty(JRPdfExporterParameter.PROPERTY_TAG_LANGUAGE));
		exp.setParameter(JRPdfExporterParameter.PDF_JAVASCRIPT,
				jContext.getProperty(JRPdfExporterParameter.PROPERTY_PDF_JAVASCRIPT));
		// metadata
		exp.setParameter(JRPdfExporterParameter.METADATA_AUTHOR,
				jContext.getProperty(PDFExporterPreferencePage.NSF_EXPORT_PDF_METADATA_AUTHOR));
		exp.setParameter(JRPdfExporterParameter.METADATA_CREATOR,
				jContext.getProperty(PDFExporterPreferencePage.NSF_EXPORT_PDF_METADATA_CREATOR));
		exp.setParameter(JRPdfExporterParameter.METADATA_KEYWORDS,
				jContext.getProperty(PDFExporterPreferencePage.NSF_EXPORT_PDF_METADATA_KEYWORDS));
		exp.setParameter(JRPdfExporterParameter.METADATA_SUBJECT,
				jContext.getProperty(PDFExporterPreferencePage.NSF_EXPORT_PDF_METADATA_SUBJECT));
		exp.setParameter(JRPdfExporterParameter.METADATA_TITLE,
				jContext.getProperty(PDFExporterPreferencePage.NSF_EXPORT_PDF_METADATA_TITLE));
		// security
		exp.setParameter(JRPdfExporterParameter.IS_ENCRYPTED,
				jContext.getPropertyBoolean(JRPdfExporterParameter.PROPERTY_ENCRYPTED));
		exp.setParameter(JRPdfExporterParameter.IS_128_BIT_KEY,
				jContext.getPropertyBoolean(JRPdfExporterParameter.PROPERTY_128_BIT_KEY));
		exp.setParameter(JRPdfExporterParameter.USER_PASSWORD,
				jContext.getProperty(JRPdfExporterParameter.PROPERTY_USER_PASSWORD));
		exp.setParameter(JRPdfExporterParameter.OWNER_PASSWORD,
				jContext.getProperty(JRPdfExporterParameter.PROPERTY_OWNER_PASSWORD));
		String perm = jContext.getProperty(PDFExporterPreferencePage.NSF_EXPORT_PDF_PERMISSION);
		if (perm != null && !perm.isEmpty())
			exp.setParameter(JRPdfExporterParameter.PERMISSIONS, new Integer(perm));

		return exp;
	}
}
