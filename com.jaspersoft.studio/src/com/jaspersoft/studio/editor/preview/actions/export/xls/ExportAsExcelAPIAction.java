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
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporter;

import com.jaspersoft.studio.editor.preview.actions.export.ExportMenuAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ExportAsExcelAPIAction extends AExportXlsAction {

	public ExportAsExcelAPIAction(IReportViewer viewer, JasperReportsConfiguration jContext, ExportMenuAction parentMenu) {
		super(viewer, jContext, parentMenu);

		setText(Messages.ExportAsExcelAPIAction_title);
		setToolTipText(Messages.ExportAsExcelAPIAction_tooltip);

		setFileExtensions(new String[] { "*.xls" }); //$NON-NLS-1$
		setFilterNames(new String[] { Messages.ExportAsExcelAPIAction_filternames });
		setDefaultFileExtension("xls"); //$NON-NLS-1$
	}

	@Override
	protected JRXlsAbstractExporter createExporter(JasperReportsConfiguration jContext) {
		return new JExcelApiExporter(jContext);
	}
}
