package com.jaspersoft.studio.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;

import com.jaspersoft.studio.editor.xml.XMLEditor;
import com.jaspersoft.studio.messages.Messages;

public class JRtxEditor extends MultiPageEditorPart {

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		setSite(site);
		setPartName(input.getName());
		setInput(input);
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createPages() {
		// createPage0();
		createPage1();
	}

	private XMLEditor xmlEditor;

	/**
	 * Creates page 0 of the multi-page editor, which contains a text editor.
	 */
	void createPage1() {
		try {
			xmlEditor = new XMLEditor();
			int index = addPage(xmlEditor, getEditorInput());
			setPageText(index, Messages.JrxmlEditor_source);
			xmlEditor.getDocumentProvider().getDocument(xmlEditor.getEditorInput())
					.addDocumentListener(new IDocumentListener() {

						public void documentChanged(DocumentEvent event) {
						}

						public void documentAboutToBeChanged(DocumentEvent event) {

						}
					});
		} catch (PartInitException e) {
			ErrorDialog.openError(Display.getCurrent().getActiveShell(),
					Messages.JrxmlEditor_error_creating_nested_text_editor, null, e.getStatus());
		}
	}
}
