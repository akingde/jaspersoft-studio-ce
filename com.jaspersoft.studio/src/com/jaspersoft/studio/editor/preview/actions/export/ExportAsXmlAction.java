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
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.JRXmlExporterParameter;

import com.jaspersoft.studio.editor.preview.view.report.swt.IReportViewer;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.exporter.XMLExporterPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ExportAsXmlAction extends AbstractExportAction {

	public ExportAsXmlAction(IReportViewer viewer, JasperReportsConfiguration jContext) {
		super(viewer, jContext);

		setText(Messages.ExportAsXmlAction_title);
		setToolTipText(Messages.ExportAsXmlAction_tooltip);

		setFileExtensions(new String[] { "*.xml", "*.jrpxml" }); //$NON-NLS-1$ //$NON-NLS-2$
		setFilterNames(new String[] { Messages.ExportAsXmlAction_ext1, Messages.ExportAsXmlAction_ext2 });
		setDefaultFileExtension("xml"); //$NON-NLS-1$
	}

	@Override
	protected JRAbstractExporter getExporter(JasperReportsConfiguration jContext) {
		JRXmlExporter exp = new JRXmlExporter(jContext);

		exp.setParameter(JRXmlExporterParameter.IS_EMBEDDING_IMAGES, Boolean.FALSE);
		exp.setParameter(JRXmlExporterParameter.DTD_LOCATION, XMLExporterPreferencePage.NSF_EXPORT_XML_DTD_LOCATION);
		return exp;
	}
}
