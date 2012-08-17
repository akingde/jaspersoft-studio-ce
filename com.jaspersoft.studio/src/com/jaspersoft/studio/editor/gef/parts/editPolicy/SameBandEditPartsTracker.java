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
package com.jaspersoft.studio.editor.gef.parts.editPolicy;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.tools.MarqueeDragTracker;
import org.eclipse.swt.events.MouseEvent;

import com.jaspersoft.studio.editor.gef.parts.band.BandEditPart;

/**
 * This class override the original MarqueeDragTracker to add the drag selection without
 * selecting the marquee tool
 * @author Orlandin Marco
 *
 */
public class SameBandEditPartsTracker extends MarqueeDragTracker{
	public SameBandEditPartsTracker() {
		super();
	}
	
	/**
	 * When a click is done in a band only that band will be selected and all others
	 * elements deselected, when is done out of the work area all the elements will be deselected
	 */
	@Override
	public void mouseUp(MouseEvent me, EditPartViewer viewer) {
		
		boolean wasDragging = movedPastThreshold();
		if (me.button == 1  &&  !wasDragging) {
			EditPart clickedPart = viewer.findObjectAt(new Point(me.x, me.y));
			if (clickedPart instanceof BandEditPart){
				viewer.select(clickedPart);
			} else viewer.deselectAll();
		}
		else super.mouseUp(me, viewer);
	};


}
