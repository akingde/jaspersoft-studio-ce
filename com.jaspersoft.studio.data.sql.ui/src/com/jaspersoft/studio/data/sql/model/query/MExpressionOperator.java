package com.jaspersoft.studio.data.sql.model.query;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.model.ANode;

public class MExpressionOperator extends AMKeyword {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MExpressionOperator(ANode parent) {
		super(parent, AMKeyword.AND_OPERATOR, null);
	}

}
