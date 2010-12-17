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
package com.jaspersoft.studio.chart.model.plot;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.JRPiePlot;
import net.sf.jasperreports.charts.design.JRDesignPiePlot;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.chart.model.MChartItemLabel;
import com.jaspersoft.studio.chart.property.descriptor.PlotPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;

public class MPiePlot extends MChartPlot {
	public MPiePlot(JRPiePlot value) {
		super(value);
	}
	public String getDisplayText() {
		return Messages.MPiePlot_pie_plot;
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

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		PlotPropertyDescriptor itemLabelD = new PlotPropertyDescriptor(JRDesignPiePlot.PROPERTY_ITEM_LABEL, Messages.MPiePlot_item_label);
		itemLabelD.setDescription(Messages.MPiePlot_item_label_description);
		desc.add(itemLabelD);

		CheckBoxPropertyDescriptor showLabelsD = new CheckBoxPropertyDescriptor(JRDesignPiePlot.PROPERTY_SHOW_LABELS,
				Messages.MPiePlot_show_labels, NullEnum.NULL);
		showLabelsD.setDescription(Messages.MPiePlot_show_labels_description);
		desc.add(showLabelsD);

		CheckBoxPropertyDescriptor circularD = new CheckBoxPropertyDescriptor(JRDesignPiePlot.PROPERTY_CIRCULAR,
				Messages.MPiePlot_circular, NullEnum.NULL);
		circularD.setDescription(Messages.MPiePlot_circular_description);
		desc.add(circularD);

		NTextPropertyDescriptor legendLabelFormatD = new NTextPropertyDescriptor(
				JRDesignPiePlot.PROPERTY_LEGEND_LABEL_FORMAT, Messages.MPiePlot_legend_label_format);
		legendLabelFormatD.setDescription(Messages.MPiePlot_legend_label_format_description);
		desc.add(legendLabelFormatD);

		NTextPropertyDescriptor labelFormatD = new NTextPropertyDescriptor(JRDesignPiePlot.PROPERTY_LABEL_FORMAT,
				Messages.MPiePlot_label_format);
		labelFormatD.setDescription(Messages.MPiePlot_label_format_description);
		desc.add(labelFormatD);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignPiePlot jrElement = (JRDesignPiePlot) getValue();
		if (id.equals(JRDesignPiePlot.PROPERTY_SHOW_LABELS))
			return jrElement.getShowLabels();
		if (id.equals(JRDesignPiePlot.PROPERTY_CIRCULAR))
			return jrElement.getCircular();
		if (id.equals(JRDesignPiePlot.PROPERTY_LEGEND_LABEL_FORMAT))
			return jrElement.getLegendLabelFormat();
		if (id.equals(JRDesignPiePlot.PROPERTY_LABEL_FORMAT))
			return jrElement.getLabelFormat();
		if (id.equals(JRDesignPiePlot.PROPERTY_ITEM_LABEL)) {
			if (ilFont == null){
				ilFont = new MChartItemLabel(jrElement.getItemLabel());
				setChildListener(ilFont);
			}
			return ilFont;
		}

		return super.getPropertyValue(id);
	}

	private MChartItemLabel ilFont;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignPiePlot jrElement = (JRDesignPiePlot) getValue();
		if (id.equals(JRDesignPiePlot.PROPERTY_SHOW_LABELS))
			jrElement.setShowLabels((Boolean) value);
		else if (id.equals(JRDesignPiePlot.PROPERTY_CIRCULAR))
			jrElement.setCircular((Boolean) value);
		else if (id.equals(JRDesignPiePlot.PROPERTY_LEGEND_LABEL_FORMAT))
			jrElement.setLegendLabelFormat((String) value);
		else if (id.equals(JRDesignPiePlot.PROPERTY_LABEL_FORMAT))
			jrElement.setLabelFormat((String) value);
		else
			super.setPropertyValue(id, value);
	}
}
