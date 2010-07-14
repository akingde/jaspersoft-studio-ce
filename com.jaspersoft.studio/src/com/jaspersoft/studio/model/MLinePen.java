/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.base.JRBasePen;
import net.sf.jasperreports.engine.type.LineStyleEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.property.descriptor.FloatPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.color.ColorPropertyDescriptor;
import com.jaspersoft.studio.utils.Colors;
import com.jaspersoft.studio.utils.EnumHelper;

public class MLinePen extends APropertyNode implements IPropertySource {

	public MLinePen(JRPen linePen) {
		super();
		setValue(linePen);
	}

	@Override
	protected void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		// pen
		ColorPropertyDescriptor penLineColorD = new ColorPropertyDescriptor(JRBasePen.PROPERTY_LINE_COLOR, "Line Color",
				NullEnum.INHERITED);
		penLineColorD
				.setDescription("Line color for the pen. Hexadecimal formatted values preceded by the # character or decimal values are accepted along with the following predefined color values: black, blue, cyan, darkGray, gray, green, lightGray, magenta, orange, pink, red, yellow, white.");
		desc.add(penLineColorD);

		FloatPropertyDescriptor penLineWidthD = new FloatPropertyDescriptor(JRBasePen.PROPERTY_LINE_WIDTH, "Line Width");
		penLineWidthD.setDescription("The line width of the pen.");
		desc.add(penLineWidthD);

		ComboBoxPropertyDescriptor penLineStyleD = new ComboBoxPropertyDescriptor(JRBasePen.PROPERTY_LINE_STYLE,
				"Line Style", EnumHelper.getEnumNames(LineStyleEnum.values(), NullEnum.INHERITED));
		penLineStyleD.setDescription("The line style of the pen.");
		desc.add(penLineStyleD);

		defaultsMap.put(JRBasePen.PROPERTY_LINE_STYLE, null);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	@Override
	public Object getPropertyValue(Object id) {
		// pen
		JRPen linePen = (JRPen) getValue();
		if (linePen != null) {
			if (id.equals(JRBasePen.PROPERTY_LINE_COLOR))
				return Colors.getSWTRGB4AWTGBColor(linePen.getOwnLineColor());
			if (id.equals(JRBasePen.PROPERTY_LINE_WIDTH))
				return linePen.getOwnLineWidth();
			if (id.equals(JRBasePen.PROPERTY_LINE_STYLE))
				return EnumHelper.getValue(linePen.getOwnLineStyleValue(), 0, true);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void setPropertyValue(Object id, Object value) {
		JRPen linePen = (JRPen) getValue();
		if (linePen != null) {
			if (id.equals(JRBasePen.PROPERTY_LINE_WIDTH))
				linePen.setLineWidth(((Float) value));
			else if (id.equals(JRBasePen.PROPERTY_LINE_COLOR)) {
				if (value instanceof RGB)
					linePen.setLineColor(Colors.getAWT4SWTRGBColor((RGB) value));
			} else if (id.equals(JRBasePen.PROPERTY_LINE_STYLE))
				linePen.setLineStyle((LineStyleEnum) EnumHelper.getSetValue(LineStyleEnum.values(), value, 0, true));
		}
	}

	@Override
	public String getDisplayText() {
		return null;
	}

	@Override
	public ImageDescriptor getImagePath() {
		return null;
	}

}
