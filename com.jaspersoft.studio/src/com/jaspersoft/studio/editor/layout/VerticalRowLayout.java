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
import net.sf.jasperreports.engine.design.JRDesignBreak;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;

public class VerticalRowLayout extends AbstractLayout {
	
	public Map<JRElement, Rectangle> layout(JasperDesign jd, JRElementGroup container, JRElement[] elements, Dimension c) {
		Map<JRElement, Rectangle> map = new HashMap<JRElement, Rectangle>();
		int x = 0;
		int y = 0;
		int w = c.width;
		
		//The breaks can't grow in height so cosider this when layouting
		int breakNumbers = 0;
		for (JRElement el : elements) {
			if (el instanceof JRDesignBreak){
				breakNumbers++;
			}
		}
		int h = ((int) Math.floor((float) c.height / (elements.length - breakNumbers))) - breakNumbers;
		int rest = c.height - h * (elements.length - breakNumbers) - breakNumbers;
		
		for (JRElement el : elements) {
			JRDesignElement del = (JRDesignElement) el;
			map.put(el, new Rectangle(el.getX(), el.getY(), el.getWidth(), el.getHeight()));
			if (del instanceof JRDesignBreak){
				del.setX(x);
				del.setY(y);
				del.setWidth(w);
				y++;
			} else {
				del.setX(x);
				del.setY(y);
				del.setWidth(w);
				del.setHeight(h + rest);
				// if last grab free pixels
				y += h + rest;
				if (rest > 0)
					rest = 0;
				LayoutManager.layout(jd, map, el);
			}
		}
		return map;
	}

	@Override
	public String getName() {
		return Messages.VerticalRowLayout_name;
	}

	@Override
	public String getToolTip() {
		return Messages.VerticalRowLayout_tooltip;
	}

	@Override
	public String getIcon() {
		return "icons/layout-h.png"; //$NON-NLS-1$
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
			int w = parentSize.width;
			int h = (int) Math.floor((float) parentSize.height / elements.length);
			int rest = parentSize.height - h * elements.length;
			for (Object rawEl : elements) {
				if (rawEl instanceof JRElement){
					JRElement el = (JRElement)rawEl;
					map.put(el, new Rectangle(x, y, w, h + rest));
					// if last grab free pixels
					y += h + rest;
					if (rest > 0)
						rest = 0;
				}
			}
		} else if (insertPosition >= 0){
			int y = 0;
			int w = parentSize.width;
			for(int i = 0; i < insertPosition; i++){
				Object el = elements[i];
				if (el instanceof JRElement){
					y += ((JRElement)el).getHeight();
				}
			}
			map.put(jrFields.get(0), new Rectangle(0, y - 2, w, 4));
			
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
			if ((jrChild.getX() + offsetLeft) < dropPosition.x && dropPosition.x < jrChild.getX() + jrChild.getWidth()) {
				int y = jrChild.getY();
				int span1 = y - 5;
				int span2 = y + 5;
				if (dropPosition.y > span1 && dropPosition.y < span2){
					return index;
				}
				span1 += jrChild.getHeight();
				span2 += jrChild.getHeight();
				if (dropPosition.y > span1 && dropPosition.y < span2){
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
