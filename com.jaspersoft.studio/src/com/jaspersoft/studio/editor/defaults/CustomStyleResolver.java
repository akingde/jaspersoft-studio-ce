/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.defaults;

import java.awt.Color;

import com.jaspersoft.studio.model.MEllipse;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MGraphicElementLineBox;
import com.jaspersoft.studio.model.MGraphicElementLinePen;
import com.jaspersoft.studio.model.MLine;
import com.jaspersoft.studio.model.MRectangle;
import com.jaspersoft.studio.model.image.MImage;
import com.jaspersoft.studio.model.text.MTextElement;
import com.jaspersoft.studio.model.text.MTextField;

import net.sf.jasperreports.engine.JRBoxContainer;
import net.sf.jasperreports.engine.JREllipse;
import net.sf.jasperreports.engine.JRLine;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.JRRectangle;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.base.JRBaseParagraph;
import net.sf.jasperreports.engine.base.JRBoxPen;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignGraphicElement;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JRDesignTextElement;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import net.sf.jasperreports.engine.util.StyleResolver;

/**
 * 
 * Custom methods to apply the attribute of an element, included the ones Inherited by its style or the default one, to
 * another element of the same type.
 * 
 * This class redefine some resolvers to have as result of an attribute value null if it is not defined anywhere. The
 * normal behavior would be in some cases a default value
 * 
 * @author Orlandin Marco
 *
 */
public class CustomStyleResolver {

	// -- CUSTOM RESOLVER WITH NULL DEFAULT VALUE --//

	private static String getFontName(JRStyle style, StyleResolver sr) {
		String ownFontName = style.getOwnFontName();
		if (ownFontName != null) {
			return ownFontName;
		}
		JRStyle baseStyle = sr.getBaseStyle(style);
		if (baseStyle != null) {
			String fontName = baseStyle.getFontName();
			if (fontName != null) {
				return fontName;
			}
		}
		return null;
	}

	private static String getMarkup(JRStyle style, StyleResolver sr) {
		String ownMarkup = style.getOwnMarkup();
		if (ownMarkup != null) {
			return ownMarkup;
		}
		JRStyle baseStyle = sr.getBaseStyle(style);
		if (baseStyle != null) {
			return baseStyle.getMarkup();
		}
		return null;
	}

	private static String getPdfFontName(JRStyle style, StyleResolver sr) {
		String ownPdfFontName = style.getOwnPdfFontName();
		if (ownPdfFontName != null) {
			return ownPdfFontName;
		}
		JRStyle baseStyle = sr.getBaseStyle(style);
		if (baseStyle != null) {
			String pdfFontName = baseStyle.getPdfFontName();
			if (pdfFontName != null) {
				return pdfFontName;
			}
		}
		return null;
	}

	private static String getPdfEncoding(JRStyle style, StyleResolver sr) {
		String ownPdfEncoding = style.getOwnPdfEncoding();
		if (ownPdfEncoding != null) {
			return ownPdfEncoding;
		}
		JRStyle baseStyle = sr.getBaseStyle(style);
		if (baseStyle != null) {
			String pdfEncoding = baseStyle.getPdfEncoding();
			if (pdfEncoding != null) {
				return pdfEncoding;
			}
		}
		return null;
	}

	private static Integer getPadding(JRLineBox box, StyleResolver sr) {
		Integer ownPadding = box.getOwnPadding();
		if (ownPadding != null) {
			return ownPadding;
		}
		JRStyle baseStyle = sr.getBaseStyle(box);
		if (baseStyle != null) {
			Integer padding = baseStyle.getLineBox().getPadding();
			if (padding != null) {
				return padding;
			}
		}
		return null;
	}

	private static Integer getTopPadding(JRLineBox box, StyleResolver sr) {
		Integer ownTopPadding = box.getOwnTopPadding();
		if (ownTopPadding != null) {
			return ownTopPadding;
		}
		Integer ownPadding = box.getOwnPadding();
		if (ownPadding != null) {
			return ownPadding;
		}
		JRStyle style = sr.getBaseStyle(box);
		if (style != null) {
			Integer topPadding = style.getLineBox().getTopPadding();
			if (topPadding != null) {
				return topPadding;
			}
		}
		return null;
	}

