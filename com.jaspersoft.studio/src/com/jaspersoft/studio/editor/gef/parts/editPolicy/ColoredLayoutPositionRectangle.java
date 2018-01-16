/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.parts.editPolicy;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.ZoomManager;

import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.band.MBand;

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.design.JasperDesign;

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
	private Map<Object, Rectangle> positions = null;
	
	/**
	 * Cache list that contains all the element in nodes that can be converted to a JRElement (the value
	 * of the {@link MGraphicElement} is extracted in this case)
	 */
	private List<Object> elements = new ArrayList<Object>();
	
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
	 * The position where the new elements are inserted, -1 means at the end of the container
	 */
	private int insertPosition;
	
	/**
	 * The edit part where the figure is set, used to retrieve the viewer and the zoom level for scaling
	 */
	private EditPart part;
	
	/**
	 * Crate the feedback provider for a drag and drop operation
	 * 
	 * @param The edit part where the figure is set, used to retrieve the viewer and the zoom level for scaling
	 * @param borderColor the color used to highlight the border of the parent
	 * @param borderWidth the width of the highlight on the parent border
	 * @param container the container where the drop will be done
	 * @param nodes The list of nodes that are dropped, should be a list of {@link MGraphicElement} or {@link JRElement}, 
	 * even mixed. Types different from this, excluding the subtypes, are ignored 
	 */
	public ColoredLayoutPositionRectangle(EditPart part, Color borderColor, float borderWidth, ANode container, List<Object> nodes){
		this(part, borderColor, borderWidth, container, nodes, -1);
	}
	
	/**
	 * Crate the feedback provider for a drag and drop operation
	 * 
	 * @param The edit part where the figure is set, used to retrieve the viewer and the zoom level for scaling
	 * @param borderColor the color used to highlight the border of the parent
	 * @param borderWidth the width of the highlight on the parent border
	 * @param container the container where the drop will be done
	 * @param nodes The list of nodes that are dropped, should be a list of {@link ANode} or {@link JRElement}, 
	 * even mixed. Types different from this, excluding the subtypes, are ignored 
	 * @param insertPosition the position where the elements will be inserted, -1 means at the end of the container
	 */
	public ColoredLayoutPositionRectangle(EditPart part, Color borderColor, float borderWidth, ANode container, List<Object> nodes, int insertPosition){
		super(borderColor, borderWidth);
		this.container = container;
		this.nodes = nodes;
		this.insertPosition = insertPosition;
		this.part = part;
	}

	/**
	 * Return a dummy element for a drag of an element from the palette. It is 
	 * created if not cached, otherwise it is returned from the cache
	 */
	private JRElement getEmptyElement(MGraphicElement node){
		JRElement value = newElementsCache.get(node.getClass());
		if(value == null){
			value = node.createJRElement(container.getJasperDesign(), false);
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
	private Map<Object, Rectangle> getLayoutPosition(){
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
				} else if (node instanceof ANode){
					elements.add(((ANode) node).getValue());
				} else {
					elements.add(node);
				}
			}
			positions = LayoutManager.createLayoutPosition(container, insertPosition, elements);
			if (positions == null) {
				hasPosition = false;
			}
			return positions;
		}
	}
	
	protected void outlineShape(Graphics graphics) {
		super.outlineShape(graphics);
		ZoomManager zoomMgr = (ZoomManager) part.getViewer().getProperty(ZoomManager.class.toString());
		double zoom = 1.0d;
		if (zoomMgr != null) {
			zoom = zoomMgr.getZoom();
		}
		Map<Object, Rectangle> positions = getLayoutPosition();
		if (positions != null){
			Graphics2D g = ComponentFigure.getG2D(graphics);
			Rectangle r = Rectangle.SINGLETON.setBounds(getBounds());
			int offset = 0;
			//In the band the draw should start from the page margin, so we add an offset
			if (container instanceof MBand){
				JasperDesign jd = container.getJasperDesign();
				offset = (int)Math.round(jd.getLeftMargin()*zoom);
			}
			for(Rectangle elementPosition : positions.values()){
				if (elementPosition != null){
					g.setStroke(new BasicStroke(borderWidth));
					g.setColor(new Color(159, 159, 159));
					int x1 = (int)Math.round(elementPosition.x * zoom) + offset + r.x;
					int y1 = r.y + (int)Math.round(elementPosition.y * zoom);
					
					Rectangle rect = new Rectangle(x1, y1, (int)Math.round(elementPosition.width * zoom), (int)Math.round(elementPosition.height * zoom));
					g.drawRect( rect.x,  rect.y,  rect.width,  rect.height);
				}
			}
		}
	}
}
