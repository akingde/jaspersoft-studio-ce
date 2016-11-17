/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.callout.pin.command;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Point;

import com.jaspersoft.studio.callout.MCallout;
import com.jaspersoft.studio.callout.pin.MPin;
import com.jaspersoft.studio.callout.pin.MPinConnection;
import com.jaspersoft.studio.utils.SelectionHelper;

import net.sf.jasperreports.engine.design.JRDesignElement;

/**
 * Create a pin for a callout in the position the mouse was the last time
 * a right click was done. This is done to create the pin in the position
 * the contextual menu was opened. The calculation of the point is done
 * when the command is executed. If this position can't be found then
 * it will be used the current mouse pointer position
 * 
 * @author Orlandin Marco
 */
public class CreatePinOnMouseLocationCommand extends Command {
	
	private Point location = null;
	
	private MCallout parent;
	
	private MPin mpin;

	public CreatePinOnMouseLocationCommand(MCallout parent) {
		super("Create Pin");
		this.parent = parent;
	}

	@Override
	public void execute() {
		if (mpin == null)
			createObject();
		else {
			parent.addPinConnection(mpin.getSourceConnections());
			mpin.setParent(parent, -1);
			parent.addChild(mpin.getSourceConnections());
		}
		parent.setPropertyValue("", "");
	}

	private void createObject() {
		if (location == null){
			location = getPinLocation();
		}
		mpin = new MPin(parent, new org.eclipse.draw2d.geometry.Point(location.x, location.y));
		new MPinConnection(parent, mpin);
	}

	@Override
	public void undo() {
		parent.removePinConnection(mpin.getSourceConnections());
		parent.removeChild(mpin);
		parent.removeChild(mpin.getSourceConnections());
		parent.setPropertyValue("", "");
	}

	@Override
	public boolean canExecute() {
		return true;
	}
	
	/**
	 * Return the position where the pin should be placed. First it try
	 * to get the position where the last right click was done, if not available
	 * it will use the current cursor position. If even this is not available then it
	 * will place the pin in a fixed position near the callout
	 * 
	 * @return a not null position for the pin
	 */
	protected Point getPinLocation(){
		Point location = new Point(0, 0);
		//search the last right click position
		Point cursorPosition = SelectionHelper.getCursorRelativePositionOnClick(3);
		if (cursorPosition == null){
			cursorPosition = SelectionHelper.getCursorCurrentRelativePosition();
 		} 
		if (cursorPosition != null){
			location.x = cursorPosition.x;
			location.y = cursorPosition.y;
		} else {
			location.x = 20 + (Integer) parent.getPropertyValue(JRDesignElement.PROPERTY_X);
			location.y = -24 + (Integer) parent.getPropertyValue(JRDesignElement.PROPERTY_Y);
		}
		return location;
	}
	
}
