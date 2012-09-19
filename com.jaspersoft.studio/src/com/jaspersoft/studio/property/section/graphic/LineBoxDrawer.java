package com.jaspersoft.studio.property.section.graphic;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.export.draw.BoxDrawer;
import net.sf.jasperreports.engine.export.legacy.BorderOffset;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import net.sf.jasperreports.engine.util.JRPenUtil;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Canvas;

class LineBoxDrawer extends BoxDrawer {
	
	private List<Border> clickablesElements = new ArrayList<Border>();
	
	private Canvas paintingSquare;
	
	private enum Location{LEFT,RIGHT,BOTTOM,TOP};
	
	private static Stroke dashedStroke = null;
	
	private class Border{
		
		private Rectangle rect;
		
		private Location border;
		
		private boolean selected;

		
		public Border(Rectangle rect, Location border){
			this.rect = rect;
			this.border = border;
			selected = false;
		}
		
		public Location getLocation(){
			return border;
		}
		
		public boolean isIntersecting(int x, int y){
			return rect.contains(new Point(x, y));
		}
		
		public void setRectangle(Rectangle rect){
			this.rect = rect;
		}
		
		public boolean getSelected(){
			return selected;
		}
		
		public boolean changeSelected(){
			selected = !selected;
			return selected;
		}
		
		public Rectangle getRect(){
			return rect;
		}
		
		public void setSelected(boolean newValue){
			selected = newValue;
		}
		
	}
	
	public LineBoxDrawer(JasperReportsContext jasperReportsContext, Canvas square) {
		super(jasperReportsContext);
		if (dashedStroke == null){
			dashedStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10f, new float[]{4,4}, 0f);
		}
		paintingSquare = square;
		paintingSquare.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				for(Border bord : clickablesElements){
					if (bord.isIntersecting(e.x, e.y)){
						boolean isSelected = bord.changeSelected();
						paintingSquare.redraw();
					}
				}
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void refresh(){
		paintingSquare.redraw();
	}
	
	public boolean isLeftSelected(){
		Border border = getBorder(Location.LEFT);
		return border != null ? border.getSelected() : false;
	}
	
	public boolean isRightSelected(){
		Border border = getBorder(Location.RIGHT);
		return border != null ? border.getSelected() : false;
	}
	
	public boolean isTopSelected(){
		Border border = getBorder(Location.TOP);
		return border != null ? border.getSelected() : false;
	}
	
	public boolean isBottomSelected(){
		Border border = getBorder(Location.BOTTOM);
		return border != null ? border.getSelected() : false;
	}

	public void drawBox(Graphics2D graphics2d, JRLineBox box, JRPrintElement element) {
		//drawBox(graphics2d, box, element, 0, 0);
		drawLeftPen(graphics2d, box.getTopPen(), box.getLeftPen(), box.getBottomPen(), element, 0, 0);
		drawTopPen(graphics2d, box.getTopPen(), box.getLeftPen(), box.getRightPen(), element, 0, 0);
		drawBottomPen(graphics2d, box.getLeftPen(), box.getBottomPen(), box.getRightPen(), element, 0, 0);
		drawRightPen(graphics2d, box.getTopPen(), box.getBottomPen(), box.getRightPen(), element, 0, 0);
	}
	
	private void removeBorder(Location borderPosition){
		Iterator<Border> it = clickablesElements.iterator();
		boolean notFound = true;
		while(it.hasNext() && notFound){
			if (it.next().equals(borderPosition)){
				notFound = false;
				it.remove();
			}
		}
	}
	
