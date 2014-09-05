/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * Licensed under commercial Jaspersoft Subscription License Agreement
 ******************************************************************************/
package com.jaspersoft.studio.components.bridge.model.command;

import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.components.bridge.model.MBridge;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.command.CreateElementCommand;
import com.jaspersoft.studio.model.frame.MFrame;

/**
 * Create command for the Bridge component element.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class CreateBridgeCommand extends CreateElementCommand {

	public CreateBridgeCommand(ANode destNode, MGraphicElement srcNode,
			Rectangle position, int index) {
		super(destNode, srcNode, position, index);
	}

	public CreateBridgeCommand(MBand destNode, MGraphicElement srcNode,
			int index) {
		super(destNode, srcNode, index);
	}

	public CreateBridgeCommand(MElementGroup destNode, MGraphicElement srcNode,
			int index) {
		super(destNode, srcNode, index);
	}

	public CreateBridgeCommand(MFrame destNode, MGraphicElement srcNode,
			int index) {
		super(destNode, srcNode, index);
	}

	public CreateBridgeCommand(MFrame destNode, MGraphicElement srcNode,
			Rectangle position, int index) {
		super(destNode, srcNode, position, index);
	}

	@Override
	protected void createObject() {
		if (jrElement == null) {
			srcNode = new MBridge();
			jrElement = srcNode.createJRElement(jasperDesign);
		}
		if (jrElement != null) {
			setElementBounds();
		}
	}
}
