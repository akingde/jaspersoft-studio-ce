/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.barcode.model.barcode4j;

import java.util.HashSet;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.barcode.messages.Messages;
import com.jaspersoft.studio.editor.defaults.DefaultManager;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.descriptors.DoublePropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.IntegerPropertyDescriptor;

import net.sf.jasperreports.components.barcode4j.Barcode4jComponent;
import net.sf.jasperreports.components.barcode4j.PDF417Component;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;

public class MPDF417 extends MBarcode4j {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	private static IPropertyDescriptor[] descriptors;

	public MPDF417() {
		super();
	}

	public MPDF417(ANode parent, JRDesignComponentElement jrBarcode, int newIndex) {
		super(parent, jrBarcode, newIndex);
	}

	@Override
	public JRDesignComponentElement createJRElement(JasperDesign jasperDesign, boolean applyDefault) {
		JRDesignComponentElement el = new JRDesignComponentElement();
		PDF417Component component = new PDF417Component();
		JRDesignExpression exp = new JRDesignExpression();
		exp.setText("\"123456789\""); //$NON-NLS-1$
		component.setCodeExpression(exp);
		el.setComponent(component);
		el.setComponentKey(
				new ComponentKey("http://jasperreports.sourceforge.net/jasperreports/components", "jr", "PDF417")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		if (applyDefault) {
			DefaultManager.INSTANCE.applyDefault(this.getClass(), el);
		}
		
		return el;
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

		IntegerPropertyDescriptor minColumnsD = new IntegerPropertyDescriptor(PDF417Component.PROPERTY_MIN_COLUMNS,
				Messages.MPDF417_min_columns);
		minColumnsD.setDescription(Messages.MPDF417_min_columns_description);
		desc.add(minColumnsD);
		minColumnsD.setBounds(1, 30);

		IntegerPropertyDescriptor maxColumnsD = new IntegerPropertyDescriptor(PDF417Component.PROPERTY_MAX_COLUMNS,
				Messages.MPDF417_max_columns);
		maxColumnsD.setDescription(Messages.MPDF417_max_columns_description);
		desc.add(maxColumnsD);
		maxColumnsD.setBounds(1, 30);

		IntegerPropertyDescriptor minRowsD = new IntegerPropertyDescriptor(PDF417Component.PROPERTY_MIN_ROWS,
				Messages.MPDF417_min_rows);
		minRowsD.setDescription(Messages.MPDF417_min_rows_description);
		desc.add(minRowsD);
		minRowsD.setBounds(3, 90);

		IntegerPropertyDescriptor maxRowsD = new IntegerPropertyDescriptor(PDF417Component.PROPERTY_MAX_ROWS,
				Messages.MPDF417_max_rows);
		maxRowsD.setDescription(Messages.MPDF417_max_rows_description);
		desc.add(maxRowsD);
		maxRowsD.setBounds(3, 90);

		DoublePropertyDescriptor width2HeightRatioD = new DoublePropertyDescriptor(
				PDF417Component.PROPERTY_WIDTH_TO_HEIGHT_RATIO, Messages.MPDF417_width_to_height_ratio);
		width2HeightRatioD.setDescription(Messages.MPDF417_width_to_height_ratio_description);
		desc.add(width2HeightRatioD);

		IntegerPropertyDescriptor errorCorrectionLevelD = new IntegerPropertyDescriptor(
				PDF417Component.PROPERTY_ERROR_CORRECTION_LEVEL, Messages.MPDF417_error_correction_level);
		errorCorrectionLevelD.setDescription(Messages.MPDF417_error_correction_level_description);
		desc.add(errorCorrectionLevelD);
		errorCorrectionLevelD.setBounds(0, 8);

		DoublePropertyDescriptor vertQuietZoneD = new DoublePropertyDescriptor(
				Barcode4jComponent.PROPERTY_VERTICAL_QUIET_ZONE, Messages.MBarcode4j_vertical_quiet_zone);
		vertQuietZoneD.setDescription(Messages.MBarcode4j_vertical_quiet_zone_description);
		desc.add(vertQuietZoneD);
		vertQuietZoneD.setCategory(Messages.common_properties_category);

		minColumnsD.setCategory(Messages.MPDF417_properties_category);
		maxColumnsD.setCategory(Messages.MPDF417_properties_category);
		minRowsD.setCategory(Messages.MPDF417_properties_category);
		maxRowsD.setCategory(Messages.MPDF417_properties_category);
		width2HeightRatioD.setCategory(Messages.MPDF417_properties_category);
		errorCorrectionLevelD.setCategory(Messages.MPDF417_properties_category);
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		PDF417Component jrList = (PDF417Component) jrElement.getComponent();

		if (id.equals(PDF417Component.PROPERTY_MIN_COLUMNS))
			return jrList.getMinColumns();
		if (id.equals(PDF417Component.PROPERTY_MAX_COLUMNS))
			return jrList.getMaxColumns();
		if (id.equals(PDF417Component.PROPERTY_MIN_ROWS))
			return jrList.getMinRows();
		if (id.equals(PDF417Component.PROPERTY_MAX_ROWS))
			return jrList.getMaxRows();
		if (id.equals(PDF417Component.PROPERTY_WIDTH_TO_HEIGHT_RATIO))
			return jrList.getWidthToHeightRatio();
		if (id.equals(PDF417Component.PROPERTY_ERROR_CORRECTION_LEVEL))
			return jrList.getErrorCorrectionLevel();

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		PDF417Component jrList = (PDF417Component) jrElement.getComponent();

		if (id.equals(PDF417Component.PROPERTY_MIN_ROWS))
			jrList.setMinRows((Integer) value);
		if (id.equals(PDF417Component.PROPERTY_MAX_ROWS))
			jrList.setMaxRows((Integer) value);
		if (id.equals(PDF417Component.PROPERTY_MIN_COLUMNS))
			jrList.setMinColumns((Integer) value);
		if (id.equals(PDF417Component.PROPERTY_MAX_COLUMNS))
			jrList.setMaxColumns((Integer) value);
		if (id.equals(PDF417Component.PROPERTY_WIDTH_TO_HEIGHT_RATIO))
			jrList.setWidthToHeightRatio((Double) value);
		if (id.equals(PDF417Component.PROPERTY_ERROR_CORRECTION_LEVEL))
			jrList.setErrorCorrectionLevel((Integer) value);

		super.setPropertyValue(id, value);
	}

	@Override
	public HashSet<String> generateGraphicalProperties() {
		HashSet<String> properties = super.generateGraphicalProperties();
		properties.add(PDF417Component.PROPERTY_MIN_ROWS);
		properties.add(PDF417Component.PROPERTY_MAX_ROWS);
		properties.add(PDF417Component.PROPERTY_MIN_COLUMNS);
		properties.add(PDF417Component.PROPERTY_MAX_COLUMNS);
		properties.add(PDF417Component.PROPERTY_WIDTH_TO_HEIGHT_RATIO);
		properties.add(PDF417Component.PROPERTY_ERROR_CORRECTION_LEVEL);
		return properties;
	}

	@Override
	public void trasnferProperties(JRElement target) {
		super.trasnferProperties(target);

		JRDesignComponentElement jrSourceElement = (JRDesignComponentElement) getValue();
		PDF417Component jrSourceBarcode = (PDF417Component) jrSourceElement.getComponent();

		JRDesignComponentElement jrTargetElement = (JRDesignComponentElement) target;
		PDF417Component jrTargetBarcode = (PDF417Component) jrTargetElement.getComponent();

		jrTargetBarcode.setMinColumns(jrSourceBarcode.getMinColumns());
		jrTargetBarcode.setMaxColumns(jrSourceBarcode.getMaxColumns());
		jrTargetBarcode.setMinRows(jrSourceBarcode.getMinRows());
		jrTargetBarcode.setMaxRows(jrSourceBarcode.getMaxRows());
		jrTargetBarcode.setWidthToHeightRatio(jrSourceBarcode.getWidthToHeightRatio());
		jrTargetBarcode.setErrorCorrectionLevel(jrSourceBarcode.getErrorCorrectionLevel());
	}
}
