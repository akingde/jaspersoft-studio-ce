/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.editors;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.jaspersoft.studio.book.model.BookFactory;
import com.jaspersoft.studio.editor.AbstractJRXMLEditor;
import com.jaspersoft.studio.editor.report.CachedSelectionProvider;
import com.jaspersoft.studio.editor.report.CommonSelectionCacheProvider;
import com.jaspersoft.studio.model.INode;

public class JRBookEditor extends AbstractJRXMLEditor implements CachedSelectionProvider {
	
	public static final String BOOK_EDITOR_ID = "com.jaspersoft.studio.book.editors.JRBookEditor";
	
	private JRBookDesignEditor designEditor;

	@Override
	protected void createDesignEditorPage() throws PartInitException {
		designEditor = new JRBookDesignEditor(jrContext);
		int index = addPage(designEditor,getEditorInput());
		setPageText(index, "Design");
	}

	@Override
	protected String getEditorHelpID() {
		return "com.jaspersoft.studio.doc.editor_book";
	}

	@Override
	protected boolean isDesignerDirty() {
		return designEditor.isDirty();
	}

	@Override
	protected ISelection getDesignerPageSelection() {
		return designEditor.getGraphicalViewer().getSelection();
	}

	@Override
	protected void setDesignerPageSelection(ISelection newSelection) {
		designEditor.getGraphicalViewer().setSelection(newSelection);
	}

	@Override
	public void updateVisualView() {
		if(designEditor!=null){
			designEditor.setModel(getModel());
		}
	}

	@Override
	protected EditorPart getDesignEditor() {
		return designEditor;
	}
	
	@Override
	public void init(IEditorSite site, IEditorInput editorInput)
			throws PartInitException {
		super.init(site, editorInput);
	}

	@Override
	protected INode createEditorModel() {
		return BookFactory.createReport(jrContext);
	}

	@Override
	public CommonSelectionCacheProvider getSelectionCache() {
		if (designEditor != null) return designEditor.getSelectionCache();
		return null;
	}

	
}
