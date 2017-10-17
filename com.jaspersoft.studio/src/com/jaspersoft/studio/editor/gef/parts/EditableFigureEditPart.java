/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.parts;

import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.part.MultiPageEditorSite;

import com.jaspersoft.studio.editor.IJROBjectEditor;
import com.jaspersoft.studio.model.ANode;

public class EditableFigureEditPart extends FigureEditPart {
	
	@Override
	public void performRequest(Request req) {
		if (RequestConstants.REQ_OPEN.equals(req.getType())) {
			Object value = ((ANode) getModel()).getValue();
			IEditorPart editorPart = ((DefaultEditDomain) getViewer().getEditDomain()).getEditorPart();
			openEditor(value, editorPart, ((ANode) getModel()));
		}
		super.performRequest(req);
	}

	public static void openEditor(final Object val, IEditorPart editor, ANode node) {
		if (editor.getEditorSite() instanceof MultiPageEditorSite) {
			final MultiPageEditorPart mpep = ((MultiPageEditorSite) editor.getEditorSite()).getMultiPageEditor();
			if (mpep instanceof IJROBjectEditor)
				doOpenEditor(val, (IJROBjectEditor) mpep, node);
		} else {
			editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			if (editor instanceof IJROBjectEditor)
				doOpenEditor(val, (IJROBjectEditor) editor, node);
		}
	}

	public static void doOpenEditor(final Object val, final IJROBjectEditor editor, final ANode node) {
		Display.getCurrent().asyncExec(new Runnable() {
			public void run() {
				((IJROBjectEditor) editor).openEditor(val, node);
			}
		});
	}
}
