/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.figures.svg;

import org.apache.batik.svggen.SVGColor;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGPaintDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.wb.swt.SWTResourceManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.css.CSSValue;
import org.w3c.dom.css.RGBColor;
import org.w3c.dom.css.ViewCSS;
import org.w3c.dom.svg.SVGPaint;

public class SVGUtils {

	private SVGUtils() {
	}

	public static String toSVGColor(Document document, Color color) {
		java.awt.Color awtColor = new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue());
		SVGGeneratorContext svgContext = SVGGeneratorContext.createDefault(document);
		SVGPaintDescriptor paint = SVGColor.toSVG(awtColor, svgContext);
		return paint.getPaintValue();
	}

	public static Color toSWTColor(Element element, String attributeName) {
		Document document = element.getOwnerDocument();
		ViewCSS viewCSS = (ViewCSS) document.getDocumentElement();
		CSSStyleDeclaration computedStyle = viewCSS.getComputedStyle(element, null);
		SVGPaint svgPaint = (SVGPaint) computedStyle.getPropertyCSSValue(attributeName);
		if (svgPaint.getPaintType() == SVGPaint.SVG_PAINTTYPE_RGBCOLOR) {
			RGBColor rgb = svgPaint.getRGBColor();
			float red = rgb.getRed().getFloatValue(CSSValue.CSS_PRIMITIVE_VALUE);
			float green = rgb.getGreen().getFloatValue(CSSValue.CSS_PRIMITIVE_VALUE);
			float blue = rgb.getBlue().getFloatValue(CSSValue.CSS_PRIMITIVE_VALUE);
			return SWTResourceManager.getColor((int) red, (int) green, (int) blue);
		}
		return null;
	}
}
