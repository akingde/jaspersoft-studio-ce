package com.jaspersoft.studio.editor.layout;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

public class VerticalRowLayout implements ILayout {
	public Map<JRElement, Rectangle> layout(JRElement[] elements, Dimension c) {
		Map<JRElement, Rectangle> map = new HashMap<JRElement, Rectangle>();
		int x = 0;
		int y = 0;
		int w = c.width;
		int h = (int) Math.floor((float) c.height / elements.length);

		for (JRElement el : elements) {
			JRDesignElement del = (JRDesignElement) el;
			map.put(el, new Rectangle(el.getX(), el.getY(), el.getWidth(), el.getHeight()));
			del.setX(x);
			del.setY(y);
			del.setWidth(w);
			del.setHeight(h);
			// if last grab free pixels
			y += h;
		}
		return map;
	}

	@Override
	public String getName() {
		return "Vertical Layout";
	}

	@Override
	public String getToolTip() {
		return "Vertical Layout";
	}

	@Override
	public String getIcon() {
		return "icons/layout-3.png";
	}
}
