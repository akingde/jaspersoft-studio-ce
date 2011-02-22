/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of iReport.
 *
 * iReport is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * iReport is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with iReport. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.chart.model.series.category;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.design.JRDesignCategorySeries;
import net.sf.jasperreports.engine.JRHyperlink;
import net.sf.jasperreports.engine.design.JRDesignHyperlink;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.chart.ChartNodeIconDescriptor;
import com.jaspersoft.studio.chart.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.model.MHyperLink;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.property.descriptor.JRPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;

public class MCategorySeries extends APropertyNode {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

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

	public MCategorySeries(ANode parent, JRDesignCategorySeries value, int newIndex) {
		super(parent, -1);
		setValue(value);
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
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {

		JRExpressionPropertyDescriptor catExpD = new JRExpressionPropertyDescriptor(
				JRDesignCategorySeries.PROPERTY_CATEGORY_EXPRESSION, Messages.MCategorySeries_category_expression);
		catExpD.setDescription(Messages.MCategorySeries_category_expression_description);
		desc.add(catExpD);

		JRExpressionPropertyDescriptor lblExprD = new JRExpressionPropertyDescriptor(
				JRDesignCategorySeries.PROPERTY_LABEL_EXPRESSION, Messages.common_label_expression);
		lblExprD.setDescription(Messages.MCategorySeries_label_expression_description);
		desc.add(lblExprD);

		JRExpressionPropertyDescriptor seriesExprD = new JRExpressionPropertyDescriptor(
				JRDesignCategorySeries.PROPERTY_SERIES_EXPRESSION, Messages.common_series_expression);
		seriesExprD.setDescription(Messages.MCategorySeries_series_expression_description);
		desc.add(seriesExprD);

		JRExpressionPropertyDescriptor valExprD = new JRExpressionPropertyDescriptor(
				JRDesignCategorySeries.PROPERTY_VALUE_EXPRESSION, Messages.common_value_expression);
		valExprD.setDescription(Messages.MCategorySeries_value_expression_description);
		desc.add(valExprD);

		JRPropertyDescriptor itemHyperLinkD = new JRPropertyDescriptor(JRDesignCategorySeries.PROPERTY_ITEM_HYPERLINK,
				Messages.common_item_hyperlink);
		itemHyperLinkD.setDescription(Messages.MCategorySeries_item_hyperlink_description);
		desc.add(itemHyperLinkD);

		defaultsMap.put(JRDesignCategorySeries.PROPERTY_CATEGORY_EXPRESSION, null);
		defaultsMap.put(JRDesignCategorySeries.PROPERTY_LABEL_EXPRESSION, null);
		defaultsMap.put(JRDesignCategorySeries.PROPERTY_SERIES_EXPRESSION, null);
		defaultsMap.put(JRDesignCategorySeries.PROPERTY_VALUE_EXPRESSION, null);
		defaultsMap.put(JRDesignCategorySeries.PROPERTY_ITEM_HYPERLINK, null);

	}

	private MHyperLink mHyperLink;

	private MExpression dExpression;
	private MExpression hExpression;
	private MExpression lExpression;
	private MExpression oExpression;

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
		if (id.equals(JRDesignCategorySeries.PROPERTY_CATEGORY_EXPRESSION)) {
			if (dExpression == null) {
				dExpression = new MExpression(jrElement.getCategoryExpression());
				setChildListener(dExpression);
			}
			return dExpression;
		}
		if (id.equals(JRDesignCategorySeries.PROPERTY_LABEL_EXPRESSION)) {
			if (hExpression == null) {
				hExpression = new MExpression(jrElement.getLabelExpression());
				setChildListener(hExpression);
			}
			return hExpression;
		}
		if (id.equals(JRDesignCategorySeries.PROPERTY_SERIES_EXPRESSION)) {
			if (lExpression == null) {
				lExpression = new MExpression(jrElement.getSeriesExpression());
				setChildListener(lExpression);
			}
			return lExpression;
		}
		if (id.equals(JRDesignCategorySeries.PROPERTY_VALUE_EXPRESSION)) {
			if (oExpression == null) {
				oExpression = new MExpression(jrElement.getValueExpression());
				setChildListener(oExpression);
			}
			return oExpression;
		}

		return null;
	}

	public void setPropertyValue(Object id, Object value) {
		JRDesignCategorySeries jrElement = (JRDesignCategorySeries) getValue();

		if (id.equals(JRDesignCategorySeries.PROPERTY_CATEGORY_EXPRESSION))
			jrElement.setCategoryExpression(ExprUtil.setValues(jrElement.getCategoryExpression(), value));
		else if (id.equals(JRDesignCategorySeries.PROPERTY_LABEL_EXPRESSION))
			jrElement.setLabelExpression(ExprUtil.setValues(jrElement.getLabelExpression(), value));
		else if (id.equals(JRDesignCategorySeries.PROPERTY_SERIES_EXPRESSION))
			jrElement.setSeriesExpression(ExprUtil.setValues(jrElement.getSeriesExpression(), value));
		else if (id.equals(JRDesignCategorySeries.PROPERTY_VALUE_EXPRESSION))
			jrElement.setValueExpression(ExprUtil.setValues(jrElement.getValueExpression(), value));
	}

	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	public String getDisplayText() {
		return getIconDescriptor().getTitle();
	}

}