/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview;

import org.eclipse.core.resources.IResource;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.part.PageBookView;

import com.jaspersoft.studio.editor.AbstractJRXMLEditor;

public class ReportStateView extends PageBookView {
	public static final String ID = "com.jaspersoft.studio.editor.preview.reportstate";
	private ReportStatePage page;

	@Override
	protected IPage createDefaultPage(PageBook book) {
		ReportStatePage p = new ReportStatePage(null);
		initPage(p);
		p.createControl(book);
		return p;
	}

	@Override
	protected PageRec doCreatePage(final IWorkbenchPart part) {
		PreviewJRPrint preview = null;
		if (part instanceof PreviewJRPrint)
			preview = (PreviewJRPrint) part;
		else if (part instanceof AbstractJRXMLEditor) {
			Display.getDefault().asyncExec(() -> {
				PreviewJRPrint p = (PreviewJRPrint) ((AbstractJRXMLEditor) part)
						.getEditor(AbstractJRXMLEditor.PAGE_PREVIEW);
				if (p != null)
					page.setupConsole(p.getConsole());
			});

		} else
			throw new RuntimeException("Unsupported WorkbenchPart: " + part.toString());
		page = new ReportStatePage(preview != null ? preview.getConsole() : null);
		initPage(page);
		page.createControl(getPageBook());
		return new PageRec(part, page);
	}

	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class key) {
		if (key == IResource.class && getSite() != null) {
			IWorkbenchPage p = getSite().getPage();
			if (p != null && p.getActiveEditor() != null) {
				IEditorInput editorInput = p.getActiveEditor().getEditorInput();
				if (editorInput instanceof IFileEditorInput)
					return ((IFileEditorInput) editorInput).getFile();
			}
		}
		return super.getAdapter(key);
	}

	@Override
	protected void doDestroyPage(IWorkbenchPart part, PageRec pageRecord) {
		pageRecord.page.dispose();
	}

	@Override
	protected IWorkbenchPart getBootstrapPart() {
		IWorkbenchPage p = getSite().getPage();
		if (p != null) {
			// check whether the active part is important to us
			IWorkbenchPart activePart = p.getActivePart();
			return isImportant(activePart) ? activePart : null;
		}
		return null;
	}

	@Override
	protected boolean isImportant(IWorkbenchPart part) {
		return part instanceof AbstractJRXMLEditor || part instanceof PreviewJRPrint;
	}

}
