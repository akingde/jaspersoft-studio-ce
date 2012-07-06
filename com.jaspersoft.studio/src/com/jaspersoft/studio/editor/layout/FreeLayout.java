package com.jaspersoft.studio.editor.layout;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRElement;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

public class FreeLayout implements ILayout {

	@Override
	public Map<JRElement, Rectangle> layout(JRElement[] elements, Dimension c) {
		return new HashMap<JRElement, Rectangle>();
	}

}
