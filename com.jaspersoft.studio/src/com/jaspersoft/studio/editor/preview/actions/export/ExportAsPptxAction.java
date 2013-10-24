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

import net.sf.jasperreports.eclipse.viewer.IReportViewer;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ExportAsPptxAction extends AbstractExportAction {

	public ExportAsPptxAction(IReportViewer viewer, JasperReportsConfiguration jContext, ExportMenuAction parentMenu) {
		super(viewer, jContext, parentMenu);

		setText("Export As PowerPoint");
		setToolTipText("Export as PowerPoint");

		setFileExtensions(new String[] { "*.pptx" }); //$NON-NLS-1$
		setFilterNames(new String[] { "PowerPoint (*.pptx)" });
		setDefaultFileExtension("pptx"); //$NON-NLS-1$
	}

	@Override
	protected JRAbstractExporter getExporter(JasperReportsConfiguration jContext) {
		JRPptxExporter exp = new JRPptxExporter(jContext);

		return exp;
	}
}
