/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.series.timeperiod;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.design.JRDesignTimePeriodSeries;
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

public class MTimePeriodSeries extends APropertyNode {
	
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
			iconDescriptor = new ChartNodeIconDescriptor("timeperiodseries"); //$NON-NLS-1$
		return iconDescriptor;
	}

	public MTimePeriodSeries() {
		super();
	}

	public MTimePeriodSeries(ANode parent, JRDesignTimePeriodSeries value,
			int newIndex) {
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

		JRExpressionPropertyDescriptor startDateExpD = new JRExpressionPropertyDescriptor(
				JRDesignTimePeriodSeries.PROPERTY_START_DATE_EXPRESSION,
				Messages.common_start_date_expression);
		startDateExpD
				.setDescription(Messages.MTimePeriodSeries_start_date_expression_description);
		desc.add(startDateExpD);

		JRExpressionPropertyDescriptor endDateExpD = new JRExpressionPropertyDescriptor(
				JRDesignTimePeriodSeries.PROPERTY_END_DATE_EXPRESSION,
				Messages.common_end_date_expression);
		endDateExpD
				.setDescription(Messages.MTimePeriodSeries_end_date_expression_description);
		desc.add(endDateExpD);

		JRExpressionPropertyDescriptor lblExprD = new JRExpressionPropertyDescriptor(
				JRDesignTimePeriodSeries.PROPERTY_LABEL_EXPRESSION,
				Messages.common_label_expression);
		lblExprD.setDescription(Messages.MTimePeriodSeries_label_expression_description);
		desc.add(lblExprD);

		JRExpressionPropertyDescriptor seriesExprD = new JRExpressionPropertyDescriptor(
				JRDesignTimePeriodSeries.PROPERTY_SERIES_EXPRESSION,
				Messages.common_series_expression);
		seriesExprD
				.setDescription(Messages.MTimePeriodSeries_series_expression_description);
		desc.add(seriesExprD);

		JRExpressionPropertyDescriptor valExprD = new JRExpressionPropertyDescriptor(
				JRDesignTimePeriodSeries.PROPERTY_VALUE_EXPRESSION,
				Messages.common_value_expression);
		valExprD.setDescription(Messages.MTimePeriodSeries_value_expression_description);
		desc.add(valExprD);

		JRPropertyDescriptor itemHyperLinkD = new JRPropertyDescriptor(
				JRDesignTimePeriodSeries.PROPERTY_ITEM_HYPERLINK,
				Messages.common_item_hyperlink);
		itemHyperLinkD
				.setDescription(Messages.MTimePeriodSeries_item_hyperlink_description);
		desc.add(itemHyperLinkD);
	}
	
	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();
		
		defaultsMap.put(JRDesignTimePeriodSeries.PROPERTY_START_DATE_EXPRESSION, new DefaultValue(null, true));
		defaultsMap.put(JRDesignTimePeriodSeries.PROPERTY_END_DATE_EXPRESSION, new DefaultValue(null, true));
		defaultsMap.put(JRDesignTimePeriodSeries.PROPERTY_LABEL_EXPRESSION, new DefaultValue(null, true));
		defaultsMap.put(JRDesignTimePeriodSeries.PROPERTY_SERIES_EXPRESSION, new DefaultValue(null, true));
		defaultsMap.put(JRDesignTimePeriodSeries.PROPERTY_VALUE_EXPRESSION, new DefaultValue(null, true));
		defaultsMap.put(JRDesignTimePeriodSeries.PROPERTY_ITEM_HYPERLINK, new DefaultValue(null, true));
		
		return defaultsMap;
	}

	public Object getPropertyValue(Object id) {
		JRDesignTimePeriodSeries jrElement = (JRDesignTimePeriodSeries) getValue();

		if (id.equals(JRDesignTimePeriodSeries.PROPERTY_ITEM_HYPERLINK)) {
			JRHyperlink itemHyperLink = jrElement.getItemHyperlink();
			if (itemHyperLink == null)
				itemHyperLink = new JRDesignHyperlink();
			mHyperLink = new MHyperLink(itemHyperLink);
			setChildListener(mHyperLink);
			return mHyperLink;
		}
		if (id.equals(JRDesignTimePeriodSeries.PROPERTY_START_DATE_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getStartDateExpression());
		if (id.equals(JRDesignTimePeriodSeries.PROPERTY_END_DATE_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getEndDateExpression());
		if (id.equals(JRDesignTimePeriodSeries.PROPERTY_LABEL_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getLabelExpression());
		if (id.equals(JRDesignTimePeriodSeries.PROPERTY_SERIES_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getSeriesExpression());
		if (id.equals(JRDesignTimePeriodSeries.PROPERTY_VALUE_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getValueExpression());

		return null;
	}

	public void setPropertyValue(Object id, Object value) {
		JRDesignTimePeriodSeries jrElement = (JRDesignTimePeriodSeries) getValue();

		if (id.equals(JRDesignTimePeriodSeries.PROPERTY_START_DATE_EXPRESSION))
			jrElement.setStartDateExpression(ExprUtil.setValues(
					jrElement.getStartDateExpression(), value));
		else if (id
				.equals(JRDesignTimePeriodSeries.PROPERTY_END_DATE_EXPRESSION))
			jrElement.setEndDateExpression(ExprUtil.setValues(
					jrElement.getEndDateExpression(), value));
		else if (id.equals(JRDesignTimePeriodSeries.PROPERTY_LABEL_EXPRESSION))
			jrElement.setLabelExpression(ExprUtil.setValues(
					jrElement.getLabelExpression(), value));
		else if (id.equals(JRDesignTimePeriodSeries.PROPERTY_SERIES_EXPRESSION))
			jrElement.setSeriesExpression(ExprUtil.setValues(
					jrElement.getSeriesExpression(), value));
		else if (id.equals(JRDesignTimePeriodSeries.PROPERTY_VALUE_EXPRESSION))
			jrElement.setValueExpression(ExprUtil.setValues(
					jrElement.getValueExpression(), value));
	}

	@Override
	public JRDesignTimePeriodSeries getValue() {
		return (JRDesignTimePeriodSeries) super.getValue();
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
