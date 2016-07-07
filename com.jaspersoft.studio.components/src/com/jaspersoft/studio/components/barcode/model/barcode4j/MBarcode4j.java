/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.components.barcode.model.barcode4j;

import java.util.HashSet;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.barcode.BarcodeNodeIconDescriptor;
import com.jaspersoft.studio.components.barcode.messages.Messages;
import com.jaspersoft.studio.components.barcode.model.MBarcode;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.DoublePropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.PixelPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.components.barbecue.StandardBarbecueComponent;
import net.sf.jasperreports.components.barcode4j.Barcode4jComponent;
import net.sf.jasperreports.components.barcode4j.OrientationEnum;
import net.sf.jasperreports.components.barcode4j.TextPositionEnum;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;

/*
 * The Class MBarcode.
 */
public class MBarcode4j extends MBarcode {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	/**
	 * The icon descriptor.
	 */
	private static IIconDescriptor iconDescriptor;

	private static IPropertyDescriptor[] descriptors;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new BarcodeNodeIconDescriptor("barcode"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m barcode.
	 */
	public MBarcode4j() {
		super();
	}

	/**
	 * Instantiates a new m barcode.
	 * 
	 * @param parent
	 *            the parent
	 * @param jrBarcode
	 *            the jr barcode
	 * @param newIndex
	 *            the new index
	 */
	public MBarcode4j(ANode parent, JRDesignComponentElement jrBarcode, int newIndex) {
		super(parent, newIndex);
		setValue(jrBarcode);
	}

	@Override
	public JRDesignComponentElement createJRElement(JasperDesign jasperDesign) {
		JRDesignComponentElement el = new JRDesignComponentElement(jasperDesign);
		return el;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		return getIconDescriptor().getTitle();
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
		super.createPropertyDescriptors(desc);

		NamedEnumPropertyDescriptor<OrientationEnum> orientationD = new NamedEnumPropertyDescriptor<OrientationEnum>(
				Barcode4jComponent.PROPERTY_ORIENTATION, Messages.MBarcode4j_orientation, OrientationEnum.UP,
				NullEnum.NOTNULL);
		orientationD.setDescription(Messages.MBarcode4j_orientation_description);
		desc.add(orientationD);

		JRExpressionPropertyDescriptor patternExprD = new JRExpressionPropertyDescriptor(
				Barcode4jComponent.PROPERTY_PATTERN_EXPRESSION, Messages.MBarcode4j_pattern_expression);
		patternExprD.setDescription(Messages.MBarcode4j_pattern_expression_description);
		desc.add(patternExprD);

		NamedEnumPropertyDescriptor<TextPositionEnum> textPositionD = new NamedEnumPropertyDescriptor<TextPositionEnum>(
				Barcode4jComponent.PROPERTY_TEXT_POSITION, Messages.MBarcode4j_text_position, TextPositionEnum.NONE,
				NullEnum.NULL);
		textPositionD.setDescription(Messages.MBarcode4j_text_position_description);
		desc.add(textPositionD);

		DoublePropertyDescriptor quiteZoneD = new DoublePropertyDescriptor(Barcode4jComponent.PROPERTY_QUIET_ZONE,
				Messages.MBarcode4j_quiet_zone);
		quiteZoneD.setDescription(Messages.MBarcode4j_quiet_zone_description);
		desc.add(quiteZoneD);

		DoublePropertyDescriptor vertQuietZoneD = new DoublePropertyDescriptor(
				Barcode4jComponent.PROPERTY_VERTICAL_QUIET_ZONE, Messages.MBarcode4j_vertical_quiet_zone);
		vertQuietZoneD.setDescription(Messages.MBarcode4j_vertical_quiet_zone_description);
		desc.add(vertQuietZoneD);

		PixelPropertyDescriptor moduleWidthD = new PixelPropertyDescriptor(Barcode4jComponent.PROPERTY_MODULE_WIDTH,
				Messages.MBarcode4j_module_width);
		moduleWidthD.setDescription(Messages.MBarcode4j_module_width_description);
		desc.add(moduleWidthD);

		vertQuietZoneD.setCategory(Messages.common_properties_category);
		moduleWidthD.setCategory(Messages.common_properties_category);
		quiteZoneD.setCategory(Messages.common_properties_category);
		orientationD.setCategory(Messages.common_properties_category);
		patternExprD.setCategory(Messages.common_properties_category);
		textPositionD.setCategory(Messages.common_properties_category);
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		Barcode4jComponent jrBarcodeComponent = (Barcode4jComponent) jrElement.getComponent();

		if (id.equals(StandardBarbecueComponent.PROPERTY_EVALUATION_TIME))
			return jrBarcodeComponent.getEvaluationTimeValue();
		if (id.equals(StandardBarbecueComponent.PROPERTY_EVALUATION_GROUP))
			return jrBarcodeComponent.getEvaluationGroup();

		if (id.equals(Barcode4jComponent.PROPERTY_MODULE_WIDTH))
			return jrBarcodeComponent.getModuleWidth() != null ? jrBarcodeComponent.getModuleWidth().intValue() : null;
		if (id.equals(Barcode4jComponent.PROPERTY_QUIET_ZONE))
			return jrBarcodeComponent.getQuietZone();
		if (id.equals(Barcode4jComponent.PROPERTY_VERTICAL_QUIET_ZONE))
			return jrBarcodeComponent.getVerticalQuietZone();
		if (id.equals(Barcode4jComponent.PROPERTY_ORIENTATION)) {
			OrientationEnum orientation = jrBarcodeComponent.getOrientationValue();
			return NamedEnumPropertyDescriptor.getIntValue(OrientationEnum.UP, NullEnum.NOTNULL, orientation);
		}
		if (id.equals(Barcode4jComponent.PROPERTY_TEXT_POSITION)) {
			TextPositionEnum position = jrBarcodeComponent.getTextPositionValue();
			return NamedEnumPropertyDescriptor.getIntValue(TextPositionEnum.NONE, NullEnum.NOTNULL, position);
		}
		if (id.equals(StandardBarbecueComponent.PROPERTY_CODE_EXPRESSION))
			return ExprUtil.getExpression(jrBarcodeComponent.getCodeExpression());
		if (id.equals(Barcode4jComponent.PROPERTY_PATTERN_EXPRESSION))
			return ExprUtil.getExpression(jrBarcodeComponent.getPatternExpression());
		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		Barcode4jComponent barcodeComponent = (Barcode4jComponent) jrElement.getComponent();

		if (id.equals(StandardBarbecueComponent.PROPERTY_EVALUATION_TIME)) {
			EvaluationTimeEnum evalTime = EnumHelper.getEnumByObjectValue(EvaluationTimeEnum.values(), value);
			barcodeComponent.setEvaluationTimeValue(evalTime);
			if (evalTime != null && !evalTime.equals(EvaluationTimeEnum.GROUP)) {
				barcodeComponent.setEvaluationGroup(null);
			}
		} else if (id.equals(StandardBarbecueComponent.PROPERTY_EVALUATION_GROUP)) {
			barcodeComponent.setEvaluationGroup(ModelUtils.getGroupNameForProperty(value));
		}

		else if (id.equals(Barcode4jComponent.PROPERTY_MODULE_WIDTH))
			if (value instanceof Integer)
				barcodeComponent.setModuleWidth(((Integer) value).doubleValue());
			else
				barcodeComponent.setModuleWidth((Double) value);
		else if (id.equals(Barcode4jComponent.PROPERTY_QUIET_ZONE))
			barcodeComponent.setQuietZone((Double) value);
		else if (id.equals(Barcode4jComponent.PROPERTY_VERTICAL_QUIET_ZONE))
			barcodeComponent.setVerticalQuietZone((Double) value);
		else if (id.equals(Barcode4jComponent.PROPERTY_ORIENTATION)) {
			OrientationEnum orientation = NamedEnumPropertyDescriptor.getEnumValue(OrientationEnum.UP, NullEnum.NOTNULL,
					value);
			barcodeComponent.setOrientation(orientation);
		} else if (id.equals(Barcode4jComponent.PROPERTY_TEXT_POSITION)) {
			TextPositionEnum position = NamedEnumPropertyDescriptor.getEnumValue(TextPositionEnum.NONE,
					NullEnum.NOTNULL, value);
			barcodeComponent.setTextPosition(position);
		} else if (id.equals(Barcode4jComponent.PROPERTY_PATTERN_EXPRESSION))
			barcodeComponent
					.setPatternExpression(ExprUtil.setValues(barcodeComponent.getPatternExpression(), value, null));
		else if (id.equals(StandardBarbecueComponent.PROPERTY_CODE_EXPRESSION))
			barcodeComponent.setCodeExpression(ExprUtil.setValues(barcodeComponent.getCodeExpression(), value, null));
		else
			super.setPropertyValue(id, value);
	}

	@Override
	public HashSet<String> generateGraphicalProperties() {
		HashSet<String> properties = super.generateGraphicalProperties();
		properties.add(StandardBarbecueComponent.PROPERTY_CODE_EXPRESSION);
		properties.add(Barcode4jComponent.PROPERTY_ORIENTATION);
		properties.add(Barcode4jComponent.PROPERTY_TEXT_POSITION);
		properties.add(Barcode4jComponent.PROPERTY_MODULE_WIDTH);
		properties.add(Barcode4jComponent.PROPERTY_QUIET_ZONE);
		properties.add(Barcode4jComponent.PROPERTY_VERTICAL_QUIET_ZONE);
		return properties;
	}

	@Override
	public void trasnferProperties(JRElement target) {
		super.trasnferProperties(target);

		JRDesignComponentElement jrSourceElement = (JRDesignComponentElement) getValue();
		Barcode4jComponent jrSourceBarcode = (Barcode4jComponent) jrSourceElement.getComponent();

		JRDesignComponentElement jrTargetElement = (JRDesignComponentElement) target;
		Barcode4jComponent jrTargetBarcode = (Barcode4jComponent) jrTargetElement.getComponent();

		jrTargetBarcode.setModuleWidth(jrSourceBarcode.getModuleWidth());
		jrTargetBarcode.setQuietZone(jrSourceBarcode.getQuietZone());
		jrTargetBarcode.setVerticalQuietZone(jrSourceBarcode.getVerticalQuietZone());
		jrTargetBarcode.setOrientation(jrSourceBarcode.getOrientationValue());
		jrTargetBarcode.setTextPosition(jrSourceBarcode.getTextPositionValue());
	}
}
