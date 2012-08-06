/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.gef.decorator.error;

import java.util.List;

import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.design.JRDesignElement;

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
import com.jaspersoft.studio.model.MGraphicElement;

public class PositionErrorDecorator implements IElementDecorator {

	private IDecorator decorator = null;

	@Override
	public void setupFigure(ComponentFigure fig, FigureEditPart editPart) {
		
		//It will remove the type ErrorDecorator from the list because equals is override in ErrorDecorator
		fig.removeDecorator(decorator);
		// check if we should show this decorator
		if ((Boolean)editPart.getViewer().getProperty(ShowErrorsAction.ID)) {
			if (decorator == null){
				decorator = new ErrorDecorator();
			}
			if (!isValidPosition(fig, (MGraphicElement)editPart.getModel())){
				fig.addDecorator(decorator);
			}		
		}
	}
	
	/**
	 * Evaluate if an element is between the bounds of the father
	 * @param fig The moved graphical element.
	 * @param itemModel the model associated to the moved item
	 * @return true if the element position is valid (so no warning should be shown), false otherwise
	 */
	private boolean isValidPosition(ComponentFigure fig, MGraphicElement itemModel)
  {
      if (itemModel.getParent() instanceof APropertyNode){
      	APropertyNode fatherModel = (APropertyNode) itemModel.getParent();
	      Integer height = (Integer)itemModel.getPropertyValue(JRDesignElement.PROPERTY_HEIGHT);
	      Integer width = (Integer)itemModel.getPropertyValue(JRDesignElement.PROPERTY_WIDTH);
	      Integer x = (Integer)itemModel.getPropertyValue(JRDesignElement.PROPERTY_X);
	      Integer y = (Integer)itemModel.getPropertyValue(JRDesignElement.PROPERTY_Y);
	      Integer father_height = (Integer)fatherModel.getPropertyValue(JRDesignElement.PROPERTY_HEIGHT);	      
	      if (fig.getJrElement().getElementGroup() instanceof JRBand)
        {
	      		//Integer father_width = itemModel.getRoot().getJasperDesign().getColumnWidth();
            return y + height<=father_height;
        } else if (fig.getJrElement().getElementGroup() != null){
        	Integer father_width = (Integer)fatherModel.getPropertyValue(JRDesignElement.PROPERTY_WIDTH);
			    return father_height >= height + y &&
			           x >= 0 &&
			           y >= 0 &&
			           father_width >= x + width;
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

}
