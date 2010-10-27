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
package com.jaspersoft.studio.model.text;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.base.JRBaseFont;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.jface.IntegerCellEditorValidator;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.utils.ModelUtils;

public class MFont extends APropertyNode {
	public MFont(JRFont value) {
		super();
		setValue(value);
	}

	public ImageDescriptor getImagePath() {
		return null;
	}

	public String getDisplayText() {
		return null;
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		RWComboBoxPropertyDescriptor fontNameD = new RWComboBoxPropertyDescriptor(JRBaseFont.PROPERTY_FONT_NAME,
				"Font Name", ModelUtils.getFontNames(), NullEnum.INHERITED);
		fontNameD.setDescription("Name of the font.");
		desc.add(fontNameD);

		RWComboBoxPropertyDescriptor fontSizeD = new RWComboBoxPropertyDescriptor(JRBaseFont.PROPERTY_FONT_SIZE,
				"Font Size", ModelUtils.getFontSizes(), NullEnum.INHERITED);
		fontSizeD.setDescription("Size of the font.");
		fontSizeD.setValidator(new IntegerCellEditorValidator());
		desc.add(fontSizeD);

		RWComboBoxPropertyDescriptor pdfFontNameD = new RWComboBoxPropertyDescriptor(JRBaseFont.PROPERTY_PDF_FONT_NAME,
				"PDF Font Name", ModelUtils.getPDFFontNames(), NullEnum.INHERITED);
		pdfFontNameD.setDescription("Name of the PDF font.");
		desc.add(pdfFontNameD);

		RWComboBoxPropertyDescriptor pdfEncodingD = new RWComboBoxPropertyDescriptor(JRBaseFont.PROPERTY_PDF_ENCODING,
				"PDF Encoding", ModelUtils.getPDFEncodings(), NullEnum.INHERITED);
		pdfEncodingD.setDescription("PDF encoding.");
		desc.add(pdfEncodingD);

		CheckBoxPropertyDescriptor boldD = new CheckBoxPropertyDescriptor(JRBaseFont.PROPERTY_BOLD, "Bold",
				NullEnum.INHERITED);
		boldD.setDescription("Flag indicating if the font is bold.");
		desc.add(boldD);

		CheckBoxPropertyDescriptor italicD = new CheckBoxPropertyDescriptor(JRBaseFont.PROPERTY_ITALIC, "Italic",
				NullEnum.INHERITED);
		italicD.setDescription("Flag indicating if the font is italic.");
		desc.add(italicD);

		CheckBoxPropertyDescriptor underlineD = new CheckBoxPropertyDescriptor(JRBaseFont.PROPERTY_UNDERLINE, "Underline",
				NullEnum.INHERITED);
		underlineD.setDescription("Flag indicating if the font is underlined.");
		desc.add(underlineD);

		CheckBoxPropertyDescriptor strikeTroughD = new CheckBoxPropertyDescriptor(JRBaseFont.PROPERTY_STRIKE_THROUGH,
				"Strike Through", NullEnum.INHERITED);
		strikeTroughD.setDescription("Flag indicating if the font is strikethrough.");
		desc.add(strikeTroughD);

		CheckBoxPropertyDescriptor pdfEmbedD = new CheckBoxPropertyDescriptor(JRBaseFont.PROPERTY_PDF_EMBEDDED,
				"PDF Embedded", NullEnum.INHERITED);
		pdfEmbedD.setDescription("PDF embedded.");
		desc.add(pdfEmbedD);

		defaultsMap.put(JRBaseFont.PROPERTY_STRIKE_THROUGH, Boolean.FALSE);
		defaultsMap.put(JRBaseFont.PROPERTY_UNDERLINE, Boolean.FALSE);
		defaultsMap.put(JRBaseFont.PROPERTY_ITALIC, Boolean.FALSE);
		defaultsMap.put(JRBaseFont.PROPERTY_BOLD, Boolean.FALSE);
		defaultsMap.put(JRBaseFont.PROPERTY_FONT_NAME, "SansSerif");
		defaultsMap.put(JRBaseFont.PROPERTY_FONT_SIZE, "10");

		fontNameD.setCategory("Font");
		fontSizeD.setCategory("Font");
		pdfFontNameD.setCategory("Font");
		pdfEncodingD.setCategory("Font");
		boldD.setCategory("Font");
		italicD.setCategory("Font");
		underlineD.setCategory("Font");
		strikeTroughD.setCategory("Font");
		pdfEmbedD.setCategory("Font");

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
	public Object getPropertyValue(Object id) {
		JRFont jrElement = (JRFont) getValue();
		if (id.equals(JRBaseFont.PROPERTY_BOLD))
			return jrElement.isOwnBold();
		if (id.equals(JRBaseFont.PROPERTY_UNDERLINE))
			return jrElement.isOwnUnderline();
		if (id.equals(JRBaseFont.PROPERTY_ITALIC))
			return jrElement.isOwnItalic();
		if (id.equals(JRBaseFont.PROPERTY_STRIKE_THROUGH))
			return jrElement.isOwnStrikeThrough();
		if (id.equals(JRBaseFont.PROPERTY_PDF_EMBEDDED))
			return jrElement.isOwnPdfEmbedded();
		if (id.equals(JRBaseFont.PROPERTY_FONT_NAME))
			return jrElement.getOwnFontName();
		if (id.equals(JRBaseFont.PROPERTY_PDF_FONT_NAME))
			return jrElement.getOwnPdfFontName();
		if (id.equals(JRBaseFont.PROPERTY_PDF_ENCODING))
			return ModelUtils.getKey4PDFEncoding(jrElement.getOwnPdfEncoding());
		if (id.equals(JRBaseFont.PROPERTY_FONT_SIZE))
			return jrElement.getOwnFontSize() != null ? jrElement.getOwnFontSize().toString() : "";
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRFont jrElement = (JRFont) getValue();
		if (id.equals(JRBaseFont.PROPERTY_BOLD))
			jrElement.setBold((Boolean) value);
		else if (id.equals(JRBaseFont.PROPERTY_UNDERLINE))
			jrElement.setUnderline((Boolean) value);
		else if (id.equals(JRBaseFont.PROPERTY_ITALIC))
			jrElement.setItalic((Boolean) value);
		else if (id.equals(JRBaseFont.PROPERTY_STRIKE_THROUGH))
			jrElement.setStrikeThrough((Boolean) value);
		else if (id.equals(JRBaseFont.PROPERTY_PDF_EMBEDDED))
			jrElement.setPdfEmbedded((Boolean) value);
		else if (id.equals(JRBaseFont.PROPERTY_FONT_NAME))
			jrElement.setFontName((String) value);
		else if (id.equals(JRBaseFont.PROPERTY_FONT_SIZE))
			jrElement.setFontSize(new Integer((String) value));
		else if (id.equals(JRBaseFont.PROPERTY_PDF_FONT_NAME))
			jrElement.setPdfFontName((String) value);
		else if (id.equals(JRBaseFont.PROPERTY_PDF_ENCODING))
			jrElement.setPdfEncoding(ModelUtils.getPDFEncoding2key((String) value));
	}
}
