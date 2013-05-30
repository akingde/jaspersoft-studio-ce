package com.jaspersoft.studio.data.sql.model.query;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.data.sql.model.AMSQLObject;
import com.jaspersoft.studio.model.ANode;

public class MHaving extends AMSQLObject {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MHaving(ANode parent, String value) {
		super(parent, value, "");
	}

}
