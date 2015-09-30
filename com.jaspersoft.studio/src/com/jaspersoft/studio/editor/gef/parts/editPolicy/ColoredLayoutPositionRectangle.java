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
package com.jaspersoft.studio.editor.gef.parts.editPolicy;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRElement;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;

/**
 * Rectangle figure with colored border, used show a color feedback on
 * the target of the drop operation. If the parent of the drop has a layout
 * it show also a feedback about how the element will be layouted after the drop 
 * 
 * @author Orlandin Marco
 *
 */
public class ColoredLayoutPositionRectangle extends ColoredRectangle{
	
	/**
	 * The target of the drop operation
	 */
	private ANode container;
	
	/**
	 * The list of nodes that are dropped, should be a list of {@link MGraphicElement} or {@link JRElement}, 
	 * even mixed. Types different from this, excluding the subtypes, are ignored 
	 */
	private List<Object> nodes;
	
	/**
	 * Contains the last feedback calculated for the elements
	 */
	private Map<JRElement, Rectangle> positions = null;
	
	/**
	 * Cache list that contains all the element in nodes that can be converted to a JRElement (the value
	 * of the {@link MGraphicElement} is extracted in this case)
	 */
	private List<JRElement> elements = new ArrayList<JRElement>();
	
	/**
	 * Flag used to know if the last attempt to get the layout feedback position was
	 * Successful. If it was not this flag is set to false and every other attempt to get the feedback
	 * on the current parent for this drag operation will return null, skipping the computation
	 */
	private boolean hasPosition = true;
	
	/**
	 * Cache map of dummy elements that is possible to create from their ANode
	 */
	private static HashMap<Class<?>, JRElement> newElementsCache = new HashMap<Class<?>, JRElement>();
	
	/**
	 * Crate the feedback provider for a drag and drop operation
	 * 
	 * @param borderColor the color used to highlight the border of the parent
	 * @param borderWidth the width of the highlight on the parent border
	 * @param container the container where the drop will be done
	 * @param nodes The list of nodes that are dropped, should be a list of {@link MGraphicElement} or {@link JRElement}, 
	 * even mixed. Types different from this, excluding the subtypes, are ignored 
	 */
	public ColoredLayoutPositionRectangle(Color borderColor, float borderWidth, ANode container, List<Object> nodes){
		super(borderColor, borderWidth);
		this.container = container;
		this.nodes = nodes;
	}

	/**
	 * Return a dummy element for a drag of an element from the palette. It is 
	 * created if not cached, otherwise it is returned from the cache
	 */
	private JRElement getEmptyElement(MGraphicElement node){
		JRElement value = newElementsCache.get(node.getClass());
		if(value == null){
			value = node.createJRElement(container.getJasperDesign());
			newElementsCache.put(node.getClass(), value);
		}
		return value;
	}
	
	/**
	 * Calculate the layout position for the parent, considering inside
	 * it also the dragged node
	 * 
	 * @return every dragged node plus every node inside the parent with its position after the  layout
	 */
	private Map<JRElement, Rectangle> getLayoutPosition(){
		//Use the cache
		if (!hasPosition) return null;
		if (positions != null) return positions;
		else {
			elements.clear();
			for(Object node : nodes){
				if (node instanceof MGraphicElement){
					MGraphicElement pNode = (MGraphicElement) node;
					if (pNode.getValue() != null && pNode.getValue() instanceof JRElement){
						elements.add((JRElement)pNode.getValue());
					} else {
						elements.add(getEmptyElement(pNode));
					}
				} else if (node instanceof JRElement){
					elements.add((JRElement)node);
				}
			}
			positions = LayoutManager.createLayoutPosition(container, elements);
			if (positions == null) {
				hasPosition = false;
			}
			return positions;
		}
	}
	
	protected void outlineShape(Graphics graphics) {
		super.outlineShape(graphics);
		
		Map<JRElement, Rectangle> positions = getLayoutPosition();
		if (positions != null){
			Graphics2D g = ComponentFigure.getG2D(graphics);
			Rectangle r = Rectangle.SINGLETON.setBounds(getBounds());
			for(Rectangle elementPosition : positions.values()){
				if (elementPosition != null){
					g.setStroke(new BasicStroke(borderWidth));
					g.setColor(new Color(159, 159, 159));
					int x1 = r.x + elementPosition.x+3;
					int y1 = r.y + elementPosition.y+3;
					g.drawRect(x1, y1, elementPosition.width-6, elementPosition.height-6);
				}
			}
		}
	}
}
