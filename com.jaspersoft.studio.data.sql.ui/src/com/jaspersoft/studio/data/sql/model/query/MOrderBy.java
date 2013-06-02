package com.jaspersoft.studio.data.sql.model.query;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.model.ANode;

public class MOrderBy extends AMKeyword {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MOrderBy(ANode parent) {
		super(parent, "ORDER BY", null);
	}

}
