/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.chart.model.chartAxis;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.JRChartAxis;
import net.sf.jasperreports.charts.design.JRDesignChartAxis;
import net.sf.jasperreports.charts.type.AxisPositionEnum;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.chart.ChartNodeIconDescriptor;
import com.jaspersoft.studio.chart.messages.Messages;
import com.jaspersoft.studio.chart.model.MChart;
import com.jaspersoft.studio.chart.model.plot.PlotFactory;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.property.descriptor.JRPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.utils.EnumHelper;

public class MChartAxes extends APropertyNode {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new ChartNodeIconDescriptor("chartaxis"); //$NON-NLS-1$
		return iconDescriptor;
	}

	public String getDisplayText() {
		JRChartAxis ca = (JRChartAxis) getValue();
		if (ca != null) {
			return PlotFactory.getChartPlot(ca.getChart().getPlot()).getDisplayText();
		}
		return getIconDescriptor().getTitle();
	}

	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	public MChartAxes() {
		super();
	}

	public MChartAxes(ANode parent, JRChartAxis jrChart, int newIndex) {
		super(parent, newIndex);
		setValue(jrChart);
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
	public void setDescriptors(IPropertyDescriptor[] descriptors1, Map<String, Object> defaultsMap1) {
		descriptors = descriptors1;
		defaultsMap = defaultsMap1;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		// super.createPropertyDescriptors(desc, defaultsMap);

		ComboBoxPropertyDescriptor positionD = new ComboBoxPropertyDescriptor(JRDesignChartAxis.PROPERTY_POSITION,
				Messages.MChartAxes_position, EnumHelper.getEnumNames(AxisPositionEnum.values(), NullEnum.NOTNULL));
		positionD.setDescription(Messages.MChartAxes_position_description);
		desc.add(positionD);

		JRPropertyDescriptor chartD = new JRPropertyDescriptor(JRDesignChartAxis.PROPERTY_CHART, Messages.MChartAxes_chart);
		chartD.setDescription(Messages.MChartAxes_chart_description);
		desc.add(chartD);
		//
		// if (mChart == null) {
		// mChart = new MChart();
		// mChart.setValue(((JRChartAxis) getValue()).getChart());
		// }
		// mChart.createPropertyDescriptors(desc, defaultsMap);

	}

	@Override
	protected void postDescriptors(IPropertyDescriptor[] descriptors) {
		// initialize style
		JasperDesign jasperDesign = getJasperDesign();
		if (jasperDesign != null) {
			// initialize groups
			JRGroup[] groups = jasperDesign.getGroups();
			String[] items = new String[groups.length + 1];
			items[0] = ""; //$NON-NLS-1$
			for (int j = 0; j < groups.length; j++) {
				items[j + 1] = groups[j].getName();
			}
			setGroupItems(items);
		}
	}

	protected void setGroupItems(String[] items) {
		if (mChart != null)
			mChart.setGroupItems(items);
	}

	private MChart mChart;

	public Object getPropertyValue(Object id) {
		JRDesignChartAxis jrElement = (JRDesignChartAxis) getValue();

		if (id.equals(JRDesignChartAxis.PROPERTY_POSITION))
			return EnumHelper.getValue(jrElement.getPositionValue(), 1, false);

		if (id.equals(JRDesignChartAxis.PROPERTY_CHART)) {
			if (mChart == null)
				mChart = new MChart();
			setChildListener(mChart);
			mChart.setValue(jrElement.getChart());
			return mChart;
		}

		// if (mChart == null) {
		// mChart = new MChart();
		// mChart.setValue(((JRChartAxis) getValue()).getChart());
		// }
		//
		// Object val = mChart.getPropertyValue(id);
		// if (val != null)
		// return val;

		return null;
	}

	public void setPropertyValue(Object id, Object value) {
		JRDesignChartAxis jrElement = (JRDesignChartAxis) getValue();
		if (id.equals(JRDesignChartAxis.PROPERTY_POSITION))
			jrElement.setPosition((AxisPositionEnum) EnumHelper.getSetValue(AxisPositionEnum.values(), value, 1, false));
		else if (mChart != null) {
			mChart.setPropertyValue(id, value);
		}
	}

	@Override
	public void setValue(Object value) {
		JRDesignChartAxis oldObject = (JRDesignChartAxis) getValue();
		JRDesignChartAxis newObject = (JRDesignChartAxis) value;

		if (oldObject != null) {
			((JRDesignChart) oldObject.getChart()).getEventSupport().removePropertyChangeListener(this);
		}
		if (newObject != null) {
			((JRDesignChart) newObject.getChart()).getEventSupport().addPropertyChangeListener(this);
		}
		super.setValue(value);
	}

}
