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
package com.jaspersoft.studio.editor.preview.view.report;

import net.sf.jasperreports.eclipse.viewer.IReportViewer;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;

import com.jaspersoft.studio.editor.preview.actions.export.ExportAsCsvAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsCsvMetadataAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsDocxAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsJasperReportsAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsOdtAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsPdfAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsPptxAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsRtfAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsTextAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsXmlAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsXmlWithImagesAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportMenuAction;
import com.jaspersoft.studio.editor.preview.actions.export.html.ExportAsHtmlAction;
import com.jaspersoft.studio.editor.preview.actions.export.html.ExportAsLHtmlAction;
import com.jaspersoft.studio.editor.preview.actions.export.html.ExportAsXHtmlAction;
import com.jaspersoft.studio.editor.preview.actions.export.xls.ExportAsExcelAPIAction;
import com.jaspersoft.studio.editor.preview.actions.export.xls.ExportAsOdsAction;
import com.jaspersoft.studio.editor.preview.actions.export.xls.ExportAsXlsAction;
import com.jaspersoft.studio.editor.preview.actions.export.xls.ExportAsXlsMetadataAction;
import com.jaspersoft.studio.editor.preview.actions.export.xls.ExportAsXlsxAction;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ExportMenu {
	public static ExportMenuAction getExportMenu(IReportViewer rptviewer, JasperReportsConfiguration jContext) {
		ExportMenuAction exportMenu = new ExportMenuAction(rptviewer);

		MenuManager mm = exportMenu.getMenuManager();
		mm.add(new ExportAsJasperReportsAction(rptviewer, jContext));
		mm.add(new Separator());

		mm.add(new ExportAsPdfAction(rptviewer, jContext));
		mm.add(new ExportAsLHtmlAction(rptviewer, jContext));
		mm.add(new ExportAsHtmlAction(rptviewer, jContext));
		mm.add(new ExportAsXHtmlAction(rptviewer, jContext));
		mm.add(new Separator());

		mm.add(new ExportAsRtfAction(rptviewer, jContext));
		mm.add(new ExportAsDocxAction(rptviewer, jContext));
		mm.add(new ExportAsOdtAction(rptviewer, jContext));
		mm.add(new ExportAsOdsAction(rptviewer, jContext));
		mm.add(new ExportAsPptxAction(rptviewer, jContext));
		mm.add(new ExportAsTextAction(rptviewer, jContext));

		mm.add(new Separator());
		mm.add(new ExportAsXlsAction(rptviewer, jContext));
		mm.add(new ExportAsXlsMetadataAction(rptviewer, jContext));
		mm.add(new ExportAsXlsxAction(rptviewer, jContext));
		mm.add(new ExportAsExcelAPIAction(rptviewer, jContext));

		mm.add(new ExportAsCsvAction(rptviewer, jContext));
		mm.add(new ExportAsCsvMetadataAction(rptviewer, jContext));

		mm.add(new Separator());
		mm.add(new ExportAsXmlAction(rptviewer, jContext));
		mm.add(new ExportAsXmlWithImagesAction(rptviewer, jContext));
		// exportMenu.setDefaultAction(pdfAction);

		return exportMenu;
	}
}
