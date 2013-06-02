package com.jaspersoft.studio.preferences.fonts.utils;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.wb.swt.SWTResourceManager;

public class BoldStyler extends Styler {
	private String fForegroundColorName;
	private String fBackgroundColorName;

	public BoldStyler(String foregroundColorName, String backgroundColorName) {
		fForegroundColorName = foregroundColorName;
		fBackgroundColorName = backgroundColorName;
	}

	@Override
	public void applyStyles(TextStyle textStyle) {
		textStyle.font = SWTResourceManager.getBoldFont(new Font(Display.getDefault(), FontUtils.getTextEditorFontData()));
		ColorRegistry colorRegistry = JFaceResources.getColorRegistry();
		if (fForegroundColorName != null)
			textStyle.foreground = colorRegistry.get(fForegroundColorName);
		if (fBackgroundColorName != null)
			textStyle.background = colorRegistry.get(fBackgroundColorName);
	}
}
