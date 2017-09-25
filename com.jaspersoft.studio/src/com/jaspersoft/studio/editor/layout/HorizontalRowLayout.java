/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.layout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;

public class HorizontalRowLayout extends AbstractLayout {
	
	public Map<JRElement, Rectangle> layout(JasperDesign jd, JRElementGroup container, JRElement[] elements, Dimension c) {
		Map<JRElement, Rectangle> map = new HashMap<JRElement, Rectangle>();
		int x = 0;
		int y = 0;
		int w = (int) Math.floor((float) c.width / elements.length);
		int rest = c.width - w * elements.length;
		int h = c.height;
		for (JRElement el : elements) {
			JRDesignElement del = (JRDesignElement) el;
			map.put(el, new Rectangle(el.getX(), el.getY(), el.getWidth(), el.getHeight()));
			del.setX(x);
			del.setY(y);
			del.setWidth(w + rest);
			del.setHeight(h);
			// if last grab free pixels
			x += w + rest;
			if (rest > 0)
				rest = 0;
			LayoutManager.layout(jd, map, el);
		}
		return map;
	}

	@Override
	public String getName() {
		return Messages.HorizontalRowLayout_name;
	}

	@Override
	public String getToolTip() {
		return Messages.HorizontalRowLayout_toolTip;
	}

	@Override
	public String getIcon() {
		return "icons/layout-3.png"; //$NON-NLS-1$
	}
	
	@Override
	public boolean allowChildBoundChange(ANode resizedNode, Rectangle oldBounds, Rectangle newBounds) {
		return ModelUtils.safeEquals(oldBounds, newBounds);
	}

	@Override
	public Map<Object, Rectangle> getLayoutPosition(Object[] elements, int insertPosition, Dimension parentSize) {
		Map<Object, Rectangle> map = new HashMap<Object, Rectangle>();
		List<JRElement> jrElements = new ArrayList<JRElement>();
		List<JRField> jrFields = new ArrayList<JRField>();
		for (Object el : elements) {
			if (el instanceof JRElement){
				jrElements.add((JRElement)el);
			} else if (el instanceof JRField){
				jrFields.add((JRField)el);
			}
		
		}	
		if (jrFields.isEmpty()){
			int x = 0;
			int y = 0;
			int w = (int) Math.floor((float) parentSize.width / elements.length);
			int rest = parentSize.width - w * elements.length;
			int h = parentSize.height;
			for (Object rawEl : elements) {
				if (rawEl instanceof JRElement){
					JRElement el = (JRElement)rawEl;
					map.put(el, new Rectangle(x, y, w+rest, h));
					// if last grab free pixels
					x += w + rest;
					if (rest > 0)
						rest = 0;
				}
			}
		} else if (insertPosition >= 0){
			int x = 0;
			int h = parentSize.height;
			for(int i = 0; i < insertPosition; i++){
				Object el = elements[i];
				if (el instanceof JRElement){
					x += ((JRElement)el).getWidth();
				}
			}
			map.put(jrFields.get(0), new Rectangle(x-2, 0, 4, h));
			
		}
		return map;
	}
	
	@Override
	public int getInsertPosition(ANode container, Point dropPosition) {
		int index = 0;
		int offsetLeft = 0;
		if (container instanceof MBand){
			offsetLeft += container.getJasperDesign().getLeftMargin();
		}
		for(INode child : container.getChildren()){
			JRDesignElement jrChild = (JRDesignElement)child.getValue();
			if (jrChild.getY() < dropPosition.y && dropPosition.y < jrChild.getY() + jrChild.getHeight()){
				int x = jrChild.getX() + offsetLeft;
				int span1 = x - 5;
				int span2 = x + 5;
				if (dropPosition.x > span1 && dropPosition.x < span2){
					return index;
				}
				span1 += jrChild.getWidth();
				span2 += jrChild.getWidth();
				if (dropPosition.x > span1 && dropPosition.x < span2){
					if (index < container.getChildren().size()){
						return index + 1;
					} else {
						return -1;
					}
				}
			} 
			index ++;
		}
		return -1;
	}
}
