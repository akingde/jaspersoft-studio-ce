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
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ExportAsCsvAction extends AbstractExportAction {

	public ExportAsCsvAction(IReportViewer viewer, JasperReportsConfiguration jContext, ExportMenuAction parentMenu) {
		super(viewer, jContext, parentMenu);
		setText(Messages.ExportAsCsvAction_text);
		setToolTipText(Messages.ExportAsCsvAction_tooltip);

		setFileExtensions(new String[] { "*.csv" }); //$NON-NLS-1$
		setFilterNames(new String[] { Messages.ExportAsCsvAction_filtername });
		setDefaultFileExtension("csv"); //$NON-NLS-1$
	}

	@Override
	protected JRAbstractExporter getExporter(JasperReportsConfiguration jContext) {
		JRCsvExporter exp = new JRCsvExporter(jContext);
		exp.setParameter(JRCsvExporterParameter.FIELD_DELIMITER,
				jContext.getProperty(JRCsvExporterParameter.PROPERTY_FIELD_DELIMITER));
		exp.setParameter(JRCsvExporterParameter.RECORD_DELIMITER,
				jContext.getProperty(JRCsvExporterParameter.PROPERTY_RECORD_DELIMITER));

		return exp;
	}

}
