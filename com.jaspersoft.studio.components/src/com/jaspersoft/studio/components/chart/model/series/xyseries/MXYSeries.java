/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.series.xyseries;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.design.JRDesignXySeries;
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
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;

public class MXYSeries extends APropertyNode {
	
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
			iconDescriptor = new ChartNodeIconDescriptor("xyseries"); //$NON-NLS-1$
		return iconDescriptor;
	}

	public MXYSeries() {
		super();
	}

	public MXYSeries(ANode parent, JRDesignXySeries value, int newIndex) {
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

		JRExpressionPropertyDescriptor xValueExpD = new JRExpressionPropertyDescriptor(
				JRDesignXySeries.PROPERTY_X_VALUE_EXPRESSION,
				Messages.common_x_value_expression);
		xValueExpD
				.setDescription(Messages.MXYSeries_x_value_expression_description);
		desc.add(xValueExpD);

		JRExpressionPropertyDescriptor yValueExpD = new JRExpressionPropertyDescriptor(
				JRDesignXySeries.PROPERTY_Y_VALUE_EXPRESSION,
				Messages.common_y_value_expression);
		yValueExpD
				.setDescription(Messages.MXYSeries_y_value_expression_description);
		desc.add(yValueExpD);

		JRExpressionPropertyDescriptor lblExprD = new JRExpressionPropertyDescriptor(
				JRDesignXySeries.PROPERTY_LABEL_EXPRESSION,
				Messages.common_label_expression);
		lblExprD.setDescription(Messages.MXYSeries_label_expression_description);
		desc.add(lblExprD);

		JRExpressionPropertyDescriptor seriesExprD = new JRExpressionPropertyDescriptor(
				JRDesignXySeries.PROPERTY_SERIES_EXPRESSION,
				Messages.common_series_expression);
		seriesExprD
				.setDescription(Messages.MXYSeries_series_expression_description);
		desc.add(seriesExprD);

		JRPropertyDescriptor itemHyperLinkD = new JRPropertyDescriptor(
				JRDesignXySeries.PROPERTY_ITEM_HYPERLINK,
				Messages.common_item_hyperlink);
		itemHyperLinkD
				.setDescription(Messages.MXYSeries_item_hyperlink_description);
		desc.add(itemHyperLinkD);

		CheckBoxPropertyDescriptor printRVAlueD = new CheckBoxPropertyDescriptor(
				JRDesignXySeries.PROPERTY_AUTO_SORT,
				Messages.MXYSeries_autoSortTitle);
		printRVAlueD.setDescription(Messages.MXYSeries_autoSortDescription);
		desc.add(printRVAlueD);
	}

	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();
		
		defaultsMap.put(JRDesignXySeries.PROPERTY_AUTO_SORT, new DefaultValue(Boolean.TRUE, false));
		defaultsMap.put(JRDesignXySeries.PROPERTY_X_VALUE_EXPRESSION, new DefaultValue(null, true));
		defaultsMap.put(JRDesignXySeries.PROPERTY_Y_VALUE_EXPRESSION, new DefaultValue(null, true));
		defaultsMap.put(JRDesignXySeries.PROPERTY_LABEL_EXPRESSION, new DefaultValue(null, true));
		defaultsMap.put(JRDesignXySeries.PROPERTY_SERIES_EXPRESSION, new DefaultValue(null, true));
		defaultsMap.put(JRDesignXySeries.PROPERTY_ITEM_HYPERLINK, new DefaultValue(null, true));
		
		return defaultsMap;
	}

	public Object getPropertyValue(Object id) {
		JRDesignXySeries jrElement = (JRDesignXySeries) getValue();

		if (id.equals(JRDesignXySeries.PROPERTY_ITEM_HYPERLINK)) {
			JRHyperlink itemHyperLink = jrElement.getItemHyperlink();
			if (itemHyperLink == null)
				itemHyperLink = new JRDesignHyperlink();
			mHyperLink = new MHyperLink(itemHyperLink);
			setChildListener(mHyperLink);
			return mHyperLink;
		}
		if (id.equals(JRDesignXySeries.PROPERTY_AUTO_SORT))
			return jrElement.getAutoSort() == null ? true : jrElement
					.getAutoSort();
		if (id.equals(JRDesignXySeries.PROPERTY_X_VALUE_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getXValueExpression());
		if (id.equals(JRDesignXySeries.PROPERTY_Y_VALUE_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getYValueExpression());
		if (id.equals(JRDesignXySeries.PROPERTY_LABEL_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getLabelExpression());
		if (id.equals(JRDesignXySeries.PROPERTY_SERIES_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getSeriesExpression());
		return null;
	}

	public void setPropertyValue(Object id, Object value) {
		JRDesignXySeries jrElement = (JRDesignXySeries) getValue();

		if (id.equals(JRDesignXySeries.PROPERTY_AUTO_SORT))
			jrElement.setAutoSort((Boolean) value);
		else if (id.equals(JRDesignXySeries.PROPERTY_X_VALUE_EXPRESSION))
			jrElement.setXValueExpression(ExprUtil.setValues(
					jrElement.getXValueExpression(), value));
		else if (id.equals(JRDesignXySeries.PROPERTY_Y_VALUE_EXPRESSION))
			jrElement.setYValueExpression(ExprUtil.setValues(
					jrElement.getYValueExpression(), value));
		else if (id.equals(JRDesignXySeries.PROPERTY_LABEL_EXPRESSION))
			jrElement.setLabelExpression(ExprUtil.setValues(
					jrElement.getLabelExpression(), value));
		else if (id.equals(JRDesignXySeries.PROPERTY_SERIES_EXPRESSION))
			jrElement.setSeriesExpression(ExprUtil.setValues(
					jrElement.getSeriesExpression(), value));
	}

	@Override
	public JRDesignXySeries getValue() {
		return (JRDesignXySeries) super.getValue();
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
