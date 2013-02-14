package com.jaspersoft.studio.editor.action.border;

import net.sf.jasperreports.engine.type.LineStyleEnum;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.wb.swt.ResourceManager;


public class TemplateBorder{
	
		private Float lineWidth;
		
		private LineStyleEnum lineStyle;
		
		private Color color;
		
		private static int width;
		
		public TemplateBorder(Float lineWidth, LineStyleEnum lineStyle, RGB lineColor){
			this.lineStyle = lineStyle;
			this.lineWidth = lineWidth;
			if (lineColor != null) this.color =  new Color(null, lineColor);
			else this.color = ColorConstants.black;
		}
		
		public TemplateBorder(Float lineWidth, LineStyleEnum lineStyle){
			this(lineWidth, lineStyle, ColorConstants.black.getRGB());
		}
		
		public RGB getColor(){
			return color.getRGB();
		}
		
		public LineStyleEnum getStyle(){
			return lineStyle;
		}
		
		public Float getLineWidth(){
			return lineWidth;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof TemplateBorder){
				TemplateBorder other = (TemplateBorder) obj;
				if ((getLineWidth() == null && other.getLineWidth() == null) || (getLineWidth() <=0  && other.getLineWidth() <=0)) return true;
				boolean colorEquals = (color == null && other.getColor() == null) || (color != null && getColor().equals(other.getColor()));
				boolean widthEquals = (getLineWidth() == null && other.getLineWidth() == null) || (getLineWidth() != null && getLineWidth().equals(other.getLineWidth()));
				return (widthEquals && getStyle() == other.getStyle() && colorEquals);
			}
			return false;
		}
		
		private Image getNoBordersImage(){
			String key = "linePreset_noBorders";
			Image image = ResourceManager.getImage(key);
			if (image == null){
				ImageData data = new ImageData(getWidth(), 15, 1, new PaletteData(new RGB[]{ColorConstants.white.getRGB()}));
				data.transparentPixel = data.getPixel(0, 0);
		    image = new Image(null,data);
				GC graphics = new GC(image);
				//graphics.setTextAntialias(SWT.ON);
				graphics.setFont(ResourceManager.getFont("Time New Roman", 10, SWT.NORMAL));
				graphics.drawString("No Borders", 5, 0);
				graphics.dispose();
				ResourceManager.addImage(key, image);
			}
			return image;
		}
		
		/**
		 * Paint the top border
		 */
		public Image getImage(){
				if (lineWidth == null || lineWidth<=0) return getNoBordersImage();
				String key = "linePreset_"+lineStyle.toString()+lineWidth.toString()+getColor().toString();
				Image image = ResourceManager.getImage(key);
				if (image == null){
					ImageData data = new ImageData(getWidth(), 20, 1, new PaletteData(new RGB[]{ColorConstants.white.getRGB()}));
					data.transparentPixel = data.getPixel(0, 0);
			    image = new Image(null,data); 
					GC graphics = new GC(image);
					int width = image.getBounds().width-5;
					switch(lineStyle){
						case DASHED:
							graphics.setLineStyle(SWT.LINE_DASH);
							break;
						case DOTTED:
							graphics.setLineStyle(SWT.LINE_DOT);
							break;
						case SOLID:
							graphics.setLineStyle(SWT.LINE_SOLID);
							break;
						default:
							graphics.setLineStyle(SWT.LINE_SOLID);
							
					}
					graphics.setForeground(color);
					graphics.setLineWidth(Math.round(lineWidth));
					if (lineStyle == LineStyleEnum.DOUBLE)
					{
						int startX = 5;
						Float imageHeight = new Float(image.getBounds().height);
						int startY1 = Math.round((imageHeight/3) - (lineWidth/2) + lineWidth/4);
						int startY2 = Math.round((imageHeight/3)*2 - (lineWidth/2) + lineWidth/4);
						graphics.drawLine(startX, startY1, width, startY1);
						graphics.drawLine(startX, startY2, width, startY2);
					}
					else
					{
						int startX = 5;
						Float imageHeight = new Float(image.getBounds().height);
						int startY = Math.round((imageHeight/ 2) - (lineWidth/2));
						graphics.drawLine(startX, startY, width, startY);
					}
					graphics.dispose();
					ResourceManager.addImage(key, image);
				}
				return image;
		}	
		
		public static Image getCustomImage()
		{
				String key = "linePreset_custom";
				Image image = ResourceManager.getImage(key);
				if (image == null){
					ImageData data = new ImageData(getWidth(), 15, 1, new PaletteData(new RGB[]{ColorConstants.white.getRGB()}));
					data.transparentPixel = data.getPixel(0, 0);
			    image = new Image(null,data);
					GC graphics = new GC(image);
					graphics.setFont(ResourceManager.getFont("Time New Roman", 10, SWT.NORMAL));
					//graphics.setTextAntialias(SWT.ON);
					graphics.drawString("Custom", 5, 0);
					graphics.dispose();
					ResourceManager.addImage(key, image);
				}
				return image;
		}	
		
		
		public static void setWidth(int newWidth){
			if (newWidth>0) width = newWidth;
		}
		
		public static int getWidth(){
			if (width <= 0) width = 70;
			return width;
		}
}