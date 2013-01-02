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

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.JRXmlExporterParameter;

import com.jaspersoft.studio.editor.preview.view.report.swt.IReportViewer;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.exporter.XMLExporterPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ExportAsXmlWithImagesAction extends AbstractExportAction {

	public ExportAsXmlWithImagesAction(IReportViewer viewer, JasperReportsConfiguration jContext) {
		super(viewer, jContext);

		setText(Messages.ExportAsXmlWithImagesAction_title);
		setToolTipText(Messages.ExportAsXmlWithImagesAction_tooltip);

		setFileExtensions(new String[] { "*.xml", "*.jrpxml" }); //$NON-NLS-1$ //$NON-NLS-2$
		setFilterNames(new String[] { Messages.ExportAsXmlWithImagesAction_filtername,
				Messages.ExportAsXmlWithImagesAction_filtername1 });
		setDefaultFileExtension("xml"); //$NON-NLS-1$
	}

	@Override
	protected JRAbstractExporter getExporter(JasperReportsConfiguration jContext) {
		JRXmlExporter exp = new JRXmlExporter(jContext);

		exp.setParameter(JRXmlExporterParameter.IS_EMBEDDING_IMAGES, Boolean.TRUE);
		exp.setParameter(JRXmlExporterParameter.DTD_LOCATION, XMLExporterPreferencePage.NSF_EXPORT_XML_DTD_LOCATION);
		return exp;
	}
}
