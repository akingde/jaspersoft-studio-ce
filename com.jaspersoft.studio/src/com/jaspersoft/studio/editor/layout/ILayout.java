package com.jaspersoft.studio.editor.layout;

import java.util.Map;

import net.sf.jasperreports.engine.JRElement;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

public interface ILayout {
	public static final String KEY = "com.jaspersoft.studio.layout";

	public Map<JRElement, Rectangle> layout(JRElement[] elements, Dimension c);
}
