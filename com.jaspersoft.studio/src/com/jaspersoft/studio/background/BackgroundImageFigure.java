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

public class BackgroundImageFigure extends RectangleFigure {

		private MBackgrounImage model;
		
		private Image image = null;
		
		private String lastPath = null;
		
		private ReportContainer currentContainer = null;
		
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
		
		@Override
		public boolean isVisible() {
			loadImage();
			return image != null && isVisibleFlagCheck();
		}
		
		protected void setModel(MBackgrounImage model){
			lastPath = null;
			image = null;
			this.model = model;
		}
		
		public boolean hasImage(){
			loadImage();
			return image != null;
		}
		
		private ReportContainer getCurrentContainer(){
			if (currentContainer == null){
				IEditorPart currentEditor = SelectionHelper.getActiveJRXMLEditor();
				if (currentEditor instanceof JrxmlEditor){
					JrxmlEditor editor = (JrxmlEditor) currentEditor;
					currentContainer =  editor.getReportContainer();
				}
			}
			return currentContainer;
		}
		
		protected boolean isVisibleFlagCheck(){
			ReportContainer container = getCurrentContainer();
			if (container != null) return container.isBackgroundImageVisible();
			else return true;
		}
}
