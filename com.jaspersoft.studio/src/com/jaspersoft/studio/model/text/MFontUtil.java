/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
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
