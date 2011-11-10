package com.jaspersoft.studio.editor.preview.view.report;

import org.eclipse.jface.action.IAction;
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
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

public class ExportMenu {
	public static ExportMenuAction getExportMenu(IReportViewer rptviewer, PropertiesHelper ph) {
		ExportMenuAction exportMenu = new ExportMenuAction(rptviewer);
		IAction pdfAction = null;
		MenuManager mm = exportMenu.getMenuManager();
		mm.add(new ExportAsJasperReportsAction(rptviewer, ph));
		mm.add(new Separator());

		mm.add(pdfAction = new ExportAsPdfAction(rptviewer, ph));
		mm.add(new ExportAsHtmlAction(rptviewer, ph));
		mm.add(new ExportAsXHtmlAction(rptviewer, ph));
		mm.add(new Separator());

		mm.add(new ExportAsRtfAction(rptviewer, ph));
		mm.add(new ExportAsDocxAction(rptviewer, ph));
		mm.add(new ExportAsOdtAction(rptviewer, ph));
		mm.add(new ExportAsOdsAction(rptviewer, ph));
		mm.add(new ExportAsPptxAction(rptviewer, ph));
		mm.add(new ExportAsTextAction(rptviewer, ph));

		mm.add(new Separator());
		mm.add(new ExportAsXlsAction(rptviewer, ph));
		mm.add(new ExportAsXlsxAction(rptviewer, ph));
		mm.add(new ExportAsExcelAPIAction(rptviewer, ph));

		mm.add(new ExportAsCsvAction(rptviewer, ph));
		mm.add(new ExportAsCsvMetadataAction(rptviewer, ph));

		mm.add(new Separator());
		mm.add(new ExportAsXmlAction(rptviewer, ph));
		mm.add(new ExportAsXmlWithImagesAction(rptviewer, ph));
		exportMenu.setDefaultAction(pdfAction);

		return exportMenu;
	}
}
