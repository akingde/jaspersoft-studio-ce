/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

/**
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
	 *          the new value
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
	 * This represents a convenient way to access the JasperDesign object that subtends this node.
	 * 
	 * @return the JasperDesign that subtends this model
	 */
	public JasperDesign getJasperDesign();
}
