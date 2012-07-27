/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.layout;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

public class LayoutCommand extends Command {
	private Map<JRElement, Rectangle> map = new HashMap<JRElement, Rectangle>();
	private ILayout layout;
	private JRElementGroup container;
	private Dimension size;

	public LayoutCommand(JRElementGroup container, ILayout layout, Dimension size) {
		super();
		this.size = size;
		this.layout = layout;
		this.container = container;
	}

	@Override
	public void execute() {
		map = layout.layout(container.getElements(), size);
	}

	@Override
	public void undo() {
		for (JRElement el : map.keySet()) {
			Rectangle r = map.get(el);
			el.setX(r.x);
			((JRDesignElement) el).setY(r.y);
			el.setWidth(r.width);
			((JRDesignElement) el).setHeight(r.height);
		}
	}
}
