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
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;

import com.jaspersoft.studio.editor.preview.view.report.swt.IReportViewer;
import com.jaspersoft.studio.messages.Messages;
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
		return exp;
	}

}
