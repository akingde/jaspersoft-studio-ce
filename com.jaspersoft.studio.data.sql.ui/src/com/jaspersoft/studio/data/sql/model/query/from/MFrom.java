package com.jaspersoft.studio.data.sql.model.query.from;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.data.sql.model.query.AMKeyword;
import com.jaspersoft.studio.model.ANode;

public class MFrom extends AMKeyword {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MFrom(ANode parent) {
		super(parent, "FROM", null);
		noSqlIfEmpty = true;
	}

}