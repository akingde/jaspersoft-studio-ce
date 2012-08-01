/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
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
		// EditPart eparent = null;
		for (EditPart ep : eparts) {
			if (ep instanceof IContainer) {
				ANode cn = (ANode) ep.getModel();
				int depth = n.findParent(cn);
				if (depth != -1) {
					if (mindepth > depth) {
						mindepth = depth;
						// eparent = ep;
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
