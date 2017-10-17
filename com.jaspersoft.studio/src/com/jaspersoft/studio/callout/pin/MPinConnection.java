/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.callout.pin;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.callout.MCallout;
import com.jaspersoft.studio.model.ANode;

public class MPinConnection extends ANode {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MPinConnection(MCallout callout, MPin pin) {
		pin.setmPinConnection(this);
		callout.addPinConnection(this);
	}

	@Override
	public ImageDescriptor getImagePath() {
		return null;
	}

	@Override
	public String getDisplayText() {
		return null;
	}
}
