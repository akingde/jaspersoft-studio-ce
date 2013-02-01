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
package com.jaspersoft.studio.server.model;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.Activator;

public class MDummy extends ANode {

	public MDummy(ANode parent) {
		super(parent, -1);
	}

	public ImageDescriptor getImagePath() {
		return Activator.getDefault().getImageDescriptor("icons/hourglass.png");
	}

	public String getDisplayText() {
		return "Pending ...";
	}

}
