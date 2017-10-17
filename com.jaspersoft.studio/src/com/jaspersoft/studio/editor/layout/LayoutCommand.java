/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.layout;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

public class LayoutCommand extends Command {
	private Map<JRElement, Rectangle> map = new HashMap<JRElement, Rectangle>();
	private ILayout layout;
	private JRElementGroup container;
	private Dimension size;
	private JasperDesign jd;
	
	public LayoutCommand(JasperDesign jd, JRElementGroup container, ILayout layout, Dimension size) {
		super();
		this.size = size;
		this.layout = layout;
		this.container = container;
		this.jd = jd;
	}

	@Override
	public void execute() {
		if (layout != null && container != null)
			map = layout.layout(jd, container, container.getElements(), size);
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
