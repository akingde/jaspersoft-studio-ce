/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.style.view.text;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.eclipse.swt.graphics.RGB;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.exporter.BaseResource;
import com.jaspersoft.studio.editor.action.exporter.IExportedResourceHandler;
import com.jaspersoft.studio.editor.action.exporter.IResourceDefinition;
import com.jaspersoft.studio.editor.style.TemplateStyle;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.style.view.TemplateStyleView;
import com.jaspersoft.studio.utils.AlfaRGB;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.eclipse.ui.util.RunnableOverwriteQuestion;
import net.sf.jasperreports.eclipse.ui.util.RunnableOverwriteQuestion.RESPONSE_TYPE;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.eclipse.util.Pair;
import net.sf.jasperreports.eclipse.util.StringUtils;
import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.base.JRBaseFont;
import net.sf.jasperreports.engine.base.JRBaseLineBox;
import net.sf.jasperreports.engine.base.JRBoxPen;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.RotationEnum;
import net.sf.jasperreports.engine.type.VerticalTextAlignEnum;

/**
 * 
 * This class specialize the TemplateStyle to handle a text element (static text or text field).
 * 
 * @author Orlandin Marco
 *
 */
public class TextStyle extends TemplateStyle implements IExportedResourceHandler {
	
	/**
	 * The type of this template
	 */
	public static final String TEMPLATE_TYPE = "textStyle";

	private static final long serialVersionUID = 1539973461820002113L;

	private final static String TRANSPARENT = "is_transparent";

	private final static String FOREGROUND_COLOR = "foreground_color";

	private final static String BACKGROUND_COLOR = "background_color";

	private final static String VERTICAL_ALIGNMENT = "vertical_alignment";

	private final static String HORIZONTAL_ALIGNMENT = "horizontal_alignment";

	private final static String ROTATION = "rotation";

	private final static String BORDER_BOX = "linebox";

	private final static String FONT = "font";
	
	/**
	 * Filename used to store metadata of the exported resources
	 */
	private static final String INDEX_FILE_NAME = "index.properties";
	
	/**
	 * The name of the file where the styles will be written
	 */
	private static final String STYLE_FILE_NAME= "exportedStyles.xml";
	
	/**
	 * Cache when the list of exportable resource definition is requested, used to avoid multiple calculation
	 */
	private List<IResourceDefinition> cachedExportableResources = null;

	/**
	 * Cache when the list of importable resource definition is requested, used to avoid multiple calculation of the same container
	 */
	private Pair<String, List<IResourceDefinition>> cachedImportableResources = null;

	public TextStyle() {
		super(null, null);
	}

	public TextStyle(JRStyle style) {
		super(null, null);
		setTransparent(style.getOwnModeValue() != null ? ModeEnum.TRANSPARENT.equals(style.getOwnModeValue()) : true);
		setBackGround(style.getOwnBackcolor());
		setForeGround(style.getOwnForecolor());
		setVerticalAlignmen(style.getOwnVerticalTextAlign() != null ? style.getOwnVerticalTextAlign()
				: VerticalTextAlignEnum.TOP);
		setHorizontalAlignmen(style.getOwnHorizontalTextAlign() != null ? style.getOwnHorizontalTextAlign()
				: HorizontalTextAlignEnum.LEFT);
		setRotation(style.getOwnRotationValue() != null ? style.getOwnRotationValue() : RotationEnum.NONE);

		JRFont font = new JRBaseFont();
		font.setBold(style.isOwnBold() != null ? style.isOwnBold() : false);
		font.setItalic(new Boolean(style.isOwnItalic() != null ? style.isOwnItalic() : false));
		style.isItalic();
		font.setUnderline(new Boolean(style.isOwnUnderline() != null ? style.isOwnUnderline() : false));
		font.setStrikeThrough(new Boolean(style.isOwnStrikeThrough() != null ? style.isOwnStrikeThrough() : false));
		font.setFontName(style.getOwnFontName());
		font.setFontSize(style.getOwnFontsize());
		setFont(font);

		JRLineBox originBox = style.getLineBox();
		JRBaseLineBox copyBox = new JRBaseLineBox(null);
		copyBox.setPadding(originBox.getOwnPadding() != null ? new Integer(originBox.getOwnPadding()) : null);
		copyBox.setTopPadding(originBox.getOwnTopPadding() != null ? new Integer(originBox.getOwnTopPadding()) : null);
		copyBox.setBottomPadding(originBox.getOwnBottomPadding() != null ? new Integer(originBox.getOwnBottomPadding())
				: null);
		copyBox.setLeftPadding(originBox.getOwnLeftPadding() != null ? new Integer(originBox.getOwnLeftPadding()) : null);
		copyBox
				.setRightPadding(originBox.getOwnRightPadding() != null ? new Integer(originBox.getOwnRightPadding()) : null);
		copyLinePen(originBox.getPen(), copyBox.getPen());
		copyLinePen(originBox.getLeftPen(), copyBox.getLeftPen());
		copyLinePen(originBox.getRightPen(), copyBox.getRightPen());
		copyLinePen(originBox.getBottomPen(), copyBox.getBottomPen());
		copyLinePen(originBox.getTopPen(), copyBox.getTopPen());
		setBorders(copyBox);

		String name = style.getName();
		if (name != null && !name.isEmpty())
			setDescription(name);
	}

