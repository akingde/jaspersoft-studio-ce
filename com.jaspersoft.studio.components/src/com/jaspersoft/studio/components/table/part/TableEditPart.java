/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.table.part;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPolicy;

import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.editor.gef.parts.EditableFigureEditPart;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.FigurePageLayoutEditPolicy;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.FigureSelectionEditPolicy;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MPage;

public class TableEditPart extends EditableFigureEditPart {
		
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

}
