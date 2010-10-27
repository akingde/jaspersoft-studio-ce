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

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.barcode.BarcodeNodeIconDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.property.descriptor.IntegerPropertyDescriptor;
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
			iconDescriptor = new BarcodeNodeIconDescriptor("barcode");
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
		exp.setValueClassName("java.lang.String");
		exp.setText("\"1234\"");
		component.setCodeExpression(exp);
		el.setComponent(component);
		el.setComponentKey(new ComponentKey("http://jasperreports.sourceforge.net/jasperreports/components", "jr",
				"barbecue"));
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
				"Bar Width");
		widthD.setDescription("Width of the bar.");
		desc.add(widthD);

		IntegerPropertyDescriptor heightD = new IntegerPropertyDescriptor(StandardBarbecueComponent.PROPERTY_BAR_HEIGTH,
				"Bar Height");
		heightD.setDescription("Height of the bar.");
		desc.add(heightD);

		JRExpressionPropertyDescriptor appIDexprD = new JRExpressionPropertyDescriptor(
				StandardBarbecueComponent.PROPERTY_APPLICATION_IDENTIFIER_EXPRESSION, "Application Identifier Expression");
		appIDexprD.setDescription("The application identifier expression.");
		desc.add(appIDexprD);

		RComboBoxPropertyDescriptor typeD = new RComboBoxPropertyDescriptor(StandardBarbecueComponent.PROPERTY_TYPE,
				"Type", new String[] { "2of7", "3of9", "Bookland", "Codabar", "Code128", "Code128A", "Code128B", "Code128C",
						"Code39", "Code39 (Extended)", "EAN128", "EAN13", "GlobalTradeItemNumber", "Int2of5", "Monarch", "NW7",
						"PDF417", "PostNet", "RandomWeightUPCA", "SCC14ShippingCode", "ShipmentIdentificationNumber", "SSCC18",
						"Std2of5", "UCC128", "UPCA", "USD3", "USD4", "USPS" });
		typeD.setDescription("The type of barcode.");
		desc.add(typeD);

		CheckBoxPropertyDescriptor checksumRequiredD = new CheckBoxPropertyDescriptor(
				StandardBarbecueComponent.PROPERTY_CHECKSUM_REQUIRED, "Checksum Required");
		checksumRequiredD.setDescription("Checksum is required.");
		desc.add(checksumRequiredD);

		CheckBoxPropertyDescriptor drawTextD = new CheckBoxPropertyDescriptor(StandardBarbecueComponent.PROPERTY_DRAW_TEXT,
				"Draw Text");
		drawTextD.setDescription("Draw text.");
		desc.add(drawTextD);

		widthD.setCategory("Barcode Properties");
		typeD.setCategory("Barcode Properties");
		drawTextD.setCategory("Barcode Properties");
		checksumRequiredD.setCategory("Barcode Properties");
		heightD.setCategory("Barcode Properties");
		appIDexprD.setCategory("Barcode Properties");

		defaultsMap.put(StandardBarbecueComponent.PROPERTY_EVALUATION_TIME, EvaluationTimeEnum.NOW);
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
			if (codeExpression == null)
				codeExpression = new MExpression(jrList.getCodeExpression());
			return codeExpression;
		}
		if (id.equals(StandardBarbecueComponent.PROPERTY_APPLICATION_IDENTIFIER_EXPRESSION)) {
			if (appidExpression == null)
				appidExpression = new MExpression(jrList.getApplicationIdentifierExpression());
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

		else if (id.equals(StandardBarbecueComponent.PROPERTY_BAR_HEIGTH))
			jrList.setBarHeight((Integer) value);
		else if (id.equals(StandardBarbecueComponent.PROPERTY_BAR_WIDTH))
			jrList.setBarWidth((Integer) value);
		else if (id.equals(StandardBarbecueComponent.PROPERTY_CODE_EXPRESSION)) {
			if (value instanceof MExpression) {
				codeExpression = (MExpression) value;
				JRExpression expression = (JRExpression) codeExpression.getValue();
				jrList.setCodeExpression(expression);
			}
		} else if (id.equals(StandardBarbecueComponent.PROPERTY_APPLICATION_IDENTIFIER_EXPRESSION)) {
			if (value instanceof MExpression) {
				appidExpression = (MExpression) value;
				JRExpression expression = (JRExpression) appidExpression.getValue();
				jrList.setApplicationIdentifierExpression(expression);
			}
		} else
			super.setPropertyValue(id, value);
	}

}