	public Boolean isTransparent() {
		Object value = getProperty(TRANSPARENT);
		return value != null ? (Boolean) value : false;
	}

	public AlfaRGB getBackGround() {
		return (AlfaRGB) getProperty(BACKGROUND_COLOR);
	}

	public AlfaRGB getForeGround() {
		return (AlfaRGB) getProperty(FOREGROUND_COLOR);
	}

	public Color getBackGroundColor() {
		return alfaRGBtoColor((AlfaRGB) getProperty(BACKGROUND_COLOR));
	}

	public Color getForeGroundColor() {
		return alfaRGBtoColor((AlfaRGB) getProperty(FOREGROUND_COLOR));
	}

	public VerticalTextAlignEnum getVerticalAlignmen() {
		return (VerticalTextAlignEnum) getProperty(VERTICAL_ALIGNMENT);
	}

	public HorizontalTextAlignEnum getHorizontalAlignmen() {
		return (HorizontalTextAlignEnum) getProperty(HORIZONTAL_ALIGNMENT);
	}

	public RotationEnum getRotation() {
		return (RotationEnum) getProperty(ROTATION);
	}

	public JRFont getFont() {
		return (JRFont) getProperty(FONT);
	}

	public JRLineBox getBorders() {
		return (JRLineBox) getProperty(BORDER_BOX);
	}

	public void setTransparent(Boolean value) {
		storePropertiy(TRANSPARENT, value);
	}

	public void setBackGround(AlfaRGB value) {
		storePropertiy(BACKGROUND_COLOR, value);
	}

	public void setForeGround(AlfaRGB value) {
		storePropertiy(FOREGROUND_COLOR, value);
	}

	public void setBackGround(Color value) {
		AlfaRGB aColor = value != null ? new AlfaRGB(new RGB(value.getRed(), value.getGreen(), value.getBlue()),
				value.getAlpha()) : null;
		storePropertiy(BACKGROUND_COLOR, aColor);
	}

	public void setForeGround(Color value) {
		AlfaRGB aColor = value != null ? new AlfaRGB(new RGB(value.getRed(), value.getGreen(), value.getBlue()),
				value.getAlpha()) : null;
		storePropertiy(FOREGROUND_COLOR, aColor);
	}

	public void setVerticalAlignmen(VerticalTextAlignEnum value) {
		storePropertiy(VERTICAL_ALIGNMENT, value);
	}

	public void setHorizontalAlignmen(HorizontalTextAlignEnum value) {
		storePropertiy(HORIZONTAL_ALIGNMENT, value);
	}

	public void setRotation(RotationEnum value) {
		storePropertiy(ROTATION, value);
	}

	public void setFont(JRFont value) {
		storePropertiy(FONT, value);
	}

	public void setBorders(JRLineBox value) {
		storePropertiy(BORDER_BOX, value);
	}

	private String getFontXML(JRFont value) {
		String result = "<font name=\"" + Misc.nvl(value.getOwnFontName()) + "\" ";
		result += "size=\"" + Misc.nvl(value.getOwnFontsize(),"") + "\" ";
		result += "isBold=\"" + value.isOwnBold() + "\" isItalic=\"" + value.isOwnItalic() + "\" ";
		result += "isUnderline=\"" + value.isOwnUnderline() + "\" isStriketrought=\"" + value.isOwnStrikeThrough() + "\"/>";
		return result;
	}

