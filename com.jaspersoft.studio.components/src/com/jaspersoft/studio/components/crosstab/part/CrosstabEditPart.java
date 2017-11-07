/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.part;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;

import com.jaspersoft.studio.compatibility.ToolUtilitiesCompatibility;
import com.jaspersoft.studio.components.SubEditorEditPartTracker;
import com.jaspersoft.studio.components.SubeditorResizableEditPolicy;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.editor.gef.parts.EditableFigureEditPart;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.FigurePageLayoutEditPolicy;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.FigureSelectionEditPolicy;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.SearchParentDragTracker;
import com.jaspersoft.studio.editor.outline.editpolicy.CloseSubeditorDeletePolicy;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MPage;

public class CrosstabEditPart extends EditableFigureEditPart {

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
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new SubeditorResizableEditPolicy());
	}

	@Override
	protected void setupFigure(IFigure rect) {
		super.setupFigure(rect);
		if (((ANode) getModel()).getParent() instanceof MPage) {
			MCrosstab m = (MCrosstab) getModel();
			Dimension d = m.getCrosstabManager().getSize();
			Dimension dr = rect.getSize();
			rect.setSize(Math.max(dr.width, d.width) + 2,
					Math.max(dr.height, d.height) + 2);
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