	private Border getBorder(Location borderPosition){
		Iterator<Border> it = clickablesElements.iterator();
		Border result = null;
		while(it.hasNext() && (result == null)){
			Border actBorder = it.next();
			if (actBorder.getLocation().equals(borderPosition)){
				result = actBorder;
			}
		}
		return result;
	}
	
	
	private Border updateBorder(Location borderPosition, Rectangle newSize){
		Iterator<Border> it = clickablesElements.iterator();
		boolean notFound = true;
		Border actualElement = null;
		while(it.hasNext() && notFound){
			actualElement = it.next();
			if (actualElement.getLocation().equals(borderPosition)){
				notFound = false;
				actualElement.setRectangle(newSize);
			}
		}
		if (notFound){
			actualElement = new Border(newSize, borderPosition);
			clickablesElements.add(actualElement);
		}
		return actualElement;
	}


	protected void drawLeftPen(Graphics2D grx, JRPen topPen, JRPen leftPen, JRPen bottomPen, JRPrintElement element, 
																int offsetX, int offsetY)
	{
		Stroke leftStroke = JRPenUtil.getStroke(leftPen, BasicStroke.CAP_BUTT);
		int height = element.getHeight();
		float topOffset = topPen.getLineWidth().floatValue() / 2 - BorderOffset.getOffset(topPen);
		float bottomOffset = bottomPen.getLineWidth().floatValue() / 2 - BorderOffset.getOffset(bottomPen);
		float leftPenWidth = 7.0f;
		LineStyleEnum lineStyle;
		if (leftStroke != null && height > 0)
		{
			leftPenWidth = leftPen.getLineWidth().floatValue();
			lineStyle = leftPen.getLineStyleValue();
			grx.setStroke(leftStroke);
			grx.setColor(leftPen.getLineColor());
		} else {
			grx.setStroke(new BasicStroke(7));
			grx.setColor(new Color(255,255,255,255));
			lineStyle = LineStyleEnum.SOLID;
		}
		AffineTransform oldTx = grx.getTransform();
		if (lineStyle == LineStyleEnum.DOUBLE)
		{
			float translationX = element.getX() + offsetX - leftPenWidth / 3;
			float translationY= element.getY() + offsetY - topOffset;
			grx.translate(translationX, translationY);
			float scaleX = 1;
			float scaleY = (height + (topOffset + bottomOffset))/ height; 
			grx.scale(scaleX, scaleY);
			grx.drawLine(0, 0, 0, height);
			grx.setTransform(oldTx);
			Rectangle newRect = new Rectangle(Math.round((translationX-leftPenWidth/2)*scaleX),Math.round(translationY*scaleY), Math.round(leftPenWidth+leftPenWidth/3),height);
			Border updatedBorder = updateBorder(Location.LEFT, newRect);
			if (updatedBorder.getSelected()){
				Color oldColor = grx.getColor();
				Stroke oldStroke = grx.getStroke();
				grx.setColor(Color.black);
				grx.setStroke(dashedStroke);
				grx.drawRect(newRect.x-1, newRect.y-1, newRect.width+5, newRect.height+2);
				grx.setStroke(oldStroke);
				grx.setColor(oldColor);
			}
			grx.translate(element.getX() + offsetX + leftPenWidth / 3, element.getY() + offsetY + topOffset / 3);
			if(height > (topOffset + bottomOffset) / 3)
			{
				grx.scale(1, (height - (topOffset + bottomOffset) / 3)/ height);
			}
			grx.drawLine(0,0,0,	height);
			grx.setTransform(oldTx);

		} else {
			float translationX = element.getX() + offsetX + BorderOffset.getOffset(leftPen);
			float translationY= element.getY() + offsetY - topOffset;
			grx.translate(translationX, translationY);
			float scaleX = 1;
			float scaleY = (height + topOffset + bottomOffset)/ height; 
			grx.scale(scaleX, scaleY);
			grx.drawLine(0,	0, 0,	height);
			grx.setTransform(oldTx);
			Rectangle newRect = new Rectangle(Math.round((translationX-leftPenWidth/2)*scaleX),Math.round(translationY*scaleY), Math.round(leftPenWidth),height);
			Border updatedBorder = updateBorder(Location.LEFT, newRect);
			if (updatedBorder.getSelected()){
				Stroke oldStroke = grx.getStroke();
				Color oldColor = grx.getColor();
				grx.setColor(Color.black);
				grx.setStroke(dashedStroke);
				grx.drawRect(newRect.x-3, newRect.y-2, newRect.width+6, newRect.height+4);
				grx.setStroke(oldStroke);
				grx.setColor(oldColor);
			}
		}
	}
	
