/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.actions.export.xls;

import net.sf.jasperreports.eclipse.viewer.IReportViewer;
import net.sf.jasperreports.engine.export.JRExportProgressMonitor;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.export.SimpleOdsReportConfiguration;

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
	protected JROdsExporter createExporter(JasperReportsConfiguration jContext, JRExportProgressMonitor monitor) {
		JROdsExporter exp = new JROdsExporter(jContext);

		SimpleOdsReportConfiguration rconf = new SimpleOdsReportConfiguration();
		setupReportConfiguration(rconf, monitor);
		exp.setConfiguration(rconf);

		return exp;
	}
}
