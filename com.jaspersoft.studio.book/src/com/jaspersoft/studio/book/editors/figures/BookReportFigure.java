/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.editors.figures;

import org.eclipse.draw2d.FreeformLayeredPane;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

/**
 * Figure of the page where all the section are placed
 * 
 * @author Orlandin Marco
 *
 */
public class BookReportFigure extends FreeformLayeredPane {
	
	/**
	 * Default width of the page
	 */
	public static final int FIGURE_WIDTH = 300;
	
	public BookReportFigure(){
		GridLayout layout = new GridLayout(1, false);
		setLayoutManager(layout);
		
		// The calculation of the size of the book figure should be 
		// updated by the containing figures (or model changes).
		// by default let's use a 3 sections x 2 documents each to start...
		setBounds(new Rectangle(5, 5, FIGURE_WIDTH, 700));
	}
	
	@Override
	public void add(IFigure figure, Object constraint, int index) {
		GridData dataConstraint = new GridData(SWT.FILL,SWT.FILL,true,false);
		super.add(figure, dataConstraint, index);
	}
	
	protected void paintChildren(Graphics graphics) {
		for (int i = 0; i < getChildren().size(); i++) {
			IFigure child = (IFigure) getChildren().get(i);
			if (child.isVisible()) {
				child.paint(graphics);
			}
		}
	}
	
	@Override
	public boolean containsPoint(int x, int y) {
		return getBounds().contains(x, y);
	}
}
