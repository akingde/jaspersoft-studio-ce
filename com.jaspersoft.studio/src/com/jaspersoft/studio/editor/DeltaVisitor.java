package com.jaspersoft.studio.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;

import com.jaspersoft.studio.utils.UIUtils;

public class DeltaVisitor implements IResourceDeltaVisitor {
	private EditorPart part;

	public DeltaVisitor(EditorPart part) {
		this.part = part;
	}

	public boolean visit(IResourceDelta delta) {
		if (!delta.getResource().equals((((IFileEditorInput) part.getEditorInput()).getFile())))
			return true;
		switch (delta.getKind()) {
		case IResourceDelta.ADDED:
			break;
		case IResourceDelta.REMOVED:
			if ((IResourceDelta.MOVED_TO & delta.getFlags()) == 0) {
				// file removed
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						part.getSite().getPage().closeEditor(part, false);
					}
				});
			} else
				changeInput(delta.getMovedToPath());
			break;
		case IResourceDelta.CHANGED:
			changeInput(delta.getFullPath());
			break;
		}
		return true;
	}

	private void changeInput(IPath toPath) {
		final IFile newFile = ResourcesPlugin.getWorkspace().getRoot().getFile(toPath);
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				try {
					part.init(part.getEditorSite(), new FileEditorInput(newFile));
				} catch (PartInitException e) {
					UIUtils.showError(e);
				}
			}
		});
	}
}
