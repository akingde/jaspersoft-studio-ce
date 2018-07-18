/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.text;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.model.IRotatable;
import com.jaspersoft.studio.model.MGraphicElementLineBox;
import com.jaspersoft.studio.properties.view.validation.ValidationError;
import com.jaspersoft.studio.property.JSSStyleResolver;
import com.jaspersoft.studio.property.descriptor.JRPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.RotationPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.TextHAlignPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.TextVAlignPropertyDescriptor;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.base.JRBaseFont;
import net.sf.jasperreports.engine.base.JRBaseParagraph;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignFont;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignTextElement;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.RotationEnum;
import net.sf.jasperreports.engine.type.VerticalTextAlignEnum;
import net.sf.jasperreports.engine.util.StyleResolver;

public abstract class MTextElement extends MGraphicElementLineBox implements IRotatable {

	public static final String PARAGRAPH = "paragraph"; //$NON-NLS-1$

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	private static TextHAlignPropertyDescriptor hAlignD;
	
	private static TextVAlignPropertyDescriptor vAlignD;
	
	private static RotationPropertyDescriptor rotationD;
	
	private MFont tFont;
	
	private MParagraph mParagraph;

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
	public HashMap<String, Object> getStylesDescriptors() {
		HashMap<String, Object> result = super.getStylesDescriptors();
		if (getValue() == null)
			return result;
		JRDesignTextElement jrElement = (JRDesignTextElement) getValue();
		result.put(PARAGRAPH, getPropertyValue(PARAGRAPH));
		result.put(JRDesignStyle.PROPERTY_VERTICAL_TEXT_ALIGNMENT, jrElement.getOwnVerticalTextAlign());
		result.put(JRDesignStyle.PROPERTY_HORIZONTAL_TEXT_ALIGNMENT, jrElement.getOwnHorizontalTextAlign());
		result.put(JRDesignStyle.PROPERTY_ROTATION, jrElement.getOwnRotationValue());
		result.put(JRDesignStyle.PROPERTY_MARKUP, jrElement.getOwnMarkup());
		result.putAll(tFont.getStylesDescriptors());
		return result;
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		super.createPropertyDescriptors(desc);

		RWComboBoxPropertyDescriptor markupD = new RWComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_MARKUP,
				Messages.MTextElement_markup, ModelUtils.getMarkups(getJasperConfiguration()), NullEnum.INHERITED);
		markupD.setDescription(Messages.MTextElement_markup_description);
		desc.add(markupD);

		hAlignD = new TextHAlignPropertyDescriptor(JRBaseStyle.PROPERTY_HORIZONTAL_TEXT_ALIGNMENT,
				Messages.common_horizontal_alignment, NullEnum.INHERITED);
		hAlignD.setDescription(Messages.MTextElement_horizontal_alignment_description);
		desc.add(hAlignD);

		vAlignD = new TextVAlignPropertyDescriptor(JRBaseStyle.PROPERTY_VERTICAL_TEXT_ALIGNMENT,
				Messages.common_vertical_alignment, NullEnum.INHERITED);
		vAlignD.setDescription(Messages.MTextElement_vertical_alignment_description);
		desc.add(vAlignD);

		rotationD = new RotationPropertyDescriptor(JRBaseStyle.PROPERTY_ROTATION, Messages.common_rotation,
				NullEnum.INHERITED);
		rotationD.setDescription(Messages.MTextElement_rotation_description);
		desc.add(rotationD);

		JRPropertyDescriptor paragraph = new JRPropertyDescriptor(PARAGRAPH, "Paragraph");
		desc.add(paragraph);

		setHelpPrefix(desc, "net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#textElement"); //$NON-NLS-1$

		getMFont().createPropertyDescriptors(desc);

