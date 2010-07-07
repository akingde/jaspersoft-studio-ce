/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.gef.rulers;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import org.eclipse.gef.EditPart;

// TODO: Auto-generated Javadoc
/**
 * Model object representing a guide.
 * <p>
 * In addition to maintaining information about which parts are attached to the
 * guide, LogicGuide also maintains information about the edge along which those
 * parts are attached. This information is useful during resize operations to
 * determine the attachment status of a part.
 * 
 * @author Chicu Veaceslav
 */
public class ReportRulerGuide implements Serializable {

	/** Property used to notify listeners when the parts attached to a guide are changed. */
	public static final String PROPERTY_CHILDREN = "subparts changed"; //$NON-NLS-1$
	
	/** Property used to notify listeners when the guide is re-positioned. */
	public static final String PROPERTY_POSITION = "position changed"; //$NON-NLS-1$

	/** The Constant serialVersionUID. */
	static final long serialVersionUID = 1;

	/** The listeners. */
	protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
	
	/** The map. */
	private Map<Object, EditPart> map;
	
	/** The position. */
	private int position;
	
	/** The horizontal. */
	private boolean horizontal;

	/**
	 * Empty default constructor.
	 */
	public ReportRulerGuide() {
		// empty constructor
	}

	/**
	 * Constructor.
	 * 
	 * @param isHorizontal
	 *          <code>true</code> if the guide is horizontal (i.e., placed on a vertical ruler)
	 */
	public ReportRulerGuide(boolean isHorizontal) {
		setHorizontal(isHorizontal);
	}

	/**
	 * Adds the property change listener.
	 * 
	 * @param listener
	 *          the listener
	 * @see PropertyChangeSupport#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.addPropertyChangeListener(listener);
	}

	/**
	 * Gets the map.
	 * 
	 * @return The Map containing all the parts attached to this guide, and their alignments; the keys are LogicSubparts
	 *         and values are Integers
	 */
	public Map<Object, EditPart> getMap() {
		if (map == null) {
			map = new Hashtable<Object, EditPart>();
		}
		return map;
	}

	/**
	 * Gets the parts.
	 * 
	 * @return the set of all the parts attached to this guide; a set is used because a part can only be attached to a
	 *         guide along one edge.
	 */
	public Set<Object> getParts() {
		return getMap().keySet();
	}

	/**
	 * Gets the position.
	 * 
	 * @return the position/location of the guide (in pixels)
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * Checks if is horizontal.
	 * 
	 * @return if the guide is horizontal (i.e., placed on a vertical ruler)
	 */
	public boolean isHorizontal() {
		return horizontal;
	}

	/**
	 * Removes the property change listener.
	 * 
	 * @param listener
	 *          the listener
	 * @see PropertyChangeSupport#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		listeners.removePropertyChangeListener(listener);
	}

	/**
	 * Sets the orientation of the guide.
	 * 
	 * @param isHorizontal
	 *          <code>true</code> if this guide is to be placed on a vertical ruler
	 */
	public void setHorizontal(boolean isHorizontal) {
		horizontal = isHorizontal;
	}

	/**
	 * Sets the location of the guide.
	 * 
	 * @param offset
	 *          The location of the guide (in pixels)
	 */
	public void setPosition(int offset) {
		if (position != offset) {
			int oldValue = position;
			position = offset;
			listeners.firePropertyChange(PROPERTY_POSITION, new Integer(oldValue), new Integer(position));
		}
	}

}
