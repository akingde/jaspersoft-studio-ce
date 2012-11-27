package com.jaspersoft.studio.editor.toolitems;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

public interface ISelectionContributionItem {
	public void setSelection(ISelection selection);

	public void setWorkbenchPart(IWorkbenchPart workbenchPart);
}
