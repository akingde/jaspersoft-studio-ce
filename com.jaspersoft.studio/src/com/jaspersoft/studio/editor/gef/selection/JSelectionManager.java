package com.jaspersoft.studio.editor.gef.selection;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.SelectionManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

public class JSelectionManager extends SelectionManager {
	
	
	/**
	 * Sets the selection.
	 * 
	 * @param newSelection
	 *            the new selection
	 * @since 3.2
	 */
	public void setSelection(ISelection newSelection) {
		if (!(newSelection instanceof IStructuredSelection))
			return;
		List orderedSelection = ((IStructuredSelection) newSelection).toList();
		EditPart focusedEditPart = null;
		if (!orderedSelection.isEmpty()) {
			Iterator itr = orderedSelection.iterator();
			EditPart part = (EditPart) itr.next();
			focusedEditPart = part.getViewer().getFocusEditPart();
			super.setSelection(newSelection);
			itr = orderedSelection.iterator();
			boolean foundPrimary = false;
			while (itr.hasNext()) {
				part = (EditPart) itr.next();
				if (part == focusedEditPart){
					part.setSelected(EditPart.SELECTED_PRIMARY);
					foundPrimary = true;
				} else {
					part.setSelected(EditPart.SELECTED);
				}
			}
			//If the primary element is not in the selection, the last on will be selected (standard behavior)
			if (!foundPrimary){
				part.setSelected(EditPart.SELECTED_PRIMARY);
			}
		}
	}
}
