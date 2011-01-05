package com.jaspersoft.studio.editor.gef.parts;

import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.requests.ChangeBoundsRequest;

public interface IContainerPart {
	public EditPolicy getEditPolicy();

	// public Command createChangeConstraintCommand(EditPart child, Object constraint);

	public Object getConstraintFor(ChangeBoundsRequest request, GraphicalEditPart child);
}
