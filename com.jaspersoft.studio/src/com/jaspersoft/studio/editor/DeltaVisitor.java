/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

public class DeltaVisitor implements IResourceDeltaVisitor {
	private EditorPart part;

	public DeltaVisitor(EditorPart part) {
		this.part = part;
	}

	public boolean visit(IResourceDelta delta) {
		IEditorInput editorInput = part.getEditorInput();
		IFile file = null;
		if(editorInput instanceof IFileEditorInput){
			file = ((IFileEditorInput) editorInput).getFile();
		}
		if (delta == null || delta.getResource() == null || part == null || editorInput == null	|| file == null)
			return true;
		if (!delta.getResource().equals(file)) {
			return true;
		}
		switch (delta.getKind()) {
		case IResourceDelta.ADDED:
			break;
		case IResourceDelta.REMOVED:
			if ((IResourceDelta.MOVED_TO & delta.getFlags()) == 0) {
				// file removed
				UIUtils.getDisplay().asyncExec(new Runnable() {
					public void run() {
						part.getSite().getPage().closeEditor(part, false);
					}
				});
			} else
				changeInput(delta.getMovedToPath());
			break;
		case IResourceDelta.CHANGED:
			if ((delta.getFlags() & IResourceDelta.CONTENT) != 0 || (delta.getFlags() & IResourceDelta.SYNC) != 0
					|| (delta.getFlags() & IResourceDelta.REPLACED) != 0)
				changeInput(delta.getFullPath());
			break;
		}
		return true;
	}

	private void changeInput(IPath toPath) {
		final IFile newFile = ResourcesPlugin.getWorkspace().getRoot().getFile(toPath);
		UIUtils.getDisplay().syncExec(new Runnable() {
			public void run() {
				try {
					ISelectionProvider selectionProvider = part.getSite().getSelectionProvider();
					part.init(part.getEditorSite(), new FileEditorInput(newFile));
					if (selectionProvider != null)
						part.getSite().setSelectionProvider(selectionProvider);
				} catch (PartInitException e) {
					UIUtils.showError(e);
				}
			}
		});
	}
}
