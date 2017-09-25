/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.layout;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.ANode;

import net.sf.jasperreports.engine.JRPropertiesMap;

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
	public boolean showAdditionalControlsOnChild(JRPropertiesMap elementProperties, JRPropertiesMap parentProperties) {
		return false;
	}

	@Override
	public boolean showAdditionalControlsOnNode(JRPropertiesMap elementProperties, JRPropertiesMap parentProperties) {
		return false;
	}
	
	@Override
	public boolean allowChildBoundChange(ANode resizedNode, Rectangle oldBounds, Rectangle newBounds) {
		return true;
	}
	
	@Override
	public Command activate(ANode selectedNode) {
		return null;
	}
	
	@Override
	public Command deactivate(ANode selectedNode) {
		return null;
	}
	
	@Override
	public boolean isSelectable(ANode selectedNode) {
		return true;
	}
	
	@Override
	public int getInsertPosition(ANode container, Point dropPosition) {
		return -1;
	}
}