	private static Integer getLeftPadding(JRLineBox box, StyleResolver sr) {
		Integer ownLeftPadding = box.getOwnLeftPadding();
		if (ownLeftPadding != null) {
			return ownLeftPadding;
		}
		Integer ownPadding = box.getOwnPadding();
		if (ownPadding != null) {
			return ownPadding;
		}
		JRStyle style = sr.getBaseStyle(box);
		if (style != null) {
			Integer leftPadding = style.getLineBox().getLeftPadding();
			if (leftPadding != null) {
				return leftPadding;
			}
		}
		return null;
	}

	private static Integer getBottomPadding(JRLineBox box, StyleResolver sr) {
		Integer ownBottomPadding = box.getOwnBottomPadding();
		if (ownBottomPadding != null) {
			return ownBottomPadding;
		}
		Integer ownPadding = box.getOwnPadding();
		if (ownPadding != null) {
			return ownPadding;
		}
		JRStyle style = sr.getBaseStyle(box);
		if (style != null) {
			Integer bottomPadding = style.getLineBox().getBottomPadding();
			if (bottomPadding != null) {
				return bottomPadding;
			}
		}
		return null;
	}

	private static Integer getRightPadding(JRLineBox box, StyleResolver sr) {
		Integer ownRightPadding = box.getOwnRightPadding();
		if (ownRightPadding != null) {
			return ownRightPadding;
		}
		Integer ownPadding = box.getOwnPadding();
		if (ownPadding != null) {
			return ownPadding;
		}
		JRStyle style = sr.getBaseStyle(box);
		if (style != null) {
			Integer rightPadding = style.getLineBox().getRightPadding();
			if (rightPadding != null) {
				return rightPadding;
			}
		}
		return null;
	}

	private static LineStyleEnum getLineStyleValue(JRBoxPen boxPen, StyleResolver sr) {
		LineStyleEnum ownLineStyle = boxPen.getOwnLineStyleValue();
		if (ownLineStyle != null) {
			return ownLineStyle;
		}
		LineStyleEnum penLineStyle = boxPen.getBox().getPen().getOwnLineStyleValue();
		if (penLineStyle != null) {
			return penLineStyle;
		}
		JRStyle baseStyle = sr.getBaseStyle(boxPen.getPenContainer());
		if (baseStyle != null) {
			LineStyleEnum lineStyle = boxPen.getPen(baseStyle.getLineBox()).getLineStyleValue();
			if (lineStyle != null) {
				return lineStyle;
			}
		}
		return null;
	}

	private static LineStyleEnum getLineStyleValue(JRPen pen, StyleResolver sr) {
		if (pen instanceof JRBoxPen) {
			return getLineStyleValue((JRBoxPen) pen, sr);
		} else {
			LineStyleEnum ownLineStyle = pen.getOwnLineStyleValue();
			if (ownLineStyle != null) {
				return ownLineStyle;
			}
			JRStyle baseStyle = sr.getBaseStyle(pen.getPenContainer());
			if (baseStyle != null) {
				LineStyleEnum lineStyle = baseStyle.getLinePen().getLineStyleValue();
				if (lineStyle != null) {
					return lineStyle;
				}
			}
		}
		return null;
	}

	// -- END OF THE CUSTOM RESOLVER --//

	protected static Color getColorClone(Color source) {
		if (source == null)
			return null;
		else
			return new Color(source.getRed(), source.getGreen(), source.getBlue(), source.getAlpha());
	}

	private static void inheritLinePenProeprties(JRPen jrPenTarget, JRPen jrStylePen, StyleResolver sr) {
		if (jrPenTarget != null && jrStylePen != null) {
			if (jrPenTarget.getOwnLineColor() == null)
				jrPenTarget.setLineColor(sr.getLineColor(jrStylePen, null));
			if (jrPenTarget.getOwnLineStyleValue() == null)
				jrPenTarget.setLineStyle(getLineStyleValue(jrStylePen, sr));
			if (jrPenTarget.getOwnLineWidth() == null)
				jrPenTarget.setLineWidth(sr.getLineWidth(jrStylePen, null));
		}
	}

