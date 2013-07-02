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
package com.jaspersoft.studio.data.sql.model;

import java.util.UUID;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.data.sql.SQLActivator;
import com.jaspersoft.studio.model.ANode;

public class MDBObjects extends ANode {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	transient private ImageDescriptor icon;
	private String image;
	protected String tooltip;
	private String id;

	public MDBObjects(ANode parent, String value, String image) {
		super(parent, -1);
		setValue(value);
		this.image = image;
		id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	@Override
	public String getValue() {
		return (String) super.getValue();
	}

	@Override
	public String getToolTip() {
		String name = getValue();
		if (tooltip != null)
			name += "\n" + tooltip;
		return name;
	}

	@Override
	public ImageDescriptor getImagePath() {
		if (icon == null && image != null)
			icon = SQLActivator.getInstance().getImageDescriptor(image);
		return icon;
	}

	@Override
	public String getDisplayText() {
		return getValue();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof MDBObjects && ((MDBObjects) obj).getId().equals(getId());
	}

	public int hashCode() {
		return getId().hashCode();
	};
}
