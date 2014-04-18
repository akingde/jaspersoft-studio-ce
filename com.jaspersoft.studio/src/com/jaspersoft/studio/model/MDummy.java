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
