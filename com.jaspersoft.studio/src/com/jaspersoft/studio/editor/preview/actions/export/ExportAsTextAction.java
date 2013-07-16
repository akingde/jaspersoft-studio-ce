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

import net.sf.jasperreports.eclipse.viewer.IReportViewer;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.exporter.TextExporterPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ExportAsTextAction extends AbstractExportAction {

	public ExportAsTextAction(IReportViewer viewer, JasperReportsConfiguration jContext) {
		super(viewer, jContext);
		setText(Messages.ExportAsTextAction_title);
		setToolTipText(Messages.ExportAsTextAction_tooltip);

		setFileExtensions(new String[] { "*.txt" }); //$NON-NLS-1$
		setFilterNames(new String[] { Messages.ExportAsTextAction_filtername });
		setDefaultFileExtension("txt"); //$NON-NLS-1$
	}

	@Override
	protected JRAbstractExporter getExporter(JasperReportsConfiguration jContext) {
		JRTextExporter exp = new JRTextExporter(jContext);

		exp.setParameter(JRTextExporterParameter.CHARACTER_WIDTH,
				jContext.getPropertyFloat(JRTextExporterParameter.PROPERTY_CHARACTER_WIDTH, 0f));
		exp.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT,
				jContext.getPropertyFloat(JRTextExporterParameter.PROPERTY_CHARACTER_HEIGHT, 0f));
		exp.setParameter(JRTextExporterParameter.PAGE_WIDTH,
				jContext.getPropertyInteger(JRTextExporterParameter.PROPERTY_PAGE_WIDTH, 0));
		exp.setParameter(JRTextExporterParameter.PAGE_HEIGHT,
				jContext.getPropertyInteger(JRTextExporterParameter.PROPERTY_PAGE_HEIGHT, 0));

		exp.setParameter(JRTextExporterParameter.LINE_SEPARATOR,
				jContext.getProperty(TextExporterPreferencePage.NSF_EXPORT_TEXT_LINE_SEPARATOR, "\n"));
		exp.setParameter(JRTextExporterParameter.BETWEEN_PAGES_TEXT,
				jContext.getProperty(TextExporterPreferencePage.NSF_EXPORT_TEXT_BETWEEN_PAGE_TEXT));

		return exp;
	}

}
