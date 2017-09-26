/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.OrderedLayout;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.ui.progress.WorkbenchJob;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.gef.parts.JSSScalableFreeformRootEditPart;

/**
 * Figure used to show the background image
 * 
 * @author Orlandin Marco
 *
 */
public class BackgroundImageFigure extends RectangleFigure {

		/**
		 * Maximum width for the scaled images, this can be decreased dynamically
		 * if it require to much memory
		 */
		protected static int MAXIMUM_WIDTH = 8000;
		
		/**
		 * Maximum height for the scaled images, this can be decreased dynamically
		 * if it require to much memory
		 */
		protected static int MAXIMUM_HEIGHT = 8000;
	
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
		 * To avoid to resacle the source image on each repaint keep 
		 * a map of the already scaled images, the key is calculated with the get key method
		 */
		private Map<String, Image> scaledImages = new HashMap<String, Image>();
		
		/**
		 * The path of the last displayed image
		 */
		private String lastPath = null;
		
		/**
		 * Zoom manager of the current viewer
		 */
		private ZoomManager zoomManager;
		
		/**
		 * When a background image need to be calculate an update job is run. 
		 * This update job is delayed and cancelled if another update job is requested before it start
		 */
		private UpdatePanelJob updatePanelJob = null;
		
		/**
		 * The delay of the update job
		 */
		private static final int UPDATE_DELAY=500;
		
		/**
		 * Job to update to recalculate the background image and that will
		 * request the repaint of the figure after it is calculated
		 */
		private class UpdatePanelJob extends WorkbenchJob {
			
			private int width;
			
			private int height;
			
			public UpdatePanelJob(int width, int height){
				super("Update Scaled Image");
				setSystem(true);
				this.width = width;
				this.height = height;
			}
			
			@Override
			public IStatus runInUIThread(IProgressMonitor monitor) {
				try{
					BufferedImage newImage = resizeImageWithHint(image, width, height);
					synchronized (BackgroundImageFigure.this) {
						if (!monitor.isCanceled()){
							scaledImages.put(getImageKey(width, height), newImage);
						}
					}
				} catch (OutOfMemoryError ex){
					//The requested image is too big, decrease the maximum sizes
					MAXIMUM_WIDTH-=(MAXIMUM_WIDTH/10);
					MAXIMUM_HEIGHT-=(MAXIMUM_HEIGHT/10);
				} finally {
					updatePanelJob = null;
					model.getFigureEditPart().refresh();
				}
				return Status.OK_STATUS;
			}
		}
		
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
			synchronized (BackgroundImageFigure.this) {
				//cancel the old update job if present
				if (updatePanelJob != null){
					updatePanelJob.cancel();
					updatePanelJob = null;
				}
				if (path == null){
					image = null;
					lastPath = null;
					//discard the cache
					scaledImages.clear();
				} else if (!path.equals(lastPath)){
					BufferedImage img = null;
					//discard the cache
					scaledImages.clear();
					try {
					  img = ImageIO.read(new File(path));
					  lastPath = path;
					} catch (IOException e) {
					}
					this.image = img;
				}
			}
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
				
				graphics2d.drawImage(getScaledImage(scaledWidth, scaledHeight), getLocation().x, getLocation().y, scaledWidth, scaledHeight, null);
				graphics2d.setComposite(oldComposite);
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
		
		/**
		 * Return the current level of zoom
		 */
		protected double getZoom(){
			if (zoomManager == null){
				EditPartViewer viewer = model.getFigureEditPart().getViewer();
				zoomManager = (ZoomManager)viewer.getProperty(ZoomManager.class.toString());
				if (zoomManager == null){
					//fallback
					zoomManager = ((JSSScalableFreeformRootEditPart) viewer.getRootEditPart()).getZoomManager();
				}
			}
			return zoomManager != null ? zoomManager.getZoom() : 1d;
		}
		
