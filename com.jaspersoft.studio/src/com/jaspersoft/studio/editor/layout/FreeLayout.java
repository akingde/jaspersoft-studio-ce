/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.layout;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRElement;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.messages.Messages;

public class FreeLayout extends AbstractLayout {

	@Override
	public Map<JRElement, Rectangle> layout(JRElement[] elements, Dimension c) {
		return new HashMap<JRElement, Rectangle>();
	}

	@Override
	public String getName() {
		return Messages.FreeLayout_name;
	}

	@Override
	public String getToolTip() {
		return Messages.FreeLayout_tooltip;
	}

	@Override
	public String getIcon() {
		return "icons/layout.png"; //$NON-NLS-1$
	}

	@Override
	public Map<JRElement, Rectangle> getLayoutPosition(JRElement[] elements, Dimension parentSize) {
		return null;
	}
}
