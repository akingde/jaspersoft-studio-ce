/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.html.model;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.html.HtmlNodeIconDescriptor;
import com.jaspersoft.studio.html.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.components.html.HtmlComponent;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;
import net.sf.jasperreports.engine.type.HorizontalImageAlignEnum;
import net.sf.jasperreports.engine.type.ScaleImageEnum;
import net.sf.jasperreports.engine.type.VerticalImageAlignEnum;

public class MHtml extends MGraphicElement {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private IPropertyDescriptor[] descriptors;
	private static IIconDescriptor iconDescriptor;
	private RComboBoxPropertyDescriptor evaluationGroupNameD;
	private static NamedEnumPropertyDescriptor<ScaleImageEnum> scaleTypeD;
	private static NamedEnumPropertyDescriptor<HorizontalImageAlignEnum> hAlignD;
	private static NamedEnumPropertyDescriptor<VerticalImageAlignEnum> vAlignD;
	private static NamedEnumPropertyDescriptor<EvaluationTimeEnum> evaluationTimeD;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new HtmlNodeIconDescriptor("html"); //$NON-NLS-1$
		return iconDescriptor;
	}

	public MHtml() {
		super();
	}

	public MHtml(ANode parent, int newIndex) {
		super(parent, newIndex);
	}

	public MHtml(ANode parent, JRDesignComponentElement jrHtml, int newIndex) {
		this(parent, newIndex);
		setValue(jrHtml);
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
	public JRDesignElement createJRElement(JasperDesign jasperDesign) {
		JRDesignComponentElement el = new JRDesignComponentElement(jasperDesign);
		HtmlComponent component = new HtmlComponent();
		JRDesignExpression exp = new JRDesignExpression();
		exp.setValueClassName("java.lang.String"); //$NON-NLS-1$
		exp.setText("\"<p style='background-color:yellow;'>HTML paragraph</p>\""); //$NON-NLS-1$
		component.setHtmlContentExpression(exp);
		el.setComponent(component);
		el.setComponentKey(new ComponentKey(
				"http://jasperreports.sourceforge.net/htmlcomponent", "hc", "html")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return el;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		String p = getElementNameProperty();
		return Misc.isNullOrEmpty(p) ? getIconDescriptor().getTitle() : p;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getImagePath()
	 */
	@Override
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getToolTip()
	 */
	@Override
	public String getToolTip() {
		return getIconDescriptor().getToolTip();
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *            the desc
	 */
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		super.createPropertyDescriptors(desc);

		JRExpressionPropertyDescriptor contentExprD = new JRExpressionPropertyDescriptor(
				HtmlComponent.PROPERTY_HTMLCONTENT_EXPRESSION,
				Messages.MHtml_content_expression);
		contentExprD
				.setDescription(Messages.MHtml_content_expression_description);
		desc.add(contentExprD);

		scaleTypeD = new NamedEnumPropertyDescriptor<ScaleImageEnum>(
				HtmlComponent.PROPERTY_SCALE_TYPE, Messages.MHtml_scaletype,
				ScaleImageEnum.CLIP, NullEnum.NOTNULL);
		scaleTypeD.setDescription(Messages.MHtml_scaletype_description);
		desc.add(scaleTypeD);

		CheckBoxPropertyDescriptor clipOverflow = new CheckBoxPropertyDescriptor(
				HtmlComponent.PROPERTY_CLIP_ON_OVERFLOW,
				Messages.MHtml_cliponoverflow, NullEnum.NULL);
		clipOverflow.setDescription(Messages.MHtml_cliponoverflow_desc);
		desc.add(clipOverflow);

		hAlignD = new NamedEnumPropertyDescriptor<HorizontalImageAlignEnum>(
				HtmlComponent.PROPERTY_HORIZONTAL_ALIGN,
				Messages.MHtml_horizontalalign,
				HorizontalImageAlignEnum.CENTER, NullEnum.NOTNULL);
		hAlignD.setDescription(Messages.MHtml_horizontalalign_description);
		desc.add(hAlignD);

		vAlignD = new NamedEnumPropertyDescriptor<VerticalImageAlignEnum>(
				HtmlComponent.PROPERTY_VERTICAL_ALIGN,
				Messages.MHtml_verticalalign, VerticalImageAlignEnum.BOTTOM,
				NullEnum.NOTNULL);
		vAlignD.setDescription(Messages.MHtml_verticalalign_description);
		desc.add(vAlignD);

		evaluationTimeD = new NamedEnumPropertyDescriptor<EvaluationTimeEnum>(
				HtmlComponent.PROPERTY_EVALUATION_TIME,
				Messages.MHtml_evaluation_time, EvaluationTimeEnum.AUTO,
				NullEnum.NOTNULL);
		evaluationTimeD
				.setDescription(Messages.MHtml_evaluation_time_description);
		desc.add(evaluationTimeD);

		evaluationGroupNameD = new RComboBoxPropertyDescriptor(
				HtmlComponent.PROPERTY_EVALUATION_GROUP,
				Messages.MHtml_evaluation_group, new String[] { "" }); //$NON-NLS-1$
		evaluationGroupNameD
				.setDescription(Messages.MHtml_evaluation_group_description);
		desc.add(evaluationGroupNameD);

		contentExprD.setCategory(Messages.common_properties_category);
		scaleTypeD.setCategory(Messages.common_properties_category);
		hAlignD.setCategory(Messages.common_properties_category);
		vAlignD.setCategory(Messages.common_properties_category);
		evaluationTimeD.setCategory(Messages.common_properties_category);
		evaluationGroupNameD.setCategory(Messages.common_properties_category);
		clipOverflow.setCategory(Messages.common_properties_category);
	}

	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();
		
		defaultsMap.put(HtmlComponent.PROPERTY_EVALUATION_TIME, new DefaultValue(EvaluationTimeEnum.NOW, false));
		defaultsMap.put(HtmlComponent.PROPERTY_CLIP_ON_OVERFLOW, new DefaultValue(Boolean.FALSE, false));
		
		int scaleValue = NamedEnumPropertyDescriptor.getIntValue(ScaleImageEnum.RETAIN_SHAPE, NullEnum.NOTNULL, ScaleImageEnum.RETAIN_SHAPE);
		defaultsMap.put(HtmlComponent.PROPERTY_SCALE_TYPE, new DefaultValue(scaleValue, false));
		
		int horizontalAlignmentValue = NamedEnumPropertyDescriptor.getIntValue(HorizontalImageAlignEnum.LEFT, NullEnum.NOTNULL, HorizontalImageAlignEnum.LEFT); 
		defaultsMap.put(HtmlComponent.PROPERTY_HORIZONTAL_ALIGN, new DefaultValue(horizontalAlignmentValue, false));
		
		int verticalAligmentValue = NamedEnumPropertyDescriptor.getIntValue(VerticalImageAlignEnum.MIDDLE, NullEnum.NOTNULL, VerticalImageAlignEnum.MIDDLE); 
		defaultsMap.put(HtmlComponent.PROPERTY_VERTICAL_ALIGN, new DefaultValue(verticalAligmentValue, false));
		
		return defaultsMap;
	}
	
	@Override
	protected void setGroupItems(String[] items) {
		super.setGroupItems(items);
		if (evaluationGroupNameD != null)
			evaluationGroupNameD.setItems(items);
	}

	@Override
	public void setValue(Object value) {
		if (getValue() != null) {
			Object obj = getComponent();
			if (obj instanceof JRChangeEventsSupport)
				((JRChangeEventsSupport) obj).getEventSupport()
						.removePropertyChangeListener(this);
		}
		if (value != null) {
			Object obj = getComponent(value);
			if (value instanceof JRChangeEventsSupport)
				((JRChangeEventsSupport) obj).getEventSupport()
						.addPropertyChangeListener(this);
		}
		super.setValue(value);
	}

	private Object getComponent() {
		return getComponent(getValue());
	}

	private Object getComponent(Object value) {
		if (value != null) {
			JRDesignComponentElement jrElement = (JRDesignComponentElement) value;
			return jrElement.getComponent();
		}
		return null;
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		HtmlComponent htmlComp = (HtmlComponent) jrElement.getComponent();

		if (id.equals(HtmlComponent.PROPERTY_EVALUATION_TIME))
			return htmlComp.getEvaluationTime();
		if (id.equals(HtmlComponent.PROPERTY_EVALUATION_GROUP))
			return htmlComp.getEvaluationGroup();
		if (id.equals(HtmlComponent.PROPERTY_SCALE_TYPE))
			return scaleTypeD.getIntValue(htmlComp.getScaleType());
		if (id.equals(HtmlComponent.PROPERTY_HORIZONTAL_ALIGN))
			return hAlignD.getIntValue(htmlComp.getHorizontalImageAlign());
		if (id.equals(HtmlComponent.PROPERTY_VERTICAL_ALIGN))
			return vAlignD.getIntValue(htmlComp.getVerticalImageAlign());
		if (id.equals(HtmlComponent.PROPERTY_HTMLCONTENT_EXPRESSION))
			return ExprUtil.getExpression(htmlComp.getHtmlContentExpression());
		if (id.equals(HtmlComponent.PROPERTY_CLIP_ON_OVERFLOW))
			return htmlComp.getClipOnOverflow();

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		HtmlComponent htmlComp = (HtmlComponent) jrElement.getComponent();

		if (id.equals(HtmlComponent.PROPERTY_EVALUATION_TIME)) {
			EvaluationTimeEnum evalTime = EnumHelper.getEnumByObjectValue(EvaluationTimeEnum.values(), value);
			htmlComp.setEvaluationTime(evalTime);
			if(evalTime != null && !evalTime.equals(EvaluationTimeEnum.GROUP)) {
				htmlComp.setEvaluationGroup(null);
			}
		} else if (id.equals(HtmlComponent.PROPERTY_EVALUATION_GROUP))
			htmlComp.setEvaluationGroup(ModelUtils.getGroupNameForProperty(value));
		else if (id.equals(HtmlComponent.PROPERTY_SCALE_TYPE))
			htmlComp.setScaleType(scaleTypeD.getEnumValue(value));
		else if (id.equals(HtmlComponent.PROPERTY_HORIZONTAL_ALIGN))
			htmlComp.setHorizontalImageAlign(hAlignD.getEnumValue(value));
		else if (id.equals(HtmlComponent.PROPERTY_VERTICAL_ALIGN))
			htmlComp.setVerticalImageAlign(vAlignD.getEnumValue(value));
		else if (id.equals(HtmlComponent.PROPERTY_HTMLCONTENT_EXPRESSION))
			htmlComp.setHtmlContentExpression(ExprUtil.setValues(
					htmlComp.getHtmlContentExpression(), value));
		else if (id.equals(HtmlComponent.PROPERTY_CLIP_ON_OVERFLOW))
			htmlComp.setClipOnOverflow((Boolean) value);
		else
			super.setPropertyValue(id, value);
	}
}
