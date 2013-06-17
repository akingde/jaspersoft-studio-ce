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
package com.jaspersoft.studio.data.sql.ui.gef.layout;

import java.util.List;

import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.data.sql.ui.gef.parts.FromEditPart;

public class GraphLayoutManager extends AbstractLayout {
	private FromEditPart diagram;

	public GraphLayoutManager(FromEditPart diagram) {
		this.diagram = diagram;
	}

	protected Dimension calculatePreferredSize(IFigure container, int wHint, int hHint) {
		container.validate();
		List<?> children = container.getChildren();
		Rectangle result = new Rectangle().setLocation(container.getClientArea().getLocation());
		for (int i = 0; i < children.size(); i++)
			result.union(((IFigure) children.get(i)).getBounds());
		result.resize(container.getInsets().getWidth(), container.getInsets().getHeight());
		return result.getSize();
	}

	public void layout(IFigure container) {
		new DirectedGraphLayoutVisitor().layoutDiagram(diagram);
		diagram.setTableModelBounds();
	}
}
