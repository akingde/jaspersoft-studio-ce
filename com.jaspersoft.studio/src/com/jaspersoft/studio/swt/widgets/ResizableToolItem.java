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

public class ResizableToolItem extends ToolItem{

		private static HashMap<String, ImageData> iconImagesDataMap = new HashMap<String, ImageData>();
		
		private ResourceCache imagesResource = new ResourceCache();
	
		private IAction associatedAction;
		
		private int suggestedHeight = SWT.DEFAULT;
		
		private PaintListener paintListener = null;
		
		private RGB lastParentBackground = null;
		
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
			updateImage();
		}
		
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
		 * resized version of that image to a fixed height
		 * 
		 * @param action the action, it must have an image
		 * @return an image if the one from the icon was too small or the image
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
	