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
package com.jaspersoft.studio.editor.preview.actions.export.xls;

import net.sf.jasperreports.eclipse.viewer.IReportViewer;
import net.sf.jasperreports.engine.export.JExcelApiMetadataExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractMetadataExporterParameter;

import com.jaspersoft.studio.editor.preview.actions.export.ExportMenuAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ExportAsXlsMetadataAction extends AExportXlsAction {

	public ExportAsXlsMetadataAction(IReportViewer viewer, JasperReportsConfiguration jContext,
			ExportMenuAction parentMenu) {
		super(viewer, jContext, parentMenu);

		setText("Export As XLS With Metadata");
		setToolTipText("Export As XLS With Metadata");

		setFileExtensions(new String[] { "*.xls" }); //$NON-NLS-1$
		setFilterNames(new String[] { Messages.ExportAsExcelAPIAction_filternames });
		setDefaultFileExtension("xls"); //$NON-NLS-1$
	}

	@Override
	protected JRXlsAbstractExporter createExporter(JasperReportsConfiguration jContext) {
		JExcelApiMetadataExporter exp = new JExcelApiMetadataExporter(jContext);
		exp.setParameter(JRXlsAbstractMetadataExporterParameter.WRITE_HEADER,
				jContext.getProperty(JRXlsAbstractMetadataExporterParameter.PROPERTY_WRITE_HEADER));
		exp.setParameter(JRXlsAbstractMetadataExporterParameter.COLUMN_NAMES,
				jContext.getProperty(JRXlsAbstractMetadataExporterParameter.PROPERTY_COLUMN_NAMES_PREFIX));
		return exp;
	}
}