	private static JRFont buildFont(Node xmlFontNode) {
		NamedNodeMap fontAttributes = xmlFontNode.getAttributes();
		String fontName = fontAttributes.getNamedItem("name").getNodeValue();
		Float size = StringUtils.safeParseAsFloat(fontAttributes.getNamedItem("size").getNodeValue());
		boolean isBold = fontAttributes.getNamedItem("isBold").getNodeValue().equals("true");
		boolean isItalic = fontAttributes.getNamedItem("isItalic").getNodeValue().equals("true");
		boolean isUnderline = fontAttributes.getNamedItem("isUnderline").getNodeValue().equals("true");
		boolean isStriketrought = fontAttributes.getNamedItem("isStriketrought").getNodeValue().equals("true");

		JRBaseFont result = new JRBaseFont();
		result.setFontName(fontName);
		result.setFontSize(size);
		result.setBold(isBold);
		result.setItalic(isItalic);
		result.setUnderline(isUnderline);
		result.setStrikeThrough(isStriketrought);
		return result;
	}

	private AlfaRGB colorToAlfaRGB(Color value) {
		if (value == null)
			return null;
		int red = value.getRed();
		int green = value.getGreen();
		int blue = value.getBlue();
		return new AlfaRGB(new RGB(red, green, blue), value.getAlpha());
	}

	private static Color alfaRGBtoColor(AlfaRGB value) {
		if (value == null)
			return null;
		RGB rgb = value.getRgb();
		if (rgb == null)
			return null;
		int red = rgb.red;
		int green = rgb.green;
		int blue = rgb.blue;
		return new Color(red, green, blue, value.getAlfa());
	}

	private String getPenXML(String tagName, JRBoxPen value) {
		LineStyleEnum style = value.getOwnLineStyleValue() != null ? value.getOwnLineStyleValue() : LineStyleEnum.SOLID;
		float lineWidth = value.getOwnLineWidth() != null ? value.getOwnLineWidth() : 0f;
		Color color = value.getOwnLineColor() != null ? value.getOwnLineColor() : new Color(0, 0, 0);
		String result = "<" + tagName + " lineStyle=\"" + style.getName() + "\" ";
		result += "lineWidth=\"" + lineWidth + "\">";
		result += xmlColor("lineColor", colorToAlfaRGB(color));
		result += "</" + tagName + ">";
		return result;
	}

	private static void buildPen(Node xmlPenNode, JRBoxPen sourcePen) {
		NamedNodeMap penAttributes = xmlPenNode.getAttributes();
		LineStyleEnum lineStyle = getLineStyleFromValue(penAttributes.getNamedItem("lineStyle").getNodeValue()); 
		float lineWidth = Float.parseFloat(penAttributes.getNamedItem("lineWidth").getNodeValue());
		AlfaRGB lineColor = null;
		Node firstChild = xmlPenNode.getFirstChild();
		if (firstChild != null && firstChild.getNodeName().equals("lineColor")) {
			lineColor = rgbColor(firstChild);
		}
		if (lineColor != null)
			sourcePen.setLineColor(alfaRGBtoColor(lineColor));
		sourcePen.setLineStyle(lineStyle);
		sourcePen.setLineWidth(lineWidth);
	}

	private String getLineBoxXML(JRLineBox value) {
		String result = "<linebox padding=\"" + value.getOwnPadding() + "\" ";
		result += "leftPadding=\"" + value.getOwnLeftPadding() + "\" ";
		result += "rightPadding=\"" + value.getOwnRightPadding() + "\" topPadding=\"" + value.getOwnTopPadding() + "\" ";
		result += "bottomPadding=\"" + value.getOwnBottomPadding() + "\">";
		result += getPenXML("pen", value.getPen());
		result += getPenXML("leftPen", value.getLeftPen());
		result += getPenXML("rightPen", value.getRightPen());
		result += getPenXML("topPen", value.getTopPen());
		result += getPenXML("bottomPen", value.getBottomPen());
		result += "</linebox>";
		return result;
	}

	private static Integer getSafeIntValue(String value) {
		if (value != null && !value.equals("null")) {
			try {
				Integer intValue = Integer.parseInt(value);
				return intValue;
			} catch (NumberFormatException ex) {
				return null;
			}
		}
		return null;
	}

