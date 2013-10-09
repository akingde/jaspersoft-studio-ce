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
import net.sf.jasperreports.engine.export.JRCsvMetadataExporter;
import net.sf.jasperreports.engine.export.JRCsvMetadataExporterParameter;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ExportAsCsvMetadataAction extends AbstractExportAction {

	public ExportAsCsvMetadataAction(IReportViewer viewer, JasperReportsConfiguration jContext,
			ExportMenuAction parentMenu) {
		super(viewer, jContext, parentMenu);

		setText(Messages.ExportAsCsvMetadataAction_title);
		setToolTipText(Messages.ExportAsCsvMetadataAction_tooltip);

		setFileExtensions(new String[] { "*.csv" }); //$NON-NLS-1$
		setFilterNames(new String[] { Messages.ExportAsCsvAction_filtername });
		setDefaultFileExtension("csv"); //$NON-NLS-1$
	}

	@Override
	protected JRAbstractExporter getExporter(JasperReportsConfiguration jContext) {
		JRCsvMetadataExporter exp = new JRCsvMetadataExporter(jContext);

		String param = jContext.getProperty(JRCsvMetadataExporterParameter.PROPERTY_FIELD_DELIMITER);
		exp.setParameter(JRCsvMetadataExporterParameter.FIELD_DELIMITER, param);

		param = jContext.getProperty(JRCsvMetadataExporterParameter.PROPERTY_RECORD_DELIMITER);
		exp.setParameter(JRCsvMetadataExporterParameter.RECORD_DELIMITER, param);

		param = jContext.getProperty(JRCsvMetadataExporterParameter.PROPERTY_COLUMN_NAMES_PREFIX);
		if (param != null)
			exp.setParameter(JRCsvMetadataExporterParameter.COLUMN_NAMES, param);

		Boolean bparam = jContext.getPropertyBoolean(JRCsvMetadataExporterParameter.PROPERTY_WRITE_HEADER);
		exp.setParameter(JRCsvMetadataExporterParameter.WRITE_HEADER, bparam);

		return exp;
	}
}