	protected void drawRightPen(Graphics2D grx, JRPen topPen, JRPen bottomPen, JRPen rightPen, JRPrintElement element, int offsetX, int offsetY)
		{
			Stroke rightStroke = JRPenUtil.getStroke(rightPen, BasicStroke.CAP_BUTT);
			int height = element.getHeight();
			int width = element.getWidth();
			float topOffset = topPen.getLineWidth().floatValue() / 2 - BorderOffset.getOffset(topPen);
			float bottomOffset = bottomPen.getLineWidth().floatValue() / 2 - BorderOffset.getOffset(bottomPen);
			float rightPenWidth = 7.0f;
			LineStyleEnum lineStyle;
			if (rightStroke != null && height > 0)
			{
				rightPenWidth = rightPen.getLineWidth().floatValue();
				lineStyle = rightPen.getLineStyleValue();
				grx.setStroke(rightStroke);
				grx.setColor(rightPen.getLineColor());
			} else {
				grx.setStroke(new BasicStroke(7));
				grx.setColor(new Color(255,255,255,255));
				lineStyle = LineStyleEnum.SOLID;
			}
			AffineTransform oldTx = grx.getTransform();
			if (lineStyle == LineStyleEnum.DOUBLE)
			{
				float translationX = element.getX() + offsetX + width + rightPenWidth / 3;
				float translationY=  element.getY() + offsetY - topOffset;
				grx.translate(translationX, translationY);
				float scaleX = 1;
				float scaleY = (height + topOffset + bottomOffset)/ height; 
				grx.scale(scaleX, scaleY);
				grx.drawLine(0, 0, 0, height);
				grx.setTransform(oldTx);
				Rectangle newRect = new Rectangle(Math.round((translationX-rightPenWidth)*scaleX),Math.round(translationY*scaleY), Math.round(rightPenWidth+rightPenWidth/3),height);
				Border updatedBorder = updateBorder(Location.RIGHT, newRect);
				if (updatedBorder.getSelected()){
					Color oldColor = grx.getColor();
					Stroke oldStroke = grx.getStroke();
					grx.setColor(Color.black);
					grx.setStroke(dashedStroke);
					grx.drawRect(newRect.x-1, newRect.y-1, newRect.width+5, newRect.height+2);
					grx.setStroke(oldStroke);
					grx.setColor(oldColor);
				}
				grx.translate(element.getX() + offsetX + width - rightPenWidth / 3, element.getY() + offsetY + topOffset / 3);
				if(height > (topOffset + bottomOffset) / 3)
				{
					grx.scale(1, (height - (topOffset + bottomOffset) / 3)/ height);
				}
				grx.drawLine(0, 0, 0, height);
				grx.setTransform(oldTx);
			}
			else
			{
				float translationX = element.getX() + offsetX + width - BorderOffset.getOffset(rightPen);
				float translationY=  element.getY() + offsetY - topOffset;
				grx.translate(translationX, translationY);
				float scaleX = 1;
				float scaleY = (height + topOffset + bottomOffset)/ height; 
				grx.scale(scaleX, scaleY);
				grx.drawLine(0,	0, 0,	height);
				Rectangle newRect = new Rectangle(Math.round((translationX-rightPenWidth/2)*scaleX),Math.round(translationY*scaleY), Math.round(topPen.getLineWidth()+rightPenWidth/2),height);
				Border updatedBorder = updateBorder(Location.RIGHT, newRect);
				grx.setTransform(oldTx);
				if (updatedBorder.getSelected()){
					Stroke oldStroke = grx.getStroke();
					Color oldColor = grx.getColor();
					grx.setColor(Color.black);
					grx.setStroke(dashedStroke);
					grx.drawRect(newRect.x-3, newRect.y-2, newRect.width+6, newRect.height+4);
					grx.setStroke(oldStroke);
					grx.setColor(oldColor);
				}
			}
		}
	
