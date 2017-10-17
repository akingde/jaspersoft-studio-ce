/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.series.category;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.design.JRDesignCategorySeries;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRHyperlink;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignHyperlink;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.chart.ChartNodeIconDescriptor;
import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.model.MHyperLink;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.property.descriptor.JRPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class MCategorySeries extends APropertyNode {
	
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
			iconDescriptor = new ChartNodeIconDescriptor("categoryseries"); //$NON-NLS-1$
		return iconDescriptor;
	}

	public MCategorySeries() {
		super();
	}

	public MCategorySeries(ANode parent, JRDesignCategorySeries value,
			int newIndex) {
		super(parent, -1);
		setValue(value);
	}

	@Override
	public JRDesignCategorySeries getValue() {
		return (JRDesignCategorySeries) super.getValue();
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

		JRExpressionPropertyDescriptor catExpD = new JRExpressionPropertyDescriptor(
				JRDesignCategorySeries.PROPERTY_CATEGORY_EXPRESSION,
				Messages.MCategorySeries_category_expression);
		catExpD.setDescription(Messages.MCategorySeries_category_expression_description);
		desc.add(catExpD);

		JRExpressionPropertyDescriptor lblExprD = new JRExpressionPropertyDescriptor(
				JRDesignCategorySeries.PROPERTY_LABEL_EXPRESSION,
				Messages.common_label_expression);
		lblExprD.setDescription(Messages.MCategorySeries_label_expression_description);
		desc.add(lblExprD);

		JRExpressionPropertyDescriptor seriesExprD = new JRExpressionPropertyDescriptor(
				JRDesignCategorySeries.PROPERTY_SERIES_EXPRESSION,
				Messages.common_series_expression);
		seriesExprD
				.setDescription(Messages.MCategorySeries_series_expression_description);
		desc.add(seriesExprD);

		JRExpressionPropertyDescriptor valExprD = new JRExpressionPropertyDescriptor(
				JRDesignCategorySeries.PROPERTY_VALUE_EXPRESSION,
				Messages.common_value_expression);
		valExprD.setDescription(Messages.MCategorySeries_value_expression_description);
		desc.add(valExprD);

		JRPropertyDescriptor itemHyperLinkD = new JRPropertyDescriptor(
				JRDesignCategorySeries.PROPERTY_ITEM_HYPERLINK,
				Messages.common_item_hyperlink);
		itemHyperLinkD
				.setDescription(Messages.MCategorySeries_item_hyperlink_description);
		desc.add(itemHyperLinkD);
	}
	
	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();
		defaultsMap.put(JRDesignCategorySeries.PROPERTY_CATEGORY_EXPRESSION, new DefaultValue(null, true));
		defaultsMap.put(JRDesignCategorySeries.PROPERTY_LABEL_EXPRESSION, new DefaultValue(null, true));
		defaultsMap.put(JRDesignCategorySeries.PROPERTY_SERIES_EXPRESSION, new DefaultValue(null, true));
		defaultsMap.put(JRDesignCategorySeries.PROPERTY_VALUE_EXPRESSION, new DefaultValue(null, true));
		defaultsMap.put(JRDesignCategorySeries.PROPERTY_ITEM_HYPERLINK, new DefaultValue(null, true));
		
		return defaultsMap;
	}

	public Object getPropertyValue(Object id) {
		JRDesignCategorySeries jrElement = (JRDesignCategorySeries) getValue();

		if (id.equals(JRDesignCategorySeries.PROPERTY_ITEM_HYPERLINK)) {
			JRHyperlink itemHyperLink = jrElement.getItemHyperlink();
			if (itemHyperLink == null)
				itemHyperLink = new JRDesignHyperlink();
			mHyperLink = new MHyperLink(itemHyperLink);
			setChildListener(mHyperLink);
			return mHyperLink;
		}
		if (id.equals(JRDesignCategorySeries.PROPERTY_CATEGORY_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getCategoryExpression());
		if (id.equals(JRDesignCategorySeries.PROPERTY_LABEL_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getLabelExpression());
		if (id.equals(JRDesignCategorySeries.PROPERTY_SERIES_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getSeriesExpression());
		if (id.equals(JRDesignCategorySeries.PROPERTY_VALUE_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getValueExpression());

		return null;
	}

	public void setPropertyValue(Object id, Object value) {
		JRDesignCategorySeries jrElement = (JRDesignCategorySeries) getValue();

		if (id.equals(JRDesignCategorySeries.PROPERTY_CATEGORY_EXPRESSION))
			jrElement.setCategoryExpression(ExprUtil.setValues(
					jrElement.getCategoryExpression(), value));
		else if (id.equals(JRDesignCategorySeries.PROPERTY_LABEL_EXPRESSION))
			jrElement.setLabelExpression(ExprUtil.setValues(
					jrElement.getLabelExpression(), value));
		else if (id.equals(JRDesignCategorySeries.PROPERTY_SERIES_EXPRESSION))
			jrElement.setSeriesExpression(ExprUtil.setValues(
					jrElement.getSeriesExpression(), value));
		else if (id.equals(JRDesignCategorySeries.PROPERTY_VALUE_EXPRESSION))
			jrElement.setValueExpression(ExprUtil.setValues(
					jrElement.getValueExpression(), value));
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

	@Override
	public ExpressionContext getExpressionContext() {
		JRDesignDataset dataSet = ModelUtils.getDataset(this);
		JasperReportsConfiguration conf = getJasperConfiguration();
		if (dataSet != null && conf != null)
			return new ExpressionContext(dataSet, conf);
		return null;
	}

	@Override
	public Object getAdapter(Class adapter) {
		if (ExpressionContext.class.equals(adapter)) {
			return getExpressionContext();
		}
		return super.getAdapter(adapter);
	}
}
