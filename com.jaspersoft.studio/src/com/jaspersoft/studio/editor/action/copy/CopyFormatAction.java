/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.copy;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRBoxContainer;
import net.sf.jasperreports.engine.JRCommonElement;
import net.sf.jasperreports.engine.JRCommonImage;
import net.sf.jasperreports.engine.JRCommonRectangle;
import net.sf.jasperreports.engine.JRCommonText;
import net.sf.jasperreports.engine.JRDefaultStyleProvider;
import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.JRImageAlignment;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JRStyleContainer;
import net.sf.jasperreports.engine.JRTextAlignment;
import net.sf.jasperreports.engine.JRTextField;
import net.sf.jasperreports.engine.base.JRBaseLineBox;
import net.sf.jasperreports.engine.base.JRBasePen;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.base.JRBoxPen;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignGraphicElement;
import net.sf.jasperreports.engine.type.HorizontalImageAlignEnum;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.RotationEnum;
import net.sf.jasperreports.engine.type.ScaleImageEnum;
import net.sf.jasperreports.engine.type.VerticalImageAlignEnum;
import net.sf.jasperreports.engine.type.VerticalTextAlignEnum;

import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MGraphicElementLinePen;
import com.jaspersoft.studio.model.MLineBox;
import com.jaspersoft.studio.utils.AlfaRGB;
import com.jaspersoft.studio.utils.Colors;

/**
 * Action to copy the appearance of an element and that can be 
 * pasted then into one or more other elements
 * 
 * @author Orlandin Marco
 * 
 */
public class CopyFormatAction extends ACachedSelectionAction {
	
	/**
	 * List of the copied\pasted properties
	 */
  public static final String[] propertyNames = new String[] {
    JRBaseStyle.PROPERTY_BACKCOLOR,
    JRBaseStyle.PROPERTY_BLANK_WHEN_NULL,
    JRBaseStyle.PROPERTY_BOLD,
    JRBaseStyle.PROPERTY_FONT_NAME,
    JRBaseStyle.PROPERTY_FONT_SIZE,
    JRBaseStyle.PROPERTY_FORECOLOR,
    JRBaseStyle.PROPERTY_HORIZONTAL_TEXT_ALIGNMENT,
    JRBaseStyle.PROPERTY_HORIZONTAL_IMAGE_ALIGNMENT,
    JRBaseStyle.PROPERTY_ITALIC,
    JRBaseStyle.PROPERTY_MARKUP,
    JRBaseStyle.PROPERTY_MODE,
    JRBaseStyle.PROPERTY_PATTERN,
    JRBaseStyle.PROPERTY_PDF_EMBEDDED,
    JRBaseStyle.PROPERTY_PDF_ENCODING,
    JRBaseStyle.PROPERTY_PDF_FONT_NAME,
    JRBaseStyle.PROPERTY_RADIUS,
    JRBaseStyle.PROPERTY_ROTATION,
    JRBaseStyle.PROPERTY_SCALE_IMAGE,
    JRBaseStyle.PROPERTY_STRIKE_THROUGH,
    JRBaseStyle.PROPERTY_UNDERLINE,
    JRBaseStyle.PROPERTY_VERTICAL_TEXT_ALIGNMENT,
    JRBaseStyle.PROPERTY_VERTICAL_IMAGE_ALIGNMENT,
  };
  
  /**
   * Map of the values copied for every property
   */
  private static HashMap<String, Object> copiedValues = null;
  
  public static final String ID = "CopyFormatAction"; //$NON-NLS-1$
	
