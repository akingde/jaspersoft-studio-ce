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
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

import org.eclipse.core.resources.IResource;

import com.jasperassistant.designer.viewer.IReportViewer;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

public class ExportAsXlsAction extends AbstractExportAction {

	public ExportAsXlsAction(IReportViewer viewer, IResource file) {
		super(viewer, file);

		setText(Messages.ExportAsXlsAction_title);
		setToolTipText(Messages.ExportAsXlsAction_tooltip);

		setFileExtensions(new String[] { "*.xls" }); //$NON-NLS-1$
		setFilterNames(new String[] { Messages.ExportAsXlsAction_filternames });
		setDefaultFileExtension("xls"); //$NON-NLS-1$
	}

	@Override
	protected JRAbstractExporter getExporter(PropertiesHelper ph) {
		JRXlsExporter exporter = new JRXlsExporter();

		exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET,
				ph.getBoolean(JRXlsAbstractExporterParameter.PROPERTY_ONE_PAGE_PER_SHEET));
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
				ph.getBoolean(JRXlsAbstractExporterParameter.PROPERTY_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS));
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				ph.getBoolean(JRXlsAbstractExporterParameter.PROPERTY_REMOVE_EMPTY_SPACE_BETWEEN_ROWS));

		exporter.setParameter(JRXlsAbstractExporterParameter.IS_COLLAPSE_ROW_SPAN,
				ph.getBoolean(JRXlsAbstractExporterParameter.PROPERTY_COLLAPSE_ROW_SPAN));
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_IGNORE_GRAPHICS,
				ph.getBoolean(JRXlsAbstractExporterParameter.PROPERTY_IGNORE_GRAPHICS));
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_IMAGE_BORDER_FIX_ENABLED,
				ph.getBoolean(JRXlsAbstractExporterParameter.PROPERTY_IMAGE_BORDER_FIX_ENABLED));
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND,
				ph.getBoolean(JRXlsAbstractExporterParameter.PROPERTY_WHITE_PAGE_BACKGROUND));

		exporter.setParameter(JRXlsAbstractExporterParameter.CREATE_CUSTOM_PALETTE,
				ph.getBoolean(JRXlsAbstractExporterParameter.PROPERTY_CREATE_CUSTOM_PALETTE));
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_IGNORE_CELL_BACKGROUND,
				ph.getBoolean(JRXlsAbstractExporterParameter.PROPERTY_IGNORE_CELL_BACKGROUND));
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_IGNORE_CELL_BORDER,
				ph.getBoolean(JRXlsAbstractExporterParameter.PROPERTY_IGNORE_CELL_BORDER));
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_FONT_SIZE_FIX_ENABLED,
				ph.getBoolean(JRXlsAbstractExporterParameter.PROPERTY_FONT_SIZE_FIX_ENABLED));

		exporter.setParameter(JRXlsAbstractExporterParameter.IS_DETECT_CELL_TYPE,
				ph.getBoolean(JRXlsAbstractExporterParameter.PROPERTY_DETECT_CELL_TYPE));

		exporter.setParameter(JRXlsAbstractExporterParameter.MAXIMUM_ROWS_PER_SHEET,
				ph.getInteger(JRXlsAbstractExporterParameter.PROPERTY_MAXIMUM_ROWS_PER_SHEET));

		exporter.setParameter(JRXlsAbstractExporterParameter.PASSWORD,
				ph.getString(JRXlsAbstractExporterParameter.PROPERTY_PASSWORD));
		exporter.setParameter(JRXlsAbstractExporterParameter.SHEET_NAMES,
				ph.getString(JRXlsAbstractExporterParameter.PROPERTY_SHEET_NAMES_PREFIX));
		return exporter;
	}
}
