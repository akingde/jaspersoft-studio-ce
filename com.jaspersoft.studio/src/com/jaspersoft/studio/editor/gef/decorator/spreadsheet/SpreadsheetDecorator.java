/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator.spreadsheet;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.editor.gef.decorator.IDecorator;
import com.jaspersoft.studio.editor.gef.decorator.chainable.AbstractPainter;
import com.jaspersoft.studio.editor.gef.decorator.chainable.IDecoratorInterface;
import com.jaspersoft.studio.editor.gef.decorator.chainable.AbstractPainter.Location;
import com.jaspersoft.studio.editor.gef.decorator.image.ImageLocation;
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
public class SpreadsheetDecorator implements IDecorator, IDecoratorInterface {
	
	/**
	 * The definition of the image displayed when the element is binded
	 */
	private ImageLocation bindedImage = new ImageLocation(Location.BottomLeft, "icons/resources/bindedElementsDecorator.png");

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
						bindedImage.paint(g, r.x + 4, r.y + r.height - bindedImage.getElementSize(g).y);
					}
				}
			}
		}
	}

	@Override
	public ArrayList<AbstractPainter> getDecoratorPainter(ComponentFigure fig) {
		JRPropertiesMap mapProperties = fig.getJrElement().getPropertiesMap();
		ArrayList<AbstractPainter> result = new ArrayList<AbstractPainter>();

		if (mapProperties.containsProperty(SpreadsheetLayout.PROPERTY_ID) && fig instanceof AHandleBoundsFigure){
			MGraphicElement model = ((AHandleBoundsFigure)fig).getModel();
			JRPropertiesMap parentMap = LayoutManager.getPropertyMap(model.getParent());
			String ssName = SpreadsheetLayout.class.getName();	
			if (parentMap != null && ssName.equals(parentMap.getProperty(ILayout.KEY))) {
				result.add(bindedImage);
			}
		}

		
		return result;
	}
}
