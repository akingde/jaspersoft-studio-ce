package com.jaspersoft.studio.data.sql.model.query;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.model.ANode;

public class MGroupBy extends AMKeyword {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MGroupBy(ANode parent) {
		super(parent, "GROUP BY", null);
	}

}
