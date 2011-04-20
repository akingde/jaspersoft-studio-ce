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
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;

import org.eclipse.core.resources.IResource;

import com.jasperassistant.designer.viewer.IReportViewer;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

public class ExportAsCsvAction extends AbstractExportAction {

	public ExportAsCsvAction(IReportViewer viewer, IResource file) {
		super(viewer, file);
		setText(Messages.ExportAsCsvAction_text);
		setToolTipText(Messages.ExportAsCsvAction_tooltip);

		setFileExtensions(new String[] { "*.csv" }); //$NON-NLS-1$
		setFilterNames(new String[] { Messages.ExportAsCsvAction_filtername });
		setDefaultFileExtension("csv"); //$NON-NLS-1$
	}

	@Override
	protected JRAbstractExporter getExporter(PropertiesHelper ph) {
		JRCsvExporter exp = new JRCsvExporter();

		exp.setParameter(JRCsvExporterParameter.FIELD_DELIMITER,
				ph.getString(JRCsvExporterParameter.PROPERTY_FIELD_DELIMITER));
		exp.setParameter(JRCsvExporterParameter.RECORD_DELIMITER,
				ph.getString(JRCsvExporterParameter.PROPERTY_RECORD_DELIMITER));

		return exp;
	}

}
