package com.jaspersoft.studio.data.sql.model.metadata;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.data.sql.model.AMSQLObject;
import com.jaspersoft.studio.model.ANode;

public class MSqlSchema extends AMSQLObject {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MSqlSchema(ANode parent, String value) {
		super(parent, value, "icons/database.png");
	}

}
