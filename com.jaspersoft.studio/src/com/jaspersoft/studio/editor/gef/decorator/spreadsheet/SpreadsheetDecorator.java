/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator.spreadsheet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.editor.gef.decorator.IDecorator;
import com.jaspersoft.studio.editor.gef.decorator.text.TextDecoratorInterface;
import com.jaspersoft.studio.editor.gef.decorator.text.TextLocation;
import com.jaspersoft.studio.editor.gef.decorator.text.TextLocation.Location;
import com.jaspersoft.studio.editor.gef.figures.AHandleBoundsFigure;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.java2d.J2DUtils;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.editor.layout.spreadsheet.SpreadsheetLayout;
import com.jaspersoft.studio.model.MGraphicElement;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignElement;

/**
 * Draw the decorator for the spreadsheet layout when the properties is set
 * 
 * @author Orlandin Marco
 * 
 */
public class SpreadsheetDecorator implements IDecorator, TextDecoratorInterface {

	/**
	 * Left upper corner image
	 */
	private static ImageIcon startImageAwt = null;

	/**
	 * right lower corner image
	 */
	private static ImageIcon endImageAwt = null;

	/**
	 * Font of the text
	 */
	private static Font JSS_TEXT_FONT = new Font("SansSerif", 0, 10);

	/**
	 * Color of the text
	 */
	private static Color JSS_TEXT_COLOR = new Color(195, 47, 193);

	/**
	 * Constructor, load the images if the weren't loaded before
	 */
	public SpreadsheetDecorator() {
		if (startImageAwt == null || endImageAwt == null) {
			startImageAwt = new javax.swing.ImageIcon(SpreadsheetDecorator.class.getResource("/icons/resources/corner1.png"));
			endImageAwt = new javax.swing.ImageIcon(SpreadsheetDecorator.class.getResource("/icons/resources/corner2.png"));
		}
	}

	/**
	 * Print on the element it's selected pdf tags
	 */
	@Override
	public void paint(Graphics graphics, ComponentFigure fig) {
		if (fig.getJrElement() instanceof JRDesignElement) {

			Rectangle r = fig.getBounds();
			Graphics2D g = ComponentFigure.getG2D(graphics);
			if (g != null ) {
				
				Stroke oldStroke = g.getStroke();
				g.setStroke(J2DUtils.getInvertedZoomedStroke(oldStroke, graphics.getAbsoluteScale()));

				JRPropertiesMap v = fig.getJrElement().getPropertiesMap();
				if (v.containsProperty(SpreadsheetLayout.PROPERTY_ID) && fig instanceof AHandleBoundsFigure){
					MGraphicElement model = ((AHandleBoundsFigure)fig).getModel();
					JRPropertiesMap parentMap = LayoutManager.getPropertyMap(model.getParent());
					String ssName = SpreadsheetLayout.class.getName();	
					if (parentMap != null && ssName.equals(parentMap.getProperty(ILayout.KEY))) {
						drawEnd(g, r); 
			
						Font f = g.getFont();
	
						Color color = g.getColor();
	
						g.setFont(JSS_TEXT_FONT);
						g.setColor(JSS_TEXT_COLOR);
						int strWidth = g.getFontMetrics().stringWidth("COL");
						AttributedString as = new AttributedString("COL");
						as.addAttribute(TextAttribute.FONT, g.getFont());
						as.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
						g.drawString(as.getIterator(), r.x + r.width - strWidth - 6, r.y + r.height - 6);
						
						g.setFont(f);
						g.setColor(color);
					}
				}
			}
		}
	}

	/**
	 * Set the font of the displayed text
	 * 
	 * @param newFont
	 *          the new font
	 */
	public void setTextFont(Font newFont) {
		JSS_TEXT_FONT = newFont;
	}

	/**
	 * Set the color of the displayed text
	 * 
	 * @param newColor
	 *          the new color
	 */
	public void setTextColor(Color newColor) {
		JSS_TEXT_COLOR = newColor;
	}

	/**
	 * Draw the image on the right lower corner
	 * 
	 * @param gr
	 *          object used to draw the image
	 * @param r
	 *          item where the image will be drawn
	 */
	private void drawEnd(Graphics2D gr, Rectangle r) {
		gr.drawImage(endImageAwt.getImage(), r.x + r.width - endImageAwt.getIconWidth() - 2,
				r.y + r.height - endImageAwt.getIconHeight() - 2, null);
	}

	@Override
	public ArrayList<TextLocation> getText(ComponentFigure fig) {
		JRPropertiesMap mapProperties = fig.getJrElement().getPropertiesMap();
		ArrayList<TextLocation> result = new ArrayList<TextLocation>();

		if (mapProperties.containsProperty(SpreadsheetLayout.PROPERTY_ID) && fig instanceof AHandleBoundsFigure){
			MGraphicElement model = ((AHandleBoundsFigure)fig).getModel();
			JRPropertiesMap parentMap = LayoutManager.getPropertyMap(model.getParent());
			String ssName = SpreadsheetLayout.class.getName();	
			if (parentMap != null && ssName.equals(parentMap.getProperty(ILayout.KEY))) {
				String fullString = "COL";
				TextLocation as = new TextLocation(Location.BottomRight, fullString);
				as.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				result.add(as);
			}
		}

		
		return result;
	}

	@Override
	public Color getColor() {
		return JSS_TEXT_COLOR;
	}

	@Override
	public Font getFont() {
		return JSS_TEXT_FONT;
	}

}
