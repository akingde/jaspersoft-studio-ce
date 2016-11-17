/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.text;

import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignFont;

import com.jaspersoft.studio.model.APropertyNode;

public class MFontUtil {
	public static MFont getMFont(MFont mfont, JRFont jrfont, JRStyle style, APropertyNode node) {
		if (mfont == null) {
			if (jrfont == null)
				jrfont = new JRDesignFont(style);
			mfont = new MFont(jrfont);
			mfont.setJasperConfiguration(node.getJasperConfiguration());
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
