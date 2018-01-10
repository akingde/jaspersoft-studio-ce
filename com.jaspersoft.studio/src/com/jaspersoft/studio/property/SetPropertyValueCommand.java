/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.events.JRPropertyChangeSupport;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.APropertyNode;

/**
 * Command used to edit a value of a JRPropertiesMap
 * 
 * @author Orlandin Marco
 *
 */
public class SetPropertyValueCommand extends Command {

	/**
	 * The map
	 */
	private JRPropertiesMap map;

	/**
	 * The key of the value to edit
	 */
	private String valueKey;

	/**
	 * The new value, if null the value will be removed
	 */
	private String newValue;

	/**
	 * The value on the map before the edit, used for undo
	 */
	private String oldValue;

	/**
	 * flag used to know if before the set a value with the same key was already
	 * present or not, used for the undo
	 */
	private boolean wasOldValuePresent = false;
	private JRPropertyChangeSupport pChangeSupport;

	/**
	 * Create the command
	 * 
	 * @param map
	 *            The map, must be not null
	 * @param valueKey
	 *            The key of the value to edit, must be not null
	 * @param newValue
	 *            The new value, if null the value will be removed
	 */
	public SetPropertyValueCommand(JRPropertiesMap map, String valueKey, String newValue) {
		this.map = map;
		this.valueKey = valueKey;
		this.newValue = newValue;
	}

	public SetPropertyValueCommand(JRPropertiesMap map, String valueKey, String newValue,
			JRPropertyChangeSupport pChangeSupport) {
		this(map, valueKey, newValue);
		this.pChangeSupport = pChangeSupport;
	}

	@Override
	public void execute() {
		wasOldValuePresent = map.containsProperty(valueKey);
		oldValue = map.getProperty(valueKey);
		if (newValue == null) {
			map.removeProperty(valueKey);
		} else {
			map.setProperty(valueKey, newValue);
		}
		if (pChangeSupport != null)
			pChangeSupport.firePropertyChange(APropertyNode.PROPERTY_MAP, oldValue, newValue);
	}

	@Override
	public void undo() {
		if (!wasOldValuePresent) {
			map.removeProperty(valueKey);
		} else {
			map.setProperty(valueKey, oldValue);
		}
		if (pChangeSupport != null)
			pChangeSupport.firePropertyChange(APropertyNode.PROPERTY_MAP, newValue, oldValue);
	}
}