	private static void copyIntheritedLineBox(JRDesignElement target, JRStyle style, StyleResolver sr) {
		JRLineBox styleBox = style.getLineBox();
		if (styleBox != null && target instanceof JRBoxContainer) {
			JRLineBox jrTargetBox = ((JRBoxContainer) target).getLineBox();
			if (jrTargetBox.getOwnPadding() == null)
				jrTargetBox.setPadding(getPadding(styleBox, sr));
			if (jrTargetBox.getOwnTopPadding() == null)
				jrTargetBox.setTopPadding(getTopPadding(styleBox, sr));
			if (jrTargetBox.getOwnBottomPadding() == null)
				jrTargetBox.setBottomPadding(getBottomPadding(styleBox, sr));
			if (jrTargetBox.getOwnLeftPadding() == null)
				jrTargetBox.setLeftPadding(getLeftPadding(styleBox, sr));
			if (jrTargetBox.getOwnRightPadding() == null)
				jrTargetBox.setRightPadding(getRightPadding(styleBox, sr));

			inheritLinePenProeprties(jrTargetBox.getPen(), styleBox.getPen(), sr);
			inheritLinePenProeprties(jrTargetBox.getLeftPen(), styleBox.getLeftPen(), sr);
			inheritLinePenProeprties(jrTargetBox.getRightPen(), styleBox.getRightPen(), sr);
			inheritLinePenProeprties(jrTargetBox.getTopPen(), styleBox.getTopPen(), sr);
			inheritLinePenProeprties(jrTargetBox.getBottomPen(), styleBox.getBottomPen(), sr);
		}
	}

	private static void copyInheritedTextualAttributes(JRDesignElement target, JRStyle style, StyleResolver sr) {
		JRDesignTextElement jrTarget = (JRDesignTextElement) target;

		if (jrTarget.getOwnHorizontalTextAlign() == null)
			jrTarget.setHorizontalTextAlign(style.getHorizontalTextAlign());
		if (jrTarget.getOwnVerticalTextAlign() == null)
			jrTarget.setVerticalTextAlign(style.getVerticalTextAlign());
		if (jrTarget.getOwnMarkup() == null)
			jrTarget.setMarkup(getMarkup(style, sr));
		if (jrTarget.getOwnRotationValue() == null)
			jrTarget.setRotation(style.getRotationValue());

		if (jrTarget.isOwnBold() == null)
			jrTarget.setBold(style.isBold());
		if (jrTarget.isOwnItalic() == null)
			jrTarget.setItalic(style.isItalic());
		if (jrTarget.isOwnUnderline() == null)
			jrTarget.setUnderline(style.isUnderline());
		if (jrTarget.isOwnStrikeThrough() == null)
			jrTarget.setStrikeThrough(style.isStrikeThrough());
		if (jrTarget.isOwnPdfEmbedded() == null)
			jrTarget.setPdfEmbedded(style.isPdfEmbedded());
		if (jrTarget.getOwnFontName() == null)
			jrTarget.setFontName(getFontName(style, sr));
		if (jrTarget.getOwnFontsize() == null)
			jrTarget.setFontSize(style.getFontsize());
		if (jrTarget.getOwnPdfFontName() == null)
			jrTarget.setPdfFontName(getPdfFontName(style, sr));
		if (jrTarget.getOwnPdfEncoding() == null)
			jrTarget.setPdfEncoding(getPdfEncoding(style, sr));

		JRBaseParagraph jrTargetParagraph = (JRBaseParagraph) jrTarget.getParagraph();
		JRBaseParagraph jrStyleParagraph = (JRBaseParagraph) style.getParagraph();
		if (jrTargetParagraph != null && jrStyleParagraph != null) {
			if (jrTargetParagraph.getOwnLineSpacing() == null)
				jrTargetParagraph.setLineSpacing(jrStyleParagraph.getLineSpacing());
			if (jrTargetParagraph.getOwnLineSpacingSize() == null)
				jrTargetParagraph.setLineSpacingSize(jrStyleParagraph.getLineSpacingSize());
			if (jrTargetParagraph.getOwnFirstLineIndent() == null)
				jrTargetParagraph.setFirstLineIndent(jrStyleParagraph.getFirstLineIndent());
			if (jrTargetParagraph.getOwnLeftIndent() == null)
				jrTargetParagraph.setLeftIndent(jrStyleParagraph.getLeftIndent());
			if (jrTargetParagraph.getOwnRightIndent() == null)
				jrTargetParagraph.setRightIndent(jrStyleParagraph.getRightIndent());
			if (jrTargetParagraph.getOwnSpacingAfter() == null)
				jrTargetParagraph.setSpacingAfter(jrStyleParagraph.getSpacingAfter());
			if (jrTargetParagraph.getOwnSpacingBefore() == null)
				jrTargetParagraph.setSpacingBefore(jrStyleParagraph.getSpacingBefore());
			if (jrTargetParagraph.getOwnTabStopWidth() == null)
				jrTargetParagraph.setTabStopWidth(jrStyleParagraph.getTabStopWidth());
		}
	}

