/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.fields.table.widget;

import java.beans.PropertyChangeListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.dataset.fields.table.TColumn;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;

public abstract class AWidget {
	private static Map<String, Class<? extends AWControl>> wmap = new HashMap<>();
	static {
		wmap.put(Class.class.getName(), WClassName.class);
		wmap.put(Boolean.class.getName(), WBoolean.class);
		wmap.put(boolean.class.getName(), WBooleanNative.class);
		wmap.put(JRDesignExpression.class.getName(), WExpression.class);
		wmap.put(JRPropertiesMap.class.getName(), WProperties.class);

		wmap.put(Integer.class.getName(), WNumber.class);
		wmap.put(BigInteger.class.getName(), WNumber.class);
		wmap.put(Long.class.getName(), WNumber.class);
		wmap.put(Short.class.getName(), WNumber.class);
		wmap.put(Byte.class.getName(), WNumber.class);
		wmap.put(Double.class.getName(), WNumber.class);
		wmap.put(Float.class.getName(), WNumber.class);
		wmap.put(BigDecimal.class.getName(), WNumber.class);
	}

	public static void addControlValueType(String key, Class<? extends AWControl> wcnt) {
		wmap.put(key, wcnt);
	}

	protected TColumn c;
	protected Object element;
	protected AWControl control;
	protected JasperReportsConfiguration jConfig;

	public AWidget(Composite parent, TColumn c, Object element, JasperReportsConfiguration jConfig) {
		this.c = c;
		this.element = element;
		this.jConfig = jConfig;

		initControl(parent, c);

		final PropertyChangeListener l = evt -> {
			if (evt.getPropertyName().equals(c.getPropertyName()))
				if (control != null)
					control.fillValue();
		};
		if (element instanceof JRChangeEventsSupport)
			((JRChangeEventsSupport) element).getEventSupport().addPropertyChangeListener(l);
		if (element instanceof JRPropertiesHolder)
			((JRPropertiesHolder) element).getPropertiesMap().getEventSupport().addPropertyChangeListener(l);
		if (control != null)
			control.addDisposeListener(e -> {
				if (element instanceof JRChangeEventsSupport)
					((JRChangeEventsSupport) element).getEventSupport().removePropertyChangeListener(l);
				if (element instanceof JRPropertiesHolder)
					((JRPropertiesHolder) element).getPropertiesMap().getEventSupport().removePropertyChangeListener(l);
			});
	}

	public AWControl getControl() {
		return control;
	}

	protected void initControl(Composite parent, TColumn c) {
		if (wmap.containsKey(c.getPropertyType())) {
			try {
				Constructor<? extends AWControl> constr = wmap.get(c.getPropertyType()).getConstructor(AWidget.class);
				control = constr.newInstance(this);
			} catch (SecurityException | NoSuchMethodException | IllegalArgumentException | InstantiationException
					| IllegalAccessException | InvocationTargetException e) {
				// nothing to do
			}
		} else if (c.getType().equals("classTypeCombo"))
			control = new WClassName(this);
		else if (c.getType().equals("checkbox"))
			control = new WBoolean(this);
		else {
			try {
				Class<?> clazz = Class.forName(c.getPropertyType());
				if (clazz.isEnum())
					control = new WEnum(this, (Class<? extends Enum<?>>) clazz);
			} catch (ClassNotFoundException e) {
				// nothing to do
			}
		}
		if (control == null)
			control = new WText(this);

		control.createLabel(parent, c);
		control.createControl(parent);
		control.fillValue();
	}

	public JasperReportsConfiguration getjConfig() {
		return jConfig;
	}

	public TColumn getTColumn() {
		return c;
	}

	protected String getToolTipText() {
		String tt = control.getText();
		if (!Misc.isNullOrEmpty(tt))
			tt += "\n\n";
		if (c.getType().equals("jrProperty"))
			tt += c.getPropertyName() + "\n";
		if (c.getPropertyMetadata() != null && c.getPropertyMetadata().isDeprecated())
			tt += "\nDeprecated\n";
		tt += "Type: " + c.getPropertyType();
		if (!Misc.isNullOrEmpty(c.getDescription())) {
			if (!Misc.isNullOrEmpty(tt))
				tt += "\n\n";
			tt += c.getDescription();
		}
		return tt;
	}

	protected abstract Object getValue();

	public abstract void setValue(Object value);

	public Object getElement() {
		return element;
	}

	public void setElement(Object element) {
		this.element = element;
	}
}
