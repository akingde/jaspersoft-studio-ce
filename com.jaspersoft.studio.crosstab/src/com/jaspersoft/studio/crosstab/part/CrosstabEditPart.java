package com.jaspersoft.studio.crosstab.part;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.SnapFeedbackPolicy;

import com.jaspersoft.studio.crosstab.model.MCrosstab;
import com.jaspersoft.studio.crosstab.part.editpolicy.CrosstabLayoutEditPolicy;
import com.jaspersoft.studio.editor.gef.parts.EditableFigureEditPart;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.ElementEditPolicy;
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
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ElementEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new CrosstabLayoutEditPolicy());
		installEditPolicy("Snap Feedback", new SnapFeedbackPolicy()); //$NON-NLS-1$
	}

	@Override
	protected void setupFigure(IFigure rect) {
		super.setupFigure(rect);
		if (((ANode) getModel()).getParent() instanceof MPage) {
			MCrosstab m = (MCrosstab) getModel();
			Dimension d = m.getCrosstabManager().getSize();
			Dimension dr = rect.getSize();
			rect.setSize(Math.max(dr.width, d.width) + 2, Math.max(dr.height, d.height) + 2);
		}
	}

}
