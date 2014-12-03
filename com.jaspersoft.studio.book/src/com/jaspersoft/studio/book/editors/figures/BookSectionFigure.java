/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.book.editors.figures;

import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.book.editparts.BookPagesEditPart;
import com.jaspersoft.studio.book.editparts.BookSectionEditPart;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;

/**
 * Figure where that will contains all the pages of a section
 * 
 * 
 * @author Orlandin Marco
 *
 */
public class BookSectionFigure extends RectangleFigure {

	/**
	 * Font size of the title text
	 */
	private static final int TITLE_FONT_HEIGHT = 14;
	
	/**
	 * Width of the horizontal line separator
	 */
	private static final int HORIZONTAL_LINE_WIDTH = 1;

	/**
	 * The height offset of the horizontal line separator
	 */
	public static int HORIZONTAL_LINE_OFFSET = 27;
	
	/**
	 * The parent part
	 */
	private BookSectionEditPart parentPart;
	
	/**
	 * Flag to know if the figure should drawn the drag and drop feedback
	 */
	private boolean drawFeedback = false;
	
	/**
	 * Part after which the feedback must be drawn, null if it is before
	 * the first element. This is used only when drawFeedback is true
	 */
	private EditPart afterPart = null;
	
	public BookSectionFigure(BookSectionEditPart parentPart){
		this.parentPart = parentPart;
		setLayoutManager(new FlowLayout());
	}
	
	/**
	 * Calculate the height of the figure depending on its content
	 */
	@Override
	public Dimension getPreferredSize(int wHint, int hHint) {
		Rectangle currentBounds = getBounds();
		int preferredHeight = BookPagesFigure.PREFERRED_HEIGHT;
		//FALLBACK: if the parent figure has width 0 because it was still not
		//calculated use it's default width
		int pageWidth = BookReportFigure.FIGURE_WIDTH;
		if (currentBounds.width > 0) pageWidth = currentBounds.width;
		int numberForLine = pageWidth / (BookPagesFigure.PREFERRED_WIDTH + 5);
		int numberOfLines = (parentPart.getChildren().size() / numberForLine);
		if (parentPart.getChildren().size() % numberForLine >0) numberOfLines++;
		if (numberOfLines == 0 ) numberOfLines++;
		preferredHeight = (preferredHeight+4) * numberOfLines;
		return new Dimension(-1, preferredHeight+HORIZONTAL_LINE_OFFSET+16);
	}
	
	@Override
	protected void fillShape(Graphics graphics) {
		// Backup the graphic properties.
		int oldLineWidth = graphics.getLineWidth();
		Color oldForeColor = graphics.getForegroundColor();
		Color oldBackColor = graphics.getBackgroundColor();
		Font  oldFont = graphics.getFont();
		
		
		graphics.setForegroundColor(ResourceManager.getColor(189, 189, 189));
		Rectangle figureBounds = getBounds();

		
		// Set the proper font size.
		// The proper font size may actually behave differently system by
		// system. We want here a font with a real height of more or less
		// 14 pixels.
		Font titleFont = ResourceManager.getFont(oldFont.getFontData()[0].getName(), TITLE_FONT_HEIGHT, SWT.None);
		graphics.setFont(titleFont);
		
		// The name of the section is taken by the model. We may decide to write
		// very custom section titles, based on properties and other information
		ANode model = (APropertyNode)parentPart.getModel();
		graphics.drawText(model.getDisplayText(), figureBounds.x+10, figureBounds.y+1);

		// The left line to highlight the section.
		// For now the color is always set to gray, but we can use the color
		// to highlight special sections like details.
		graphics.setLineWidth(5);
		graphics.drawLine(figureBounds.x, figureBounds.y, figureBounds.x, figureBounds.y + figureBounds.height -10);
		
		// Draw the top line just under the section title.
		// The line is drawn just after the font height
		// We hardcode here the vertical space we use for the y coordinate, but this number should be
		// caculated dynamically in case the font changes system by system.
		
		HORIZONTAL_LINE_OFFSET = graphics.getFontMetrics().getHeight() + 2;
		graphics.setLineWidth(HORIZONTAL_LINE_WIDTH);
		graphics.drawLine(figureBounds.x+10, figureBounds.y + HORIZONTAL_LINE_OFFSET, figureBounds.x+figureBounds.width, figureBounds.y + HORIZONTAL_LINE_OFFSET);
		
		// Restore graphics properties
		graphics.setLineWidth(oldLineWidth);
		graphics.setForegroundColor(oldForeColor);
		graphics.setBackgroundColor(oldBackColor);
		graphics.setFont(oldFont);
	}
	
	@Override
	protected void paintClientArea(Graphics graphics) {
		if (getChildren().isEmpty())
			return;
		paintChildren(graphics);
		paintDropFeedBack(graphics);
	}

	protected void paintDropFeedBack(Graphics graphics){
		if (drawFeedback){
			graphics.setLineWidth(2);
			graphics.setForegroundColor(ResourceManager.getColor(0,0,0));
			if (afterPart == null){
				if (getChildren().size()>0){
					IFigure childFigure = (IFigure)getChildren().get(0);
					Rectangle figureBound = childFigure.getBounds();
					graphics.drawLine(figureBound.x, figureBound.y, figureBound.x, figureBound.y+figureBound.height);
				} 
			} else {
				Rectangle figureBound = ((BookPagesEditPart)afterPart).getFigure().getBounds();
				int x = figureBound.x + figureBound.width-10;
				graphics.drawLine(x, figureBound.y, x, figureBound.y+figureBound.height);
			}
		}
	}
	
	@Override
	protected void outlineShape(Graphics graphics) {
	}
	
	/**
	 * Remove an eventual draw drop feedback
	 */
	public void clearFeedback(){
		drawFeedback = false;
	}
	
	/**
	 * Set to draw the drop feedback
	 * 
	 * @param afterPart after which of the children part the 
	 * feedback should be drawn or null if it should be drawn
	 * before the first part
	 */
	public void drawFeedback(EditPart afterPart){
		this.afterPart = afterPart;
		drawFeedback = true;
	}
	
	/**
	 * Check if this figure has a drop feedback drawn
	 * 
	 * @return true if it has a drop feedback, false otherwise
	 */
	public boolean hasFeedback(){
		return drawFeedback;
	}
	
	/**
	 * Return after which of the children part the drop feedback will 
	 * be paint if the drawFeedback is true
	 * 
	 * @return after which of the children part the 
	 * feedback should be drawn or null if it should be drawn
	 * before the first part
	 */
	public EditPart afterPart(){
		return afterPart;
	}
}
