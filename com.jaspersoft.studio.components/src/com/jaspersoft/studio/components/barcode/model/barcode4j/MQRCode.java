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
import java.util.Map;

import net.sf.jasperreports.components.barbecue.StandardBarbecueComponent;
import net.sf.jasperreports.components.barcode4j.ErrorCorrectionLevelEnum;
import net.sf.jasperreports.components.barcode4j.QRCodeComponent;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.barcode.BarcodeNodeIconDescriptor;
import com.jaspersoft.studio.components.barcode.model.MBarcode;
import com.jaspersoft.studio.editor.defaults.DefaultManager;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.PixelPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;

public class MQRCode extends MBarcode {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MQRCode() {
		super();
	}

	public MQRCode(ANode parent, JRDesignComponentElement jrBarcode,
			int newIndex) {
		super(parent, newIndex);
		setValue(jrBarcode);
	}

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
	public JRDesignComponentElement createJRElement(JasperDesign jasperDesign) {
		JRDesignComponentElement el = new JRDesignComponentElement();
		QRCodeComponent component = new QRCodeComponent();
		JRDesignExpression exp = new JRDesignExpression();
		exp.setText("\"123456789\""); //$NON-NLS-1$
		component.setCodeExpression(exp);
		el.setComponent(component);
		el.setComponentKey(new ComponentKey(
				"http://jasperreports.sourceforge.net/jasperreports/components", "jr", "QRCode")); //$NON-NLS-1$

		DefaultManager.INSTANCE.applyDefault(this.getClass(), el);

		return el;
	}

	private static IPropertyDescriptor[] descriptors;
	private static Map<String, Object> defaultsMap;
	private static NamedEnumPropertyDescriptor<ErrorCorrectionLevelEnum> errLevelD;

	@Override
	public Map<String, Object> getDefaultsMap() {
		return defaultsMap;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1,
			Map<String, Object> defaultsMap1) {
		descriptors = descriptors1;
		defaultsMap = defaultsMap1;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *            the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc,
			Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		errLevelD = new NamedEnumPropertyDescriptor<ErrorCorrectionLevelEnum>(
				QRCodeComponent.PROPERTY_ERROR_CORRECTION_LEVEL,
				"Error Correction Level", ErrorCorrectionLevelEnum.H,
				NullEnum.NOTNULL);
		errLevelD.setDescription("Error correction level.");
		errLevelD.setCategory("QR Code");
		desc.add(errLevelD);

		PixelPropertyDescriptor margind = new PixelPropertyDescriptor(
				QRCodeComponent.PROPERTY_MARGIN, "Margin");
		margind.setDescription("Margin");
		margind.setCategory("QR Code");
		desc.add(margind);

		defaultsMap.put(QRCodeComponent.PROPERTY_ERROR_CORRECTION_LEVEL,
				ErrorCorrectionLevelEnum.H);
		defaultsMap.put(QRCodeComponent.PROPERTY_MARGIN, new Integer(1));
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		QRCodeComponent qrCodeComponent = (QRCodeComponent) jrElement
				.getComponent();

		if (id.equals(QRCodeComponent.PROPERTY_ERROR_CORRECTION_LEVEL))
			return errLevelD.getIntValue(qrCodeComponent
					.getErrorCorrectionLevel());
		if (id.equals(QRCodeComponent.PROPERTY_MARGIN))
			return qrCodeComponent.getMargin();
		if (id.equals(StandardBarbecueComponent.PROPERTY_CODE_EXPRESSION))
			return ExprUtil.getExpression(qrCodeComponent.getCodeExpression());
		if (id.equals(StandardBarbecueComponent.PROPERTY_EVALUATION_TIME))
			return qrCodeComponent.getEvaluationTimeValue();
		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		QRCodeComponent qrcodeComponent = (QRCodeComponent) jrElement
				.getComponent();

		if (id.equals(QRCodeComponent.PROPERTY_ERROR_CORRECTION_LEVEL))
			qrcodeComponent.setErrorCorrectionLevel(errLevelD
					.getEnumValue(value));
		else if (id.equals(QRCodeComponent.PROPERTY_MARGIN))
			qrcodeComponent.setMargin((Integer) value);
		else if (id.equals(StandardBarbecueComponent.PROPERTY_CODE_EXPRESSION))
			qrcodeComponent.setCodeExpression(ExprUtil.setValues(
					qrcodeComponent.getCodeExpression(), value, null));
		else if (id.equals(StandardBarbecueComponent.PROPERTY_EVALUATION_TIME)) {
			EvaluationTimeEnum evalTime = EnumHelper.getEnumByObjectValue(
					EvaluationTimeEnum.values(), value);
			qrcodeComponent.setEvaluationTimeValue(evalTime);
			if (evalTime != null && !evalTime.equals(EvaluationTimeEnum.GROUP)) {
				qrcodeComponent.setEvaluationGroup(null);
			}
		}
		super.setPropertyValue(id, value);
	}

	@Override
	public void trasnferProperties(JRElement target) {
		super.trasnferProperties(target);

		JRDesignComponentElement jrSourceElement = (JRDesignComponentElement) getValue();
		QRCodeComponent jrSourceBarcode = (QRCodeComponent) jrSourceElement
				.getComponent();

		JRDesignComponentElement jrTargetElement = (JRDesignComponentElement) target;
		QRCodeComponent jrTargetBarcode = (QRCodeComponent) jrTargetElement
				.getComponent();

		jrTargetBarcode.setMargin(jrSourceBarcode.getMargin());
		jrTargetBarcode.setErrorCorrectionLevel(jrSourceBarcode
				.getErrorCorrectionLevel());
	}

	@Override
	public HashSet<String> generateGraphicalProperties() {
		HashSet<String> properties = super.generateGraphicalProperties();
		properties.add(QRCodeComponent.PROPERTY_ERROR_CORRECTION_LEVEL);
		properties.add(QRCodeComponent.PROPERTY_MARGIN);
		return properties;
	}
}
