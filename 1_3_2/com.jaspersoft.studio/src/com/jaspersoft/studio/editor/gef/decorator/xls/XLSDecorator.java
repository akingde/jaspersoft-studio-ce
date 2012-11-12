package com.jaspersoft.studio.editor.gef.decorator.xls;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.editor.action.xls.XLSAction;
import com.jaspersoft.studio.editor.gef.decorator.IDecorator;
import com.jaspersoft.studio.editor.gef.decorator.text.TextDecoratorInterface;
import com.jaspersoft.studio.editor.gef.decorator.text.TextLocation;
import com.jaspersoft.studio.editor.gef.decorator.text.TextLocation.Location;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.java2d.J2DUtils;

/**
 * Decorator for the XSL action, it also provide an interface to became a text 
 * contributor
 * @author Orlandin Marco
 *
 */
public class XLSDecorator implements IDecorator, TextDecoratorInterface {

	/**
	 * Left upper corner image
	 */
	private static ImageIcon startImageAwt = null;

	/**
	 * right lower corner image
	 */
	private static ImageIcon endImageAwt = null;

	/**
	 * Font of the text
	 */
	private static Font JSS_TEXT_FONT = new Font("SansSerif", 0, 10);

	/**
	 * Color of the text
	 */
	private static Color JSS_TEXT_COLOR = new Color(198, 29, 29);
	
	/**
	 * Pair of id-value used by the decorator
	 */
	String[] tags;


	/**
	 * Constructor, load the images if the weren't loaded before
	 */
	public XLSDecorator() {
		if (startImageAwt == null || endImageAwt == null) {
			startImageAwt = new javax.swing.ImageIcon(XLSDecorator.class.getResource("/icons/resources/corner1.png"));
			endImageAwt = new javax.swing.ImageIcon(XLSDecorator.class.getResource("/icons/resources/corner2.png"));
		}
		tags = new String[] { XLSAction.FIT_COL_ID, "FitCol", XLSAction.FIT_ROW_ID, "FitRow", XLSAction.AUTOFILTER_ID,
				"AutoFilter", XLSAction.BREAK_AFTER_ROW_ID, "BreakAfter", XLSAction.BREAK_BEFORE_ROW_ID, "BreakBefore",
				XLSAction.CELL_HIDDEN_ID, "Hidden", XLSAction.CELL_LOCKED_ID, "Locked" };
	}

	/**
	 * Print on the element it's selected xsl tags
	 */
	@Override
	public void paint(Graphics graphics, ComponentFigure fig) {
		if (fig.getJrElement() instanceof JRDesignElement) {

			Rectangle r = fig.getBounds();
			Graphics2D g = ComponentFigure.getG2D(graphics);
			if (g != null) {
				Stroke oldStroke = g.getStroke();
				g.setStroke(J2DUtils.getInvertedZoomedStroke(oldStroke, graphics.getAbsoluteScale()));

				String tagValue = "";
				String startString = "";
				String fullString = "";
				String endString = "";

				boolean drawstart = false;
				boolean drawend = false;

				JRPropertiesMap v = fig.getJrElement().getPropertiesMap();
				for (int i = 0; i < tags.length; i += 2) {
					String prop = tags[i];
					String label = tags[i + 1];
					tagValue = v.getProperty(prop);
					if (tagValue != null) {
						if (tagValue.equals("true")) {
							drawstart = true;
							fullString += label + " ";
						} else if (tagValue.equals("start")) {
							drawstart = true;
							startString += label + " ";
						} else if (tagValue.equals("end")) {
							drawend = true;
							endString = label + " " + endString;
						}
					}

				}
				if (drawstart)
					drawStart(g, r);
				if (drawend)
					drawEnd(g, r);

				Font f = g.getFont();

				Color color = g.getColor();

				startString = startString.trim();
				endString = endString.trim();
				fullString = fullString.trim();

				g.setFont(JSS_TEXT_FONT);
				g.setColor(JSS_TEXT_COLOR);

				if (startString.length() > 0) {
					g.drawString(startString, r.x + 4, r.y + 11);
				}

				if (endString.length() > 0) {
					int strWidth = g.getFontMetrics().stringWidth(endString);
					g.drawString(endString, r.x + r.width - strWidth - 6, r.y + r.height - 6);
				}

				if (fullString.length() > 0) {
					int strWidth = 0;
					if (startString.length() > 0)
						strWidth = g.getFontMetrics().stringWidth(startString + " ");
					AttributedString as = new AttributedString(fullString);
					as.addAttribute(TextAttribute.FONT, g.getFont());
					as.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
					g.drawString(as.getIterator(), r.x + 4 + strWidth, r.y + 11);
				}

				g.setFont(f);
				g.setColor(color);
			}
		}
	}

	/**
	 * Set the font of the displayed text
	 * 
	 * @param newFont
	 *          the new font
	 */
	public void setTextFont(Font newFont) {
		JSS_TEXT_FONT = newFont;
	}

	/**
	 * Set the color of the displayed text
	 * 
	 * @param newColor
	 *          the new color
	 */
	public void setTextColor(Color newColor) {
		JSS_TEXT_COLOR = newColor;
	}

	/**
	 * Draw the image on the right lower corner
	 * 
	 * @param gr
	 *          object used to draw the image
	 * @param r
	 *          item where the image will be drawn
	 */
	private void drawEnd(Graphics2D gr, Rectangle r) {
		gr.drawImage(endImageAwt.getImage(), r.x + r.width - endImageAwt.getIconWidth() - 2,
				r.y + r.height - endImageAwt.getIconHeight() - 2, null);
	}

	/**
	 * Draw the image on the left upper corner
	 * 
	 * @param gr
	 *          object used to draw the image
	 * @param r
	 *          item where the image will be drawn
	 */
	private void drawStart(Graphics2D gr, Rectangle r) {
		gr.drawImage(startImageAwt.getImage(), r.x, r.y, null);
	}

	@Override
	public ArrayList<TextLocation> getText(JRPropertiesMap mapProperties) {
		ArrayList<TextLocation> result = new ArrayList<TextLocation>();
		String tagValue = "";
		String startString = "";
		String fullString = "";
		String endString = "";

		for (int i = 0; i < tags.length; i += 2) {
			String prop = tags[i];
			String label = tags[i + 1];
			tagValue = mapProperties.getProperty(prop);
			if (tagValue != null) {
				if (tagValue.equals("true")) {
					endString += label + " ";
				} else if (tagValue.equals("start")) {
					startString += label.concat("Start") + " ";
				} else if (tagValue.equals("end")) {
					endString = label.concat("End") + " " + endString;
				}
			}

		}
	
		startString = startString.trim();
		endString = endString.trim();
		fullString = fullString.trim();

		if (startString.length() > 0) {
			result.add(new TextLocation(Location.TopLeft, startString));
		}

		if (endString.length() > 0) {
			result.add(new TextLocation(Location.BottomRight, endString));
		}

		if (fullString.length() > 0) {
			TextLocation as = new TextLocation(Location.TopLeft, fullString);
			as.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			result.add(as);
		}
		
		return result;
	}

	@Override
	public Color getColor() {
		return JSS_TEXT_COLOR;
	}

	@Override
	public Font getFont() {
		return JSS_TEXT_FONT;
	}

}
