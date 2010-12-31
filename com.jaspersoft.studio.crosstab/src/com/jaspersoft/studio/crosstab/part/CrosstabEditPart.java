package com.jaspersoft.studio.crosstab.part;

import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.SnapFeedbackPolicy;

import com.jaspersoft.studio.crosstab.part.editpolicy.CrosstabLayoutEditPolicy;
import com.jaspersoft.studio.editor.gef.parts.EditableFigureEditPart;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.ElementEditPolicy;

public class CrosstabEditPart extends EditableFigureEditPart {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ElementEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new CrosstabLayoutEditPolicy());
		installEditPolicy("Snap Feedback", new SnapFeedbackPolicy()); //$NON-NLS-1$
	}

}
