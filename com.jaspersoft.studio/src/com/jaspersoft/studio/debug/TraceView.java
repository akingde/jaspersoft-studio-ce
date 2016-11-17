/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.debug;

import org.eclipse.core.resources.IResource;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.part.PageBookView;

import com.jaspersoft.studio.editor.JrxmlEditor;

public class TraceView extends PageBookView {
	public static final String ID = "com.jaspersoft.studio.debug.trace";
	private TracePage page;

	@Override
	protected IPage createDefaultPage(PageBook book) {
		TracePage page = new TracePage(null);
		initPage(page);
		page.createControl(book);
		return page;
	}

	@Override
	protected PageRec doCreatePage(final IWorkbenchPart part) {
		if (part instanceof JrxmlEditor) {
			page = new TracePage((JrxmlEditor) part);
			initPage(page);
			page.createControl(getPageBook());
			return new PageRec(part, page);
		}
		throw new RuntimeException("Unsupported WorkbenchPart: " + part.toString());

	}

	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class key) {
		if (key == IResource.class && getSite() != null) {
			IWorkbenchPage page = getSite().getPage();
			if (page != null && page.getActiveEditor() != null) {
				IEditorInput editorInput = page.getActiveEditor().getEditorInput();
				if (editorInput != null && editorInput instanceof IFileEditorInput)
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
		if (part instanceof JrxmlEditor)
			return true;
		return false;
	}

}
