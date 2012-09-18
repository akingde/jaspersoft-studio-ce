/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.base.JRBasePen;
import net.sf.jasperreports.engine.type.LineStyleEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.combomenu.ComboItem;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.color.ColorPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.FloatPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.JSSPopupPropertyDescriptor;
import com.jaspersoft.studio.utils.Colors;
import com.jaspersoft.studio.utils.ResourceManager;

public class MLinePen extends APropertyNode implements IPropertySource {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	private static List<ComboItem> lineSpacingItems = null;
	
	public MLinePen(JRPen linePen) {
		super();
		setValue(linePen);
	}
	
	@Override
	public HashMap<String,Object> getStylesDescriptors() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		if (getValue() == null)
			return result;
		JRBasePen element = (JRBasePen) getValue();
		result.put(JRBasePen.PROPERTY_LINE_COLOR, element.getOwnLineColor());
		result.put(JRBasePen.PROPERTY_LINE_STYLE, element.getOwnLineStyleValue());
		result.put(JRBasePen.PROPERTY_LINE_WIDTH, element.getOwnLineWidth());
		return result;
	}
	
	private List<ComboItem> createLineSpacingItems(){
		if (lineSpacingItems == null){
			lineSpacingItems = new ArrayList<ComboItem>();
			LineStyleEnum[] values = LineStyleEnum.class.getEnumConstants();
			lineSpacingItems.add(new ComboItem(Messages.getString("LineSpacing_nullEnum"), true,  ResourceManager.getImageDescriptor(this.getClass(),"/icons/resources/inherited.png"), 0, NullEnum.INHERITED, 0));
			ImageDescriptor[] images = new ImageDescriptor[] { JaspersoftStudioPlugin.getImageDescriptor("icons/resources/line-solid.png"),
																													JaspersoftStudioPlugin.getImageDescriptor("icons/resources/line-dashed.png"),
																												JaspersoftStudioPlugin.getImageDescriptor("icons/resources/line-dotted.png"),
																												JaspersoftStudioPlugin.getImageDescriptor("icons/resources/line-double.png"), };
			for(int i=0; i<values.length; i++){
				LineStyleEnum value = values[i];
				lineSpacingItems.add(new ComboItem(Messages.getString("LineStyle_".concat(value.getName())), true, images[i], i+1, value , i+1));
			}
		}
		return lineSpacingItems;
	}
	

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		// pen
		ColorPropertyDescriptor penLineColorD = new ColorPropertyDescriptor(JRBasePen.PROPERTY_LINE_COLOR,
				Messages.common_line_color, NullEnum.INHERITED);
		penLineColorD.setDescription(Messages.MLinePen_line_color_description);
		desc.add(penLineColorD);

		FloatPropertyDescriptor penLineWidthD = new FloatPropertyDescriptor(JRBasePen.PROPERTY_LINE_WIDTH,
				Messages.MLinePen_line_width);
		penLineWidthD.setDescription(Messages.MLinePen_line_width_description);
		desc.add(penLineWidthD);

		/*penLineStyleD = new LineStylePropertyDescriptor(JRBasePen.PROPERTY_LINE_STYLE, Messages.common_line_style,
				LineStyleEnum.class, NullEnum.INHERITED);*/
		penLineStyleD = new JSSPopupPropertyDescriptor(JRBasePen.PROPERTY_LINE_STYLE, Messages.common_line_style,
				LineStyleEnum.class, NullEnum.INHERITED, createLineSpacingItems());
		penLineStyleD.setDescription(Messages.MLinePen_line_style_description);
		desc.add(penLineStyleD);

		defaultsMap.put(JRBasePen.PROPERTY_LINE_STYLE, null);
		defaultsMap.put(JRBasePen.PROPERTY_LINE_COLOR, null);
		defaultsMap.put(JRBasePen.PROPERTY_LINE_WIDTH, null);
	}

	private static IPropertyDescriptor[] descriptors;
	private static Map<String, Object> defaultsMap;
	private static JSSPopupPropertyDescriptor penLineStyleD;

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
			if (id.equals(JRBasePen.PROPERTY_LINE_STYLE))
				return penLineStyleD.getEnumValue(linePen.getOwnLineStyleValue());
		}
		return null;
	}

	public Object getPropertyActualValue(Object id) {
		// pen
		JRPen linePen = (JRPen) getValue();
		if (linePen != null) {
			if (id.equals(JRBasePen.PROPERTY_LINE_COLOR))
				return Colors.getSWTRGB4AWTGBColor(linePen.getLineColor());
			if (id.equals(JRBasePen.PROPERTY_LINE_WIDTH))
				return linePen.getLineWidth();
			if (id.equals(JRBasePen.PROPERTY_LINE_STYLE))
				return penLineStyleD.getEnumValue(linePen.getLineStyleValue());
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
			if (id.equals(JRBasePen.PROPERTY_LINE_WIDTH))
				linePen.setLineWidth(((Float) value));
			else if (id.equals(JRBasePen.PROPERTY_LINE_COLOR)) {
				if (value == null)
					linePen.setLineColor(null);
				else if (value instanceof RGB)
					linePen.setLineColor(Colors.getAWT4SWTRGBColor((RGB) value));
			} else if (id.equals(JRBasePen.PROPERTY_LINE_STYLE))
				linePen.setLineStyle((LineStyleEnum) penLineStyleD.getEnumValue(value));
		}
	}

	public String getDisplayText() {
		return null;
	}

	public ImageDescriptor getImagePath() {
		return null;
	}

}
