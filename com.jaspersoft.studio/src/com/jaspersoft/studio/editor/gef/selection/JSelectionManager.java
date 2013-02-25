/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.selection;

import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.SelectionManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.editor.gef.parts.band.BandEditPart;

/**
 * Class that extend the default SelectionManager to change the behavior when a selection of more elements 
 * is done
 * @author Orlandin Marco
 *
 */
public class JSelectionManager extends SelectionManager {
	
	private static EditPart lastSelected = null;
	
	
	/**
	 * Sets the selection, override the original method to store and give the status of selected primary
	 * to the item that was primary before the setSelection. If the element isn't in the new selected items
	 * the default behavior will be used (the primary element will be the last on the list of the new selected 
	 * items).
	 * 
	 * @param newSelection
	 *            the new selection
	 * @since 3.2
	 */
	@Override
	public void setSelection(ISelection newSelection) {
		if (!(newSelection instanceof IStructuredSelection))
			return;
		List<?> orderedSelection = ((IStructuredSelection) newSelection).toList();
		EditPart focusedEditPart = null;
		if (!orderedSelection.isEmpty()) {
			Iterator<?> itr = orderedSelection.iterator();
			EditPart part = (EditPart) itr.next();
			if (part.getViewer() != null){
				focusedEditPart = part.getViewer().getFocusEditPart();
				//Search a focused element into the selection
				if (!(focusedEditPart instanceof BandEditPart) && (focusedEditPart instanceof FigureEditPart)){
					lastSelected = focusedEditPart;
				} 
				//If not found the last selected element will be choosed if it is in the selection
				if (lastSelected != null && !orderedSelection.contains(lastSelected)){
					lastSelected = null;
				} 
				super.setSelection(newSelection);
				//If even the last selected element wasn't in the selection the first element of the selection will be choosed
				if (lastSelected == null){
					lastSelected = (EditPart)orderedSelection.get(0);
				}
				
				itr = orderedSelection.iterator();
				while (itr.hasNext()) {
						part = (EditPart) itr.next();
						part.setSelected(EditPart.SELECTED);
				}
				lastSelected.setSelected(EditPart.SELECTED_PRIMARY);
			}
		}else {
			super.deselectAll();
		}
	}
}
