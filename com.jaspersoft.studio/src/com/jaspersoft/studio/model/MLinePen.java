/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.help.HelpReferenceBuilder;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.messages.MessagesByKeys;
import com.jaspersoft.studio.property.JSSStyleResolver;
import com.jaspersoft.studio.property.combomenu.ComboItem;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.color.ColorPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.FloatPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.JSSPopupPropertyDescriptor;
import com.jaspersoft.studio.utils.AlfaRGB;
import com.jaspersoft.studio.utils.Colors;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.base.JRBasePen;
import net.sf.jasperreports.engine.type.LineStyleEnum;

public class MLinePen extends APropertyNode implements IPropertySource {
	
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	private static List<ComboItem> lineSpacingItems = null;

	private static IPropertyDescriptor[] descriptors;
	
	private static JSSPopupPropertyDescriptor penLineStyleD;
	
	public MLinePen(JRPen linePen) {
		super();
		setValue(linePen);
	}

	@Override
	public HashMap<String, Object> getStylesDescriptors() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		if (getValue() == null)
			return result;
		JRBasePen element = (JRBasePen) getValue();
		result.put(JRBasePen.PROPERTY_LINE_COLOR, element.getOwnLineColor());
		result.put(JRBasePen.PROPERTY_LINE_STYLE, element.getOwnLineStyleValue());
		result.put(JRBasePen.PROPERTY_LINE_WIDTH, element.getOwnLineWidth());
		return result;
	}

	private List<ComboItem> createLineSpacingItems() {
		if (lineSpacingItems == null) {
			lineSpacingItems = new ArrayList<ComboItem>();
			LineStyleEnum[] values = LineStyleEnum.class.getEnumConstants();
			lineSpacingItems.add(new ComboItem(MessagesByKeys.getString("LineSpacing_nullEnum"), true, ResourceManager
					.getImage(this.getClass(), "/icons/resources/inherited.png"), 0, NullEnum.INHERITED, null));
			Image[] images = new Image[] { ResourceManager.getImage(this.getClass(), "/icons/resources/line-solid.png"),
					ResourceManager.getImage(this.getClass(), "/icons/resources/line-dashed.png"),
					ResourceManager.getImage(this.getClass(), "/icons/resources/line-dotted.png"),
					ResourceManager.getImage(this.getClass(), "/icons/resources/line-double.png"), };
			for (int i = 0; i < values.length; i++) {
				LineStyleEnum value = values[i];
				lineSpacingItems.add(new ComboItem(MessagesByKeys.getString("LineStyle_".concat(value.getName())), true,
						images[i], i + 1, value, value));
			}
		}
		return lineSpacingItems;
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		// pen
		ColorPropertyDescriptor penLineColorD = new ColorPropertyDescriptor(JRBasePen.PROPERTY_LINE_COLOR,
				Messages.common_line_color, NullEnum.INHERITED);
		penLineColorD.setDescription(Messages.MLinePen_line_color_description);
		penLineColorD.setHelpRefBuilder(new HelpReferenceBuilder(
				"net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#pen_lineColor"));
		desc.add(penLineColorD);

		FloatPropertyDescriptor penLineWidthD = new FloatPropertyDescriptor(JRBasePen.PROPERTY_LINE_WIDTH, Messages.MLinePen_line_width);
		penLineWidthD.setDescription(Messages.MLinePen_line_width_description);
		penLineWidthD.setHelpRefBuilder(new HelpReferenceBuilder("net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#pen_lineWidth"));
		penLineWidthD.setBounds(0, 999);
		desc.add(penLineWidthD);

		penLineStyleD = new JSSPopupPropertyDescriptor(JRBasePen.PROPERTY_LINE_STYLE, Messages.common_line_style,
				LineStyleEnum.DASHED, NullEnum.INHERITED, createLineSpacingItems());
		penLineStyleD.setDescription(Messages.MLinePen_line_style_description);
		penLineStyleD.setHelpRefBuilder(new HelpReferenceBuilder(
				"net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#pen_lineStyle"));
		desc.add(penLineStyleD);
	}

	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();
		
		defaultsMap.put(JRBasePen.PROPERTY_LINE_STYLE, new DefaultValue(null, true));
		defaultsMap.put(JRBasePen.PROPERTY_LINE_COLOR, new DefaultValue(null, true));
		defaultsMap.put(JRBasePen.PROPERTY_LINE_WIDTH, new DefaultValue(null, true));
		
		return defaultsMap;
	}


	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors = descriptors1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		// pen
		JRPen linePen = (JRPen) getValue();
		if (linePen != null) {
			if (id.equals(JRBasePen.PROPERTY_LINE_COLOR))
				return Colors.getSWTRGB4AWTGBColor(linePen.getOwnLineColor());
			if (id.equals(JRBasePen.PROPERTY_LINE_WIDTH))
				return linePen.getOwnLineWidth();
			if (id.equals(JRBasePen.PROPERTY_LINE_STYLE)){
				if (penLineStyleD == null){
					getPropertyDescriptors();
				}
				return penLineStyleD.getIntValue(linePen.getOwnLineStyleValue());
			}
		}
		return null;
	}

	public Object getPropertyActualValue(Object id) {
		JRBasePen linePen = (JRBasePen) getValue();
		JSSStyleResolver resolver = getStyleResolver();
		if (linePen != null) {
			if (id.equals(JRBasePen.PROPERTY_LINE_COLOR)){
				Color lineColor = resolver.getLineColor(linePen, linePen.getPenContainer().getDefaultLineColor());
				return Colors.getSWTRGB4AWTGBColor(lineColor);
			} else if (id.equals(JRBasePen.PROPERTY_LINE_WIDTH)) {
				return resolver.getLineWidth(linePen, linePen.getPenContainer().getDefaultLineWidth());
			} else if (id.equals(JRBasePen.PROPERTY_LINE_STYLE)){
				if (penLineStyleD == null){
					getPropertyDescriptors();
				}
				LineStyleEnum lineStyle = resolver.getLineStyleValue(linePen);
				return penLineStyleD.getIntValue(lineStyle);
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRPen linePen = (JRPen) getValue();
		if (linePen != null) {
			if (id.equals(JRBasePen.PROPERTY_LINE_WIDTH)) {
				Float lineWidth = (Float) value;
				if (lineWidth != null && lineWidth.floatValue()>=0){
					linePen.setLineWidth(Math.abs(lineWidth));
				} else {
					linePen.setLineWidth(null);
				}
			} else if (id.equals(JRBasePen.PROPERTY_LINE_COLOR)) {
				if (value == null)
					linePen.setLineColor(null);
				else if (value instanceof AlfaRGB)
					linePen.setLineColor(Colors.getAWT4SWTRGBColor((AlfaRGB) value));
			} else if (id.equals(JRBasePen.PROPERTY_LINE_STYLE))
				linePen.setLineStyle(penLineStyleD.getEnumValue(value));
		}
	}

	public String getDisplayText() {
		return null;
	}

	public ImageDescriptor getImagePath() {
		return null;
	}

}
