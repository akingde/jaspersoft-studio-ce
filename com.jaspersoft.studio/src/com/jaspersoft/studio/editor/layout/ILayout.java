/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.layout;

import java.util.Map;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.ANode;

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JasperDesign;

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
	public abstract Map<JRElement, Rectangle> layout(JasperDesign jd, JRElementGroup container, JRElement[] elements, Dimension c);
	
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
	public boolean showAdditionalControlsOnChild(JRPropertiesMap elementProperties, JRPropertiesMap parentProperties);

	/**
	 * The implementation of this will be called when an element that is using this implementation as layout is
	 * selected, and check if that element should have additional control in the layout section
	 * 
	 * @param elementProperties the properties of the selected element
	 * @param parentProperties the properties of the parent
	 * 
	 * @return true if the element should have additional control in the layout section (created trough createControls implementation),
	 * false otherwise
	 */
	public boolean showAdditionalControlsOnNode(JRPropertiesMap elementProperties, JRPropertiesMap parentProperties);
	
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
	 * @param insertPosition the position where the elements will be inserted, -1 means at the end of the container
	 * @param parentSize the dimension of the parent
	 * @return the position of each elements after layout operation
	 */
	public abstract Map<Object, Rectangle> getLayoutPosition(Object[] elements, int insertPosition, Dimension parentSize);

	/**
	 * Return the commands to execute when the layout is activated. A layout is activated when it is assigned
	 * to a container
	 * 
	 * @param selectedNode the node where the layout is set
	 * @return a command, can be null if the activation does nothing
	 */
	public Command activate(ANode selectedNode);
	
	/**
	 * Return the commands to execute when the layout is deactivated. A layout is activated when it is removed
	 * to a container
	 * 
	 * @param selectedNode the node where the layout was set
	 * @return a command, can be null if the deactivation does nothing
	 */
	public Command deactivate(ANode selectedNode);
	
	/**
	 * Return if this layout is selectable on the selected node
	 * 
	 * @param selectedNode the node where the user want to set a layout
	 * @return true if this layout is available, false otherwise
	 */
	public boolean isSelectable(ANode selectedNode);
	
	/**
	 * Return the insert position of a dropped element into a container. This
	 * method is called on the implementation of the layout the container is using
	 * 
	 * @param container the container where the element is dropped
	 * @param dropPosition the position where the mouse cursor is
	 * @return the index where the new node will be inserted or -1 to add it 
	 * at the end
	 */
	public int getInsertPosition(ANode container, Point dropPosition);
}
