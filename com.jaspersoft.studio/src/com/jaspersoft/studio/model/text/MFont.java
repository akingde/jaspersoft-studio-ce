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

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.base.JRBaseFont;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.jface.IntegerCellEditorValidator;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.utils.ModelUtils;

public class MFont extends APropertyNode {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

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
				Messages.common_font_name, ModelUtils.getFontNames(), NullEnum.INHERITED);
		fontNameD.setDescription(Messages.MFont_font_name_description);
		desc.add(fontNameD);

		RWComboBoxPropertyDescriptor fontSizeD = new RWComboBoxPropertyDescriptor(JRBaseFont.PROPERTY_FONT_SIZE,
				Messages.common_font_size, ModelUtils.getFontSizes(), NullEnum.INHERITED);
		fontSizeD.setDescription(Messages.MFont_font_size_description);
		fontSizeD.setValidator(new IntegerCellEditorValidator());
		desc.add(fontSizeD);

		RWComboBoxPropertyDescriptor pdfFontNameD = new RWComboBoxPropertyDescriptor(JRBaseFont.PROPERTY_PDF_FONT_NAME,
				Messages.MFont_pdf_font_name, ModelUtils.getPDFFontNames(), NullEnum.INHERITED);
		pdfFontNameD.setDescription(Messages.MFont_pdf_font_name_description);
		desc.add(pdfFontNameD);

		RWComboBoxPropertyDescriptor pdfEncodingD = new RWComboBoxPropertyDescriptor(JRBaseFont.PROPERTY_PDF_ENCODING,
				Messages.MFont_pdf_encoding, ModelUtils.getPDFEncodings(), NullEnum.INHERITED);
		pdfEncodingD.setDescription(Messages.MFont_pdf_encoding_description);
		desc.add(pdfEncodingD);

		CheckBoxPropertyDescriptor boldD = new CheckBoxPropertyDescriptor(JRBaseFont.PROPERTY_BOLD, Messages.common_bold,
				NullEnum.INHERITED);
		boldD.setDescription(Messages.MFont_bold_description);
		desc.add(boldD);

		CheckBoxPropertyDescriptor italicD = new CheckBoxPropertyDescriptor(JRBaseFont.PROPERTY_ITALIC,
				Messages.common_italic, NullEnum.INHERITED);
		italicD.setDescription(Messages.MFont_italic_description);
		desc.add(italicD);

		CheckBoxPropertyDescriptor underlineD = new CheckBoxPropertyDescriptor(JRBaseFont.PROPERTY_UNDERLINE,
				Messages.common_underline, NullEnum.INHERITED);
		underlineD.setDescription(Messages.MFont_underline_description);
		desc.add(underlineD);

		CheckBoxPropertyDescriptor strikeTroughD = new CheckBoxPropertyDescriptor(JRBaseFont.PROPERTY_STRIKE_THROUGH,
				Messages.common_strike_trough, NullEnum.INHERITED);
		strikeTroughD.setDescription(Messages.MFont_strike_trough_description);
		desc.add(strikeTroughD);

		CheckBoxPropertyDescriptor pdfEmbedD = new CheckBoxPropertyDescriptor(JRBaseFont.PROPERTY_PDF_EMBEDDED,
				Messages.MFont_pdf_embedded, NullEnum.INHERITED);
		pdfEmbedD.setDescription(Messages.MFont_pdf_embedded_description);
		desc.add(pdfEmbedD);

		defaultsMap.put(JRBaseFont.PROPERTY_STRIKE_THROUGH, Boolean.FALSE);
		defaultsMap.put(JRBaseFont.PROPERTY_UNDERLINE, Boolean.FALSE);
		defaultsMap.put(JRBaseFont.PROPERTY_ITALIC, Boolean.FALSE);
		defaultsMap.put(JRBaseFont.PROPERTY_BOLD, Boolean.FALSE);
		defaultsMap.put(JRBaseFont.PROPERTY_FONT_NAME, "SansSerif"); //$NON-NLS-1$
		defaultsMap.put(JRBaseFont.PROPERTY_FONT_SIZE, "10"); //$NON-NLS-1$

		fontNameD.setCategory(Messages.common_font);
		fontSizeD.setCategory(Messages.common_font);
		pdfFontNameD.setCategory(Messages.common_font);
		pdfEncodingD.setCategory(Messages.common_font);
		boldD.setCategory(Messages.common_font);
		italicD.setCategory(Messages.common_font);
		underlineD.setCategory(Messages.common_font);
		strikeTroughD.setCategory(Messages.common_font);
		pdfEmbedD.setCategory(Messages.common_font);

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
			return jrElement.getOwnFontSize() != null ? jrElement.getOwnFontSize().toString() : ""; //$NON-NLS-1$
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
