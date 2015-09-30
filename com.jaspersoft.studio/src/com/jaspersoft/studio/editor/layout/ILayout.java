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
package com.jaspersoft.studio.editor.layout;

import java.util.Map;

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRPropertiesMap;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.model.ANode;

/**
 * Interface to implement to provide a layout strategy inside JSS
 * 
 * @author Orlandin Marco
 */
public interface ILayout {
	
	/**
	 * Key used to save the name of the layout class that implement this in the properties
	 * of the container using this implementation 
	 */
	public static final String KEY = "com.jaspersoft.studio.layout";

	/**
	 * Return the name of this layout
	 */
	public abstract String getName();

	/**
	 * Return the tooltip of this layout
	 */
	public abstract String getToolTip();

	/**
	 * Return the icon path of this layout
	 */
	public abstract String getIcon();

	/**
	 * Layout all the elements of a container. The position of the elements must
	 * be changed by the implementation of this method and the returned map must have
	 * as key each of the element passed and as value the position that  the element had
	 * Before to be layouted
	 * 
	 * @param elements the elements to be layouted, must be not null and all the elements must belong
	 * to the same parent
	 * @param c the dimension of the parent
	 * @return the position of each elements before this layout operation changed their size\location
	 */
	public abstract Map<JRElement, Rectangle> layout(JRElement[] elements, Dimension c);
	
	/**
	 * Return the object used to configure visually the layout properties.
	 * This object shouldn't be cached but a new one should be build when it is requested.
	 * 
	 * @return the layout ui provider, can be null if showAdditionalControls return false, otherwise
	 * it should return something
	 */
	public ILayoutUIProvider getControlsProvider();
	
	/**
	 * The implementation of this will be called when a children of an element that is using this implementation as layout is
	 * selected, and check if that element should have additional control in the layout section because of the parent layout strategy
	 * 
	 * @param elementProperties the properties of the selected element
	 * @param parentProperties the properties of the parent
	 * 
	 * @return true if the element should have additional control in the layout section (created trough createControls implementation),
	 * false otherwise
	 */
	public boolean showAdditionalControls(JRPropertiesMap elementProperties, JRPropertiesMap parentProperties);
	
	/**
	 * Check if an operation that edit the bounds of an element is allowed using while the parent is using this layout
	 * 
	 * @param resizedNode the node moved or resized
	 * @param oldBound the old bounds of the element
	 * @param newBounds the new bounds of the child element
	 */
	public boolean allowChildBoundChange(ANode resizedNode, Rectangle oldBounds, Rectangle newBounds);
	
	/**
	 * Return the layout position of all the elements of a container. The position of the elements is not
	 * changed, the new position is only returned in the return vale
	 * 
	 * @param elements the elements to be layout, must be not null and all the elements must belong
	 * to the same parent
	 * @param c the dimension of the parent
	 * @return the position of each elements after layout operation
	 */
	public abstract Map<JRElement, Rectangle> getLayoutPosition(JRElement[] elements, Dimension parentSize);
}
