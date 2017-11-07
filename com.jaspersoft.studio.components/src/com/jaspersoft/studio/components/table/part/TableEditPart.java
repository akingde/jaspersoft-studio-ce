/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.part;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;

import com.jaspersoft.studio.compatibility.ToolUtilitiesCompatibility;
import com.jaspersoft.studio.components.SubEditorEditPartTracker;
import com.jaspersoft.studio.components.SubeditorResizableEditPolicy;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.part.editpolicy.JSSCompoundTableCommand;
import com.jaspersoft.studio.editor.gef.parts.EditableFigureEditPart;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.FigurePageLayoutEditPolicy;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.FigureSelectionEditPolicy;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.SearchParentDragTracker;
import com.jaspersoft.studio.editor.outline.editpolicy.CloseSubeditorDeletePolicy;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.MPage;

public class TableEditPart extends EditableFigureEditPart implements IContainer {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new CloseSubeditorDeletePolicy());
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new FigureSelectionEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new FigurePageLayoutEditPolicy());
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new SubeditorResizableEditPolicy(){
			@Override
			protected Command getResizeCommand(ChangeBoundsRequest request) {
				//If the operation is a resize of the table store the command into the a JSSTableCommand to have
				//the support to the fill space if it is enabled
				Command resizeCommand = super.getResizeCommand(request);
				if (resizeCommand != null){
					JSSCompoundTableCommand compoundCommand = new JSSCompoundTableCommand((MTable)getHost().getModel());
					compoundCommand.add(resizeCommand);
					return compoundCommand;
				}
				return null;
			}
		});
	}
	
	@Override
	protected void setupFigure(IFigure rect) {
		super.setupFigure(rect);
		ANode md = getModel();
		if (((ANode) md).getParent() instanceof MPage) {
			MTable m = (MTable) md;
			Dimension d = m.getTableManager().getSize();
			Dimension dr = rect.getSize();
			rect.setSize(Math.max(dr.width, d.width) + 4,
					Math.max(dr.height, d.height) + 4);
		}
	}

	@Override
	public DragTracker getDragTracker(Request request) {
		if (ToolUtilitiesCompatibility.isSubeditorMainElement(this)) return new SubEditorEditPartTracker(this);
		else return new SearchParentDragTracker(this);
	}
	
	@Override
	public EditPart getDropContainer() {
		if (getModel().getParent() instanceof MPage) {
			return this;
		} else {
			return getParentEditPart(this);
		}
	}
}
