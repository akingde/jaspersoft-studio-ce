/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.barcode.model.barcode4j;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.components.barbecue.StandardBarbecueComponent;
import net.sf.jasperreports.components.barcode4j.BarcodeComponent;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.barcode.BarcodeNodeIconDescriptor;
import com.jaspersoft.studio.barcode.model.MBarcode;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.property.descriptor.DoublePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;

/**
 * The Class MBarcode.
 */
public class MBarcode4j extends MBarcode {

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
	public MBarcode4j() {
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
	public MBarcode4j(ANode parent, JRDesignComponentElement jrBarcode, int newIndex) {
		super(parent, newIndex);
		setValue(jrBarcode);
	}

	@Override
	public JRDesignComponentElement createJRElement(JasperDesign jasperDesign) {
		JRDesignComponentElement el = new JRDesignComponentElement();

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

		ComboBoxPropertyDescriptor orientationD = new ComboBoxPropertyDescriptor(BarcodeComponent.PROPERTY_ORIENTATION,
				"Orientation", Orientation.getItems());
		orientationD.setDescription("Orientation.");
		desc.add(orientationD);

		JRExpressionPropertyDescriptor patternExprD = new JRExpressionPropertyDescriptor(
				BarcodeComponent.PROPERTY_PATTERN_EXPRESSION, "Pattern Expression");
		patternExprD.setDescription("The pattern expression.");
		desc.add(patternExprD);

		ComboBoxPropertyDescriptor textPositionD = new ComboBoxPropertyDescriptor(BarcodeComponent.PROPERTY_TEXT_POSITION,
				"Text Position", TextPosition.getItems());
		textPositionD.setDescription("Text position.");
		desc.add(textPositionD);

		DoublePropertyDescriptor quiteZoneD = new DoublePropertyDescriptor(BarcodeComponent.PROPERTY_QUIET_ZONE,
				"Quiet Zone");
		quiteZoneD.setDescription("Quiet Zone.");
		desc.add(quiteZoneD);

		DoublePropertyDescriptor moduleWidthD = new DoublePropertyDescriptor(BarcodeComponent.PROPERTY_MODULE_WIDTH,
				"Module Width");
		moduleWidthD.setDescription("Module Width.");
		desc.add(moduleWidthD);

		DoublePropertyDescriptor vertQuietZoneD = new DoublePropertyDescriptor(
				BarcodeComponent.PROPERTY_VERTICAL_QUIET_ZONE, "Vertical Quiet Zone");
		vertQuietZoneD.setDescription("Vertical Quiet Zone.");
		desc.add(vertQuietZoneD);

		vertQuietZoneD.setCategory("Barcode Properties");
		moduleWidthD.setCategory("Barcode Properties");
		quiteZoneD.setCategory("Barcode Properties");
		orientationD.setCategory("Barcode Properties");
		patternExprD.setCategory("Barcode Properties");
		textPositionD.setCategory("Barcode Properties");

	}

	private MExpression codeExpression;
	private MExpression ptExpression;

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		BarcodeComponent jrList = (BarcodeComponent) jrElement.getComponent();

		if (id.equals(StandardBarbecueComponent.PROPERTY_EVALUATION_TIME))
			return EnumHelper.getValue(jrList.getEvaluationTimeValue(), 1, false);
		if (id.equals(StandardBarbecueComponent.PROPERTY_EVALUATION_GROUP))
			return jrList.getEvaluationGroup();

		if (id.equals(BarcodeComponent.PROPERTY_MODULE_WIDTH))
			return jrList.getModuleWidth();
		if (id.equals(BarcodeComponent.PROPERTY_QUIET_ZONE))
			return jrList.getQuietZone();
		if (id.equals(BarcodeComponent.PROPERTY_VERTICAL_QUIET_ZONE))
			return jrList.getVerticalQuietZone();
		if (id.equals(BarcodeComponent.PROPERTY_ORIENTATION))
			return Orientation.getPos4Orientation(jrList.getOrientation());
		if (id.equals(BarcodeComponent.PROPERTY_TEXT_POSITION))
			return TextPosition.getPos4TextPosition(jrList.getTextPosition());

		if (id.equals(StandardBarbecueComponent.PROPERTY_CODE_EXPRESSION)) {
			if (codeExpression == null)
				codeExpression = new MExpression(jrList.getCodeExpression());
			return codeExpression;
		}
		if (id.equals(BarcodeComponent.PROPERTY_PATTERN_EXPRESSION)) {
			if (ptExpression == null)
				ptExpression = new MExpression(jrList.getPatternExpression());
			return ptExpression;
		}

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		BarcodeComponent jrList = (BarcodeComponent) jrElement.getComponent();

		if (id.equals(StandardBarbecueComponent.PROPERTY_EVALUATION_TIME))
			jrList.setEvaluationTimeValue((EvaluationTimeEnum) EnumHelper.getSetValue(EvaluationTimeEnum.values(), value, 1,
					false));
		else if (id.equals(StandardBarbecueComponent.PROPERTY_EVALUATION_GROUP))
			jrList.setEvaluationGroup((String) value);

		else if (id.equals(BarcodeComponent.PROPERTY_MODULE_WIDTH))
			jrList.setModuleWidth((Double) value);
		else if (id.equals(BarcodeComponent.PROPERTY_QUIET_ZONE))
			jrList.setQuietZone((Double) value);
		else if (id.equals(BarcodeComponent.PROPERTY_VERTICAL_QUIET_ZONE))
			jrList.setVerticalQuietZone((Double) value);
		else if (id.equals(BarcodeComponent.PROPERTY_ORIENTATION))
			jrList.setOrientation(Orientation.getOrientation4Pos((Integer) value));
		else if (id.equals(BarcodeComponent.PROPERTY_TEXT_POSITION))
			jrList.setTextPosition(TextPosition.getTextPosition4Pos((Integer) value));

		else if (id.equals(BarcodeComponent.PROPERTY_PATTERN_EXPRESSION)) {
			if (value instanceof MExpression) {
				ptExpression = (MExpression) value;
				JRExpression expression = (JRExpression) ptExpression.getValue();
				jrList.setPatternExpression(expression);
			}
		} else if (id.equals(StandardBarbecueComponent.PROPERTY_CODE_EXPRESSION)) {
			if (value instanceof MExpression) {
				codeExpression = (MExpression) value;
				JRExpression expression = (JRExpression) codeExpression.getValue();
				jrList.setCodeExpression(expression);
			}
		} else
			super.setPropertyValue(id, value);
	}
}
