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
package com.jaspersoft.studio.model.text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.JRStaticText;
import net.sf.jasperreports.engine.base.JRBaseParagraph;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignTextElement;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.RotationEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IRotatable;
import com.jaspersoft.studio.model.MGraphicElementLineBox;
import com.jaspersoft.studio.property.descriptor.JRPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.HAlignPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.JSSEnumPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.RotationPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.VAlignPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;
import com.jaspersoft.studio.utils.ModelUtils;

public abstract class MTextElement extends MGraphicElementLineBox implements IRotatable {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

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
	public HashMap<String,Object> getStylesDescriptors() {
		HashMap<String, Object> result = super.getStylesDescriptors();
		if (getValue() == null)
			return result;
		result.putAll(tFont.getStylesDescriptors());
		return result;
	}


	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		RWComboBoxPropertyDescriptor markupD = new RWComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_MARKUP,
				Messages.MTextElement_markup, ModelUtils.getMarkups(getJasperConfiguration()), NullEnum.INHERITED);
		markupD.setDescription(Messages.MTextElement_markup_description);
		desc.add(markupD);

		hAlignD = new HAlignPropertyDescriptor(JRBaseStyle.PROPERTY_HORIZONTAL_ALIGNMENT,
				Messages.common_horizontal_alignment, HorizontalAlignEnum.class, NullEnum.INHERITED);
		hAlignD.setDescription(Messages.MTextElement_horizontal_alignment_description);
		desc.add(hAlignD);

		vAlignD = new VAlignPropertyDescriptor(JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT, Messages.common_vertical_alignment,
				VerticalAlignEnum.class, NullEnum.INHERITED);
		vAlignD.setDescription(Messages.MTextElement_vertical_alignment_description);
		desc.add(vAlignD);

		rotationD = new RotationPropertyDescriptor(JRBaseStyle.PROPERTY_ROTATION, Messages.common_rotation,
				RotationEnum.class, NullEnum.INHERITED);
		rotationD.setDescription(Messages.MTextElement_rotation_description);
		desc.add(rotationD);

		JRPropertyDescriptor paragraph = new JRPropertyDescriptor("paragraph", "Paragraph");
		desc.add(paragraph);

		tFont = new MFont((JRFont) getValue());
		tFont.setJasperConfiguration(getJasperConfiguration());
		tFont.createPropertyDescriptors(desc, defaultsMap);

		paragraph.setCategory(Messages.MTextElement_text_properties_category);
		markupD.setCategory(Messages.MTextElement_text_properties_category);
		hAlignD.setCategory(Messages.MTextElement_text_properties_category);
		vAlignD.setCategory(Messages.MTextElement_text_properties_category);
		rotationD.setCategory(Messages.MTextElement_text_properties_category);

		defaultsMap.put(JRBaseStyle.PROPERTY_HORIZONTAL_ALIGNMENT, null);
		defaultsMap.put(JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT, null);
		defaultsMap.put(JRBaseStyle.PROPERTY_ROTATION, null);
	}

	private MFont tFont;
	private MParagraph mParagraph;
	private static JSSEnumPropertyDescriptor hAlignD;
	private static JSSEnumPropertyDescriptor vAlignD;
	private static JSSEnumPropertyDescriptor rotationD;

	private MFont getMFont() {
		if (tFont == null) {
			tFont = new MFont((JRFont) getValue());
			tFont.setJasperConfiguration(getJasperConfiguration());
			setChildListener(tFont);
		}
		return tFont;
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignTextElement jrElement = (JRDesignTextElement) getValue();

		if (id.equals(JRDesignStyle.PROPERTY_MARKUP))
			return jrElement.getOwnMarkup();

		if (id.equals("paragraph")) {
			if (mParagraph == null) {
				mParagraph = new MParagraph((JRBaseParagraph) jrElement.getParagraph());
				setChildListener(mParagraph);
			}
			return mParagraph;
		}

		if (id.equals(JRBaseStyle.PROPERTY_HORIZONTAL_ALIGNMENT))
			return hAlignD.getEnumValue(jrElement.getOwnHorizontalAlignmentValue());
		if (id.equals(JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT))
			return vAlignD.getEnumValue(jrElement.getOwnVerticalAlignmentValue());
		if (id.equals(JRBaseStyle.PROPERTY_ROTATION))
			return rotationD.getEnumValue(jrElement.getOwnRotationValue());

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
			jrElement.setHorizontalAlignment((HorizontalAlignEnum) hAlignD.getEnumValue(value));
		else if (id.equals(JRBaseStyle.PROPERTY_VERTICAL_ALIGNMENT)) {
			VerticalAlignEnum va = (VerticalAlignEnum) EnumHelper.getSetValue(VerticalAlignEnum.values(), value, 1, true);
			if (va != null && va.equals(VerticalAlignEnum.JUSTIFIED))
				va = VerticalAlignEnum.MIDDLE;
			jrElement.setVerticalAlignment(va);
		} else if (id.equals(JRBaseStyle.PROPERTY_ROTATION))
			jrElement.setRotation((RotationEnum) rotationD.getEnumValue(value));

		getMFont().setPropertyValue(id, value);

		super.setPropertyValue(id, value);
	}
}
