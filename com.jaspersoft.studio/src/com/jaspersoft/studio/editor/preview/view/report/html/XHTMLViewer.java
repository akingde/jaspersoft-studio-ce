/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.view.report.html;

import net.sf.jasperreports.eclipse.viewer.ReportViewer;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.preview.actions.export.AExportAction;
import com.jaspersoft.studio.editor.preview.actions.export.html.ExportAsXHtmlAction;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class XHTMLViewer extends HTMLViewer {
	public XHTMLViewer(Composite parent, JasperReportsConfiguration jContext) {
		super(parent, jContext);
	}

	@Override
	protected AExportAction createExporter(ReportViewer rptv) {
		return new ExportAsXHtmlAction(rptv, jContext, null);
	}

}
