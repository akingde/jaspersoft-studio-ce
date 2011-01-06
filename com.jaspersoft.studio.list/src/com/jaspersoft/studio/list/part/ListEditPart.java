package com.jaspersoft.studio.list.part;

import org.eclipse.gef.EditPolicy;

import com.jaspersoft.studio.editor.gef.parts.EditableFigureEditPart;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.ElementEditPolicy;

public class ListEditPart extends EditableFigureEditPart {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ElementEditPolicy());
	}

}
