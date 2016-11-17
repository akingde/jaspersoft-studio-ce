/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.model.query.select;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.viewers.StyledString;

import com.jaspersoft.studio.data.sql.QueryWriter;
import com.jaspersoft.studio.data.sql.model.ISubQuery;
import com.jaspersoft.studio.data.sql.model.query.AMQueryAliased;
import com.jaspersoft.studio.model.ANode;

public class MSelectSubQuery extends AMQueryAliased<String> implements
		ISubQuery {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MSelectSubQuery(ANode parent) {
		this(parent, -1);
	}

	public MSelectSubQuery(ANode parent, int index) {
		super(parent, "(", null, index);
	}

	@Override
	public StyledString getStyledDisplayText() {
		StyledString st = new StyledString("(...)");
		addAlias(st);
		return st;
	}

	@Override
	public String getToolTip() {
		return "(" + QueryWriter.writeSubQuery(this) + ")" + addAlias();
	}

}