	protected void drawTopPen(Graphics2D grx, JRPen topPen, JRPen leftPen, JRPen rightPen, JRPrintElement element, int offsetX, int offsetY)
		{
			Stroke topStroke = JRPenUtil.getStroke(topPen, BasicStroke.CAP_BUTT);
			int width = element.getWidth();
			float leftOffset = leftPen.getLineWidth().floatValue() / 2 - BorderOffset.getOffset(leftPen);
			float rightOffset = rightPen.getLineWidth().floatValue() / 2 - BorderOffset.getOffset(rightPen);
			float topPenWidth = 7.0f;
			LineStyleEnum lineStyle;
			if (topStroke != null && width > 0)
			{
				topPenWidth = topPen.getLineWidth().floatValue();
				lineStyle = topPen.getLineStyleValue();
				grx.setStroke(topStroke);
				grx.setColor(topPen.getLineColor());
			} else {
				grx.setStroke(new BasicStroke(7));
				grx.setColor(new Color(255,255,255,255));
				lineStyle = LineStyleEnum.SOLID;
			}
			AffineTransform oldTx = grx.getTransform();
			if (lineStyle == LineStyleEnum.DOUBLE)
			{
				float translationX = element.getX() + offsetX - leftOffset;
				float translationY=  element.getY() + offsetY - topPenWidth / 3;
				grx.translate(translationX, translationY);
				float scaleX = ((width + leftOffset + rightOffset) / width);
				float scaleY = 1; 
				grx.scale(scaleX, scaleY);
				grx.drawLine(0, 0, width, 0);
				grx.setTransform(oldTx);
				Rectangle newRect = new Rectangle(Math.round(translationX*scaleX),Math.round((translationY-topPenWidth/3)*scaleY), width, Math.round(topPenWidth+topPenWidth/3));
				Border updatedBorder = updateBorder(Location.TOP, newRect);
				if (updatedBorder.getSelected()){
					Color oldColor = grx.getColor();
					Stroke oldStroke = grx.getStroke();
					grx.setColor(Color.black);
					grx.setStroke(dashedStroke);
					grx.drawRect(newRect.x-1, newRect.y-1, newRect.width+5, newRect.height+2);
					grx.setStroke(oldStroke);
					grx.setColor(oldColor);
				}
				grx.translate(element.getX() + offsetX + leftOffset / 3, element.getY() + offsetY + topPenWidth / 3);
				if(width > (leftOffset + rightOffset) / 3)
				{
					grx.scale((width - (leftOffset + rightOffset) / 3)/ width,1);
				}
				grx.drawLine(0,	0, width,	0);
				grx.setTransform(oldTx);
			}
			else
			{
				float translationX = element.getX() + offsetX - leftOffset;
				float translationY=  element.getY() + offsetY + BorderOffset.getOffset(topPen);
				grx.translate(translationX,translationY);
				float scaleX = (width + leftOffset + rightOffset)/ width;
				float scaleY = 1; 
				grx.scale(scaleX,scaleY);
				grx.drawLine(0, 0, width, 0);
				Rectangle newRect = new Rectangle(Math.round(translationX*scaleX),Math.round((translationY-topPenWidth/2)*scaleY), width, Math.round(topPenWidth));
				Border updatedBorder = updateBorder(Location.TOP, newRect);
				grx.setTransform(oldTx);
				if (updatedBorder.getSelected()){
					Stroke oldStroke = grx.getStroke();
					Color oldColor = grx.getColor();
					grx.setColor(Color.black);
					grx.setStroke(dashedStroke);
					grx.drawRect(newRect.x-3, newRect.y-2, newRect.width+6, newRect.height+4);
					grx.setStroke(oldStroke);
					grx.setColor(oldColor);
				}
			}
		}

