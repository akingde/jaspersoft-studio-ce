/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.series.time;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.design.JRDesignTimeSeries;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRHyperlink;
import net.sf.jasperreports.engine.design.JRDesignHyperlink;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.chart.ChartNodeIconDescriptor;
import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.model.MHyperLink;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.property.descriptor.JRPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;

public class MTimeSeries extends APropertyNode {
	
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	private static IPropertyDescriptor[] descriptors;
	
	private MHyperLink mHyperLink;
	
	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new ChartNodeIconDescriptor("timeseries"); //$NON-NLS-1$
		return iconDescriptor;
	}

	public MTimeSeries() {
		super();
	}

	public MTimeSeries(ANode parent, JRDesignTimeSeries value, int newIndex) {
		super(parent, -1);
		setValue(value);
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors = descriptors1;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *            the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {

		JRExpressionPropertyDescriptor timePeriodD = new JRExpressionPropertyDescriptor(
				JRDesignTimeSeries.PROPERTY_TIME_PERIOD_EXPRESSION,
				Messages.MTimeSeries_time_period_expression);
		timePeriodD
				.setDescription(Messages.MTimeSeries_time_period_expression_description);
		desc.add(timePeriodD);

		JRExpressionPropertyDescriptor lblExprD = new JRExpressionPropertyDescriptor(
				JRDesignTimeSeries.PROPERTY_LABEL_EXPRESSION,
				Messages.common_label_expression);
		lblExprD.setDescription(Messages.MTimeSeries_label_expression_description);
		desc.add(lblExprD);

		JRExpressionPropertyDescriptor seriesExprD = new JRExpressionPropertyDescriptor(
				JRDesignTimeSeries.PROPERTY_SERIES_EXPRESSION,
				Messages.common_series_expression);
		seriesExprD
				.setDescription(Messages.MTimeSeries_series_expression_description);
		desc.add(seriesExprD);

		JRExpressionPropertyDescriptor valExprD = new JRExpressionPropertyDescriptor(
				JRDesignTimeSeries.PROPERTY_VALUE_EXPRESSION,
				Messages.common_value_expression);
		valExprD.setDescription(Messages.MTimeSeries_value_expression_description);
		desc.add(valExprD);

		JRPropertyDescriptor itemHyperLinkD = new JRPropertyDescriptor(
				JRDesignTimeSeries.PROPERTY_ITEM_HYPERLINK,
				Messages.common_item_hyperlink);
		itemHyperLinkD
				.setDescription(Messages.MTimeSeries_item_hyperlink_description);
		desc.add(itemHyperLinkD);
	}
	
	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();
		
		defaultsMap.put(JRDesignTimeSeries.PROPERTY_TIME_PERIOD_EXPRESSION, new DefaultValue(null, true));
		defaultsMap.put(JRDesignTimeSeries.PROPERTY_LABEL_EXPRESSION, new DefaultValue(null, true));
		defaultsMap.put(JRDesignTimeSeries.PROPERTY_SERIES_EXPRESSION, new DefaultValue(null, true));
		defaultsMap.put(JRDesignTimeSeries.PROPERTY_VALUE_EXPRESSION, new DefaultValue(null, true));
		defaultsMap.put(JRDesignTimeSeries.PROPERTY_ITEM_HYPERLINK, new DefaultValue(null, true));
		
		return defaultsMap;
	}

	public Object getPropertyValue(Object id) {
		JRDesignTimeSeries jrElement = (JRDesignTimeSeries) getValue();

		if (id.equals(JRDesignTimeSeries.PROPERTY_ITEM_HYPERLINK)) {
			JRHyperlink itemHyperLink = jrElement.getItemHyperlink();
			if (itemHyperLink == null)
				itemHyperLink = new JRDesignHyperlink();
			mHyperLink = new MHyperLink(itemHyperLink);
			setChildListener(mHyperLink);
			return mHyperLink;
		}
		if (id.equals(JRDesignTimeSeries.PROPERTY_TIME_PERIOD_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getTimePeriodExpression());
		if (id.equals(JRDesignTimeSeries.PROPERTY_LABEL_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getLabelExpression());
		if (id.equals(JRDesignTimeSeries.PROPERTY_SERIES_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getSeriesExpression());
		if (id.equals(JRDesignTimeSeries.PROPERTY_VALUE_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getValueExpression());

		return null;
	}

	public void setPropertyValue(Object id, Object value) {
		JRDesignTimeSeries jrElement = (JRDesignTimeSeries) getValue();

		if (id.equals(JRDesignTimeSeries.PROPERTY_TIME_PERIOD_EXPRESSION))
			jrElement.setTimePeriodExpression(ExprUtil.setValues(
					jrElement.getTimePeriodExpression(), value));
		else if (id.equals(JRDesignTimeSeries.PROPERTY_LABEL_EXPRESSION))
			jrElement.setLabelExpression(ExprUtil.setValues(
					jrElement.getLabelExpression(), value));
		else if (id.equals(JRDesignTimeSeries.PROPERTY_SERIES_EXPRESSION))
			jrElement.setSeriesExpression(ExprUtil.setValues(
					jrElement.getSeriesExpression(), value));
		else if (id.equals(JRDesignTimeSeries.PROPERTY_VALUE_EXPRESSION))
			jrElement.setValueExpression(ExprUtil.setValues(
					jrElement.getValueExpression(), value));
	}

	@Override
	public JRDesignTimeSeries getValue() { 
		return (JRDesignTimeSeries) super.getValue();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource() instanceof JRDesignHyperlink) {
			JRHyperlink hl = (JRHyperlink) evt.getSource();
			if (getValue().getItemHyperlink() == null)
				getValue().setItemHyperlink(hl);
		}
		super.propertyChange(evt);
	}

	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	public String getDisplayText() {
		return getIconDescriptor().getTitle();
	}

}
