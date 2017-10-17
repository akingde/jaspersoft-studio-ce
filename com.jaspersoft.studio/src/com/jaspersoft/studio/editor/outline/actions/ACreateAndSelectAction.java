/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline.actions;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public abstract class ACreateAndSelectAction extends ACreateAction {

	public ACreateAndSelectAction(IWorkbenchPart part) {
		super(part);
	}

	protected JasperReportsConfiguration getJrConfig() {
		ISelection s = getSelection();
		if (s instanceof StructuredSelection) {
			Object obj = ((StructuredSelection) s).getFirstElement();
			if (obj instanceof EditPart) {
				EditPart editPart = (EditPart) obj;
				if (editPart.getModel() instanceof ANode)
					return ((ANode) editPart.getModel()).getJasperConfiguration();
			}
		}
		return null;
	}

	@Override
	public void run() {
		super.run();
		ISelection s = getSelection();
		if (s instanceof StructuredSelection) {
			Object obj = ((StructuredSelection) s).getFirstElement();
			if (obj instanceof EditPart) {
				EditPart editPart = (EditPart) obj;
				List<?> children = editPart.getChildren();
				if (children != null && !children.isEmpty()) {
					int last = children.size() - 1;
					StructuredSelection newselection = new StructuredSelection(children.get(last));
					setSelection(newselection);
					getWorkbenchPart().getSite().getSelectionProvider().setSelection(newselection);
				}
			}
		}
	}
}
