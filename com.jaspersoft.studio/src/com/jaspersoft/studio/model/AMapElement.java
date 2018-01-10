/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;

public abstract class AMapElement extends APropertyNode {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public AMapElement() {
		super();
		setValue("");
	}

	public AMapElement(ANode parent, int index) {
		super(parent, index);
	}

	public AMapElement(ANode parent, Object value, int index) {
		super(parent, index);
		setValue(Misc.nvl(value, ""));
	}

	private IPropertyDescriptor[] descriptors;

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors = descriptors1;
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
	}

	protected Map<String, Object> props = new HashMap<String, Object>();

	@Override
	public Object getPropertyValue(Object id) {
		return Misc.nvl(props.get(id), getDefaultsPropertiesMap().get(id));
	}

	@Override
	public Object getPropertyActualValue(Object id) {
		return props.get(id);
	}

	private boolean noEvents = false;

	public void setNoEvents(boolean noEvents) {
		this.noEvents = noEvents;
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		Object oldVal = props.get((String) id);
		if (value == null)
			props.remove((String) id);
		else
			props.put((String) id, value);
		// System.out.println(id + " ; " + value);
		if (!noEvents) {
			firePropertyChange(id, value, oldVal);
		}
	}

	public void firePropertyChange(Object id, Object value, Object oldVal) {
		if (getRoot() != null)
			getRoot().getPropertyChangeSupport().firePropertyChange((String) id, oldVal, value);
		getPropertyChangeSupport().firePropertyChange((String) id, oldVal, value);
	}

	public Set<String> getProperties() {
		return props.keySet();
	}

	public void copyProperties(Map<String, Object> ps) {
		setNoEvents(true);
		props.clear();
		for (String key : ps.keySet())
			setPropertyValue(key, ps.get(key));
		setNoEvents(false);
	}

	public void copyProperties(AMapElement dest) {
		dest.setNoEvents(true);
		for (String key : props.keySet())
			dest.setPropertyValue(key, getPropertyValue(key));
		dest.setNoEvents(false);
	}

	public Map<String, Object> copyPropertiesUndo(AMapElement dest) {
		Map<String, Object> old = new HashMap<String, Object>();
		dest.setNoEvents(true);
		for (String key : props.keySet()) {
			old.put(key, dest.getPropertyValue(key));
			dest.setPropertyValue(key, getPropertyValue(key));
		}
		dest.setNoEvents(false);
		return old;
	}
}
