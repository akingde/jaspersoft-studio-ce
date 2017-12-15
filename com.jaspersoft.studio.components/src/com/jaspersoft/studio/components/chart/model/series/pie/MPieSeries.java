/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.series.pie;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.design.JRDesignPieSeries;
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

public class MPieSeries extends APropertyNode {
	
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
			iconDescriptor = new ChartNodeIconDescriptor("pieseries"); //$NON-NLS-1$
		return iconDescriptor;
	}

	public MPieSeries() {
		super();
	}

	public MPieSeries(ANode parent, JRDesignPieSeries value, int newIndex) {
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

		JRExpressionPropertyDescriptor keyExpD = new JRExpressionPropertyDescriptor(
				JRDesignPieSeries.PROPERTY_KEY_EXPRESSION,
				Messages.common_key_expression);
		keyExpD.setDescription(Messages.MPieSeries_key_expression_description);
		desc.add(keyExpD);

		JRExpressionPropertyDescriptor lblExprD = new JRExpressionPropertyDescriptor(
				JRDesignPieSeries.PROPERTY_LABEL_EXPRESSION,
				Messages.common_label_expression);
		lblExprD.setDescription(Messages.MPieSeries_label_expression_description);
		desc.add(lblExprD);

		JRExpressionPropertyDescriptor valExprD = new JRExpressionPropertyDescriptor(
				JRDesignPieSeries.PROPERTY_VALUE_EXPRESSION,
				Messages.common_value_expression);
		valExprD.setDescription(Messages.MPieSeries_value_expression_description);
		desc.add(valExprD);

		JRPropertyDescriptor sectionHyperLinkD = new JRPropertyDescriptor(
				JRDesignPieSeries.PROPERTY_SECTION_HYPERLINK,
				Messages.common_section_hyperlink);
		sectionHyperLinkD
				.setDescription(Messages.MPieSeries_section_hyperlink_description);
		desc.add(sectionHyperLinkD);
	}
	
	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();
		
		defaultsMap.put(JRDesignPieSeries.PROPERTY_KEY_EXPRESSION, new DefaultValue(null, true));
		defaultsMap.put(JRDesignPieSeries.PROPERTY_LABEL_EXPRESSION, new DefaultValue(null, true));
		defaultsMap.put(JRDesignPieSeries.PROPERTY_VALUE_EXPRESSION, new DefaultValue(null, true));
		defaultsMap.put(JRDesignPieSeries.PROPERTY_SECTION_HYPERLINK, new DefaultValue(null, true));
		
		return defaultsMap;
	}

	public Object getPropertyValue(Object id) {
		JRDesignPieSeries jrElement = getValue();

		if (id.equals(JRDesignPieSeries.PROPERTY_SECTION_HYPERLINK)) {
			JRHyperlink itemHyperLink = jrElement.getSectionHyperlink();
			if (itemHyperLink == null)
				itemHyperLink = new JRDesignHyperlink();
			mHyperLink = new MHyperLink(itemHyperLink);
			setChildListener(mHyperLink);
			return mHyperLink;
		}
		if (id.equals(JRDesignPieSeries.PROPERTY_KEY_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getKeyExpression());
		if (id.equals(JRDesignPieSeries.PROPERTY_LABEL_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getLabelExpression());
		if (id.equals(JRDesignPieSeries.PROPERTY_VALUE_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getValueExpression());
		return null;
	}

	public void setPropertyValue(Object id, Object value) {
		JRDesignPieSeries jrElement = getValue();

		if (id.equals(JRDesignPieSeries.PROPERTY_KEY_EXPRESSION))
			jrElement.setKeyExpression(ExprUtil.setValues(
					jrElement.getKeyExpression(), value));
		else if (id.equals(JRDesignPieSeries.PROPERTY_LABEL_EXPRESSION))
			jrElement.setLabelExpression(ExprUtil.setValues(
					jrElement.getKeyExpression(), value));
		else if (id.equals(JRDesignPieSeries.PROPERTY_VALUE_EXPRESSION))
			jrElement.setValueExpression(ExprUtil.setValues(
					jrElement.getValueExpression(), value));
	}

	@Override
	public JRDesignPieSeries getValue() {
		return (JRDesignPieSeries) super.getValue();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource() instanceof JRDesignHyperlink) {
			JRHyperlink hl = (JRHyperlink) evt.getSource();
			if (getValue().getSectionHyperlink() == null)
				getValue().setSectionHyperlink(hl);
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
