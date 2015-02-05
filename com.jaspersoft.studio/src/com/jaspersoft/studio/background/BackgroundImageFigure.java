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
package com.jaspersoft.studio.background;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.OrderedLayout;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;

/**
 * Figure used to show the background image
 * 
 * @author Orlandin Marco
 *
 */
public class BackgroundImageFigure extends RectangleFigure {

		/**
		 * Flag used to know if in the current editor the background image should be shown if available
		 */
		private boolean showBackgroundImage = true;

		/**
		 * The model from where the figure is created
		 */
		private MBackgrounImage model;
		
		/**
		 * The image file to display, it is cached to avoid the reloading
		 */
		private BufferedImage image = null;
		
		/**
		 * To avoid to reascle the source image on each repaint keep 
		 * an already scaled version that fit the image
		 */
		private Image scaledImage = null;
		
		/**
		 * The path of the last displayed image
		 */
		private String lastPath = null;
		
		/**
		 * Create the image and if it is available load the image resource
		 * from the file system
		 * 
		 * @param model model with the informations to create the image
		 */
		public BackgroundImageFigure(MBackgrounImage model){
			super();
			this.model = model;
			setOutline(false);
			ToolbarLayout layout = new ToolbarLayout(false);
			layout.setMinorAlignment(OrderedLayout.ALIGN_CENTER);
			layout.setSpacing(5);
			setLayoutManager(layout);
			setBackgroundColor(ResourceManager.getColor(255, 255, 255));
			loadImage();	
		}
	
		/**
		 * Load the image on the filesystem and cache it. It is loaded
		 * if a path is available and valid inside the model and if the 
		 * image cached has a different path. 
		 */
		private void loadImage()
		{
			String path = (String)model.getPropertyValue(MBackgrounImage.PROPERTY_PATH);
			if (path == null){
				image = null;
				scaledImage = null;
			} else if (!path.equals(lastPath)){
				BufferedImage img = null;
				scaledImage = null;
				try {
				  img = ImageIO.read(new File(path));
				  lastPath = path;
				} catch (IOException e) {
				}
				this.image = img;
			}
		}
		
		/**
		 * Update the size of the figure accordingly to the ones provided
		 * by the model
		 */
		private void updateSize(){
			setSize(model.getDefaultWidth(), model.getDefaultHeight());
			setLocation(new Point(model.getDefaultX(), model.getDefaultY()));
		}
		
		@Override
		public void paint(Graphics graphics) {
			loadImage();
			updateSize();
			Graphics2D graphics2d = ComponentFigure.getG2D(graphics);
			if (image != null)
			{
				Composite oldComposite = graphics2d.getComposite();
				float alpha = model.getAlpha();
				int type = AlphaComposite.SRC_OVER; 
				AlphaComposite composite = AlphaComposite.getInstance(type, alpha);
				graphics2d.setComposite( composite );
				int scaledWidth;
				int scaledHeight;
				//Calculate the image size
				if (model.isKeepRatio()) {
					Point ratioSize = calculateWidestRation();
					scaledHeight = ratioSize.y;
					scaledWidth = ratioSize.x;
				} else {
					scaledWidth = getSize().width;
					scaledHeight = getSize().height;
				}
				//Recalculate the scaled image if necessary
				if (scaledImage == null || scaledImage.getWidth(null) != scaledWidth || scaledImage.getHeight(null) != scaledHeight){
					scaledImage = resizeImageWithHint(image, scaledWidth, scaledHeight);
				}
				graphics2d.drawImage(scaledImage, getLocation().x, getLocation().y, null);
				graphics2d.setComposite(oldComposite);
			}			
		}
		
		/**
		 * Used when the image is painted in the figure keeping its aspect
		 * ratio. calculate the best width and height that can fit most the
		 * figure without loosing the aspect ratio
		 * 
		 * @return the width and height of the image keeping the ratio and 
		 * filling most of the surface of the figure
		 */
		private Point calculateWidestRation(){
			int originalWidth = image.getWidth(null);
			int originalHeight = image.getHeight(null);
			int maxWidth = getSize().width;
			int maxHeight = getSize().height;
			int newWidth1  = Math.round(((float)(originalWidth*maxHeight))/((float)originalHeight));
			int newHeight1 = maxHeight;
			int newHeight2 = Math.round(((float)originalHeight*maxWidth)/((float)originalWidth));
			int newWidth2 = maxWidth;
			
			if (newWidth1 > maxWidth) return new Point(newWidth2, newHeight2);
			else if (newHeight2 > maxHeight) return new Point(newWidth1, newHeight1);
			else {
				if ((newWidth1*newHeight1) > (newWidth2*newHeight2)){
					return new Point(newWidth1, newHeight1);
				} else {
					return new Point(newWidth2, newHeight2);
				}
			}
		}
		
		/**
		 * The image is visible if there is an available resource and
		 * if the editor has the show background option enabled
		 */
		@Override
		public boolean isVisible() {
			loadImage();
			return image != null && showBackgroundImage;
		}
		
		/**
		 * Update the model of the image and discard the cache
		 * 
		 * @param model the model
		 */
		protected void setModel(MBackgrounImage model){
			lastPath = null;
			image = null;
			this.model = model;
		}
		
		/**
		 * Check if the image resource is available, if was not loaded
		 * yet it try to load it
		 * 
		 * @return true if there is an image to display, false otherwise
		 */
		public boolean hasImage(){
			loadImage();
			return image != null;
		}
		
		/**
		 * Check if in the current editor the background image is visible. However it will be shown only if there is a
		 * background image defined
		 * 
		 * @return true if the background image is visible, false otherwise
		 */
		public boolean isBackgroundImageVisible() {
			return showBackgroundImage;
		}

		/**
		 * Set in the current editor the visibility for the background image. However it will be shown only if there is a
		 * background image defined
		 * 
		 * @param value
		 *          true if the background should be visible, false otherwise
		 */
		public void setBackgroundImageVisible(boolean value) {
			this.showBackgroundImage = value;
		}
		
		/**
		 * Scale the source image to a specific width and height
		 * 
		 * @param originalImage the original image, must be not null
		 * @param imageNewWidthm the new width
		 * @param imageNewHeight the new height
		 * @return a new image, that is the scaled version of the original one
		 */
    private BufferedImage resizeImageWithHint(BufferedImage originalImage, int imageNewWidthm, int imageNewHeight){
			BufferedImage resizedImage = new BufferedImage(imageNewWidthm, imageNewHeight, originalImage.getType());
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(originalImage, 0, 0, imageNewWidthm, imageNewHeight, null);
			g.dispose();	
			g.setComposite(AlphaComposite.Src);
		 
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
			RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.setRenderingHint(RenderingHints.KEY_RENDERING,
			RenderingHints.VALUE_RENDER_QUALITY);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
			return resizedImage;
    }	
}
