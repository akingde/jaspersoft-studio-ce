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

import net.sf.jasperreports.charts.JRPiePlot;
import net.sf.jasperreports.charts.design.JRDesignPiePlot;
import net.sf.jasperreports.engine.JRConstants;

public class MPiePlot extends MChartPlot {
	
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	private static IPropertyDescriptor[] descriptors;
	
	private MChartItemLabel itemLabelModel;

	public MPiePlot(JRPiePlot value) {
		super(value);
	}

	@Override
	public String getDisplayText() {
		return Messages.MPiePlot_pie_plot;
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
				JRDesignPiePlot.PROPERTY_ITEM_LABEL, Messages.common_item_label);
		itemLabelD.setDescription(Messages.MPiePlot_item_label_description);
		desc.add(itemLabelD);
		itemLabelD
				.setHelpRefBuilder(new HelpReferenceBuilder(
						"net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#itemLabel"));

		CheckBoxPropertyDescriptor showLabelsD = new CheckBoxPropertyDescriptor(
				JRDesignPiePlot.PROPERTY_SHOW_LABELS,
				Messages.common_show_labels, NullEnum.NULL);
		showLabelsD.setDescription(Messages.MPiePlot_show_labels_description);
		desc.add(showLabelsD);

		CheckBoxPropertyDescriptor circularD = new CheckBoxPropertyDescriptor(
				JRDesignPiePlot.PROPERTY_CIRCULAR, Messages.common_circular,
				NullEnum.NULL);
		circularD.setDescription(Messages.MPiePlot_circular_description);
		desc.add(circularD);

		NTextPropertyDescriptor legendLabelFormatD = new NTextPropertyDescriptor(
				JRDesignPiePlot.PROPERTY_LEGEND_LABEL_FORMAT,
				Messages.common_legend_label_format);
		legendLabelFormatD
				.setDescription(Messages.MPiePlot_legend_label_format_description);
		desc.add(legendLabelFormatD);

		NTextPropertyDescriptor labelFormatD = new NTextPropertyDescriptor(
				JRDesignPiePlot.PROPERTY_LABEL_FORMAT,
				Messages.common_label_format);
		labelFormatD.setDescription(Messages.MPiePlot_label_format_description);
		desc.add(labelFormatD);

		setHelpPrefix(desc,
				"net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#piePlot");
	}
	
	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();
		
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
		JRDesignPiePlot jrElement = (JRDesignPiePlot) getValue();
		if (itemLabelModel == null) {
			itemLabelModel = new MChartItemLabel(jrElement.getItemLabel());
			setChildListener(itemLabelModel);
		}
		if (id.equals(JRDesignPiePlot.PROPERTY_SHOW_LABELS))
			return jrElement.getShowLabels();
		if (id.equals(JRDesignPiePlot.PROPERTY_CIRCULAR))
			return jrElement.getCircular();
		if (id.equals(JRDesignPiePlot.PROPERTY_LEGEND_LABEL_FORMAT))
			return jrElement.getLegendLabelFormat();
		if (id.equals(JRDesignPiePlot.PROPERTY_LABEL_FORMAT))
			return jrElement.getLabelFormat();
		if (id.equals(JRDesignPiePlot.PROPERTY_ITEM_LABEL)) {
			return itemLabelModel;
		} else {
			Object value = itemLabelModel.getPropertyValue(id);
			if (value == null)
				value = super.getPropertyValue(id);
			return value;
		}
	}

	@Override
	public Object getPropertyActualValue(Object id) {
		JRDesignPiePlot jrElement = (JRDesignPiePlot) getValue();
		if (id.equals(JRDesignPiePlot.PROPERTY_SHOW_LABELS)) {
			Boolean value = jrElement.getShowLabels();
			return value != null ? value : true;
		}
		if (id.equals(JRDesignPiePlot.PROPERTY_CIRCULAR)) {
			Boolean value = jrElement.getCircular();
			return value != null ? value : true;
		}
		return super.getPropertyActualValue(id);
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
			itemLabelModel.setPropertyValue(id, value);
		super.setPropertyValue(id, value);
	}
}
