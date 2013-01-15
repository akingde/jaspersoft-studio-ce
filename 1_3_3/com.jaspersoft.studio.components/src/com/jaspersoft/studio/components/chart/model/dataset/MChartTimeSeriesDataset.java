/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.dataset;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.design.JRDesignTimeSeriesDataset;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.CollectionElementAddedEvent;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.util.ReportFactory;
import com.jaspersoft.studio.property.descriptor.classname.ClassTypePropertyDescriptor;

public class MChartTimeSeriesDataset extends MChartDataset {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MChartTimeSeriesDataset(ANode parent,
			JRDesignTimeSeriesDataset value, JasperDesign jasperDesign) {
		super(parent, value, jasperDesign);
	}

	private static IPropertyDescriptor[] descriptors;
	private static Map<String, Object> defaultsMap;

	@Override
	public Map<String, Object> getDefaultsMap() {
		return defaultsMap;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1,
			Map<String, Object> defaultsMap1) {
		descriptors = descriptors1;
		defaultsMap = defaultsMap1;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *            the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc,
			Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		ClassTypePropertyDescriptor timePeriodD = new ClassTypePropertyDescriptor(
				JRDesignTimeSeriesDataset.PROPERTY_TIME_PERIOD,
				Messages.MChartTimeSeriesDataset_time_period);
		timePeriodD
				.setDescription(Messages.MChartTimeSeriesDataset_time_period_description);
		desc.add(timePeriodD);

		timePeriodD
				.setCategory(Messages.MChartTimeSeriesDataset_chart_time_period_dataset_category);

		defaultsMap.put(JRDesignTimeSeriesDataset.PROPERTY_TIME_PERIOD, null);
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignTimeSeriesDataset jrElement = (JRDesignTimeSeriesDataset) getValue();
		if (jrElement != null)
			if (id.equals(JRDesignTimeSeriesDataset.PROPERTY_TIME_PERIOD)) {
				Class<?> timePeriod = jrElement.getTimePeriod();
				if (timePeriod != null)
					return timePeriod.toString();
				return null;
			}

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignTimeSeriesDataset jrElement = (JRDesignTimeSeriesDataset) getValue();

		if (id.equals(JRDesignTimeSeriesDataset.PROPERTY_TIME_PERIOD)) {
			if (value instanceof Class<?>) {
				jrElement.setTimePeriod((Class<?>) value);
			} else {
				Class<?> v = null;
				if (value != null) {
					try {
						v = Class.forName((String) value);
						jrElement.setTimePeriod(v);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		} else
			super.setPropertyValue(id, value);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("timeSeries")) { //$NON-NLS-1$
			if (evt.getSource() == getValue()) {
				if (evt.getOldValue() == null && evt.getNewValue() != null) {
					int newIndex = -1;
					if (evt instanceof CollectionElementAddedEvent) {
						newIndex = ((CollectionElementAddedEvent) evt)
								.getAddedIndex();
					}
					ReportFactory.createNode(this, evt.getNewValue(), newIndex);
				} else if (evt.getOldValue() != null
						&& evt.getNewValue() == null) {
					// delete
					for (INode n : getChildren()) {
						if (n.getValue() == evt.getOldValue()) {
							removeChild((ANode) n);
							break;
						}
					}
				} else {
					// changed
					for (INode n : getChildren()) {
						if (n.getValue() == evt.getOldValue())
							n.setValue(evt.getNewValue());
					}
				}
			}
		}
		super.propertyChange(evt);
	}
}
