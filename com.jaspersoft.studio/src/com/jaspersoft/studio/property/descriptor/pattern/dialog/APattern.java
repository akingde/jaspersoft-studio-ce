package com.jaspersoft.studio.property.descriptor.pattern.dialog;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.text.Format;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public abstract class APattern {
	public APattern(Composite parent, Format formatter, Object sample) {
		super();
		this.formatter = formatter;
		this.sample = sample;
		control = createControl(parent);
	}

	private String pattern;
	private Format formatter;
	private Object sample;
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public Format getFormatter() {
		return formatter;
	}

	public void setFormatter(Format formatter) {
		this.formatter = formatter;
	}

	public Object getSample() {
		return sample;
	}

	public void setSample(Object sample) {
		this.sample = sample;
	}

	public abstract Control createControl(Composite parent);

	private Control control;

	public Control getControl() {
		return control;
	}

	protected void formatChanged() {
		propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(this, "pattern", null, getPattern()));
	}

	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	public PropertyChangeSupport getPropertyChangeSupport() {
		return propertyChangeSupport;
	}
}
