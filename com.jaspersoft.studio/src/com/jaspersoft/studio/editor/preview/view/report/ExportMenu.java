/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.preview.view.report;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;

import com.jaspersoft.studio.editor.preview.actions.export.ExportAsCsvAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsCsvMetadataAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsDocxAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsExcelAPIAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsHtmlAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsJasperReportsAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsOdsAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsOdtAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsPdfAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsPptxAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsRtfAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsTextAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsXHtmlAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsXlsAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsXlsxAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsXmlAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsXmlWithImagesAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportMenuAction;
import com.jaspersoft.studio.editor.preview.view.report.swt.IReportViewer;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ExportMenu {
	public static ExportMenuAction getExportMenu(IReportViewer rptviewer, JasperReportsConfiguration jContext) {
		ExportMenuAction exportMenu = new ExportMenuAction(rptviewer);

		MenuManager mm = exportMenu.getMenuManager();
		mm.add(new ExportAsJasperReportsAction(rptviewer, jContext));
		mm.add(new Separator());

		mm.add(new ExportAsPdfAction(rptviewer, jContext));
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
