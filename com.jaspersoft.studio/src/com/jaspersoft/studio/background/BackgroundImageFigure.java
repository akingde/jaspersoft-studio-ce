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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.OrderedLayout;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.ui.IEditorPart;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.report.ReportContainer;
import com.jaspersoft.studio.utils.SelectionHelper;

/**
 * Figure used to show the background image
 * 
 * @author Orlandin Marco
 *
 */
public class BackgroundImageFigure extends RectangleFigure {

		/**
		 * The model from where the figure is created
		 */
		private MBackgrounImage model;
		
		/**
		 * The image file to display, it is cached to avoid the reloading
		 */
		private Image image = null;
		
		/**
		 * The path of the last displayed image
		 */
		private String lastPath = null;
		
		/**
		 * Report container used to check if the image is in edit mode
		 */
		private JrxmlEditor currentEditor = null;
		
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
			} else if (!path.equals(lastPath)){
				BufferedImage img = null;
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
				if (model.isKeepRatio()) {
					Point ratioSize = calculateWidestRation();
					graphics2d.drawImage(image, getLocation().x, getLocation().y, ratioSize.x, ratioSize.y, null);
				} else {
					graphics2d.drawImage(image, getLocation().x, getLocation().y, getSize().width, getSize().height, null);
				}
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
			return image != null && isVisibleFlagCheck();
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
		 * Get the current report container and cache if for the next calls
		 * 
		 * @return a report container or null if it isn't available
		 */
		private ReportContainer getCurrentContainer(){
			if (currentEditor == null){
				IEditorPart editor = SelectionHelper.getActiveJRXMLEditor();
				if (editor instanceof JrxmlEditor){
					currentEditor = (JrxmlEditor) editor;
				}
			}
			return currentEditor != null ? currentEditor.getReportContainer() : null;
		}
		
		/**
		 * Check if the editor has the show background option enabled
		 * 
		 * @return true if the option is enabled, false otherwise
		 */
		protected boolean isVisibleFlagCheck(){
			ReportContainer container = getCurrentContainer();
			if (container != null) return container.isBackgroundImageVisible();
			else return true;
		}
}
