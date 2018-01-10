/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.style;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.ExternalStylesManager;
import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.help.HelpReferenceBuilder;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.model.IContainerEditPart;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.IDragable;
import com.jaspersoft.studio.model.IGraphicalPropertiesHandler;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.IPastable;
import com.jaspersoft.studio.model.MLineBox;
import com.jaspersoft.studio.model.MLinePen;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.text.MFont;
import com.jaspersoft.studio.model.text.MParagraph;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.model.util.ReportFactory;
import com.jaspersoft.studio.property.JSSStyleResolver;
import com.jaspersoft.studio.property.descriptor.JRPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.box.BoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.color.ColorPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.FontNamePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.FontSizeButtonPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RWFloatComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RWStyleComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.pattern.PatternPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.pen.PenPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.ImageHAlignPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.ImageVAlignPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.IntegerPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.JSSValidatedTextPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.RotationPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.TextHAlignPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.TextVAlignPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPBooleanToggle;
import com.jaspersoft.studio.utils.AlfaRGB;
import com.jaspersoft.studio.utils.Colors;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.base.JRBaseFont;
import net.sf.jasperreports.engine.base.JRBaseParagraph;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.CollectionElementAddedEvent;
import net.sf.jasperreports.engine.type.FillEnum;
import net.sf.jasperreports.engine.type.HorizontalImageAlignEnum;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.RotationEnum;
import net.sf.jasperreports.engine.type.ScaleImageEnum;
import net.sf.jasperreports.engine.type.VerticalImageAlignEnum;
import net.sf.jasperreports.engine.type.VerticalTextAlignEnum;

/*
 * The Class MStyle.
 * 
 * @author Chicu Veaceslav
 */
public class MStyle extends APropertyNode implements ICopyable, IPastable, IContainerEditPart, IDragable, MNotConditionalMarker {
	
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;
	
	private static IPropertyDescriptor[] descriptors;
	
	private static StyleNameValidator validator;
	
	private static RWComboBoxPropertyDescriptor styleD;
	
	private static NamedEnumPropertyDescriptor<FillEnum> fillD;
	
	private static NamedEnumPropertyDescriptor<ScaleImageEnum> scaleD;
	
	private static TextHAlignPropertyDescriptor halignText;
	
	private static TextVAlignPropertyDescriptor valignText;
	
	private static ImageHAlignPropertyDescriptor halignImage;
	
	private static ImageVAlignPropertyDescriptor valignImage;
	
	private static RotationPropertyDescriptor rotationD;
	
	private static final String LINE_PEN = "LinePen"; //$NON-NLS-1$
	
	private static final String LINE_BOX = "LineBox"; //$NON-NLS-1$
	
	public static final String PARAGRAPH = "paragraph"; //$NON-NLS-1$

	private MLinePen linePen;
	
	private MLineBox lineBox;
	
