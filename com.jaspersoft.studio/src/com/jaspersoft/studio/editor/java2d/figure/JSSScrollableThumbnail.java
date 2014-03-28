/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.java2d.figure;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.nio.ByteOrder;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.KeyEvent;
import org.eclipse.draw2d.KeyListener;
import org.eclipse.draw2d.LayoutListener;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.editor.java2d.J2DGraphics;
import com.jaspersoft.studio.editor.java2d.J2DGraphicsSource;
import com.jaspersoft.studio.model.MRoot;

public class JSSScrollableThumbnail extends Figure {

	private class ClickScrollerAndDragTransferrer extends MouseMotionListener.Stub implements MouseListener {
		private boolean dragTransfer;

		public void mouseDoubleClicked(MouseEvent me) {
		}

		public void mouseDragged(MouseEvent me) {
			if (dragTransfer)
				syncher.mouseDragged(me);
		}

		public void mousePressed(MouseEvent me) {
			if (!(JSSScrollableThumbnail.this.getClientArea().contains(me.getLocation())))
				return;
			Dimension selectorCenter = selector.getBounds().getSize().scale(0.5f);
			Point scrollPoint = me.getLocation().getTranslated(getLocation().getNegated()).translate(selectorCenter.negate())
					.scale(1.0f / getViewportScaleX(), 1.0f / getViewportScaleY())
					.translate(viewport.getHorizontalRangeModel().getMinimum(), viewport.getVerticalRangeModel().getMinimum());

			viewport.setViewLocation(scrollPoint);
			syncher.mousePressed(me);
			dragTransfer = true;
		}

		public void mouseReleased(MouseEvent me) {
			syncher.mouseReleased(me);
			dragTransfer = false;
		}
	}

	private class ScrollSynchronizer extends MouseMotionListener.Stub implements MouseListener {
		private Point startLocation;
		private Point viewLocation;

		public void mouseDoubleClicked(MouseEvent me) {
		}

		public void mouseDragged(MouseEvent me) {
			if (startLocation != null) {
				Dimension d = me.getLocation().getDifference(startLocation);
				d.scale(1.0f / getViewportScaleX(), 1.0f / getViewportScaleY());
				viewport.setViewLocation(viewLocation.getTranslated(d));
				me.consume();
			}
		}

		public void mousePressed(MouseEvent me) {
			startLocation = me.getLocation();
			viewLocation = viewport.getViewLocation();
			me.consume();
		}

		public void mouseReleased(MouseEvent me) {
		}
	}

	private class SelectorFigure extends Figure {


		
		public void paintFigure(Graphics g) {
			if (cachedImage == null) 
				cachedImage = getThumbnailImage();
			g.drawImage(cachedImage, 0, 0);
			
			
			//g.setForegroundColor(ColorConstants.menuBackgroundSelected);
			//g.drawRectangle(bounds);
		}
	}
	
	@Override
	protected void paintFigure(Graphics graphics) {
		super.paintFigure(graphics);
		selector.paint(graphics);
	}

	private FigureListener figureListener = new FigureListener() {
		public void figureMoved(IFigure source) {
			reconfigureSelectorBounds();
		}
	};
	
	
	private KeyListener keyListener = new KeyListener.Stub() {
		public void keyPressed(KeyEvent ke) {
			int moveX = viewport.getClientArea().width / 4;
			int moveY = viewport.getClientArea().height / 4;
			if (ke.keycode == SWT.HOME || (isMirrored() ? ke.keycode == SWT.ARROW_RIGHT : ke.keycode == SWT.ARROW_LEFT))
				viewport.setViewLocation(viewport.getViewLocation().translate(-moveX, 0));
			else if (ke.keycode == SWT.END || (isMirrored() ? ke.keycode == SWT.ARROW_LEFT : ke.keycode == SWT.ARROW_RIGHT))
				viewport.setViewLocation(viewport.getViewLocation().translate(moveX, 0));
			else if (ke.keycode == SWT.ARROW_UP || ke.keycode == SWT.PAGE_UP)
				viewport.setViewLocation(viewport.getViewLocation().translate(0, -moveY));
			else if (ke.keycode == SWT.ARROW_DOWN || ke.keycode == SWT.PAGE_DOWN)
				viewport.setViewLocation(viewport.getViewLocation().translate(0, moveY));
		}
	};

