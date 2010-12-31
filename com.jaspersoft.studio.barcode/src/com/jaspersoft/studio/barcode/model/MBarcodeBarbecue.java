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
package com.jaspersoft.studio.barcode.model;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.components.barbecue.StandardBarbecueComponent;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;
import net.sf.jasperreports.engine.type.RotationEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.barcode.BarcodeNodeIconDescriptor;
import com.jaspersoft.studio.barcode.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.property.descriptor.IntegerPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;

/**
 * The Class MBarcode.
 */
public class MBarcodeBarbecue extends MBarcode {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

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
	public MBarcodeBarbecue() {
		super();
	}

	/**
	 * Instantiates a new m barcode.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrBarcode
	 *          the jr barcode
	 * @param newIndex
	 *          the new index
	 */
	public MBarcodeBarbecue(ANode parent, JRDesignComponentElement jrBarcode, int newIndex) {
		super(parent, newIndex);
		setValue(jrBarcode);
	}

	@Override
	public JRDesignComponentElement createJRElement(JasperDesign jasperDesign) {
		JRDesignComponentElement el = new JRDesignComponentElement();
		StandardBarbecueComponent component = new StandardBarbecueComponent();
		JRDesignExpression exp = new JRDesignExpression();
		exp.setValueClassName("java.lang.String"); //$NON-NLS-1$
		exp.setText("\"1234\""); //$NON-NLS-1$
		component.setCodeExpression(exp);
		el.setComponent(component);
		el.setComponentKey(new ComponentKey("http://jasperreports.sourceforge.net/jasperreports/components", "jr", //$NON-NLS-1$ //$NON-NLS-2$
				"barbecue")); //$NON-NLS-1$
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
		super.createPropertyDescriptors(desc, defaultsMap);

		IntegerPropertyDescriptor widthD = new IntegerPropertyDescriptor(StandardBarbecueComponent.PROPERTY_BAR_WIDTH,
				Messages.MBarcodeBarbecue_bar_width);
		widthD.setDescription(Messages.MBarcodeBarbecue_bar_width_description);
		desc.add(widthD);

		IntegerPropertyDescriptor heightD = new IntegerPropertyDescriptor(StandardBarbecueComponent.PROPERTY_BAR_HEIGTH,
				Messages.MBarcodeBarbecue_bar_height);
		heightD.setDescription(Messages.MBarcodeBarbecue_bar_height_description);
		desc.add(heightD);

		JRExpressionPropertyDescriptor appIDexprD = new JRExpressionPropertyDescriptor(
				StandardBarbecueComponent.PROPERTY_APPLICATION_IDENTIFIER_EXPRESSION,
				Messages.MBarcodeBarbecue_application_identifier_expression);
		appIDexprD.setDescription(Messages.MBarcodeBarbecue_application_identifier_expression_description);
		desc.add(appIDexprD);

		RComboBoxPropertyDescriptor typeD = new RComboBoxPropertyDescriptor(StandardBarbecueComponent.PROPERTY_TYPE,
				Messages.MBarcodeBarbecue_type, new String[] {
						"2of7", "3of9", "Bookland", "Codabar", "Code128", "Code128A", "Code128B", "Code128C", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
						"Code39", "Code39 (Extended)", "EAN128", "EAN13", "GlobalTradeItemNumber", "Int2of5", "Monarch", "NW7", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
						"PDF417", "PostNet", "RandomWeightUPCA", "SCC14ShippingCode", "ShipmentIdentificationNumber", "SSCC18", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
						"Std2of5", "UCC128", "UPCA", "USD3", "USD4", "USPS" }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		typeD.setDescription(Messages.MBarcodeBarbecue_type_description);
		desc.add(typeD);

		CheckBoxPropertyDescriptor checksumRequiredD = new CheckBoxPropertyDescriptor(
				StandardBarbecueComponent.PROPERTY_CHECKSUM_REQUIRED, Messages.MBarcodeBarbecue_checksum_required);
		checksumRequiredD.setDescription(Messages.MBarcodeBarbecue_checksum_required_description);
		desc.add(checksumRequiredD);

		CheckBoxPropertyDescriptor drawTextD = new CheckBoxPropertyDescriptor(StandardBarbecueComponent.PROPERTY_DRAW_TEXT,
				Messages.MBarcodeBarbecue_draw_text);
		drawTextD.setDescription(Messages.MBarcodeBarbecue_draw_text_description);
		desc.add(drawTextD);

		ComboBoxPropertyDescriptor rotationD = new ComboBoxPropertyDescriptor(StandardBarbecueComponent.PROPERTY_ROTATION,
				Messages.MBarcodeBarbecue_rotation, EnumHelper.getEnumNames(RotationEnum.values(), NullEnum.INHERITED));
		rotationD.setDescription(Messages.MBarcodeBarbecue_rotation_description);
		desc.add(rotationD);

		rotationD.setCategory(Messages.common_properties_category);
		widthD.setCategory(Messages.common_properties_category);
		typeD.setCategory(Messages.common_properties_category);
		drawTextD.setCategory(Messages.common_properties_category);
		checksumRequiredD.setCategory(Messages.common_properties_category);
		heightD.setCategory(Messages.common_properties_category);
		appIDexprD.setCategory(Messages.common_properties_category);

		defaultsMap.put(StandardBarbecueComponent.PROPERTY_EVALUATION_TIME, EvaluationTimeEnum.NOW);
		defaultsMap.put(StandardBarbecueComponent.PROPERTY_ROTATION, null);
	}

	private MExpression codeExpression;
	private MExpression appidExpression;

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		StandardBarbecueComponent jrList = (StandardBarbecueComponent) jrElement.getComponent();

		if (id.equals(StandardBarbecueComponent.PROPERTY_EVALUATION_TIME))
			return EnumHelper.getValue(jrList.getEvaluationTimeValue(), 1, false);
		if (id.equals(StandardBarbecueComponent.PROPERTY_EVALUATION_GROUP))
			return jrList.getEvaluationGroup();
		if (id.equals(StandardBarbecueComponent.PROPERTY_CHECKSUM_REQUIRED))
			return new Boolean(jrList.isChecksumRequired());
		if (id.equals(StandardBarbecueComponent.PROPERTY_DRAW_TEXT))
			return new Boolean(jrList.isDrawText());

		if (id.equals(StandardBarbecueComponent.PROPERTY_TYPE))
			return jrList.getType();

		if (id.equals(StandardBarbecueComponent.PROPERTY_BAR_HEIGTH))
			return jrList.getBarHeight();
		if (id.equals(StandardBarbecueComponent.PROPERTY_BAR_WIDTH))
			return jrList.getBarWidth();
		if (id.equals(StandardBarbecueComponent.PROPERTY_CODE_EXPRESSION)) {
			if (codeExpression == null) {
				codeExpression = new MExpression(jrList.getCodeExpression());
				setChildListener(codeExpression);
			}
			return codeExpression;
		}
		if (id.equals(StandardBarbecueComponent.PROPERTY_ROTATION))
			return EnumHelper.getValue(jrList.getOwnRotation(), 0, true);
		if (id.equals(StandardBarbecueComponent.PROPERTY_APPLICATION_IDENTIFIER_EXPRESSION)) {
			if (appidExpression == null) {
				appidExpression = new MExpression(jrList.getApplicationIdentifierExpression());
				setChildListener(appidExpression);
			}
			return appidExpression;
		}
		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		StandardBarbecueComponent jrList = (StandardBarbecueComponent) jrElement.getComponent();

		if (id.equals(StandardBarbecueComponent.PROPERTY_EVALUATION_TIME))
			jrList.setEvaluationTimeValue((EvaluationTimeEnum) EnumHelper.getSetValue(EvaluationTimeEnum.values(), value, 1,
					false));
		else if (id.equals(StandardBarbecueComponent.PROPERTY_EVALUATION_GROUP))
			jrList.setEvaluationGroup((String) value);
		else if (id.equals(StandardBarbecueComponent.PROPERTY_CHECKSUM_REQUIRED))
			jrList.setChecksumRequired(((Boolean) value).booleanValue());
		else if (id.equals(StandardBarbecueComponent.PROPERTY_DRAW_TEXT))
			jrList.setDrawText(((Boolean) value).booleanValue());
		else if (id.equals(StandardBarbecueComponent.PROPERTY_TYPE))
			jrList.setType((String) value);
		else if (id.equals(StandardBarbecueComponent.PROPERTY_ROTATION))
			jrList.setRotation((RotationEnum) EnumHelper.getSetValue(RotationEnum.values(), value, 0, true));

		else if (id.equals(StandardBarbecueComponent.PROPERTY_BAR_HEIGTH))
			jrList.setBarHeight((Integer) value);
		else if (id.equals(StandardBarbecueComponent.PROPERTY_BAR_WIDTH))
			jrList.setBarWidth((Integer) value);
		else if (id.equals(StandardBarbecueComponent.PROPERTY_CODE_EXPRESSION)) {
			if (value instanceof MExpression) {
				codeExpression = (MExpression) value;
				setChildListener(codeExpression);
				JRExpression expression = (JRExpression) codeExpression.getValue();
				jrList.setCodeExpression(expression);
			}
		} else if (id.equals(StandardBarbecueComponent.PROPERTY_APPLICATION_IDENTIFIER_EXPRESSION)) {
			if (value instanceof MExpression) {
				appidExpression = (MExpression) value;
				setChildListener(appidExpression);
				JRExpression expression = (JRExpression) appidExpression.getValue();
				jrList.setApplicationIdentifierExpression(expression);
			}
		} else
			super.setPropertyValue(id, value);
	}

}
