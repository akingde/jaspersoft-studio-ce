/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.callout.pin.command;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.callout.MCallout;
import com.jaspersoft.studio.callout.pin.MPin;

public class DeletePinCommand extends Command {
	private MCallout parent;
	private MPin mpin;

	public DeletePinCommand(MCallout parent, MPin child) {
		super("Delete Pin");
		this.parent = parent;
		this.mpin = child;
	}

	@Override
	public void execute() {
		parent.removePinConnection(mpin.getSourceConnections());
		parent.removeChild(mpin);
		parent.removeChild(mpin.getSourceConnections());
		parent.setPropertyValue("", "");
	}

	@Override
	public void undo() {
		parent.addPinConnection(mpin.getSourceConnections());
		mpin.setParent(parent, -1);
		parent.addChild(mpin.getSourceConnections());
		parent.setPropertyValue("", "");
	}

	@Override
	public boolean canExecute() {
		return true;
	}
}
