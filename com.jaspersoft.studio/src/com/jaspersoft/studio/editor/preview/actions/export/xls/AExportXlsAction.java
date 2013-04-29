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

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;

import com.jaspersoft.studio.editor.preview.actions.export.AbstractExportAction;
import com.jaspersoft.studio.editor.preview.view.report.swt.IReportViewer;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public abstract class AExportXlsAction extends AbstractExportAction {

	public AExportXlsAction(IReportViewer viewer, JasperReportsConfiguration jContext) {
		super(viewer, jContext);
	}

	@Override
	protected JRAbstractExporter getExporter(JasperReportsConfiguration jContext) {
		JRXlsAbstractExporter exporter = createExporter(jContext);

		exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET,
				jContext.getPropertyBoolean(JRXlsAbstractExporterParameter.PROPERTY_ONE_PAGE_PER_SHEET));
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
				jContext.getPropertyBoolean(JRXlsAbstractExporterParameter.PROPERTY_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS));
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				jContext.getPropertyBoolean(JRXlsAbstractExporterParameter.PROPERTY_REMOVE_EMPTY_SPACE_BETWEEN_ROWS));

		exporter.setParameter(JRXlsAbstractExporterParameter.IS_COLLAPSE_ROW_SPAN,
				jContext.getPropertyBoolean(JRXlsAbstractExporterParameter.PROPERTY_COLLAPSE_ROW_SPAN));
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_IGNORE_GRAPHICS,
				jContext.getPropertyBoolean(JRXlsAbstractExporterParameter.PROPERTY_IGNORE_GRAPHICS));
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_IMAGE_BORDER_FIX_ENABLED,
				jContext.getPropertyBoolean(JRXlsAbstractExporterParameter.PROPERTY_IMAGE_BORDER_FIX_ENABLED));
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND,
				jContext.getPropertyBoolean(JRXlsAbstractExporterParameter.PROPERTY_WHITE_PAGE_BACKGROUND));

		exporter.setParameter(JRXlsAbstractExporterParameter.CREATE_CUSTOM_PALETTE,
				jContext.getPropertyBoolean(JRXlsAbstractExporterParameter.PROPERTY_CREATE_CUSTOM_PALETTE));
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_IGNORE_CELL_BACKGROUND,
				jContext.getPropertyBoolean(JRXlsAbstractExporterParameter.PROPERTY_IGNORE_CELL_BACKGROUND));
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_IGNORE_CELL_BORDER,
				jContext.getPropertyBoolean(JRXlsAbstractExporterParameter.PROPERTY_IGNORE_CELL_BORDER));
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_FONT_SIZE_FIX_ENABLED,
				jContext.getPropertyBoolean(JRXlsAbstractExporterParameter.PROPERTY_FONT_SIZE_FIX_ENABLED));

		exporter.setParameter(JRXlsAbstractExporterParameter.IS_DETECT_CELL_TYPE,
				jContext.getPropertyBoolean(JRXlsAbstractExporterParameter.PROPERTY_DETECT_CELL_TYPE));

		exporter.setParameter(JRXlsAbstractExporterParameter.MAXIMUM_ROWS_PER_SHEET,
				jContext.getPropertyInteger(JRXlsAbstractExporterParameter.PROPERTY_MAXIMUM_ROWS_PER_SHEET));

		exporter.setParameter(JRXlsAbstractExporterParameter.PASSWORD,
				jContext.getProperty(JRXlsAbstractExporterParameter.PROPERTY_PASSWORD));
		exporter.setParameter(JRXlsAbstractExporterParameter.SHEET_NAMES,
				jContext.getProperty(JRXlsAbstractExporterParameter.PROPERTY_SHEET_NAMES_PREFIX));
		return exporter;
	}

	protected abstract JRXlsAbstractExporter createExporter(JasperReportsConfiguration jContext);
}
