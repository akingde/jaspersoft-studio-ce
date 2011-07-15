package com.jaspersoft.studio.editor.outline.part;

import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;

import com.jaspersoft.studio.editor.outline.editpolicy.JDContainerEditPolicy;
import com.jaspersoft.studio.editor.outline.editpolicy.JDTreeContainerEditPolicy;

public class NotDragableContainerTreeEditPart extends NotDragableTreeEditPart {
	/**
	 * Creates and installs pertinent EditPolicies.
	 */
	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.CONTAINER_ROLE, new JDContainerEditPolicy());
		installEditPolicy(EditPolicy.TREE_CONTAINER_ROLE, new JDTreeContainerEditPolicy());
		// If this editpart is the contents of the viewer, then it is not deletable!
		if (getParent() instanceof RootEditPart)
			installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());
	}
}
