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
package com.jaspersoft.studio.editor.layout.grid;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.editor.layout.AbstractLayout;
import com.jaspersoft.studio.editor.layout.ILayoutUIProvider;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;

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
	 * The key used to store the row weight
	 */
	public static final String PROPERTY_WEIGHTX = "com.jaspersoft.layout.grid.weight.x"; //$NON-NLS-1$
	
	/**
	 * The key used to store the column weight
	 */
	public static final String PROPERTY_WEIGHTY = "com.jaspersoft.layout.grid.weight.y"; //$NON-NLS-1$
	
	/**
	 * The key used to store the property for the fixed size of the element
	 */
	public static final String PROPERTY_IS_FIXED = "com.jaspersoft.layout.grid.weight.fixed"; //$NON-NLS-1$
	
	
	/**
	 * When the parent has a grid layout it always show additional controls
	 */
	public boolean showAdditionalControls(JRPropertiesHolder elementProperties, JRPropertiesHolder parentProperties) {
		return true;
	}
	
	@Override
	public ILayoutUIProvider getControlsProvider() {
		return new JSSGridBagUIProvider();
	}
	
	@Override
	public boolean allowChildBoundChange(ANode resizedNode, Rectangle oldBounds, Rectangle newBounds) {
		JRPropertiesHolder currentElement = LayoutManager.getPropertyHolder(resizedNode);
		if (currentElement != null){
			Object value = currentElement.getPropertiesMap().getProperty(PROPERTY_IS_FIXED);
			boolean isFixed = value != null ? Boolean.parseBoolean(value.toString()) : false;
			return isFixed;
		}
		return false;
	}
	
	@Override
	public Map<JRElement, Rectangle> layout(JRElement[] elements, Dimension c) {
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
				LayoutManager.layout(result, del);
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
		return "icons/layout.png"; //$NON-NLS-1$
	}
}
