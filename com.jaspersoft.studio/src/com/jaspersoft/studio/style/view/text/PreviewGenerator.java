/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.style.view.text;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;

import com.jaspersoft.studio.jasper.JSSDrawVisitor;
import com.jaspersoft.studio.jasper.JSSReportConverter;
import com.jaspersoft.studio.utils.ImageUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.SplitTypeEnum;
import net.sf.jasperreports.engine.type.StretchTypeEnum;

/**
 * Class that offer the static methods to generate a JR static text image starting from a TextStyle
 * 
 * @author Orlandin Marco
 *
 */
public class PreviewGenerator {

	/**
	 * Buffered image where the preview is painted
	 */
	private static BufferedImage bi = null;

	/**
	 * Visitor used to print the jr element
	 */
	private static JSSDrawVisitor visitor = null;

	/**
	 * Design where the printed static text element is placed
	 */
	private static JasperDesign jasperDesign = null;

	/**
	 * The static text element
	 */
	private static JRDesignStaticText textElement = null;

	/**
	 * Generate an swt image data as preview of a text style. It show how a text element should appear when the style is
	 * applied
	 * 
	 * @param style
	 *          The style used to generate the preview
	 * @param width
	 *          the width of the preview area
	 * @param height
	 *          the eight of the preview area
	 * @param containerBackground
	 *          the background used then the style has a transparent background
	 * @return and swt image data
	 */
	public static ImageData generatePreview(TextStyle style, int width, int height, RGB containerBackground) {
		if (jasperDesign == null)
			createDesign();
		((JRDesignBand) jasperDesign.getTitle()).setHeight(height);
		jasperDesign.setPageWidth(width);
		setDesignElement(style, width, height, containerBackground);
		// If we have not a buffered image or the old one has a different size from what we need, the we create a new
		// buffered image and the cache it
		createBufferedImage();
		visitor.visitStaticText(textElement);
		return ImageUtils.convertToSWT(bi);
	}

	/**
	 * Create a very minimal jasperdesign where the static text is placed. It is cached since we don't need to create it
	 * everytime
	 */
	private static void createDesign() {
		jasperDesign = new JasperDesign();
		jasperDesign.setJasperReportsContext(JasperReportsConfiguration.getDefaultInstance());
		JRDesignBand jrBand = new JRDesignBand();
		jasperDesign.setTitle(jrBand);
		textElement = new JRDesignStaticText();
		jasperDesign.setLeftMargin(0);
		jasperDesign.setRightMargin(0);
		jasperDesign.setTopMargin(0);
		jasperDesign.setBottomMargin(0);
		jrBand.addElement(textElement);
		jrBand.setSplitType(SplitTypeEnum.STRETCH);
		textElement.setStretchType(StretchTypeEnum.NO_STRETCH);
		textElement.setPrintRepeatedValues(false);
		textElement.setPrintWhenDetailOverflows(true);
	}

	/**
	 * Set the static text element to have the appearance described in the text style
	 * 
	 * @param style
	 *          text style
	 * @param width
	 *          width of the static text element
	 * @param height
	 *          height of the static text element
	 * @param containerBackground
	 *          the background used then the style has a transparent background
	 */
	private static void setDesignElement(TextStyle style, int width, int height, RGB containerBackground) {
		textElement.setWidth(width - 1);
		textElement.setHeight(height - 1);
		textElement.setText(style.getDescription());
		UpdateStyleCommand.applayStyleToTextElement(style, textElement);
		if (style.isTransparent()) {
			textElement.setMode(ModeEnum.OPAQUE);
			textElement.setBackcolor(new Color(containerBackground.red, containerBackground.green, containerBackground.blue));
		}
	}

	/**
	 * Create a buffered image of the same size of the text element and cache it
	 */
	private static void createBufferedImage() {
		bi = new BufferedImage(textElement.getWidth(), textElement.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bi.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		visitor = new JSSDrawVisitor(
				new JSSReportConverter(JasperReportsConfiguration.getDefaultInstance(), jasperDesign, true), g2d);
	}
}