		paragraph.setCategory(Messages.MTextElement_text_properties_category);
		markupD.setCategory(Messages.MTextElement_text_properties_category);
		hAlignD.setCategory(Messages.MTextElement_text_properties_category);
		vAlignD.setCategory(Messages.MTextElement_text_properties_category);
		rotationD.setCategory(Messages.MTextElement_text_properties_category);
	}
	
	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();
		
		defaultsMap.put(JRBaseStyle.PROPERTY_HORIZONTAL_TEXT_ALIGNMENT, new DefaultValue(null, true));
		defaultsMap.put(JRBaseStyle.PROPERTY_VERTICAL_TEXT_ALIGNMENT, new DefaultValue(null, true));
		defaultsMap.put(JRBaseStyle.PROPERTY_ROTATION, new DefaultValue(null, true));
		
		defaultsMap.putAll(getMFont().getDefaultsPropertiesMap());
		
		return defaultsMap;
	}

	private MFont getMFont() {
		if (tFont == null) {
			tFont = new MFont((JRFont) getValue());
			tFont.setJasperConfiguration(getJasperConfiguration());
			setChildListener(tFont);
		}
		return tFont;
	}

	@Override
	public Object getPropertyActualValue(Object id) {
		JRDesignTextElement jrElement = (JRDesignTextElement) getValue();
		JSSStyleResolver resolver = getStyleResolver();
		
		if (id.equals(JRDesignStyle.PROPERTY_MARKUP)){
			return resolver.getMarkup(jrElement);
		} else if (id.equals(JRBaseStyle.PROPERTY_HORIZONTAL_TEXT_ALIGNMENT)) {
			if (hAlignD == null)
				getPropertyDescriptors();
			return hAlignD.getIntValue(resolver.getHorizontalTextAlign(jrElement));
		} else if (id.equals(JRBaseStyle.PROPERTY_VERTICAL_TEXT_ALIGNMENT)) {
			if (vAlignD == null)
				getPropertyDescriptors();
			return vAlignD.getIntValue(resolver.getVerticalTextAlign(jrElement));
		} else	if (id.equals(JRBaseStyle.PROPERTY_ROTATION)) {
			if (rotationD == null)
				getPropertyDescriptors();
			return rotationD.getIntValue(resolver.getRotationValue(jrElement));
		}
		
		if (getMFont() != null) {
			Object val = tFont.getPropertyActualValue(id);
			if (val != null)
				return val;
		}

		return super.getPropertyActualValue(id);
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignTextElement jrElement = (JRDesignTextElement) getValue();

		if (id.equals(JRDesignStyle.PROPERTY_MARKUP))
			return jrElement.getOwnMarkup();

		if (id.equals(PARAGRAPH)) {
			if (mParagraph == null) {
				mParagraph = new MParagraph(this, (JRBaseParagraph) jrElement.getParagraph());
				setChildListener(mParagraph);
			}
			return mParagraph;
		}

		if (id.equals(JRBaseStyle.PROPERTY_HORIZONTAL_TEXT_ALIGNMENT)) {
			if (hAlignD == null)
				getPropertyDescriptors();
			HorizontalTextAlignEnum ownValue = jrElement.getOwnHorizontalTextAlign();
			if (ownValue == null) return null;
			else return hAlignD.getIntValue(ownValue);
		}
		if (id.equals(JRBaseStyle.PROPERTY_VERTICAL_TEXT_ALIGNMENT)) {
			if (vAlignD == null)
				getPropertyDescriptors();
			VerticalTextAlignEnum ownValue = jrElement.getOwnVerticalTextAlign();
			if (ownValue == null) return null;
			else return vAlignD.getIntValue(ownValue);
		}
		if (id.equals(JRBaseStyle.PROPERTY_ROTATION)) {
			if (rotationD == null)
				getPropertyDescriptors();
			RotationEnum ownValue = jrElement.getOwnRotationValue();
			if (ownValue == null) return null;
			else return rotationD.getIntValue(ownValue);
		}

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

		else if (id.equals(JRBaseStyle.PROPERTY_HORIZONTAL_TEXT_ALIGNMENT))
			jrElement.setHorizontalTextAlign((HorizontalTextAlignEnum) hAlignD.getEnumValue(value));
		else if (id.equals(JRBaseStyle.PROPERTY_VERTICAL_TEXT_ALIGNMENT)) {
			VerticalTextAlignEnum va = (VerticalTextAlignEnum) vAlignD.getEnumValue(value);
			jrElement.setVerticalTextAlign(va);
		} else if (id.equals(JRBaseStyle.PROPERTY_ROTATION))
			jrElement.setRotation((RotationEnum) rotationD.getEnumValue(value));

		getMFont().setPropertyValue(id, value);

		super.setPropertyValue(id, value);
	}

	/**
	 * Return the graphical properties for an MTextElement
	 */
	public HashSet<String> generateGraphicalProperties() {
		HashSet<String> result = super.generateGraphicalProperties();
		result.add(JRBaseStyle.PROPERTY_HORIZONTAL_TEXT_ALIGNMENT);
		result.add(JRBaseStyle.PROPERTY_ROTATION);

		result.add(JRBaseParagraph.PROPERTY_LINE_SPACING);
		result.add(JRBaseParagraph.PROPERTY_LINE_SPACING_SIZE);
		result.add(JRBaseParagraph.PROPERTY_FIRST_LINE_INDENT);
		result.add(JRBaseParagraph.PROPERTY_LEFT_INDENT);
		result.add(JRBaseParagraph.PROPERTY_RIGHT_INDENT);
		result.add(JRBaseParagraph.PROPERTY_SPACING_BEFORE);
		result.add(JRBaseParagraph.PROPERTY_SPACING_AFTER);
		result.add(JRBaseParagraph.PROPERTY_TAB_STOP_WIDTH);

		result.add(JRBaseStyle.PROPERTY_VERTICAL_TEXT_ALIGNMENT);
		result.add(JRBaseFont.PROPERTY_BOLD);
		result.add(JRBaseFont.PROPERTY_UNDERLINE);
		result.add(JRBaseFont.PROPERTY_STRIKE_THROUGH);
		result.add(JRBaseFont.PROPERTY_ITALIC);
		result.add(JRBaseFont.PROPERTY_FONT_SIZE);
		result.add(JRBaseFont.PROPERTY_FONT_NAME);
		return result;
	}

	@Override
	public void trasnferProperties(JRElement target) {
		super.trasnferProperties(target);
		JRDesignTextElement jrSource = (JRDesignTextElement) getValue();
		JRDesignTextElement jrTarget = (JRDesignTextElement) target;

		jrTarget.setHorizontalTextAlign(jrSource.getOwnHorizontalTextAlign());
		jrTarget.setVerticalTextAlign(jrSource.getOwnVerticalTextAlign());
		jrTarget.setMarkup(getStringClone(jrSource.getOwnMarkup()));
		jrTarget.setRotation(jrSource.getOwnRotationValue());

		jrTarget.setBold(jrSource.isOwnBold());
		jrTarget.setItalic(jrSource.isOwnItalic());
		jrTarget.setUnderline(jrSource.isOwnUnderline());
		jrTarget.setStrikeThrough(jrSource.isOwnStrikeThrough());
		jrTarget.setPdfEmbedded(jrSource.isOwnPdfEmbedded());
		jrTarget.setFontName(getStringClone(jrSource.getOwnFontName()));
		jrTarget.setFontSize(jrSource.getOwnFontsize());
		jrTarget.setPdfFontName(getStringClone(jrSource.getOwnPdfFontName()));
		jrTarget.setPdfEncoding(getStringClone(jrSource.getOwnPdfEncoding()));

		JRBaseParagraph jrTargetParagraph = (JRBaseParagraph) jrTarget.getParagraph();
		JRBaseParagraph jrSourceParagraph = (JRBaseParagraph) jrSource.getParagraph();
		if (jrTargetParagraph != null && jrSourceParagraph != null) {
			jrTargetParagraph.setLineSpacing(jrSourceParagraph.getOwnLineSpacing());
			jrTargetParagraph.setLineSpacingSize(jrSourceParagraph.getOwnLineSpacingSize());
			jrTargetParagraph.setFirstLineIndent(jrSourceParagraph.getOwnFirstLineIndent());
			jrTargetParagraph.setLeftIndent(jrSourceParagraph.getOwnLeftIndent());
			jrTargetParagraph.setRightIndent(jrSourceParagraph.getOwnRightIndent());
			jrTargetParagraph.setSpacingAfter(jrSourceParagraph.getOwnSpacingAfter());
			jrTargetParagraph.setSpacingBefore(jrTargetParagraph.getOwnSpacingBefore());
			jrTargetParagraph.setTabStopWidth(jrSourceParagraph.getOwnTabStopWidth());
		}
	}

	private StyleResolver sr;

	/**
	 * A text element shouldn't provide a pdf font, since it is deprecated. So the validation return an error message (in
	 * addition to the ones provided by the superclass) when the pdf font is used directly by the element or inherited by
	 * a style
	 */
	@Override
	protected List<ValidationError> doValidation() {
		List<ValidationError> errors = super.doValidation();
		JRFont font = (JRFont) getValue();
		// check if the element is using a pdf font
		if (font.getOwnPdfFontName() != null || (font.isOwnPdfEmbedded() != null && font.isOwnPdfEmbedded())) {
			if (errors == null)
				errors = new ArrayList<ValidationError>();
			List<String> props = new ArrayList<String>();
			props.add(JRDesignFont.PROPERTY_PDF_EMBEDDED);
			errors.add(new ValidationError(props, Messages.MTextElement_pdfError));
		} else {
			if (sr == null)
				sr = new StyleResolver(getJasperConfiguration());
			// The element is not using a pdf font, check if it is inherited from a style
			JRStyle baseStyle = sr.getBaseStyle(font);
			String inheritedFromStyle = getPdfFontName(baseStyle);
			if (inheritedFromStyle != null) {
				if (errors == null)
					errors = new ArrayList<ValidationError>();
				List<String> props = new ArrayList<String>();
				props.add(JRDesignFont.PROPERTY_STYLE);
				errors.add(new ValidationError(props,
						MessageFormat.format(Messages.MTextElement_pdfErrorStyle, new Object[] { inheritedFromStyle })));
			}
		}
		return errors;
	}

	/**
	 * Check recursively if a style provide a pdf font directly or inherited from a parent style
	 * 
	 * @param style
	 *          the current style
	 * @return the name of the style that provide a pdf font or null if no styles provide it
	 */
	private String getPdfFontName(JRStyle style) {
		if (style == null)
			return null;
		String ownPdfFontName = style.getOwnPdfFontName();
		if (ownPdfFontName != null || (style.isOwnPdfEmbedded() != null && style.isOwnPdfEmbedded())) {
			return style.getName();
		}
		if (sr == null)
			sr = new StyleResolver(getJasperConfiguration());
		JRStyle baseStyle = sr.getBaseStyle(style);
		if (baseStyle == null)
			return null;
		String pdfFontName = getPdfFontName(baseStyle);
		if (pdfFontName != null || (baseStyle.isOwnPdfEmbedded() != null && baseStyle.isOwnPdfEmbedded())) {
			return baseStyle.getName();
		}
		return null;
	}

}
