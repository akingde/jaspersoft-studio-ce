/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.layout;

import net.sf.jasperreports.engine.JRPropertiesMap;

import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.model.ANode;

/**
 * Empty class that provide some default implementation to the interface ILayout.
 * This implementation are for the layout that doesn't provide configuration interfaces
 * to the children of the container
 */
public abstract class AbstractLayout implements ILayout {
	
	@Override
	public ILayoutUIProvider getControlsProvider() {
		return null;
	}
	
	@Override
	public boolean showAdditionalControls(JRPropertiesMap elementProperties, JRPropertiesMap parentProperties) {
		return false;
	}

	@Override
	public boolean allowChildBoundChange(ANode resizedNode, Rectangle oldBounds, Rectangle newBounds) {
		return true;
	}
}