	private static JRLineBox buildBox(Node xmlBoxNode) {
		NamedNodeMap boxAttributes = xmlBoxNode.getAttributes();
		Integer padding = getSafeIntValue(boxAttributes.getNamedItem("padding").getNodeValue());
		Integer leftPadding = getSafeIntValue(boxAttributes.getNamedItem("leftPadding").getNodeValue());
		Integer rightPadding = getSafeIntValue(boxAttributes.getNamedItem("rightPadding").getNodeValue());
		Integer bottomPadding = getSafeIntValue(boxAttributes.getNamedItem("bottomPadding").getNodeValue());
		Integer topPadding = getSafeIntValue(boxAttributes.getNamedItem("topPadding").getNodeValue());

		JRBaseLineBox result = new JRBaseLineBox(null);
		result.setPadding(padding);
		result.setTopPadding(topPadding);
		result.setBottomPadding(bottomPadding);
		result.setLeftPadding(leftPadding);
		result.setRightPadding(rightPadding);

		Node firstChild = xmlBoxNode.getFirstChild();
		while (firstChild != null) {
			if (firstChild.getNodeName().equals("pen")) {
				buildPen(firstChild, result.getPen());
			} else if (firstChild.getNodeName().equals("leftPen")) {
				buildPen(firstChild, result.getLeftPen());
			} else if (firstChild.getNodeName().equals("rightPen")) {
				buildPen(firstChild, result.getRightPen());
			} else if (firstChild.getNodeName().equals("topPen")) {
				buildPen(firstChild, result.getTopPen());
			} else if (firstChild.getNodeName().equals("bottomPen")) {
				buildPen(firstChild, result.getBottomPen());
			}
			firstChild = firstChild.getNextSibling();
		}
		return result;
	}

	/**
	 * Return an XML representation of the crosstab style
	 * 
	 * @return a string containing the xml representation of the crosstab style
	 */
	@Override
	public String getXMLData() {
		String result = "<" + getTemplateName() + " type=\"" + getTemplateName() + "\" ";
		result += "verticalAlignment=\"" + getVerticalAlignmen().getName() + "\" horizontalAlignment=\""
				+ getHorizontalAlignmen().getName() + "\" rotation=\"" + getRotation().getName() + "\" ";
		result += "isTransparent=\"" + isTransparent().toString() + "\">";
		result += "<description>".concat(getDescription()).concat("</description>");
		if (getForeGround() != null)
			result += xmlColor("foreground", getForeGround());
		if (getBackGround() != null)
			result += xmlColor("background", getBackGround());
		result += getFontXML(getFont());
		result += getLineBoxXML(getBorders());
		result += "</" + getTemplateName() + ">";
		return result;
	}

	/**
	 * Rebuild a CrosstabStyle from its XML representation
	 * 
	 * @param xmlNode
	 *          an XML node with the representation of a CrosstabStyle
	 * @return the CrosstabStyle builded from the xmlNode, or null if something goes wrong during the rebuilding
	 */
	@Override
	public TemplateStyle buildFromXML(Node xmlNode) {
		try {
			NamedNodeMap rootAttributes = xmlNode.getAttributes();
			VerticalTextAlignEnum verticalAlignment = getTextVAlignmentFromValue(rootAttributes.getNamedItem("verticalAlignment").getNodeValue());
			HorizontalTextAlignEnum horizontalAlignment = getTextHAlignmentFromValue(rootAttributes.getNamedItem("horizontalAlignment").getNodeValue());
			RotationEnum rotation = getRotationFromValue(rootAttributes.getNamedItem("rotation").getNodeValue());
			boolean transparent = rootAttributes.getNamedItem("isTransparent").getNodeValue().equals("true");

			AlfaRGB background = null;
			AlfaRGB foreground = null;
			JRFont font = null;
			JRLineBox box = null;

			Node firstChild = xmlNode.getFirstChild();
			String description = null;
			while (firstChild != null) {
				if (firstChild.getNodeName().equals("foreground")) {
					foreground = rgbColor(firstChild);
				} else if (firstChild.getNodeName().equals("background")) {
					background = rgbColor(firstChild);
				} else if (firstChild.getNodeName().equals("font")) {
					font = buildFont(firstChild);
				} else if (firstChild.getNodeName().equals("linebox")) {
					box = buildBox(firstChild);
				} else if (firstChild.getNodeName().equals("description")) {
					Node descriptionNode = firstChild.getChildNodes().item(0);
					description = descriptionNode != null ? descriptionNode.getNodeValue() : "";
				}
				firstChild = firstChild.getNextSibling();
			}
			TextStyle result = new TextStyle();
			result.setVerticalAlignmen(verticalAlignment);
			result.setHorizontalAlignmen(horizontalAlignment);
			result.setRotation(rotation);
			result.setTransparent(transparent);
			result.setBackGround(background);
			result.setForeGround(foreground);
			result.setFont(font);
			result.setBorders(box);
			result.setDescription(description);
			return result;
		} catch (Exception ex) {
			System.out.println("Unable to rebuild the text style");
			ex.printStackTrace();
			return null;
		}
	}

