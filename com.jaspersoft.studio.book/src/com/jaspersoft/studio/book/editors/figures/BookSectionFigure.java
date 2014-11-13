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

public class BookSectionFigure extends RectangleFigure {

	private static final int TITLE_FONT_HEIGHT = 14;
	
	private static final int HORIZONTAL_LINE_WIDTH = 1;

	public static int HORIZONTAL_LINE_OFFSET = 27;
	
	private BookSectionEditPart parentPart;
	
	private boolean drawFeedback = false;
	
	private EditPart afterPart = null;
	
	public BookSectionFigure(BookSectionEditPart parentPart){
		this.parentPart = parentPart;
		setLayoutManager(new FlowLayout());
	}
	
	@Override
	public Dimension getPreferredSize(int wHint, int hHint) {
		Rectangle currentBounds = getBounds();
		int preferredHeight = BookPagesFigure.PREFERRED_HEIGHT;
		if (currentBounds.width > 0){
			int numberForLine = currentBounds.width / (BookPagesFigure.PREFERRED_WIDTH + 5);
			int numberOfLines = (parentPart.getChildren().size() / numberForLine);
			if (parentPart.getChildren().size() % numberForLine >0) numberOfLines++;
			if (numberOfLines == 0 ) numberOfLines++;
			preferredHeight = (preferredHeight+4) * numberOfLines;
		}
		return new Dimension(-1, preferredHeight+HORIZONTAL_LINE_OFFSET+16);
	}
	
	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);

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
	
	public void clearFeedback(){
		drawFeedback = false;
	}
	
	public void drawFeedback(EditPart afterPart){
		this.afterPart = afterPart;
		drawFeedback = true;
	}
	
	public boolean hasFeedback(){
		return drawFeedback;
	}
	
	public EditPart afterPart(){
		return afterPart;
	}
}
