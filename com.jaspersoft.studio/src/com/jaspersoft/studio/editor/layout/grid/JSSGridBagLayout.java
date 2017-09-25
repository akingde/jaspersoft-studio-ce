/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.layout.grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.editor.layout.AbstractLayout;
import com.jaspersoft.studio.editor.layout.ILayoutUIProvider;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * GridBagLayout for the elements inside a container in JSS.
 * It show the properties to configure an element position when the parent
 * has as layout a Grid Layout, the value of the layout properties are
 * stored inside the element
 * 
 * @author Orlandin Marco
 *
 */
public class JSSGridBagLayout extends AbstractLayout {

	/**
	 * The key used to store the column position
	 */
	public static final String PROPERTY_X = "com.jaspersoft.layout.grid.x"; //$NON-NLS-1$
	
	/**
	 * The key used to store the row position
	 */
	public static final String PROPERTY_Y = "com.jaspersoft.layout.grid.y"; //$NON-NLS-1$
	
	/**
	 * The key used to store the column span
	 */
	public static final String PROPERTY_COLSPAN = "com.jaspersoft.layout.grid.colspan"; //$NON-NLS-1$
	
	/**
	 * The key used to store the row span
	 */
	public static final String PROPERTY_ROWSPAN = "com.jaspersoft.layout.grid.rowspan"; //$NON-NLS-1$
	
	/**
	 * The key used to store the column weight
	 */
	public static final String PROPERTY_WEIGHT_COLUMN = "com.jaspersoft.layout.grid.weight.x"; //$NON-NLS-1$
	
	/**
	 * The key used to store the row weight
	 */
	public static final String PROPERTY_WEIGHT_ROW = "com.jaspersoft.layout.grid.weight.y"; //$NON-NLS-1$
	
	/**
	 * The key used to store the property for the fixed size of the element
	 */
	public static final String PROPERTY_IS_FIXED = "com.jaspersoft.layout.grid.weight.fixed"; //$NON-NLS-1$
	
	
	/**
	 * When the parent has a grid layout it always show additional controls
	 */
	public boolean showAdditionalControlsOnChild(JRPropertiesMap elementProperties, JRPropertiesMap parentProperties) {
		return true;
	}
	
	@Override
	public ILayoutUIProvider getControlsProvider() {
		return new JSSGridBagUIProvider();
	}
	
	@Override
	public boolean allowChildBoundChange(ANode resizedNode, Rectangle oldBounds, Rectangle newBounds) {
		if (ModelUtils.safeEquals(oldBounds, newBounds)) return true;
		JRPropertiesHolder currentElement = LayoutManager.getPropertyHolder(resizedNode);
		if (currentElement != null){
			Object value = currentElement.getPropertiesMap().getProperty(PROPERTY_IS_FIXED);
			boolean isFixed = value != null ? Boolean.parseBoolean(value.toString()) : false;
			return isFixed;
		}
		return false;
	}
	
	@Override
	public Map<JRElement, Rectangle> layout(JasperDesign jd, JRElementGroup container, JRElement[] elements, Dimension c) {
		GridBagLayoutUtil layout = new GridBagLayoutUtil();
		Map<JRElement, Rectangle> result = layout.layoutContainer(c, elements);
		Map<JRElement, Rectangle> oldValues = new HashMap<JRElement, Rectangle>();
		for(Entry<JRElement, Rectangle> entry : result.entrySet()){
			JRDesignElement del = (JRDesignElement) entry.getKey();
			oldValues.put(del, new Rectangle(del.getX(), del.getY(), del.getWidth(), del.getHeight()));
			Rectangle placement = entry.getValue();
			boolean relayoutChildren = del.getWidth() != placement.width|| del.getHeight() != placement.height;
			del.setX(placement.x);
			del.setY(placement.y);
			del.setWidth(placement.width);
			del.setHeight(placement.height);	
			if (relayoutChildren){
				LayoutManager.layout(jd, result, del);
			}
		}
		return oldValues;
	}
	
	@Override
	public String getName() {
		return Messages.JSSGridBagLayout_layoutName;
	}

	@Override
	public String getToolTip() {
		return Messages.FreeLayout_tooltip;
	}

	@Override
	public String getIcon() {
		return "icons/layout-6.png"; //$NON-NLS-1$
	}

	@Override
	public Map<Object, Rectangle> getLayoutPosition(Object[] elements, int insertPosition, Dimension parentSize) {
		GridBagLayoutUtil layout = new GridBagLayoutUtil();
		List<JRElement> elementsToLayout = new ArrayList<JRElement>();
		for(Object obj : elements){
			if (obj instanceof JRElement){
				elementsToLayout.add((JRElement)obj);
			}
		}
		Map<JRElement, Rectangle> result = layout.layoutContainer(parentSize, elementsToLayout.toArray(new JRElement[elementsToLayout.size()]));
		Map<Object, Rectangle> rawResult = new HashMap<Object, Rectangle>();
		rawResult.putAll(result);
		return rawResult;
	}
}