	public TextStyle clone() {
		TextStyle copy = new TextStyle();
		copy.setBackGround(getBackGround() != null ? getBackGround().clone() : null);
		copy.setForeGround(getForeGround() != null ? getForeGround().clone() : null);
		copy.setTransparent(new Boolean(isTransparent()));
		copy.setDescription(new String(getDescription()));
		copy.setRotation(getRotation());
		copy.setHorizontalAlignmen(getHorizontalAlignmen());
		copy.setVerticalAlignmen(getVerticalAlignmen());
		JRBaseFont copyFont = new JRBaseFont();
		JRFont originFont = getFont();
		copyFont.setBold(new Boolean(originFont.isOwnBold()));
		copyFont.setItalic(new Boolean(originFont.isOwnItalic()));
		copyFont.setUnderline(new Boolean(originFont.isOwnUnderline()));
		copyFont.setStrikeThrough(new Boolean(originFont.isOwnStrikeThrough()));
		copyFont.setFontName(originFont.getOwnFontName());
		copyFont.setFontSize(originFont.getOwnFontsize());
		copy.setFont(copyFont);
		JRLineBox originBox = getBorders();
		JRBaseLineBox copyBox = new JRBaseLineBox(null);
		copyBox.setPadding(originBox.getOwnPadding() != null ? new Integer(originBox.getOwnPadding()) : null);
		copyBox.setTopPadding(originBox.getOwnTopPadding() != null ? new Integer(originBox.getOwnTopPadding()) : null);
		copyBox.setBottomPadding(originBox.getOwnBottomPadding() != null ? new Integer(originBox.getOwnBottomPadding())
				: null);
		copyBox.setLeftPadding(originBox.getOwnLeftPadding() != null ? new Integer(originBox.getOwnLeftPadding()) : null);
		copyBox
				.setRightPadding(originBox.getOwnRightPadding() != null ? new Integer(originBox.getOwnRightPadding()) : null);
		copyLinePen(originBox.getPen(), copyBox.getPen());
		copyLinePen(originBox.getLeftPen(), copyBox.getLeftPen());
		copyLinePen(originBox.getRightPen(), copyBox.getRightPen());
		copyLinePen(originBox.getBottomPen(), copyBox.getBottomPen());
		copyLinePen(originBox.getTopPen(), copyBox.getTopPen());
		copy.setBorders(copyBox);
		return copy;
	}

