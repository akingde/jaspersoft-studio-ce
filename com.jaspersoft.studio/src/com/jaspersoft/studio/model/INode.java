/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.List;

import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import net.sf.jasperreports.engine.design.JasperDesign;

/*
 * The Interface INode.
 * 
 * @author Chicu Veaceslav
 */
public interface INode extends PropertyChangeListener {

	/**
	 * Gets the parent.
	 * 
	 * @return the parent
	 */
	public INode getParent();

	/**
	 * Gets the children.
	 * 
	 * @return the children
	 */
	public List<INode> getChildren();

	/**
	 * Sets the value.
	 * 
	 * @param value
	 *            the new value
	 */
	public void setValue(Object value);

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public Object getValue();

	/**
	 * Gets the property change support.
	 * 
	 * @return the property change support
	 */
	public PropertyChangeSupport getPropertyChangeSupport();

	/**
	 * Gets the root.
	 * 
	 * @return the root
	 */
	public INode getRoot();

	// -- CellLabelProvider methods, move to interface
	// ------------------------------------------------------------------------------------
	/**
	 * Gets the image path.
	 * 
	 * @return the image path
	 */
	public org.eclipse.jface.resource.ImageDescriptor getImagePath();

	/**
	 * Gets the display text.
	 * 
	 * @return the display text
	 */
	public String getDisplayText();

	/**
	 * Gets the display text.
	 * 
	 * @return the display text
	 */
	public StyledString getStyledDisplayText();

	/**
	 * Gets the tool tip.
	 * 
	 * @return the tool tip
	 */
	public String getToolTip();

	/**
	 * Gets the font.
	 * 
	 * @return the font
	 */
	public Font getFont();

	/**
	 * Gets the foreground.
	 * 
	 * @return the foreground
	 */
	public Color getForeground();

	/**
	 * Gets the background.
	 * 
	 * @return the background
	 */
	public Color getBackground();

	/**
	 * This represents a convenient way to access the JasperDesign object that
	 * subtends this node.
	 * 
	 * @return the JasperDesign that subtends this model
	 */
	public JasperDesign getJasperDesign();

	/**
	 * Boolean flag to declare if the children are visible or not
	 * 
	 * @return true if the children are visible, false otherwise
	 */
	public boolean showChildren();
}
