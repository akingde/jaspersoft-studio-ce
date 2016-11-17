/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.model.query.select;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.data.sql.model.query.AMKeyword;
import com.jaspersoft.studio.model.ANode;

public class MSelect extends AMKeyword {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MSelect(ANode parent, String value, int index) {
		super(parent, value, null, index);
	}

	public MSelect(ANode parent) {
		this(parent, AMKeyword.SELECT_KEYWORD, -1);
	}

	@Override
	public String toSQLString() {
		return getValue() + " ";
	}

}
