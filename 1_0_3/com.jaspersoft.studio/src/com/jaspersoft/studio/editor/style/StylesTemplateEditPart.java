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

package com.jaspersoft.studio.editor.style;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;

import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.editor.style.editpolicy.StyleTemplateLayoutEditPolicy;

public class StylesTemplateEditPart extends FigureEditPart {

	@Override
	protected IFigure createFigure() {
		RectangleFigure f = new RectangleFigure() {
			@Override
			protected void outlineShape(Graphics graphics) {

			}
		};

		GridLayout lm = new GridLayout(2, true);
		lm.horizontalSpacing = 10;
		lm.verticalSpacing = 10;
		f.setLayoutManager(lm);
		f.setBounds(new Rectangle(10, 10, 600, 600));
		f.setBorder(null);
		return f;
	}

	@Override
	protected void setupFigure(IFigure rect) {
		super.setupFigure(rect);
		int size = getModelChildren() == null ? rect.getChildren().size() : getModelChildren().size();
		rect.setSize(750, (size / 2 + 1) * 150 + 20);
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new StyleTemplateLayoutEditPolicy());
	}

	@Override
	public boolean isSelectable() {
		return true;
	}

}
