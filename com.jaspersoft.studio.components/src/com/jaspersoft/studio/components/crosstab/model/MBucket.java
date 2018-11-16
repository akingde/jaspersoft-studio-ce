/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.classname.NClassTypePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;

import net.sf.jasperreports.crosstabs.JRCrosstabBucket;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabBucket;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.analytics.dataset.BucketOrder;
import net.sf.jasperreports.engine.design.JRDesignExpression;

public class MBucket extends APropertyNode {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	private static IPropertyDescriptor[] descriptors;

	private static NamedEnumPropertyDescriptor<BucketOrder> orderD;

	private APropertyNode bucketContainer;

	/**
	 * Instantiates a new bucked.
	 * 
	 * @param jfRield
	 *            the jf rield
	 * @param bucketContainer
	 *            the node in the model hierarchy from where the bucked was
	 *            created. It is essentially the parent and it is used to build
	 *            the expression context. But the bucked node will not be saw as
	 *            the child of its container
	 */
	public MBucket(JRCrosstabBucket jfRield, APropertyNode bucketContainer) {
		super();
		setValue(jfRield);
		this.bucketContainer = bucketContainer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		return Messages.common_bucket;
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
		orderD = new NamedEnumPropertyDescriptor<BucketOrder>(JRDesignCrosstabBucket.PROPERTY_ORDER,
				Messages.common_order, BucketOrder.ASCENDING, NullEnum.NOTNULL);
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

		NClassTypePropertyDescriptor classD = new NClassTypePropertyDescriptor(
				JRDesignCrosstabBucket.PROPERTY_VALUE_CLASS, Messages.MBucket_valueClassTitle);
		classD.setDescription(Messages.MBucket_valueClassDescription);
		desc.add(classD);
	}

	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();

		defaultsMap.put(JRDesignCrosstabBucket.PROPERTY_VALUE_CLASS, new DefaultValue(null, true));

		return defaultsMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java
	 * .lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignCrosstabBucket jrField = (JRDesignCrosstabBucket) getValue();
		if (id.equals(JRDesignCrosstabBucket.PROPERTY_ORDER))
			return orderD.getIntValue(jrField.getOrder());
		if (id.equals(JRDesignCrosstabBucket.PROPERTY_COMPARATOR_EXPRESSION))
			return ExprUtil.getExpression(jrField.getComparatorExpression());
		if (id.equals(JRDesignCrosstabBucket.PROPERTY_ORDER_BY_EXPRESSION))
			return ExprUtil.getExpression(jrField.getOrderByExpression());
		if (id.equals(JRDesignCrosstabBucket.PROPERTY_EXPRESSION))
			return ExprUtil.getExpression(jrField.getExpression());
		if (id.equals(JRDesignCrosstabBucket.PROPERTY_VALUE_CLASS))
			return jrField.getValueClassName();
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java
	 * .lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignCrosstabBucket jrField = (JRDesignCrosstabBucket) getValue();

		if (id.equals(JRDesignCrosstabBucket.PROPERTY_ORDER)) {
			jrField.setOrder(orderD.getEnumValue(value));
		} else if (id.equals(JRDesignCrosstabBucket.PROPERTY_COMPARATOR_EXPRESSION))
			jrField.setComparatorExpression(ExprUtil.setValues(jrField.getComparatorExpression(), value));
		else if (id.equals(JRDesignCrosstabBucket.PROPERTY_ORDER_BY_EXPRESSION))
			jrField.setOrderByExpression(ExprUtil.setValues(jrField.getOrderByExpression(), value));
		else if (id.equals(JRDesignCrosstabBucket.PROPERTY_EXPRESSION))
			jrField.setExpression((JRDesignExpression) ExprUtil.setValues(jrField.getExpression(), value));
		else if (id.equals(JRDesignCrosstabBucket.PROPERTY_VALUE_CLASS))
			jrField.setValueClassName((String) value);
	}

	// Code to provide the expression context to the descriptors

	@Override
	public ExpressionContext getExpressionContext() {
		return bucketContainer.getExpressionContext();
	}
	
	@Override
	public ExpressionContext getExpressionContext(String propertyID) {
		if(propertyID.equals(JRDesignCrosstabBucket.PROPERTY_ORDER_BY_EXPRESSION)) {
			// special handling for the order by expression in terms of context
			// see also "Bucket Comparator and Sort Order" paragraph in JR PDF doc
			MCrosstab mCrosstab = CrosstabUtil.getMCrosstab(bucketContainer);
			if(mCrosstab!=null) {
				return new ExpressionContext(mCrosstab.getValue(), bucketContainer.getJasperConfiguration());
			}			
		}
		return getExpressionContext();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter) {
		if (ExpressionContext.class.equals(adapter)) {
			return getExpressionContext();
		} else
			return super.getAdapter(adapter);
	}
}