	private MParagraph mParagraph;
	
	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("style"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m style.
	 */
	public MStyle() {
		super();
	}

	public MStyle(ANode parent, int newIndex) {
		super(parent, newIndex);
	}

	/**
	 * Instantiates a new m style.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrstyle
	 *          the jrstyle
	 * @param newIndex
	 *          the new index
	 */
	public MStyle(ANode parent, JRDesignStyle jrstyle, int newIndex) {
		super(parent, newIndex);
		setValue(jrstyle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		return ((JRStyle) getValue()).getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getImagePath()
	 */
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getToolTip()
	 */
	@Override
	public String getToolTip() {
		return getIconDescriptor().getToolTip();
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors = descriptors1;
	}

	@Override
	protected void postDescriptors(IPropertyDescriptor[] descriptors) {
		super.postDescriptors(descriptors);
		// Set into the validator the actual reference
		validator.setTargetNode(this);
	}

	public HashMap<String, Object> getStylesDescriptors() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		if (getValue() == null)
			return result;
		JRStyle element = (JRStyle) getValue();
		result.put(JRDesignStyle.PROPERTY_BACKCOLOR, element.getOwnBackcolor());
		result.put(JRDesignStyle.PROPERTY_FORECOLOR, element.getOwnForecolor());
		result.put(JRDesignStyle.PROPERTY_FILL, element.getOwnFillValue());
		result.put(JRDesignStyle.PROPERTY_FONT_NAME, element.getOwnFontName());
		result.put(JRDesignStyle.PROPERTY_FONT_SIZE, element.getOwnFontsize());
		result.put(JRDesignStyle.PROPERTY_BOLD, element.isOwnBold());
		result.put(JRDesignStyle.PROPERTY_ITALIC, element.isOwnItalic());
		result.put(JRDesignStyle.PROPERTY_UNDERLINE, element.isOwnUnderline());
		result.put(JRDesignStyle.PROPERTY_STRIKE_THROUGH, element.isOwnStrikeThrough());
		result.put(JRDesignStyle.PROPERTY_HORIZONTAL_IMAGE_ALIGNMENT, element.getOwnHorizontalImageAlign());
		result.put(JRDesignStyle.PROPERTY_HORIZONTAL_TEXT_ALIGNMENT, element.getOwnHorizontalTextAlign());
		result.put(JRDesignStyle.PROPERTY_MARKUP, element.getOwnMarkup());
		result.put(JRDesignStyle.PROPERTY_MODE, element.getOwnModeValue());
		result.put(JRDesignStyle.PROPERTY_PATTERN, element.getOwnPattern());
		result.put(JRDesignStyle.PROPERTY_RADIUS, element.getOwnRadius());
		result.put(JRDesignStyle.PROPERTY_ROTATION, element.getOwnRotationValue());
		result.put(JRDesignStyle.PROPERTY_SCALE_IMAGE, element.getOwnScaleImageValue());
		result.put(JRDesignStyle.PROPERTY_VERTICAL_TEXT_ALIGNMENT, element.getOwnVerticalTextAlign());
		result.put(JRDesignStyle.PROPERTY_VERTICAL_IMAGE_ALIGNMENT, element.getOwnVerticalImageAlign());
		result.put(JRDesignStyle.PROPERTY_BLANK_WHEN_NULL, element.isOwnBlankWhenNull());
		result.put(JRDesignStyle.PROPERTY_PDF_FONT_NAME, element.getOwnPdfFontName());
		result.put(JRDesignStyle.PROPERTY_PDF_ENCODING, element.getOwnPdfEncoding());
		result.put(JRDesignStyle.PROPERTY_PDF_EMBEDDED, element.isOwnPdfEmbedded());
		result.put(PARAGRAPH, getPropertyValue(PARAGRAPH));
		MLinePen linePen = (MLinePen) getPropertyValue(LINE_PEN);
		result.put(LINE_PEN, linePen);
		MLineBox lineBox = (MLineBox) getPropertyValue(LINE_BOX);
		result.put(LINE_BOX, lineBox);
		return result;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		styleD = new RWStyleComboBoxPropertyDescriptor(JRDesignStyle.PROPERTY_PARENT_STYLE, Messages.common_parent_style,
				new String[] { "" }, NullEnum.NULL); //$NON-NLS-1$
		styleD.setDescription(Messages.MStyle_parent_style_description);
		styleD.setHelpRefBuilder(new HelpReferenceBuilder(
				"net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#reportElement_style")); //$NON-NLS-1$
		desc.add(styleD);

		validator = new StyleNameValidator();
		validator.setTargetNode(this);
		JSSValidatedTextPropertyDescriptor nameD = new JSSValidatedTextPropertyDescriptor(JRDesignStyle.PROPERTY_NAME,
				Messages.common_name, validator);
		nameD.setDescription(Messages.MStyle_name_description);
		desc.add(nameD);

		PenPropertyDescriptor linePenD = new PenPropertyDescriptor(LINE_PEN, Messages.common_line_pen);
		linePenD.setDescription(Messages.MStyle_line_pen_description);
		desc.add(linePenD);

		BoxPropertyDescriptor lineBoxD = new BoxPropertyDescriptor(LINE_BOX, Messages.common_line_box);
		lineBoxD.setDescription(Messages.MStyle_line_box_description);
		desc.add(lineBoxD);
		lineBoxD.setCategory(Messages.common_graphic);

		ColorPropertyDescriptor forecolorD = new ColorPropertyDescriptor(JRDesignStyle.PROPERTY_FORECOLOR,
				Messages.common_forecolor, NullEnum.INHERITED);
		forecolorD.setDescription(Messages.MStyle_forecolor_description);
		desc.add(forecolorD);

		ColorPropertyDescriptor backcolorD = new ColorPropertyDescriptor(JRDesignStyle.PROPERTY_BACKCOLOR,
				Messages.common_backcolor, NullEnum.INHERITED);
		backcolorD.setDescription(Messages.MStyle_backcolor_description);
		desc.add(backcolorD);

		IntegerPropertyDescriptor radiusD = new IntegerPropertyDescriptor(JRBaseStyle.PROPERTY_RADIUS,
				Messages.common_radius);
		radiusD.setDescription(Messages.MStyle_radius_description);
		desc.add(radiusD);

		fillD = new NamedEnumPropertyDescriptor<FillEnum>(JRBaseStyle.PROPERTY_FILL, Messages.common_fill, FillEnum.SOLID,
				NullEnum.UNDEFINED);
		fillD.setDescription(Messages.MStyle_fill_description);
		desc.add(fillD);

		scaleD = new NamedEnumPropertyDescriptor<ScaleImageEnum>(JRBaseStyle.PROPERTY_SCALE_IMAGE, Messages.MStyle_scale,
				ScaleImageEnum.CLIP, NullEnum.UNDEFINED);
		scaleD.setDescription(Messages.MStyle_scale_description);
		desc.add(scaleD);

		halignText = new TextHAlignPropertyDescriptor(JRBaseStyle.PROPERTY_HORIZONTAL_TEXT_ALIGNMENT,
				Messages.MStyle_textHorizontal, NullEnum.INHERITED);
		halignText.setDescription(Messages.MStyle_horizontal_alignment_description);
		desc.add(halignText);

		valignText = new TextVAlignPropertyDescriptor(JRBaseStyle.PROPERTY_VERTICAL_TEXT_ALIGNMENT,
				Messages.MStyle_textVertical, NullEnum.INHERITED);
		valignText.setDescription(Messages.MStyle_vertical_alignment_description);
		desc.add(valignText);

		halignImage = new ImageHAlignPropertyDescriptor(JRBaseStyle.PROPERTY_HORIZONTAL_IMAGE_ALIGNMENT,
				Messages.MStyle_imageHorizontal, NullEnum.INHERITED);
		halignImage.setDescription(Messages.MStyle_horizontal_alignment_description);
		desc.add(halignImage);

		valignImage = new ImageVAlignPropertyDescriptor(JRBaseStyle.PROPERTY_VERTICAL_IMAGE_ALIGNMENT,
				Messages.MStyle_imageVertical, NullEnum.INHERITED);
		valignImage.setDescription(Messages.MStyle_vertical_alignment_description);
		desc.add(valignImage);

		rotationD = new RotationPropertyDescriptor(JRBaseStyle.PROPERTY_ROTATION, Messages.common_rotation,
				NullEnum.INHERITED);
		rotationD.setDescription(Messages.MStyle_rotation_description);
		desc.add(rotationD);

		CheckBoxPropertyDescriptor opaqueDBool = new CheckBoxPropertyDescriptor(JRBaseStyle.PROPERTY_MODE, Messages.common_opaque);
		opaqueDBool.setDescription(Messages.MGraphicElement_opaque_description);
		desc.add(opaqueDBool);

		CheckBoxPropertyDescriptor blankWhenNullD = new CheckBoxPropertyDescriptor(JRBaseStyle.PROPERTY_BLANK_WHEN_NULL,
				Messages.common_blank_when_null, NullEnum.INHERITED);
		blankWhenNullD.setDescription(Messages.MStyle_blank_when_null_description);
		desc.add(blankWhenNullD);

		CheckBoxPropertyDescriptor boldD = new CheckBoxPropertyDescriptor(JRBaseFont.PROPERTY_BOLD, Messages.common_bold,
				NullEnum.INHERITED) {
			@Override
			public ASPropertyWidget<CheckBoxPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
				return new SPBooleanToggle(parent, section, this, JaspersoftStudioPlugin.getInstance().getImage(
						"icons/resources/bold.png")); //$NON-NLS-1$
			}
		};
		boldD.setDescription(Messages.MFont_bold_description);
		desc.add(boldD);

		CheckBoxPropertyDescriptor italicD = new CheckBoxPropertyDescriptor(JRBaseFont.PROPERTY_ITALIC,
				Messages.common_italic, NullEnum.INHERITED) {
			@Override
			public ASPropertyWidget<CheckBoxPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
				return new SPBooleanToggle(parent, section, this, JaspersoftStudioPlugin.getInstance().getImage(
						"icons/resources/italic.png")); //$NON-NLS-1$
			}
		};
		italicD.setDescription(Messages.MFont_italic_description);
		desc.add(italicD);

		CheckBoxPropertyDescriptor underlineD = new CheckBoxPropertyDescriptor(JRBaseFont.PROPERTY_UNDERLINE,
				Messages.common_underline, NullEnum.INHERITED) {
			@Override
			public ASPropertyWidget<CheckBoxPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
				return new SPBooleanToggle(parent, section, this, JaspersoftStudioPlugin.getInstance().getImage(
						"icons/resources/underline.png")); //$NON-NLS-1$
			}
		};
		underlineD.setDescription(Messages.MFont_underline_description);
		desc.add(underlineD);

