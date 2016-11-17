/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.swt.widgets;

import java.util.HashMap;

import org.eclipse.jface.action.IAction;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceCache;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.utils.ImageUtils;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * Extension of the toolitems to allow to set their size. Toolitems has no easy way
 * to set the size, they inherit it both from the toolbar or the content, taking the maximum.
 * The biggest toolitem so set the size of the toolbar, all the others adapt to it. On some
 * SWT implementation this cause a stretch of the image . This new toolitem will allow to 
 * set their height using a trick to pad the internal image with the background color of the toolbar
 * 
 * 
 * @author Orlandin Marco
 *
 */
public class ResizableToolItem extends ToolItem{

		/**
		 * Cache map of the padded icons generated. The key is the action id for which they were 
		 * generated plus the background of the toolbar in the moment they were generated
		 */
		private static HashMap<String, ImageData> iconImagesDataMap = new HashMap<String, ImageData>();
		
		/**
		 * Cache map of the swt images created for this toolitem, it is disposed when the toolitem is 
		 * disposed also
		 */
		private ResourceCache imagesResource = new ResourceCache();
	
		/**
		 * The action associated to this toolitem
		 */
		private IAction associatedAction;
		
		/**
		 * The height of this toolitem, SWT.DEFAULT is the original height of the image
		 */
		private int suggestedHeight = SWT.DEFAULT;
		
		/**
		 * Listener on the tollbar repaint. Used to check if the toolbar has
		 * changed background and update the images
		 */
		private PaintListener paintListener = null;
		
		/**
		 * Last background of the toolbar, used to update the images when the toolbar
		 * background changes
		 */
		private RGB lastParentBackground = null;
		
		/**
		 * Create the toolitem 
		 * 
		 * @param parent the parent toolbar
		 * @param style the style, they are the same of the base ToolItem
		 * @param associtatedAction the action associated to this toolitem, must be not null
		 * @param suggestedHeight the height for the icon, SWT.DEFAULT is the original height of the image
		 */
		public ResizableToolItem(ToolBar parent, int style, IAction associtatedAction, int suggestedHeight) {
			super(parent, style);
			this.associatedAction = associtatedAction;
			this.suggestedHeight = suggestedHeight;
			paintListener = new PaintListener(){
				
				public void paintControl(PaintEvent e) {
					updateImage();
				};
			};
			parent.addPaintListener(paintListener);
			//Need to set the image on the creation otherwise on some OS it will not able to display any image
			updateImage();
		}
		
		/**
		 * Update the image inside the toolitem, but only if the background of the toolbar
		 * changed since the last time it was updated
		 */
		private void updateImage(){
			RGB currentParentBackground = getParent().getBackground().getRGB();
			if (!ModelUtils.safeEquals(lastParentBackground, currentParentBackground)){
				lastParentBackground = currentParentBackground;
				ImageData resizedData = getResizedImage(associatedAction);
				setImage(imagesResource.getImage(resizedData));
			}
		}
		
		/**
		 * Must be implemented empty to allow the extension of a standard swt widget
		 */
		@Override
		protected void checkSubclass() {
		}
		
		/**
		 * Get the image from the action and if it is tool small return a 
		 * resized version of that image to a fixed height. The returned value
		 * is an ImageData and first is checked in the cache.
		 * 
		 * @param action the action, it must have an image
		 * @return an ImageData if the one from the icon was too small or the image
		 * of the action itself
		 */
		private ImageData getResizedImage(IAction action){
			RGB currentBackground = getParent().getBackground().getRGB();
			String toolbarBackgroundKey = getParent().getBackground().getRGB().toString();
			String dataKey = action.getId() + toolbarBackgroundKey;
			if (iconImagesDataMap.containsKey(dataKey)){
				return iconImagesDataMap.get(dataKey);
			} else {
				Image loadedImage = ResourceManager.getImage(action.getImageDescriptor());
				ImageData resultData = null;
				if (loadedImage != null){
					//Resize the image if it is too small
					int width = loadedImage.getImageData().width;
					int height = loadedImage.getImageData().height;
					
					if (height < suggestedHeight && suggestedHeight != -1){
						height = suggestedHeight;		
					}
					
					if (width != loadedImage.getImageData().width || height != loadedImage.getImageData().height){
						Image resizedImage = ImageUtils.padImage(loadedImage, width, height, currentBackground);
						resultData = (ImageData)resizedImage.getImageData().clone();
						resizedImage.dispose();
						//store the data
						iconImagesDataMap.put(dataKey, resultData);
					}
				}
				return resultData;
			}
		}
		
		@Override
		public void dispose() {
			getParent().removePaintListener(paintListener);
			super.dispose();
			imagesResource.dispose();
		}
		
	}
	
