/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator.chainable;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.editor.gef.decorator.IDecorator;
import com.jaspersoft.studio.editor.gef.decorator.pdf.PDFDecorator;
import com.jaspersoft.studio.editor.gef.decorator.text.TextLocation;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.java2d.J2DUtils;

import net.sf.jasperreports.engine.design.JRDesignElement;

/**
 * This decorator is used to print one or more decorators into an element, one after another.
 * The decorator is given by contributor modules, so to print a decorator it is sufficient to 
 * add a new module. A contributor must implement the {@link IDecoratorInterface} interface.
 * @author Orlandin Marco
 *
 */
public class ChainableDecorator implements IDecorator {

	/**
	 * Left upper corner image
	 */
	private static ImageIcon startImageAwt = null;

	/**
	 * right lower corner image
	 */
	private static ImageIcon endImageAwt = null;

	/**
	 * List of text contributor
	 */
	private ArrayList<IDecoratorInterface> textDecorators;
	
	public ChainableDecorator(){
		textDecorators = new ArrayList<IDecoratorInterface>();
		if (startImageAwt == null || endImageAwt == null) {
			startImageAwt = new javax.swing.ImageIcon(PDFDecorator.class.getResource("/icons/resources/corner1.png"));
			endImageAwt = new javax.swing.ImageIcon(PDFDecorator.class.getResource("/icons/resources/corner2.png"));
		}
	}
	
	/**
	 * Add a new text contributor to the decorator
	 * @param newDecorator the new contributor
	 */
	public void addDecorator(IDecoratorInterface newDecorator){
		textDecorators.add(newDecorator);
	}
	
	/**
	 * Remove a previous added contributor to the decorator
	 * @param toRemove element to remove
	 */
	public void removeDecorator(IDecoratorInterface toRemove){
		textDecorators.remove(toRemove);
	}
	
	/**
	 * Check if a contributor is present
	 * @param element element to search
	 * @return true if the contributor is already present, false otherwise
	 */
	public boolean contains(IDecoratorInterface element){
		return textDecorators.contains(element);
	}

	@Override
	public void paint(Graphics graphics, ComponentFigure fig) {
		if (fig.getJrElement() instanceof JRDesignElement) {
			Rectangle r = fig.getBounds();
			Graphics2D g = ComponentFigure.getG2D(graphics);
			if (g != null) {
				HashMap<TextLocation.Location, Integer> textMap = new HashMap<TextLocation.Location, Integer>();
				Stroke oldStroke = g.getStroke();
				Color oldColor = g.getColor();
				Font oldFont = g.getFont();
				g.setStroke(J2DUtils.getInvertedZoomedStroke(oldStroke, graphics.getAbsoluteScale()));
				boolean leftUpperCorner = false;
				boolean rightLowerCorner = false;
				for (IDecoratorInterface decorator : textDecorators) {
					ArrayList<AbstractPainter> texts = decorator.getDecoratorPainter(fig);
					for (AbstractPainter text : texts) {
						Point elementSize = text.getElementSize(g);
						if (elementSize.x != 0 && elementSize.y != 0) {
							Integer strWidth;
							if (!textMap.containsKey(text.getLocation())) {
								textMap.put(text.getLocation(), 0);
								strWidth = 0;
							} else
								strWidth = textMap.get(text.getLocation());
							switch (text.getLocation()) {
							case TopLeft:
								text.paint(g, r.x + strWidth + 4, r.y + 11);
								strWidth += text.getElementSize(g).x;
								leftUpperCorner = true;
								break;
							case TopRight:
								strWidth += text.getElementSize(g).x;
								text.paint(g, r.x + r.width - strWidth - 6, r.y + 11);
								break;
							case BottomLeft:
								text.paint(g, r.x + strWidth + 4, r.y + r.height - text.getElementSize(g).y);
								strWidth += text.getElementSize(g).x;
								break;
							case BottomRight:
								strWidth += text.getElementSize(g).x;
								text.paint(g, r.x + r.width - strWidth - 6, r.y + r.height - text.getElementSize(g).y);
								rightLowerCorner = true;
								break;
							}
							//Put a space at the end of the string
							strWidth += g.getFontMetrics().stringWidth(" ")+1;
							textMap.put(text.getLocation(), strWidth);
						}
					}
				}
				if (leftUpperCorner)
					drawStart(g, r);
				if (rightLowerCorner)
					drawEnd(g, r);
				g.setStroke(oldStroke);
				g.setColor(oldColor);
				g.setFont(oldFont);
			}
		}
	}
	
	/**
	 * Draw the image on the right lower corner
	 * @param gr object used to draw the image
	 * @param r item where the image will be drawn
	 */
	private void drawEnd(Graphics2D gr, Rectangle r) {
		gr.drawImage(endImageAwt.getImage(), r.x + r.width - endImageAwt.getIconWidth() - 2,
				r.y + r.height - endImageAwt.getIconHeight() - 2, null);
	}

	/**
	 * Draw the image on the left upper corner
	 * @param gr object used to draw the image
	 * @param r item where the image will be drawn
	 */
	private void drawStart(Graphics2D gr, Rectangle r) {
		gr.drawImage(startImageAwt.getImage(), r.x, r.y, null);
	}

}
