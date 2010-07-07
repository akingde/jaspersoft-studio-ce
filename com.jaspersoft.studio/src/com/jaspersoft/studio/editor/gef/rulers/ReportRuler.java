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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.rulers.RulerProvider;

// TODO: Auto-generated Javadoc
/**
 * The Class ReportRuler.
 * 
 * @author Chicu Veaceslav
 */
public class ReportRuler implements Serializable {

	/** The Constant PROPERTY_CHILDREN. */
	public static final String PROPERTY_CHILDREN = "children changed"; //$NON-NLS-1$
	
	/** The Constant PROPERTY_UNIT. */
	public static final String PROPERTY_UNIT = "units changed"; //$NON-NLS-1$

	/** The Constant serialVersionUID. */
	static final long serialVersionUID = 1;

	/** The listeners. */
	protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
	
	/** The unit. */
	private int unit;
	
	/** The horizontal. */
	private boolean horizontal;
	
	/** The guides. */
	private List<ReportRulerGuide> guides = new ArrayList<ReportRulerGuide>();

	/**
	 * Instantiates a new report ruler.
	 * 
	 * @param isHorizontal
	 *          the is horizontal
	 */
	public ReportRuler(boolean isHorizontal) {
		this(isHorizontal, RulerProvider.UNIT_PIXELS);
	}

	/**
	 * Instantiates a new report ruler.
	 * 
	 * @param isHorizontal
	 *          the is horizontal
	 * @param unit
	 *          the unit
	 */
	public ReportRuler(boolean isHorizontal, int unit) {
		horizontal = isHorizontal;
		setUnit(unit);
	}

	/**
	 * Adds the guide.
	 * 
	 * @param guide
	 *          the guide
	 */
	public void addGuide(ReportRulerGuide guide) {
		if (!guides.contains(guide)) {
			guide.setHorizontal(!isHorizontal());
			guides.add(guide);
			listeners.firePropertyChange(PROPERTY_CHILDREN, null, guide);
		}
	}

	/**
	 * Adds the property change listener.
	 * 
	 * @param listener
	 *          the listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.addPropertyChangeListener(listener);
	}

	// the returned list should not be modified
	/**
	 * Gets the guides.
	 * 
	 * @return the guides
	 */
	public List<ReportRulerGuide> getGuides() {
		return guides;
	}

	/**
	 * Gets the unit.
	 * 
	 * @return the unit
	 */
	public int getUnit() {
		return unit;
	}

	/**
	 * Checks if is hidden.
	 * 
	 * @return true, if is hidden
	 */
	public boolean isHidden() {
		return false;
	}

	/**
	 * Checks if is horizontal.
	 * 
	 * @return true, if is horizontal
	 */
	public boolean isHorizontal() {
		return horizontal;
	}

	/**
	 * Removes the guide.
	 * 
	 * @param guide
	 *          the guide
	 */
	public void removeGuide(ReportRulerGuide guide) {
		if (guides.remove(guide)) {
			listeners.firePropertyChange(PROPERTY_CHILDREN, null, guide);
		}
	}

	/**
	 * Removes the property change listener.
	 * 
	 * @param listener
	 *          the listener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		listeners.removePropertyChangeListener(listener);
	}

	/**
	 * Sets the hidden.
	 * 
	 * @param isHidden
	 *          the new hidden
	 */
	public void setHidden(boolean isHidden) {
	}

	/**
	 * Sets the unit.
	 * 
	 * @param newUnit
	 *          the new unit
	 */
	public void setUnit(int newUnit) {
		if (unit != newUnit) {
			int oldUnit = unit;
			unit = newUnit;
			listeners.firePropertyChange(PROPERTY_UNIT, oldUnit, newUnit);
		}
	}

}
