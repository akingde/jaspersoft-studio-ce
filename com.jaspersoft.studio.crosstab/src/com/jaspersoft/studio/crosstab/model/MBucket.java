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
package com.jaspersoft.studio.crosstab.model;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.crosstabs.JRCrosstabBucket;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabBucket;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.type.SortOrderEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;

public class MBucket extends APropertyNode {

	/**
	 * Instantiates a new m field.
	 * 
	 * @param parent
	 *          the parent
	 * @param jfRield
	 *          the jf rield
	 * @param newIndex
	 *          the new index
	 */
	public MBucket(JRCrosstabBucket jfRield) {
		super();
		setValue(jfRield);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		return Messages.MBucket_bucket;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getImagePath()
	 */
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getToolTip()
	 */
	@Override
	public String getToolTip() {
		return getIconDescriptor().getToolTip();
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
		ComboBoxPropertyDescriptor orderD = new ComboBoxPropertyDescriptor(JRDesignCrosstabBucket.PROPERTY_ORDER,
				Messages.MBucket_order, EnumHelper.getEnumNames(SortOrderEnum.values(), NullEnum.NOTNULL));
		orderD.setDescription(Messages.MBucket_order_description);
		desc.add(orderD);

		JRExpressionPropertyDescriptor orderByExprD = new JRExpressionPropertyDescriptor(
				JRDesignCrosstabBucket.PROPERTY_ORDER_BY_EXPRESSION, Messages.MBucket_order_by_expression);
		orderByExprD.setDescription(Messages.MBucket_order_by_expression_description);
		desc.add(orderByExprD);

		JRExpressionPropertyDescriptor compExprD = new JRExpressionPropertyDescriptor(
				JRDesignCrosstabBucket.PROPERTY_COMPARATOR_EXPRESSION, Messages.MBucket_comparator_expression);
		compExprD.setDescription(Messages.MBucket_comparator_expression_description);
		desc.add(compExprD);

		JRExpressionPropertyDescriptor exprD = new JRExpressionPropertyDescriptor(
				JRDesignCrosstabBucket.PROPERTY_EXPRESSION, Messages.MBucket_expression);
		exprD.setDescription(Messages.MBucket_expression_description);
		desc.add(exprD);
	}

	private MExpression oExpression;
	private MExpression cExpression;
	private MExpression eExpression;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignCrosstabBucket jrField = (JRDesignCrosstabBucket) getValue();
		if (id.equals(JRDesignCrosstabBucket.PROPERTY_ORDER))
			return EnumHelper.getValue(jrField.getOrderValue(), 1, false);
		if (id.equals(JRDesignCrosstabBucket.PROPERTY_COMPARATOR_EXPRESSION)) {
			if (cExpression == null) {
				cExpression = new MExpression(jrField.getComparatorExpression());
				setChildListener(cExpression);
			}
			return cExpression;
		}
		if (id.equals(JRDesignCrosstabBucket.PROPERTY_ORDER_BY_EXPRESSION)) {
			if (oExpression == null) {
				oExpression = new MExpression(jrField.getOrderByExpression());
				setChildListener(oExpression);
			}
			return oExpression;
		}
		if (id.equals(JRDesignCrosstabBucket.PROPERTY_EXPRESSION)) {
			if (eExpression == null) {
				eExpression = new MExpression(jrField.getExpression());
				setChildListener(eExpression);
			}
			return eExpression;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignCrosstabBucket jrField = (JRDesignCrosstabBucket) getValue();

		if (id.equals(JRDesignCrosstabBucket.PROPERTY_ORDER))
			jrField.setOrder((SortOrderEnum) EnumHelper.getSetValue(SortOrderEnum.values(), value, 1, false));
		else if (id.equals(JRDesignCrosstabBucket.PROPERTY_COMPARATOR_EXPRESSION)) {
			if (value instanceof MExpression) {
				cExpression = (MExpression) value;
				JRExpression expression = (JRExpression) cExpression.getValue();
				jrField.setComparatorExpression(expression);
			}
		} else if (id.equals(JRDesignCrosstabBucket.PROPERTY_ORDER_BY_EXPRESSION)) {
			if (value instanceof MExpression) {
				oExpression = (MExpression) value;
				JRExpression expression = (JRExpression) oExpression.getValue();
				jrField.setComparatorExpression(expression);
			}
		} else if (id.equals(JRDesignCrosstabBucket.PROPERTY_EXPRESSION)) {
			if (value instanceof MExpression) {
				eExpression = (MExpression) value;
				JRExpression expression = (JRExpression) eExpression.getValue();
				jrField.setExpression((JRDesignExpression) expression);
			}
		}
	}
}
