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

/**
 * Class that extend the default SelectionManager to change the behavior when a selection of more elements 
 * is done
 * @author Orlandin Marco
 *
 */
public class JSelectionManager extends SelectionManager {
	
	
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
