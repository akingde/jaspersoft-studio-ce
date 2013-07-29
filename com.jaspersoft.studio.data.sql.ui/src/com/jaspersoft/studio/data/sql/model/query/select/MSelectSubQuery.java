package com.jaspersoft.studio.data.sql.model.query.select;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.data.sql.model.query.AMQueryAliased;
import com.jaspersoft.studio.model.ANode;

public class MSelectSubQuery extends AMQueryAliased<String> {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MSelectSubQuery(ANode parent) {
		this(parent, -1);
	}

	public MSelectSubQuery(ANode parent, int index) {
		super(parent, "(", null, index);
	}

	@Override
	public String getToolTip() {
		return getValue() + addAlias();
	}
}
