/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.parameter;

import net.sf.jasperreports.crosstabs.JRCrosstab;
import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.parameter.MParameters;

public class MCrosstabParameters extends MParameters<JRCrosstab> {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MCrosstabParameters(ANode parent, JRCrosstab jrDataset,
			String property) {
		super(parent, jrDataset, property);
	}

}