		CheckBoxPropertyDescriptor strikeThroughD = new CheckBoxPropertyDescriptor(JRBaseFont.PROPERTY_STRIKE_THROUGH,
				Messages.common_strike_trough, NullEnum.INHERITED) {
			@Override
			public ASPropertyWidget<CheckBoxPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
				return new SPBooleanToggle(parent, section, this, JaspersoftStudioPlugin.getInstance().getImage(
						"icons/resources/strikethrought.png")); //$NON-NLS-1$
			}
		};
		strikeThroughD.setDescription(Messages.MFont_strike_trough_description);
		desc.add(strikeThroughD);

		CheckBoxPropertyDescriptor defaultD = new CheckBoxPropertyDescriptor(JRDesignStyle.PROPERTY_DEFAULT,
				Messages.MStyle_default_style, NullEnum.NOTNULL);
		defaultD.setDescription(Messages.MStyle_default_style_description);
		desc.add(defaultD);

		RWComboBoxPropertyDescriptor markupD = new RWComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_MARKUP,
				Messages.MStyle_markup, ModelUtils.getMarkups(getJasperConfiguration()), NullEnum.INHERITED);
		markupD.setDescription(Messages.MStyle_markup_description);
		desc.add(markupD);

		FontNamePropertyDescriptor fontNameD = new FontNamePropertyDescriptor(JRBaseStyle.PROPERTY_FONT_NAME,
				Messages.common_font_name, getJasperConfiguration().getFontList(), NullEnum.INHERITED);
		fontNameD.setDescription(Messages.MStyle_font_name_description);
		desc.add(fontNameD);

		RWFloatComboBoxPropertyDescriptor fontSizeD = new RWFloatComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_FONT_SIZE, Messages.common_font_size, ModelUtils.FONT_SIZES, NullEnum.INHERITED, false);
		fontSizeD.setDescription(Messages.MStyle_font_size_description);
		desc.add(fontSizeD);

		FontSizeButtonPropertyDescriptor fontIncrement = new FontSizeButtonPropertyDescriptor(MFont.FONT_INCREMENT, this);
		desc.add(fontIncrement);

		PatternPropertyDescriptor patternD = new PatternPropertyDescriptor(JRBaseStyle.PROPERTY_PATTERN,
				Messages.common_pattern);
		patternD.setDescription(Messages.MStyle_pattern_description);
		desc.add(patternD);

		JRPropertyDescriptor paragraph = new JRPropertyDescriptor(PARAGRAPH, "Paragraph"); //$NON-NLS-1$
		desc.add(paragraph);

		RWComboBoxPropertyDescriptor pdfFontNameD = new RWComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_PDF_FONT_NAME,
				Messages.MFont_pdf_font_name, ModelUtils.getPDFFontNames(), NullEnum.INHERITED);
		pdfFontNameD.setDescription(Messages.MFont_pdf_font_name_description);
		pdfFontNameD.setCategory(Messages.MFont_pdfCategory);
		desc.add(pdfFontNameD);

		RWComboBoxPropertyDescriptor pdfEncodingD = new RWComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_PDF_ENCODING,
				Messages.MFont_pdf_encoding, ModelUtils.getPDFEncodings(), NullEnum.INHERITED);
		pdfEncodingD.setDescription(Messages.MFont_pdf_encoding_description);
		pdfEncodingD.setCategory(Messages.MFont_pdfCategory);
		desc.add(pdfEncodingD);

		CheckBoxPropertyDescriptor pdfEmbedded = new CheckBoxPropertyDescriptor(JRBaseStyle.PROPERTY_PDF_EMBEDDED,
				Messages.MFont_pdf_embedded);
		pdfEmbedded.setDescription(Messages.MFont_pdf_embedded_description);
		pdfEmbedded.setCategory(Messages.MFont_pdfCategory);
		desc.add(pdfEmbedded);

		paragraph.setCategory(Messages.common_text);
		styleD.setCategory(Messages.MStyle_Style_properties);
		nameD.setCategory(Messages.MStyle_Style_properties);
		defaultD.setCategory(Messages.MStyle_Style_properties);

		forecolorD.setCategory(Messages.MStyle_common_category);
		backcolorD.setCategory(Messages.MStyle_common_category);
		opaqueDBool.setCategory(Messages.MStyle_common_category);

		linePenD.setCategory(Messages.common_graphic);

		radiusD.setCategory(Messages.common_graphic);
		scaleD.setCategory(Messages.common_graphic);
		fillD.setCategory(Messages.common_graphic);

		patternD.setCategory(Messages.common_text);
		blankWhenNullD.setCategory(Messages.common_text);
		rotationD.setCategory(Messages.common_text);
		markupD.setCategory(Messages.common_text);
		halignText.setCategory(Messages.common_text);
		valignText.setCategory(Messages.common_text);
		halignImage.setCategory(Messages.common_text);
		valignImage.setCategory(Messages.common_text);

		fontNameD.setCategory(Messages.MStyle_text_font_category);
		fontSizeD.setCategory(Messages.MStyle_text_font_category);
		boldD.setCategory(Messages.MStyle_text_font_category);
		italicD.setCategory(Messages.MStyle_text_font_category);
		underlineD.setCategory(Messages.MStyle_text_font_category);
		strikeThroughD.setCategory(Messages.MStyle_text_font_category);

		setHelpPrefix(desc, "net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#style"); //$NON-NLS-1$
	}
	
	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();
		defaultsMap.put(JRBaseStyle.PROPERTY_FORECOLOR, new DefaultValue(null, true));
		defaultsMap.put(JRBaseStyle.PROPERTY_BACKCOLOR, new DefaultValue(null, true));

		defaultsMap.put(JRBaseStyle.PROPERTY_FILL, new DefaultValue(null, true));
		defaultsMap.put(JRBaseStyle.PROPERTY_SCALE_IMAGE, new DefaultValue(null, true));
		defaultsMap.put(JRBaseStyle.PROPERTY_HORIZONTAL_TEXT_ALIGNMENT, new DefaultValue(null, true));
		defaultsMap.put(JRBaseStyle.PROPERTY_VERTICAL_TEXT_ALIGNMENT, new DefaultValue(null, true));
		defaultsMap.put(JRBaseStyle.PROPERTY_HORIZONTAL_IMAGE_ALIGNMENT, new DefaultValue(null, true));
		defaultsMap.put(JRBaseStyle.PROPERTY_VERTICAL_IMAGE_ALIGNMENT, new DefaultValue(null, true));
		defaultsMap.put(JRBaseStyle.PROPERTY_ROTATION, new DefaultValue(null, true));

		defaultsMap.put(JRDesignStyle.PROPERTY_BLANK_WHEN_NULL, new DefaultValue(Boolean.FALSE, false));
		defaultsMap.put(JRBaseStyle.PROPERTY_STRIKE_THROUGH, new DefaultValue(null, true));
		defaultsMap.put(JRBaseStyle.PROPERTY_UNDERLINE, new DefaultValue(null, true));
		defaultsMap.put(JRBaseStyle.PROPERTY_ITALIC, new DefaultValue(null, true));
		defaultsMap.put(JRBaseStyle.PROPERTY_BOLD, new DefaultValue(null, true));
		defaultsMap.put(JRBaseStyle.PROPERTY_FONT_NAME, new DefaultValue("SansSerif", true)); //$NON-NLS-1$
		defaultsMap.put(JRBaseStyle.PROPERTY_FONT_SIZE, new DefaultValue(10f, true)); //$NON-NLS-1$
		
		int modeValue = NamedEnumPropertyDescriptor.getIntValue(ModeEnum.OPAQUE, NullEnum.NOTNULL, ModeEnum.OPAQUE);
		defaultsMap.put(JRBaseStyle.PROPERTY_MODE, new DefaultValue(modeValue, true));

		return defaultsMap;
	}

	/**
	 * Return the internal style used. If the internal style is a reference to a removed style then it is also removed
	 * from the element
	 */
	public JRStyle getActualStyle() {
		JRBaseStyle jrElement = (JRBaseStyle) getValue();
		// Check if the used style is valid otherwise return null, but don't change the value.
		//it is bad to change values without the user asking for it
		if (jrElement.getStyle() != null && !getJasperDesign().getStylesMap().containsKey(jrElement.getStyle().getName())) {
			return null;
			//setPropertyValue(JRDesignStyle.PROPERTY_PARENT_STYLE, null);
		}
		if (jrElement.getStyle() != null) {
			return jrElement.getStyle();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		if (getValue() == null)
			return null;
		if (getValue() instanceof JRBaseStyle) {
			JRBaseStyle jrstyle = (JRBaseStyle) getValue();
			if (id.equals(JRDesignStyle.PROPERTY_NAME))
				return jrstyle.getName();
			if (id.equals(JRDesignStyle.PROPERTY_DEFAULT))
				return new Boolean(jrstyle.isDefault());
			if (id.equals(JRDesignStyle.PROPERTY_PARENT_STYLE)) {
				if (jrstyle.getStyleNameReference() != null)
					return jrstyle.getStyleNameReference();
				JRStyle actualStyle = getActualStyle();
				return actualStyle != null ? actualStyle.getName() : ""; //$NON-NLS-1$
			}
			if (id.equals(PARAGRAPH)) {
				if (mParagraph == null) {
					mParagraph = new MParagraph(this, (JRBaseParagraph) jrstyle.getParagraph());
					setChildListener(mParagraph);
				}
				return mParagraph;
			}
		}

		JRBaseStyle jrstyle = (JRBaseStyle) getValue();
		if (id.equals(LINE_PEN)) {
			if (linePen == null) {
				linePen = new MLinePen(jrstyle.getLinePen());
				linePen.setJasperConfiguration(getJasperConfiguration());
				setChildListener(linePen);
			}
			return linePen;
		}
		
		if (id.equals(LINE_BOX)) {
			if (lineBox == null) {
				lineBox = new MLineBox(jrstyle.getLineBox(), this);
				setChildListener(lineBox);
			}
			return lineBox;
		}
		
		if (id.equals(JRBaseStyle.PROPERTY_PATTERN))
			return jrstyle.getOwnPattern();
		if (id.equals(JRBaseStyle.PROPERTY_RADIUS))
			return jrstyle.getOwnRadius();
		if (id.equals(JRBaseStyle.PROPERTY_MARKUP))
			return jrstyle.getOwnMarkup();
		if (id.equals(JRDesignStyle.PROPERTY_FORECOLOR))
			return Colors.getSWTRGB4AWTGBColor(jrstyle.getOwnForecolor());
		else if (id.equals(JRDesignStyle.PROPERTY_BACKCOLOR))
			return Colors.getSWTRGB4AWTGBColor(jrstyle.getOwnBackcolor());

		if (id.equals(JRBaseStyle.PROPERTY_FILL))
			return fillD.getIntValue(jrstyle.getOwnFillValue());
		if (id.equals(JRBaseStyle.PROPERTY_SCALE_IMAGE))
			return scaleD.getIntValue(jrstyle.getOwnScaleImageValue());
		if (id.equals(JRBaseStyle.PROPERTY_HORIZONTAL_TEXT_ALIGNMENT))
			return halignText.getIntValue(jrstyle.getOwnHorizontalTextAlign());
		if (id.equals(JRBaseStyle.PROPERTY_VERTICAL_TEXT_ALIGNMENT))
			return valignText.getIntValue(jrstyle.getOwnVerticalTextAlign());
		if (id.equals(JRBaseStyle.PROPERTY_HORIZONTAL_IMAGE_ALIGNMENT))
			return halignImage.getIntValue(jrstyle.getOwnHorizontalImageAlign());
		if (id.equals(JRBaseStyle.PROPERTY_VERTICAL_IMAGE_ALIGNMENT))
			return valignImage.getIntValue(jrstyle.getOwnVerticalImageAlign());
		if (id.equals(JRBaseStyle.PROPERTY_ROTATION))
			return rotationD.getIntValue(jrstyle.getOwnRotationValue());
		if (id.equals(JRBaseStyle.PROPERTY_MODE)) {
			ModeEnum modeValue = jrstyle.getOwnModeValue(); 
			return modeValue != null ? modeValue.equals(ModeEnum.TRANSPARENT) : null;
		}
		if (id.equals(JRBaseStyle.PROPERTY_BLANK_WHEN_NULL))
			return jrstyle.isOwnBlankWhenNull();
		if (id.equals(JRBaseStyle.PROPERTY_STRIKE_THROUGH))
			return jrstyle.isOwnStrikeThrough();
		if (id.equals(JRBaseStyle.PROPERTY_UNDERLINE))
			return jrstyle.isOwnUnderline();
		if (id.equals(JRBaseStyle.PROPERTY_ITALIC))
			return jrstyle.isOwnItalic();
		if (id.equals(JRBaseStyle.PROPERTY_BOLD))
			return jrstyle.isOwnBold();
		if (id.equals(JRBaseStyle.PROPERTY_FONT_NAME))
			return jrstyle.getOwnFontName();
		if (id.equals(JRBaseStyle.PROPERTY_FONT_SIZE))
			return jrstyle.getOwnFontsize(); //$NON-NLS-1$
		if (id.equals(JRBaseStyle.PROPERTY_PDF_FONT_NAME))
			return jrstyle.getOwnPdfFontName();
		if (id.equals(JRBaseStyle.PROPERTY_PDF_ENCODING))
			return jrstyle.getOwnPdfEncoding();
		if (id.equals(JRBaseStyle.PROPERTY_PDF_EMBEDDED))
			return jrstyle.isOwnPdfEmbedded();
		
		if (lineBox != null) {
			Object val = lineBox.getPropertyValue(id);
			if (val != null)
				return val;
		}
		if (linePen != null) {
			Object val = linePen.getPropertyValue(id);
			if (val != null)
				return val;
		}
		return null;
	}

	public Object getPropertyActualValue(Object id) {
		if (getValue() == null)
			return null;
		JSSStyleResolver resolver = getStyleResolver();
		JRBaseStyle jrstyle = (JRBaseStyle) getValue();
		if (id.equals(JRBaseStyle.PROPERTY_PATTERN)){
			return resolver.getPattern(jrstyle);
		} else if (id.equals(JRBaseStyle.PROPERTY_RADIUS)){
			return resolver.getRadius(jrstyle);
		} else if (id.equals(JRBaseStyle.PROPERTY_MARKUP)){
			return resolver.getMarkup(jrstyle);
		} else if (id.equals(JRDesignStyle.PROPERTY_FORECOLOR)){
			Color color = resolver.getForecolor(jrstyle);
			return Colors.getSWTRGB4AWTGBColor(color);
		} else if (id.equals(JRDesignStyle.PROPERTY_BACKCOLOR)){
			Color color = resolver.getBackcolor(jrstyle);
			return Colors.getSWTRGB4AWTGBColor(color);
		} else if (id.equals(JRBaseStyle.PROPERTY_FILL)){
			FillEnum fillValue = resolver.getFillValue(jrstyle);
			return fillD.getIntValue(fillValue);
		} else if (id.equals(JRBaseStyle.PROPERTY_SCALE_IMAGE)){
			ScaleImageEnum imageValue = resolver.getScaleImageValue(jrstyle);
			return scaleD.getIntValue(imageValue);
		} else if (id.equals(JRBaseStyle.PROPERTY_HORIZONTAL_TEXT_ALIGNMENT)){
			HorizontalTextAlignEnum textAlignValue = resolver.getHorizontalTextAlign(jrstyle);
			return halignText.getIntValue(textAlignValue);
		} else if (id.equals(JRBaseStyle.PROPERTY_VERTICAL_TEXT_ALIGNMENT)){
			VerticalTextAlignEnum textAlignValue = resolver.getVerticalTextAlign(jrstyle);
			return valignText.getIntValue(textAlignValue);
		} else if (id.equals(JRBaseStyle.PROPERTY_HORIZONTAL_IMAGE_ALIGNMENT)){
			HorizontalImageAlignEnum imageAlignValue = resolver.getHorizontalImageAlign(jrstyle);
			return halignImage.getIntValue(imageAlignValue);
		} else if (id.equals(JRBaseStyle.PROPERTY_VERTICAL_IMAGE_ALIGNMENT)){
			VerticalImageAlignEnum imageAlignValue = resolver.getVerticalImageAlign(jrstyle);
			return valignImage.getIntValue(imageAlignValue);
		} else if (id.equals(JRBaseStyle.PROPERTY_ROTATION)){
			RotationEnum rotationValue = resolver.getRotationValue(jrstyle);
			return rotationD.getIntValue(rotationValue);
		} else if (id.equals(JRBaseStyle.PROPERTY_MODE)) {
			ModeEnum modeValue = resolver.getModeValue(jrstyle);
			return modeValue == null ? true : ModeEnum.TRANSPARENT == modeValue;
		} else if (id.equals(JRBaseStyle.PROPERTY_BLANK_WHEN_NULL)){
			return resolver.isBlankWhenNull(jrstyle);
		} else if (id.equals(JRBaseStyle.PROPERTY_STRIKE_THROUGH)){
			return resolver.isStrikeThrough(jrstyle);
		} else if (id.equals(JRBaseStyle.PROPERTY_UNDERLINE)){
			return resolver.isUnderline(jrstyle);
		} else if (id.equals(JRBaseStyle.PROPERTY_ITALIC)){
			return resolver.isItalic(jrstyle);
		} else if (id.equals(JRBaseStyle.PROPERTY_BOLD)){
			return resolver.isBold(jrstyle);
		} if (id.equals(JRBaseStyle.PROPERTY_FONT_NAME)){
			return resolver.getFontName(jrstyle);
		} if (id.equals(JRBaseStyle.PROPERTY_FONT_SIZE)){
			return resolver.getFontsize(jrstyle); //$NON-NLS-1$
		} else if (id.equals(JRBaseStyle.PROPERTY_PDF_FONT_NAME)){
			return resolver.getPdfFontName(jrstyle);
		} else if (id.equals(JRBaseStyle.PROPERTY_PDF_ENCODING)){
			return resolver.getPdfEncoding(jrstyle);
		} else if (id.equals(JRBaseStyle.PROPERTY_PDF_EMBEDDED)){
			return resolver.isPdfEmbedded(jrstyle);
		}

		if (lineBox != null) {
			Object val = lineBox.getPropertyActualValue(id);
			if (val != null)
				return val;
		}
		
		if (linePen != null) {
			Object val = linePen.getPropertyActualValue(id);
			if (val != null)
				return val;
		}
		return super.getPropertyActualValue(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		if (!isEditable())
			return;
		if (getValue() instanceof JRDesignStyle) {
			JRDesignStyle jrstyle = (JRDesignStyle) getValue();
			if (id.equals(JRDesignStyle.PROPERTY_NAME)) {
				jrstyle.setName((String) value);
			} else if (id.equals(JRDesignStyle.PROPERTY_DEFAULT)) {
				jrstyle.setDefault(((Boolean) value).booleanValue());
			} else if (id.equals(JRDesignStyle.PROPERTY_PARENT_STYLE)) {
				if (!Misc.isNullOrEmpty((String) value)) {
					JRStyle style = (JRStyle) getJasperDesign().getStylesMap().get(value);
					if (style != null) {
						// FIXME: It is important to set a null first the external style, because it is returned first on the
						// getPropertyValue and this raise a lot of events
						jrstyle.setParentStyleNameReference(null);
						jrstyle.setParentStyle(style);
					} else {
						jrstyle.setParentStyleNameReference((String) value);
						// Set the external style as parent style if existing, to resolve JR resolving problem at design time
						fixExternalStyleReference((String) value);
					}
				} else {
					// remove the style
					jrstyle.setParentStyleNameReference(null);
					jrstyle.setParentStyle(null);
				}
			}
		}
		JRBaseStyle jrstyle = (JRBaseStyle) getValue();
		if (id.equals(JRDesignStyle.PROPERTY_PATTERN))
			jrstyle.setPattern((String) value);
		else if (id.equals(JRDesignStyle.PROPERTY_RADIUS))
			jrstyle.setRadius((Integer) value);
		else if (id.equals(JRBaseStyle.PROPERTY_MARKUP))
			jrstyle.setMarkup((String) value);
		else if (id.equals(JRBaseStyle.PROPERTY_FORECOLOR))
			jrstyle.setForecolor(Colors.getAWT4SWTRGBColor((AlfaRGB) value));
		else if (id.equals(JRBaseStyle.PROPERTY_BACKCOLOR))
			jrstyle.setBackcolor(Colors.getAWT4SWTRGBColor((AlfaRGB) value));

		else if (id.equals(JRBaseStyle.PROPERTY_FILL))
			jrstyle.setFill(fillD.getEnumValue(value));
		else if (id.equals(JRBaseStyle.PROPERTY_SCALE_IMAGE))
			jrstyle.setScaleImage(scaleD.getEnumValue(value));
		else if (id.equals(JRBaseStyle.PROPERTY_HORIZONTAL_TEXT_ALIGNMENT))
			jrstyle.setHorizontalTextAlign((HorizontalTextAlignEnum) halignText.getEnumValue(value));
		else if (id.equals(JRBaseStyle.PROPERTY_VERTICAL_TEXT_ALIGNMENT))
			jrstyle.setVerticalTextAlign((VerticalTextAlignEnum) valignText.getEnumValue(value));
		else if (id.equals(JRBaseStyle.PROPERTY_HORIZONTAL_IMAGE_ALIGNMENT))
			jrstyle.setHorizontalImageAlign((HorizontalImageAlignEnum) halignImage.getEnumValue(value));
		else if (id.equals(JRBaseStyle.PROPERTY_VERTICAL_IMAGE_ALIGNMENT))
			jrstyle.setVerticalImageAlign((VerticalImageAlignEnum) valignImage.getEnumValue(value));
		else if (id.equals(JRBaseStyle.PROPERTY_ROTATION))
			jrstyle.setRotation((RotationEnum) rotationD.getEnumValue(value));
		else if (id.equals(JRBaseStyle.PROPERTY_MODE))
			if (value == null)
				jrstyle.setMode(null);
			else if ((Boolean) value)
				jrstyle.setMode(ModeEnum.TRANSPARENT);
			else
				jrstyle.setMode(ModeEnum.OPAQUE);
		else if (id.equals(JRBaseStyle.PROPERTY_BLANK_WHEN_NULL))
			jrstyle.setBlankWhenNull((Boolean) value);
		else if (id.equals(JRBaseStyle.PROPERTY_STRIKE_THROUGH))
			jrstyle.setStrikeThrough((Boolean) value);
		else if (id.equals(JRBaseStyle.PROPERTY_UNDERLINE))
			jrstyle.setUnderline((Boolean) value);
		else if (id.equals(JRBaseStyle.PROPERTY_ITALIC))
			jrstyle.setItalic((Boolean) value);
		else if (id.equals(JRBaseStyle.PROPERTY_BOLD))
			jrstyle.setBold((Boolean) value);
		else if (id.equals(JRBaseStyle.PROPERTY_FONT_NAME))
			jrstyle.setFontName((String) value);
		else if (id.equals(JRBaseStyle.PROPERTY_FONT_SIZE)) {
			jrstyle.setFontSize((Float) value);
		} else if (id.equals(JRBaseStyle.PROPERTY_PDF_FONT_NAME))
			jrstyle.setPdfFontName((String) value);
		else if (id.equals(JRBaseStyle.PROPERTY_PDF_ENCODING))
			jrstyle.setPdfEncoding((String) value);
		else if (id.equals(JRBaseStyle.PROPERTY_PDF_EMBEDDED))
			jrstyle.setPdfEmbedded((Boolean) value);
		else if (lineBox != null) {
			lineBox.setPropertyValue(id, value);
		}
		if (linePen != null) {
			linePen.setPropertyValue(id, value);
		}
	}

	/**
	 * When the external style change we need also to set the reference to the JRStyle as parent style. This normally
	 * should be done by jasper reports when the reports is executed, but since we need to show it at design time we need
	 * this trick to have the external styles resolved correctly when an element is painted. Anyway if the selected styles
	 * can't be found this method set the style to null
	 * 
	 * @param externalStyleName
	 *          name of the external style
	 */
	protected void fixExternalStyleReference(String externalStyleName) {
		if (externalStyleName != null) {
			JRDesignStyle jrstyle = (JRDesignStyle) getValue();
			jrstyle.setParentStyle(ExternalStylesManager.getExternalStyle((String) externalStyleName,
					getJasperConfiguration()));
		}
	}

	@Override
	public void setValue(Object value) {
		super.setValue(value);
		// Set the external style as parent style if existing, to resolve JR resolving problem at design time
		fixExternalStyleReference(((JRBaseStyle) value).getStyleNameReference());
	}

	/**
	 * Creates the jr style.
	 * 
	 * @param jrDesign
	 *          the jr design
	 * @return the jR design style
	 */
	public static JRDesignStyle createJRStyle(JasperDesign jrDesign) {
		JRDesignStyle jrDesignStyle = new JRDesignStyle(jrDesign);
		jrDesignStyle.setName(ModelUtils.getDefaultName(jrDesign.getStylesMap(), "Style")); //$NON-NLS-1$
		return jrDesignStyle;
	}

	/**
	 * Search all the nodes that are using this styles and set the flag to tell the graphic manager to repaint them
	 * At the first iteration the node must be the root
	 * 
	 * @param childerns
	 *          the children of the actual level
	 * @param force force the refresh in all the nodes, ignoring if they are using or not the style
	 */
	private void setStyleRefresh(INode node, boolean force) {
		if (force || getValue().isDefault()){
			if (node instanceof IGraphicalPropertiesHandler){
				IGraphicalPropertiesHandler graphicalElement = (IGraphicalPropertiesHandler)node;
				graphicalElement.setChangedProperty(true);
				//Since a style change can change the presence of an error decorator (the fault property can be inherited)
				//we need to refresh also the elements depending on this style
				((ANode)node).revalidateChildren();
			}
			//update all the nodes
			for (INode child : node.getChildren()) {
				setStyleRefresh(child, force);
			}
		} else {
			HashMap<String, List<ANode>> nodesUsingStyle = ((ANode)node).getUsedStyles();
			List<ANode> nodes = nodesUsingStyle.get(getValue().getName());
			if (nodes == null) return;
			for (ANode aNode : nodes) {
				if (aNode.getUsedStyles().containsKey(getValue().getName()) && aNode instanceof IGraphicalPropertiesHandler) {
					IGraphicalPropertiesHandler graphicalElement = (IGraphicalPropertiesHandler)aNode;
					//ask the refresh of the element
					graphicalElement.setStyleChangedProperty();
					//Since a style change can change the presence of an error decorator (the fault property can be inherited)
					//we need to refresh also the elements depending on this style
					((ANode)aNode).revalidateChildren();
				}
			}
		}
	}

	/**
	 * Return the styles used by this element and eventually by its children.
	 * 
	 * @return a not null map with the names of all the styles used by this
	 * element or one of its children. The value corresponding to each style is
	 * the reference to the element that is using the style
	 */
	@Override
	public HashMap<String, List<ANode>> getUsedStyles() {
		HashMap<String, List<ANode>> result = super.getUsedStyles();
		JRStyle style = getValue().getStyle();
		addElementStyle(style, result);
		return result;
	}
	
	@Override
	public void setStyle(JRStyle style) {
		if (getValue() != null){
			((JRDesignStyle)getValue()).setParentStyle(style);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.ANode#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (JRDesignStyle.PROPERTY_NAME.equals(evt.getPropertyName())) {
			JasperDesign design = getJasperDesign();
			JRBaseStyle jrstyle = (JRBaseStyle) getValue();
			if (design != null) {
				design.getStylesMap().remove(evt.getOldValue());
				design.getStylesMap().put(jrstyle.getName(), jrstyle);
			}
		} else if (evt.getPropertyName().equals(JRDesignStyle.PROPERTY_CONDITIONAL_STYLES) && evt.getSource() == getValue()) {
			if (evt.getOldValue() == null && evt.getNewValue() != null) {
				int newIndex = -1;
				if (evt instanceof CollectionElementAddedEvent) {
					newIndex = ((CollectionElementAddedEvent) evt).getAddedIndex();
				}
				// add the node to this parent
				ReportFactory.createNode(this, evt.getNewValue(), newIndex);
			} else if (evt.getOldValue() != null && evt.getNewValue() == null) {
				// delete
				for (INode n : getChildren()) {
					if (n.getValue() == evt.getOldValue()) {
						removeChild((ANode) n);
						break;
					}
				}
			} else {
				// changed
				for (INode n : getChildren()) {
					if (n.getValue() == evt.getOldValue())
						n.setValue(evt.getNewValue());
				}
			}
		}
		evt = new PropertyChangeEvent(getValue(), evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
		fireUpdateForElements(evt.getPropertyName().equals(JRDesignStyle.PROPERTY_DEFAULT));
		super.propertyChange(evt);
	}

	/**
	 * Search in background the elements that are using this styles and mark them for the refresh
	 */
	public void fireUpdateForElements(final boolean force) {
		Runnable notifier = new Runnable() {
			public void run() {
				// Avoid the refresh if the style is not in the hierarchy
				final INode root = getRoot();
				if (root != null) {
					setStyleRefresh(root, force);
					JSSCompoundCommand.forceRefreshVisuals(JSSCompoundCommand.getMainNode((ANode) root));
				}
			}
		};
		new Thread(notifier).start();
	}
	
	/**
	 * Return the style element
	 */
	public JRStyle getValue() {
		return (JRStyle) super.getValue();
	}


	
	@Override
	public void setEditable(boolean editable) {
		super.setEditable(editable);
		MLineBox lineBox = (MLineBox)getPropertyValue(LINE_BOX);
		lineBox.setEditable(editable);
		MLinePen linePen = (MLinePen)getPropertyValue(LINE_PEN);
		linePen.setEditable(editable);
		MParagraph paragraph = (MParagraph)getPropertyValue(PARAGRAPH);
		paragraph.setEditable(editable);
	}
	
	@Override
	public ICopyable.RESULT isCopyable2(Object parent) {
		//A style is copiable only to an MStyle or an MStyleReference that has
		//the root as parent
		if (parent instanceof MStyles){
			return ICopyable.RESULT.COPYABLE;
		} else if (parent instanceof MStylesTemplate){
			MStylesTemplate template = (MStylesTemplate)parent;
			if (template.getParent() instanceof MRoot){
				return ICopyable.RESULT.COPYABLE;
			} else {
				return ICopyable.RESULT.NOT_COPYABLE;
			}
		} else if (parent instanceof MConditionalStyle){
			return ICopyable.RESULT.NOT_COPYABLE;
		}
		return ICopyable.RESULT.CHECK_PARENT;
	}

	@Override
	public boolean isCuttable(ISelection currentSelection) {
		return true;
	}
}
