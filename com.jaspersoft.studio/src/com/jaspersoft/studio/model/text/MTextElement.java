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
package com.jaspersoft.studio.model.text;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignTextElement;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.LineSpacingEnum;
import net.sf.jasperreports.engine.type.RotationEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;

import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElementLineBox;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;
import com.jaspersoft.studio.utils.ModelUtils;

public abstract class MTextElement extends MGraphicElementLineBox {

	public MTextElement() {
		super();
	}

	public MTextElement(ANode parent, int newIndex) {
		super(parent, newIndex);
	}

	public MTextElement(ANode parent, JRDesignElement jrLine, int newIndex) {
		super(parent, jrLine, newIndex);
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		RWComboBoxPropertyDescriptor markupD = new RWComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_MARKUP,
				Messages.MTextElement_markup, ModelUtils.getMarkups(), NullEnum.INHERITED);
		markupD.setDescription(Messages.MTextElement_markup_description);
		desc.add(markupD);

		ComboBoxPropertyDescriptor hAlignD = new ComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_HORIZONTAL_ALIGNMENT,
				Messages.common_horizontal_alignment, EnumHelper.getEnumNames(HorizontalAlignEnum.values(),
						NullEnum.INHERITED));
		hAlignD.setDescription(Messages.MTextElement_horizontal_alignment_description);
		desc.add(hAlignD);

		ComboBoxPropertyDescriptor vAlignD = new ComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT,
				Messages.common_vertical_alignment, EnumHelper.getEnumNames(VerticalAlignEnum.values(),
						NullEnum.INHERITED));
		vAlignD.setDescription(Messages.MTextElement_vertical_alignment_description);
		desc.add(vAlignD);

		ComboBoxPropertyDescriptor rotationD = new ComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_ROTATION,
				Messages.common_rotation, new String[] {NullEnum.INHERITED.getName(),Messages.common_none,Messages.common_left,Messages.common_right,Messages.common_upside_down});
		rotationD.setDescription(Messages.MTextElement_rotation_description);
		desc.add(rotationD);

		ComboBoxPropertyDescriptor lineSpacingD = new ComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_LINE_SPACING,
				Messages.common_line_spacing, new String[] {NullEnum.INHERITED.getName(),Messages.common_single,LineSpacingEnum.ONE_AND_HALF.getName(),Messages.common_double});
		lineSpacingD.setDescription(Messages.MTextElement_line_spacing_description);
		desc.add(lineSpacingD);

		tFont = new MFont((JRFont) getValue());
		tFont.createPropertyDescriptors(desc, defaultsMap);

		markupD.setCategory(Messages.MTextElement_text_properties_category);
		hAlignD.setCategory(Messages.MTextElement_text_properties_category);
		vAlignD.setCategory(Messages.MTextElement_text_properties_category);
		rotationD.setCategory(Messages.MTextElement_text_properties_category);
		lineSpacingD.setCategory(Messages.MTextElement_text_properties_category);

		defaultsMap.put(JRBaseStyle.PROPERTY_HORIZONTAL_ALIGNMENT, null);
		defaultsMap.put(JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT, null);
		defaultsMap.put(JRBaseStyle.PROPERTY_ROTATION, null);
		defaultsMap.put(JRBaseStyle.PROPERTY_LINE_SPACING, null);
	}

	private MFont tFont;

	private MFont getMFont() {
		if (tFont == null) {
			tFont = new MFont((JRFont) getValue());
			setChildListener(tFont);
		}
		return tFont;
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignTextElement jrElement = (JRDesignTextElement) getValue();

		if (id.equals(JRDesignStyle.PROPERTY_MARKUP))
			return jrElement.getOwnMarkup();

		if (id.equals(JRBaseStyle.PROPERTY_HORIZONTAL_ALIGNMENT))
			return EnumHelper.getValue(jrElement.getOwnHorizontalAlignmentValue(), 1, true);
		if (id.equals(JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT))
			return EnumHelper.getValue(jrElement.getOwnVerticalAlignmentValue(), 1, true);
		if (id.equals(JRBaseStyle.PROPERTY_ROTATION))
			return EnumHelper.getValue(jrElement.getOwnRotationValue(), 0, true);
		if (id.equals(JRBaseStyle.PROPERTY_LINE_SPACING))
			return EnumHelper.getValue(jrElement.getOwnLineSpacingValue(), 0, true);

		if (getMFont() != null) {
			Object val = tFont.getPropertyValue(id);
			if (val != null)
				return val;
		}

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignTextElement jrElement = (JRDesignTextElement) getValue();
		if (id.equals(JRBaseStyle.PROPERTY_MARKUP))
			jrElement.setMarkup((String) value);

		else if (id.equals(JRBaseStyle.PROPERTY_HORIZONTAL_ALIGNMENT))
			jrElement.setHorizontalAlignment((HorizontalAlignEnum) EnumHelper.getSetValue(HorizontalAlignEnum.values(),
					value, 1, true));
		else if (id.equals(JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT))
			jrElement.setVerticalAlignment((VerticalAlignEnum) EnumHelper.getSetValue(VerticalAlignEnum.values(), value, 1,
					true));
		else if (id.equals(JRBaseStyle.PROPERTY_ROTATION))
			jrElement.setRotation((RotationEnum) EnumHelper.getSetValue(RotationEnum.values(), value, 0, true));
		else if (id.equals(JRBaseStyle.PROPERTY_LINE_SPACING))
			jrElement.setLineSpacing((LineSpacingEnum) EnumHelper.getSetValue(LineSpacingEnum.values(), value, 0, true));

		getMFont().setPropertyValue(id, value);

		super.setPropertyValue(id, value);
	}
}
