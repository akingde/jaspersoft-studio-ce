/*******************************************************************************
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator.error;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.RetargetAction;

import com.jaspersoft.studio.editor.gef.decorator.IDecorator;
import com.jaspersoft.studio.editor.gef.decorator.IElementDecorator;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.IGraphicElement;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.band.MBand;

public class PositionErrorDecorator implements IElementDecorator {

	private IDecorator decorator = null;
	private List<String> actionIDs;

	@Override
	public void setupFigure(ComponentFigure fig, FigureEditPart editPart) {

		// It will remove the type ErrorDecorator from the list because equals is override in ErrorDecorator
		fig.removeDecorator(decorator);
		// check if we should show this decorator
		if ((Boolean) editPart.getViewer().getProperty(ShowErrorsAction.ID)) {
			if (decorator == null) {
				decorator = new ErrorDecorator();
			}
			if (!isValidPosition(fig, (MGraphicElement) editPart.getModel())) {
				fig.addDecorator(decorator);
			}
		}
	}

	/**
	 * Evaluate if an element is between the bounds of the father
	 * 
	 * @param fig
	 *          The moved graphical element.
	 * @param itemModel
	 *          the model associated to the moved item
	 * @return true if the element position is valid (so no warning should be shown), false otherwise
	 */
	private boolean isValidPosition(ComponentFigure fig, MGraphicElement itemModel) {
		if (itemModel.getParent() instanceof APropertyNode) {
			JRDesignElement item = itemModel.getValue();
			int x = item.getX();
			int y = item.getY();
			int w = item.getWidth();
			int h = item.getHeight();

			int fh = Integer.MAX_VALUE;
			int fw = Integer.MAX_VALUE;

			if (itemModel.getParent() instanceof MGraphicElement) {
				MGraphicElement fatherModel = (MGraphicElement) itemModel.getParent();
				JRDesignElement fitem = fatherModel.getValue();

				fh = fitem.getHeight();
				fw = fitem.getWidth();
			} else if (itemModel.getParent() instanceof MBand) {
				JRDesignBand band = ((MBand) itemModel.getParent()).getValue();
				fh = band.getHeight();
				fw = itemModel.getJasperDesign().getPageWidth();
			} else if (itemModel.getParent() instanceof IGraphicElement) {
				IGraphicElement ge = (IGraphicElement) itemModel.getParent();
				Rectangle r = ge.getBounds();
				if (r != null) {
					fh = r.height;
					fw = r.width;
				}
			}
			if (fig.getJrElement().getElementGroup() instanceof JRBand) {
				// Integer father_width = itemModel.getRoot().getJasperDesign().getColumnWidth();
				return y + h <= fh;
			} else if (fig.getJrElement().getElementGroup() != null) {
				return fh >= h + y && x >= 0 && y >= 0 && fw >= x + w;
			}
		}
		return true;
	}

	@Override
	public void registerActions(ActionRegistry registry, List<String> selectionActions, GraphicalViewer gviewer,
			IWorkbenchPart part) {

		gviewer.setProperty(ShowErrorsAction.ID, true);
		IAction action = new ShowErrorsAction(gviewer);
		registry.registerAction(action);
	}

	@Override
	public void buildContextMenu(ActionRegistry registry, EditPartViewer viewer, IMenuManager menu) {
	}

	@Override
	public RetargetAction[] buildMenuActions() {
		return new RetargetAction[] { new RetargetAction(ShowErrorsAction.ID, Messages.ShowErrorsAction_title,
				IAction.AS_CHECK_BOX) };
	}

	@Override
	public void contribute2Menu(ActionRegistry registry, MenuManager menuManager) {
		menuManager.add(registry.getAction(ShowErrorsAction.ID));
	}

	@Override
	public List<String> getActionIDs() {
		if (actionIDs == null) {
			actionIDs = new ArrayList<String>(1);
			actionIDs.add(ShowErrorsAction.ID);
		}
		return actionIDs;
	}

}
