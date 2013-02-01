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

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.server.ServerIconDescriptor;

/**
 * Model node that represents a data adapter resource that 
 * can be persisted on a JasperServer instance.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class MDataAdapter extends AFileResource {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MDataAdapter(ANode parent, ResourceDescriptor rd, int index) {
		super(parent, rd, index);
	}
	
	private static IIconDescriptor iconDescriptor;

	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new ServerIconDescriptor("dataadapter-file"); //$NON-NLS-1$
		return iconDescriptor;
	}

	@Override
	public IIconDescriptor getThisIconDescriptor() {
		return getIconDescriptor();
	}

	public static ResourceDescriptor createDescriptor(ANode parent) {
		// Data adapter are XML file...
		ResourceDescriptor rd = MResource.createDescriptor(parent);
		rd.setWsType(ResourceDescriptor.TYPE_XML_FILE);
		return rd;
	}
}
