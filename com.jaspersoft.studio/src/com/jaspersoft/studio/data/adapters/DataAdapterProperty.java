package com.jaspersoft.studio.data.adapters;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class DataAdapterProperty {

	private String name;
	private String value;

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	public DataAdapterProperty() {
	}

	public DataAdapterProperty(String name, String value) {
		super();
		this.setName(name);
		this.setValue(value);
	}

	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
					propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	/**
	 * @param value the name to set
	 */
	public void setName(String name) {
		propertyChangeSupport.firePropertyChange("name", this.name, this.name = name);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		propertyChangeSupport.firePropertyChange("value", this.value, this.value = value);
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return name + "=" + value;
	}

	
}
