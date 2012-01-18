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

package com.jaspersoft.studio.editor.preview.actions.export;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;

import com.jaspersoft.studio.editor.preview.view.report.swt.IReportViewer;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

public class ExportAsOdtAction extends AbstractExportAction {

	public ExportAsOdtAction(IReportViewer viewer, PropertiesHelper ph) {
		super(viewer, ph);

		setText(Messages.ExportAsOdtAction_title);
		setToolTipText(Messages.ExportAsOdtAction_tooltips);

		setFileExtensions(new String[] { "*.odt" }); //$NON-NLS-1$
		setFilterNames(new String[] { Messages.ExportAsOdtAction_filtername });
		setDefaultFileExtension("odt"); //$NON-NLS-1$
	}

	@Override
	protected JRAbstractExporter getExporter(PropertiesHelper ph) {
		JROdtExporter exp = new JROdtExporter();

		return exp;
	}
}
