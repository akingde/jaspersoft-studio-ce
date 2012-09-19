package com.jaspersoft.studio.property.section.graphic;

import java.awt.Graphics2D;

import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.base.JRBasePrintText;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.editor.java2d.J2DGraphics;
import com.jaspersoft.studio.model.ILineBox;

class LineBoxRectangle extends RectangleFigure {
	
	private LineBoxDrawer bd;
	
	private BordersSection section;
	
	public LineBoxRectangle(LineBoxDrawer drawer, BordersSection section){
		bd = drawer;
		this.section = section;
	}
	
	

	@Override
	public void paint(Graphics graphics) {
		try {
			if (graphics instanceof J2DGraphics) {
				Graphics2D graphics2d = ((J2DGraphics) graphics).getGraphics2D();

				Rectangle b = getBounds();

				JRPrintElement pe = new JRBasePrintText(null);
				pe.setX(b.x + 10);
				pe.setY(b.y + 10);
				pe.setWidth(b.width - 20);
				pe.setHeight(b.height - 20);
				if (section.getElement() instanceof ILineBox && section.getElement() != null)
					bd.drawBox(graphics2d, ((ILineBox) section.getElement()).getBoxContainer().getLineBox(), pe);
			} else {
				graphics.drawRectangle(0, 0, 100, 100);
			}
		} catch (Exception e) {
			// when a font is missing exception is thrown by DrawVisitor
			// FIXME: maybe draw something, else?
			e.printStackTrace();
		}
	}
}