/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.model.query;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.model.ANode;

public class MUnion extends AMKeyword {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MUnion(ANode parent, int index) {
		super(parent, SET_OPERATOR_UNION, null, index);
		noSqlIfEmpty = true;
	}

	@Override
	public String toSQLString() {
		return super.toSQLString() + "\n";
	}
}