	private boolean equalsPen(JRBoxPen pen1, JRBoxPen pen2) {
		if (pen1 == null)
			return pen2 == null;
		if (pen2 == null)
			return pen1 == null;
		if (!ModelUtils.safeEquals(pen1.getOwnLineColor(), pen2.getOwnLineColor()))
			return false;
		if (!ModelUtils.safeEquals(pen1.getOwnLineStyleValue(), pen2.getOwnLineStyleValue()))
			return false;
		if (!ModelUtils.safeEquals(pen1.getOwnLineWidth(), pen2.getOwnLineWidth()))
			return false;
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TextStyle) {
			TextStyle element2 = (TextStyle) obj;
			if (!element2.isTransparent().equals(isTransparent()))
				return false;
			if (!ModelUtils.safeEquals(getBackGround(), element2.getBackGround()))
				return false;
			if (!ModelUtils.safeEquals(getForeGround(), element2.getForeGround()))
				return false;
			if (!ModelUtils.safeEquals(getHorizontalAlignmen(), element2.getHorizontalAlignmen()))
				return false;
			if (!ModelUtils.safeEquals(getVerticalAlignmen(), element2.getVerticalAlignmen()))
				return false;
			if (!ModelUtils.safeEquals(getRotation(), element2.getRotation()))
				return false;
			JRFont font1 = getFont();
			JRFont font2 = element2.getFont();
			if (!ModelUtils.safeEquals(font1.isOwnBold(), font2.isOwnBold()))
				return false;
			if (!ModelUtils.safeEquals(font1.isOwnItalic(), font2.isOwnItalic()))
				return false;
			if (!ModelUtils.safeEquals(font1.isOwnUnderline(), font2.isOwnUnderline()))
				return false;
			if (!ModelUtils.safeEquals(font1.isOwnStrikeThrough(), font2.isOwnStrikeThrough()))
				return false;
			if (!ModelUtils.safeEquals(font1.getOwnFontName(), font2.getOwnFontName()))
				return false;
			if (!ModelUtils.safeEquals(font1.getOwnFontsize(), font2.getOwnFontsize()))
				return false;
			JRLineBox box1 = getBorders();
			JRLineBox box2 = element2.getBorders();
			if (!ModelUtils.safeEquals(box1.getOwnPadding(), box2.getOwnPadding()))
				return false;
			if (!ModelUtils.safeEquals(box1.getOwnTopPadding(), box2.getOwnTopPadding()))
				return false;
			if (!ModelUtils.safeEquals(box1.getOwnBottomPadding(), box2.getOwnBottomPadding()))
				return false;
			if (!ModelUtils.safeEquals(box1.getOwnLeftPadding(), box2.getOwnLeftPadding()))
				return false;
			if (!ModelUtils.safeEquals(box1.getOwnRightPadding(), box2.getOwnRightPadding()))
				return false;
			if (!equalsPen(box1.getPen(), box2.getPen()))
				return false;
			if (!equalsPen(box1.getBottomPen(), box2.getBottomPen()))
				return false;
			if (!equalsPen(box1.getTopPen(), box2.getTopPen()))
				return false;
			if (!equalsPen(box1.getLeftPen(), box2.getLeftPen()))
				return false;
			if (!equalsPen(box1.getRightPen(), box2.getRightPen()))
				return false;
			return true;
		}
		return false;
	}

	private void copyLinePen(JRBoxPen originPen, JRBoxPen destinationPen) {
		Color newColor = null;
		Color originLineColor = originPen.getOwnLineColor();
		if (originLineColor != null)
			newColor = new Color(originLineColor.getRed(), originLineColor.getGreen(), originLineColor.getBlue(),
					originLineColor.getAlpha());
		destinationPen.setLineColor(newColor);
		destinationPen.setLineStyle(originPen.getOwnLineStyleValue());
		destinationPen.setLineWidth(originPen.getOwnLineWidth() != null ? new Float(originPen.getOwnLineWidth()) : null);
	}

	/**
	 * Return an unique identifier of the crosstab template type
	 * 
	 * @return a string representing the type of the crosstab template
	 */
	@Override
	public String getTemplateName() {
		return TEMPLATE_TYPE;
	}
	
	/**
	 * Return the name of the exported resource for the text styles
	 * 
	 * @return a not null and fixed string
	 */
	@Override
	public String getResourceNameExport() {
		Collection<TemplateStyle> styles = TemplateStyleView.getTemplateStylesStorage().getStylesDescriptors(TextStyle.TEMPLATE_TYPE);
		return "Text Styles (" + styles.size() + ")";
	}
	
	@Override
	public String getResourceNameImport(File exportedContainer) {
		//Load the styles from the exported folder
		List<TemplateStyle> loadedStyles = new ArrayList<TemplateStyle>();
		File exportedFolder = new File(exportedContainer, TextStyle.TEMPLATE_TYPE);
		for(File styleDefinition : exportedFolder.listFiles()){
			try{
				String xml = FileUtils.readFileAsAString(styleDefinition);
				List<TemplateStyle> fileStyles = TemplateStyleView.getTemplateStylesStorage().readTemplateFromFile(xml);
				loadedStyles.addAll(fileStyles);
			} catch (Exception ex){
				ex.printStackTrace();
			}
		}
		return "Text Styles (" + loadedStyles.size() + ")";
	}

	/**
	 * Backup all the styles of a specific type into a folder and return it.
	 * All the files are stored into a single xml file
	 * 
	 * @return a not null folder containing the backup of the styles
	 */
	@Override
	public File exportContentFolder(List<IResourceDefinition> resourcesToExport) {
		//Create the temp folder
		File tempDir = new File(System.getProperty("java.io.tmpdir")); //$NON-NLS-1$
		tempDir.deleteOnExit();
		File destDir = new File (tempDir, TextStyle.TEMPLATE_TYPE);
		if (destDir.exists()) FileUtils.recursiveDelete(destDir);
		destDir.mkdirs();
		
		//Create the set of the resources that should be exported
		HashSet<TemplateStyle> resourcesToExportSet = new HashSet<TemplateStyle>();
		for(IResourceDefinition definition : resourcesToExport){
			resourcesToExportSet.add((TemplateStyle)definition.getData());
		}

		//Convert the styles handled by this class into a single xml
		Properties props = new Properties();
		Collection<TemplateStyle> styles = TemplateStyleView.getTemplateStylesStorage().getStylesDescriptors(TextStyle.TEMPLATE_TYPE);
		StringBuffer xmlBuffer = new StringBuffer();
		xmlBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n<templateStyles>");  //$NON-NLS-1$
		int index = 0;
		for(TemplateStyle style : styles){
			if (resourcesToExportSet.contains(style)) {
				xmlBuffer.append(style.getXMLData());
				props.put(index, style.getDescription());
				index++;
			}
		}
		xmlBuffer.append("</templateStyles>"); //$NON-NLS-1$
		
		//Write the xml on the exportedfolder
		try {
			OutputStream file = new FileOutputStream(new File(destDir, STYLE_FILE_NAME));  //$NON-NLS-1$
			String xml = xmlBuffer.toString();
			file.write(xml.getBytes("UTF-8")); //$NON-NLS-1$
			file.flush();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Write the index file
		FileOutputStream out = null;
		try{
			out = new FileOutputStream(new File(destDir, INDEX_FILE_NAME));
			props.store(out, "Exported Text Styles Index");
		} catch (Exception ex){
			JaspersoftStudioPlugin.getInstance().logError(ex);
		} finally {
			FileUtils.closeStream(out);
		}
		return destDir;
	}

	/**
	 * Restore the selected styles from the content folder
	 * 
	 * @param exportedContainer a not null file containing all the resources exported 
	 * by the export procedure
	 */
	@Override
	public void restoreContentFolder(File exportedContainer, List<IResourceDefinition> resourcesToImport) {
		
		//Create the set of the files to import
		HashSet<Integer> stylesToImport = new HashSet<Integer>();
		for(IResourceDefinition resourceToImport : resourcesToImport){
			stylesToImport.add((Integer)resourceToImport.getData());
		}
		
		//Load the styles from the exported folder
		List<TemplateStyle> loadedStyles = new ArrayList<TemplateStyle>();
		File exportedFolder = new File(exportedContainer, TextStyle.TEMPLATE_TYPE);
		File exportedFile = new File(exportedFolder, STYLE_FILE_NAME);
		try{
			String xml = FileUtils.readFileAsAString(exportedFile);
			List<TemplateStyle> fileStyles = TemplateStyleView.getTemplateStylesStorage().readTemplateFromFile(xml);
			int index = 0;
			for(TemplateStyle style : fileStyles){
				if (stylesToImport.contains(index)){
					loadedStyles.add(style);
				}
				index++;
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}
		
		//Search the duplicated styles name
		HashMap<String, TemplateStyle> existingStyles = getExistingStyles(TextStyle.TEMPLATE_TYPE);
		List<String> duplicatedStyles = new ArrayList<String>();
		for(TemplateStyle style : loadedStyles){
			if (existingStyles.containsKey(style.getDescription())){
				duplicatedStyles.add(style.getDescription());
			}
		}
		
		//If there are duplicate ask the user how to handle them
		RESPONSE_TYPE response = RESPONSE_TYPE.KEEP_BOTH;
		if (duplicatedStyles.size() > 0){
			response = askOverwrite(duplicatedStyles);
		}
		
		//Restore the imported style according to the user response for the duplicated
		for(TemplateStyle style : loadedStyles){
			String name = style.getDescription();
			if (existingStyles.containsKey(name)){
				if (response == RESPONSE_TYPE.KEEP_BOTH){
					style.setDescription(getName(existingStyles, name));
					TemplateStyleView.getTemplateStylesStorage().addStyle(style);
				} else if (response == RESPONSE_TYPE.OVERWRITE){
					TemplateStyleView.getTemplateStylesStorage().removeStyle(existingStyles.get(name));
					TemplateStyleView.getTemplateStylesStorage().addStyle(style);
				}
			} else {
				TemplateStyleView.getTemplateStylesStorage().addStyle(style);
			}
		}
	}

	/**
	 * Check if there are resource to import in the imported folder
	 * 
	 * @return true if there is something to import, false otherwise
	 */
	@Override
	public List<IResourceDefinition> getRestorableResources(File exportedContainer) {
		String containerPath = exportedContainer.getAbsolutePath();
		if (cachedImportableResources == null || 
				!cachedImportableResources.getKey().equals(containerPath)){
			
	
			File exportedFolder = new File(exportedContainer, TextStyle.TEMPLATE_TYPE);
			File indexFile = new File(exportedFolder, INDEX_FILE_NAME);
			if (indexFile.exists()){
				FileInputStream is = null;
				try{
					List<IResourceDefinition> result = new ArrayList<IResourceDefinition>();
					is = new FileInputStream(indexFile);
					Properties loadedProperties = new Properties();
					loadedProperties.load(is);
					for(Entry<Object, Object> entry : loadedProperties.entrySet()){
						BaseResource resource = new BaseResource(entry.getValue().toString());
						resource.setData(entry.getKey());
						result.add(resource);
					}
					cachedImportableResources = new Pair<String, List<IResourceDefinition>>(containerPath, result);
				} catch (Exception ex){ 
					JaspersoftStudioPlugin.getInstance().logError(ex);
					cachedImportableResources = new Pair<String, List<IResourceDefinition>>(containerPath, new ArrayList<IResourceDefinition>());
				} finally {
					FileUtils.closeStream(is);
				}
			} else {
				cachedImportableResources = new Pair<String, List<IResourceDefinition>>(containerPath, new ArrayList<IResourceDefinition>());
			}
		}
		return cachedImportableResources.getValue();
	}

	/**
	 * Check if there are styles of the current type 
	 * 
	 * @return true if there are at least one text template style, false otherwise
	 */
	@Override
	public List<IResourceDefinition> getExportableResources() {
		if (cachedExportableResources == null) {
			Collection<TemplateStyle> styles = TemplateStyleView.getTemplateStylesStorage().getStylesDescriptors(TextStyle.TEMPLATE_TYPE);
			cachedExportableResources = new ArrayList<IResourceDefinition>();
			for(TemplateStyle style : styles){
				BaseResource resource = new BaseResource(style.getDescription());
				resource.setData(style);
				cachedExportableResources.add(resource);
			}
		}
		return cachedExportableResources;
	}
	
	/**
	 * Show a question dialog with the buttons overwrite, skip and  keep both,
	 * no and cancel. It is executed in the graphic thread so this method dosen't need to be called
	 * inside a the display thread.
	 *
	 * @param stylesName the list of the overlapping names, this will be used to build the message and show
	 * which elements are overlapping, must be not null
	 * @return a not null enum that can be Overwrite, Skip or Keep Both
	 */
	private RESPONSE_TYPE askOverwrite(List<String> stylesName) {
		String baseMessage = Messages.TextStyle_overlappingMEssage;
		StringBuilder message = new StringBuilder("\n");
		int index = 1;
		for(String adapter : stylesName){
			message.append(adapter);
			message.append(index == stylesName.size() ? ".\n" : ",\n"); //$NON-NLS-1$ //$NON-NLS-2$
			index ++;
		}
		String composedMessage = MessageFormat.format(baseMessage, new Object[]{message.toString()});
		return RunnableOverwriteQuestion.showQuestion(Messages.TextStyle_overlappingTitle, composedMessage);
	}
	
	/**
	 * Return an unique name for the imported resource, not already used by the others
	 * 
	 * @param existingStyles the existing resources, the search of the name will be test against this map, must be
	 * not null
	 * @param baseName the base name used into the search
	 * @return a not null unique name for the new resource
	 */
	private String getName(HashMap<String, TemplateStyle> existingStyles, String baseName){
		int index = 1;
		String newName = baseName + "_" + index; //$NON-NLS-1$
		while(existingStyles.containsKey(newName)){
			index++;
			newName = baseName + "_" + index; //$NON-NLS-1$
		}
		return newName;
	}
}
