/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.parameter;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.parameter.MParameters;

import net.sf.jasperreports.crosstabs.JRCrosstab;
import net.sf.jasperreports.engine.JRConstants;

public class MCrosstabParameters extends MParameters<JRCrosstab> {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MCrosstabParameters(ANode parent, JRCrosstab jrDataset,
			String property) {
		super(parent, jrDataset, property);
	}
	
	@Override	
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

}