	private static void copyInheritedTextFieldAttributes(JRDesignElement target, JRStyle style) {
		JRDesignTextField jrTarget = (JRDesignTextField) target;

		if (jrTarget.isOwnBlankWhenNull() == null)
			jrTarget.setBlankWhenNull(style.isBlankWhenNull());
		if (jrTarget.getOwnPattern() == null)
			jrTarget.setPattern(style.getPattern());
	}

	private static void copyInheritedImageAttributes(JRDesignElement target, JRStyle style) {
		JRDesignImage jrTarget = (JRDesignImage) target;
		if (jrTarget.getOwnFillValue() == null)
			jrTarget.setFill(style.getFillValue());
		if (jrTarget.getOwnScaleImageValue() == null)
			jrTarget.setScaleImage(style.getScaleImageValue());
		if (jrTarget.getOwnHorizontalImageAlign() == null)
			jrTarget.setHorizontalImageAlign(style.getHorizontalImageAlign());
		if (jrTarget.getOwnVerticalImageAlign() == null)
			jrTarget.setVerticalImageAlign(style.getVerticalImageAlign());
	}

	/**
	 * When an element is imported this allow to copy inside the element the attribute of its style, if any. But this
	 * dosent copy inside the element the default properties
	 */
	public static void copyInheritedAttributes(MGraphicElement sourceModel, JRDesignElement jrTarget) {
		JRDesignElement jrSource = sourceModel.getValue();
		StyleResolver sr = new StyleResolver(sourceModel.getJasperConfiguration());
		JRStyle style = sr.getBaseStyle(jrSource);
		if (style != null) {
			if (jrSource.getOwnBackcolor() == null)
				jrTarget.setBackcolor(style.getBackcolor());
			if (jrSource.getOwnForecolor() == null)
				jrTarget.setForecolor(style.getForecolor());
			if (jrSource.getOwnModeValue() == null)
				jrTarget.setMode(style.getModeValue());

			if (sourceModel instanceof MGraphicElementLineBox) {
				copyIntheritedLineBox(jrTarget, style, sr);
			}

			if (sourceModel instanceof MGraphicElementLinePen) {
				JRDesignGraphicElement jrTargetPenContainer = (JRDesignGraphicElement) jrTarget;
				inheritLinePenProeprties(jrTargetPenContainer.getLinePen(), style.getLinePen(), sr);
			}

			if (sourceModel instanceof MTextElement) {
				copyInheritedTextualAttributes(jrTarget, style, sr);
			}

			if (sourceModel instanceof MTextField) {
				copyInheritedTextFieldAttributes(jrTarget, style);
			}

			if (sourceModel instanceof MImage) {
				copyInheritedImageAttributes(jrTarget, style);
			}

			if (sourceModel instanceof MEllipse) {
				JREllipse jrEllipse = (JREllipse) jrTarget;
				if (jrEllipse.getOwnFillValue() == null)
					jrEllipse.setFill(style.getFillValue());
			}

			if (sourceModel instanceof MLine) {
				JRLine jrLine = (JRLine) jrTarget;
				if (jrLine.getOwnFillValue() == null)
					jrLine.setFill(style.getFillValue());
			}

			if (sourceModel instanceof MRectangle) {
				JRRectangle jrRectangle = (JRRectangle) jrTarget;
				if (jrRectangle.getOwnFillValue() == null)
					jrRectangle.setFill(style.getFillValue());
				if (jrRectangle.getOwnRadius() == null)
					jrRectangle.setRadius(style.getRadius());
			}
		}
	}
}