		/**
		 * Get the image that should be paint, with the specific width and height.
		 * If the image is already in the cache return it, if it is not start the 
		 * image calculation thread and return the original image instead
		 * 
		 * @param width the requested image width 
		 * @param height the requested image height
		 * @return a not null image
		 */
		protected Image getScaledImage(int width, int height){
			Dimension zoomedSize = getZoomedImage(width, height);
			//Recalculate the scaled image if necessary, first check in the cache
			synchronized (BackgroundImageFigure.this) {
				Image scaledImage = scaledImages.get(getImageKey(zoomedSize.width, zoomedSize.height));
				if (scaledImage == null){
					if (updatePanelJob == null){
						//Image missing and no job running, start it
						updatePanelJob = new UpdatePanelJob(zoomedSize.width, zoomedSize.height);
						updatePanelJob.schedule(UPDATE_DELAY);
					} else {
						if (updatePanelJob.width != zoomedSize.width || updatePanelJob.height != zoomedSize.height){
							//Image missing and a job for a different image already running, cancel it and then run a new job
							updatePanelJob.cancel();
							updatePanelJob = new UpdatePanelJob(zoomedSize.width, zoomedSize.height);
							updatePanelJob.schedule(UPDATE_DELAY);
						}
					}
					return image;
				} else {
					//Image already available
					return scaledImage;
				}
			}
		}
		
		/**
		 * Get a key for the and image that need to be stored in the cache
		 * 
		 * @param width the width of the image
		 * @param height the height of the image
		 * @return a not null key calculated as width-height
		 */
		private String getImageKey(int width, int height){
			return String.valueOf(width) + "-" + String.valueOf(height);
		}
		
		/**
		 * Get a size for the image considering also the zoom level in the editor.
		 * The zoom act as multiplier for the width and height. The returned
		 * size it will keep the proportion and will be lesser than the maximum
		 * width and height
		 * 
		 * @param width the image width 
		 * @param height the image height
		 * @return the size for the image multiplied by the zoom
		 */
		protected Dimension getZoomedImage(int width, int height){
			double zoom = getZoom();
			double zoomedWidth = width * zoom;
			double zoomedHeight = height * zoom;
			if (zoomedWidth > MAXIMUM_WIDTH && zoomedHeight > MAXIMUM_HEIGHT){
				if (zoomedWidth > zoomedHeight){
					zoomedHeight = (zoomedHeight*MAXIMUM_WIDTH)/zoomedWidth;
					zoomedWidth = MAXIMUM_WIDTH;
				} else {
					zoomedWidth = (zoomedWidth*MAXIMUM_HEIGHT)/zoomedHeight;
					zoomedHeight = MAXIMUM_HEIGHT;
				}
			}
			while(zoomedWidth > MAXIMUM_WIDTH || zoomedHeight > MAXIMUM_HEIGHT){
				if (zoomedWidth > MAXIMUM_WIDTH){
					zoomedHeight = (zoomedHeight*MAXIMUM_WIDTH)/zoomedWidth;
					zoomedWidth = MAXIMUM_WIDTH;
				} else {
					zoomedWidth = (zoomedWidth*MAXIMUM_HEIGHT)/zoomedHeight;
					zoomedHeight = MAXIMUM_HEIGHT;
				}
			}
			return new Dimension((int)Math.round(zoomedWidth), (int)Math.round(zoomedHeight));
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
		 * @param imageNewWidth the new width
		 * @param imageNewHeight the new height
		 * @return a new image, that is the scaled version of the original one
		 */
    private BufferedImage resizeImageWithHint(BufferedImage originalImage, int imageNewWidth, int imageNewHeight){ 
			int width =  imageNewWidth;
			int height = imageNewHeight;
			BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			//BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
			Graphics2D g = resizedImage.createGraphics();
			
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			g.setRenderingHint(RenderingHints.KEY_RENDERING,
			RenderingHints.VALUE_RENDER_QUALITY);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setComposite(AlphaComposite.Src);
			g.drawImage(originalImage, 0, 0, width, height, null);
			g.dispose();	
			return resizedImage;
    }	
}
