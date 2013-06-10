package com.jaspersoft.studio.data.sql.model.query.select;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.data.sql.model.query.AMKeyword;
import com.jaspersoft.studio.model.ANode;

public class MSelect extends AMKeyword {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MSelect(ANode parent) {
		super(parent, AMKeyword.SELECT_KEYWORD, null);
	}

	@Override
	public String toSQLString() {
		return getValue() + " ";
	}

}
