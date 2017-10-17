/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;

public class MDummy extends ANode {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MDummy(ANode parent) {
		super(parent, -1);
	}

	public ImageDescriptor getImagePath() {
		return JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/hourglass.png"); //$NON-NLS-1$
	}

	public String getDisplayText() {
		return Messages.MDummy_1;
	}

}
