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
package com.jaspersoft.studio.model;

import java.util.List;
import java.util.Map;

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

import com.jaspersoft.studio.jface.IntegerCellEditorValidator;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
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

		RWComboBoxPropertyDescriptor markupD = new RWComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_MARKUP, "Markup",
				ModelUtils.getMarkups(), NullEnum.INHERITED);
		markupD
				.setDescription("Specifies the name of the markup language used to embed style information into the text content. Supported values are none (plain text), styled (styled text), rtf (RTF format) and html (HTML format), but any custom made markup language can be used as long as there is a net.sf.jasperreports.engine.util.MarkupProcessorFactory implementation specified using a net.sf.jasperreports.markup.processor.factory.{markup} configuration property.");
		desc.add(markupD);

		ComboBoxPropertyDescriptor hAlignD = new ComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_HORIZONTAL_ALIGNMENT,
				"Horizontal Alignment", EnumHelper.getEnumNames(HorizontalAlignEnum.values(), NullEnum.INHERITED));
		hAlignD.setDescription("Horizontal image alignment.");
		desc.add(hAlignD);

		ComboBoxPropertyDescriptor vAlignD = new ComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT,
				"Vertical Alignment", EnumHelper.getEnumNames(VerticalAlignEnum.values(), NullEnum.INHERITED));
		vAlignD.setDescription("Vertical image alignment.");
		desc.add(vAlignD);

		ComboBoxPropertyDescriptor rotationD = new ComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_ROTATION, "Rotation",
				EnumHelper.getEnumNames(RotationEnum.values(), NullEnum.INHERITED));
		rotationD.setDescription("Type of rotation for the text object.");
		desc.add(rotationD);

		ComboBoxPropertyDescriptor lineSpacingD = new ComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_LINE_SPACING,
				"Line Spacing", EnumHelper.getEnumNames(LineSpacingEnum.values(), NullEnum.INHERITED));
		lineSpacingD.setDescription("Type of line spacing for the text object.");
		desc.add(lineSpacingD);

		CheckBoxPropertyDescriptor strikeThroughD = new CheckBoxPropertyDescriptor(JRDesignStyle.PROPERTY_STRIKE_THROUGH,
				"Strike Through", NullEnum.INHERITED);
		strikeThroughD.setDescription("Flag indicating if the font is strikethrough.");
		desc.add(strikeThroughD);

		CheckBoxPropertyDescriptor underlineD = new CheckBoxPropertyDescriptor(JRDesignStyle.PROPERTY_UNDERLINE,
				"Underline", NullEnum.INHERITED);
		underlineD.setDescription("Flag indicating if the font is underlined.");
		desc.add(underlineD);

		CheckBoxPropertyDescriptor italicD = new CheckBoxPropertyDescriptor(JRDesignStyle.PROPERTY_ITALIC, "Italic",
				NullEnum.INHERITED);
		italicD.setDescription("Flag indicating if the font is italic.");
		desc.add(italicD);

		CheckBoxPropertyDescriptor boldD = new CheckBoxPropertyDescriptor(JRDesignStyle.PROPERTY_BOLD, "Bold",
				NullEnum.INHERITED);
		boldD.setDescription("Flag indicating if the font is bold.");
		desc.add(boldD);

		RWComboBoxPropertyDescriptor fontNameD = new RWComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_FONT_NAME,
				"Font Name", ModelUtils.getFontNames(), NullEnum.INHERITED);
		fontNameD.setDescription("Name of the font.");
		desc.add(fontNameD);

		RWComboBoxPropertyDescriptor fontSizeD = new RWComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_FONT_SIZE,
				"Font Size", ModelUtils.getFontSizes(), NullEnum.INHERITED);
		fontSizeD.setDescription("Size of the font.");
		fontSizeD.setValidator(new IntegerCellEditorValidator());
		desc.add(fontSizeD);

		markupD.setCategory("Text Properties");
		hAlignD.setCategory("Text Properties");
		vAlignD.setCategory("Text Properties");
		rotationD.setCategory("Text Properties");
		lineSpacingD.setCategory("Text Properties");

		fontNameD.setCategory("Text Font");
		fontSizeD.setCategory("Text Font");
		boldD.setCategory("Text Font");
		italicD.setCategory("Text Font");
		underlineD.setCategory("Text Font");
		strikeThroughD.setCategory("Text Font");

		defaultsMap.put(JRBaseStyle.PROPERTY_HORIZONTAL_ALIGNMENT, null);
		defaultsMap.put(JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT, null);
		defaultsMap.put(JRBaseStyle.PROPERTY_ROTATION, null);
		defaultsMap.put(JRBaseStyle.PROPERTY_LINE_SPACING, null);

		defaultsMap.put(JRDesignStyle.PROPERTY_STRIKE_THROUGH, Boolean.FALSE);
		defaultsMap.put(JRDesignStyle.PROPERTY_UNDERLINE, Boolean.FALSE);
		defaultsMap.put(JRDesignStyle.PROPERTY_ITALIC, Boolean.FALSE);
		defaultsMap.put(JRDesignStyle.PROPERTY_BOLD, Boolean.FALSE);
		defaultsMap.put(JRDesignStyle.PROPERTY_FONT_NAME, "SansSerif");
		defaultsMap.put(JRDesignStyle.PROPERTY_FONT_SIZE, "10");
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

		if (id.equals(JRDesignStyle.PROPERTY_STRIKE_THROUGH))
			return jrElement.isOwnStrikeThrough();
		if (id.equals(JRDesignStyle.PROPERTY_UNDERLINE))
			return jrElement.isOwnUnderline();
		if (id.equals(JRDesignStyle.PROPERTY_ITALIC))
			return jrElement.isOwnItalic();
		if (id.equals(JRDesignStyle.PROPERTY_BOLD))
			return jrElement.isOwnBold();
		if (id.equals(JRDesignStyle.PROPERTY_FONT_NAME))
			return jrElement.getOwnFontName();
		if (id.equals(JRDesignStyle.PROPERTY_FONT_SIZE))
			return jrElement.getOwnFontSize() != null ? jrElement.getOwnFontSize().toString() : "";
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

		else if (id.equals(JRDesignStyle.PROPERTY_STRIKE_THROUGH))
			jrElement.setStrikeThrough((Boolean) value);
		else if (id.equals(JRDesignStyle.PROPERTY_UNDERLINE))
			jrElement.setUnderline((Boolean) value);
		else if (id.equals(JRDesignStyle.PROPERTY_ITALIC))
			jrElement.setItalic((Boolean) value);
		else if (id.equals(JRDesignStyle.PROPERTY_BOLD))
			jrElement.setBold((Boolean) value);
		else if (id.equals(JRDesignStyle.PROPERTY_FONT_NAME))
			jrElement.setFontName((String) value);
		else if (id.equals(JRDesignStyle.PROPERTY_FONT_SIZE))
			jrElement.setFontSize(new Integer((String) value));
		else
			super.setPropertyValue(id, value);
	}
}
