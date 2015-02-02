/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.dataset;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.design.JRDesignTimeSeriesDataset;
import net.sf.jasperreports.charts.type.TimePeriodEnum;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.CollectionElementAddedEvent;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.util.ReportFactory;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;

public class MChartTimeSeriesDataset extends MChartDataset {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MChartTimeSeriesDataset(ANode parent,
			JRDesignTimeSeriesDataset value, JasperDesign jasperDesign) {
		super(parent, value, jasperDesign);
	}

	private static IPropertyDescriptor[] descriptors;
	private static Map<String, Object> defaultsMap;
	private NamedEnumPropertyDescriptor<TimePeriodEnum> timePeriodD;

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

		timePeriodD = new NamedEnumPropertyDescriptor<TimePeriodEnum>(
				JRDesignTimeSeriesDataset.PROPERTY_TIME_PERIOD,
				Messages.MChartTimeSeriesDataset_time_period,
				TimePeriodEnum.DAY, NullEnum.NULL);
		timePeriodD
				.setDescription(Messages.MChartTimeSeriesDataset_time_period_description);
		desc.add(timePeriodD);

		timePeriodD
				.setCategory(Messages.MChartTimeSeriesDataset_chart_time_period_dataset_category);

		defaultsMap.put(JRDesignTimeSeriesDataset.PROPERTY_TIME_PERIOD,
				timePeriodD.getIntValue(TimePeriodEnum.DAY));

		setHelpPrefix(desc,
				"net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#timeSeriesDataset");
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignTimeSeriesDataset jrElement = (JRDesignTimeSeriesDataset) getValue();
		if (jrElement != null)
			if (id.equals(JRDesignTimeSeriesDataset.PROPERTY_TIME_PERIOD)) {
				return timePeriodD.getIntValue(TimePeriodEnum
						.getByValue(jrElement.getTimePeriod()));

				// if (jrElement.getTimePeriod() == null)
				// return 0;
				// TimePeriodEnum tpe = TimePeriodEnum.getByValue(jrElement
				// .getTimePeriod());
				// TimePeriodEnum[] tpevalues = TimePeriodEnum.values();
				// for (int i = 0; i < tpevalues.length; i++) {
				// if (tpe.equals(tpevalues[i]))
				// return i + 1;
				// }
				// Class<?> timePeriod = jrElement.getTimePeriod();
				// if (timePeriod != null)
				// return timePeriod.toString();
				// return null;
			}

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignTimeSeriesDataset jrElement = (JRDesignTimeSeriesDataset) getValue();

		if (id.equals(JRDesignTimeSeriesDataset.PROPERTY_TIME_PERIOD)) {
			if (value instanceof Class<?>) {
				jrElement.setTimePeriod((Class<?>) value);
			} else if (value instanceof Integer) {
				Integer v = (Integer) value;
				if (v == 0)
					jrElement.setTimePeriod(null);
				else {
					TimePeriodEnum tpe = TimePeriodEnum.values()[v - 1];
					jrElement.setTimePeriod(tpe.getTimePeriod());
				}
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
