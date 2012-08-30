/*******************************************************************************
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License  as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero  General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
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