	public CopyFormatAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}
	
	@Override
	protected void init() {
		super.init();
		setText(Messages.CopyFormatAction_title);
		setId(ID);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/copy_format.png")); //$NON-NLS-1$
		setEnabled(false);
	}

	@Override
	public void run() {
		List<APropertyNode> nodes = getNodes();
		if (nodes.size() == 1){
			copiedValues = new HashMap<String, Object>();
			APropertyNode node = nodes.get(0);
			JRDesignElement element = (JRDesignElement)node.getValue();
			readAttribute(element);
		}
	}

	/**
	 * Read the inherited attributes from the selected element and store them on the map.
	 * The attributes not provided by the element are stored with value null
	 */
	private void readAttribute(JRDesignElement element){
		copiedValues.put(JRBaseStyle.PROPERTY_BACKCOLOR, getBackcolor(element));
		copiedValues.put(JRBaseStyle.PROPERTY_FORECOLOR, getForecolor(element));
		copiedValues.put(JRBaseStyle.PROPERTY_MODE, getMode(element));
		if (element instanceof JRFont){
			JRFont font = (JRFont) element;
			copiedValues.put(JRBaseStyle.PROPERTY_BOLD, isBold(font));
			copiedValues.put(JRBaseStyle.PROPERTY_ITALIC, isItalic(font));
			copiedValues.put(JRBaseStyle.PROPERTY_STRIKE_THROUGH, isStrikeThrough(font));
			copiedValues.put(JRBaseStyle.PROPERTY_UNDERLINE, isUnderline(font));
			copiedValues.put(JRBaseStyle.PROPERTY_FONT_NAME, getFontName(font));
			copiedValues.put(JRBaseStyle.PROPERTY_FONT_SIZE, getFontsize(font));
			copiedValues.put(JRBaseStyle.PROPERTY_PDF_EMBEDDED, isPdfEmbedded(font));
			copiedValues.put(JRBaseStyle.PROPERTY_PDF_ENCODING, getPdfEncoding(font));
			copiedValues.put(JRBaseStyle.PROPERTY_PDF_FONT_NAME, getPdfFontName(font));
		}
		if (element instanceof JRTextAlignment){
			JRTextAlignment text = (JRTextAlignment)element;
			copiedValues.put(JRBaseStyle.PROPERTY_HORIZONTAL_TEXT_ALIGNMENT, getHorizontalTextAlign(text));
			copiedValues.put(JRBaseStyle.PROPERTY_VERTICAL_TEXT_ALIGNMENT, getVerticalTextAlign(text));
		}
		if (element instanceof JRImageAlignment){
			JRImageAlignment text = (JRImageAlignment)element;
			copiedValues.put(JRBaseStyle.PROPERTY_HORIZONTAL_IMAGE_ALIGNMENT, getHorizontalImageAlign(text));
			copiedValues.put(JRBaseStyle.PROPERTY_VERTICAL_IMAGE_ALIGNMENT, getVerticalImageAlign(text));
		}
		if (element instanceof JRCommonText){
			JRCommonText text = (JRCommonText)element;
			copiedValues.put(JRBaseStyle.PROPERTY_MARKUP, getMarkup(text));
			copiedValues.put(JRBaseStyle.PROPERTY_ROTATION, getRotationValue(text));
		}
		if (element instanceof JRTextField){
			JRTextField text = (JRTextField)element;
			copiedValues.put(JRBaseStyle.PROPERTY_BLANK_WHEN_NULL, isBlankWhenNull(text));
			copiedValues.put(JRBaseStyle.PROPERTY_PATTERN, getPattern(text));
		} 
		if (element instanceof JRCommonRectangle){
			JRCommonRectangle rectangle = (JRCommonRectangle)element;
			copiedValues.put(JRBaseStyle.PROPERTY_RADIUS, getRadius(rectangle));
		}
		if (element instanceof JRCommonImage){
			JRCommonImage image = (JRCommonImage)element;
			copiedValues.put(JRBaseStyle.PROPERTY_SCALE_IMAGE, getScaleImageValue(image));
		}
		if (element instanceof JRBoxContainer){
			JRBoxContainer container = (JRBoxContainer)element;
			JRLineBox box = container.getLineBox();
			if (box != null){
				copiedValues.put(JRBaseLineBox.PROPERTY_PADDING, getPadding(box));
				copiedValues.put(JRBaseLineBox.PROPERTY_BOTTOM_PADDING, getBottomPadding(box));
				copiedValues.put(JRBaseLineBox.PROPERTY_LEFT_PADDING, getLeftPadding(box));
				copiedValues.put(JRBaseLineBox.PROPERTY_RIGHT_PADDING, getRightPadding(box));
				copiedValues.put(JRBaseLineBox.PROPERTY_TOP_PADDING, getTopPadding(box));
				readPen(MLineBox.LINE_PEN, box.getPen());
				readPen(MLineBox.LINE_PEN_LEFT, box.getLeftPen());
				readPen(MLineBox.LINE_PEN_RIGHT, box.getRightPen());
				readPen(MLineBox.LINE_PEN_TOP, box.getTopPen());
				readPen(MLineBox.LINE_PEN_BOTTOM, box.getBottomPen());
			}
		} else if (element instanceof JRDesignGraphicElement){
			JRDesignGraphicElement container = (JRDesignGraphicElement)element;
			readPen(MGraphicElementLinePen.LINE_PEN, container.getLinePen());
		}
	}
	
	/**
	 * Read the attributes from a JRBoxPen and store it to the map. To avoid to overwrite
	 * the same attribute from a different pen a prefix can be added
	 */
	private void readPen(String penPrefix, JRBoxPen currentPen){
		if (currentPen != null){
			copiedValues.put(penPrefix+JRBasePen.PROPERTY_LINE_WIDTH, getLineWidth(currentPen));
			copiedValues.put(penPrefix+JRBasePen.PROPERTY_LINE_COLOR, getLineColor(currentPen));
			copiedValues.put(penPrefix+JRBasePen.PROPERTY_LINE_STYLE, getLineStyleValue(currentPen));
		}
	}
	
	/**
	 * Read the attributes from a JRPen and store it to the map. To avoid to overwrite
	 * the same attribute from a different pen a prefix can be added
	 */
	private void readPen(String penPrefix, JRPen currentPen){
		if (currentPen != null){
			copiedValues.put(penPrefix+JRBasePen.PROPERTY_LINE_WIDTH, getLineWidth(currentPen));
			copiedValues.put(penPrefix+JRBasePen.PROPERTY_LINE_COLOR, getLineColor(currentPen));
			copiedValues.put(penPrefix+JRBasePen.PROPERTY_LINE_STYLE, getLineStyleValue(currentPen));
		}
	}
	
	/**
	 * Return the list of APropertyNode inside the selection
	 * 
	 * @param selectedObjects the actual selection
	 * @return a not null list of APropertyNode
	 */
	protected List<APropertyNode> getNodes() {
		List<APropertyNode> result = new ArrayList<APropertyNode>();
		List<Object> graphicalElements = editor.getSelectionCache().getSelectionModelForType(MGraphicElement.class);
		if (graphicalElements.isEmpty())
			return result;
		for (Object it : graphicalElements) {
			// Before to add an element it is checked if its nested, this is done to avoid to copy twice an element because
			// it is also directly selected with also its container (ie a frame) selected
			result.add((APropertyNode)it);
		}
		return result;
	}
	
	/**
	 * The action is enabled only if there are exactly one node into the selection
	 */
	@Override
	protected boolean calculateEnabled() {
		return getNodes().size() == 1;
	}
	
	/**
	 * Check if the properties of an element were copied
	 * 
	 * @return true if the properties were copied, false otherwise
	 */
	public static boolean hasCopiedValues(){
		return copiedValues != null;
	}
	
	/**
	 * Return a value of a property copied by an element 
	 * 
	 * @param key key of the property
	 * @return value of the property, can be null
	 */
	public static Object getCopiedValue(String key){
		return copiedValues.get(key);
	}
	
	//REFATOR OF THE JasperReports JRStyleResolver methods, to read the attribute of the element from the hierarchy but excluding the default value

	private JRStyle getBaseStyle(JRStyleContainer styleContainer) {
		if (styleContainer != null) {
			JRStyle style = styleContainer.getStyle();
			if (style != null) {
				return style;
			}
			JRDefaultStyleProvider defaultStyleProvider = styleContainer.getDefaultStyleProvider();
			if (defaultStyleProvider != null) {
				return defaultStyleProvider.getDefaultStyle();
			}
		}
		return null;
	}

	private Boolean getMode(JRCommonElement element) {
		ModeEnum ownMode = element.getOwnModeValue();
		if (ownMode != null) {
			return ModeEnum.TRANSPARENT.equals(ownMode);
		}
		JRStyle style = getBaseStyle(element);
		if (style != null) {
			ModeEnum mode = style.getModeValue();
			if (mode != null) {
				return ModeEnum.TRANSPARENT.equals(mode);
			}
		}
		return null;
	}

	private AlfaRGB getForecolor(JRCommonElement element) {
		Color ownForecolor = element.getOwnForecolor();
		if (ownForecolor != null) {
			return Colors.getSWTRGB4AWTGBColor(ownForecolor);
		}
		JRStyle style = getBaseStyle(element);
		if (style != null) {
			Color forecolor = style.getForecolor();
			if (forecolor != null) {
				return Colors.getSWTRGB4AWTGBColor(forecolor);
			}
		}
		return null;
	}

	private String getPattern(JRTextField element) {
		String ownPattern = element.getOwnPattern();
		if (ownPattern != null) {
			return ownPattern;
		}
		JRStyle baseStyle = getBaseStyle(element);
		if (baseStyle != null) {
			return baseStyle.getPattern();
		}
		return null;
	}

	private AlfaRGB getBackcolor(JRCommonElement element) {
		Color ownBackcolor = element.getOwnBackcolor();
		if (ownBackcolor != null) {
			return Colors.getSWTRGB4AWTGBColor(ownBackcolor);
		}
		JRStyle style = getBaseStyle(element);
		if (style != null) {
			Color backcolor = style.getBackcolor();
			if (backcolor != null) {
				return Colors.getSWTRGB4AWTGBColor(backcolor);
			}
		}
		return null;
	}

	private Float getLineWidth(JRPen pen) {
		Float ownLineWidth = pen.getOwnLineWidth();
		if (ownLineWidth != null) {
			return ownLineWidth;
		}
		JRStyle baseStyle = getBaseStyle(pen.getStyleContainer());
		if (baseStyle != null) {
			Float lineWidth = baseStyle.getLinePen().getLineWidth();
			if (lineWidth != null) {
				return lineWidth;
			}
		}
		return null;
	}

	private Float getLineWidth(JRBoxPen boxPen) {
		Float ownLineWidth = boxPen.getOwnLineWidth();
		if (ownLineWidth != null) {
			return ownLineWidth;
		}
		Float penLineWidth = boxPen.getBox().getPen().getOwnLineWidth();
		if (penLineWidth != null) {
			return penLineWidth;
		}
		JRStyle baseStyle = getBaseStyle(boxPen.getStyleContainer());
		if (baseStyle != null) {
			Float lineWidth = boxPen.getPen(baseStyle.getLineBox()).getLineWidth();
			if (lineWidth != null) {
				return lineWidth;
			}
		}
		return null;
	}

	private LineStyleEnum getLineStyleValue(JRPen pen) {
		LineStyleEnum ownLineStyle = pen.getOwnLineStyleValue();
		if (ownLineStyle != null) {
			return ownLineStyle;
		}
		JRStyle baseStyle = getBaseStyle(pen.getStyleContainer());
		if (baseStyle != null) {
			LineStyleEnum lineStyle = baseStyle.getLinePen().getLineStyleValue();
			if (lineStyle != null) {
				return lineStyle;
			}
		}
		return null;
	}

	private LineStyleEnum getLineStyleValue(JRBoxPen boxPen) {
		LineStyleEnum ownLineStyle = boxPen.getOwnLineStyleValue();
		if (ownLineStyle != null) {
			return ownLineStyle;
		}
		LineStyleEnum penLineStyle = boxPen.getBox().getPen().getOwnLineStyleValue();
		if (penLineStyle != null) {
			return penLineStyle;
		}
		JRStyle baseStyle = getBaseStyle(boxPen.getStyleContainer());
		if (baseStyle != null) {
			LineStyleEnum lineStyle = boxPen.getPen(baseStyle.getLineBox()).getLineStyleValue();
			if (lineStyle != null) {
				return lineStyle;
			}
		}
		return null;
	}

	private AlfaRGB getLineColor(JRPen pen) {
		Color ownLineColor = pen.getOwnLineColor();
		if (ownLineColor != null) {
			return Colors.getSWTRGB4AWTGBColor(ownLineColor);
		}
		JRStyle baseStyle = getBaseStyle(pen.getStyleContainer());
		if (baseStyle != null) {
			Color lineColor = baseStyle.getLinePen().getLineColor();
			if (lineColor != null) {
				return Colors.getSWTRGB4AWTGBColor(lineColor);
			}
		}
		return null;
	}

	private AlfaRGB getLineColor(JRBoxPen boxPen) {
		Color ownLineColor = boxPen.getOwnLineColor();
		if (ownLineColor != null) {
			return Colors.getSWTRGB4AWTGBColor(ownLineColor);
		}
		Color penLineColor = boxPen.getBox().getPen().getOwnLineColor();
		if (penLineColor != null) {
			return Colors.getSWTRGB4AWTGBColor(penLineColor);
		}
		JRStyle baseStyle = getBaseStyle(boxPen.getStyleContainer());
		if (baseStyle != null) {
			Color lineColor = boxPen.getPen(baseStyle.getLineBox()).getLineColor();
			if (lineColor != null) {
				return Colors.getSWTRGB4AWTGBColor(lineColor);
			}
		}
		return null;
	}

	private Integer getRadius(JRCommonRectangle rectangle) {
		Integer ownRadius = rectangle.getOwnRadius();
		if (ownRadius != null) {
			return ownRadius.intValue();
		}
		JRStyle baseStyle = getBaseStyle(rectangle);
		if (baseStyle != null) {
			Integer radius = baseStyle.getRadius();
			if (radius != null) {
				return radius.intValue();
			}
		}
		return null;
	}

	private ScaleImageEnum getScaleImageValue(JRCommonImage image) {
		ScaleImageEnum ownScaleImage = image.getOwnScaleImageValue();
		if (ownScaleImage != null) {
			return ownScaleImage;
		}
		JRStyle baseStyle = getBaseStyle(image);
		if (baseStyle != null) {
			ScaleImageEnum scaleImage = baseStyle.getScaleImageValue();
			if (scaleImage != null) {
				return scaleImage;
			}
		}
		return null;
	}

	private HorizontalTextAlignEnum getHorizontalTextAlign(JRTextAlignment alignment) {
		HorizontalTextAlignEnum ownHorizontalAlignment = alignment.getOwnHorizontalTextAlign();
		if (ownHorizontalAlignment != null) {
			return ownHorizontalAlignment;
		}
		JRStyle baseStyle = getBaseStyle(alignment);
		if (baseStyle != null) {
			HorizontalTextAlignEnum horizontalAlignment = baseStyle.getHorizontalTextAlign();
			if (horizontalAlignment != null) {
				return horizontalAlignment;
			}
		}
		return null;
	}

	private HorizontalImageAlignEnum getHorizontalImageAlign(JRImageAlignment alignment) {
		HorizontalImageAlignEnum ownHorizontalAlignment = alignment.getOwnHorizontalImageAlign();
		if (ownHorizontalAlignment != null) {
			return ownHorizontalAlignment;
		}
		JRStyle baseStyle = getBaseStyle(alignment);
		if (baseStyle != null) {
			HorizontalImageAlignEnum horizontalAlignment = baseStyle.getHorizontalImageAlign();
			if (horizontalAlignment != null) {
				return horizontalAlignment;
			}
		}
		return null;
	}

	private VerticalTextAlignEnum getVerticalTextAlign(JRTextAlignment alignment) {
		VerticalTextAlignEnum ownVerticalAlignment = alignment.getOwnVerticalTextAlign();
		if (ownVerticalAlignment != null) {
			return ownVerticalAlignment;
		}
		JRStyle baseStyle = getBaseStyle(alignment);
		if (baseStyle != null) {
			VerticalTextAlignEnum verticalAlignment = baseStyle.getVerticalTextAlign();
			if (verticalAlignment != null) {
				return verticalAlignment;
			}
		}
		return null;
	}

	private VerticalImageAlignEnum getVerticalImageAlign(JRImageAlignment alignment) {
		VerticalImageAlignEnum ownVerticalAlignment = alignment.getOwnVerticalImageAlign();
		if (ownVerticalAlignment != null) {
			return ownVerticalAlignment;
		}
		JRStyle baseStyle = getBaseStyle(alignment);
		if (baseStyle != null) {
			VerticalImageAlignEnum verticalAlignment = baseStyle.getVerticalImageAlign();
			if (verticalAlignment != null) {
				return verticalAlignment;
			}
		}
		return null;
	}

	private RotationEnum getRotationValue(JRCommonText element) {
		RotationEnum ownRotation = element.getOwnRotationValue();
		if (ownRotation != null) {
			return ownRotation;
		}
		JRStyle style = getBaseStyle(element);
		if (style != null) {
			RotationEnum rotation = style.getRotationValue();
			if (rotation != null) {
				return rotation;
			}
		}
		return null;
	}

	private String getMarkup(JRCommonText element) {
		String ownMarkup = element.getOwnMarkup();
		if (ownMarkup != null) {
			return ownMarkup;
		}
		JRStyle baseStyle = getBaseStyle(element);
		if (baseStyle != null) {
			String markup = baseStyle.getMarkup();
			if (markup != null) {
				return markup;
			}
		}
		return null;
	}

	private Boolean isBlankWhenNull(JRTextField element) {
		Boolean ownBlankWhenNull = element.isOwnBlankWhenNull();
		if (ownBlankWhenNull != null) {
			return ownBlankWhenNull.booleanValue();
		}
		JRStyle baseStyle = getBaseStyle(element);
		if (baseStyle != null) {
			Boolean blankWhenNull = baseStyle.isBlankWhenNull();
			if (blankWhenNull != null) {
				return blankWhenNull.booleanValue();
			}
		}
		return null;
	}

	private String getFontName(JRFont font) {
		String ownFontName = font.getOwnFontName();
		if (ownFontName != null) {
			return ownFontName;
		}
		JRStyle baseStyle = getBaseStyle(font);
		if (baseStyle != null) {
			String fontName = baseStyle.getFontName();
			if (fontName != null) {
				return fontName;
			}
		}
		return null;
	}

	private Boolean isBold(JRFont font) {
		Boolean ownBold = font.isOwnBold();
		if (ownBold != null) {
			return ownBold.booleanValue();
		}
		JRStyle baseStyle = getBaseStyle(font);
		if (baseStyle != null) {
			Boolean bold = baseStyle.isBold();
			if (bold != null) {
				return bold.booleanValue();
			}
		}
		return null;
	}

	private Boolean isItalic(JRFont font) {
		Boolean ownItalic = font.isOwnItalic();
		if (ownItalic != null) {
			return ownItalic.booleanValue();
		}
		JRStyle baseStyle = getBaseStyle(font);
		if (baseStyle != null) {
			Boolean italic = baseStyle.isItalic();
			if (italic != null) {
				return italic.booleanValue();
			}
		}
		return null;
	}

	private Boolean isUnderline(JRFont font) {
		Boolean ownUnderline = font.isOwnUnderline();
		if (ownUnderline != null) {
			return ownUnderline.booleanValue();
		}
		JRStyle baseStyle = getBaseStyle(font);
		if (baseStyle != null) {
			Boolean underline = baseStyle.isUnderline();
			if (underline != null) {
				return underline.booleanValue();
			}
		}
		return null;
	}

	private Boolean isStrikeThrough(JRFont font) {
		Boolean ownStrikeThrough = font.isOwnStrikeThrough();
		if (ownStrikeThrough != null) {
			return ownStrikeThrough.booleanValue();
		}
		JRStyle baseStyle = getBaseStyle(font);
		if (baseStyle != null) {
			Boolean strikeThrough = baseStyle.isStrikeThrough();
			if (strikeThrough != null) {
				return strikeThrough.booleanValue();
			}
		}
		return null;
	}

	private String getFontsize(JRFont font) {
		Float ownFontSize = font.getOwnFontsize();
		if (ownFontSize != null) {
			return String.valueOf(ownFontSize);
		}
		JRStyle baseStyle = getBaseStyle(font);
		if (baseStyle != null) {
			Float fontSize = baseStyle.getFontsize();
			if (fontSize != null) {
				return String.valueOf(fontSize);
			}
		}
		return null;
	}

	private String getPdfFontName(JRFont font) {
		String ownPdfFontName = font.getOwnPdfFontName();
		if (ownPdfFontName != null) {
			return ownPdfFontName;
		}
		JRStyle baseStyle = getBaseStyle(font);
		if (baseStyle != null) {
			String pdfFontName = baseStyle.getPdfFontName();
			if (pdfFontName != null) {
				return pdfFontName;
			}
		}
		return null;
	}

	private String getPdfEncoding(JRFont font) {
		String ownPdfEncoding = font.getOwnPdfEncoding();
		if (ownPdfEncoding != null) {
			return ownPdfEncoding;
		}
		JRStyle baseStyle = getBaseStyle(font);
		if (baseStyle != null) {
			String pdfEncoding = baseStyle.getPdfEncoding();
			if (pdfEncoding != null) {
				return pdfEncoding;
			}
		}
		return null;
	}

	private Integer getPadding(JRLineBox box) {
		Integer ownPadding = box.getOwnPadding();
		if (ownPadding != null) {
			return ownPadding;
		}
		JRStyle baseStyle = getBaseStyle(box);
		if (baseStyle != null) {
			Integer padding = baseStyle.getLineBox().getPadding();
			if (padding != null) {
				return padding;
			}
		}
		return null;
	}

	private Integer getTopPadding(JRLineBox box) {
		Integer ownTopPadding = box.getOwnTopPadding();
		if (ownTopPadding != null) {
			return ownTopPadding;
		}
		Integer ownPadding = box.getOwnPadding();
		if (ownPadding != null) {
			return ownPadding;
		}
		JRStyle style = getBaseStyle(box);
		if (style != null) {
			Integer topPadding = style.getLineBox().getTopPadding();
			if (topPadding != null) {
				return topPadding;
			}
		}
		return null;
	}

	private Integer getLeftPadding(JRLineBox box) {
		Integer ownLeftPadding = box.getOwnLeftPadding();
		if (ownLeftPadding != null) {
			return ownLeftPadding;
		}
		Integer ownPadding = box.getOwnPadding();
		if (ownPadding != null) {
			return ownPadding;
		}
		JRStyle style = getBaseStyle(box);
		if (style != null) {
			Integer leftPadding = style.getLineBox().getLeftPadding();
			if (leftPadding != null) {
				return leftPadding;
			}
		}
		return null;
	}

	private Integer getBottomPadding(JRLineBox box) {
		Integer ownBottomPadding = box.getOwnBottomPadding();
		if (ownBottomPadding != null) {
			return ownBottomPadding;
		}
		Integer ownPadding = box.getOwnPadding();
		if (ownPadding != null) {
			return ownPadding;
		}
		JRStyle style = getBaseStyle(box);
		if (style != null) {
			Integer bottomPadding = style.getLineBox().getBottomPadding();
			if (bottomPadding != null) {
				return bottomPadding;
			}
		}
		return null;
	}

	private Integer getRightPadding(JRLineBox box) {
		Integer ownRightPadding = box.getOwnRightPadding();
		if (ownRightPadding != null) {
			return ownRightPadding;
		}
		Integer ownPadding = box.getOwnPadding();
		if (ownPadding != null) {
			return ownPadding;
		}
		JRStyle style = getBaseStyle(box);
		if (style != null) {
			Integer rightPadding = style.getLineBox().getRightPadding();
			if (rightPadding != null) {
				return rightPadding;
			}
		}
		return null;
	}

	private Boolean isPdfEmbedded(JRFont font) {
		Boolean ownPdfEmbedded = font.isOwnPdfEmbedded();
		if (ownPdfEmbedded != null) {
			return ownPdfEmbedded.booleanValue();
		}
		JRStyle baseStyle = getBaseStyle(font);
		if (baseStyle != null) {
			Boolean pdfEmbedded = baseStyle.isPdfEmbedded();
			if (pdfEmbedded != null) {
				return pdfEmbedded.booleanValue();
			}
		}
		return null;
	}
}
