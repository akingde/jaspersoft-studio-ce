/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.plot;

import java.util.List;
import java.util.Map;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.components.chart.model.MChartItemLabel;
import com.jaspersoft.studio.components.chart.property.descriptor.PlotPropertyDescriptor;
import com.jaspersoft.studio.help.HelpReferenceBuilder;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.DoublePropertyDescriptor;

import net.sf.jasperreports.charts.JRPie3DPlot;
import net.sf.jasperreports.charts.design.JRDesignPie3DPlot;
import net.sf.jasperreports.charts.design.JRDesignPiePlot;
import net.sf.jasperreports.engine.JRConstants;

public class MPie3DPlot extends MChartPlot {
	
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	private MChartItemLabel itemLabelModel;
	
	private static IPropertyDescriptor[] descriptors;

	public MPie3DPlot(JRPie3DPlot value) {
		super(value);
	}

	@Override
	public String getDisplayText() {
		return Messages.MPie3DPlot_pie3d_plot;
	}

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
		super.createPropertyDescriptors(desc);

		PlotPropertyDescriptor itemLabelD = new PlotPropertyDescriptor(
				JRDesignPie3DPlot.PROPERTY_ITEM_LABEL,
				Messages.common_item_label);
		itemLabelD.setDescription(Messages.MPie3DPlot_item_label_description);
		desc.add(itemLabelD);
		itemLabelD
				.setHelpRefBuilder(new HelpReferenceBuilder(
						"net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#itemLabel"));

		CheckBoxPropertyDescriptor showLabelsD = new CheckBoxPropertyDescriptor(
				JRDesignPie3DPlot.PROPERTY_SHOW_LABELS,
				Messages.common_show_labels, NullEnum.NULL);
		showLabelsD.setDescription(Messages.MPie3DPlot_show_labels_description);
		desc.add(showLabelsD);

		CheckBoxPropertyDescriptor circularD = new CheckBoxPropertyDescriptor(
				JRDesignPie3DPlot.PROPERTY_CIRCULAR, Messages.common_circular,
				NullEnum.NULL);
		circularD.setDescription(Messages.MPie3DPlot_circular_description);
		desc.add(circularD);

		NTextPropertyDescriptor legendLabelFormatD = new NTextPropertyDescriptor(
				JRDesignPie3DPlot.PROPERTY_LEGEND_LABEL_FORMAT,
				Messages.common_legend_label_format);
		legendLabelFormatD
				.setDescription(Messages.MPie3DPlot_legend_label_format_description);
		desc.add(legendLabelFormatD);

		NTextPropertyDescriptor labelFormatD = new NTextPropertyDescriptor(
				JRDesignPie3DPlot.PROPERTY_LABEL_FORMAT,
				Messages.common_label_format);
		labelFormatD
				.setDescription(Messages.MPie3DPlot_label_format_description);
		desc.add(labelFormatD);

		DoublePropertyDescriptor depthFactorD = new DoublePropertyDescriptor(
				JRDesignPie3DPlot.PROPERTY_DEPTH_FACTOR,
				Messages.MPie3DPlot_depth_factor);
		depthFactorD
				.setDescription(Messages.MPie3DPlot_depth_factor_description);
		desc.add(depthFactorD);

		setHelpPrefix(desc,
				"net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#pie3DPlot");
	}
	
	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();
		
		defaultsMap.put(JRDesignPie3DPlot.PROPERTY_DEPTH_FACTOR, new DefaultValue(true));
		defaultsMap.putAll(((MChartItemLabel)getPropertyValue(JRDesignPiePlot.PROPERTY_ITEM_LABEL)).getDefaultsPropertiesMap());
		
		return defaultsMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java
	 * .lang.Object)
	 */
	@Override
	public Object getPropertyValue(Object id) {
		JRDesignPie3DPlot jrElement = (JRDesignPie3DPlot) getValue();
		if (itemLabelModel == null) {
			itemLabelModel = new MChartItemLabel(jrElement.getItemLabel());
			setChildListener(itemLabelModel);
		}
		if (id.equals(JRDesignPie3DPlot.PROPERTY_SHOW_LABELS))
			return jrElement.getShowLabels();
		if (id.equals(JRDesignPie3DPlot.PROPERTY_CIRCULAR))
			return jrElement.getCircular();
		if (id.equals(JRDesignPie3DPlot.PROPERTY_LEGEND_LABEL_FORMAT))
			return jrElement.getLegendLabelFormat();
		if (id.equals(JRDesignPie3DPlot.PROPERTY_LABEL_FORMAT))
			return jrElement.getLabelFormat();
		if (id.equals(JRDesignPie3DPlot.PROPERTY_DEPTH_FACTOR))
			return jrElement.getDepthFactorDouble();
		if (id.equals(JRDesignPiePlot.PROPERTY_ITEM_LABEL)) {
			return itemLabelModel;
		} else {
			Object value = itemLabelModel.getPropertyValue(id);
			if (value == null)
				value = super.getPropertyValue(id);
			return value;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java
	 * .lang.Object, java.lang.Object)
	 */
	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignPie3DPlot jrElement = (JRDesignPie3DPlot) getValue();
		if (id.equals(JRDesignPie3DPlot.PROPERTY_SHOW_LABELS))
			jrElement.setShowLabels((Boolean) value);
		else if (id.equals(JRDesignPie3DPlot.PROPERTY_CIRCULAR))
			jrElement.setCircular((Boolean) value);
		else if (id.equals(JRDesignPie3DPlot.PROPERTY_LEGEND_LABEL_FORMAT))
			jrElement.setLegendLabelFormat((String) value);
		else if (id.equals(JRDesignPie3DPlot.PROPERTY_LABEL_FORMAT))
			jrElement.setLabelFormat((String) value);
		else if (id.equals(JRDesignPie3DPlot.PROPERTY_DEPTH_FACTOR))
			jrElement.setDepthFactor((Double) value);
		else
			itemLabelModel.setPropertyValue(id, value);
		super.setPropertyValue(id, value);
	}
}
