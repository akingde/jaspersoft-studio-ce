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
import net.sf.jasperreports.engine.export.JRXlsAbstractExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;

import com.jaspersoft.studio.editor.preview.actions.export.ExportMenuAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ExportAsOdsAction extends AExportXlsAction {

	public ExportAsOdsAction(IReportViewer viewer, JasperReportsConfiguration jContext, ExportMenuAction parentMenu) {
		super(viewer, jContext, parentMenu);

		setText(Messages.ExportAsOdsAction_title);
		setToolTipText(Messages.ExportAsOdsAction_tooltip);

		setFileExtensions(new String[] { "*.ods" }); //$NON-NLS-1$
		setFilterNames(new String[] { Messages.ExportAsOdsAction_filtername });
		setDefaultFileExtension("ods"); //$NON-NLS-1$
	}

	@Override
	protected JRXlsAbstractExporter createExporter(JasperReportsConfiguration jContext) {
		return new JROdsExporter(jContext);
	}
}