	protected void drawBottomPen(Graphics2D grx, JRPen leftPen, JRPen bottomPen, JRPen rightPen, JRPrintElement element, int offsetX, int offsetY)
		{
			Stroke bottomStroke = JRPenUtil.getStroke(bottomPen, BasicStroke.CAP_BUTT);
			int width = element.getWidth();
			int height = element.getHeight();
			float leftOffset = leftPen.getLineWidth().floatValue() / 2 - BorderOffset.getOffset(leftPen);
			float rightOffset = rightPen.getLineWidth().floatValue() / 2 - BorderOffset.getOffset(rightPen);
			float bottomPenWidth = 7.0f;
			LineStyleEnum lineStyle;
			if (bottomStroke != null && width > 0)
			{
				lineStyle = bottomPen.getLineStyleValue();
				grx.setStroke(bottomStroke);
				grx.setColor(bottomPen.getLineColor());
			} else {
				grx.setStroke(new BasicStroke(7));
				grx.setColor(new Color(255,255,255,255));
				lineStyle = LineStyleEnum.SOLID;
			}
			AffineTransform oldTx = grx.getTransform();
			if (lineStyle == LineStyleEnum.DOUBLE)
			{
				float translationX = element.getX() + offsetX - leftOffset;
				float translationY=  element.getY() + offsetY + height + bottomPenWidth / 3;
				grx.translate(translationX, translationY);
				float scaleX = (width + leftOffset + rightOffset)/ width;
				float scaleY = 1; 
				grx.scale(scaleX, scaleY);
				grx.drawLine(0, 0, width, 0);
				Rectangle newRect = new Rectangle(Math.round(translationX*scaleX),Math.round((translationY-bottomPen.getLineWidth())*scaleY), width, Math.round(bottomPen.getLineWidth()+bottomPenWidth/3));
				Border updatedBorder = updateBorder(Location.BOTTOM, newRect);
				grx.setTransform(oldTx);
				if (updatedBorder.getSelected()){
					Color oldColor = grx.getColor();
					Stroke oldStroke = grx.getStroke();
					grx.setColor(Color.black);
					grx.setStroke(dashedStroke);
					grx.drawRect(newRect.x-1, newRect.y-1, newRect.width+5, newRect.height+2);
					grx.setStroke(oldStroke);
					grx.setColor(oldColor);
				}
				grx.translate(element.getX() + offsetX + leftOffset / 3, element.getY() + offsetY + height - bottomPenWidth / 3);
				if(width > (leftOffset + rightOffset) / 3)
				{
					grx.scale((width - (leftOffset + rightOffset) / 3)/ width,1);
				}
				grx.drawLine(0,	0, width, 0);
				grx.setTransform(oldTx);
			}
			else
			{
				float translationX = element.getX() + offsetX - leftOffset;
				float translationY=  element.getY() + offsetY + height - BorderOffset.getOffset(bottomPen);
				grx.translate(translationX, translationY);
				float scaleX = (width + leftOffset + rightOffset)/ width;
				float scaleY = 1; 
				grx.scale(scaleX, scaleY);
				grx.drawLine(0, 0, width, 0);
				Rectangle newRect = new Rectangle(Math.round(translationX*scaleX),Math.round((translationY-bottomPen.getLineWidth()/2)*scaleY), width, Math.round(bottomPen.getLineWidth()));
				Border updatedBorder = updateBorder(Location.BOTTOM, newRect);
				grx.setTransform(oldTx);
				if (updatedBorder.getSelected()){
					Stroke oldStroke = grx.getStroke();
					Color oldColor = grx.getColor();
					grx.setColor(Color.black);
					grx.setStroke(dashedStroke);
					grx.drawRect(newRect.x-3, newRect.y-2, newRect.width+6, newRect.height+4);
					grx.setStroke(oldStroke);
					grx.setColor(oldColor);
				}
			}
		}
	
}
