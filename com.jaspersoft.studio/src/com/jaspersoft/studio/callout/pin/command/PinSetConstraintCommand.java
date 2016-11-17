/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.callout.pin.command;

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.callout.pin.MPin;

public class PinSetConstraintCommand extends Command {
	private MPin mcallout;
	private Rectangle location;
	private Rectangle oldLocation;

	public PinSetConstraintCommand(MPin mcallout, Rectangle location) {
		super("Move or Resize a Pin");
		this.mcallout = mcallout;
		this.location = location;
	}

	@Override
	public void execute() {
		if (oldLocation == null) {
			oldLocation = new Rectangle();
			oldLocation.x = (Integer) mcallout.getPropertyValue(JRDesignElement.PROPERTY_X);
			oldLocation.y = (Integer) mcallout.getPropertyValue(JRDesignElement.PROPERTY_Y);
		}

		mcallout.setPropertyValue(JRDesignElement.PROPERTY_X, location.x);
		mcallout.setPropertyValue(JRDesignElement.PROPERTY_Y, location.y);
	}

	@Override
	public void undo() {
		mcallout.setPropertyValue(JRDesignElement.PROPERTY_X, oldLocation.x);
		mcallout.setPropertyValue(JRDesignElement.PROPERTY_Y, oldLocation.y);
	}
}
