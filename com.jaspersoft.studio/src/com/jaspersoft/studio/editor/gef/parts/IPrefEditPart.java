package com.jaspersoft.studio.editor.gef.parts;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

public interface IPrefEditPart {
	public static final RGB DEFAULT_MARGINCOLOR = new RGB(170, 168, 255);

	public Color getMarginColor();
}
