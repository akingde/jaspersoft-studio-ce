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
package com.jaspersoft.studio.editor.preview.view.report.system;

import net.sf.jasperreports.eclipse.viewer.ReportViewer;
import net.sf.jasperreports.engine.JasperPrint;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.preview.actions.export.AbstractExportAction;
import com.jaspersoft.studio.editor.preview.actions.export.xls.ExportAsOdsAction;
import com.jaspersoft.studio.preferences.exporter.ExcelExporterPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class OdsViewer extends ASystemViewer {

	public OdsViewer(Composite parent, JasperReportsConfiguration jContext) {
		super(parent, jContext);
	}

	@Override
	protected AbstractExportAction createExporter(ReportViewer rptv) {
		return new ExportAsOdsAction(rptv, jContext, null);
	}

	@Override
	protected String getExtension(JasperPrint jrprint) {
		return ".ods";
	}

	@Override
	public PreferencePage getPreferencePage() {
		return new ExcelExporterPreferencePage();
	}
}