	private PropertyChangeListener propListener = new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent evt) {
			reconfigureSelectorBounds();
		}
	};

	private ScrollSynchronizer syncher;
	private IFigure selector;
	private Viewport viewport;
	private IFigure sourceFigure;
	protected Dimension targetSize = new Dimension(0, 0);
	private Image cachedImage = null; 
	private RGB backgroundColor = null;
	private boolean deactivated = false;
	public Runnable refreshScheduler = new Runnable() {
		
		@Override
		public void run() {
			while(!deactivated){
				if (isNeedRefresh() && isVisible()){
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Display.getDefault().asyncExec(new Runnable() {
						@Override
						public void run() {
							cachedImage = getThumbnailImage();
							repaint();
						}
					});
					setNeedRefresh(false);
				}
			}
		}
	};
	
	protected Image getThumbnailImage() {
		Rectangle e = getSourceRectangle();
		targetSize = getPreferredSize();
		PaletteData palette = new PaletteData(0x0000ff, 0x00ff00,0xff0000);
		ImageData data = new ImageData(e.width, e.height, 24, palette);
		Image fullImage = new Image(Display.getCurrent(), data);
		GC fullImageGC = new GC(fullImage);
		J2DGraphicsSource gs = new J2DGraphicsSource(fullImageGC);
		
		// Create a J2DGraphics with the reight size..
		Graphics graphics = gs.getGraphics(e); 
		if (backgroundColor == null){
			backgroundColor = getBackgroundColor().getRGB();
		}
		((J2DGraphics)graphics).getGraphics2D().setColor(new java.awt.Color(backgroundColor.red, backgroundColor.green, backgroundColor.blue));
		((J2DGraphics)graphics).getGraphics2D().fillRect(0,0,e.width, e.height);
		sourceFigure.paint(graphics);
		gs.flushGraphics(e);
		
		int height = (targetSize.width * e.height)/e.width;
		
		int width = -1;
		
		if (height > targetSize.height){
			height = targetSize.height;
			width = (targetSize.height * e.width)/e.height;
		} else {
			width = targetSize.width;
		}
		
		palette = new PaletteData(0x0000ff, 0x00ff00,0xff0000);
		ImageData resizedImageData = new ImageData(width,height,24,palette);
		
		Image resizedImage = new Image(Display.getCurrent(), resizedImageData);
		GC resizedImageGC = new GC(resizedImage);
		ImageData fixedImage;
		try {
			resizedImageGC.setAntialias(SWT.ON);
			resizedImageGC.setInterpolation(SWT.HIGH);
			resizedImageGC.drawImage(fullImage, 0, 0, fullImage.getBounds().width, fullImage.getBounds().height, 0, 0, width, height);
			resizedImageData = resizedImage.getImageData();
			
			fixedImage = fixColor(resizedImage.getImageData());
		} finally {
			resizedImageGC.dispose();
			resizedImage.dispose();
			fullImageGC.dispose();
			fullImage.dispose();
		}
		return new Image(Display.getCurrent(), fixedImage);
	}
	
	private ImageData fixColor(ImageData data){
		if (java.nio.ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN){
			for(int i = 0 ; i<data.width; i++){
				for (int j=0; j<data.height; j++){
					int pixel = data.getPixel(i, j);
					int newPixel = (pixel >> 16) + (pixel << 16) + (pixel & 0x00FF00); 
					data.setPixel(i,j, newPixel);
				}
			}
		}
		return data;
	}


	/**
	 * Creates a new ScrollableThumbnail that synchs with the given Viewport.
	 * 
	 * @param port
	 *          The Viewport
	 */
	public JSSScrollableThumbnail(Viewport port, MRoot rootNode) {
		super();
		setViewport(port);
		initialize();
		addLayoutListener(new LayoutListener() {
			
			@Override
			public void setConstraint(IFigure child, Object constraint) {}
			
			@Override
			public void remove(IFigure child) {}
			
			@Override
			public void postLayout(IFigure container) {
				setNeedRefresh(true);
			}
			
			@Override
			public boolean layout(IFigure container) {
				return false;
			}
			
			@Override
			public void invalidate(IFigure container) {}
		});
		this.rootNode = rootNode;
		new Thread(refreshScheduler).start();
	}

	/**
	 * @see Thumbnail#deactivate()
	 */
	public void deactivate() {
		unhookViewport();
		unhookSelector();
		deactivated = true;
	}

	private double getViewportScaleX() {
		return (double) targetSize.width / viewport.getContents().getBounds().width;
	}

	private double getViewportScaleY() {
		return (double) targetSize.height / viewport.getContents().getBounds().height;
	}

	private void hookSelector() {
		selector.addMouseListener(syncher = new ScrollSynchronizer());
		selector.addMouseMotionListener(syncher);
		selector.addKeyListener(keyListener);
		add(selector);
	}

	private void hookViewport() {
		viewport.addPropertyChangeListener(Viewport.PROPERTY_VIEW_LOCATION, propListener);
		viewport.addFigureListener(figureListener);
	}

	private void initialize() {
		selector = new SelectorFigure();
		selector.setFocusTraversable(true);
		hookSelector();
		ClickScrollerAndDragTransferrer transferrer = new ClickScrollerAndDragTransferrer();
		addMouseListener(transferrer);
		addMouseMotionListener(transferrer);
	}

	private void reconfigureSelectorBounds() {
		Rectangle rect = new Rectangle();
		Point offset = viewport.getViewLocation();
		offset.x -= viewport.getHorizontalRangeModel().getMinimum();
		offset.y -= viewport.getVerticalRangeModel().getMinimum();
		rect.setLocation(offset);
		rect.setSize(viewport.getClientArea().getSize());
		rect.scale(getViewportScaleX(), getViewportScaleY());
		rect.translate(getClientArea().getLocation());
		selector.setBounds(rect);
	}



	/**
	 * Sets the Viewport that this ScrollableThumbnail will synch with.
	 * 
	 * @param port
	 *          The Viewport
	 */
	public void setViewport(Viewport port) {
		viewport = port;
		hookViewport();
	}

	private void unhookSelector() {
		selector.removeKeyListener(keyListener);
		selector.removeMouseMotionListener(syncher);
		selector.removeMouseListener(syncher);
		remove(selector);
	}

	private void unhookViewport() {
		viewport.removePropertyChangeListener(Viewport.PROPERTY_VIEW_LOCATION, propListener);
		viewport.removeFigureListener(figureListener);
	}
	
	/**
	 * Returns the rectangular region relative to the source figure which will be the basis of the thumbnail. The value
	 * may be returned by reference and should not be modified by the caller.
	 * 
	 * @since 3.1
	 * @return the region of the source figure being used for the thumbnail
	 */
	protected Rectangle getSourceRectangle() {
		return sourceFigure.getBounds();
	}

	/**
	 * Sets the source Figure. Also sets the scales and creates the necessary update manager.
	 * 
	 * @param fig
	 *          The source figure
	 */
	public void setSource(IFigure fig) {
		if (sourceFigure == fig)
			return;
		sourceFigure = fig;
		if (sourceFigure != null) {
			repaint();
		}
		if (rootNode != null){
			rootNode.getChildren().get(0).getPropertyChangeSupport().removePropertyChangeListener(listener);
			rootNode.getChildren().get(0).getPropertyChangeSupport().addPropertyChangeListener(listener);
		}
	}
	
	private boolean needRefresh = false;
	private MRoot rootNode = null;
	private PropertyChangeListener listener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			setNeedRefresh(true);
		}
	};
	
	private boolean isNeedRefresh(){
		synchronized (this) {
			return needRefresh;
		}
	}
	
	private void setNeedRefresh(boolean value){
		synchronized (this) {
			needRefresh = value;
		}
	}
	

}
