/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
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
		int rest = c.height - h * elements.length;
		for (JRElement el : elements) {
			JRDesignElement del = (JRDesignElement) el;
			map.put(el, new Rectangle(el.getX(), el.getY(), el.getWidth(), el.getHeight()));
			del.setX(x);
			del.setY(y);
			del.setWidth(w);
			del.setHeight(h + rest);
			// if last grab free pixels
			y += h + rest;
			if (rest > 0)
				rest = 0;
			LayoutManager.layout(map, el);
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
		return "icons/layout-h.png";
	}
}
