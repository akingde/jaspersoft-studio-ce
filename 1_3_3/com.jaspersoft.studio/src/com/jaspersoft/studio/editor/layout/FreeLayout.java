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

	@Override
	public String getName() {
		return "Free Layout";
	}

	@Override
	public String getToolTip() {
		return "Free Layout";
	}

	@Override
	public String getIcon() {
		return "icons/layout.png";
	}

}
