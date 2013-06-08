package com.jaspersoft.studio.data.sql.model.query;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.model.ANode;

public class MHaving extends AMKeyword {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MHaving(ANode parent) {
		super(parent, "HAVING", null);
		noSqlIfEmpty = true;
	}

}
