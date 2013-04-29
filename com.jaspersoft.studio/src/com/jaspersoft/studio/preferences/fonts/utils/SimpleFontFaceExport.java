/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.preferences.fonts.utils;

import java.awt.Font;

import net.sf.jasperreports.engine.fonts.FontFace;

public class SimpleFontFaceExport implements FontFace {

	/**
	 * 
	 */
	private String file;
	private Font font;

	/**
	 * 
	 */
	public SimpleFontFaceExport(String file) {
		this.file = file;
	}

	/**
	 * 
	 */
	public String getName() {
		return font.getName();
	}

	/**
	 * 
	 */
	public String getFile() {
		return file;
	}

	/**
	 * 
	 */
	public Font getFont() {
		return font;
	}

}
