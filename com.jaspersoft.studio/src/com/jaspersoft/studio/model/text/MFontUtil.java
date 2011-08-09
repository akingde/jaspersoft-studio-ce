package com.jaspersoft.studio.model.text;

import com.jaspersoft.studio.model.APropertyNode;

import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignFont;

public class MFontUtil {
	public static MFont getMFont(MFont mfont, JRFont jrfont, JRStyle style, APropertyNode node) {
		if (mfont == null) {
			if (jrfont == null)
				jrfont = new JRDesignFont(style);
			mfont = new MFont(jrfont);
			node.setChildListener(mfont);
		}
		return mfont;
	}

	public static JRFont setMFont(Object value) {
		if (value != null && value instanceof MFont)
			return (JRFont) ((MFont) value).getValue();
		return null;
	}
}
