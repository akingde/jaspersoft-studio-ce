package com.jaspersoft.studio.editor.gef.parts.editPolicy;

import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;

import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.editor.gef.parts.IRulerUpdatable;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainer;

public class FigureSelectionEditPolicy extends SelectionEditPolicy {
	@Override
	protected void showSelection() {
		EditPart host = getHost();
		if (host instanceof IRulerUpdatable)
			((IRulerUpdatable) host).updateRulers();
		ANode n = (ANode) getHost().getModel();
		List<EditPart> eparts = getHost().getParent().getChildren();
		int mindepth = Integer.MAX_VALUE;
		EditPart eparent = null;
		for (EditPart ep : eparts) {
			if (ep instanceof IContainer) {
				ANode cn = (ANode) ep.getModel();
				int depth = n.findParent(cn);
				if (depth != -1) {
					if (mindepth > depth) {
						mindepth = depth;
						eparent = ep;
					}
				}
			}
		}
	}

	@Override
	protected void hideSelection() {
	}

	@Override
	public void showTargetFeedback(Request request) {
		EditPart host = getHost();
		if (host instanceof FigureEditPart && host.getSelected() == EditPart.SELECTED_NONE)
			((FigureEditPart) host).getFigure().setBorder(new LineBorder(ColorConstants.darkGreen, 2));
		super.showTargetFeedback(request);
	}

	@Override
	public void eraseTargetFeedback(Request request) {
		EditPart host = getHost();
		if (host instanceof FigureEditPart) {
			FigureEditPart feditpart = (FigureEditPart) host;
			feditpart.setPrefsBorder(feditpart.getFigure());
		}
		super.eraseTargetFeedback(request);
	}

}
