/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.java2d;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

import com.jaspersoft.studio.editor.gef.figures.ACachedGraphics;

/**
 * Graphics used to speedup the painting of the element. All the painting operation are not 
 * done on the target graphics but on an BufferedImage of the same size.
 * When the content need to be transferred on the target graphics the only thing to do is
 * to draw the image on it, without execute the operations.
 * 
 * @author Orlandin Marco
 *
 */
public class ImageGraphics2D extends ACachedGraphics {

	/**
	 * Buffered image that act as a cache for the result of the painting operations
	 */
	private BufferedImage image;
	
	/**
	 * Graphics of the buffered image
	 */
	private Graphics2D imageGraphics;
	
	/**
	 * Graphic where the buffered image can be painted.
	 */
	private Graphics2D targetGraphics;
	
	/**
	 * Create the graphic 2d
	 * 
	 * @param originalGrpahics graphic where the buffered image will be painted. It will give also
	 * the size to the buffered image. Must be not null
	 */
	public ImageGraphics2D(Graphics2D originalGrpahics) {
		this.targetGraphics = originalGrpahics;
		
		//scale the graphics to the current zoom level and set the rendering hints
		double scaleX = originalGrpahics.getTransform().getScaleX();
		double scaleY = originalGrpahics.getTransform().getScaleY();
		//we render to a resolution bigger than the image bounds and then scale to that size when a zoom bigger than 1 is used. This allow a better overall quality when using zoom
		//but the size of the rendered element must be always at lest equal to the size of the image where it will painted. Otherwise will be generated only a portion of what should
		image = new BufferedImage( (int)(targetGraphics.getClipBounds().width*Math.max(1,scaleX)), (int)(originalGrpahics.getClipBounds().height*Math.max(1, scaleY)), BufferedImage.TYPE_INT_ARGB);
		image.getWidth();
		image.getHeight();
		imageGraphics = (Graphics2D)image.createGraphics();
		imageGraphics.scale(scaleX, scaleY);
		imageGraphics.setRenderingHints(originalGrpahics.getRenderingHints());
		//need to set the clip otherwise the getClip method can return null and some JR Drawer (ie frame) relay on not having a null clip
		imageGraphics.setClip(new Rectangle(0, 0, image.getWidth(), image.getHeight()));
	}
	
	public ImageGraphics2D(Graphics2D originalGrpahics, int width, int height) {
		this.targetGraphics = originalGrpahics;
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		imageGraphics = (Graphics2D)image.createGraphics();
		
		//scale the graphics to the current zoom level and set the rendering hints
		double scaleX = originalGrpahics.getTransform().getScaleX();
		double scaleY = originalGrpahics.getTransform().getScaleY();
		imageGraphics.scale(scaleX, scaleY);
		//need to set the clip otherwise the getClip method can return null and some JR Drawer (ie frame) relay on not having a null clip
		imageGraphics.setClip(new Rectangle(0, 0, image.getWidth(), image.getHeight()));
	}
	
	/**
	 * Paint the content of the buffered image on the target image
	 */
	public void paintCache(){
		double scaleX = targetGraphics.getTransform().getScaleX();
		double scaleY = targetGraphics.getTransform().getScaleY();
		//update the current scale value
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		//scale the figure to the image size
		AffineTransform transform = new AffineTransform();
		transform.scale(1/scaleX, 1/scaleY);
		targetGraphics.drawImage(image, transform , null);
	}
	
	/**
	 * Set the target graphics, where the content of the image will be 
	 * copied calling the {@link #paintCache()} method
	 * 
	 * @param graphics a not null graphics
	 */
	public void setGraphics(Graphics2D graphics){
		targetGraphics = graphics;
	}
	
	//WRAP ALL THE GRAPHICS2D METHOD TO EXECUTE THEM ON THE BUFFERED IMAGE GRAPHICS
	
	@Override
	public void draw(Shape s) {
		imageGraphics.draw(s);
	}

	@Override
	public boolean drawImage(Image img, AffineTransform xform, ImageObserver obs) {
			return imageGraphics.drawImage(img, xform, obs);
	}

	@Override
	public void drawImage(BufferedImage img, BufferedImageOp op, int x, int y) {
		imageGraphics.drawImage(img, op, x, y);
	}

	@Override
	public void drawRenderedImage(RenderedImage img, AffineTransform xform) {
		imageGraphics.drawRenderedImage(img, xform);
	}

	@Override
	public void drawRenderableImage(RenderableImage img, AffineTransform xform) {
		imageGraphics.drawRenderableImage(img, xform);
	}

	@Override
	public void drawString(String str, int x, int y) {
		imageGraphics.drawString(str, x, y);
	}

	@Override
	public void drawString(String str, float x, float y) {
		imageGraphics.drawString(str, x, y);
	}

	@Override
	public void drawString(AttributedCharacterIterator iterator, int x, int y) {
		imageGraphics.drawString(iterator, x, y);
	}

	@Override
	public void drawString(AttributedCharacterIterator iterator, float x, float y) {
		imageGraphics.drawString(iterator, x, y);
	}

	@Override
	public void drawGlyphVector(GlyphVector g, float x, float y) {
		imageGraphics.drawGlyphVector(g, x, y);
	}

	@Override
	public void fill(Shape s) {
		imageGraphics.fill(s);
	}

	@Override
	public boolean hit(Rectangle rect, Shape s, boolean onStroke) {
		return imageGraphics.hit(rect, s, onStroke);
	}

	@Override
	public GraphicsConfiguration getDeviceConfiguration() {
		return imageGraphics.getDeviceConfiguration();
	}

	@Override
	public void setComposite(Composite comp) {
		imageGraphics.setComposite(comp);
	}

	@Override
	public void setPaint(Paint paint) {
		imageGraphics.setPaint(paint);
	}

