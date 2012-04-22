package com.jaspersoft.studio.editor.preview;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.part.PageBookView;

public class ReportStateView extends PageBookView {

	@Override
	protected IPage createDefaultPage(PageBook book) {
		ReportStatePage page = new ReportStatePage();
		initPage(page);
		page.createControl(book);
		return page;
	}

	@Override
	protected PageRec doCreatePage(IWorkbenchPart part) {
		ReportStatePage page = new ReportStatePage();
		initPage(page);
		page.createControl(getPageBook());
		return new PageRec(part, page);
	}

	@Override
	protected void doDestroyPage(IWorkbenchPart part, PageRec pageRecord) {
		pageRecord.page.dispose();
	}

	@Override
	protected IWorkbenchPart getBootstrapPart() {
		IWorkbenchPage page = getSite().getPage();
		if (page != null) {
			// check whether the active part is important to us
			IWorkbenchPart activePart = page.getActivePart();
			return isImportant(activePart) ? activePart : null;
		}
		return null;
	}

	@Override
	protected boolean isImportant(IWorkbenchPart part) {
		return part.getSite().getPluginId().startsWith("com.jaspersoft.studio");
	}

}
