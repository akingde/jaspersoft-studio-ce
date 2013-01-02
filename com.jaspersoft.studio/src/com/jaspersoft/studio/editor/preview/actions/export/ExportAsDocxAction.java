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
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;

import com.jaspersoft.studio.editor.preview.view.report.swt.IReportViewer;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ExportAsDocxAction extends AbstractExportAction {

	public ExportAsDocxAction(IReportViewer viewer, JasperReportsConfiguration jContext) {
		super(viewer, jContext);

		setText(Messages.ExportAsDocxAction_title);
		setToolTipText(Messages.ExportAsDocxAction_tooltip);

		setFileExtensions(new String[] { "*.docx" }); //$NON-NLS-1$
		setFilterNames(new String[] { Messages.ExportAsDocxAction_filtername });
		setDefaultFileExtension("docx"); //$NON-NLS-1$
	}

	@Override
	protected JRAbstractExporter getExporter(JasperReportsConfiguration jContext) {
		JRDocxExporter exp = new JRDocxExporter(jContext);

		exp.setParameter(JRDocxExporterParameter.FRAMES_AS_NESTED_TABLES,
				jContext.getPropertyBoolean(JRDocxExporterParameter.PROPERTY_FRAMES_AS_NESTED_TABLES));
		exp.setParameter(JRDocxExporterParameter.FLEXIBLE_ROW_HEIGHT,
				jContext.getPropertyBoolean(JRDocxExporterParameter.PROPERTY_FLEXIBLE_ROW_HEIGHT));
		return exp;
	}
}