	@Override
	public void setStroke(Stroke s) {
		imageGraphics.setStroke(s);
	}

	@Override
	public void setRenderingHint(Key hintKey, Object hintValue) {
		imageGraphics.setRenderingHint(hintKey, hintValue);
	}

	@Override
	public Object getRenderingHint(Key hintKey) {
		return imageGraphics.getRenderingHint(hintKey);
	}

	@Override
	public void setRenderingHints(Map<?, ?> hints) {
		imageGraphics.setRenderingHints(hints);
	}

	@Override
	public void addRenderingHints(Map<?, ?> hints) {
		imageGraphics.addRenderingHints(hints);
	}

	@Override
	public RenderingHints getRenderingHints() {
		return imageGraphics.getRenderingHints();
	}

	@Override
	public void translate(int x, int y) {
		imageGraphics.translate(x, y);;
	}

	@Override
	public void translate(double tx, double ty) {
		imageGraphics.translate(tx, ty);
	}

	@Override
	public void rotate(double theta) {
		imageGraphics.rotate(theta);
	}

	@Override
	public void rotate(double theta, double x, double y) {
		imageGraphics.rotate(theta, x, y);
	}

	@Override
	public void scale(double sx, double sy) {
		imageGraphics.scale(sx, sy);
	}

	@Override
	public void shear(double shx, double shy) {
		imageGraphics.shear(shx, shy);
	}

	@Override
	public void transform(AffineTransform Tx) {
		imageGraphics.transform(Tx);
	}

	@Override
	public void setTransform(AffineTransform Tx) {
		imageGraphics.setTransform(Tx);
	}

	@Override
	public AffineTransform getTransform() {
		return imageGraphics.getTransform();
	}

	@Override
	public Paint getPaint() {
		return imageGraphics.getPaint();
	}

	@Override
	public Composite getComposite() {
		return imageGraphics.getComposite();
	}

	@Override
	public void setBackground(Color color) {
		imageGraphics.setBackground(color);
	}

	@Override
	public Color getBackground() {
		return imageGraphics.getBackground();
	}

	@Override
	public Stroke getStroke() {
		return imageGraphics.getStroke();
	}

	@Override
	public void clip(Shape s) {
		imageGraphics.clip(s);
	}

	@Override
	public FontRenderContext getFontRenderContext() {
		return imageGraphics.getFontRenderContext();
	}

	@Override
	public Graphics create() {
		return imageGraphics.create();
	}

	@Override
	public Color getColor() {
		return imageGraphics.getColor();
	}

	@Override
	public void setColor(Color c) {
		imageGraphics.setColor(c);
	}

	@Override
	public void setPaintMode() {
		imageGraphics.setPaintMode();
	}

	@Override
	public void setXORMode(Color c1) {
		imageGraphics.setXORMode(c1);
	}

	@Override
	public Font getFont() {
		return imageGraphics.getFont();
	}

	@Override
	public void setFont(Font font) {
		imageGraphics.setFont(font);
	}

	@Override
	public FontMetrics getFontMetrics(Font f) {
		return imageGraphics.getFontMetrics(f);
	}

	@Override
	public Rectangle getClipBounds() {
		return imageGraphics.getClipBounds();
	}

	@Override
	public void clipRect(int x, int y, int width, int height) {
		imageGraphics.clipRect(x, y, width, height);
	}

	@Override
	public void setClip(int x, int y, int width, int height) {
		imageGraphics.setClip(x, y, width, height);
	}

	@Override
	public Shape getClip() {
		return imageGraphics.getClip();
	}

	@Override
	public void setClip(Shape clip) {
		imageGraphics.setClip(clip);
	}

	@Override
	public void copyArea(int x, int y, int width, int height, int dx, int dy) {
		imageGraphics.copyArea(x, y, width, height, dx, dy);
	}

	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		imageGraphics.drawLine(x1, y1, x2, y2);
	}

	@Override
	public void fillRect(int x, int y, int width, int height) {
		imageGraphics.fillRect(x, y, width, height);
	}

	@Override
	public void clearRect(int x, int y, int width, int height) {
		imageGraphics.clearRect(x, y, width, height);
	}

	@Override
	public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		imageGraphics.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
	}

	@Override
	public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		imageGraphics.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
	}

	@Override
	public void drawOval(int x, int y, int width, int height) {
		imageGraphics.drawOval(x, y, width, height);
	}

	@Override
	public void fillOval(int x, int y, int width, int height) {
		imageGraphics.fillOval(x, y, width, height);
	}

	@Override
	public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		imageGraphics.drawArc(x, y, width, height, startAngle, arcAngle);
	}

	@Override
	public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		imageGraphics.fillArc(x, y, width, height, startAngle, arcAngle);
	}

	@Override
	public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
		imageGraphics.drawPolyline(xPoints, yPoints, nPoints);
	}

	@Override
	public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		imageGraphics.drawPolygon(xPoints, yPoints, nPoints);
	}

	@Override
	public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		imageGraphics.fillPolygon(xPoints, yPoints, nPoints);
	}

	@Override
	public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
		return imageGraphics.drawImage(img, x, y, observer);
	}

	@Override
	public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
		return imageGraphics.drawImage(img, x, y, width, height, observer);
	}

	@Override
	public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
		return imageGraphics.drawImage(img, x, y, bgcolor, observer);
	}

	@Override
	public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
		return imageGraphics.drawImage(img, x, y, width, height, bgcolor, observer);
	}

	@Override
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
			ImageObserver observer) {
		return imageGraphics.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
	}

	@Override
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
			Color bgcolor, ImageObserver observer) {
		return imageGraphics.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer);
	}

	@Override
	public void dispose() {
		imageGraphics.dispose();
	}

}
