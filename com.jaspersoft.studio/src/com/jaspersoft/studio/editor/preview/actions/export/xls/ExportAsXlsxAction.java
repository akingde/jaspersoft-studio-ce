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

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

import com.jaspersoft.studio.editor.preview.view.report.swt.IReportViewer;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ExportAsXlsxAction extends AExportXlsAction {

	public ExportAsXlsxAction(IReportViewer viewer, JasperReportsConfiguration jContext) {
		super(viewer, jContext);

		setText(Messages.ExportAsXlsxAction_title);
		setToolTipText(Messages.ExportAsXlsxAction_tooltip);

		setFileExtensions(new String[] { "*.xlsx;*.xlsm" }); //$NON-NLS-1$
		setFilterNames(new String[] { "XLSx (*.xlsx)", "XLSm (*.xlsm)" });
		setDefaultFileExtension("xlsx"); //$NON-NLS-1$
	}

	@Override
	protected void setFileExtensions() {
		JasperPrint jrPrint = getReportViewer().getDocument();
		String ext = ".xlsx";
		if (jrPrint.getProperty(JRXlsxExporter.PROPERTY_MACRO_TEMPLATE) != null)
			ext = ".xlsm";

		setDefaultFileExtension(ext);
		setFilterNames(new String[] { ext.toUpperCase() + " (*." + ext + ")" });
		setFileExtensions(new String[] { "*" + ext });
	}

	@Override
	protected JRXlsAbstractExporter createExporter(JasperReportsConfiguration jContext) {
		return new JRXlsxExporter(jContext);
	}
}
